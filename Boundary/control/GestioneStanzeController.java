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

public class GestioneStanzeController implements Initializable {

	@FXML
	private Label lblGestioneStanze;

	public void initialize(URL url, ResourceBundle rb){

	}

	public void back(ActionEvent event) throws Exception{

		Parent secretaryMainPage_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene secretaryMainPage_scene = new Scene(secretaryMainPage_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(secretaryMainPage_scene);
		app_stage.show();
	}

	public void creazioneStanze(ActionEvent event) throws Exception{

		Parent creazioneStanze_parent = FXMLLoader.load(getClass().getResource("CreazioneStanze.fxml"));
		Scene creazioneStanze_scene = new Scene(creazioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(creazioneStanze_scene);
		app_stage.show();

	}

	public void modificaStanze(ActionEvent event) throws Exception{

		Parent modificaStanze_parent = FXMLLoader.load(getClass().getResource("ModificaStanze.fxml"));
		Scene modificaStanze_scene = new Scene(modificaStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(modificaStanze_scene);
		app_stage.show();

	}

	public void eliminazioneStanze(ActionEvent event) throws Exception{

		Parent eliminazioneStanze_parent = FXMLLoader.load(getClass().getResource("EliminazioneStanze.fxml"));
		Scene eliminazioneStanze_scene = new Scene(eliminazioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(eliminazioneStanze_scene);
		app_stage.show();

	}


	public void creazioneStanzaDaModello(ActionEvent event) throws Exception{

		Parent creazioneStanzaDaModello_parent = FXMLLoader.load(getClass().getResource("CreazioneStanzaDaModello.fxml"));
		Scene creazioneStanzaDaModello_scene = new Scene(creazioneStanzaDaModello_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(creazioneStanzaDaModello_scene);
		app_stage.show();

	}



}
