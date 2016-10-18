// SimpleURLContentReader.java
package com.jdojo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class SimpleURLContentReader {
	public static String getURLContent(String urlStr) {
		BufferedReader br = null;
		try {
			URL url = new URL(urlStr);

			// Get the input stream 
			InputStream ins = url.openStream();

			// Wrap input stream into a reader 
			br = new BufferedReader(new InputStreamReader(ins));

			StringBuilder sb = new StringBuilder();
			String msg = null;
			while ((msg = br.readLine()) != null) {
				sb.append(msg);
				sb.append("\n"); // Append a new line 
			}

			return sb.toString();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// If we get here it means there was an error 
		return null;
	}

	public static void main(String[] args) {
		String urlStr = "http://localhost:8080/docsapp/" + 
		                "echo_params.jsp?id=123";
		String content = getURLContent(urlStr);
		System.out.println(content);
	}
}
