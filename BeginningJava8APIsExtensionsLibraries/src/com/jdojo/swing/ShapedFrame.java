// ShapedFrame.java
package com.jdojo.swing;

import java.awt.BorderLayout;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ShapedFrame extends JFrame {
	private JButton closeButton = new JButton("Close");

	public ShapedFrame() {
		initFrame();
	}

	public void initFrame() {
		// Make sure the frame is undecorated 
		this.setUndecorated(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(200, 200);

		// Create a shape with an ellipse placed over a rectangle 
		Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, 200, 100);
		Rectangle2D.Double rect = new Rectangle2D.Double(0, 100, 200, 200);

		// Combine the ellipse and rectangle into a Path2D object and 
		// set it as the shape for the JFrame 
		Path2D path = new Path2D.Double();
		path.append(rect, true);
		path.append(ellipse, true);
		this.setShape(path);

		// Add a Close button to close the frame		 
		this.add(closeButton, BorderLayout.SOUTH);
		closeButton.addActionListener(e -> System.exit(0));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Display the custom shaped frame 
			ShapedFrame frame = new ShapedFrame();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
