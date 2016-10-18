// SwingWorkerFrame.java
package com.jdojo.swing;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingUtilities;

public class SwingWorkerFrame extends JFrame {
	String startMessage = "Please click the start button...";
	JLabel statusLabel = new JLabel(startMessage);
	JButton startButton = new JButton("Start");
	JButton cancelButton = new JButton("Cancel");
	SwingWorkerProcessor processor;

	public SwingWorkerFrame(String title) {
		super(title);
		initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		cancelButton.setEnabled(false);

		contentPane.add(statusLabel, BorderLayout.NORTH);
		contentPane.add(startButton, BorderLayout.WEST);
		contentPane.add(cancelButton, BorderLayout.EAST);

		startButton.addActionListener(e -> startProcessing());
		cancelButton.addActionListener(e -> cancelProcessing());
	}

	public void setButtonStatus(boolean canStart) {
		if (canStart) {
			startButton.setEnabled(true);
			cancelButton.setEnabled(false);
		} else {
			startButton.setEnabled(false);
			cancelButton.setEnabled(true);
		}
	}

	public void startProcessing() {
		setButtonStatus(false);
		processor = new SwingWorkerProcessor(this, 10, 1000);
		processor.execute();
	}

	public void cancelProcessing() {
		// Cancel the processing 
		processor.cancel(true);
		setButtonStatus(true);
	}

	public void updateStatus(int counter, int total) {
		String msg = "Processing " + counter + " of " + total;
		statusLabel.setText(msg);
	}

	public void doneProcessing() {
		if (processor.isCancelled()) {
			statusLabel.setText("Process cancelled ...");
		} 
		else {
			try {
				// Get the result of processing 
				int sum = processor.get();
				statusLabel.setText("Process completed. Sum is " + sum);
			} 
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		setButtonStatus(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SwingWorkerFrame frame
				= new SwingWorkerFrame("SwingWorker Frame");
			frame.pack();
			frame.setVisible(true);
		});
	}
}
