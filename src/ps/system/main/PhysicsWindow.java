package ps.system.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ps.system.api.DataStore;
import ps.system.api.SimulatorInstance;

public class PhysicsWindow extends JFrame implements SystemConstants {
	
	//Enables sharing between JavaFX and Swing components of the
	//application. Referred to statically.
	public static DataStore sharedData;
	
	public SimulatorInstance simulationDisplay;
	public JFXPanes JFXPanes; 
	
	private PhysicsWindow() {

		//Initialize global data storage hash map container
		sharedData = new DataStore();
		
		//Create JFX pane container object
		//Contains: JSplitPane of two javaFX scenes (R: Simulation, L: Graph)
		JFXPanes = new JFXPanes("Mechanics_Track Window");
		JSplitPane topPanes = JFXPanes.getSeperatedPanes();

		// Create bottom info pane(contains buttons and configuration)
		JPanel bottomPane = JFXPanes.getBottomPane();

		// Create nested split container containing horizontally layed out jfxpanes setup
		// vertically with bottom pane
		JSplitPane windowPanes = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanes, bottomPane);
		
		windowPanes.setResizeWeight(MainSplitPanes_RESIZEWEIGHT);
		windowPanes.setContinuousLayout(MainSplitPanes_CONTINUOUSLAYOUT);

		// Set Minimum Size
		topPanes.setMinimumSize(new Dimension(topPanes_MINW, topPanes_MINH));
		bottomPane.setMinimumSize(new Dimension(bottomPane_MINW, bottomPane_MINH));

		// Add split pane to frame
		getContentPane().add(windowPanes);
		//getContentPane().add(JFXPanes.getMenuPane());
	}
	
	public static void InitializeGUI() {
		JFrame mainFrame = new PhysicsWindow();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.pack();
		mainFrame.setTitle(TITLE);
		mainFrame.setSize(mainWindow_DEFAULTW, mainWindow_DEFAULTH);
		mainFrame.setVisible(true);
	}
}