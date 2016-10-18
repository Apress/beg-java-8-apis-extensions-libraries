// JDialogModalityTest.java
package com.jdojo.swing;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Dialog.ModalityType;

public class JDialogModalityTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame("My JFrame");
		frame.setBounds(0, 0, 400, 400);
		frame.setVisible(true);

		final ModalityType dialog1Modality = ModalityType.DOCUMENT_MODAL;
		final ModalityType dialog2Modality = ModalityType.DOCUMENT_MODAL;
		final JDialog dailog1 = new JDialog(frame, "JDialog 1");

		JButton openBtn = new JButton("Open JDialog 2");
		openBtn.addActionListener(e -> {
			JDialog d2 = new JDialog(dailog1, "JDialog 2");
			d2.setBounds(200, 200, 200, 200);
			d2.setModalityType(dialog2Modality);
			d2.setVisible(true);
		});

		dailog1.add(openBtn);
		dailog1.setBounds(20, 20, 200, 200);
		dailog1.setModalityType(dialog1Modality);
		dailog1.setVisible(true);
	}
}
