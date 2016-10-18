// SimplestGroupLayout.java
package com.jdojo.swing;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;

public class SimplestGroupLayout {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Simplest GroupLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		// Create an object of the GroupLayout class for contentPane 
		GroupLayout groupLayout = new GroupLayout(contentPane);

		// Set the content pane's layout to a GroupLayout 
		contentPane.setLayout(groupLayout);

		// Add two JButtons to the content pane 
		JButton b1 = new JButton("Button 1");
		JButton b2 = new JButton("Little Bigger Button 2");

		groupLayout.setHorizontalGroup(
			groupLayout.createSequentialGroup()
			.addComponent(b1)
			.addComponent(b2)
		);

		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup( 
					             GroupLayout.Alignment.BASELINE)
				           .addComponent(b1)
				           .addComponent(b2)
				);

		frame.pack();
		frame.setVisible(true);
	}
}
