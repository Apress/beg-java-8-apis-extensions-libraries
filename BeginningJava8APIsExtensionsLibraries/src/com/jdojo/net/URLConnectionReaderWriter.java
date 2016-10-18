// URLConnectionReaderWriter.java
package com.jdojo.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class URLConnectionReaderWriter {
	public static String getURLContent(String urlStr, String input) {
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			URL url = new URL(urlStr);
			URLConnection connection = url.openConnection();

			// Must call setDoOutput(true) to indicate that we will write to 
			// the connection. By default, it is false. By default, 
			// setDoInput() is set to true. 
			connection.setDoOutput(true);

			// Now, connect to the remote object 
			connection.connect();

			// Write data to the URL first before reading the response 
			OutputStream ous = connection.getOutputStream();
			bw = new BufferedWriter(new OutputStreamWriter(ous));
			bw.write(input);
			bw.flush();
			bw.close();

			// Must be placed after writing the data. Otherwise, it will 
			// result in error, because if write is performed, read must be 
			// performed after the write 
			printRequestHeaders(connection);

			InputStream ins = connection.getInputStream();

			// Wrap the input stream into a reader 
			br = new BufferedReader(new InputStreamReader(ins));

			StringBuffer sb = new StringBuffer();
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

		// If we arrive here it means there was an error 
		return null;
	}

	public static void printRequestHeaders(URLConnection connection) {
		Map headers = connection.getHeaderFields();
		System.out.println("Request Headers are:");
		System.out.println(headers);
		System.out.println();
	}

	public static void main(String[] args) {
		// Change the URL to point to the echo_params.jsp page
		// on your web server
		String urlStr = "http://www.jdojo.com/docsapp/echo_params.jsp";
		String query = null;
		try {
			// Encode the query. We need to encode only the value of the 
			// name parameter. Other names and values are fine 
			query = "id=789&name=" +  URLEncoder.encode("John & Co.", "utf-8");

			// Get the content and display it on the console 
			String content = getURLContent(urlStr, query);
			System.out.println(content);
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
