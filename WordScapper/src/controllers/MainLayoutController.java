package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import methods.CSVMethods;
import methods.DataItemRemovalEvent;
import methods.GUIMethods;
import methods.WordMethods;
import models.MyQuery;
import models.RegexMethods;

public class MainLayoutController {
	public static File openedDocxFile;
	private boolean docxIsSelected = false;
	private boolean csvIsExported = false;
	public boolean ignoreMultipleSpaces = true;
	public boolean ignoreElongation = true;
	
	private ArrayList<MyQuery> queriesList = new ArrayList<MyQuery>();
	private ArrayList<String> tablesContentsList = new ArrayList<String>();


	@FXML BorderPane mainLayoutPane;
	@FXML TilePane itemsTilePane;
	@FXML TextField docxPathTxtField;
	@FXML Button openDocxBtn, exportCSVBtn,  addDataItemBtn;
	@FXML CheckBox ignoreSpacesCheckBox, ignoreDashesCheckBox;
	@FXML ScrollPane scrollPane;

	@FXML
	public void initialize() {
		// Listen for ChildRemovalEvent on the GridPane
		itemsTilePane.addEventHandler(DataItemRemovalEvent.REMOVE_CHILD, event -> {			
			GUIMethods.deleteNodeFromTilePane(itemsTilePane, event.getChildToRemove());
		});
		ignoreSpacesCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obj, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					ignoreMultipleSpaces = true;
				}else {
					ignoreMultipleSpaces = false;
				}
			}
		});
		ignoreDashesCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obj, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					ignoreElongation = true;
				}else {
					ignoreElongation = false;
				}
			}
		});
	}
	@FXML
	public void openDocx() throws IOException {
		openedDocxFile = GUIMethods.OpenDocxFile();
		if(openedDocxFile!= null) {
			docxIsSelected = true;
			docxPathTxtField.setText(openedDocxFile.getAbsolutePath());
			XWPFDocument wordDocument = WordMethods.openDocx(openedDocxFile);
			for(XWPFTable table : wordDocument.getTables()) {
				System.out.println(String.format("Skip Spaces: %b , Skip Elongation: %b", ignoreMultipleSpaces, ignoreElongation));
				tablesContentsList.add(WordMethods.getTableContents(table, ignoreMultipleSpaces, ignoreElongation));
			}
		}
	}
	@FXML
	public void openCSV() {
		if(csvIsExported) {

		}else {
			GUIMethods.showErrorMessage("Please export CSV file first !");
		}
	}
	@FXML
	public void exportCSV() {
		if(docxIsSelected) {
			queriesList =  getQueries();
			File csvFile = GUIMethods.exportFile();
			StringBuilder foundText = new StringBuilder();
			for(MyQuery query: queriesList) {
				foundText.append(query.getQueryName()+";");
			}
			foundText.append("\n");

			for(String tableContent: tablesContentsList) {
				for(MyQuery query: queriesList) {
					String result = RegexMethods.myRegexMatcher(tableContent, query.getRegex(ignoreMultipleSpaces, csvIsExported));
					result = WordMethods.cleanText(result, ignoreMultipleSpaces, ignoreElongation);
					foundText.append(String.format("%s;", result));
				}
				foundText.append("\n");
			}
			CSVMethods.saveCSV(foundText.toString(), csvFile);
		}else {
			GUIMethods.showErrorMessage("please open docx file first!");
		}
	}
	public ArrayList<MyQuery> getQueries(){
		ArrayList<MyQuery> list = new ArrayList();
		for(Node node : itemsTilePane.getChildren()) {
			if (node.getUserData() instanceof DataItemLayoutController) {
				DataItemLayoutController controller = (DataItemLayoutController) node.getUserData();
				list.add(controller.buildQuery());
			}
		}
		return list;
	}


	@FXML
	public void confirmPageMarkers() {

	}
	@FXML
	public void addDataItem() throws IOException {
		if(docxIsSelected) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dataItemLayout.fxml"));
			Parent dataItemPane = loader.load();
			DataItemLayoutController dataItemLayoutController = loader.getController();
			dataItemPane.setUserData(dataItemLayoutController);
			itemsTilePane.getChildren().add(dataItemPane);
		}else {
			GUIMethods.showErrorMessage("Please select Valid Word Document file first !");
		}
	}
}
