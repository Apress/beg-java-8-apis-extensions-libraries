// NestedGroupLayout.java
package com.jdojo.swing;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.*;

public class NestedGroupLayout {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Nested Groups in GroupLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		// Set the content’s pane layout to GroupLayout 
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		contentPane.setLayout(groupLayout);

		// Add four JButtons to the content pane 
		JButton b1 = new JButton("Button 1");
		JButton b2 = new JButton("Little Bigger Button 2");
		JButton b3 = new JButton("3");
		JButton b4 = new JButton("Button 4");

		groupLayout.setHorizontalGroup(
			groupLayout.createSequentialGroup()
			.addGroup(groupLayout.createParallelGroup(LEADING)
				.addComponent(b1)
				.addComponent(b3))
			.addGroup(groupLayout.createParallelGroup(TRAILING)
				.addComponent(b2)
				.addComponent(b4))
		);

		groupLayout.setVerticalGroup(
			groupLayout.createSequentialGroup()
			.addGroup(groupLayout.createParallelGroup(BASELINE)
				.addComponent(b1)
				.addComponent(b2))
			.addGroup(groupLayout.createParallelGroup(BASELINE)
				.addComponent(b3)
				.addComponent(b4))
		);

		frame.pack();
		frame.setVisible(true);
	}
}
