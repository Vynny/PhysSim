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

import ps.system.main.PhysicsWindow;
import ps.system.main.SystemConstants;;


public class Sub implements MenuInterface, SystemConstants {

	private static BorderPane root;
	
	public static BorderPane getRoot() {
		return root;
	}
	
	String[][] specific;
	int animationCount = 0;
	static Timeline animation;

	public Sub() {

	}

	public Sub(double checkWidth, double checkHeight, Scene primaryScene, String identifier) {
		root = new BorderPane();

		NodeList nList = Menu.doc.getElementsByTagName("course");
		int mainMenuSize = nList.getLength();

		for (int temp = 0; temp < mainMenuSize; temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String idtype = (String) eElement.getAttribute("id");
				if (idtype.equals(identifier)) {
					NodeList checkChildren = ((Element) nNode).getElementsByTagName("animation");
					animationCount = checkChildren.getLength();
					
					specific = new String[2][animationCount];
					
					for(int sub = 0; sub < animationCount; sub++){
						Node subNode = checkChildren.item(sub);
						if(subNode.getNodeType() == Node.ELEMENT_NODE){
							Element subElement = (Element) subNode;
					
							if (subElement.getElementsByTagName("name").item(0).getTextContent().split("LANGUAGE:").length > 1) {
								specific[0][sub] = SystemLanguage.getLanguageBundle().getString(subElement.getElementsByTagName("name").item(0).getTextContent().split("LANGUAGE:")[1]);	
							} else {
								specific[0][sub] = subElement.getElementsByTagName("name").item(0).getTextContent();
							}
						
							specific[1][sub] = subElement.getElementsByTagName("file").item(0).getTextContent();
						}
					}

				}
			}

		}

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
                new KeyFrame(new Duration(20000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(),random()*Background.creatorWidth),
                    new KeyValue(circle.translateYProperty(),random()*Background.creatorHeight)
                )
            );
        }
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);

        animation.play();

		// -----------------------------------------Creating the Menu Elements
    	MenuElement creator = new MenuElement();
		StackPane[] elements = new StackPane[animationCount];
		VBox vbox = new VBox(spacer);
		for (int i = 0; i < animationCount; i++) {
			final int x = i;
			elements[i] = creator.menuElement(specific[0][i]);
			vbox.getChildren().add(elements[i]);
			elements[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
			
					PhysicsWindow.JFXPanes.simulationID.setSimulationID(specific[1][x] + "_" + specific[0][x]);

				}
			});
		}
		ScrollPane scroll = new ScrollPane();
		scroll.setFitToWidth(true);
		scroll.autosize();
		scroll.setContent(vbox);

		// -----------------------------------------Creating the Options
		OptionElement o_creator = new OptionElement();
		int o_size = 3;
		StackPane[] options = new StackPane[o_size];
		String[] o_names = { "Back", "Help", "Exit" };
		VBox o_vbox = new VBox(spacer);
		for (int i = 0; i < 3; i++) {
			options[i] = o_creator.optionElement(o_names[i]);
			o_vbox.getChildren().add(options[i]);
		}

		// BACK BUTTON EVENT
		options[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				root.getChildren().removeAll();

				Menu.resetRoot();

				//MainMenu.init(primaryStage);

			}
		});
		// HELP BUTTON EVENT
		options[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				System.exit(0);
			}
		});
		// EXIT BUTTON EVENT
		options[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		leftSide.getChildren().add(vbox);
		AnchorPane.setTopAnchor(vbox, (double) upBorderDistance);
		AnchorPane.setLeftAnchor(vbox, (double) borderDistance);

		AnchorPane rightSide = new AnchorPane();
		rightSide.getChildren().add(o_vbox);
		AnchorPane.setTopAnchor(o_vbox, (double) upBorderDistance);
		AnchorPane.setRightAnchor(o_vbox, (double) borderDistance);

		root.setLeft(leftSide);
		root.setRight(rightSide);

	}

}