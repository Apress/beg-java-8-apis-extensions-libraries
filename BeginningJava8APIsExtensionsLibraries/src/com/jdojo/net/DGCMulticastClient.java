// DGCMulticastClient.java
package com.jdojo.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

public class DGCMulticastClient {
	public static void main(String[] args) {		
		MembershipKey key = null;
		
		// Create, configure and bind the client datagram channel 		
		try (DatagramChannel client = 
				DatagramChannel.open(StandardProtocolFamily.INET)) {
			// Get the reference of a network interface 
			NetworkInterface interf = NetworkInterface.getByName(
				DGCMulticastUtil.MULTICAST_INTERFACE_NAME);
			
			
			client.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			client.bind(new InetSocketAddress(DGCMulticastUtil.MULTICAST_PORT));
			client.setOption(StandardSocketOptions.IP_MULTICAST_IF, interf);
			
			// Join the multicast group on the interf interface 
			InetAddress group = InetAddress.getByName(DGCMulticastUtil.MULTICAST_IP);
			key = client.join(group, interf);

			// Print some useful messages for the user 
			System.out.println("Joined the multicast group:" + key);
			System.out.println("Waiting for a message from the" + 
			                   " multicast group....");

			// Prepare a data buffer to receive a message from the multicast group 
			ByteBuffer buffer = ByteBuffer.allocate(1048);

			// Wait to receive a message from the multicast group 
			client.receive(buffer);

			// Convert the message in the ByteBuffer into a string 
			buffer.flip();
			int limits = buffer.limit();
			byte bytes[] = new byte[limits];
			buffer.get(bytes, 0, limits);
			String msg = new String(bytes);

			System.out.format("Multicast Message:%s%n", msg);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			// Drop the membership from the multicast group 
			if (key != null) {
				key.drop();
			}
		}
	}
}
