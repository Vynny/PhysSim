package ps.system.main;

import java.awt.BorderLayout;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ps.logic.beans.BindingInterface;
import ps.system.frames.ChartMaker;
import ps.system.frames.MenuWindow;
import ps.system.frames.Person;
import ps.system.frames.TrackWindow;

public class JFXPanes extends JPanel implements SystemConstants {

	//Pane separator
	private static JSplitPane seperatedPanes;
	
	private TrackWindow JFXsimulation;
	private ChartMaker JFXchart;
	
	public BindingInterface sharedData;
	
	public JFXPanes() {
		
		//Create panel dedicated to showing the simulation & center 
		JPanel window_Simulation = new JPanel(new BorderLayout());
		final JFXPanel jfx_Simulation = new JFXPanel();
		window_Simulation.add(jfx_Simulation, BorderLayout.CENTER);
		
		//Create panel dedicated to showing graphs & center 
		JPanel window_Graph = new JPanel(new BorderLayout());
		final JFXPanel jfx_Graph = new JFXPanel();
		window_Graph.add(jfx_Graph, BorderLayout.CENTER);
		
		//Add panels to horizontally separated panes
		seperatedPanes = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, window_Simulation, window_Graph);
		seperatedPanes.setResizeWeight(JFXPanes_RESIZEWEIGHT);
		seperatedPanes.setContinuousLayout(true);
		
		
		sharedData = new BindingInterface();
				
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				BindingInterface sharedData = new BindingInterface();
				
				JFXsimulation = new TrackWindow("Racing Simulator");
				sharedData.addData(JFXsimulation.getSharedData());
				JFXchart = new ChartMaker(sharedData);
				
				initJFX_Module(jfx_Simulation, JFXsimulation.getScene());
				initJFX_Module(jfx_Graph, JFXchart.getScene());
	
			}
		});

	}

	public JSplitPane getSeperatedPanes() {
		return seperatedPanes;
	}

	private static void initJFX_Module(JFXPanel panel, Scene scene) {
		panel.setScene(scene);
	}

/*	private static void initJFX_Module(JFXPanel panel, int i) {
		Scene scene = initScene(i);
		panel.setScene(scene);
	}*/

/*	private Scene getSceneSimulation() {
		return sceneSimulation;
	}

	private Scene getSceneGraph() {
		return sceneGraph;
	}
*/
	
	//TEST METHOD
	private static Scene initScene(int i) {
		Group root = new Group();
		Scene scene = new Scene(root);
		
		Text text = new Text("SCENE:" + i);
		text.setX(50);
		text.setY(50);
		text.setScaleX(20);
		root.getChildren().add(text);
		return scene;
	}
	
}
