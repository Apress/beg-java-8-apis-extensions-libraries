// HelloScripting.java
package com.jdojo.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HelloScripting {
	public static void main(String[] args) {
		// Create a script engine manager
		ScriptEngineManager manager = new ScriptEngineManager();

		// Obtain a Nashorn script engine from the manager
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// Store the script in a String
		String script = "print('Hello Scripting!')";
		
		try {
			// Execute the script
			engine.eval(script);
		} 
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
