// CustomContext.java
package com.jdojo.script;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import static javax.script.SimpleScriptContext.ENGINE_SCOPE;
import static javax.script.SimpleScriptContext.GLOBAL_SCOPE;

public class CustomContext {
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// Add n1 to Bindings of the manager, which will be shared
		// by all engines as their global scope Bindings
		manager.put("n1", 100);

		// Prepare the script
		String script = "var sum = n1 + n2;" + 
		                "print(msg + " +
		                "' n1=' + n1 + ', n2=' + n2 + " + 
		                "', sum=' + sum);";

		// Add n2 to the engine scope of the default context of the engine
		engine.put("n2", 200);
		engine.put("msg", "Using the default context:");
		engine.eval(script);

		// Use a Bindings to execute the script
		Bindings bindings = engine.createBindings();
		bindings.put("n2", 300);
		bindings.put("msg", "Using a Bindings:");
		engine.eval(script, bindings);

		// Use a ScriptContext to execute the script
		ScriptContext ctx = new SimpleScriptContext();
		Bindings ctxGlobalBindings = engine.createBindings();
		ctx.setBindings(ctxGlobalBindings, GLOBAL_SCOPE);
		ctx.setAttribute("n1", 400, GLOBAL_SCOPE);
		ctx.setAttribute("n2", 500, ENGINE_SCOPE);
		ctx.setAttribute("msg", "Using a ScriptContext:", ENGINE_SCOPE);
		engine.eval(script, ctx);

		// Execute the script again using the default context to 
		// prove that the default context is unaffected.
		engine.eval(script);
	}
}
