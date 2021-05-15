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

public class ModificaEdificioController implements Initializable {

    @FXML
    private Label lblModificaEdificio;

    @FXML
    private Label lblNewName;
    @FXML
    private Label lblNewMacroarea;

    @FXML
    private Label lblSelect;

    @FXML
    private TextField txtNewName;
    @FXML
    private TextField txtnewMacroarea;

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

	
    public void ModificaEdificioNome(ActionEvent event) throws Exception{

    	BuildingController bc = BuildingController.getInstance();
    	OutputBean_B outputbean = table_E.getSelectionModel().getSelectedItem();
    	BuildingBean buildingbean = new BuildingBean();

        buildingbean.setNomeEdificio(outputbean.getColumn1());
        buildingbean.setMacroarea(outputbean.getColumn2());
        buildingbean.setNewNomeEdificio(txtNewName.getText());
        bc.buildingModifyName(buildingbean);
        printBuildingsOnTable_E();
        txtNewName.setText("");

    }

    public String ModificaEdificioNome() throws Exception{

    	BuildingController bc = BuildingController.getInstance();
    	OutputBean_B outputbean = table_E.getSelectionModel().getSelectedItem();
    	BuildingBean buildingbean = new BuildingBean();

        buildingbean.setNomeEdificio(outputbean.getColumn1());
        buildingbean.setMacroarea(outputbean.getColumn2());
        buildingbean.setNewNomeEdificio(txtNewName.getText());
        bc.buildingModifyName(buildingbean);

        return buildingbean.getNewNomeEdificio();
    }

    public void ModificaEdificioMacroarea(ActionEvent event) throws Exception{

    	BuildingController bc = BuildingController.getInstance();
    	OutputBean_B outputbean = table_E.getSelectionModel().getSelectedItem();
    	BuildingBean buildingbean = new BuildingBean();

        buildingbean.setNomeEdificio(outputbean.getColumn1());
        buildingbean.setMacroarea(outputbean.getColumn2());
        buildingbean.setNewMacroarea(txtnewMacroarea.getText());
        bc.buildingModifyMacroarea(buildingbean);
        printBuildingsOnTable_E();
        txtnewMacroarea.setText("");
    }

    public void ModificaEdificioMacroarea(String newName) throws Exception{

    	BuildingController bc = BuildingController.getInstance();
    	OutputBean_B outputbean = table_E.getSelectionModel().getSelectedItem();
    	BuildingBean buildingbean = new BuildingBean();

        buildingbean.setNomeEdificio(newName);
        buildingbean.setMacroarea(outputbean.getColumn2());
        buildingbean.setNewMacroarea(txtnewMacroarea.getText());
        bc.buildingModifyMacroarea(buildingbean);

    }

    public void ApplyBoth(ActionEvent event) throws Exception{
    	String newName = ModificaEdificioNome();
    	ModificaEdificioMacroarea(newName);
    	printBuildingsOnTable_E();

        txtNewName.setText("");
        txtnewMacroarea.setText("");

    }



}