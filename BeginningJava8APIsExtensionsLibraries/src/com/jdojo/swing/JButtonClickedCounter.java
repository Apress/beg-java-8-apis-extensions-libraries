// JButtonClickedCounter.java
package com.jdojo.swing;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class JButtonClickedCounter extends JFrame {
	int counter;
	JButton counterButton = new JButton("Clicked #0");
	JButton closeButton = new JButton("Close");

	public JButtonClickedCounter() {
		super("JButton Clicked Counter");
		this.initFrame();
	}
 
	private void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 
		// Set a FlowLayout for the content pane 
		this.setLayout(new FlowLayout());
 
		// Add two JButtons to the content pane 
		this.getContentPane().add(counterButton);
		this.getContentPane().add(closeButton);
 
		// Add an ActionListener to the counter button
		counterButton.addActionListener(e -> {			
				// Increment the counter and set the JButton text 
				counter++;
				counterButton.setText("Clicked #" + counter);
			});

		// Add an ActionListener to close button
		closeButton.addActionListener(e ->	System.exit(0));
	}

	public static void main(String[] args) {
		JButtonClickedCounter frame = new JButtonClickedCounter();
		frame.pack();
		frame.setVisible(true);
	}
}
