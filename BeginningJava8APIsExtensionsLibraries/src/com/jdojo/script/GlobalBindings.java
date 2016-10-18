// GlobalBindings.java
package com.jdojo.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GlobalBindings {
	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();

		// Add two numbers to the Bindings of the manager that will be
		// shared by all its engines
		manager.put("n1", 100);
		manager.put("n2", 200);

		// Create two JavaScript engines and add the name of the engine 
		// in the engine scope of the default context of the engines
		ScriptEngine engine1 = manager.getEngineByName("JavaScript");
		engine1.put("engineName", "Engine-1");

		ScriptEngine engine2 = manager.getEngineByName("JavaScript");
		engine2.put("engineName", "Engine-2");

		// Execute a script that adds two numbers and prints the result
		String script = "var sum = n1 + n2; "
			+ "print(engineName + ' - Sum = ' + sum)";

		try {
			// Execute the script in two engines
			engine1.eval(script);
			engine2.eval(script);

			// Now add a different value for n2 for each engine
			engine1.put("n2", 1000);
			engine2.put("n2", 2000);

			// Execute the script in two engines again
			engine1.eval(script);
			engine2.eval(script);
		}
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
