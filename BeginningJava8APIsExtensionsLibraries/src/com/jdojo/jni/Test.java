package com.jdojo.jni;

public class Test { 
	private native void sayHello();
	private native void printMsg(String msg);
	private native int[] increment(int[] num, int incrementValue);
	private native double myMethod(int i, String s[], String ss);
	private native double myMethod(double i, String s[], String ss);
	private native double myMethod(short i, String s[], String ss);
}
