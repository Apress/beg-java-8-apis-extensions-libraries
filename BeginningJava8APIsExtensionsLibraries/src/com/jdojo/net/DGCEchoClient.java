// DGCEchoClient.java
package com.jdojo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DGCEchoClient {
	public static void main(String[] args) {
		DatagramChannel client = null;
		try {
			// Create a new datagram channel 
			client = DatagramChannel.open();
			
			// Bind the client to any available local address and port 
			client.bind(null);
					
			// Prepare a message for the server 
			String msg = "Hello";
			ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());	
			InetSocketAddress serverAddress = 
				new InetSocketAddress("localhost", 8989);
			
			// Send the message to the server 
			client.send(buffer, serverAddress);
			
			// Reuse the buffer to receive a response from the server	 
			buffer.clear();
			
			// Wait for the server to respond 
			client.receive(buffer);
			
			// Prepare the buffer to read the message 
			buffer.flip();
			
			// Convert the buffer into a string			 
			int limits = buffer.limit();
			byte bytes[] = new byte[limits];
			buffer.get(bytes, 0, limits);
			String response = new String(bytes);

			// Print the server message on the standard output 
			System.out.println("Server responded: " + response);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			// Close the channel 
			if (client != null) {
				try {
					client.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}						
	}
}
