// BindingsTest.java
package com.jdojo.script;

import javax.script.Bindings;
import javax.script.SimpleBindings;

public class BindingsTest {
	public static void main(String[] args) {
		// Create a Bindings instance
		Bindings params = new SimpleBindings();

		// Add some key-value pairs
		params.put("msg", "Hello");
		params.put("year", 1969);

		// Get values
		Object msg = params.get("msg");
		Object year = params.get("year");
		System.out.println("msg = " + msg);
		System.out.println("year = " + year);

		// Remove year from Bindings
		params.remove("year");
		year = params.get("year");

		boolean containsYear = params.containsKey("year");
		System.out.println("year = " + year);
		System.out.println("params contains year = " + containsYear);
	}
}
