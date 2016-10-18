// HelloJNI.java
package com.jdojo.jni;

public class HelloJNI {
	static {
		// Load the shared library using its name only 
		System.loadLibrary("beginningjava");
	}

	// Declare the native method 
	public native void hello();

	public static void main(String[] args) {
		// Create a HelloJNI object 
		HelloJNI helloJNI = new HelloJNI();

		// Call the native method 
		helloJNI.hello();
	}
}
