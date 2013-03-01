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
	
	
	public static Button startButton = new Button("Start");
	
	public static Button getStartButton() {
		return startButton;
	}

	public static Button resetButton = new Button("Reset");

	public static Button getResetButton() {
		return resetButton;
	}


	public static Button backButton = new Button("Back");

	public static Button getBackButton() {
		return backButton;
	}

	
	public InfoPane() {

		BorderPane root = new BorderPane();
		scene = new Scene(root);

		root.setCenter(Menu());
	}

	private HBox Menu() {
		HBox pane = new HBox();
		pane.setPadding(new Insets(10,10,10,10));
		pane.setSpacing(500/1.4);
		pane.setAlignment(Pos.CENTER);
	
		
		pane.getChildren().addAll(startButton, resetButton, backButton);
		
		return pane;
	}

}
