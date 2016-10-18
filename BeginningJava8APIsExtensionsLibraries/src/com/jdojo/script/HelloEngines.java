// HelloEngines.java
package com.jdojo.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HelloEngines {
	public static void main(String[] args) {
		// Get the script engine manager
		ScriptEngineManager manager = new ScriptEngineManager();

		// Try executing scripts in Nashorn, Groovy, Jython, and JRuby
		execute(manager, "JavaScript", "print('Hello JavaScript')");
		execute(manager, "Groovy", "println('Hello Groovy')");
		execute(manager, "jython", "print 'Hello Jython'");
		execute(manager, "jruby", "puts('Hello JRuby')");
	}

	public static void execute(ScriptEngineManager manager, 
	                           String engineName, 
	                           String script) {
		// Try getting the engine
		ScriptEngine engine = manager.getEngineByName(engineName);
		if (engine == null) {
			System.out.println(engineName + " is not available.");
			return;
		}

		// If we get here, it means we have the engine installed.
		// So, run the script
		try {
			engine.eval(script);
		} 
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
