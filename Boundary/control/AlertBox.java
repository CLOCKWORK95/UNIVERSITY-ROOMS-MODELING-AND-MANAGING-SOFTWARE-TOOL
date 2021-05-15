package Boundary.control;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	public static void display(String Title, String message) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(Title);
		window.setMinWidth(400);
		window.setMinHeight(250);
		Label label = new Label();
		label.setText(message);
		
		Button closebtn = new Button("Got it, return to execution!");
		closebtn.setOnAction(e->window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,closebtn);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		
		window.show();
		
	}

}
