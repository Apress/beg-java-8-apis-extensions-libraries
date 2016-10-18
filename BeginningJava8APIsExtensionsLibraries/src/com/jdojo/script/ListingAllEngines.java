// ListingAllEngines.java
package com.jdojo.script;

import java.util.List;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class ListingAllEngines {
	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
	
		// Get the list of all available engines
		List<ScriptEngineFactory> list = manager.getEngineFactories();

		// Print the details of each engine
		for (ScriptEngineFactory f : list) {
			System.out.println("Engine Name:" + f.getEngineName());
			System.out.println("Engine Version:" + 
				f.getEngineVersion());
			System.out.println("Language Name:" + f.getLanguageName());
			System.out.println("Language Version:" + 
				f.getLanguageVersion());
			System.out.println("Engine Short Names:" + f.getNames());
			System.out.println("Mime Types:" + f.getMimeTypes());
			System.out.println("----------------------------");
		}
	}
}
