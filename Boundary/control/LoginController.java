package Boundary.control;
/*       How to create a new stage (window) in javafx
 * 			Stage primaryStage = new Stage();
			Parent root = null;
			root = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
 */


import java.net.URL;
import java.util.ResourceBundle;

import Bean.SingletonBean;
import Bean.UserBean;
import Control.UserController;
import Threads.MainClassThreadTest;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private Label lblWelcome;
	@FXML
	private Label lblLogin;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;
	@FXML
	private Label lblSecretaryAccount;
	@FXML
	private Label lblInsertUserName;

	@Override
	public void initialize(URL url, ResourceBundle rb){

	}


	@FXML
	public void Login(ActionEvent event) throws Exception{


		UserBean userbean = new UserBean();
		UserController lc = UserController.getInstance();
		userbean.setUsername(txtUserName.getText());
		userbean.setPassword(txtPassword.getText());
	    SingletonBean.getSingletonInstance().setUserBean(userbean);
		lc.login(userbean);

		//Accesso Utente di Segreteria
		if (userbean.getResult() == true){
			// (codice per il cambio scena)
			
			//Popola il database con delle entità esempio.
			MainClassThreadTest.populate();
			
			Parent secretaryMainPage_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
			Scene secretaryMainPage_scene = new Scene(secretaryMainPage_parent);
			Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			app_stage.setScene(secretaryMainPage_scene);
			app_stage.show();
		}
		//Accesso Negato!
		else {
			lblWelcome.setText("Login Failed");
		}

	}

	@FXML
	public void SignIn(ActionEvent event) throws Exception{

		Parent signIn_parent = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		Scene signIn_scene = new Scene(signIn_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(signIn_scene);
		app_stage.show();

	}


}
