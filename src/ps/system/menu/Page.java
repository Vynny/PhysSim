package ps.system.menu;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Page implements MenuInterface {
	public Page() {

	}

	public void page(double actualWidth, double actualHeight,
			final Stage primaryStage, String name, String file) {

		final BorderPane root = new BorderPane();
		final Scene page = new Scene(root, actualWidth, actualHeight, Color.BLACK);
		primaryStage.setScene(page);
		// -----------------------------------------LOAD CLASS

		// Create a File object on the root of the directory containing the
		// class file
		File directory = new File(
				"C:/Users/Sylvain/Desktop/progtemp/Menu 1.1/animations/");

		VBox box = new VBox();

		try {
			// Convert File to a URL
			URL url = directory.toURI().toURL();
			URL[] urls = new URL[] { url };

			// Create a new class loader with the directory
			ClassLoader cl = new URLClassLoader(urls);


			Class<?> cls = cl.loadClass("Circle");
			Object memory = cls.newInstance();
			System.out.println(memory.toString());
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		// -----------------------------------------Creating the return
		OptionElement o_creator = new OptionElement();
		int o_size = 1;
		StackPane[] options = new StackPane[o_size];
		String[] o_names = { "Main Menu" };
		VBox o_vbox = new VBox(spacer);
		for (int i = 0; i < o_size; i++) {
			options[i] = o_creator.optionElement(o_names[i]);
			o_vbox.getChildren().add(options[i]);
		}

		// MAIN MENU BUTTON EVENT
		options[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				root.getChildren().removeAll();

				double actualWidth = page.getWidth();
				double actualHeight = page.getHeight();
				Menu.changedSize(actualWidth, actualHeight);

				//MainMenu.init(primaryStage);

			}
		});
		AnchorPane rightSide = new AnchorPane();
		rightSide.getChildren().add(o_vbox);
		AnchorPane.setTopAnchor(o_vbox, (double) borderDistance);
		AnchorPane.setRightAnchor(o_vbox, (double) borderDistance);

		// -----------------------------------------Positioning

		root.setRight(rightSide);
		root.setLeft(box);

	}

}