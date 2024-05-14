package application;

//import java.awt.Button;
//import java.awt.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

//import com.mysql.cj.xdevapi.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.Scene;
import javafx.stage.Window;


public class BeneficiaryController implements Initializable{

    @FXML
    private AnchorPane main_form;
	
    @FXML
    private Button Add;

    @FXML
    private Button Clear;

    @FXML
    private Button Delete;

    @FXML
    private TextField E_mail;

    @FXML
    private TextField First_Name;

    @FXML
    private TextField GPA;

    @FXML
    private TextField Last_Name;

    @FXML
    private Button Update;

    @FXML
    private TextField User_ID;

    @FXML
    private Button addUserBtn;

    @FXML
    private AnchorPane beneficiaryform;
    
    @FXML
    private TextField Scholarship_Applied;

    @FXML
    private TextField School_Project_Applied;

    @FXML
    private Button close;

    @FXML
    private TableColumn<UserInfoBeneficiary, String> colm_Email;

    @FXML
    private TableColumn<UserInfoBeneficiary, String> colm_FirstName;

    @FXML
    private TableColumn<UserInfoBeneficiary, String> colm_GPA;

    @FXML
    private TableColumn<UserInfoBeneficiary, String> colm_LastName;

    @FXML
    private TableColumn<UserInfoBeneficiary, String> colm_ScholarshipApplied;

    @FXML
    private TableColumn<UserInfoBeneficiary, String> colm_SchoolProjectApplied;

    @FXML
    private TableColumn<UserInfoBeneficiary, String> colm_UserID;

    @FXML
    private Button homeButton;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private TextField search;

    @FXML
    private TableView<UserInfoBeneficiary> tableView;

