package application;
import javafx.scene.Parent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main1 extends Application{
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}


    public static void main(String[] args) {
	    launch(args);
    }
}
