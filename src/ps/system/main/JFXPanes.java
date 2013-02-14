package ps.system.main;

import java.awt.BorderLayout;
import java.util.Iterator;

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

import ps.system.api.DataStore;
import ps.system.api.ChartMaker;
import ps.system.frames.MenuWindow;
import ps.system.frames.Person;
import ps.system.frames.TrackWindow;

public class JFXPanes extends JPanel implements SystemConstants {

	//Pane separator
	private static JSplitPane seperatedPanes;
	
	private TrackWindow JFXsimulation;
	private ChartMaker JFXchart;
	
	//public BindingInterface sharedData;
	
	public JFXPanes() {
		
		//Create panel dedicated to showing the simulation 
		JPanel window_Simulation = new JPanel(new BorderLayout());
		final JFXPanel jfx_Simulation = new JFXPanel();
		window_Simulation.add(jfx_Simulation, BorderLayout.CENTER);
		
		//Create panel dedicated to showing graphs 
		JPanel window_Graph = new JPanel(new BorderLayout());
		final JFXPanel jfx_Graph = new JFXPanel();
		window_Graph.add(jfx_Graph, BorderLayout.CENTER);
		
		//Add panels to horizontally separated panes
		seperatedPanes = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, window_Simulation, window_Graph);
		seperatedPanes.setResizeWeight(JFXPanes_RESIZEWEIGHT);
		seperatedPanes.setContinuousLayout(JFXPanes_CONTINUOUSLAYOUT);
		
		
		//sharedData = new BindingInterface();
				
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				DataStore sharedData = new DataStore();
				sharedData = PhysicsWindow.sharedData;
				
				JFXsimulation = new TrackWindow("Racing Simulator");
				//sharedData.addData(JFXsimulation.getSharedReadData(),JFXsimulation.getSharedWriteData());
				//JFXchart = new ChartMaker(sharedData);
				JFXchart = new ChartMaker();
				
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
