package ps.system.menu;

import static java.lang.Math.random;

import java.util.List;
import java.util.Locale;

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
import javafx.util.Duration;
import ps.system.internationalization.Language;
import ps.system.main.SystemConstants;

public class Lang implements MenuInterface, SystemConstants {

	private static BorderPane root;
	
	public static BorderPane getRoot() {
		return root;
	}
	
	private static Scene langMenu;

	public static Scene getScene() {
		return langMenu;
	}
	
	public static void setScene(Scene scene) {
		langMenu = scene;
	}
	
	private String[] specific;
	static Timeline animation;

	public Lang() {
		root = new BorderPane();
		langMenu = new Scene(root, W_WIDTH, W_HEIGHT, backgroundcolor);

		//Languages
		specific = new String[5];
		specific[0] = "English";
		specific[1] = "Français";
		specific[2] = "Polski";
		specific[3] = "Español";
		specific[4] = "Româná";
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
			
					switch(x) {
					case 0:
						SystemLanguage.loadLocale(Locale.ENGLISH);
						initMenu();
						break;
					case 1:
						SystemLanguage.loadLocale(Locale.FRENCH);
						initMenu();
						break;
					case 2:
						SystemLanguage.loadLocale(Language.POLISH);
						initMenu();
						break;
					case 3:
						SystemLanguage.loadLocale(Language.SPANISH);
						initMenu();
						break;
					case 4:
						SystemLanguage.loadLocale(Language.ROMANIAN);
						initMenu();
						break;
					}
					
					animation.stop();

				}
			});
		}
		ScrollPane scroll = new ScrollPane();
		scroll.setMinHeight(700);
		scroll.setMinWidth(375);
		scroll.setStyle("-fx-background-color:transparent;");
		scroll.setContent(vbox);

		// -----------------------------------------Creating the Options
		OptionElement o_creator = new OptionElement();
		int o_size = 2;
		StackPane[] options = new StackPane[o_size];
		String[] o_names = {"Exit" };
		VBox o_vbox = new VBox(spacer);
		for (int i = 0; i < o_names.length; i++) {
			options[i] = o_creator.optionElement(o_names[i]);
			o_vbox.getChildren().add(options[i]);
		}

		// EXIT BUTTON EVENT
		options[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
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
	
	public void initMenu() {
		root.getChildren().removeAll();
		animation.stop();
		@SuppressWarnings("unused")
		Menu menu = new Menu();
		getScene().setRoot(Menu.getRoot());
	}

}
