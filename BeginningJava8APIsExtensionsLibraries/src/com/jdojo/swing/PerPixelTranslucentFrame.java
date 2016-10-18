// PerPixelTranslucentFrame.java
package com.jdojo.swing;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PerPixelTranslucentFrame extends JFrame {
	private JButton closeButton = new JButton("Close");

	public PerPixelTranslucentFrame(String title) {
		super(title);
		initFrame();
	}

	public void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Make sure the frame is undecorated 
		this.setUndecorated(true);

		// Set the background color with all components as zero, 
		// so per-pixel translucency is used
		this.setBackground(new Color(0, 0, 0, 0));

		// Set its size 
		this.setSize(200, 200);

		// Center it on the screen 
		this.setLocationRelativeTo(null);

		this.getContentPane().setLayout(new GridLayout(0, 1));

		// Create and add three JPanel with different color gradients 
		this.add(new TranslucentJPanel(Color.RED));
		this.add(new TranslucentJPanel(Color.GREEN));
		this.add(new TranslucentJPanel(Color.BLUE));

		// Add a button to close the window 
		this.add(closeButton);
		closeButton.addActionListener(e -> System.exit(0));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			PerPixelTranslucentFrame frame
				= new PerPixelTranslucentFrame("Per-Pixel Translucent Frame");
			frame.setVisible(true);
		});
	}
}
