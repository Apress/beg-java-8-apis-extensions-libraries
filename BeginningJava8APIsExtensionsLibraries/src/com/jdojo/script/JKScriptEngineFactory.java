// JKScriptEngineFactory.java
package com.jdojo.script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public class JKScriptEngineFactory implements ScriptEngineFactory {	
	@Override
	public String getEngineName() {
		return "JKScript Engine";
	}

	@Override
	public String getEngineVersion() {
		return "1.0";
	}

	@Override
	public List<String> getExtensions() {
		return Collections.unmodifiableList(Arrays.asList("jks"));
	}

	@Override
	public List<String> getMimeTypes() {
		return Collections.unmodifiableList( 
		            Arrays.asList("text/jkscript") );		
	}

	@Override
	public List<String> getNames() {
		List<String> names = new ArrayList<>();
		names.add("jks");
		names.add("JKScript");
		names.add("jkscript");
		return Collections.unmodifiableList(names);
	}

	@Override
	public String getLanguageName() {
		return "JKScript";
	}

	@Override
	public String getLanguageVersion() {
		return "1.0";
	}

	@Override
	public Object getParameter(String key) {		
		switch (key) {
			case ScriptEngine.ENGINE:
				return getEngineName();
			case ScriptEngine.ENGINE_VERSION:
				return getEngineVersion();
			case ScriptEngine.NAME:
				return getEngineName();
			case ScriptEngine.LANGUAGE:
				return getLanguageName();
			case ScriptEngine.LANGUAGE_VERSION:
				return getLanguageVersion();
			case "THREADING":
				return "MULTITHREADED";
			default:
				return null;
		}
	}

	@Override
	public String getMethodCallSyntax(String obj, String m, String[] p) {
		return "Not implemented";
	}

	@Override
	public String getOutputStatement(String toDisplay) {
	    return "Not implemented";
	}

	@Override
	public String getProgram(String[] statements) {
		return "Not implemented";
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return new JKScriptEngine(this);
	}
}
