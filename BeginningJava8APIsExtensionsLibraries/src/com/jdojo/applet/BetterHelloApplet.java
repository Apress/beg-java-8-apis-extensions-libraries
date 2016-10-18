// BetterHelloApplet.java
package com.jdojo.applet;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class BetterHelloApplet extends JApplet {
	@Override
	public void init() {
		try {
			// Build the GUI on thw event-dispatching thread
			SwingUtilities.invokeAndWait(() -> initApplet());
		} 
		catch (InterruptedException | InvocationTargetException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
				"Error", ERROR_MESSAGE);			
		}
	}

	private void initApplet() {
		// This method is supposed to be executed on the 
		// event-dispatching thread 

		// Create Swing components
		JLabel nameLabel = new JLabel("Your Name:");
		JTextField nameFld = new JTextField(15);
		JButton sayHelloBtn = new JButton("Say Hello");

		// Add an action litener to the button to show the hello message
		sayHelloBtn.addActionListener(e -> sayHello(nameFld.getText()));

		// Add Swing components to the content pane of the applet
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(nameLabel);
		contentPane.add(nameFld);
		contentPane.add(sayHelloBtn);		
	}

	private void sayHello(String name) {
		String msg = "Hello there";
		if (name.length() > 0) {
			msg = "Hello " + name;
		}

		// Display the hello message
		JOptionPane.showMessageDialog(null, msg, "Hello", INFORMATION_MESSAGE);
	}
}
