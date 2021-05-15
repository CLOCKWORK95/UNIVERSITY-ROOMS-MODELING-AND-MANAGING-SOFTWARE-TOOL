package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.FeatureBean;
import Bean.OutputBean;
import Bean.OutputBean_F;
import Bean.OutputBean_FOR;
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.FeatureController;
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

public class ModificaStanzeController implements Initializable {

	@FXML
	private Label lblModificaStanza;

	@FXML
	private Label lblViewfeatures;
	@FXML
	private Label lblViewRoom;
	@FXML
	private Label lblViewRooms;

	@FXML
	private Label lblInsertCREATEnumberOfInst;
	@FXML
	private Label lblInsertDELETEnumberOfInst;

	@FXML
	private TextField txtInsertCREATEnumberOfInst;
	@FXML
	private TextField txtInsertDELETEnumberOfInst;


	ObservableList<OutputBean> data = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean>> data2 = FXCollections.observableArrayList();
	
	ObservableList<OutputBean_FOR> data_FOR = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_FOR>> data_FOR2 = FXCollections.observableArrayList();
	
	ObservableList<OutputBean_F> data_F = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_F>> data_F2 = FXCollections.observableArrayList();


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
	private TableView<OutputBean_FOR> table_FOR = new TableView<>();

	@FXML
	private TableColumn<OutputBean_FOR,String> table_FORColumn1;
	@FXML
	private TableColumn<OutputBean_FOR,String> table_FORColumn2;
	@FXML
	private TableColumn<OutputBean_FOR,Integer> table_FORColumn3;
	@FXML
	private TableColumn<OutputBean_FOR,Integer> table_FORColumn4;
	@FXML
	private TableColumn<OutputBean_FOR,Integer> table_FORColumn5;


	@FXML
	private TableView<OutputBean_F> table_F = new TableView<>();

	@FXML
	private TableColumn<OutputBean_F,String> table_FColumn1;
	@FXML
	private TableColumn<OutputBean_F,String> table_FColumn2;
	@FXML
	private TableColumn<OutputBean_F,Integer> table_FColumn3;



	public void initialize(URL url, ResourceBundle rb){

		tableColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column1"));
		tableColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column2"));
		tableColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column3"));
		tableColumn4.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column4"));
		tableColumn5.setCellValueFactory(new PropertyValueFactory<OutputBean,Integer>("column5"));

		table_FORColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_FOR,String>("column1"));
		table_FORColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_FOR,String>("column2"));
		table_FORColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_FOR,Integer>("column3"));
		table_FORColumn4.setCellValueFactory(new PropertyValueFactory<OutputBean_FOR,Integer>("column4"));
		table_FORColumn5.setCellValueFactory(new PropertyValueFactory<OutputBean_FOR,Integer>("column5"));

		table_FColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_F,String>("column1"));
		table_FColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_F,String>("column2"));
		table_FColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_F,Integer>("column3"));

	}



	public void back(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("GestioneStanze.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}
	
	public void next(ActionEvent event) throws Exception{

		Parent tracciareStato_Stanza_parent = FXMLLoader.load(getClass().getResource("tracciareStato_Stanza.fxml"));
		Scene tracciareStato_Stanza_scene = new Scene(tracciareStato_Stanza_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(tracciareStato_Stanza_scene);
		app_stage.show();
	}




	public void printRoomsOnTable(ActionEvent event) throws Exception{

		data.clear();
		RoomController rc = RoomController.getInstance();
		rc.printRoomsOnTableView(data,data2);
		for (int count = 0; count<= data2.size()-1; count ++) {
			table.setItems(data2.get(count));
		}

	}
	
	public void fine(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}



	public void printFeaturesOnTable_F(ActionEvent event) throws Exception{

		data_F.clear();
		FeatureController fc = FeatureController.getInstance();
		fc.printFeaturesOnTable(data_F,data_F2);
		for (int count = 0; count<= data_F2.size()-1; count ++) {
			table_F.setItems(data_F2.get(count));
		}
	}


	public void addFeaturetoRoom(ActionEvent event) throws Exception{

		
		// Gestione invalid type RoomGroup
		try {
			RoomBean roombean = SingletonBean.getSingletonInstance().getRoomBean();
			RoomController rc =  RoomController.getInstance();
			FeatureBean featurebean = new FeatureBean();
			OutputBean_F outputbean_f = table_F.getSelectionModel().getSelectedItem();

			featurebean.setCodiceFeature(outputbean_f.getColumn3());
			featurebean.setNumberOfInstances(textToInt(txtInsertCREATEnumberOfInst));
			rc.insertFeatureIntoRoom(roombean,featurebean);
			txtInsertCREATEnumberOfInst.setText("");
			printFOROnTable_FOR();
			
		} catch (Exception e) {
			AlertBox.display("Warning", e.getMessage());
		}

	}


	public void removeFeatureFromRoom(ActionEvent event) throws Exception{

		// Gestione invalid type RoomGroup
		try {
			RoomBean roombean = SingletonBean.getSingletonInstance().getRoomBean();
			RoomController rc = RoomController.getInstance();
			FeatureBean featurebean = new FeatureBean();
			OutputBean_F outputbean_f = table_F.getSelectionModel().getSelectedItem();

			featurebean.setCodiceFeature(outputbean_f.getColumn3());
			featurebean.setNumberOfInstances(textToInt(txtInsertDELETEnumberOfInst));

			rc.removeFeatureFromRoom(roombean,featurebean);
			txtInsertDELETEnumberOfInst.setText("");
			printFOROnTable_FOR();
		} catch (Exception e) {
			AlertBox.display("Warning", e.getMessage());
		}
	}


	public void printFOROnTable_FOR(ActionEvent event) throws Exception{

		data_FOR.clear();
		RoomBean roombean = new RoomBean();
		RoomController rc = RoomController.getInstance();
		OutputBean outputbean = table.getSelectionModel().getSelectedItem();
		roombean.setName(outputbean.getColumn1());
		roombean.setRoomGroup(outputbean.getColumn2());
		roombean.setNomeEdificio(outputbean.getColumn3());
		roombean.setMacroarea(outputbean.getColumn4());
		roombean.setRoomCode(outputbean.getColumn5());
		SingletonBean.getSingletonInstance().setRoomBean(roombean);
		rc.printFeaturesOfRoomOnTable(roombean,data_FOR,data_FOR2);
		for (int count = 0; count<= data_FOR2.size()-1; count ++) {
			table_FOR.setItems(data_FOR2.get(count));
		}

	}


	public void printFOROnTable_FOR() throws Exception{

		data_FOR.clear();
		RoomBean roombean = new RoomBean();
		RoomController rc = RoomController.getInstance();
		OutputBean outputbean = table.getSelectionModel().getSelectedItem();
		roombean.setName(outputbean.getColumn1());
		roombean.setRoomGroup(outputbean.getColumn2());
		roombean.setNomeEdificio(outputbean.getColumn3());
		roombean.setMacroarea(outputbean.getColumn4());
		roombean.setRoomCode(outputbean.getColumn5());
		
		rc.printFeaturesOfRoomOnTable(roombean,data_FOR,data_FOR2);
		for (int count = 0; count<= data_FOR2.size()-1; count ++) {
			table_FOR.setItems(data_FOR2.get(count));
		}

	}


	public int textToInt(TextField input){
		
		int numberOfinstances = 0;
		
		//Gestione formato input
		try {
			numberOfinstances = Integer.parseInt(input.getText());
			
		} catch (NumberFormatException e) {
			AlertBox.display("Warning", "You have to insert an Integer to add or remove features!");
		}
		return numberOfinstances;
	}


}