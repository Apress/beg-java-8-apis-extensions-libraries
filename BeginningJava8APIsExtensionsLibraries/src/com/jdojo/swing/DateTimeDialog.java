// DateTimeDialog.java
package com.jdojo.swing;

import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DateTimeDialog extends JDialog {
	JLabel dateTimeLabel = new JLabel("Datetime placeholder");
	JButton okButton = new JButton("OK");

	public DateTimeDialog() {
		initFrame();
	}

	private void initFrame() {
		// Release all resources when JDialog is closed 
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setTitle("Current Date and Time");
		this.setModal(true);

		String currentDateTimeString = getCurrentDateTimeString();
		dateTimeLabel.setText(currentDateTimeString);

		// There is no need to add components to the content pane. 
		// You can directly add them to the JDialog.
		this.add(dateTimeLabel, BorderLayout.NORTH);
		this.add(okButton, BorderLayout.SOUTH);

		// Add an action listeenr to the OK button
		okButton.addActionListener(e -> DateTimeDialog.this.dispose());
	}

	private String getCurrentDateTimeString() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy hh:mm:ss a");
		String dateString = ldt.format(formatter);
		return dateString;
	}
}
