// DragAndDropApp.java
package com.jdojo.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class DragAndDropApp extends JFrame {
	private JLabel newLabel = new JLabel("New:");
	private JTextField newTextField = new JTextField(10);
	private JLabel sourceLabel = new JLabel("Source");
	private JLabel destLabel = new JLabel("Destination");
	private JList<String> sourceList = new JList<>(new DefaultListModel<>());
	private JList<String> destList = new JList<>(new DefaultListModel<>());

	public DragAndDropApp(String title) {
		super(title);
		populateList();
		initFrame();
	}

	private void initFrame() {
		Container contentPane = this.getContentPane();

		Box nameBox = Box.createHorizontalBox();
		nameBox.add(newLabel);
		nameBox.add(newTextField);

		Box sourceBox = Box.createVerticalBox();
		sourceBox.add(sourceLabel);
		sourceBox.add(new JScrollPane(sourceList));

		Box destBox = Box.createVerticalBox();
		destBox.add(destLabel);
		destBox.add(new JScrollPane(destList));

		Box listBox = Box.createHorizontalBox();
		listBox.add(sourceBox);
		listBox.add(destBox);

		Box allBox = Box.createVerticalBox();
		allBox.add(nameBox);
		allBox.add(listBox);

		contentPane.add(allBox, BorderLayout.CENTER);

		// Our lists support only single selection 
		sourceList.setSelectionMode(
			ListSelectionModel.SINGLE_SELECTION);
		destList.setSelectionMode(
			ListSelectionModel.SINGLE_SELECTION);

		// Enable Drag and Drop for components 
		newTextField.setDragEnabled(true);
		sourceList.setDragEnabled(true);
		destList.setDragEnabled(true);

		// Set the drop mode to Insert 
		sourceList.setDropMode(DropMode.INSERT);
		destList.setDropMode(DropMode.INSERT);

		// Set the transfer handler 
		sourceList.setTransferHandler(new ListTransferHandler());
		destList.setTransferHandler(new ListTransferHandler());
	}

	public void populateList() {
		DefaultListModel<String> sourceModel
			= (DefaultListModel<String>) sourceList.getModel();

		DefaultListModel<String> destModel
			= (DefaultListModel<String>) destList.getModel();
		for (int i = 0; i < 5; i++) {
			sourceModel.add(i, "Source Item " + i);
			destModel.add(i, "Destination Item " + i);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			DragAndDropApp frame = new DragAndDropApp("Drag and Drop Frame");
			frame.pack();
			frame.setVisible(true);
		});
	}
}
