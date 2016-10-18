// AccessingScriptVariable.java
package com.jdojo.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AccessingScriptVariable {
	public static void main(String[] args) {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// Write a script that declares a global variable named year and 
		// assign it a value of 1969.
		String script = "var year = 1969";

		try {
			// Execute the script
			engine.eval(script);

			// Get the year global variable from the engine
			Object year = engine.get("year");

			// Print the class name and the value of the variable year
			System.out.println("year's class:" + 
				year.getClass().getName());
			System.out.println("year's value:" + year);
		}
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
