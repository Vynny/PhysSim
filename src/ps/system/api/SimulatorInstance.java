package ps.system.api;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import ps.system.main.InfoPane;
import ps.system.main.PhysicsWindow;
import ps.system.main.SystemConstants;

public abstract class SimulatorInstance implements SystemConstants { 

	//Data to load DataStore hashmaps with
	public Object[][] data_shared_read;
	public Object[][] data_shared_write_independant;
	public Object[][] data_shared_write_dependant;
	
	//Buttons that will appear in the infopane section
	private static InfoPane infoPane_local = new InfoPane();
	public static Button startButton = infoPane_local.getStartButton();
	public static Button resetButton = infoPane_local.getResetButton();
	public static Button backButton = infoPane_local.getBackButton();
	
	
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
				PhysicsWindow.JFXPanes.simulationID.setSimulationID(" ");
				PhysicsWindow.changeWindow("Menu");
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
		PhysicsWindow.sharedData.addWriteDataJFX(data_shared_write_independant, data_shared_write_dependant);
	}
	
	public void UnLoadData() {
		//Bind buttons to infopane
		//InitializeButtonHandlers();
		
		//Data Read by sim
		PhysicsWindow.sharedData.clearReadData(data_shared_read);
		
		//Data Written by sim
		PhysicsWindow.sharedData.clearWriteDataJFX(data_shared_write_independant, data_shared_write_dependant);
	}

}
