// ImageApplet.java
package com.jdojo.applet;

import java.awt.Container;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;

public class ImageApplet extends JApplet {
	JLabel imageLabel;

	@Override
	public void init() {
		Container contentPane = this.getContentPane();
		Image img = this.getWelcomeImage();
		if (img == null) {
			imageLabel = new JLabel("Image parameter not set...");
		} 
		else {
			imageLabel = new JLabel(new ImageIcon(img));
		}
		contentPane.add(imageLabel);
	}

	private Image getWelcomeImage() {
		Image img = null;
		String imageURL = this.getParameter("welcomeImageURL");
		if (imageURL != null) {
			img = this.getImage(this.getDocumentBase(), imageURL);
		}
		return img;
	}
}
