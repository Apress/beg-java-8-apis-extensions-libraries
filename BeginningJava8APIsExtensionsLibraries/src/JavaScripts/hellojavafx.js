// hellojavafx.js

// Load Nashorn pre-defined scripts to import JavaFX specific classes and packages
load("fx:base.js")
load("fx:controls.js")
load("fx:graphics.js")

// Define the start() method of the JavaFX application class
function start(stage) {
	var nameLbl = new Label("Enter your name:");
	var nameFld = new TextField();
	var msg = new Label();
	msg.setStyle("-fx-text-fill: blue;");

	// Create buttons 
	var sayHelloBtn = new Button("Say Hello");
	var exitBtn = new Button("Exit");

	// Add the event handler for the Say Hello button
	sayHelloBtn.onAction = function() {
		var name = nameFld.getText();
		if (name.trim().length() > 0) {
			msg.text = "Hello " + name;
		}
		else {
			msg.text = "Hello there";
		}
	};

	// Add the event handler for the Exit button
	exitBtn.onAction = function() {
		Platform.exit();
	};

	// Create the root node
	var root = new VBox();

	// Set the vertical spacing between children to 5px
	root.spacing = 5;

	// Add children to the root node
	root.children.addAll(nameLbl, nameFld, msg, sayHelloBtn, exitBtn);

	// Set the scene and title for the stage
	stage.scene = new Scene(root, 350, 150);
	stage.title = "Hello JavaFX from Nashorn";

	// Show the stage
	stage.show();
}
