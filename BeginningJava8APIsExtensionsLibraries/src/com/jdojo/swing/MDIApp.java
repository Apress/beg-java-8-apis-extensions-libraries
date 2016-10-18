// MDIApp.java
package com.jdojo.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MDIApp extends JFrame {
	private final JDesktopPane desktopPane = new JDesktopPane();

	public MDIApp(String title) {
		super(title);
		initFrame();
	}

	public void initFrame() {
		JInternalFrame frame1
			= new JInternalFrame("Frame 1", true, true, true, true);

		JInternalFrame frame2
			= new JInternalFrame("Frame 2", true, true, true, true);

		JLabel label1 = new JLabel("Frame 1 contents...");
		frame1.getContentPane().add(label1);
		frame1.pack();
		frame1.setVisible(true);

		JLabel label2 = new JLabel("Frame 2 contents...");
		frame2.getContentPane().add(label2);
		frame2.pack();
		frame2.setVisible(true);

		// Default location is (0,0) for a JInternalFrame. 
		// Set the location of frame2, so that both frames are visible 
		int x2 = frame1.getX() + frame1.getWidth() + 10;
		int y2 = frame1.getY();
		frame2.setLocation(x2, y2);

		// Add both internal frames to the desktop pane 
		desktopPane.add(frame1);
		desktopPane.add(frame2);

		// Finally add the desktop pane to the JFrame 
		this.add(desktopPane, BorderLayout.CENTER);

		// Need to set minimum size for the JFrame 
		this.setMinimumSize(new Dimension(300, 300));
	}

	public static void main(String[] args) {
		try {
			// Set the system look and feel
			UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			MDIApp frame = new MDIApp("MDI Frame");
			frame.pack();
			frame.setVisible(true);
			frame.setExtendedState(frame.MAXIMIZED_BOTH);
		});
	}
}
