// PathTest.java
package com.jdojo.jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class PathTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a triangle using a Path		
		Path pathTriangle = new Path(new MoveTo(50, 0),
		                             new LineTo(0, 50),
		                             new LineTo(100, 50),
		                             new LineTo(50, 0));

		pathTriangle.setFill(Color.LIGHTGRAY);
		pathTriangle.setStroke(Color.BLACK);
		
		// Create a triangle using a SVGPath
		SVGPath svgTriangle = new SVGPath();
		svgTriangle.setContent("M50, 0 L0, 50 L100, 50 Z");
		svgTriangle.setFill(Color.LIGHTGRAY);
		svgTriangle.setStroke(Color.BLACK);

		// Add all shapes to an HBox
		HBox root = new HBox(pathTriangle, svgTriangle);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("2D Shapes using Path and SVGPath Classes");
		stage.show();
	}
}
