// NiceSpringLayout.java
package com.jdojo.swing;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class NiceSpringLayout {
	public static void main(String[] args) {
		JFrame frame = new JFrame("SpringLayout2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		// Set the content pane's layout to a SpringLayout 
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);

		// Create two JButtons 
		JButton b1 = new JButton("Button 1");
		JButton b2 = new JButton("Little Bigger Button 2");

		// Add two JButtons without using any constraints 
		contentPane.add(b1);
		contentPane.add(b2);

		// Now add constraints to both JButtons 
		// Set x for b1 as 10 
		springLayout.putConstraint(SpringLayout.WEST, b1, 10,
		                          SpringLayout.WEST, contentPane);
		// Set y for b1 as 20 
		springLayout.putConstraint(SpringLayout.NORTH, b1, 20,
		                           SpringLayout.NORTH, contentPane);

		// Set x for b2 as 10 from the right edge of b1 
		springLayout.putConstraint(SpringLayout.WEST, b2, 10,
		                           SpringLayout.EAST, b1);
		// Set y for b1 as 20 
		springLayout.putConstraint(SpringLayout.NORTH, b2, 20,
		                           SpringLayout.NORTH, contentPane);

		/* Now set height and width for the content pane as the bottom 
		   edge of b1 + 10 and right edge of b2 + 10. Note that source 
		   is b1 for content pane's height and b2 for its width */

		// Set the bottom edge of the content pane 
		springLayout.putConstraint(SpringLayout.SOUTH, contentPane, 10,
		                           SpringLayout.SOUTH, b1);

		// Set the right edge of the content pane 
		springLayout.putConstraint(SpringLayout.EAST, contentPane, 10,
		                           SpringLayout.EAST, b2);

		frame.pack();
		frame.setVisible(true);
	}
}
