// CustomFrame.java
package com.jdojo.swing;

import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Container;
import javax.swing.GroupLayout;

public class CustomFrame extends JFrame {
	// Declare all components as instance variables 
	JButton b1 = new JButton("Button 1");
	JButton b2 = new JButton("Little Bigger Button 2");

	public CustomFrame(String title) {
		super(title);
		initFrame();
	}

	// Initialize the frame and add components to it. 
	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		GroupLayout groupLayout = new GroupLayout(contentPane);
		contentPane.setLayout(groupLayout);

		groupLayout.setHorizontalGroup(
			groupLayout.createSequentialGroup()
				.addComponent(b1)
				.addComponent(b2)
		);

		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.BASELINE)
			     .addComponent(b1)
				.addComponent(b2)
		);
	}

	// Display the CustomFrame 
	public static void main(String[] args) {
		CustomFrame frame = new CustomFrame("Custom Frame");
		frame.pack();
		frame.setVisible(true);
	}
}
