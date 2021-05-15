package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.OutputBean;
import Bean.OutputBean_B;
import Bean.RoomBean;
import Control.BuildingController;
import Control.RoomController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CreazioneStanzeController implements Initializable {

	@FXML
	private Label lblCreazioneStanza;
	@FXML
	private Label lblClickToView;
	@FXML
	private Label lblSelect;

	@FXML
	private MenuButton menuBtnRoomGroup;
	@FXML
	private MenuItem miClassroom;
	@FXML
	private MenuItem miLaboratory;
	@FXML
	private MenuItem miConferenceRoom;


	@FXML
	private Label lblInsertName;
	@FXML
	private Label lblRoomGroup;
	@FXML
	private Label lblInsertNomeEdificio;
	@FXML
	private Label lblInsertMacroArea;


	@FXML
	private TextField txtInsertName;
	@FXML
	private TextField txtInsertNomeEdificio;
	@FXML
	private TextField txtInsertMacroarea;


	ObservableList<OutputBean> data = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean>> data2 = FXCollections.observableArrayList();
	
	ObservableList<OutputBean_B> data_E = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_B>> data_E2 = FXCollections.observableArrayList();

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

		tableColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column1"));
		tableColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column2"));
		tableColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column3"));
		tableColumn4.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column4"));
		tableColumn5.setCellValueFactory(new PropertyValueFactory<OutputBean,Integer>("column5"));


		table_EColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_B,String>("column1"));
		table_EColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_B,String>("column2"));
		table_EColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_B,Integer>("column3"));
	}


	public void setLabelRoomGroupClassrom(ActionEvent event){
		lblRoomGroup.setText("Classroom");
	}

	public void setLabelRoomGroupLaboratory(ActionEvent event){
		lblRoomGroup.setText("Laboratory");
	}

	public void setLabelRoomGroupConference(ActionEvent event){
		lblRoomGroup.setText("ConferenceRoom");
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



	public void printRoomsOnTable(ActionEvent event) throws Exception{

		data.clear();
		RoomController rc = RoomController.getInstance();
		rc.printRoomsOnTableView(data,data2);
		for (int count = 0; count<= data2.size()-1; count ++) {
			table.setItems(data2.get(count));
		}

	}


	public void printBuildingsOnTable_E(ActionEvent event) throws Exception{

		data_E.clear();
		BuildingController bc = BuildingController.getInstance();
		bc.printBuildingsOnTableView(data_E,data_E2);
		for (int count = 0; count < data_E2.size(); count ++) {
			table_E.setItems(data_E2.get(count));
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


	public void creazioneStanza(ActionEvent event) throws Exception{

		OutputBean_B outputbean = table_E.getSelectionModel().getSelectedItem();
		RoomBean roombean = new RoomBean();
		roombean.setName(txtInsertName.getText());
		
		//Gestione Invalid Type RoomGroup
		try {
			roombean.setRoomGroup(lblRoomGroup.getText());
			if (!(roombean.getRoomGroup().equals("Classroom") || roombean.getRoomGroup().equals("ConferenceRoom")||
					roombean.getRoomGroup().equals("Laboratory"))){
				Exception e = new Exception("invalid type:  " + roombean.getRoomGroup() + "."
						+ "\nPossible RoomGroups are: Classroom, ConferenceRoom, Laboratory.  ");
				throw e;
			}
			
			roombean.setNomeEdificio(outputbean.getColumn1());
			roombean.setMacroarea(outputbean.getColumn2());
			RoomController rc = RoomController.getInstance();
			rc.createRoom(roombean);
			printRoomsOnTable();
			txtInsertName.setText("");
			lblRoomGroup.setText("RoomGroup");
			
		} catch (Exception e) {
			AlertBox.display("Warning", e.getMessage());
		}
		

	}





}