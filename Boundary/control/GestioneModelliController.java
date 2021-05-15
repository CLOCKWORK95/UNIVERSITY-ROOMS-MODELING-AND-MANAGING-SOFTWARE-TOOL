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

public class GestioneModelliController implements Initializable {


	@FXML
	private Label lblGestioneModelli;


	@Override
	public void initialize(URL url, ResourceBundle rb){

	}

	public void back(ActionEvent event) throws Exception{

		Parent secretaryMainPage_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene secretaryMainPage_scene = new Scene(secretaryMainPage_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(secretaryMainPage_scene);
		app_stage.show();
	}

	public void CreazioneModello(ActionEvent event) throws Exception{

		Parent creazioneModelloVuoto_parent = FXMLLoader.load(getClass().getResource("CreazioneModello.fxml"));
		Scene creazioneModelloVuoto_scene = new Scene(creazioneModelloVuoto_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(creazioneModelloVuoto_scene);
		app_stage.show();

	}

	public void ModificaModello(ActionEvent event) throws Exception{

		Parent modificaModello_parent = FXMLLoader.load(getClass().getResource("ModificaModello.fxml"));
		Scene modificaModello_scene = new Scene(modificaModello_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(modificaModello_scene);
		app_stage.show();

	}


	public void EliminaModello(ActionEvent event) throws Exception{

		Parent eliminaModello_parent = FXMLLoader.load(getClass().getResource("EliminazioneModello.fxml"));
		Scene eliminaModello_scene = new Scene(eliminaModello_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(eliminaModello_scene);
		app_stage.show();

	}



}