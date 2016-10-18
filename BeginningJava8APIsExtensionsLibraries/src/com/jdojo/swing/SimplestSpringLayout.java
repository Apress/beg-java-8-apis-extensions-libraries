// SimplestSpringLayout.java
package com.jdojo.swing;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class SimplestSpringLayout {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Simplest SpringLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		// Set the content pane's layout as SpringLayout 
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);

		// Add two JButtons to the content pane 
		JButton b1 = new JButton("Button 1");
		JButton b2 = new JButton("Little Bigger Button 2");
		contentPane.add(b1);
		contentPane.add(b2);

		frame.pack();
		frame.setVisible(true);
	}
}
