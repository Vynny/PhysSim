package ps.system.main;

import java.awt.BorderLayout;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ps.system.api.ChartMaker;
import ps.system.api.MainMenu;
import ps.system.api.SimulatorInstance;

public class JFXPanes extends JPanel implements SystemConstants {

	//Pane separators
	private static JSplitPane seperatedPanes;
	private static JPanel bottomPane;
	//private static JPanel menuPane;
	
	//JavaFX components
	private SimulatorInstance JFXsimulation;
	private ChartMaker JFXchart;
	//private static MainMenu JFXmenu;
	public static InfoPane JFXinput;
	
	//Containers for swing and JavaFX integration
	private static JFXPanel JFXPanel_Simulation = new JFXPanel();
	private static JFXPanel JFXPanel_Graph = new JFXPanel();
	private static JFXPanel JFXPanel_Input = new JFXPanel();
	
	//private static JFXPanel JFXPanel_Menu = new JFXPanel();

	//Which simulation to display
	private String simulationID;

	public JFXPanes(String simulationID) {
		
		this.simulationID = simulationID;
		//Create menu panel
		//JPanel window_Menu = new JPanel(new BorderLayout());
		//window_Menu.add(JFXPanel_Menu, BorderLayout.CENTER);
		
		//Create panel dedicated to showing the simulation 
		JPanel window_Simulation = new JPanel(new BorderLayout());
		window_Simulation.add(JFXPanel_Simulation, BorderLayout.CENTER);
		
		//Create panel dedicated to showing graphs 
		JPanel window_Graph = new JPanel(new BorderLayout());
		window_Graph.add(JFXPanel_Graph, BorderLayout.CENTER);
		
		//Create panel dedicated to showing input and buttons
		bottomPane = new JPanel(new BorderLayout());
		bottomPane.add(JFXPanel_Input, BorderLayout.CENTER);
		
		//Add panels to horizontally separated panes
		seperatedPanes = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, window_Simulation, window_Graph);
		seperatedPanes.setResizeWeight(JFXPanes_RESIZEWEIGHT);
		seperatedPanes.setContinuousLayout(JFXPanes_CONTINUOUSLAYOUT);
		
		//Menu
		//menuPane = new JPanel(new BorderLayout());
		//menuPane.add(JFXPanel_Menu, BorderLayout.CENTER);
				
		//Initialize JavaFX Event Thread
		Platform.runLater(new Runnable() {		
			@Override
			public void run() {
				InitializeJFXSimulationWindows();
			}
		});
		
	}
	
	//Initialize JavaFX Objects
	private void InitializeJFXSimulationWindows() {
		SimulationList simList = new SimulationList();
	
		//Initialize Simulation Content
		JFXsimulation = SimulationList.simulationList.get(simulationID);
		JFXsimulation.LoadData();

		//Initialize Chart for content
		JFXchart = new ChartMaker();
		
		//Initialize InfoPane for content
		JFXPanes.JFXinput = new InfoPane();
		
		//JFXmenu = new MainMenu();

		initJFX_Module(JFXPanel_Simulation, ((SimulatorInstance) JFXsimulation).getScene());
		initJFX_Module(JFXPanel_Graph, JFXchart.getScene());
		initJFX_Module(JFXPanel_Input, JFXinput.getScene());
		//initJFX_Module(JFXPanel_Menu, JFXmenu.getScene());
	}

	private static void initJFX_Module(JFXPanel panel, Scene scene) {
		panel.setScene(scene);
	}

	public JSplitPane getSeperatedPanes() {
		return seperatedPanes;
	}
	
	public JPanel getBottomPane() {
		return bottomPane;
	}
	
/*	public JPanel getMenuPane() {
		return menuPane;
	}*/
	
}
