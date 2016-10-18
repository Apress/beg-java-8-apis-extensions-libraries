// InvokeMethod.java
package com.jdojo.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class InvokeMethod {
	public static void main(String[] args) {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// Make sure the script engine implements the Invocable interface
		if (!(engine instanceof Invocable)) {
			System.out.println("Invoking methods is not supported.");
			return;
		}

		// Cast the engine reference to the Invocable type
		Invocable inv = (Invocable) engine;

		try {
			// Declare a global object with an add() method
			String script = "var calculator = new Object();" + 
			    "calculator.add = function add(n1, n2){return n1 + n2;}";

			// Evaluate the script first
			engine.eval(script);

			// Get the calculator object reference created in the script
			Object calculator = engine.get("calculator");

			// Invoke the add() method on the calculator object
			Object result = inv.invokeMethod(calculator, "add", 30, 40);
			System.out.println("Result = " + result);
		} 
		catch (ScriptException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
