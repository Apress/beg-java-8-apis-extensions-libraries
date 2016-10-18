// TranslucentJPanel.java
package com.jdojo.swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JPanel;

public class TranslucentJPanel extends JPanel {
	private int red = 240;
	private int green = 240;
	private int blue = 240;

	public TranslucentJPanel(Color bgColor) {
		this.red = bgColor.getRed();
		this.green = bgColor.getGreen();
		this.blue = bgColor.getBlue();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {		
			int width = this.getWidth();
			int height = this.getHeight();
			float startPointX = 0.0f;
			float startPointY = 0.0f;
			float endPointX = width;
			float endPointY = 0.0f;
			Color startColor = new Color(red, green, blue, 255);
			Color endColor = new Color(red, green, blue, 0);
			
			// Create a GradientPaint object 
			Paint paint = new GradientPaint(startPointX, startPointY, 
			                                startColor,
			                                endPointX, endPointY, 
			                                endColor); 

			Graphics2D g2D = (Graphics2D) g;
			g2D.setPaint(paint);
			g2D.fillRect(0, 0, width, height);
		}
	}
}
