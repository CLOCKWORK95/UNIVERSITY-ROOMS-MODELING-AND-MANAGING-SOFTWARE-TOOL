package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.BuildingBean;
import Bean.OutputBean_B;
import Control.BuildingController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CreazioneEdificioController implements Initializable {

	@FXML
	private Label lblCreazioneEdificio;

	@FXML
	private Label lblInsertName;
	@FXML
	private Label lblInsertMacroarea;
	@FXML
	private TextField txtInsertName;
	@FXML
	private TextField txtInsertMacroarea;



	ObservableList<OutputBean_B> data_E = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_B>> data_E2 = FXCollections.observableArrayList();

	@FXML
	private TableView<OutputBean_B> table_E = new TableView<>();

	@FXML
	private TableColumn<OutputBean_B,String> table_EColumn1;
	@FXML
	private TableColumn<OutputBean_B,String> table_EColumn2;
	@FXML
	private TableColumn<OutputBean_B,Integer> table_EColumn3;



	public void initialize(URL url, ResourceBundle rb){

		table_EColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_B,String>("column1"));
		table_EColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_B,String>("column2"));
		table_EColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_B,Integer>("column3"));
	}

	public void back(ActionEvent event) throws Exception{

		Parent gestioneEdifici_parent = FXMLLoader.load(getClass().getResource("GestioneEdifici.fxml"));
		Scene gestioneEdifici_scene = new Scene(gestioneEdifici_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneEdifici_scene);
		app_stage.show();
	}

	public void fine(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}
	
	public void creazioneEdificio(ActionEvent event) throws Exception{

		BuildingBean buildingbean = new BuildingBean();
		buildingbean.setNomeEdificio(txtInsertName.getText());
		buildingbean.setMacroarea(txtInsertMacroarea.getText());
		BuildingController bc = BuildingController.getInstance();
		bc.createBuilding(buildingbean);
		printBuildingsOnTable_E();
		txtInsertName.setText("");
		txtInsertMacroarea.setText("");
	}


	public void printBuildingsOnTable_E(ActionEvent event) throws Exception{

		data_E.clear();
		BuildingController bc = BuildingController.getInstance();
		bc.printBuildingsOnTableView(data_E,data_E2);
		for (int count = 0; count < data_E2.size(); count ++) {
			table_E.setItems(data_E2.get(count));
		}

	}

	public void printBuildingsOnTable_E() throws Exception{

		data_E.clear();
		BuildingController bc = BuildingController.getInstance();
		bc.printBuildingsOnTableView(data_E,data_E2);
		for (int count = 0; count < data_E2.size(); count ++) {
			table_E.setItems(data_E2.get(count));
		}

	}



}