package application;

//import java.awt.Button;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class MainSceneController implements Initializable{

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private Button close;
    
    //DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    public void loginAdmin() {
    	
    	String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
    	connect = database.connectDb();
    	
    	try {
    		prepare = connect.prepareStatement(sql);
    		prepare.setString(1, username.getText());
    		prepare.setString(2, password.getText());
    		
    		result = prepare.executeQuery();
    		Alert alert;
    		
    		if(username.getText().isEmpty() || password.getText().isEmpty()){
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank field");
    			alert.showAndWait();
    		}else {
    			if(result.next()) {
    				alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successful Login");
        			alert.showAndWait();
        			
        			loginBtn.getScene().getWindow().hide();
        			Parent root = FXMLLoader.load(getClass().getResource("Beneficiary.fxml"));
        			Stage stage = new Stage();
        			Scene scene = new Scene(root);
        			
        			root.setOnMousePressed((MouseEvent event)->{
        				x = event.getSceneX();
        				y = event.getSceneY();
        			});
        			
        			root.setOnMouseDragged((MouseEvent event)->{
        				stage.setX(event.getScreenX() - x);
        				stage.setY(event.getScreenY() - y);
        			});
        			
        			stage.initStyle(StageStyle.TRANSPARENT);
        			stage.setScene(scene);
        			stage.show();
        			
    			}else {
    				alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Wrong Username/Password");
        			alert.showAndWait();
    			}
    		}
    		
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    public void close() {
    	System.exit(0);
    }
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//TODO
	}
}

//package application;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//public class MainSceneController implements Initializable {
//
//    @FXML
//    private Button loginBtn;
//
//    @FXML
//    private AnchorPane main_form;
//
//    @FXML
//    private PasswordField password;
//
//    @FXML
//    private TextField username;
//
//    @FXML
//    private Button close;
//
//    // DATABASE TOOLS
//    private Connection connect;
//    private PreparedStatement prepare;
//    private ResultSet result;
//
//    private double x = 0;
//    private double y = 0;
//
//    public void loginAdmin() {
//
//        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, username.getText());
//            prepare.setString(2, password.getText());
//
//            result = prepare.executeQuery();
//            Alert alert;
//
//            if (username.getText().isEmpty() || password.getText().isEmpty()) {
//                alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please fill all blank field");
//                alert.showAndWait();
//            } else {
//                if (result.next()) {
//                    alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successful Login");
//                    alert.showAndWait();
//
//                    loginBtn.getScene().getWindow().hide();
//
//                    // Load example.fxml with MyController as its controller
//                    URL location = getClass().getResource("MainScene.fxml");
//                    ResourceBundle resources = ResourceBundle.getBundle("application.MainScene");
//                    FXMLLoader fxmlLoader = new FXMLLoader(location, resources);
//                    Pane root = (Pane) fxmlLoader.load();
//                    MainSceneController controller = (MainSceneController) fxmlLoader.getController();
//
//                    Stage stage = new Stage();
//                    Scene scene = new Scene(root);
//
//                    root.setOnMousePressed((MouseEvent event) -> {
//                        x = event.getSceneX();
//                        y = event.getSceneY();
//                    });
//
//                    root.setOnMouseDragged((MouseEvent event) -> {
//                        stage.setX(event.getScreenX() - x);
//                        stage.setY(event.getScreenY() - y);
//                    });
//
//                    stage.initStyle(StageStyle.TRANSPARENT);
//                    stage.setScene(scene);
//                    stage.show();
//
//                } else {
//                    alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Wrong Username/Password");
//                    alert.showAndWait();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void close() {
//        System.exit(0);
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
//}
