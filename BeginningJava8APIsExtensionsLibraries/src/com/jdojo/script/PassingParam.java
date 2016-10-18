// PassingParam.java
package com.jdojo.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class PassingParam {	
	public static void main(String[] args) {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
	
		// Store the script in a String. Here, msg is a variable		
		// that we have not declared in the script
		String script = "print(msg)";

		try {
			// Store a parameter named msg in the engine
			engine.put("msg", "Hello from Java program");
			
			// Execute the script
			engine.eval(script);
		}
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
