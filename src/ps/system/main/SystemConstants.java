package ps.system.main;

import java.util.LinkedHashMap;

import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

public interface SystemConstants {

	/*
	 * ----------------------WINDOW ELEMENT CONFIG----------------------
	 * 
	 */
	
	//Main Window Title
	String TITLE = "Physics Sim v0.2 pre-alpha";

	//Main Window Size
	int mainWindow_DEFAULTW = 1500;
	int mainWindow_DEFAULTH = 1000;	
	
	//Main Pane Setup
	int topPanes_MINW = 500;
	int topPanes_MINH = 500;
	int bottomPane_MINW = 100;
	int bottomPane_MINH = 50;
	
	//Pane resize weights
	double JFXPanes_RESIZEWEIGHT = 0.75;
	double MainSplitPanes_RESIZEWEIGHT = 0.6;
	
	boolean JFXPanes_CONTINUOUSLAYOUT = true;
	boolean MainSplitPanes_CONTINUOUSLAYOUT = true;
	
	//Engine constants
	String SIMFRAMEPATH = "ps.simulation.frames.";
	
	/*
	 * Swing handler constants
	 */
	
	//Pixels per standard unit measurement (Unit: meter)
	//100pixels = 1m
	int PIXELRATIO = 100;	
	

	/*
	 * ----------------------LinkedHashMaps----------------------
	 * 
	 */
	
	//DataExchange
	
	//->DataWrite JFX
	LinkedHashMap<String, Node> DATAWRITE_JFX_dependant = PhysicsWindow.sharedData.getDataWriteJFX_dependant();
	LinkedHashMap<String, Timeline> DATAWRITE_JFX_independant = PhysicsWindow.sharedData.getDataWriteJFX_independant(); 
	
	//->DataWrite Swing
	LinkedHashMap<String, SimpleDoubleProperty> DATAWRITE_Swing_dependant = PhysicsWindow.sharedData.getDataWriteSwing_dependant();
	LinkedHashMap<String, SimpleDoubleProperty> DATAWRITE_Swing_independant = PhysicsWindow.sharedData.getDataWriteSwing_independant(); 
	
	//->DataRead
	LinkedHashMap<String, Property> DATAREAD = PhysicsWindow.sharedData.getDataRead();
										
}
