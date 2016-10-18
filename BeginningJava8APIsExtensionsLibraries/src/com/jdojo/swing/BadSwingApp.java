// BadSwingApp.java
package com.jdojo.swing;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JComboBox;

public class BadSwingApp extends JFrame {
	JComboBox<String> combo = new JComboBox<>();
	
	public BadSwingApp(String title) {
		super(title);
		initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();		
		contentPane.add(combo, BorderLayout.NORTH);

		// Add an ItemEvent listener to the combobox
		combo.addItemListener(e -> 
			System.out.println("isEventDispatchThread(): " +
				SwingUtilities.isEventDispatchThread()));

		combo.addItem("First");
		combo.addItem("Second");
		combo.addItem("Third");
	}

	public static void main(String[] args) {
		BadSwingApp badSwingApp = new BadSwingApp("Abd Swing App");
		badSwingApp.pack();
		badSwingApp.setVisible(true);
	}
}
