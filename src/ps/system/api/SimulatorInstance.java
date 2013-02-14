package ps.system.api;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.scene.Scene;

public class SimulatorInstance {


	public Object[][] data_shared_read;
	public Object[][] data_shared_write_independant;
	public Object[][] data_shared_write_dependant;
	
	
	//Scene in which the simulation will take place
	protected Scene scene;

	public Scene getScene() {
		return scene;
	}
	
	//Dynamic list of shared data bindings (PhysSim API)
	protected ArrayList<Object> sharedData = new ArrayList<Object>();
	
	public SimulatorInstance() {
		
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


	
	protected void addSharedData(Object[] data) {
		for (int i = 0; i < data.length; i++) {
			sharedData.add(data[i]);
		}
	}

}
