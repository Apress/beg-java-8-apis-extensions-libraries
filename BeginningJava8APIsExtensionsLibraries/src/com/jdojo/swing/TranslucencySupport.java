// TranslucencySupport.java
package com.jdojo.swing;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class TranslucencySupport {
	public static void main(String[] args) {
		GraphicsEnvironment graphicsEnv
			= GraphicsEnvironment.getLocalGraphicsEnvironment();

		GraphicsDevice graphicsDevice
			= graphicsEnv.getDefaultScreenDevice();

		// Print the translucency supported by the platform 
		boolean isSupported
			= graphicsDevice.isWindowTranslucencySupported(
				PERPIXEL_TRANSPARENT);
		System.out.println("PERPIXEL_TRANSPARENT supported: "
			+ isSupported);

		isSupported
			= graphicsDevice.isWindowTranslucencySupported(TRANSLUCENT);
		System.out.println("TRANSLUCENT supported: " + isSupported);

		isSupported = graphicsDevice.isWindowTranslucencySupported(
			PERPIXEL_TRANSLUCENT);
		System.out.println("PERPIXEL_TRANSLUCENT supported: "
			+ isSupported);
	}
}
