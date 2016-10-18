// ListNetworkInterfaces.java
package com.jdojo.net;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ListNetworkInterfaces {
	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface nif = e.nextElement();
				System.out.println("Name: " + nif.getName() + 
					", Supports Multicast: " + nif.supportsMulticast() + 
					", isUp(): " + nif.isUp()) ;
			}
		} 
		catch (SocketException ex) {
			ex.printStackTrace();
		}

	}
}
