// JNIJavaObjectAccessTest.java
package com.jdojo.jni;

public class JNIJavaObjectAccessTest {
	static {
		System.loadLibrary("beginningjava");
	}

	private int num = 10;
	private static int count = 1001;
	
	public void objectCallBack() {
		System.out.println("Inside objectCallBack() method.");
	}
	
	public static void classCallBack() {
		System.out.println("Inside classCallBack() method.");
	}
	
	public native void callBack();
	
	public int hashCode() {
		return -9999;
	}
	
	public static void main(String[] args) {
		JNIJavaObjectAccessTest test = new JNIJavaObjectAccessTest();
	
		System.out.println("Before calling native method...");	
		System.out.println("num = " + test.num);
		System.out.println("count = " + count);
	
		// Call native method 
		test.callBack();
	
		System.out.println("After calling native method...");
		System.out.println("num = " + test.num);
		System.out.println("count = " + count);	
	}
}
