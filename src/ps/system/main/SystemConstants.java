package ps.system.main;

import java.util.HashMap;

import ps.system.api.SimulatorInstance;
import ps.system.frames.TrackWindow;

public interface SystemConstants {

	//Main Window Title
	String TITLE = "Physics Sim v0.1 pre-alpha";

	//Main Window Size
	int mainWindow_DEFAULTW = 1500;
	int mainWindow_DEFAULTH = 1000;	
	
	//Main Pane Setup
	int topPanes_MINW = 500;
	int topPanes_MINH = 500;
	int bottomPane_MINW = 100;
	int bottomPane_MINH = 50;
	
	//Pane resize weights
	double JFXPanes_RESIZEWEIGHT = 0.5;
	double MainSplitPanes_RESIZEWEIGHT = 0.99;
	
	boolean JFXPanes_CONTINUOUSLAYOUT = true;
	boolean MainSplitPanes_CONTINUOUSLAYOUT = true;
										
}
