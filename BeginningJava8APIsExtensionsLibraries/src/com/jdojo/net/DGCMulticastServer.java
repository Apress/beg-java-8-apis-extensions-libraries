// DGCMulticastServer.java
package com.jdojo.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DGCMulticastServer {
	public static void main(String[] args) {
		// Get a datagram channel object to act as a server 
		try (DatagramChannel server = DatagramChannel.open()) {
			// Bind the server to any available local address 
			server.bind(null);

			// Set the network interface for outgoing multicast data 
			NetworkInterface interf = NetworkInterface.getByName(DGCMulticastUtil.MULTICAST_INTERFACE_NAME);

			server.setOption(StandardSocketOptions.IP_MULTICAST_IF, interf);

			// Prepare a message to send to the multicast group 
			String msg = "Hello from multicast!";
			ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

			// Get the multicast group reference to send data to 
			InetSocketAddress group
				= new InetSocketAddress(DGCMulticastUtil.MULTICAST_IP,
					DGCMulticastUtil.MULTICAST_PORT);

			// Send the message to the multicast group 
			server.send(buffer, group);
			
			System.out.println("Sent the multicast message: " + msg);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
