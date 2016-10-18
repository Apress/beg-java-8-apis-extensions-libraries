// JKScriptEngine.java
package com.jdojo.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class JKScriptEngine extends AbstractScriptEngine {
	private ScriptEngineFactory factory;

	public JKScriptEngine(ScriptEngineFactory factory) {
		this.factory = factory;
	}

	@Override
	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		try {
			Expression exp = new Expression(script, context);
			Object result = exp.eval();
			return result;
		} 
		catch (Exception e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public Object eval(Reader reader, ScriptContext context)
			throws ScriptException {
		// Read all lines from the Reader 
		BufferedReader br = new BufferedReader(reader);

		String script = "";
		String str = null;
		try {
			while ((str = br.readLine()) != null) {
				script = script + str;
			}
		} 
		catch (IOException e) {
			throw new ScriptException(e);
		}

		// Use the String version of eval()
		return eval(script, context);
	}

	@Override
	public Bindings createBindings() {
		return new SimpleBindings();
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return factory;
	}
}
