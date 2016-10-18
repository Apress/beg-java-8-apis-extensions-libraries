// MirroredTextField.java
package com.jdojo.swing;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Container;
import javax.swing.text.Document;

public class MirroredTextField extends JFrame {
	JLabel nameLabel = new JLabel("Name:") ;
	JLabel mirroredNameLabel = new JLabel("Mirrored Name:") ;
	JTextField name = new JTextField(20);
	JTextField mirroredName = new JTextField(20);

	public MirroredTextField() {
		super("Mirrored JTextField");
		this.initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2, 0));

		Container contentPane = this.getContentPane();
		contentPane.add(nameLabel);
		contentPane.add(name);
		contentPane.add(mirroredNameLabel);
		contentPane.add(mirroredName);
		
		// Set the model for mirroredName to be the same 
		// as name's model, so they share their content's storage.
		Document nameModel = name.getDocument();
		mirroredName.setDocument(nameModel);
	}

	public static void main(String[] args) {
		MirroredTextField frame = new MirroredTextField();
		frame.pack();
		frame.setVisible(true);
	}
}
