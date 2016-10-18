// UsingInterfaces.java
package com.jdojo.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class UsingInterfaces {
	public static void main(String[] args) {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// Make sure the script engine implements Invocable interface
		if (!(engine instanceof Invocable)) {
			System.out.println("Interface implementation in script"
				+ " is not supported.");
			return;
		}

		// Cast the engine reference to the Invocable type
		Invocable inv = (Invocable) engine;

		// Create the script for add() and subtract() functions
		String script = "function add(n1, n2) { return n1 + n2; } "
			+ "function subtract(n1, n2) { return n1 - n2; }";

		try {
			// Compile the script that will be stored in the engine
			engine.eval(script);

			// Get the interface implementation
			Calculator calc = inv.getInterface(Calculator.class);
			if (calc == null) {
				System.err.println("Calculator interface " + 
					"implementation not found.");
				return;
			}

			int result1 = calc.add(15, 10);
			System.out.println("add(15, 10) = " + result1);

			int result2 = calc.subtract(15, 10);
			System.out.println("subtract(15, 10) = " + result2);
		} 
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
