// SpringLayout2.java
package com.jdojo.swing;

import javax.swing.SpringLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Spring;

public class SpringLayout2 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("SpringLayout2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		// Set the content pane's layout to a SpringLayout 
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);

		// Add two JButtons to the content pane 
		JButton b1 = new JButton("Button 1");
		JButton b2 = new JButton("Little Bigger Button 2");
		
		// Create Constraints objects for b1 and b2 
		SpringLayout.Constraints b1c = new SpringLayout.Constraints();
		SpringLayout.Constraints b2c = new SpringLayout.Constraints();

		// Create a Spring object for y value for b1 and b2 
		Spring yPadding = Spring.constant(20);
		
		// Set (10, 20) for (x, y) for b1 
		b1c.setX(Spring.constant(10));
		b1c.setY(yPadding);

		// Set (150, 20) for (x, y) for b2 
		b2c.setX(Spring.constant(150));
		b2c.setY(yPadding);
		
		// Use the Constraints object while adding b1 and b2 
		contentPane.add(b1, b1c);
		contentPane.add(b2, b2c);

		frame.pack();
		frame.setVisible(true);
	}
}
