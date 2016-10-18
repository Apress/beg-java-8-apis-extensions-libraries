// SynthLookAndFeelFrame.java
package com.jdojo.swing;

import java.io.InputStream;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class SynthLookAndFeelFrame extends JFrame {
	JLabel nameLabel = new JLabel("Name:");
	JTextField nameTextField = new JTextField(20);
	JButton closeButton = new JButton("Close");

	public SynthLookAndFeelFrame(String title) {
		super(title);
		initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(nameLabel);
		contentPane.add(nameTextField);
		contentPane.add(closeButton);
	}

	public static void main(String[] args) {
		try {
			SynthLookAndFeel laf = new SynthLookAndFeel();
			Class c = SynthLookAndFeelFrame.class;
			InputStream ins = c.getResourceAsStream("/synthlaf.xml");
			laf.load(ins, c);
			UIManager.setLookAndFeel(laf);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		SynthLookAndFeelFrame frame = 
			new SynthLookAndFeelFrame("Synth Look-and-Feel Frame");
		frame.pack();
		frame.setVisible(true);
	}
}
