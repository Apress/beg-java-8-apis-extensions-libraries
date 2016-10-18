// ActionJButtonTest.java
package com.jdojo.swing;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Container;

public class ActionJButtonTest extends JFrame {
	// Inner Class starts here
	public class CloseAction extends AbstractAction {
		public CloseAction() {
			super("Close");
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	} // Inner Class ends here

	JButton closeButton1;
	JButton closeButton2;
	Action closeAction = new CloseAction(); // See inner class above 

	public ActionJButtonTest() {
		super("Using Action object with JButton");
		this.initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		Container contentPane = this.getContentPane();
	
		// Use the same closeAction object to create both Close buttons 
		closeButton1 = new JButton(closeAction);
		closeButton2 = new JButton(closeAction);

		contentPane.add(closeButton1);
		contentPane.add(closeButton2);
	}

	public static void main(String[] args) {
		ActionJButtonTest frame = new ActionJButtonTest();
		frame.pack();
		frame.setVisible(true);
	}
}
