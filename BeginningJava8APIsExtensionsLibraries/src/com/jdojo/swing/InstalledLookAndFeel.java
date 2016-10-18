// InstalledLookAndFeel.java
package com.jdojo.swing;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class InstalledLookAndFeel {
	public static void main(String[] args) {
		// Get the list of installed L&F
		LookAndFeelInfo[] lafList = UIManager.getInstalledLookAndFeels();

		// Print the names and class names of all installed L&F
		for (LookAndFeelInfo lafInfo : lafList) {
			String name = lafInfo.getName();
			String className = lafInfo.getClassName();
			System.out.println("Name: " + name + ", Class Name: " + className);
		}
	}
}
