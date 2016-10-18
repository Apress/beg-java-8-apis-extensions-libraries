// UDPMultiCastReceiver.java
package com.jdojo.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMultiCastReceiver {
	public static void main(String[] args) {
		int mcPort = 18777;
		String mcIPStr = "230.1.1.1";
		MulticastSocket mcSocket = null;
		InetAddress mcIPAddress = null;
		try {
			mcIPAddress = InetAddress.getByName(mcIPStr);
			mcSocket = new MulticastSocket(mcPort);
			System.out.println("Multicast Receiver running at:" + 
			                   mcSocket.getLocalSocketAddress());

			// Join the group 
			mcSocket.joinGroup(mcIPAddress);

			DatagramPacket packet =
				new DatagramPacket(new byte[1024], 1024);

			while (true) {
				System.out.println("Waiting for a multicast" + 
				                   " message...");

				mcSocket.receive(packet);

				String msg = new String(packet.getData(),
				                        packet.getOffset(),
				                        packet.getLength());
				System.out.println("[Multicast Receiver] Received:" + 
				                   msg);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (mcSocket != null) {
				try {
					mcSocket.leaveGroup(mcIPAddress);
					mcSocket.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
