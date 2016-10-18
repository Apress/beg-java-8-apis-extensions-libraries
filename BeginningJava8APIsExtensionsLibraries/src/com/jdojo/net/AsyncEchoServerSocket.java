// AsyncEchoServerSocket.java
package com.jdojo.net;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.net.InetSocketAddress;
import java.nio.channels.CompletionHandler;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.AsynchronousServerSocketChannel;

public class AsyncEchoServerSocket {
	private static class Attachment {
		AsynchronousServerSocketChannel server;
		AsynchronousSocketChannel client;
		ByteBuffer buffer;	
		SocketAddress clientAddr;
		boolean isRead;
	}
	
	private static class ConnectionHandler implements 
			CompletionHandler<AsynchronousSocketChannel, Attachment> {
		@Override
		public void completed(AsynchronousSocketChannel client, Attachment attach) {
			try {
				// Get the client address 
				SocketAddress clientAddr = client.getRemoteAddress();
				System.out.format("Accepted a connection from %s%n", 					                  clientAddr);
				
				// Accept another connection 
				attach.server.accept(attach, this);
				
				// Handle the client connection by using an asyn read 
				ReadWriteHandler rwHandler = new ReadWriteHandler();
				Attachment newAttach = new Attachment();
				newAttach.server = attach.server;
				newAttach.client = client;
				newAttach.buffer = ByteBuffer.allocate(2048);
				newAttach.isRead = true;
				newAttach.clientAddr = clientAddr;				
				client.read(newAttach.buffer, newAttach, rwHandler);					} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void failed(Throwable e, Attachment attach) {
			System.out.println("Failed to accept a connection.");
			e.printStackTrace();
		}		
	}
	
	private static class ReadWriteHandler 
		implements CompletionHandler<Integer, Attachment> {
		@Override
		public void completed(Integer result, Attachment attach) {	
			if (result == -1) {				
				try {
					attach.client.close();
					System.out.format(
						"Stopped listening to the client %s%n",
						 attach.clientAddr);
				} 
				catch (IOException ex) {
					ex.printStackTrace();
				}
				return;
			}			
						
			if (attach.isRead) {	
				// A read to the client was completed 
				
				// Get the buffer ready to read from it 
				attach.buffer.flip();
			
				int limits = attach.buffer.limit();
				byte bytes[] = new byte[limits];				
				attach.buffer.get(bytes, 0, limits);
				Charset cs = Charset.forName("UTF-8");
				String msg = new String(bytes, cs);
				
				// Print the message from the client 
				System.out.format("Client at %s says: %s%n", 
				                  attach.clientAddr, msg);
				
				// Let us echo back the same message to the client 
				attach.isRead = false; // It is a write 
				
				// Prepare the buffer to be read again 
				attach.buffer.rewind(); 			
				
				// Write to the client 
				attach.client.write(attach.buffer, attach, this);
			}
			else {
				// A write to the client was completed. Perform 
				// another read from the client 
				attach.isRead = true;
				
				// Prepare the buffer to be filled in 
				attach.buffer.clear();				
				
				// Perform a read from the client 
				attach.client.read(attach.buffer, attach, this);
			}
		}

		@Override
		public void failed(Throwable e, Attachment attach) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {		 
		try (AsynchronousServerSocketChannel server = 
			AsynchronousServerSocketChannel.open()) {			
			// Bind the server to the localhost and the port 8989 
			String host = "localhost";
			int port = 8989;
			InetSocketAddress sAddr = 
			  new InetSocketAddress(host, port);
			server.bind(sAddr);			
			
			// Display a message that server is ready 
			System.out.format("Server is listening at %s%n", sAddr);			
			// Prepare the attachment 
			Attachment attach = new Attachment();
			attach.server = server;
			
			// Accept new connections 
			server.accept(attach, new ConnectionHandler());						
			try {
				// Wait until the main thread is interrupted 
				Thread.currentThread().join();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}			 
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
