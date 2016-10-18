// TransformationTest.java
package com.jdojo.jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class TransformationTest  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setStroke(Color.BLACK);

		Rectangle rect2 = new Rectangle(100, 50, Color.YELLOW);
		rect2.setStroke(Color.BLACK);

		// Apply a translation, rotate, scale and shear transformations
		// to rect2
		Translate translate = new Translate(50, 10);
		Rotate rotate = new Rotate(30, 0, 0);
		Scale scale = new Scale(0.5, 0.5);
		Shear shear = new Shear(0.5, 0.5);
		rect2.getTransforms().addAll(translate, rotate, scale, shear);
		
		Pane root = new Pane(rect1, rect2);
		root.setPrefSize(200, 100);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying Transformations");
		stage.show();
	}
}
