// JKScriptTest.java
package com.jdojo.script;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JKScriptTest {	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Create JKScript engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JKScript");
		if (engine == null) {
			System.out.println("JKScript engine is not available. ");
			System.out.println("Add jkscript.jar to CLASSPATH.");
			return;
		}
		
		// Test scripts as String
		testString(manager, engine);
		
		// Test scripts as a Reader
		testReader(manager, engine);
	}
	
	public static void testString(ScriptEngineManager manager, 
			                      ScriptEngine engine) {
		try {	
			// Use simple expressions with numeric literals
			String script = "12.8 + 15.2";
			Object result = engine.eval(script);
			System.out.println(script + " = " + result);

			script = "-90.0 - -10.5";
			result = engine.eval(script);
			System.out.println(script + " = " + result);

			script = "5 * 12";
			result = engine.eval(script);
			System.out.println(script + " = " + result);

			script = "56.0 / -7.0";
			result = engine.eval(script);
			System.out.println(script + " = " + result);
			
			// Use global scope bindings variables
			manager.put("num1", 10.0);
			manager.put("num2", 20.0);
			script = "num1 + num2";
			result = engine.eval(script);           
			System.out.println(script + " = " + result);
			
			// Use global and engine scopes bindings. num1 from 
			// engine scope and num2 from global scope will be used.
			engine.put("num1", 70.0);
			script = "num1 + num2";
			result = engine.eval(script);
			System.out.println(script + " = " + result);
			
			// Try mixture of number literal and bindings. num1 from
			// the engine scope bindings will be used
			script = "10 + num1";
			result = engine.eval(script);
			System.out.println(script + " = " + result);
		}
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static void testReader(ScriptEngineManager manager, 
			                      ScriptEngine engine) {
		try {
			Path scriptPath = Paths.get("jkscript.txt").toAbsolutePath();
			if (!Files.exists(scriptPath)) {
				System.out.println(scriptPath + 
					" script file does not exist.");
				return;
			}
			
			try(Reader reader = Files.newBufferedReader(scriptPath);) {
				Object result = engine.eval(reader);
				System.out.println("Result of " + 
					scriptPath + " = " + result);
			}
		}
		catch(ScriptException | IOException e) {
			e.printStackTrace();
		}
	}
}
