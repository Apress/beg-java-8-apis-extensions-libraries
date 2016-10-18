// ReaderAsSource.java
package com.jdojo.script;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ReaderAsSource {
	public static void main(String[] args) {
		// Construct the script file path		
		String scriptFileName = "helloscript.js";		
		Path scriptPath = Paths.get(scriptFileName);
		
		// Make sure the script file exists. If not, print the full path of 
		// the script file and terminate the program.		
		if (! Files.exists(scriptPath) ) {
			System.out.println(scriptPath.toAbsolutePath() + 
				" does not exist.");
			return;
		}
		
		// Get the Nashorn script engine	
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
	
		try {
			// Get a Reader for the script file
			Reader scriptReader = Files.newBufferedReader(scriptPath);
			
			// Execute the script in the file
			engine.eval(scriptReader);
		}		
		catch (IOException | ScriptException e) {
			e.printStackTrace(); 
		}
	}
}
