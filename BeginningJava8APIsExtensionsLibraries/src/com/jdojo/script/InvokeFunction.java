// InvokeFunction.java
package com.jdojo.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class InvokeFunction {	
	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
	
		// Make sure the script engine implements the Invocable interface
		if (!(engine instanceof Invocable)) {
			System.out.println("Invoking procedures is not supported.");
			return;
		}
		
		// Cast the engine reference to the Invocable type
		Invocable inv = (Invocable)engine;
		
		try {
			String script = "function add(n1, n2) { return n1 + n2; }";
			
			// Evaluate the script first
			engine.eval(script);
			
			// Invoke the add function	twice
			Object result1 = inv.invokeFunction("add", 30, 40);
			System.out.println("Result1 = " + result1);
			
			Object result2 = inv.invokeFunction("add", 10, 20);
			System.out.println("Result2 = " + result2);		
		}
		catch (ScriptException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
