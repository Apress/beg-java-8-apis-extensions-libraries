// AppletParameters.java
package com.jdojo.applet;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AppletParameters extends JApplet {
	private JTextArea welcomeTextArea = new JTextArea(2, 20);
	private JButton helloButton = new JButton();

	@Override
	public void init() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(new JScrollPane(welcomeTextArea));
		contentPane.add(helloButton);

		// Show parameters when the button is clicked
		helloButton.addActionListener(e -> showParameters());

		// Make the welcome JTextArea non-editable
		welcomeTextArea.setEditable(false);

		// Display the welcome message
		String welcomeMsg = this.getParameter("welcomeText");
		if (welcomeMsg == null || welcomeMsg.equals("")) {
			welcomeMsg = "Welcome!";
		}
		welcomeTextArea.setText(welcomeMsg);

		// Set the hello button text
		String helloButtonText = this.getParameter("helloButtonText");
		if (helloButtonText == null || helloButtonText.equals("")) {
			helloButtonText = "Hello";
		}

		helloButton.setText(helloButtonText);
	}

	private void showParameters() {
		String welcomeText = this.getParameter("welcomeText");
		String helloButtonText = this.getParameter("helloButtonText");

		String msg = "Parameters passed from HTML are\nwelcomeText="
			+ welcomeText + "\nhelloButtonText=" + helloButtonText;
		JOptionPane.showMessageDialog(null, msg);
	}
}
