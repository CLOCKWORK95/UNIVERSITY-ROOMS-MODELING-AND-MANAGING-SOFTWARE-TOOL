package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.FeatureBean;
import Bean.OutputBean_F;
import Control.FeatureController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class EliminazioneCaratteristicheController implements Initializable {

	@FXML
	private Label lblEliminazioneCaratteristica;


	ObservableList<OutputBean_F> data_F = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_F>> data_F2 = FXCollections.observableArrayList();


	@FXML
	private TableView<OutputBean_F> table_F = new TableView<>();

	@FXML
	private TableColumn<OutputBean_F,String> table_FColumn1;
	@FXML
	private TableColumn<OutputBean_F,String> table_FColumn2;
	@FXML
	private TableColumn<OutputBean_F,Integer> table_FColumn3;




	public void initialize(URL url, ResourceBundle rb){

		table_FColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_F,String>("column1"));
		table_FColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_F,String>("column2"));
		table_FColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_F,Integer>("column3"));

	}

	public void back(ActionEvent event) throws Exception{

		Parent gestioneCaratteristiche_parent = FXMLLoader.load(getClass().getResource("GestioneCaratteristiche.fxml"));
		Scene gestioneCaratteristiche_scene = new Scene(gestioneCaratteristiche_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneCaratteristiche_scene);
		app_stage.show();
	}

	public void fine(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}

	public void eliminaCaratteristica(ActionEvent event) throws Exception{
		FeatureBean featurebean = new FeatureBean();
		FeatureController fc = FeatureController.getInstance();
		OutputBean_F outputbean = table_F.getSelectionModel().getSelectedItem();
		featurebean.setName(outputbean.getColumn1());
		featurebean.setDescription(outputbean.getColumn2());
		featurebean.setCodiceFeature(outputbean.getColumn3());

		fc.deleteFeature(featurebean);
		printFeaturesOnTable_F();
	}

	public void eliminaTutto(ActionEvent event) throws Exception{

		FeatureController fc = FeatureController.getInstance();
		fc.deleteAll();
		printFeaturesOnTable_F();

	}



	public void printFeaturesOnTable_F(ActionEvent event) throws Exception{

		data_F.clear();
		FeatureController fc = FeatureController.getInstance();
		fc.printFeaturesOnTable(data_F,data_F2);
		for (int count = 0; count<= data_F2.size()-1; count ++) {
			table_F.setItems(data_F2.get(count));
		}
	}

	public void printFeaturesOnTable_F() throws Exception{

		data_F.clear();
		FeatureController fc = FeatureController.getInstance();
		fc.printFeaturesOnTable(data_F,data_F2);
		for (int count = 0; count<= data_F2.size()-1; count ++) {
			table_F.setItems(data_F2.get(count));
		}

	}




}