// JToolBarFrame.java
package com.jdojo.swing;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class JToolBarFrame extends JFrame {
	JToolBar toolBar = new JToolBar("My JToolBar");
	JTextArea msgText = new JTextArea(3, 45);

	public JToolBarFrame(String title) {
		super(title);
		initFrame();
	}

	// Initialize the JFrame and add components to it 
	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		prepareToolBar();

		// Add the toolbar in the north and a JTextArea in the center 
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(new JScrollPane(msgText), BorderLayout.CENTER);
		msgText.append("Move the toolbar around using its" +
		               " handle at the left end");
	}

	private void prepareToolBar() {
		Insets zeroInset = new Insets(0, 0, 0, 0);
		
		JButton newButton = new JButton("New");
		newButton.setMargin(zeroInset);
		newButton.setToolTipText("Add a new policy");

		JButton openButton = new JButton("Open");
		openButton.setMargin(zeroInset);
		openButton.setToolTipText("Open a policy");
		
		JButton exitButton = new JButton("Exit");
		exitButton.setMargin(zeroInset);
		exitButton.setToolTipText("Exit the application");
		
		// Add an action listener to the Exit toolbar button
		exitButton.addActionListener(e -> System.exit(0));
		
		toolBar.add(newButton);
		toolBar.add(openButton);
		toolBar.addSeparator();
		toolBar.add(exitButton);

		toolBar.setRollover(true);
	}

	// Display the frame 
	public static void main(String[] args) {
		JToolBarFrame frame = new JToolBarFrame("JToolBar Test");
		frame.pack();
		frame.setVisible(true);
	}
}
