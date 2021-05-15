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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EliminazioneEdificioController implements Initializable {

	@FXML
	private Label lblEliminazioneEdificio;

	@FXML
	private Label lblSelect;


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

		Parent gestioneEdificio_parent = FXMLLoader.load(getClass().getResource("GestioneEdifici.fxml"));
		Scene gestioneEdificio_scene = new Scene(gestioneEdificio_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneEdificio_scene);
		app_stage.show();
	}
	
	public void fine(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}



	public void eliminazioneEdificio(ActionEvent event) throws Exception{

		BuildingController bc = BuildingController.getInstance();
		BuildingBean bb = new BuildingBean();
		OutputBean_B outputbean = table_E.getSelectionModel().getSelectedItem();
		bb.setNomeEdificio(outputbean.getColumn1());
		bb.setMacroarea(outputbean.getColumn2());
		bc.deleteBuilding(bb);
		printBuildingsOnTable_E();


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