// TCPEchoClient.java
package com.jdojo.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPEchoClient {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader socketReader = null;
		BufferedWriter socketWriter = null;
		try {
			// Create a socket that will connect to localhost 
			// at port 12900. Note that the server must also be
			// running at localhost and 12900. 
			socket = new Socket("localhost", 12900);

			System.out.println("Started client socket at " +
				socket.getLocalSocketAddress());

			// Create a buffered reader and writer using the socket's 
			// input and output streams 
			socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			socketWriter = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));

			// Create a buffered reader for user's input 
			BufferedReader consoleReader =
				new BufferedReader(new InputStreamReader(System.in));

			String promptMsg = "Please enter a message (Bye to quit):";
			String outMsg = null;

			System.out.print(promptMsg);
			while ((outMsg = consoleReader.readLine()) != null) {
				if (outMsg.equalsIgnoreCase("bye")) {
					break;
				}

				// Add a new line to the message to the server,
				// because the server reads one line at a time.
				socketWriter.write(outMsg);
				socketWriter.write("\n");
				socketWriter.flush();

				// Read and display the message from the server 
				String inMsg = socketReader.readLine();
				System.out.println("Server: " + inMsg);

				System.out.println(); // Print a blank line 
				System.out.print(promptMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			// Finally close the socket 
			if (socket != null) {
				try {
					socket.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
