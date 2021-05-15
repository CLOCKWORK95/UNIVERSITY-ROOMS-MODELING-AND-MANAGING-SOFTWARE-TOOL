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

public class GestioneCaratteristicheController implements Initializable {

	@FXML
	private Label lblGestioneCaratteristiche;

	public void initialize(URL url, ResourceBundle rb){

	}

	public void back(ActionEvent event) throws Exception{

		Parent secretaryMainPage_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene secretaryMainPage_scene = new Scene(secretaryMainPage_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(secretaryMainPage_scene);
		app_stage.show();
	}

	public void creazioneCaratteristiche(ActionEvent event) throws Exception{

		Parent creazioneCaratteristiche_parent = FXMLLoader.load(getClass().getResource("CreazioneCaratteristiche.fxml"));
		Scene creazioneCaratteristiche_scene = new Scene(creazioneCaratteristiche_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(creazioneCaratteristiche_scene);
		app_stage.show();

	}

	public void eliminazioneCaratteristiche(ActionEvent event) throws Exception{

		Parent eliminazioneCaratteristiche_parent = FXMLLoader.load(getClass().getResource("EliminazioneCaratteristiche.fxml"));
		Scene eliminazioneCaratteristiche_scene = new Scene(eliminazioneCaratteristiche_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(eliminazioneCaratteristiche_scene);
		app_stage.show();

	}

	public void tracciareStatoCaratteristiche(ActionEvent event) throws Exception{

		Parent tracciareStatoCaratteristiche_parent = FXMLLoader.load(getClass().getResource("TracciareStatoCaratteristiche.fxml"));
		Scene tracciareStatoCaratteristiche_scene = new Scene(tracciareStatoCaratteristiche_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(tracciareStatoCaratteristiche_scene);
		app_stage.show();

	}



}