// ScriptContextTest.java
package com.jdojo.script;

import java.util.List;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;
import static javax.script.ScriptContext.ENGINE_SCOPE;
import static javax.script.ScriptContext.GLOBAL_SCOPE;

public class ScriptContextTest {	
	public static void main(String[] args) {
		// Create a script context
		ScriptContext ctx = new SimpleScriptContext();
		
		// Get the list of scopes supported by the script context
		List<Integer> scopes = ctx.getScopes();
		System.out.println("Supported Scopes: " + scopes);
		
		// Add three key-value pairs to the engine scope bindings		
		ctx.setAttribute("year", 1969, ENGINE_SCOPE);
		ctx.setAttribute("month", 9, ENGINE_SCOPE);
		ctx.setAttribute("day", 19, ENGINE_SCOPE);
		
		// Add a global scope Bindings to the context
		Bindings globalBindings = new SimpleBindings();
		ctx.setBindings(globalBindings, GLOBAL_SCOPE);
		
		// Add two key-value pairs to the global scope bindings
		ctx.setAttribute("year", 1982, GLOBAL_SCOPE);
		ctx.setAttribute("name", "Boni", GLOBAL_SCOPE);
		
		// Get the value of year without specifying the scope
		int yearValue = (Integer)ctx.getAttribute("year"); 
		System.out.println("yearValue = " + yearValue);
		
		// Get the value of name
		String nameValue = (String)ctx.getAttribute("name");
		System.out.println("nameValue = " + nameValue);
		
		// Get the value of year from engine  and global scopes
		int engineScopeYear = (Integer)ctx.getAttribute("year", ENGINE_SCOPE);
		int globalScopeYear = (Integer)ctx.getAttribute("year", GLOBAL_SCOPE);

		System.out.println("engineScopeYear = " + engineScopeYear);
		System.out.println("globalScopeYear = " + globalScopeYear);
	}
}
