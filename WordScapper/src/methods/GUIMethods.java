package methods;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hpsf.Array;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUIMethods {
	public static File OpenDocxFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        // Set file filter (optional)
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Docx Files", "*.docx")
        );

        // Show the open file dialog
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        // Check if a file was selected
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        	return selectedFile;
        } else {
            System.out.println("No file selected.");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No file selected.");           // Dialog title
            alert.setHeaderText("No file selected.");     // Header text (can be null)
            alert.setContentText("No file selected.");   // Detailed error message
            alert.showAndWait();   
        	return null;
        }
	}
	public static File exportFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export CSV File");

        // Set file filter (optional)
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        // Show the open file dialog
        File savedFile = fileChooser.showSaveDialog(new Stage());


        // Check if a file was selected
        if (savedFile != null) {
            System.out.println("saved file: " + savedFile.getAbsolutePath());
        	return savedFile;
        } else {
            System.out.println("No file selected.");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No file selected.");           // Dialog title
            alert.setHeaderText("No file selected.");     // Header text (can be null)
            alert.setContentText("No file selected.");   // Detailed error message
            alert.showAndWait();   
        	return null;
        }		
	}
	
	public static void showErrorMessage(String message) {
        System.out.println(message);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(message);           // Dialog title
        alert.setHeaderText(message);     // Header text (can be null)
        alert.setContentText(message);   // Detailed error message
        alert.showAndWait();   
	}
	public static void deleteNodeFromTilePane(TilePane tilePane, Node node) {
		tilePane.getChildren().remove(node);
	}

}
