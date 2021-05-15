package Boundary.control;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import Bean.SingletonBean;
import Bean.UserBean;

public class SecretaryMainPageController implements Initializable {


	@FXML
	private Label lblSecretaryAccount;
	@FXML
	private Label lblInsertUserName;



	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			UserBean ub =SingletonBean.getSingletonInstance().getUserBean();
			lblInsertUserName.setText(ub.getUsername());

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		}

	}


	@FXML
	public void Logout(ActionEvent event) throws Exception{

		Parent login_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene login_scene = new Scene(login_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(login_scene);
		app_stage.show();
	}

	public void gestioneStanze(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("GestioneStanze.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();

	}

	public void gestioneEdifici(ActionEvent event) throws Exception{
		Parent gestioneEdifici_parent = FXMLLoader.load(getClass().getResource("GestioneEdifici.fxml"));
		Scene gestioneEdifici_scene = new Scene(gestioneEdifici_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneEdifici_scene);
		app_stage.show();
	}

	public void gestioneModelli(ActionEvent event) throws Exception{
		Parent gestioneModelli_parent = FXMLLoader.load(getClass().getResource("GestioneModelli.fxml"));
		Scene gestioneModelli_scene = new Scene(gestioneModelli_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneModelli_scene);
		app_stage.show();
	}

	public void gestioneCaratteristiche(ActionEvent event) throws Exception{
		Parent gestioneCaratteristiche_parent = FXMLLoader.load(getClass().getResource("GestioneCaratteristiche.fxml"));
		Scene gestioneCaratteristiche_scene = new Scene(gestioneCaratteristiche_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneCaratteristiche_scene);
		app_stage.show();
	}

}
