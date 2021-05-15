package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.ModelBean;
import Bean.OutputBean_F;
import Bean.SingletonBean;
import Control.ModelController;
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

public class CreazioneModelloController implements Initializable {

	@FXML
	private Label lblCreazioneModello;

	@FXML
	private Label lblName;
	@FXML
	private TextField txtName;

	@FXML
	private MenuButton menuButtonRoomGroup;
	@FXML
	private MenuItem miClassroom;
	@FXML
	private MenuItem miLaboratory;
	@FXML
	private MenuItem miConferenceRoom;

	@FXML
	private Label lblRoomGroup;


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

		Parent gestioneModelli_parent = FXMLLoader.load(getClass().getResource("GestioneModelli.fxml"));
		Scene gestioneModelli_scene = new Scene(gestioneModelli_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneModelli_scene);
		app_stage.show();
	}

	public void next(ActionEvent event) throws Exception{
		Parent creazioneModello_parent = FXMLLoader.load(getClass().getResource("CreazioneModello_Inserimento.fxml"));
		Scene creazioneModello_scene = new Scene(creazioneModello_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(creazioneModello_scene);
		app_stage.show();
	}


	public void printModelsOnTable_F(ActionEvent event) throws Exception{

		data_F.clear();
		ModelController mc = ModelController.getInstance();
		mc.printModelsOnTable_F(data_F,data_F2);
		for (int count = 0; count < data_F2.size(); count ++) {
			table_F.setItems(data_F2.get(count));
		}
	}


	public void printModelsOnTable_F() throws Exception{
		
		data_F.clear();
		ModelController mc = ModelController.getInstance();
		mc.printModelsOnTable_F(data_F,data_F2);
		for (int count = 0; count < data_F2.size(); count ++) {
			table_F.setItems(data_F2.get(count));
		}
	}




	public void creazioneModelloVuoto(ActionEvent event) throws Exception{

		ModelBean mb = new ModelBean();
		mb.setNomeModello(txtName.getText());
		
		//Gestione Invalid Type RoomGroup
		try {
			mb.setRoomGroup(lblRoomGroup.getText());
			if (!(mb.getRoomGroup().equals("Classroom") || mb.getRoomGroup().equals("ConferenceRoom")||
					mb.getRoomGroup().equals("Laboratory"))){
				Exception e = new Exception("invalid type:  " + mb.getRoomGroup() + "."
						+ "\nPossible RoomGroups are: Classroom, ConferenceRoom, Laboratory.  ");
				throw e;
			}
			ModelController mc = ModelController.getInstance();
			mc.createModel(mb);
			SingletonBean.getSingletonInstance().setModelBean(mb);
			printModelsOnTable_F();
			txtName.setText("");
			lblRoomGroup.setText("RoomGroup");
			
		} catch (Exception e) {
			AlertBox.display("Warning", e.getMessage());
		}		
				
		
		
	}




}