package ps.system.api;

import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ps.system.frames.Banner;
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

		Banner banner = new Banner("PhysSim Main Menu");

		root.setTop(banner.showBanner());
		root.setCenter(AccordionMenu());
		
		scene = new Scene(root);
	}
	
	private static HBox AccordionMenu() {
		HBox box = new HBox();
		
		TitledPane MechanicsPane = new TitledPane("Mechanics", new Button("Button"));
        TitledPane WavesPane = new TitledPane("Waves, Optics, and Modern Physics", new Text("String"));
        TitledPane EMPane = new TitledPane("Electricity and Magnetism", new Rectangle(120,50, Color.RED));
        Accordion accordionMenu = new Accordion();
        accordionMenu.getPanes().add(MechanicsPane);
        accordionMenu.getPanes().add(WavesPane);
        accordionMenu.getPanes().add(EMPane);
        accordionMenu.setMinSize(1000, 450);

        box.getChildren().add(accordionMenu);
		return box;
	}
	
	
}
