package ps.system.menu;

import static java.lang.Math.random;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import ps.system.internationalization.Language;
import ps.system.main.PhysicsWindow;
import ps.system.main.SystemConstants;;


public class Lang implements MenuInterface, SystemConstants {

	private static BorderPane root;
	
	public static BorderPane getRoot() {
		return root;
	}
	
	String[] specific;
	
	static Timeline animation;

	public Lang() {

	}

	public Lang(double checkWidth, double checkHeight, Scene primaryScene) {
		root = new BorderPane();

		//Languages
		
		specific = new String[5];
		specific[0] = "English";
		specific[1] = "French";
		specific[2] = "";
		specific[3] = "Polish";
		specific[4] = "Romanian";
		int langCount = specific.length;

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
    	MenuElement creator = new MenuElement();
		StackPane[] elements = new StackPane[langCount];
		VBox vbox = new VBox(spacer);
		for (int i = 0; i < langCount; i++) {
			final int x = i;
			elements[i] = creator.menuElement(specific[i]);
			vbox.getChildren().add(elements[i]);
			elements[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
			
					animation.stop();

				}
			});
		}
		ScrollPane scroll = new ScrollPane();
		scroll.setMinHeight(380);
		scroll.setMinWidth(375);
		scroll.setStyle("-fx-background-color:transparent;");
		scroll.setContent(vbox);

		// -----------------------------------------Creating the Options
		OptionElement o_creator = new OptionElement();
		int o_size = 2;
		StackPane[] options = new StackPane[o_size];
		String[] o_names = {"Main Menu", "Exit" };
		VBox o_vbox = new VBox(spacer);
		for (int i = 0; i < 2; i++) {
			options[i] = o_creator.optionElement(o_names[i]);
			o_vbox.getChildren().add(options[i]);
		}
		// BACK BUTTON EVENT
		options[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				root.getChildren().removeAll();
				animation.stop();

				Menu.resetRoot();

				//MainMenu.init(primaryStage);

			}
		});

		// EXIT BUTTON EVENT
		options[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				System.exit(0);
			}
		});

		// -----------------------------------------Title

		Group programName = Title.title();
		BorderPane.setAlignment(programName, Pos.CENTER);
		BorderPane.setMargin(programName, new Insets(22, 0, 22, 0));
		root.setTop(programName);

		// -----------------------------------------Positioning

		AnchorPane leftSide = new AnchorPane();
		leftSide.getChildren().add(scroll);
		AnchorPane.setTopAnchor(scroll, (double) upBorderDistance);
		AnchorPane.setLeftAnchor(scroll, (double) borderDistance);

		AnchorPane rightSide = new AnchorPane();
		rightSide.getChildren().add(o_vbox);
		AnchorPane.setTopAnchor(o_vbox, (double) upBorderDistance);
		AnchorPane.setRightAnchor(o_vbox, (double) borderDistance);

		root.setLeft(leftSide);
		root.setRight(rightSide);

	}

}
