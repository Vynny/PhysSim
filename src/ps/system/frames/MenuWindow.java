package ps.system.frames;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuWindow implements Constants {
	
	static Object[][] marathonerAttributes = new Object[NUMBER_OF_MARATHONERS][NUMBER_OF_ATTRIBUTES];
	private static Scene scene;
	
	public static Scene getScene() {
		return scene;
	}
	
	/*public void setScene(Scene scene) {
		this.scene = scene;
	}*/


	public MenuWindow(String TITLE, String BGColor) {
		BorderPane paneMain = new BorderPane();
		scene = new Scene(paneMain);

		Banner banner = new Banner(TITLE);

		paneMain.setTop(banner.showBanner());
		paneMain.setCenter(SelectionMenu(BGColor));
	}

	private static VBox SelectionMenu(String BGColor) {
		VBox vbox = new VBox();
		vbox.setStyle(BGColor);
		vbox.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		
		Button marathonPictures = new Button("Display a slide show of marathoner pictures");
		marathonPictures.setScaleX(BUTTONSCALE);
		marathonPictures.setScaleY(BUTTONSCALE);

		marathonPictures.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("TEST");
			}
			
		});
		
		Button marathonSimulation = new Button("Display a marathon simulation");
		marathonSimulation.setScaleX(BUTTONSCALE);
		marathonSimulation.setScaleY(BUTTONSCALE);
		
		marathonSimulation.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("TEST");
			}

		});
		
		Button marathonExit = new Button("Exit");
		marathonExit.setScaleX(BUTTONSCALE);
		marathonExit.setScaleY(BUTTONSCALE);
		
		marathonExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("TERMINATING APPLICATION");
				System.exit(0);
				
			}
			
		});
		
		vbox.getChildren().addAll(marathonPictures, marathonSimulation, marathonExit);
		
		return vbox;
	}

}