    @FXML
    //private Label username;
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
    public void addUserAdd() {
    	
    	String sql = "INSERT INTO User" 
    			+"(User_ID, First_Name, Last_Name, E_mail, GPA, Scholarship_Applied, School_Project_Applied) "
    			+ "VALUES(?,?,?,?,?,?,?)";
    	
    	connect = database.connectDb();
    	
    	try {
    		Alert alert;
    		if (User_ID.getText().isEmpty()
    			|| First_Name.getText().isEmpty()
    			|| Last_Name.getText().isEmpty()
    			|| E_mail.getText().isEmpty()
    			|| GPA.getText().isEmpty()
    			|| Scholarship_Applied.getText().isEmpty()
    			|| School_Project_Applied.getText().isEmpty()) {
    		alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Message");
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill all the blank fields");
    		alert.showAndWait();
    		}else {
    			
    			String check = "SELECT User_ID FROM User WHERE USER_ID = '"
    					+User_ID.getText() +"'";
    			
    			statement = connect.createStatement();
    			result = statement.executeQuery(check);
    			
    			if(result.next()) {
    	    		alert = new Alert(AlertType.ERROR);
    	    		alert.setTitle("Error Message");
    	    		alert.setHeaderText(null);
    	    		alert.setContentText("User ID: "+ User_ID.getText() + "was already existing!");
    	    		alert.showAndWait();
    			}else {
    		        prepare = connect.prepareStatement(sql);
    		        prepare.setString(1, User_ID.getText());
    		        prepare.setString(2, First_Name.getText());
    		        prepare.setString(3, Last_Name.getText());
    		        prepare.setString(4, E_mail.getText());
    		        prepare.setString(5, GPA.getText());
    		        prepare.setString(6, Scholarship_Applied.getText());
    		        prepare.setString(7, School_Project_Applied.getText());
    		        
    		        prepare.executeUpdate();
    		        
    	    		alert = new Alert(AlertType.INFORMATION);
    	    		alert.setTitle("Information Message");
    	    		alert.setHeaderText(null);
    	    		alert.setContentText("Successfully Added!");
    	    		alert.showAndWait();
    	    		
    	    		addUserShowListData();
    	    		addUserReset();
    			}
    		}
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    public void addUserReset() {
    	User_ID.setText("");
    	First_Name.setText("");
    	Last_Name.setText("");
    	E_mail.setText("");
    	GPA.setText("");
    	Scholarship_Applied.setText("");
    	School_Project_Applied.setText("");
    	
    }
    
//    private String[] positionList = {"Marketer Coordinator", "Web Developer (Back End)", "Web Developer (Back End)", "App Developer"};
//    public void addUserPositionList() {
//    	List<String> listP = new ArrayList<>();
//    	
//    	for(String data: positionList) {
//    		listP.add(data);
//    	}
//    	
//    	ObservableList listData = FXCollections.ObservableArrayList(listP);
//    }
    
    public ObservableList<UserInfoBeneficiary> addUserListData(){
    	
    	ObservableList<UserInfoBeneficiary> listData = FXCollections.observableArrayList();
    	String sql = "SELECT * FROM UserBeneficiary";
    	
    	connect = database.connectDb();
    	
    	try {
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		UserInfoBeneficiary UserD;
    		
    		while(result.next()) {
    			UserD = new UserInfoBeneficiary(result.getInt("User_ID"), 
    					result.getString("First_Name"), 
    					result.getString("Last_Name"), 
    					result.getString("E_mail"), 
    					result.getInt("GPA"), 
    					result.getString("Scholarship_Applied"), 
    					result.getString("School_Project_Applied"));
    			listData.add(UserD);
    		}
    			
    	}catch(Exception e) {e.printStackTrace();}
    	return listData;
    }
    
    private ObservableList<UserInfoBeneficiary> addUserBList;
    public void addUserShowListData() {
    	addUserBList = addUserListData();
    	
    	colm_UserID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
    	colm_FirstName.setCellValueFactory(new PropertyValueFactory<>("First_Name"));
    	colm_LastName.setCellValueFactory(new PropertyValueFactory<>("Last_Name"));
    	colm_Email.setCellValueFactory(new PropertyValueFactory<>("E_mail"));
    	colm_GPA.setCellValueFactory(new PropertyValueFactory<>("GPA"));
    	colm_ScholarshipApplied.setCellValueFactory(new PropertyValueFactory<>("Scholarship_Applied"));
    	colm_SchoolProjectApplied.setCellValueFactory(new PropertyValueFactory<>("School_Project_Applied"));
    	tableView.setItems(addUserBList);
    }
    
    public void addUserSelect() {
    	UserInfoBeneficiary UserD = tableView.getSelectionModel().getSelectedItem();
    	int num = tableView.getSelectionModel().getSelectedIndex();
    	
    	if((num-1)< -1) {return;}
    	
    	User_ID.setText(String.valueOf(UserD.getUser_ID()));
    	First_Name.setText(String.valueOf(UserD.getFirst_Name()));
    	Last_Name.setText(String.valueOf(UserD.getLast_Name()));
    	E_mail.setText(String.valueOf(UserD.getE_mail()));
    	GPA.setText(String.valueOf(UserD.getGPA()));
    	Scholarship_Applied.setText(String.valueOf(UserD.getScholarship_Applied()));
    	School_Project_Applied.setText(String.valueOf(UserD.getSchool_Project_Applied()));
    	
    	
    }
    
    
    
    //public void displayUsername() {
    	//username.setText(getData.username);
    //}
	
    private double x=0;
    private double y=0;
    public void logout() {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Message");
    	alert.setHeaderText(null);
    	alert.setContentText("Are you sure you want to logout?");
    	Optional<ButtonType> option = alert.showAndWait();
    	//try {
    		//if(option.get().equals(ButtonType.OK)) {
    			
    			//logout.getScene().getWindow().hide();
    			//Parent root = FXMLLoader.load(getClass().getResource("Beneficiary.fxml"));
    			//Stage stage = new Stage();
    			//Scene scene = new Scene(root);
    			
                //root.setOnMousePressed((MouseEvent event) -> {
            //x = event.getSceneX();
            //y = event.getSceneY();
        //});

        //root.setOnMouseDragged((MouseEvent event) -> {
            //stage.setX(event.getScreenX() - x);
            //stage.setY(event.getScreenY() - y);
        //});

        //stage.initStyle(StageStyle.TRANSPARENT);
    			
    			//stage.setScene(scene);
    			//stage.show();
    		//}
    	//}catch(Exception e) {e.printStackTrace();}
    }
    
    public void close() {
    	System.exit(0);
    }
    
    public void minimize() {
    	Stage stage = (Stage)main_form.getScene().getWindow();
    	stage.setIconified(true);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addUserShowListData();
		//addUserPositionList();
	}
}