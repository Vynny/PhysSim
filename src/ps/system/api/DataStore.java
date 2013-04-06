package ps.system.api;

import java.util.LinkedHashMap;

import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

public class DataStore {
	
	/*
	 * Data read by the simulation, accepts variables that the simulation can respond to.
	 */
	
	// (Example: initial velocity, gravity constant, angle theta, etc)
	private static LinkedHashMap<String, Property> dataRead = new LinkedHashMap<String, Property>();

	/*
	 * Data written by the simulation, for use in graphing and displaying.
	 */

	//JavaFX
	private static LinkedHashMap<String, Timeline> dataWriteJFX_independant = new LinkedHashMap<String, Timeline>();
	private static LinkedHashMap<String, Node> dataWriteJFX_dependant = new LinkedHashMap<String, Node>();
	
	//Swing
	private static LinkedHashMap<String, SimpleDoubleProperty> dataWriteSwing_independant = new LinkedHashMap<String, SimpleDoubleProperty>();
	private static LinkedHashMap<String, SimpleDoubleProperty> dataWriteSwing_dependant = new LinkedHashMap<String, SimpleDoubleProperty>();
	
	
	public DataStore() {

	}
	
	/*
	 * Read
	 */
	public LinkedHashMap<String, Property> getDataRead() {
		return dataRead;
	}

	// Add Read Data
	public void addReadData(Object[][] sharedData) {
		for (int i = 0; i < sharedData.length; i++) {
			dataRead.put((String) sharedData[i][0], (Property) sharedData[i][1]);
		}
	}

	// Read Data clear
	public void clearReadData(Object[][] sharedData) {
		dataRead.clear();
	}

	// Retrieve Data
	public Property getDataByKey(String key) {
		return dataRead.get(key);
	}
	

	/*
	 * Write 
	 */
	
	//JavaFX
	public void addWriteDataJFX(Object[][] sharedIndependantData, Object[][] sharedDependantData) {
		for (int i = 0; i < sharedIndependantData.length; i++) {
			dataWriteJFX_independant.put((String) sharedIndependantData[i][0], (Timeline) sharedIndependantData[i][1]);
		}
		
		for (int i = 0; i < sharedDependantData.length; i++) {
			dataWriteJFX_dependant.put((String) sharedDependantData[i][0], (Node) sharedDependantData[i][1]);
		}
	}

	public LinkedHashMap<String, Timeline> getDataWriteJFX_independant() {
		return dataWriteJFX_independant;
	}

	public LinkedHashMap<String, Node> getDataWriteJFX_dependant() {
		return dataWriteJFX_dependant;
	}

	// Swing
	public void addWriteDataSwing(Object[][] sharedIndependantData, Object[][] sharedDependantData) {
		for (int i = 0; i < sharedIndependantData.length; i++) {
			dataWriteSwing_independant.put((String) sharedIndependantData[i][0], (SimpleDoubleProperty) sharedIndependantData[i][1]);
		}

		for (int i = 0; i < sharedDependantData.length; i++) {
			dataWriteSwing_dependant.put((String) sharedDependantData[i][0], (SimpleDoubleProperty) sharedDependantData[i][1]);
		}
	}

	public LinkedHashMap<String, SimpleDoubleProperty> getDataWriteSwing_independant() {
		return dataWriteSwing_independant;
	}

	public LinkedHashMap<String, SimpleDoubleProperty> getDataWriteSwing_dependant() {
		return dataWriteSwing_dependant;
	}

	/*
	 * Clear
	 */
	
	//JavaFX
	public void clearWriteDataJFX(Object[][] sharedIndependantData, Object[][] sharedDependantData) {
		dataWriteJFX_independant.clear();
		dataWriteJFX_dependant.clear();
	}
	
	//Swing
	public void clearWriteDataSwing(Object[][] sharedIndependantData, Object[][] sharedDependantData) {
		dataWriteSwing_independant.clear();
		dataWriteSwing_dependant.clear();
	}


}
