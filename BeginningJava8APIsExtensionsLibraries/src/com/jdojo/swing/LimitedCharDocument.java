// LimitedCharDocument.java
package com.jdojo.swing;

import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;

public class LimitedCharDocument extends PlainDocument {
	private int limit = -1; // < 0 means an unlimited characters 

	public LimitedCharDocument() {
	}

	public LimitedCharDocument(int limit) {
		this.limit = limit;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet a) 
									throws BadLocationException {
		String newString = str;
		if (limit >= 0 && str != null) {
			// Check for the limit 
			int currentLength = this.getLength() ;
			int newTextLength = str.length();
			if (currentLength + newTextLength > limit) {
				newString = str.substring(0, limit - currentLength);
			}
		}

		super.insertString(offset, newString, a);
	}
}
