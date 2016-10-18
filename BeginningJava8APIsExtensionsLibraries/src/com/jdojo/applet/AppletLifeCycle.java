// AppletLifeCycle.java
package com.jdojo.applet;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AppletLifeCycle extends JApplet {
	private int startCount = 0;
	private int stopCount = 0;
	
	@Override
	public void init() {
		this.getContentPane().add(new JLabel("Applet Life Cycle!!!"));
		JOptionPane.showMessageDialog(null, "init()");
	}

	@Override
	public void start() {
		startCount++;
		JOptionPane.showMessageDialog(null, "start(): " + startCount);
	}

	@Override
	public void stop() {
		stopCount++;
		JOptionPane.showMessageDialog(null, "stop(): " + stopCount);
	}

	@Override
	public void destroy() {
		JOptionPane.showMessageDialog(null, "destroy()");
	}
}
