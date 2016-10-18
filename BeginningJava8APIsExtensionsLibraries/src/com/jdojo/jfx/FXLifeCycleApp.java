// FXLifeCycleApp.java
package com.jdojo.jfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXLifeCycleApp extends Application {
	public FXLifeCycleApp() {
		String name = Thread.currentThread().getName();
		System.out.println("FXLifeCycleApp() constructor: " + name);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		String name = Thread.currentThread().getName();
		System.out.println("init() method: " + name);
	}

	@Override
	public void start(Stage stage) {
		String name = Thread.currentThread().getName();
		System.out.println("start() method: " + name);
		
		// Add an Exit button to the scene
		Button exitBtn = new Button("Exit");
		exitBtn.setOnAction(e -> Platform.exit());
		
		Scene scene = new Scene(new Group(exitBtn), 300, 100);
		stage.setScene(scene);
		stage.setTitle("JavaFX Application Life Cycle");
		stage.show();
	}

	@Override
	public void stop() {
		String name = Thread.currentThread().getName();
		System.out.println("stop() method: " + name);
	}
}
