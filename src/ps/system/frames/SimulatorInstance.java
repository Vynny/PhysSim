package ps.system.frames;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;

public class SimulatorInstance {

	//Scene in which the simulation will take place
	protected Scene scene;

	public Scene getScene() {
		return scene;
	}
	
	//Dynamic list of shared data bindings (PhysSim API)
	protected ArrayList<Object> sharedData = new ArrayList<Object>();
	
	public SimulatorInstance() {
		
	}
	
	public ArrayList<Object> getSharedData() {
		return sharedData;
	}

	
	public void addSharedData(Object[] data) {
		for (int i = 0; i < data.length; i++) {
			sharedData.add(data[i]);
		}
	}

}
