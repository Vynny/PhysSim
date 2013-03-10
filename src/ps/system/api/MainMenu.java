package ps.system.api;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ps.system.frames.Banner;
import ps.system.main.SimulationList;
import ps.system.main.SystemConstants;
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
	
	private static Object[] MechanicsOptions;
	private static Object[] WavesOptions;
	private static Object[] EMOptions;
	
	public MainMenu() {
		BorderPane root = new BorderPane();

		Banner banner = new Banner("PhysSim Main Menu");

		root.setTop(banner.showBanner());
		root.setCenter(AccordionMenu());
		
		scene = new Scene(root);
	}
	
	private HBox AccordionMenu() {
		HBox box = new HBox();
		
		if (!(SimulationList.simulationList.isEmpty())) {
			 LoadTopicNames();
		} else {
			System.out.println("ERROR: NO TOPICS FOUND, CONSULT SIMULATIONLIST.JAVA CLASS");
		}
		
		TitledPane MechanicsPane = new TitledPane("Mechanics", GenerateMenu(MechanicsOptions));
        TitledPane WavesPane = new TitledPane("Waves, Optics, and Modern Physics",  GenerateMenu(WavesOptions));
        TitledPane EMPane = new TitledPane("Electricity and Magnetism", GenerateMenu(EMOptions));
        
        Accordion accordionMenu = new Accordion();
        accordionMenu.getPanes().add(MechanicsPane);
        accordionMenu.getPanes().add(WavesPane);
        accordionMenu.getPanes().add(EMPane);
        accordionMenu.setMinSize(1000, 450);

        box.getChildren().add(accordionMenu);
		return box;
	}
	
	private VBox GenerateMenu(Object[] topics) {
		VBox box = new VBox();
		Button buttons[] = new Button[topics.length];
		
		box.setPadding(new Insets(10,10,10,10));
		box.setSpacing(20);
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button((String) topics[i]);
			box.getChildren().addAll(buttons[i]);
		}

		return box;
	}
	
	private void LoadTopicNames() {
		ArrayList<String> topics_Mechanics = new ArrayList<String>();
		ArrayList<String> topics_Waves = new ArrayList<String>();
		ArrayList<String> topics_EM = new ArrayList<String>();
		
		String topicMap[] = SimulationList.simulationList.keySet().toArray(new String[0]);

		for (int i = 0; i < topicMap.length; i++) {
			String element[] = topicMap[i].split("_");
			
			if (element[0].equals("Mechanics")) {
				topics_Mechanics.add(element[1]);
			} else if (element[0].equals("Waves")) {
				topics_Waves.add(element[1]);
			} else if (element[0].equals("EM")) {
				topics_EM.add(element[1]);
			}
		}
		
		MechanicsOptions = topics_Mechanics.toArray();
		WavesOptions = topics_Waves.toArray();
		EMOptions = topics_EM.toArray();
	}
	
}
