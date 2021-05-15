package Boundary.control;

import java.net.URL;
import java.util.ResourceBundle;

import Bean.FeatureBean;
import Bean.ModelBean;
import Bean.OutputBean_F;
import Bean.OutputBean_FOM;
import Bean.SingletonBean;
import Control.FeatureController;
import Control.ModelController;
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

public class CreazioneModello_InserimentoController implements Initializable {

	@FXML
	private Label lblModificaStanza;

	@FXML
	private Label lblName;
	@FXML
	private Label lblRoomgroup;



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


	ObservableList<OutputBean_FOM> data_FOM = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_FOM>> data_FOM2 = FXCollections.observableArrayList();
	
	ObservableList<OutputBean_F> data_F = FXCollections.observableArrayList();
	ObservableList<ObservableList<OutputBean_F>> data_F2 = FXCollections.observableArrayList();


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
	private TableView<OutputBean_F> table_F = new TableView<>();

	@FXML
	private TableColumn<OutputBean_F,String> table_FColumn1;
	@FXML
	private TableColumn<OutputBean_F,String> table_FColumn2;
	@FXML
	private TableColumn<OutputBean_F,Integer> table_FColumn3;





	public void initialize(URL url, ResourceBundle rb){

		lblName.setText(SingletonBean.getSingletonInstance().getModelBean().getName());
		lblRoomgroup.setText(SingletonBean.getSingletonInstance().getModelBean().getRoomGroup());


		table_FOMColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,String>("column1"));
		table_FOMColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,String>("column2"));
		table_FOMColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,Integer>("column3"));
		table_FOMColumn4.setCellValueFactory(new PropertyValueFactory<OutputBean_FOM,Integer>("column4"));

		table_FColumn1.setCellValueFactory(new PropertyValueFactory<OutputBean_F,String>("column1"));
		table_FColumn2.setCellValueFactory(new PropertyValueFactory<OutputBean_F,String>("column2"));
		table_FColumn3.setCellValueFactory(new PropertyValueFactory<OutputBean_F,Integer>("column3"));


	}



	public void back(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("CreazioneModello.fxml"));
		Scene gestioneStanze_scene = new Scene(gestioneStanze_parent);
		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		app_stage.setScene(gestioneStanze_scene);
		app_stage.show();
	}
	
	
	public void creaStanzaDaModello(ActionEvent event) throws Exception{

		Parent gestioneStanze_parent = FXMLLoader.load(getClass().getResource("CreazioneStanzaDalModello.fxml"));
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

	



	public void printFeaturesOnTable_F(ActionEvent event) throws Exception{

		data_F.clear();
		FeatureController fc = FeatureController.getInstance();
		fc.printFeaturesOnTable(data_F,data_F2);
		for (int count = 0; count<= data_F2.size()-1; count ++) {
			table_F.setItems(data_F2.get(count));
		}


	}


	public void addFeaturetoModel(ActionEvent event) throws Exception{

		ModelBean modelbean = SingletonBean.getSingletonInstance().getModelBean();
		ModelController mc = ModelController.getInstance();
		FeatureBean featurebean = new FeatureBean();
		OutputBean_F outputbean_f = table_F.getSelectionModel().getSelectedItem();

		featurebean.setCodiceFeature(outputbean_f.getColumn3());
		featurebean.setNumberOfInstances(textToInt(txtInsertCREATEnumberOfInst));

		mc.insertFeatureIntoModel(modelbean,featurebean);
		txtInsertCREATEnumberOfInst.setText("");
		printFOMOnTable_FOM();

	}




	public void removeFeatureFromModel(ActionEvent event) throws Exception{

		ModelBean modelbean = SingletonBean.getSingletonInstance().getModelBean();
		ModelController rc = ModelController.getInstance();
		FeatureBean featurebean = new FeatureBean();
		OutputBean_F outputbean_f = table_F.getSelectionModel().getSelectedItem();

		featurebean.setCodiceFeature(outputbean_f.getColumn3());
		featurebean.setNumberOfInstances(textToInt(txtInsertDELETEnumberOfInst));

		rc.removeFeatureFromModel(modelbean,featurebean);
		txtInsertDELETEnumberOfInst.setText("");
		printFOMOnTable_FOM();
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




	public int textToInt(TextField input){
		int numberOfinstances = Integer.parseInt(input.getText());
		return numberOfinstances;
	}


}