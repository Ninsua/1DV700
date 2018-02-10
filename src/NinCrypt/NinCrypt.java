package NinCrypt;

/*
 * TODO
 * Too much
 */

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NinCrypt extends Application {
	static final boolean SUBSTITUTION = true;
	static final boolean TRANSPOSITION  = false;
	static final boolean ENCRYPT = true;
	static final boolean DECRYPT  = false;
	static boolean tech = true;
	static boolean mode = true;
	static File selectedFile;

	private void execute(File f,String key) throws IllegalStateException {
		File file = f;
		String input;
		String output;
		Crypt encryptor;

		//Check techs
		if (tech == TRANSPOSITION) {
			encryptor = new PermEncryptor();
		}
		else {
			encryptor = new SubEncryptor();
		}

		//Read file
		input = FileHelper.readFile(file);

		//Encrypt/decrypt
		if (mode == DECRYPT) {
			encryptor.setCipherText(input);
			encryptor.setKey(key);
			encryptor.decrypt();
			output = encryptor.getPlainText();
		}
			
		else {
			encryptor.setPlainText(input.toString());
			encryptor.setKey(key);
			encryptor.encrypt();
			output = encryptor.getCipherText();
		}
		
		//Write to file
		FileHelper.writeFile(file,output);
	}
	
	//Simple GUI
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("NinCrypt version 0.01");
		String bold = "-fx-font-weight: bold";
		String italic = "-fx-font-style: italic";
		
	    //Nodes
		Label techLabel = new Label("Technique");
		techLabel.setFont(Font.font("Helvetica"));
		techLabel.setStyle(bold);
		Label modeLabel = new Label("Mode");
		modeLabel.setFont(Font.font("Helvetica"));
		modeLabel.setStyle(bold);
		Label fileLabel = new Label("File");
		fileLabel.setFont(Font.font("Helvetica"));
		fileLabel.setStyle(bold);
		Label pathLabel = new Label("");
		pathLabel.setFont(Font.font("Helvetica"));
		pathLabel.setStyle(italic);
		Label keyLabel = new Label("Key");
		keyLabel.setFont(Font.font("Helvetica"));
		keyLabel.setStyle(bold);
	    Label results = new Label("");
	    results.setFont(Font.font("Helvetica"));
	    results.setStyle(bold);
		
	    final TextField keyField = new TextField();
	    keyField.setPrefWidth(400);
	    
	    ToggleGroup techButtons = new ToggleGroup();
	    ToggleGroup modeButtons = new ToggleGroup();
	    
	    RadioButton subButton = new RadioButton("Substitution");
	    RadioButton permButton = new RadioButton("Transposition");
	    subButton.setToggleGroup(techButtons);
	    subButton.setOnAction(techToSub -> tech = SUBSTITUTION);
	    permButton.setToggleGroup(techButtons);
	    permButton.setOnAction(techToSub -> tech = TRANSPOSITION);
	    techButtons.selectToggle(subButton);
	    
	    RadioButton encryptButton = new RadioButton("Encrypt");
	    encryptButton.setOnAction(techToSub -> mode = ENCRYPT);
	    RadioButton decryptButton = new RadioButton("Decrypt");
	    decryptButton.setOnAction(techToSub -> mode = DECRYPT);
	    encryptButton.setToggleGroup(modeButtons);
	    decryptButton.setToggleGroup(modeButtons);
	    modeButtons.selectToggle(encryptButton);
	    
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Select file");
	    fileChooser.selectedExtensionFilterProperty();
	    
	    Button fileChooserOpener = new Button("Select file");
	    fileChooserOpener.setOnAction(opfen -> {
	    		if (selectedFile != null) {
	    			fileChooser.setInitialDirectory(selectedFile.getParentFile());
	    		}
                selectedFile = fileChooser.showOpenDialog(primaryStage);
                if (selectedFile != null) {
                	pathLabel.setText(selectedFile.getPath());
                }
                });
	   
	    Button execute = new Button("Execute");
	    execute.setOnAction(run -> {
	    	try {
	    		FileHelper.fileCheck(selectedFile);
	    		execute(selectedFile,keyField.getText());
	    		results.setText("Success!");
	    	}
	    	
	    	catch (IllegalStateException e) {
	    		results.setText(e.getMessage());
	    	}
	    });
	    
	    //Panes
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
	    grid.setHgap(4);
	    grid.setVgap(4);
	    grid.setPadding(new Insets(0,5,5,5));
	    //grid.setGridLinesVisible(true); //For debugging
		
		grid.add(techLabel, 0, 0);
		grid.add(subButton, 0, 1); //Sub bottom
		grid.add(permButton, 0, 2); //Perm button
		grid.add(modeLabel, 0, 3);
		grid.add(encryptButton, 0, 4); //Sub button
		grid.add(decryptButton, 0, 5); //Perm button
		grid.add(fileLabel, 0, 6);
		grid.add(fileChooserOpener, 0, 7);
		grid.add(pathLabel, 0, 8);
		grid.add(keyLabel, 0, 9);
		grid.add(keyField, 0, 10);
		grid.add(execute, 0, 11);
		grid.add(results, 0, 12);
		
		pathLabel.setText("Warning: Currently only works on text files");
		Scene scene = new Scene(grid);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//Run GUI
	public static void main(String[] args) {
		launch(args);
	}

}