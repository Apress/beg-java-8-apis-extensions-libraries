// FlowLayoutNesting.java
package com.jdojo.swing;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlowLayoutNesting {
	public static void main(String[] args) {
		JFrame frame = new JFrame("FlowLayout Nesting");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the content pane's layout to FlowLayout 
		frame.getContentPane().setLayout(new FlowLayout());

		// JPanel is an empty container with a FlowLayout manager 
		JPanel panel = new JPanel();

		// Add thirty JButtons to the JPanel 
		for(int i = 1; i <= 30; i++) {
			panel.add(new JButton("Button " + i));
		}

		// Add JPanel to the content pane 
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
	}
}
