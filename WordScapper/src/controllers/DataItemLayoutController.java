package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import methods.DataItemRemovalEvent;
import models.MyQuery;

public class DataItemLayoutController {
	@FXML BorderPane dataItemPane;
	@FXML Button closeBtn;
	@FXML TextField startMarkerTxtField, endMarkerTxtField, dataTitleTextField;
	@FXML Label foundNumberLabel;
	@FXML
	public void initialize() {
		
	}
	
    @FXML private void closeItem() {
        // Fire a custom event to notify the parent
    	dataItemPane.fireEvent(new DataItemRemovalEvent(dataItemPane));
    }
    public MyQuery buildQuery() {
    	String queryName = dataTitleTextField.getText();
    	String startMarker = startMarkerTxtField.getText();
    	String endMarker = endMarkerTxtField.getText();
    	if(!queryName.isBlank() && !queryName.isEmpty() && !startMarker.isBlank() && !startMarker.isEmpty() && !endMarker.isBlank() && !endMarker.isEmpty()) {
        	return new MyQuery(queryName, startMarker, endMarker);    		
    	}else {
    		return null;
    	}
    }
}
