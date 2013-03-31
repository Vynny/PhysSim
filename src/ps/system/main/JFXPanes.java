package ps.system.main;

import java.awt.BorderLayout;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ps.logic.beans.SimulationIDBean;
import ps.system.api.ChartMaker;
import ps.system.api.JoFrame;
import ps.system.api.SimulatorInstance;
import ps.system.frames.JoBounceBall;

public class JFXPanes extends JPanel implements SystemConstants {

	// Pane separators
	private static JSplitPane seperatedPanes;
	private static JPanel bottomPane;
	private static JPanel menuPane;

	// Swing components
	private JoFrame SwingSimulation;
	
	// JavaFX components
	private SimulatorInstance JFXSimulation;
	private MainMenu JFXMenu;
	private static ChartMaker JFXChart;
	public static InfoPane JFXInput;

	private JPanel window_Menu;
	private JPanel window_Simulation;
	private JPanel window_Graph;
	
	/*
	 * Containers for swing and JavaFX integration
	 */
	
	//Swing
	private static JPanel JPanel_Simulation = new JPanel();
	
	//JavaFX
	private static JFXPanel JFXPanel_Simulation = new JFXPanel();
	private static JFXPanel JFXPanel_Graph = new JFXPanel();
	private static JFXPanel JFXPanel_Input = new JFXPanel();
	private static JFXPanel JFXPanel_Menu = new JFXPanel();

	// Which simulation to display
	// private String simulationID = null;
	public SimulationIDBean simulationID = new SimulationIDBean();

	public JFXPanes() {

		// this.simulationID = simulationID;
		// Create menu panel
		window_Menu = new JPanel(new BorderLayout());
		window_Menu.add(JFXPanel_Menu, BorderLayout.CENTER);

		// Create panel dedicated to showing the simulation
		window_Simulation = new JPanel(new BorderLayout());

		// Create panel dedicated to showing graphs
		window_Graph = new JPanel(new BorderLayout());
		window_Graph.add(JFXPanel_Graph, BorderLayout.CENTER);

		// Create panel dedicated to showing input and buttons
		bottomPane = new JPanel(new BorderLayout());
		bottomPane.add(JFXPanel_Input, BorderLayout.CENTER);

		// Add panels to horizontally separated panes
		seperatedPanes = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, window_Simulation, window_Graph);
		seperatedPanes.setResizeWeight(JFXPanes_RESIZEWEIGHT);
		seperatedPanes.setContinuousLayout(JFXPanes_CONTINUOUSLAYOUT);

		// Menu
		menuPane = new JPanel(new BorderLayout());
		menuPane.add(JFXPanel_Menu, BorderLayout.CENTER);

		// Initialize JavaFX Event Thread
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				InitializeJFXSimulationWindows();
			}
		});

	}

	// Initialize JavaFX Objects
	private void InitializeJFXSimulationWindows() {
		SimulationList simList = new SimulationList();

		if (simulationID.getSimulationID() == null) {
			JFXMenu = new MainMenu();
			initJFX_Module(JFXPanel_Menu, JFXMenu.getScene());
		}

		simulationID.SimulationIDProperty().addListener(
				new InvalidationListener() {

					@Override
					public void invalidated(Observable arg0) {
						System.out.println(simulationID.getSimulationID());

						if (!(simulationID.getSimulationID().equals(" "))) {
							
							// Initialize Simulation Content
							Object genericSimulation = SimulationList.simulationList.get(simulationID.getSimulationID());
							
							if (genericSimulation instanceof SimulatorInstance) {
								JFXSimulation = (SimulatorInstance) genericSimulation;
								window_Simulation.add(JFXPanel_Simulation, BorderLayout.CENTER);
								initJFX_Module(JFXPanel_Simulation, ((SimulatorInstance) JFXSimulation).getScene());
								
								JFXSimulation.LoadData();
								
								// Initialize Chart for content
								JFXPanes.JFXChart = new ChartMaker(ChartMaker.JFXDATASET);

								// Initialize InfoPane for content
								JFXPanes.JFXInput = new InfoPane();
								
							} else {
								SwingSimulation = (JoFrame) genericSimulation;
								JPanel_Simulation = SwingSimulation;
								window_Simulation.add(JPanel_Simulation);
								
								SwingSimulation.LoadData();
								
								// Initialize Chart for content
								JFXPanes.JFXChart = new ChartMaker(ChartMaker.SWINGDATASET);

								// Initialize InfoPane for content
								JFXPanes.JFXInput = new InfoPane();
							}


							initJFX_Module(JFXPanel_Graph, JFXChart.getScene());
							initJFX_Module(JFXPanel_Input, JFXInput.getScene());
						} else {
							/*
							 * TO DO:
							 * 	-> Fix data unloading to properly load according chart 
							 */
							JFXSimulation.UnLoadData();
							PhysicsWindow.changeWindow("Menu");
						}
					}

				});

	}

	private static void initJFX_Module(JFXPanel panel, Scene scene) {
		panel.setScene(scene);
	}
	
	private static void initSwing_Module(JFXPanel panel, Scene scene) {
		panel.setScene(scene);
	}

	public JSplitPane getSeperatedPanes() {
		return seperatedPanes;
	}

	public JPanel getBottomPane() {
		return bottomPane;
	}

	public JPanel getMenuPane() {
		return menuPane;
	}

}
