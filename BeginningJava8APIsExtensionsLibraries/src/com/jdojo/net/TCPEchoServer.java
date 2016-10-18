// TCPEchoServer.java
package com.jdojo.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {
	public static void main(String[] args) {
		try {
			// Create a Server socket 
			ServerSocket serverSocket = 
				new ServerSocket(12900, 100, InetAddress.getByName("localhost"));
			System.out.println("Server started at: " + serverSocket);

			// Keep accepting client connections in an infinite loop 
			while (true) {
				System.out.println("Waiting for a connection...");

				// Accept a connection 
				final Socket activeSocket = serverSocket.accept();

				System.out.println("Received a connection from " + activeSocket);

				// Create a new thread to handle the new connection 
				Runnable runnable = () -> handleClientRequest(activeSocket);				
				new Thread(runnable).start(); // start a new thread 
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void handleClientRequest(Socket socket) {
		BufferedReader socketReader = null;
		BufferedWriter socketWriter = null;

		try {
			// Create a buffered reader and writer for the socket
			socketReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			socketWriter = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));

			String inMsg = null;
			while ((inMsg = socketReader.readLine()) != null) {
				System.out.println("Received from client: " + inMsg);

				// Echo the received message to the client 
				String outMsg = inMsg;
				socketWriter.write(outMsg);
				socketWriter.write("\n");
				socketWriter.flush();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				socket.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
