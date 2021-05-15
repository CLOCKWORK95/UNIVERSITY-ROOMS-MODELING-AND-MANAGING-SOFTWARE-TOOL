package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.OutputBean;
import Bean.RoomBean;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class EliminazioneStanzeController implements Initializable {

	@FXML
	private Label lblEliminazioneStanza;



	ObservableList<OutputBean> data = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean>> data2 = FXCollections.observableArrayList();

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



	public void initialize(URL url, ResourceBundle rb){

		tableColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column1"));
		tableColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column2"));
		tableColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column3"));
		tableColumn4.setCellValueFactory(new PropertyValueFactory<OutputBean,String>("column4"));
		tableColumn5.setCellValueFactory(new PropertyValueFactory<OutputBean,Integer>("column5"));

	}

	public void back(ActionEvent event) throws Exception{

		Parent secretaryMainPage_parent = FXMLLoader.load(getClass().getResource("GestioneStanze.fxml"));
		Scene secretaryMainPage_scene = new Scene(secretaryMainPage_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(secretaryMainPage_scene);
		app_stage.show();
	}


	public void fine(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("SecretaryMainPage.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}
	
	public void eliminaStanza(ActionEvent event) throws Exception{

		RoomController rc = RoomController.getInstance();
		RoomBean roombean = new RoomBean();
		OutputBean outputbean = table.getSelectionModel().getSelectedItem();
		roombean.setName(outputbean.getColumn1());
		roombean.setRoomGroup(outputbean.getColumn2());
		roombean.setNomeEdificio(outputbean.getColumn3());
		roombean.setMacroarea(outputbean.getColumn4());
		roombean.setRoomCode(outputbean.getColumn5());

		rc.deleteRoom(rc.getRoomCode(roombean));

		printRoomsOnTable();

	}



	public void deleteAll(ActionEvent event) throws Exception{
		RoomBean roombean = new RoomBean();
		RoomController rc = RoomController.getInstance();
		rc.deleteAll(roombean);
		printRoomsOnTable();
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
