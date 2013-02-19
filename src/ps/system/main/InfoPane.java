package ps.system.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class InfoPane {
	
	private Scene scene;

	public Scene getScene() {
		return scene;
	}
	
	public InfoPane() {

		BorderPane root = new BorderPane();
		scene = new Scene(root);

		root.setCenter(Menu());
	}

	private HBox Menu() {
		HBox pane = new HBox();
		//setStyle("-fx-background-color: #336699");
		pane.setPadding(new Insets(10,10,10,10));
		pane.setSpacing(500/1.4);
		pane.setAlignment(Pos.CENTER);
		
		Button startButton = new Button("Start");
		
		//startButton.setOnAction();
		
		Button resetButton = new Button("Reset");
		//resetButton.setOnAction();
		
		Button backButton = new Button("Back");
		//backButton.setOnAction();
		
		pane.getChildren().addAll(startButton, resetButton, backButton);
		
		return pane;
	}

}
