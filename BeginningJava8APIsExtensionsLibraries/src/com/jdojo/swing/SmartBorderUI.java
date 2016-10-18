// SmartBorderUI.java
package com.jdojo.swing;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JTextField;
import javax.swing.plaf.LayerUI;

public class SmartBorderUI extends LayerUI<JTextField> {
	@Override
	public void paint(Graphics g, JComponent layer) {				
		// Let the superclass paint the component first 
		super.paint(g, layer);
		
		Graphics gTemp = (Graphics2D) g.create();					
		int width = layer.getWidth();
		int height = layer.getHeight();		
		
		// Suppress the unchecked warning
		@SuppressWarnings("unchecked")
		JLayer<JTextField> myLayer = (JLayer<JTextField>)layer;
		
		JTextField field = (JTextField)myLayer.getView();
				
		// When in focus, draw a red rectangle. Otherwise, draw a blue rectangle 
		Color bColor; 
		if (field.hasFocus()) {
			bColor = Color.RED;
		}
		else {
			bColor = Color.BLUE;
		}
		
		gTemp.setColor(bColor);		
		gTemp.drawRect(0, 0, width, height);		
		gTemp.dispose();
	}
	
	@Override
	public void installUI(JComponent c) {
		// Let the superclass do its job 
		super.installUI(c);		
		
		// Set the event mask for the layer stating that it is interested 
		// in listening to the focus event for its target 
		JLayer layer = (JLayer)c;
		layer.setLayerEventMask(AWTEvent.FOCUS_EVENT_MASK);
	}
	
	@Override
	public void uninstallUI(JComponent c) {
		// Let the superclass do its job 
		super.uninstallUI(c);
	
		JLayer layer = (JLayer) c;
	
		// Set the event mask back to zero 
		layer.setLayerEventMask(0);
	}
	
	@Override
	protected void processFocusEvent(FocusEvent e, JLayer layer) {
		layer.repaint();
	}
}
