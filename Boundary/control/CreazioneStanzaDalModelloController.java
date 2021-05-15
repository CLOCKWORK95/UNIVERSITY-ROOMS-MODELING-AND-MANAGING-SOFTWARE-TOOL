package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.ModelBean;
import Bean.OutputBean;
import Bean.OutputBean_B;
import Bean.OutputBean_F;
import Bean.OutputBean_FOM;
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.BuildingController;
import Control.ModelController;
import Control.RoomController;
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

public class CreazioneStanzaDalModelloController implements Initializable {

	@FXML
	private Label lblCreazioneStanzaDaModello;

	@FXML
	private Label lblViewFOM;
	@FXML
	private Label lblViewRooms;
	@FXML
	private Label lblViewModels;

	@FXML
	private Label lblSelect;


	@FXML
	private Label lblInsertName;

	@FXML
	private TextField txtInsertName;


	ObservableList<OutputBean_FOM> data_FOM = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_FOM>> data_FOM2 = FXCollections.observableArrayList();
	
	ObservableList<OutputBean_F> dataModels = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_F>> dataModels2 = FXCollections.observableArrayList();
	
	ObservableList<OutputBean> data = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean>> data2 = FXCollections.observableArrayList();
	
	ObservableList<OutputBean_B> data_E = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_B>> data_E2 = FXCollections.observableArrayList();



	@FXML
	private TableView<OutputBean_FOM> table_FOM = new TableView<>();

	@FXML
	private TableColumn<OutputBean_FOM,String> table_FOMColumn1;
	@FXML
	private TableColumn<OutputBean_FOM,String> table_FOMColumn2;
	@FXML
	private TableColumn<OutputBean_FOM,Integer> table_FOMColumn3;
	@FXML
	private TableColumn<OutputBean_FOM,Integer> table_FOMColumn4;



	@FXML
	private TableView<OutputBean> table = new TableView<>();

	@FXML
	private TableColumn<OutputBean,String> tableColumn1;
	@FXML
	private TableColumn<OutputBean,String> tableColumn2;
	@FXML
	private TableColumn<OutputBean,String> tableColumn3;
	@FXML
	private TableColumn<OutputBean,String> tableColumn4;
	@FXML
	private TableColumn<OutputBean,Integer> tableColumn5;


	@FXML
	private TableView<OutputBean_B> table_E = new TableView<>();

	@FXML
	private TableColumn<OutputBean_B,String> table_EColumn1;
	@FXML
	private TableColumn<OutputBean_B,String> table_EColumn2;
	@FXML
	private TableColumn<OutputBean_B,Integer> table_EColumn3;



	public void initialize(URL url, ResourceBundle rb){

		table_FOMColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,String>("column1"));
		table_FOMColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,String>("column2"));
		table_FOMColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,Integer>("column3"));
		table_FOMColumn4.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,Integer>("column4"));

		tableColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column1"));
		tableColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column2"));
		tableColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column3"));
		tableColumn4.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column4"));
		tableColumn5.setCellValueFactory(new PropertyValueFactory<OutputBean,Integer>("column5"));

		table_EColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_B,String>("column1"));
		table_EColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_B,String>("column2"));
		table_EColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_B,Integer>("column3"));
	}



	public void back(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("GestioneStanze.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}
	
	public void fine(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}




	public void printBuildingsOnTable_E(ActionEvent event) throws Exception{

		data_E.clear();
		BuildingController bc = BuildingController.getInstance();
		bc.printBuildingsOnTableView(data_E,data_E2);
		for (int count = 0; count < data_E2.size(); count ++) {
			table_E.setItems(data_E2.get(count));
		}

	}



	public void printFOMOnTable_FOM(ActionEvent event) throws Exception{

		data_FOM.clear();
		ModelBean modelbean = SingletonBean.getSingletonInstance().getModelBean();
		ModelController mc = ModelController.getInstance();
		mc.printFeaturesOfModelOnTable(modelbean,data_FOM,data_FOM2);
		for (int count = 0; count < data_FOM2.size(); count ++) {
			table_FOM.setItems(data_FOM2.get(count));
		}


	}



	public void printFOMOnTable_FOM() throws Exception{

		data_FOM.clear();
		ModelBean modelbean = SingletonBean.getSingletonInstance().getModelBean();
		ModelController mc = ModelController.getInstance();
		mc.printFeaturesOfModelOnTable(modelbean,data_FOM,data_FOM2);
		for (int count = 0; count < data_FOM2.size(); count ++) {
			table_FOM.setItems(data_FOM2.get(count));
		}

	}


	public void createRoomFromModel(ActionEvent event) throws Exception{

		RoomController rc = RoomController.getInstance();
		OutputBean_B outputbean_E = table_E.getSelectionModel().getSelectedItem();
		RoomBean roombean = new RoomBean();
		ModelBean modelbean = SingletonBean.getSingletonInstance().getModelBean();

		roombean.setName(txtInsertName.getText());
		roombean.setNomeEdificio(outputbean_E.getColumn1());
		roombean.setMacroarea(outputbean_E.getColumn2());

		rc.createRoomFromModel(modelbean,roombean);
		printRoomsOnTable();
		txtInsertName.setText("");


	}


	public void printRoomsOnTable(ActionEvent event) throws Exception{

		data.clear();
		RoomController rc = RoomController.getInstance();
		rc.printRoomsOnTableView(data,data2);
		for (int count = 0; count<= data2.size()-1; count ++) {
			table.setItems(data2.get(count));
		}

	}

	public void printRoomsOnTable() throws Exception{

		data.clear();
		RoomController rc = RoomController.getInstance();
		rc.printRoomsOnTableView(data,data2);
		for (int count = 0; count<= data2.size()-1; count ++) {
			table.setItems(data2.get(count));
		}

	}







}