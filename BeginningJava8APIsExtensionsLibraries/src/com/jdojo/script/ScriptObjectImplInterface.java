// ScriptObjectImplInterface.java
package com.jdojo.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptObjectImplInterface {
	public static void main(String[] args) {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
	
		// Make sure the engine implements the Invocable interface
		if (!(engine instanceof Invocable)) {
			System.out.println("Interface implementation in " + 
			                   "script is not supported.");
			return;
		}
	
		// Cast the engine reference to the Invocable type
		Invocable inv = (Invocable)engine;
		
		String script = "var calc = new Object(); " + 
			"calc.add = function add(n1, n2) {return n1 + n2; }; " + 
			"calc.subtract = function subtract(n1, n2) {return n1 - n2;};";

		try {
			// Compile and store the script in the engine
			engine.eval(script);

			// Get the reference of the global script object calc
			Object calc = engine.get("calc");
				  
			// Get the implementation of the Calculator interface
			Calculator calculator = inv.getInterface(calc, Calculator.class);
			if (calculator == null) {
				System.err.println("Calculator interface " + 
				                   "implementation not found.");
				return;
			}

			int result1 = calculator.add(15, 10);
			System.out.println( "add(15, 10) = " + result1);

			int result2 = calculator.subtract(15, 10);
			System.out.println("subtract(15, 10) = " + result2);
		}
		catch (ScriptException e) {
				e.printStackTrace();
		}
	}
}
