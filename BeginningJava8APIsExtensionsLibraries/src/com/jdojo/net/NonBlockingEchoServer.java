// NonBlockingEchoServer.java
package com.jdojo.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingEchoServer {
	public static void main(String[] args) throws Exception {
		InetAddress hostIPAddress = InetAddress.getByName("localhost");
		int port = 19000;

		// Get a selector 
		Selector selector = Selector.open();

		// Get a server socket channel 
		ServerSocketChannel ssChannel = ServerSocketChannel.open();

		// Make the server socket channel non-blocking and bind it to an address 
		ssChannel.configureBlocking(false);
		ssChannel.socket().bind(new InetSocketAddress(hostIPAddress, port));

		// Register a socket server channel with the selector for accept operation, 
		// so that it can be notified when a new connection request arrives 
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);

		// Now we will keep waiting in a loop for any kind of request that arrives
		// to the server - connection, read, or write request. If a connection request
		// comes in, we will accept the request and register a new socket channel with 
		// the selector for read and write operations. If read or write requests come 
		// in, we will forward that request to the registered channel. 
		while (true) {
			if (selector.select() <= 0) {
				continue;
			}
			processReadySet(selector.selectedKeys());
		}
	}

	public static void processReadySet(Set readySet) throws Exception {
		SelectionKey key = null;
		Iterator iterator = null;
		iterator = readySet.iterator();
		while (iterator.hasNext()) {
			// Get the next ready selection key object 
			key = (SelectionKey) iterator.next();

			// Remove the key from the ready key set 
			iterator.remove();

			// Process the key according to the operation it is ready for 
			if (key.isAcceptable()) {
				processAccept(key);
			}

			if (key.isReadable()) {
				String msg = processRead(key);
				if (msg.length() > 0) {
					echoMsg(key, msg);
				}
			}
		}
	}

	public static void processAccept(SelectionKey key) throws IOException {
		// This method call indicates that we got a new connection request. 
		// Accept the connection request and register the new socket channel with 
		// the selector, so that client can communicate on a new channel 
		ServerSocketChannel ssChannel =	(ServerSocketChannel)key.channel();
		SocketChannel sChannel = (SocketChannel) ssChannel.accept();
		sChannel.configureBlocking(false);

		// Register only for read. Our message is small and we write it 
		// back to the client as soon as we read it 
		sChannel.register(key.selector(), SelectionKey.OP_READ);
	}

	public static String processRead(SelectionKey key) throws Exception {
		SocketChannel sChannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int bytesCount = sChannel.read(buffer);
		String msg = "";

		if (bytesCount > 0) {
			buffer.flip();
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buffer);
			msg = charBuffer.toString();
			System.out.println("Received Message: " + msg);
		}

		return msg;
	}

	public static void echoMsg(SelectionKey key, String msg) throws IOException {
		SocketChannel sChannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
		sChannel.write(buffer);
	}
}
