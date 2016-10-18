// RemoteUtility.java
package com.jdojo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.ZonedDateTime;

public interface RemoteUtility extends Remote {
	// Echoes a string message back to the client
	String echo(String msg) throws RemoteException;

	// Returns the current date and time to the client
	ZonedDateTime getServerTime() throws RemoteException;

	// Adds two integers and returns the result to the client
	int add(int n1, int n2) throws RemoteException;
}
