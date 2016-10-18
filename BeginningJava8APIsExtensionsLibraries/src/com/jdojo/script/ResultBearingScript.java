// ResultBearingScript.java
package com.jdojo.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ResultBearingScript {
	public static void main(String[] args) throws ScriptException {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// Pass a Result object to the script. The script will store the
		// result of the script in the result object
		Result result = new Result();
		engine.put("result", result);

		// Store the script in a String
		String script = "3 + 4; result.setValue(101);";

		// Execute the script, which uses the passed in Result object to 
		// return a value
		engine.eval(script);

		// Use the result object to get the returned value from the script
		int returnedValue = result.getValue(); // Will be 101

		System.out.println("Returned value is " + returnedValue);
	}
}
