// CompilableTest .java
package com.jdojo.script;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CompilableTest  {
	public static void main(String[] args) {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		if (!(engine instanceof Compilable)) {
			System.out.println("Script compilation not supported.");
			return;
		}

		// Cast the engine reference to the Compilable type
		Compilable comp = (Compilable)engine;

		try {
			// Compile a script
			String script = "print(n1 + n2)";
			CompiledScript cScript = comp.compile(script);

			// Store n1 and n2 script variables in a Bindings
			Bindings scriptParams = engine.createBindings();
			scriptParams.put("n1", 2);
			scriptParams.put("n2", 3);
			cScript.eval(scriptParams);

			// Execute the script again with different values for n1 and n2
			scriptParams.put("n1", 9);
			scriptParams.put("n2", 7);
			cScript.eval(scriptParams);
		}
		catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
