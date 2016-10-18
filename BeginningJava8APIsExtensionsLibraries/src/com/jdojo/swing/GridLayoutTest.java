// GridLayoutTest.java
package com.jdojo.swing;

import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JButton;

public class GridLayoutTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame("GridLayout Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 0));

		for (int i = 1; i <= 9; i++) {
			buttonPanel.add(new JButton("Button " + i));
		}

		contentPane.add(buttonPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
