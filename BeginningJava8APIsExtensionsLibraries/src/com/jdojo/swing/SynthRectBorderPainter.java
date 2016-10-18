// SynthRectBorderPainter.java
package com.jdojo.swing;

import javax.swing.plaf.synth.SynthPainter;
import javax.swing.plaf.synth.SynthContext;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class SynthRectBorderPainter extends SynthPainter {
	@Override
	public void paintTextFieldBorder(SynthContext context, Graphics g, 
	 int x, int y, int w, int h) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLUE);
		g2.drawRect(x, y, w, h);
	}

	@Override
	public void paintButtonBorder(SynthContext context, Graphics g, 
	 int x, int y, int w, int h) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(4));
		g2.setColor(Color.RED);
		g2.drawRect(x, y, w, h);
	}
}
