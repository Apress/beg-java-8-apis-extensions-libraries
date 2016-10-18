// UniformTranslucentFrame.java
package com.jdojo.swing;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class UniformTranslucentFrame extends JFrame {
	private JButton closeButton = new JButton("Close");

	public UniformTranslucentFrame(String title) {
		super(title);
		initFrame();
	}

	public void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Make sure the frame is undecorated 
		this.setUndecorated(true);

		// Set 40% opacity. That is, 60% translucency. 
		this.setOpacity(0.40f);

		// Set its size 
		this.setSize(200, 200);

		// Center it on the screen 
		this.setLocationRelativeTo(null);

		// Add a button to close the window 
		this.add(closeButton, BorderLayout.SOUTH);

		// Exit the aplication when the close button is clicked
		closeButton.addActionListener(e -> 	System.exit(0));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			UniformTranslucentFrame frame
				= new UniformTranslucentFrame("Translucent Frame");
			frame.setVisible(true);
		});
	}
}
