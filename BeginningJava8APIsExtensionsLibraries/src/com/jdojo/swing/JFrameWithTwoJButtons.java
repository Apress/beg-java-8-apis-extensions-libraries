// JFrameWithTwoJButtons.java
package com.jdojo.swing;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JButton;

public class JFrameWithTwoJButtons {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Adding Component to JFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add two buttons - Close and Help 
		JButton closeButton = new JButton("Close");
		JButton helpButton = new JButton("Help");
		Container contentPane = frame.getContentPane();
		contentPane.add(closeButton);
		contentPane.add(helpButton);
		frame.pack();
		frame.setVisible(true);
	}
}
