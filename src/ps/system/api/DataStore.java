package ps.system.api;

import java.util.concurrent.ConcurrentHashMap;

import javafx.animation.Timeline;
import javafx.scene.Node;

public class DataStore {
	
	// Data read by the simulation, accepts variables that the simulation can respond to.
	// (Example: initial velocity, gravity constant, angle theta, etc)
	private ConcurrentHashMap<String, Number> dataRead = new ConcurrentHashMap<String, Number>();

	// Data written by the simulation, for use in graphing and displaying.
	// (Example: time)
	private ConcurrentHashMap<String, Timeline> dataWrite_independant = new ConcurrentHashMap<String, Timeline>();
	// (Example: property bindings)
	private ConcurrentHashMap<String, Node> dataWrite_dependant = new ConcurrentHashMap<String, Node>();
	
	public DataStore() {

	}
	
	//Read
	public ConcurrentHashMap<String, Number> getDataRead() {
		return dataRead;
	}

	public void addReadData(Object[][] sharedData) {
		for (int i = 0; i < sharedData.length; i++) {
			dataRead.put((String) sharedData[i][0], (Number) sharedData[i][1]);
		}
	}
	

	//Write 
	public void addWriteData(Object[][] sharedIndependantData, Object[][] sharedDependantData) {
		for (int i = 0; i < sharedIndependantData.length; i++) {
			dataWrite_independant.put((String) sharedIndependantData[i][0], (Timeline) sharedIndependantData[i][1]);
		}
		
		for (int i = 0; i < sharedDependantData.length; i++) {
			dataWrite_dependant.put((String) sharedDependantData[i][0], (Node) sharedDependantData[i][1]);
		}
	}

	public ConcurrentHashMap<String, Timeline> getDataWrite_independant() {
		return dataWrite_independant;
	}

	public ConcurrentHashMap<String, Node> getDataWrite_dependant() {
		return dataWrite_dependant;
	}

	//Retrieve Data
	public Number getDataByKey(String key) {
		return dataRead.get(key);
	}

}
