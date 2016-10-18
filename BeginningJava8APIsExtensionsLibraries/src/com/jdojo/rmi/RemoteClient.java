// RemoteClient.java
package com.jdojo.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.ZonedDateTime;

public class RemoteClient {
	public static void main(String[] args) {
		SecurityManager secManager = System.getSecurityManager();
		if (secManager == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			//Locate the registry
			Registry registry = 
				LocateRegistry.getRegistry("localhost", 1099);

			String name = "MyRemoteUtility";
			RemoteUtility remoteUtilStub = 
				(RemoteUtility) registry.lookup(name);

			// Echo a message from the server
			String msg = "Hello";
			String reply = remoteUtilStub.echo(msg);
			System.out.println("Echo Message: " + msg + 
			                   ", Echo reply: " + reply);

			// Get the server date and time with the zone info
			ZonedDateTime serverTime = remoteUtilStub.getServerTime();			
			System.out.println("Server Time: " + serverTime);

			// Add two integers
			int n1 = 101;
			int n2 = 207;
			int sum = remoteUtilStub.add(n1, n2);
			System.out.println(n1 + " + " + n2 + " = " + sum);
		} 
		catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
