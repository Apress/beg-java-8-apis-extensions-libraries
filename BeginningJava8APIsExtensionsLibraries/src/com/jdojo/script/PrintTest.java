// PrintTest.java
package com.jdojo.script;

public class PrintTest {	
	public void print(String str) {
		System.out.println("print(String): " + str);
	}
	
	public void print(Object obj) {
		System.out.println("print(Object): " + obj);
	}
	
	public void print(Double num) {
		System.out.println("print(Double): " + num);
	}
	
	public static void main(String[] args) {
		PrintTest pt = new PrintTest();
		Object[] list = new Object[]{"Hello", new Object(), 10.5};
		
		for(Object arg : list) {
			pt.print(arg);
		}
	}
}
