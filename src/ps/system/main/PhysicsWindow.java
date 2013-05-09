package ps.system.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ps.system.api.DataStore;
import ps.system.api.SimulatorInstanceJFX;
import ps.system.frames.JFXPanes;
import ps.system.internationalization.Language;

public class PhysicsWindow extends JFrame implements SystemConstants {
	
	
	private static JFrame mainFrame;
	
	//Enables sharing between JavaFX and Swing components of the
	//application. 
	public static DataStore sharedData;
	public static Language SystemLanguage;
	
	public static JFXPanes JFXPanes; 
	public SimulatorInstanceJFX simulationDisplay;
	
	//Split Panes
	private static JSplitPane topPanes;
	private static JSplitPane windowPanes;
	
	//CardLayout
	private static CardLayout windowCards;
	private static JPanel mainPanel;
	
	public PhysicsWindow() {

		//Language Setup
		SystemLanguage = new Language();
		//SystemLanguage.loadLocale(Locale.FRENCH);

		//Initialize global data storage hash map container
		sharedData = new DataStore();
		
		//Create JFX pane container object
		//Contains: JSplitPane of two javaFX scenes (R: Simulation, L: Graph)
		JFXPanes = new JFXPanes();
		
		// Create top simulation pane and chart pant
		topPanes = JFXPanes.getSeperatedPanes();
		// Create bottom info pane(contains buttons and configuration)
		JPanel bottomPane = JFXPanes.getBottomPane();

		// Create nested split container containing horizontally laid out jfxpanes setup
		// vertically with bottom pane
		windowPanes = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanes, bottomPane);
		windowPanes.setResizeWeight(MainSplitPanes_RESIZEWEIGHT);
		windowPanes.setContinuousLayout(MainSplitPanes_CONTINUOUSLAYOUT);

		// Set Minimum Size
		topPanes.setMinimumSize(new Dimension(topPanes_MINW, topPanes_MINH));
		bottomPane.setMinimumSize(new Dimension(bottomPane_MINW, bottomPane_MINH));
		
		//Configure CardLayout for holding menu & simulation panes
		windowCards = new CardLayout();
		mainPanel = new JPanel(windowCards);
		mainPanel.add(JFXPanes.getMenuPane(), "Menu");
		mainPanel.add(windowPanes, "Simulation");


		// Add main display pane to frame
		getContentPane().add(mainPanel);
	}

	public static int getTopSlidePosition() {
		return topPanes.getDividerLocation();
	}
	
	public static int getBottomSlidePosition() {
		return windowPanes.getDividerLocation();
	}
	
	public static void changeWindow(String windowID) {
		windowCards.show(mainPanel, windowID);
	}
	
	public static void InitializeGUI() {
		mainFrame = new PhysicsWindow();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setTitle(TITLE);
		mainFrame.pack();
		mainFrame.setSize(mainWindow_DEFAULTW, mainWindow_DEFAULTH);
		mainFrame.setVisible(true);
	}
	
}