// DrawingCanvas.java
package com.jdojo.swing;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.JFrame;

public class DrawingCanvas extends JPanel {
	public DrawingCanvas() {
		this.setPreferredSize(new Dimension(600, 75));
	}

	@Override
	public void paintComponent(Graphics g) {
		// Paint its background 
		super.paintComponent(g);
	
		// Draw a line 
		g.drawLine(10, 10, 50, 50);
	
		// Draw a rectangle 
		g.drawRect(80, 10, 40, 20);

		// Draw an oval 
		g.drawOval(140, 10, 40, 20);

		// Fill an oval 
		g.fillOval(200, 10, 40, 20);

		// Draw a circle 
		g.drawOval(250, 10, 40, 40);

		// Draw an arc 
		g.drawArc(300, 10, 50, 50, 60, 120);

		// Draw a string 
		g.drawString("Hello Swing!", 350, 30);

		// Draw a thicker rectangle using Graphics2D 
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRect(450, 10, 50, 50);
	}

	public static void main(String[] args) {
		JFrame frame = 
			new JFrame("Sample Drawings Using a Graphics Object");
		frame.getContentPane().add(new DrawingCanvas());
		frame.pack();
		frame.setVisible(true);
	}
}
