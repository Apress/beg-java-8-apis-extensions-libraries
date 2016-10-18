// UDPMultiCastSender.java
package com.jdojo.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMultiCastSender {
	public static void main(String[] args) {
		int mcPort = 18777;
		String mcIPStr = "230.1.1.1";
		DatagramSocket udpSocket = null;

		try {
			// Create a datagram socket 
			udpSocket = new DatagramSocket();

			// Prepare a message 
			InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);

			byte[] msg = "Hello multicast socket".getBytes();
			DatagramPacket packet = 
				new DatagramPacket(msg, msg.length);
			packet.setAddress(mcIPAddress);
			packet.setPort(mcPort);
			udpSocket.send(packet);

			System.out.println("Sent a multicast message.");
			System.out.println("Exiting application");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (udpSocket != null) {
				try {
					udpSocket.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
