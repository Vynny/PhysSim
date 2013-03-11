package ps.system.main;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ps.system.frames.Banner;

/*
 * 
 * !!!!!!!!!!!!!!!!!INCOMPLETE!!!!!!!!!!!!!!!
 * 
 */
public class MainMenu implements SystemConstants {

	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	public MainMenu() {
		BorderPane root = new BorderPane();

		Banner banner = new Banner("PhysSim Main Menu");

		root.setTop(banner.showBanner());
		root.setCenter(AccordionMenu());

		scene = new Scene(root);
	}

	private HBox AccordionMenu() {
		HBox box = new HBox();

		TitledPane MechanicsPane = null;
		TitledPane WavesPane = null; 
		TitledPane EMPane = null;
		
		if (!(SimulationList.simulationList.isEmpty())) {
			MechanicsPane = new TitledPane("Mechanics", GenerateMenu("Mechanics"));
			WavesPane = new TitledPane("Waves, Optics, and Modern Physics", GenerateMenu("Waves"));
			EMPane = new TitledPane("Electricity and Magnetism", GenerateMenu("EM"));
		} else {
			System.err.println("ERROR: NO TOPICS FOUND, CONSULT SIMULATIONLIST.JAVA CLASS");
		}

	

		Accordion accordionMenu = new Accordion();
		accordionMenu.getPanes().add(MechanicsPane);
		accordionMenu.getPanes().add(WavesPane);
		accordionMenu.getPanes().add(EMPane);
		accordionMenu.setMinSize(1000, 450);

		box.getChildren().add(accordionMenu);
		return box;
	}

	private VBox GenerateMenu(String s) {
		VBox box = new VBox();
		ArrayList<String> topics = new ArrayList<String>();
		final String topicMap[] = SimulationList.simulationList.keySet().toArray(new String[0]);
		
		final Button buttons[]; 
		String topicButtons[];
		
		int startIndex = -1;
		
		box.setSpacing(20);
		box.setPadding(new Insets(20,20,20,20));
		
		for (int i = 0; i < topicMap.length; i++) {
			String element[] = topicMap[i].split("_");
			if (element[0].equals(s)) {
				topics.add(element[1]);
				if (startIndex == -1) {
					startIndex = i;
				}
			}
		}
		
		topicButtons = topics.toArray(new String[topics.size()]);
		buttons = new Button[topicButtons.length];

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(topicButtons[i]);
			System.out.println(i + " ButtonTopic " + topicButtons[i]);

			final int topicMapIndex = startIndex + i;
			
			buttons[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					PhysicsWindow.JFXPanes.simulationID.setSimulationID(topicMap[topicMapIndex]);
					PhysicsWindow.changeWindow("Simulation");
				}

			});

			box.getChildren().addAll(buttons[i]);
		}

		return box;
	}

}
