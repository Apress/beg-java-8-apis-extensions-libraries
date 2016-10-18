// ButtonGroupFrame.java
package com.jdojo.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class ButtonGroupFrame extends JFrame {
	ButtonGroup genderGroup = new ButtonGroup();
	JRadioButton genderMale = new JRadioButton("Male");
	JRadioButton genderFemale = new JRadioButton("Female");
	JRadioButton genderUnknown = new JRadioButton("Unknown");

	public ButtonGroupFrame() {
		this.initFrame();
	}

	private void initFrame() {
		this.setTitle("Mutually Exclusive JRadioButtons Group");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Add three gender JRadioButtons to a ButtonGroup, 
		// so they become mutually exclusive choices 
		genderGroup.add(genderMale);
		genderGroup.add(genderFemale);
		genderGroup.add(genderUnknown);

		// Add gender radio button to a vertical Box 
		Box b1 = Box.createVerticalBox();
		b1.add(genderMale);
		b1.add(genderFemale);
		b1.add(genderUnknown);

		// Add the vertical box to the center of the frame 
		Container contentPane = this.getContentPane();
		contentPane.add(b1, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		ButtonGroupFrame bf = new ButtonGroupFrame();
		bf.pack();
		bf.setVisible(true);
	}
}
