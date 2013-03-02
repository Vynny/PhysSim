package ps.system.api;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import ps.system.main.InfoPane;
import ps.system.main.JFXPanes;
import ps.system.main.PhysicsWindow;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.util.Duration;

public abstract class SimulatorInstance {

	//Hashmaps
	public Object[][] data_shared_read;
	public Object[][] data_shared_write_independant;
	public Object[][] data_shared_write_dependant;
	
	//Buttons
	private static InfoPane test = new InfoPane();
	public static Button startButton = test.getStartButton();
	public static Button resetButton = test.getResetButton();
	public static Button backButton = test.getBackButton();
	
	
	//Scene in which the simulation will take place
	public Scene scene;

	public Scene getScene() {
		return scene;
	}
	
	//Constructor
	public SimulatorInstance() {
		
	}
	
	//Standard Button Handlers
	private void InitializeButtonHandlers() {
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
			}
		});

		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			}

		});
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("FUNCTIONALITY DISABLED");
			}
			
		});
	}
	
	public Object[][] getSharedReadData() {
		return this.data_shared_read;
	}
	
	public Object[][] getIndependantSharedWriteData() {
		return this.data_shared_write_independant;
	}
	
	public Object[][] getDependantSharedWriteData() {
		return this.data_shared_write_dependant;
	}
	
	public void LoadData() {
		//Bind buttons to infopane
		InitializeButtonHandlers();
		
		//Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);
		
		//Data Written by sim
		PhysicsWindow.sharedData.addWriteData(data_shared_write_independant, data_shared_write_dependant);
	}

}
