package ps.system.menu;

import static java.lang.Math.random;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import ps.system.main.SystemConstants;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu implements MenuInterface, SystemConstants {
	
	private static Scene mainMenu;

	public static Scene getScene() {
		return mainMenu;
	}
	
	public static void setScene(Scene scene) {
		mainMenu = scene;
	}
	
	private static BorderPane root;

	public static BorderPane getRoot() {
		return root;
	}
	
	static int width = W_WIDTH;
	static int height = W_HEIGHT;
	static Document doc;
	static Timeline animation;

	public static void changedSize(double checkWidth, double checkHeight) {
		width = (int) checkWidth;
		height = (int) checkHeight;
	}

	public Menu() {

	    root = new BorderPane();
		mainMenu = new Scene(root, width, height, backgroundcolor);

		try {
			File fXMLfile = new File("C:/Users/Sylvain/Desktop/progtemp/Menu 1.1/animations/manifest.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXMLfile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("course");
			int mainMenuSize = nList.getLength();
			final String[][] names = new String[2][mainMenuSize];

			for (int temp = 0; temp < mainMenuSize; temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					names[1][temp] = (String) eElement.getAttribute("id");
					names[0][temp] = (String) eElement.getElementsByTagName("coursetitle").item(0).getTextContent();
					
					System.out.println("TITLE " + temp + ": " + names[0][temp] );
					
					if (eElement.getElementsByTagName("coursetitle").item(0).getTextContent().split("LANGUAGE:").length > 1) {

						names[0][temp] = SystemLanguage.getLanguageBundle().getString((String)eElement.getElementsByTagName("coursetitle").item(0).getTextContent().split("LANGUAGE:")[1]);

					} else {
						names[0][temp] = (String) eElement.getElementsByTagName("coursetitle").item(0).getTextContent();
					}
				

				}
			}

			// -----------------------------Creating the Menu Elements

			int size = mainMenuSize;
			StackPane[] elements = new StackPane[size];
			MenuElement creator = new MenuElement();
			VBox vbox = new VBox(spacer);

			for (int i = 0; i < size; i++) {
				final int x = i;
				elements[i] = creator.menuElement(names[0][i]);
				vbox.getChildren().add(elements[i]);
				elements[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						
						root.getChildren().removeAll();
						animation.stop();
						@SuppressWarnings("unused")
						Sub submenu = new Sub(0, 0, getScene(), names[1][x]);
						getScene().setRoot(submenu.getRoot());
						

					}
				});
			}
			
			ScrollPane scroll = new ScrollPane();
			scroll.setMinHeight(480);
			scroll.setMinWidth(375);
			scroll.setStyle("-fx-background-color:transparent;");
			scroll.setContent(vbox);
			
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
	        animation.setAutoReverse(true);
	        //animation.setCycleCount(Animation.INDEFINITE);
	        
	        animation.play();

			// -----------------------------------------Creating the Options
			OptionElement o_creator = new OptionElement();
			String[] o_names = {"Help", "Language", "Exit" };
			int o_size = o_names.length;
			StackPane[] options = new StackPane[o_size];
			VBox o_vbox = new VBox(spacer);
			for (int i = 0; i < o_size; i++) {
				options[i] = o_creator.optionElement(o_names[i]);
				o_vbox.getChildren().add(options[i]);
			}
			// HELP BUTTON EVENT
			options[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					root.getChildren().removeAll();
					animation.stop();
					@SuppressWarnings("unused")
					Help help = new Help(0, 0, getScene());
					getScene().setRoot(help.getRoot());
				}
			});
			//LANGUAGE PAGE
			options[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					root.getChildren().removeAll();
					animation.stop();
					@SuppressWarnings("unused")
					Lang lang = new Lang(0, 0, getScene());
					getScene().setRoot(lang.getRoot());
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
			leftSide.getChildren().add(scroll);
			AnchorPane.setTopAnchor(scroll, (double) upBorderDistance);
			AnchorPane.setLeftAnchor(scroll, (double) borderDistance);

			AnchorPane rightSide = new AnchorPane();
			rightSide.getChildren().add(o_vbox);
			AnchorPane.setTopAnchor(o_vbox, (double) upBorderDistance);
			AnchorPane.setRightAnchor(o_vbox, (double) borderDistance);

			root.setLeft(leftSide);
			root.setRight(rightSide);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
	
	public static void resetRoot() {
		getScene().setRoot(root);
	}

}
