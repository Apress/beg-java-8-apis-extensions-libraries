// CustomScriptOutput.java
package com.jdojo.script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CustomScriptOutput {

	public static void main(String[] args) {
		// Get the Nashorn engine
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// Print the absolute path of the output file
		File outputFile = new File("jsoutput.txt");
		System.out.println("Script output will be written to " +
				outputFile.getAbsolutePath());

		FileWriter writer = null;

		try {
			writer = new FileWriter(outputFile);

			// Set a custom output writer for the engine
			ScriptContext defaultCtx = engine.getContext();
			defaultCtx.setWriter(writer);

			// Execute a script
			String script = "print('Hello custom output writer')";
			engine.eval(script);
		} 
		catch (IOException | ScriptException e) {
			e.printStackTrace();
		} 
		finally {
			if (writer != null) {
				try {
					writer.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
