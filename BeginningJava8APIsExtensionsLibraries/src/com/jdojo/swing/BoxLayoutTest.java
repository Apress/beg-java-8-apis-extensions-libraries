// BoxLayoutTest.java
package com.jdojo.swing;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class BoxLayoutTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame("BoxLayout Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		
		JPanel hPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(hPanel, BoxLayout.X_AXIS);
		hPanel.setLayout(boxLayout);		

		for(int i = 1; i <= 3; i++) {
			hPanel.add(new JButton("Button " + i));
		}

		contentPane.add(hPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
}
