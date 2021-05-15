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

public class GestioneEdificiController implements Initializable {


	@FXML
	private Label lblGestioneEdifici;


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

	public void CreazioneEdificio(ActionEvent event) throws Exception{

		Parent creazioneEdificio_parent = FXMLLoader.load(getClass().getResource("CreazioneEdificio.fxml"));
		Scene creazioneEdificio_scene = new Scene(creazioneEdificio_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(creazioneEdificio_scene);
		app_stage.show();

	}

	public void ModificaEdificio(ActionEvent event) throws Exception{

		Parent modificaEdificio_parent = FXMLLoader.load(getClass().getResource("ModificaEdificio.fxml"));
		Scene modificaEdificio_scene = new Scene(modificaEdificio_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(modificaEdificio_scene);
		app_stage.show();

	}


	public void EliminazioneEdificio(ActionEvent event) throws Exception{

		Parent eliminazioneEdificio_parent = FXMLLoader.load(getClass().getResource("EliminazioneEdificio.fxml"));
		Scene eliminazioneEdificio_scene = new Scene(eliminazioneEdificio_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(eliminazioneEdificio_scene);
		app_stage.show();

	}



}