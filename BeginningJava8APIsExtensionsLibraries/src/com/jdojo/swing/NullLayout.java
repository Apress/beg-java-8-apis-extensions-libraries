// NullLayout.java
package com.jdojo.swing;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JButton;

public class NullLayout  {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Null Layout Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(null);
		
		JButton b1 = new JButton("Small Button 1");
		JButton b2 = new JButton("Big Big Big Button 2...");
		contentPane.add(b1);
		contentPane.add(b2);

		// Must set (x, y) and (width, height) of components 
		b1.setBounds(10, 10, 100, 20);
		b2.setBounds(120, 10, 150, 20);
	
		// Must set the size of JFrame, because it uses a null layout.
		// Now, you cannot use the pack() method to compute its size.
		frame.setBounds(0, 0, 350, 100);
		frame.setVisible(true);
	}
}
