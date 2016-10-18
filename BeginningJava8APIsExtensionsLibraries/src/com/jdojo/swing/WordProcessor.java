// WordProcessor.java
package com.jdojo.swing;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;
import java.awt.Color;

public class WordProcessor extends JFrame {
	JTextPane textPane = new JTextPane();

	JButton normalBtn = new JButton("Normal");
	JButton boldBtn = new JButton("Bold");
	JButton italicBtn = new JButton("Italic");
	JButton underlineBtn = new JButton("Underline");
	JButton superscriptBtn = new JButton("Superscript");
	JButton blueBtn = new JButton("Blue");
	JButton leftBtn = new JButton("Left Align");
	JButton rightBtn = new JButton("Right Align");

	public WordProcessor(String title) {
		super(title);
		initFrame();
	}

	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();

		JPanel buttonPanel = this.getButtonPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		contentPane.add(textPane, BorderLayout.CENTER);

		this.addStyles(); // Add styles to the text pane for later use 
		insertTestStrings(); // Insert some texts to the text pane 
	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(normalBtn);
		buttonPanel.add(boldBtn);
		buttonPanel.add(italicBtn);
		buttonPanel.add(underlineBtn);
		buttonPanel.add(superscriptBtn);
		buttonPanel.add(blueBtn);
		buttonPanel.add(leftBtn);
		buttonPanel.add(rightBtn);

		// Add ation event listeners to buttons
		normalBtn.addActionListener(e -> setNewStyle("normal", true));
		boldBtn.addActionListener(e -> setNewStyle("bold", true));
		italicBtn.addActionListener(e -> setNewStyle("italic", true));
		underlineBtn.addActionListener(
			e -> setNewStyle("underline", true));
		superscriptBtn.addActionListener(
			e -> setNewStyle("superscript", true));
		blueBtn.addActionListener(e -> setNewStyle("blue", true));
		leftBtn.addActionListener(e -> setNewStyle("left", false));
		rightBtn.addActionListener(e ->	setNewStyle("right", false));

		return buttonPanel;
	}

	private void addStyles() {
		// Get the default style 
		StyleContext sc = StyleContext.getDefaultStyleContext();
		Style defaultContextStyle = 
			sc.getStyle(StyleContext.DEFAULT_STYLE);

		// Add some styles to the document, to retrieve and use later 
		StyledDocument document = textPane.getStyledDocument();
		Style normalStyle = 
			document.addStyle("normal", defaultContextStyle);

		// Create a bold style 
		Style boldStyle = document.addStyle("bold", normalStyle);
		StyleConstants.setBold(boldStyle, true);

		// Create an italic style 
		Style italicStyle = document.addStyle("italic", normalStyle);
		StyleConstants.setItalic(italicStyle, true);

		// Create an underline style 
		Style underlineStyle = 
			document.addStyle("underline", normalStyle);
		StyleConstants.setUnderline(underlineStyle, true);

		// Create a superscript style 
		Style superscriptStyle = 
			document.addStyle("superscript", normalStyle);
		StyleConstants.setSuperscript(superscriptStyle, true);

		// Create a blue color style 
		Style blueColorStyle = document.addStyle("blue", normalStyle);
		StyleConstants.setForeground(blueColorStyle, Color.BLUE);

		// Create a left alignment paragraph style 
		Style leftStyle = document.addStyle("left", normalStyle);
		StyleConstants.setAlignment(leftStyle, StyleConstants.ALIGN_LEFT);

		// Create a right alignment paragraph style 
		Style rightStyle = document.addStyle("right", normalStyle);
		StyleConstants.setAlignment(
			rightStyle, StyleConstants.ALIGN_RIGHT);
	}

	private void setNewStyle(String styleName, boolean isCharacterStyle) {
		StyledDocument document = textPane.getStyledDocument();
		Style newStyle = document.getStyle(styleName);
		int start = textPane.getSelectionStart();
		int end = textPane.getSelectionEnd();
		if (isCharacterStyle) {
			boolean replaceOld = styleName.equals("normal");
			document.setCharacterAttributes(start, end - start, 				                                newStyle, replaceOld);
		}
		else {
			document.setParagraphAttributes(start, end - start,
			                                newStyle, false);
		}
	}

	private void insertTestStrings() {
		StyledDocument document = textPane.getStyledDocument();
		try {
			document.insertString(0, "Hello JTextPane\n", null);
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WordProcessor frame = new WordProcessor("Word Processor");
		frame.setSize(700, 500);
		frame.setVisible(true);
	}
}
