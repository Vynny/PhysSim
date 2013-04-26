package ps.system.frames;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ps.simulation.frames.Banner;
import ps.system.main.PhysicsWindow;
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

	public MainMenu() {
		BorderPane root = new BorderPane();

		Banner banner = new Banner(SystemLanguage.getLanguageBundle().getString("Menu_title"));

		root.setTop(banner.showBanner());
		root.setCenter(AccordionMenu());
		root.setLeft(LanguageMenu());
		
		scene = new Scene(root);
	}

	@SuppressWarnings("unchecked")
	private HBox LanguageMenu() {
		HBox box = new HBox();
		
		ObservableList<String> options = FXCollections.observableArrayList(
				"English", "French");
		
		final ComboBox comboBox = new ComboBox(options);
		
		comboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(newValue);
				SystemLanguage.loadLocale(SystemLanguage.getLanguagesSupported()[newValue.intValue()]);
			}
			
		});
		

		
		box.getChildren().addAll(comboBox);

		return box;
	}
	private HBox AccordionMenu() {
		HBox box = new HBox();

		TitledPane MechanicsPane = null;
		TitledPane WavesPane = null; 
		TitledPane EMPane = null;
		
		if (!(SimulationList.simulationList.isEmpty())) {
			MechanicsPane = new TitledPane(SystemLanguage.getLanguageBundle().getString("Categories_mechanics"), GenerateMenu("Mechanics"));
			WavesPane = new TitledPane(SystemLanguage.getLanguageBundle().getString("Categories_waves"), GenerateMenu("Waves"));
			EMPane = new TitledPane(SystemLanguage.getLanguageBundle().getString("Categories_em"), GenerateMenu("EM"));
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
		String buttonTopics[];
		
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
		
		buttonTopics = topics.toArray(new String[topics.size()]);
		buttons = new Button[buttonTopics.length];

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(buttonTopics[i]);
			System.out.println(i + " ButtonTopic " + buttonTopics[i]);

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
