// AsyncEchoClientSocket.java
package com.jdojo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.Future;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.nio.channels.AsynchronousSocketChannel;

public class AsyncEchoClientSocket {
	private static class Attachment {
		AsynchronousSocketChannel channel;
		ByteBuffer buffer;
		Thread mainThread;
		boolean isRead;
	}
	
	private static class ReadWriteHandler 
		implements CompletionHandler<Integer, Attachment> {

		@Override
		public void completed(Integer result, Attachment attach) {
			if (attach.isRead) {
				attach.buffer.flip();
				
				// Get the text read from the server 
				Charset cs = Charset.forName("UTF-8");

				int limits = attach.buffer.limit();
				byte bytes[] = new byte[limits];
				attach.buffer.get(bytes, 0, limits);
				String msg = new String(bytes, cs);

				// A read from the server was completed 
				System.out.format("Server Responded: %s%n", msg);
				
				// Prompt the user for another message 
				msg = this.getTextFromUser();
				if (msg.equalsIgnoreCase("bye")) {
					// Interrupt the main thread, so the program terminates 
					attach.mainThread.interrupt();
					return;
				}

				// Prepare buffer to be filled in again 
				attach.buffer.clear();
				byte[] data = msg.getBytes(cs);
				attach.buffer.put(data);

				// Prepared buffer to be read
				attach.buffer.flip();
				
				attach.isRead = false; // It is a write 
				
				// Write to the server 
				attach.channel.write(attach.buffer, attach, this);
			}
			else {
				// A write to the server was completed. Perform another 
				// read from the server 
				attach.isRead = true;
				
				// Prepare the buffer to be filled in 
				attach.buffer.clear();
				
				// Read from the server 
				attach.channel.read(attach.buffer, attach, this);
			}
		}

		@Override
		public void failed(Throwable e, Attachment attach) {
			e.printStackTrace();
		}
		
		private String getTextFromUser() {
			System.out.print("Please enter a message (Bye to quit):");
			String msg = null;
			
			BufferedReader consoleReader = 
				new BufferedReader(new InputStreamReader(System.in));
			try {
				msg = consoleReader.readLine();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
			return msg;
		}
	}
	
	public static void main(String[] args) {
		// Use a try-with-resources to open a channel
		try (AsynchronousSocketChannel channel
			= AsynchronousSocketChannel.open()) {
			// Connect the client to the server 
			String serverName = "localhost";
			int serverPort = 8989;
			SocketAddress serverAddr = 
				new InetSocketAddress(serverName, serverPort);
		
			Future<Void> result = channel.connect(serverAddr);
			System.out.println("Connecting to the server...");

			// Wait for the connection to complete 
			result.get();

			// Connection to the server is complete now 
			System.out.println("Connected to the server...");

			// Start reading from and writing to the server 
			Attachment attach = new Attachment();
			attach.channel = channel;
			attach.buffer = ByteBuffer.allocate(2048);
			attach.isRead = false;
			attach.mainThread = Thread.currentThread();
			
			// Place the "Hello" message in the buffer 
			Charset cs = Charset.forName("UTF-8");	
			String msg = "Hello";	
			byte[] data = msg.getBytes(cs);
			attach.buffer.put(data);
			attach.buffer.flip();
			
			// Write to the server 
			ReadWriteHandler readWriteHandler = new ReadWriteHandler();
			channel.write(attach.buffer, attach, readWriteHandler) ;
			
			// Let this thread wait for ever on its own death until interrupted 
			attach.mainThread.join();
		}
		catch (ExecutionException | IOException e) {
			e.printStackTrace();
		}
		catch(InterruptedException e) {
			System.out.println("Disconnected from the server.");
		}
	}
}
