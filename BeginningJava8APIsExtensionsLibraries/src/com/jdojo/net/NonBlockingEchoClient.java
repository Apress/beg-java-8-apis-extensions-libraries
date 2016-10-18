// NonBlockingEchoClient.java
package com.jdojo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingEchoClient {
	private static BufferedReader userInputReader = null;

	public static void main(String[] args) throws Exception {
		InetAddress serverIPAddress = InetAddress.getByName("localhost");
		int port = 19000;
		InetSocketAddress serverAddress = new InetSocketAddress(serverIPAddress, port);

		// Get a selector 
		Selector selector = Selector.open();

		// Create and configure a client socket channel 
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(serverAddress);

		// Register the channel for connect, read and write operations 
		int operations = 
			SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE;
		channel.register(selector, operations);

		userInputReader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			if (selector.select() > 0) {
				boolean doneStatus = processReadySet(selector.selectedKeys());
				if (doneStatus) {
					break;
				}
			}
		}

		channel.close();
	}

	public static boolean processReadySet(Set readySet) throws Exception {
		SelectionKey key = null;
		Iterator iterator = null;
		iterator = readySet.iterator();
		while (iterator.hasNext()) {
			// Get the next ready selection key object 
			key = (SelectionKey) iterator.next();

			// Remove the key from the ready key set 
			iterator.remove();

			if (key.isConnectable()) {
				boolean connected = processConnect(key);
				if (!connected) {
					return true; // Exit 
				}
			}

			if (key.isReadable()) {
				String msg = processRead(key);
				System.out.println("[Server]: " + msg);
			}

			if (key.isWritable()) {
				String msg = getUserInput();
				if (msg.equalsIgnoreCase("bye")) {
					return true; // Exit 
				}
				processWrite(key, msg);
			}

		}

		return false; // Not done yet 
	}

	public static boolean processConnect(SelectionKey key) {
		SocketChannel channel = (SocketChannel) key.channel();

		try {
			// Call the finishConnect() in a loop as it is non-blocking 
			// for your channel 
			while (channel.isConnectionPending()) {
				channel.finishConnect();
			}
		}
		catch (IOException e) {
			// Cancel the channel's registration with the selector 
			key.cancel();
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static String processRead(SelectionKey key) throws Exception {
		SocketChannel sChannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		sChannel.read(buffer);
		buffer.flip();
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuffer = decoder.decode(buffer);
		String msg = charBuffer.toString();
		return msg;
	}

	public static void processWrite(SelectionKey key, String msg) throws IOException {
		SocketChannel sChannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
		sChannel.write(buffer);
	}

	public static String getUserInput() throws IOException {
		String promptMsg = "Please enter a message(Bye to quit): ";
		System.out.print(promptMsg);
		String userMsg = userInputReader.readLine();
		return userMsg;
	}
}
