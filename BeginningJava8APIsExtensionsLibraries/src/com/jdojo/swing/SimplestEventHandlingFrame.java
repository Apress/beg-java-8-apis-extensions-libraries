// SimplestEventHandlingFrame.java
package com.jdojo.swing;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;

public class SimplestEventHandlingFrame extends JFrame {
	JButton closeButton = new JButton("Close");

	public SimplestEventHandlingFrame() {
		super("Simplest Event Handling JFrame");
		this.initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set a FlowLayout for the content pane 
		this.setLayout(new FlowLayout());

		// Add the Close JButton to the content pane 
		this.getContentPane().add(closeButton);

		// Add an ActionListener to closeButton 
		closeButton.addActionListener(e -> System.exit(0));
	}

	public static void main(String[] args) {
		SimplestEventHandlingFrame frame = new SimplestEventHandlingFrame();
		frame.pack();
		frame.setVisible(true);
	}
}
