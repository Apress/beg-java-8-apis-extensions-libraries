// DrawingHelloApplet.java
package com.jdojo.applet;

import javax.swing.JApplet;
import java.awt.Graphics;

public class DrawingHelloApplet extends JApplet {
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Hello Applet!", 10, 20 );
	}
}
