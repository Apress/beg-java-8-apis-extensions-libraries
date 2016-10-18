// DGCEchoServer.java
package com.jdojo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DGCEchoServer {	
	public static void main(String[] args) {
		DatagramChannel server = null;
		
		try {
			// Create a datagram channel and bind it to localhost at port 8989 
			server = DatagramChannel.open();			
			InetSocketAddress sAddr = new InetSocketAddress("localhost", 8989);
			server.bind(sAddr);

			ByteBuffer buffer = ByteBuffer.allocate(1024);
						
			// Wait in an infinite loop for a client to send data 
			while (true) {
				System.out.println("Waiting for a message from" + 
				                   " a remote host at " + sAddr);

				// Wait for a client to send a message 
				SocketAddress remoteAddr = server.receive(buffer);

				// Prepare the buffer to read the message 
				buffer.flip();

				// Convert the buffer data into a String 
				int limits = buffer.limit();
				byte bytes[] = new byte[limits];
				buffer.get(bytes, 0, limits);
				String msg = new String(bytes);

				System.out.println("Client at " + remoteAddr + 
 				                   " says: " + msg);

				// Reuse the buffer to echo the message to the client 
				buffer.rewind();	

				// Send the message back to the client 
				server.send(buffer, remoteAddr);

				// Prepare the buffer to receive the next message 
				buffer.clear();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			// Close the channel 
			if (server != null) {
				try {
					server.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
