// ReadUserHomeApplet.java
package com.jdojo.applet;

import javax.swing.JApplet;
import javax.swing.JTextArea;
import java.io.StringWriter;
import java.io.PrintWriter;
import javax.swing.JScrollPane;
import java.awt.Container;

public class ReadUserHomeApplet extends JApplet {
	JTextArea msgTextArea = null;

	@Override
	public void init() {
		String msg = "";
		try {
			String userHome = System.getProperty("user.home");
			msg = "User's Home Directory is '" + userHome + "'";
		} 
		catch (Throwable t) {
			msg = this.getStackTrace(t);
		}

		this.msgTextArea = new JTextArea(msg, 10, 40);
		Container contentPane = this.getContentPane();
		contentPane.add(new JScrollPane(msgTextArea));
	}

	public String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.close();
		return sw.toString();
	}
}
