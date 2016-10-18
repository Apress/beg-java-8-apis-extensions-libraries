// ListTransferHandler.java
package com.jdojo.swing;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

public class ListTransferHandler extends TransferHandler {
	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY_OR_MOVE;
	}

	@Override
	protected Transferable createTransferable(JComponent source) {
		// Suppress the unchecked cast warning
		@SuppressWarnings("unchecked")
		JList<String> sourceList = (JList<String>)source;
		
		String data = sourceList.getSelectedValue();

		// Uses only the first selected item in the list 
		Transferable t = new StringSelection(data);
		return t;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		// Suppress the unchecked cast warning
		@SuppressWarnings("unchecked")
		JList<String> sourceList = (JList<String>)source;
		
		String movedItem = sourceList.getSelectedValue();

		if (action == TransferHandler.MOVE) {
			// Remove the moved item 
			DefaultListModel<String> listModel
				= (DefaultListModel<String>) sourceList.getModel();
			listModel.removeElement(movedItem);
		}
	}

	@Override
	public boolean canImport(TransferHandler.TransferSupport support) {
		// We only support drop, not copy-paste 
		if (!support.isDrop()) {
			return false;
		}

		return support.isDataFlavorSupported(DataFlavor.stringFlavor);
	}

	@Override
	public boolean importData(TransferHandler.TransferSupport support) {
		// This is necessary to handle paste 
		if (!this.canImport(support)) {
			return false;
		}

		// Get the data 
		Transferable t = support.getTransferable();
		String data = null;
		try {
			data = (String) t.getTransferData(DataFlavor.stringFlavor);
			if (data == null) {
				return false;
			}
		} 
		catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
			return false;
		}

		// Get the drop location for the JList 
		JList.DropLocation dropLocation
			= (JList.DropLocation) support.getDropLocation();

		int dropIndex = dropLocation.getIndex();
		
		// Suppress the unchecked cast warning
		@SuppressWarnings("unchecked")
		JList<String> targetList = (JList<String>)support.getComponent();

		DefaultListModel<String> listModel
			= (DefaultListModel<String>)targetList.getModel();

		if (dropLocation.isInsert()) {
			listModel.add(dropIndex, data);
		} 
		else {
			listModel.set(dropIndex, data);
		}

		return true;
	}
}
