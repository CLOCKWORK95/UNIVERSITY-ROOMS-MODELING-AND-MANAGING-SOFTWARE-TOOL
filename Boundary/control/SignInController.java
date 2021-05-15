package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.UserBean;
import Control.UserController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController implements Initializable {

	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private TextField txtconfirmPassword;
	@FXML
	private Label lblSignIn;
	@FXML
	private Label lblconfirmPassword;
	@FXML
	private Label lblUsername;
	@FXML
	private Label lblPassword;
	@FXML
	private TextArea txtArea;



	public void initialize(URL url, ResourceBundle rb){

	}


	public void back(ActionEvent event) throws Exception{

		Parent login_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene login_scene = new Scene(login_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(login_scene);
		app_stage.show();
	}

	public void sign(ActionEvent event) throws Exception{

		if ((txtPassword.getText().equals(txtconfirmPassword.getText())) && (txtPassword.getText()!= "")) {

			UserBean userbean = new UserBean();
			UserController uc = UserController.getInstance();
			userbean.setUsername(txtUsername.getText());
			userbean.setPassword(txtPassword.getText());
			uc.signIn(userbean);
			txtArea.setText(userbean.getText());
			txtUsername.setText("");
			txtPassword.setText("");
			txtconfirmPassword.setText("");

		}
	}



}