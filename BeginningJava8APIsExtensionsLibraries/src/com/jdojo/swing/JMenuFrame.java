// JMenuFrame.java
package com.jdojo.swing;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;

public class JMenuFrame extends JFrame {
	JLabel msgLabel = new JLabel("Right click to see popup menu");
	JTextArea msgText = new JTextArea(10, 60);
	JPopupMenu popupMenu = new JPopupMenu();

	public JMenuFrame(String title) {
		super(title);
		initFrame();
	}

	// Initialize the JFrame and add components to it 
	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();

		// Add the message label and text area 
		contentPane.add(new JScrollPane(msgText), BorderLayout.CENTER);
		contentPane.add(msgLabel, BorderLayout.SOUTH);

		// Set the menu bar for the frame 
		JMenuBar menuBar = getCustomMenuBar();
		this.setJMenuBar(menuBar);

		// Create a popup menu and add a mouse listener to show it 
		createPopupMenu();
	}

	private JMenuBar getCustomMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// Get the File and Help menus 
		JMenu fileMenu = getFileMenu();
		JMenu helpMenu = getHelpMenu();

		// Add the File and Help menus to the menu bar 
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		return menuBar;
	}

	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("File");

		// Set Alt-F as mnemonic for the File menu 
		fileMenu.setMnemonic(KeyEvent.VK_F);
	
		// Prepare a New Menu item. It will have sub menus 
		JMenu newMenu = getNewMenu();
		fileMenu.add(newMenu);

		JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);

		fileMenu.add(openMenuItem);

		// You can add a JSeparator or just call the convenience method 
		// addSeparator() on fileMenu. You can replace the following statement 
		// with fileMenu.add(new JSeparator()); 
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		// Add an ActionListener to the Exit menu item 
		exitMenuItem.addActionListener(e -> System.exit(0));

		return fileMenu;	
	}

	private JMenu getNewMenu() {
		// New menu will have two sub menus - Policy and Claim 
		JMenu newMenu = new JMenu("New");

		// Add submenus to New menu 
		JMenuItem policyMenuItem = new JMenuItem("Policy", KeyEvent.VK_P);
		JMenuItem claimMenuItem = new JMenuItem("Claim", KeyEvent.VK_C);
		newMenu.add(policyMenuItem);
		newMenu.add(claimMenuItem);

		return newMenu;
	}

	private JMenu getHelpMenu() { 
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem indexMenuItem = new JMenuItem("Index", KeyEvent.VK_I);
		JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);

		// Set F1 as the accelerator key for the Index menu item 
		KeyStroke f1Key = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
		indexMenuItem.setAccelerator(f1Key);
		
		helpMenu.add(indexMenuItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutMenuItem);
		
		// Add an action listener to the index menu item 
		indexMenuItem.addActionListener(e -> 
			msgText.append("You have selected Help >> Index menu item.\n"));
		
		return helpMenu;
	}

	private void createPopupMenu() {
		// Create a popup menu and add a mouse listener to the frame, 
		// so a popup menu is displayed when the user clicks a right mouse button 
		JMenuItem popup1 = new JMenuItem("Popup1");
		JMenuItem popup2 = new JMenuItem("Popup2");
		JMenuItem popup3 = new JMenuItem("Popup3");

		// Create an action listener
		ActionListener al =  e -> {
			JMenuItem menuItem = (JMenuItem)e.getSource();
			String menuText = menuItem.getText();
			String msg = "You clicked " + menuText + " menu item.\n";
			msgText.append(msg);
		};
		

		// Add the same action listener to all popup menu items 
		popup1.addActionListener(al);
		popup2.addActionListener(al);
		popup3.addActionListener(al);

		// Add menu items to popup menu 
		popupMenu.add(popup1);
		popupMenu.add(popup2);
		popupMenu.add(popup3);
		
		// Create a mouse listener to show a popup menu 
		MouseListener ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				displayPopupMenu(e);
			}
 
			@Override
			public void mouseReleased(MouseEvent e) {
				displayPopupMenu(e);
			}
		};
		
		// Add a mouse listener to the msg text and label 
		msgText.addMouseListener(ml);
		msgLabel.addMouseListener(ml);		
	}

	private void displayPopupMenu(MouseEvent e) {
		// Make sure this mouse event is supposed to show the popup menu. 
		// Different platforms show the popup menu in different mouse events
		if (e.isPopupTrigger()) {
			this.popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	// Display the CustomFrame 
	public static void main(String[] args) {
		JMenuFrame frame = new JMenuFrame("JMenu and JPopupMenu Test");
		frame.pack();
		frame.setVisible(true);
	}
}
