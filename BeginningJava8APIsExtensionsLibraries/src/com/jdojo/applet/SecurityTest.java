// SecurityTest.java
package com.jdojo.applet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SecurityTest {
	public static void main(String[] args) throws IOException {
		// Message to be written to the file
		String msg = "Testing Java filee permission security...";
		
		// Change the path C:\sec_demo.txt to conform to the 
		// syntax supported by your operating system
		Path filePath = Paths.get("C:\\sec_demo.txt");
		
		// Write message to the file
		Files.write(filePath, msg.getBytes());
		
		// Print a message 
		System.out.println("Test message written to " + filePath);
	}
}
