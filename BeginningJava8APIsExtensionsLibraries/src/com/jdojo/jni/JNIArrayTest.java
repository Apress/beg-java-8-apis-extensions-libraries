// JNIArrayTest.java
package com.jdojo.jni;

import java.util.Arrays;

public class JNIArrayTest {
	static {
		System.loadLibrary("beginningjava");
	}

	// Three native method declarations
	public native int sum(int[] num);
	public native String concat(String[] str);
	public native int[] increment(int[] num, int incrementBy);

	public static void main(String[] args) {
		JNIArrayTest test = new JNIArrayTest();
	
		int[] num = {1, 2, 3, 4, 5};
		String[] str = {"One", "Two", "Three", "Four", "Five" } ;
	
		System.out.println("Original Number Array: " + Arrays.toString(num));
	
		System.out.println("Original String Array: " + Arrays.toString(str));
		int sum = 0;
		sum = test.sum(num);
		System.out.println("Sum: " + sum);
	
		String concatenatedStr = test.concat(str);
		System.out.println("Concatenated String: " + concatenatedStr);
	
		int increment = 5;
		int[] incrementedNum = test.increment(num, increment);
		System.out.println("Increment By: " + increment);
		System.out.println("Incremented Number Arrays: " + 
		                   Arrays.toString(incrementedNum));
	}
}
