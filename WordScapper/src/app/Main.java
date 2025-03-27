package app;

import controllers.MainLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application { 
	@Override
	public void start(Stage primaryStage) {
		try {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/mainLayout.fxml"));
//			MainLayoutController mainController = loader.getController();
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
