// BlueBorderUI.java
package com.jdojo.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.plaf.LayerUI;

public class BlueBorderUI extends LayerUI<JTextField> {
	@Override
	public void paint(Graphics g, JComponent layer) {		
		// Let the superclass paint the component first 
		super.paint(g, layer);
		
		// Create a copy of the Graphics object 
		Graphics gTemp = (Graphics2D) g.create();	
		
		// Get the dimension of the layer 
		int width = layer.getWidth();
		int height = layer.getHeight();		
		
		// Draw a blue rectangle that is custom your border 
		gTemp.setColor(Color.BLUE);		
		gTemp.drawRect(0, 0, width, height);	
	
		// Destroy the copy of the Graphics object	 
		gTemp.dispose();
	}
}
