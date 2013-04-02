package ps.system.api;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ps.system.frames.JFXPanes;
import ps.system.main.PhysicsWindow;


public class InfoPane {
	
	
	private SimulatorInstanceSwing swingInstance;
	private Scene scene;

	public Scene getScene() {
		return scene;
	}
	
	
	private static Button startButton = new Button("Start");
	
	public Button getStartButton() {
		return startButton;
	}

	private static Button resetButton = new Button("Reset");

	public Button getResetButton() {
		return resetButton;
	}


	private static Button backButton = new Button("Back");

	public Button getBackButton() {
		return backButton;
	}

	
	public InfoPane() {

		BorderPane root = new BorderPane();
		scene = new Scene(root);

		root.setCenter(Menu());
	}
	
	public InfoPane(SimulatorInstanceSwing swingInstance) {
		BorderPane root = new BorderPane();
		scene = new Scene(root);
		
		this.swingInstance = swingInstance;
		initalizeSwingButtonHandlers();

		root.setCenter(Menu());
	}
	
	private void initalizeSwingButtonHandlers() {
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.start();
			}
		});

		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.stop();
				JFXPanes.getJFXChart().clearData();
			}

		});
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.stop();
				
				PhysicsWindow.JFXPanes.simulationID.setSimulationID(" ");
				PhysicsWindow.changeWindow("Menu");
			}
			
		});
	}
	
	private void initalizeJFXButtonHandlers() {
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.start();
			}
		});

		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.stop();
				JFXPanes.getJFXChart().clearData();
			}

		});
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.stop();
				
				PhysicsWindow.JFXPanes.simulationID.setSimulationID(" ");
				PhysicsWindow.changeWindow("Menu");
			}
			
		});
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
