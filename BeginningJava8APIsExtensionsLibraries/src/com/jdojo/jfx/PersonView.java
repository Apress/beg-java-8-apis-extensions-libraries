// PersonView.java
package com.jdojo.jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PersonView extends Application {	
	// Labels	
	Label fNameLbl = new Label("First Name:");
	Label lNameLbl = new Label("Last Name:");
	Label bDateLbl = new Label("Birth Date:");
	Label genderLbl = new Label("Gender:");
	
	// Fields	
	TextField fNameFld = new TextField();
	TextField lNameFld = new TextField();
	DatePicker bDateFld = new DatePicker();	
	ChoiceBox<String> genderFld = new ChoiceBox<>();	
	TextArea dataFld = new TextArea();
	
	// Buttons
	Button saveBtn = new Button("Save");
	Button closeBtn = new Button("Close");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Populate the gender choice box
		genderFld.getItems().addAll("Male", "Fenale", "Unknwon");
		
		// Set the preferred rows and columns for the text area
		dataFld.setPrefColumnCount(30);
		dataFld.setPrefRowCount(5);
		
		GridPane grid = new GridPane();	
		grid.setHgap(5);
		grid.setVgap(5);
		
		// Place the controls in the grid	
		grid.add(fNameLbl, 0, 0);  // column=0, row=0
		grid.add(lNameLbl, 0, 1);  // column=0, row=1
		grid.add(bDateLbl, 0, 2);  // column=0, row=2
		grid.add(genderLbl, 0, 3); // column=0, row=3
			
		grid.add(fNameFld, 1, 0);  // column=1, row=0
		grid.add(lNameFld, 1, 1);  // column=1, row=1
		grid.add(bDateFld, 1, 2);  // column=1, row=2
		grid.add(genderFld, 1, 3); // column=1, row=3
		grid.add(dataFld, 1, 4, 3, 2); // column=1, row=4, colspan=3, rowspan=3
		
		// Add buttons and make them the same width
		VBox buttonBox = new VBox(saveBtn, closeBtn);
		saveBtn.setMaxWidth(Double.MAX_VALUE);
		closeBtn.setMaxWidth(Double.MAX_VALUE);
		
		grid.add(buttonBox, 2, 0, 1, 2); // column=2, row=0, colspan=1, rowspan=2

		// Show the data in the text area when the Save button is clicked
		saveBtn.setOnAction(e -> showData());
		
		// Close the window when the Close button is clicked
		closeBtn.setOnAction(e -> stage.hide());
				
		// Set a CSS for the GridPane to add a padding and a blue border
		grid.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		Scene scene = new Scene(grid);
		stage.setScene(scene);
		stage.setTitle("Person Details");
		stage.sizeToScene();
		stage.show();
	}
	
	private void showData() {
		String data = "First Name = " + fNameFld.getText() + 
		              "\nLast Name=" + lNameFld.getText() + 
		              "\nBirth Date=" + bDateFld.getValue() + 
		              "\nGender=" + genderFld.getValue();
		dataFld.setText(data);
	}
}
