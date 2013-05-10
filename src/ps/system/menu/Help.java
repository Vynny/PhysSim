package ps.system.menu;

import static java.lang.Math.random;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ps.system.main.SystemConstants;


public class Help implements MenuInterface, SystemConstants {

	private static BorderPane root;
	
	public static BorderPane getRoot() {
		return root;
	}
	
	String[] specific;
	static Timeline animation;

	public Help() {
		root = new BorderPane();

		//------------------------------------------ANIMATIONS
		
		Background makeback = new Background();
		
		List<javafx.scene.Node> allCircles = makeback.getBackgroundElements(root);
		
        animation = new Timeline();
        for(javafx.scene.Node circle: allCircles) {
            animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0s
                    new KeyValue(circle.translateXProperty(),random()*Background.creatorWidth),
                    new KeyValue(circle.translateYProperty(),random()*Background.creatorHeight)
                ),
                new KeyFrame(new Duration(2), // set end position at 40s
                    new KeyValue(circle.translateXProperty(),random()*Background.creatorWidth),
                    new KeyValue(circle.translateYProperty(),random()*Background.creatorHeight)
                )
            );
        }
        animation.setAutoReverse(false);

        animation.play();

		// -----------------------------------------Creating the Menu Elements
		VBox vbox = new VBox(spacer);
		vbox.getChildren().add(AccordionMenu());

		// -----------------------------------------Creating the Options
		OptionElement o_creator = new OptionElement();
		String[] o_names = {SystemLanguage.getLanguageBundle().getString("Menu_back") };
		StackPane[] options = new StackPane[o_names.length];
		VBox o_vbox = new VBox(spacer);
		
		for (int i = 0; i < o_names.length; i++) {
			options[i] = o_creator.optionElement(o_names[i]);
			o_vbox.getChildren().add(options[i]);
		}
		
		// BACK BUTTON EVENT
		options[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				root.getChildren().removeAll();
				animation.stop();
				Sub.resetRoot();
			}
		});


		// -----------------------------------------Title

		Group programName = Title.title();
		BorderPane.setAlignment(programName, Pos.CENTER);
		BorderPane.setMargin(programName, new Insets(22, 0, 22, 0));
		root.setTop(programName);

		// -----------------------------------------Positioning

		AnchorPane leftSide = new AnchorPane();
		leftSide.getChildren().add(vbox);
		AnchorPane.setTopAnchor(vbox, (double) upBorderDistance + 50);
		AnchorPane.setLeftAnchor(vbox, (double) borderDistance);

		AnchorPane rightSide = new AnchorPane();
		rightSide.getChildren().add(o_vbox);
		AnchorPane.setTopAnchor(o_vbox, (double) upBorderDistance);
		AnchorPane.setRightAnchor(o_vbox, (double) borderDistance);

		root.setLeft(leftSide);
		root.setRight(rightSide);

	}
	
	private HBox AccordionMenu() {
		HBox box = new HBox();

		ArrayList<TitledPane> tPanes = new ArrayList<TitledPane>();
		
		for (int i = 0; i < Sub.getAnimationCount(); i++) {
			String title = Sub.getSpecific()[0][i];
			String text = Sub.getSpecific()[2][i];
			tPanes.add(i, new TitledPane(title, GenerateText(text)));
		}

		Accordion accordionMenu = new Accordion();
		accordionMenu.setMaxSize(900, 450);
		accordionMenu.setStyle("-fx-background-color:transparent; " + 
				   "-fx-text-fill: #000000; " + 
			       "-fx-font-size: 24px; " +
			       "-fx-hbar-policy : never;" + "-fx-vbar-policy : never;");
		for (int i = 0; i < tPanes.size(); i++) {
			accordionMenu.getPanes().addAll(tPanes.get(i));
		}
		
		box.getChildren().add(accordionMenu);
		return box;
	}

	private VBox GenerateText(String s) {
		VBox box = new VBox();
		TextArea about = new TextArea();
		about.prefColumnCountProperty().set(50);
		
		about.setText(s);
		about.setEditable(false);
		about.setStyle("-fx-background-color:transparent; " + 
					   "-fx-text-fill: #000000; " + 
				       "-fx-font-size: 24px; " +
				       "-fx-hbar-policy : never;" + "-fx-vbar-policy : never;");

		box.setSpacing(20);
		box.setPadding(new Insets(20, 20, 20, 20));
		box.getChildren().add(about);
		
		return box;
	}

}
