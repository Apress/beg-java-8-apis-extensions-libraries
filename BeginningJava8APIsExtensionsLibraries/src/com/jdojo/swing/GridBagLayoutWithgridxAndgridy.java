// GridBagLayoutWithgridxAndgridy.java
package com.jdojo.swing;

import java.awt.GridBagLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class GridBagLayoutWithgridxAndgridy {
	public static void main(String[] args) {
		String title = "GridBagLayout with gridx and gridy";
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridBagLayout());

		// Create an object for GridBagConstraints to set 
		// the constraints for each JButton 
		GridBagConstraints gbc = new GridBagConstraints();
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				gbc.gridx = x;
				gbc.gridy = y;
				String text = "Button (" + x + ", " + y + ")";
				contentPane.add(new JButton(text), gbc);
			}
		}
		
		frame.pack();
		frame.setVisible(true);
	}
}
