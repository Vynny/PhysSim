package ps.system.frames;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ps.logic.beans.TimerBean;
import ps.system.api.SimulatorInstance;
import ps.system.main.PhysicsWindow;

public class TrackWindow extends SimulatorInstance implements Constants {

	//Global arrays
	private static Object[][] marathonerAttributes = new Object[NUMBER_OF_MARATHONERS][NUMBER_OF_ATTRIBUTES];
	private static Person[] Marathoners = new Person[NUMBER_OF_MARATHONERS];
	private static Timeline runners = new Timeline();
	private static Text[] trackText = new Text[Marathoners.length];
	private static boolean[] marathonFinished = new boolean[Marathoners.length];
	private static KeyFrame[] keyFrames;
	private static KeyValue[] keyValues;
	
	//Global panes
	private static Pane marathonersPane;
	
	
	public static int SIM_basetime = 5;
	public static double SIM_distance = 500;
	
	public TrackWindow(String title) {
		//BEGIN JAVAFX
		GenerateMarathonerProperties();
		Handlers();

		for (int i = 0; i < Marathoners.length; i++) {
			System.out.println("TEST: " + Marathoners[i].toString());
		}
		
		BorderPane root = new BorderPane();
		scene = new Scene(root);

		Banner banner = new Banner(title);

		root.setTop(banner.showBanner());
		root.setCenter(TrackPane());
		//root.setBottom(BottomMenu());
	}
	
	private static BorderPane TrackPane() {
		BorderPane trackPane = new BorderPane();
		marathonersPane = new Pane();

		trackPane.setStyle(BGCOLOR);
		Track track = new Track();
		track.buildTrack(trackPane, TRACK_WIDTH, TRACK_HEIGHT, TRACK_COLOR, TRACK_COLOR_START, TRACK_COLOR_FINISH);

		for (int i = 0; i < Marathoners.length; i++) {
			Marathoners[i].runner(marathonersPane, 50, 20 + TRACK_LANE_SPACING * i);
		}

		trackPane.setBottom(StatusMenu());
		trackPane.getChildren().add(marathonersPane);

		return trackPane;
	}
	
	private static VBox StatusMenu() {
		final TimerBean timer = new TimerBean();
		timer.setTimer(0);
		
		VBox statusPane = new VBox();
		statusPane.setStyle(BGCOLOR);
		statusPane.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		statusPane.setSpacing(20);
		statusPane.setAlignment(Pos.BASELINE_CENTER);
		
		for (int i = 0; i < trackText.length; i++) {
			String text = "Track " + (i + 1) + ": 0";
			trackText[i] = new Text(text);
			trackText[i].setScaleX(2);
			trackText[i].setScaleY(2);
			statusPane.getChildren().addAll(trackText[i]);
		}
		
		runners.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				
				for (int i = 0; i < trackText.length; i++) {
					String text = "Track " + (i + 1) + ": ";

					if ((Marathoners[i].runnerNode().getTranslateX() == SIM_distance) && !marathonFinished[i]) {
						int places = 0;
						int currentTime = (int) runners.getCurrentTime().toMillis();

						Marathoners[i].StopRunning();
						marathonFinished[i] = true;

						for (int j = 0; j < marathonFinished.length; j++) {
							if (marathonFinished[j] == true) {
								places++;
							} 
						}

						switch (places) {
						case 1:
							trackText[i].setText(text + currentTime + "ms " +" - 1st Place!");
							break;
						case 2:
							trackText[i].setText(text + currentTime + "ms " +" - 2nd Place");
							break;
						case 3:
							trackText[i].setText(text + currentTime + "ms " + " - 3rd Place");
							break;
						}

					} else if (!marathonFinished[i]) {
						timer.setTimer((int) runners.getCurrentTime().toMillis());
						trackText[i].setText(text + timer.getTimer() + "ms");
					}
				}
			}

		});
			
		return statusPane;
	}
	
	private static void Handlers() {
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {		
				Random randomNumber = new Random();
				keyValues = new KeyValue[Marathoners.length];
				keyFrames = new KeyFrame[Marathoners.length];
				
				runners.setCycleCount(1);
				
				for (int i = 0; i < Marathoners.length; i++) {
					double lapTime = SIM_basetime + (double)((randomNumber.nextInt(10)/2) + randomNumber.nextInt(20)/3)/1.5;
					keyValues[i]= new KeyValue(Marathoners[i].runnerNode().translateXProperty(), SIM_distance, interpolators[randomNumber.nextInt(interpolators.length - 1)]);
					keyFrames[i] = new KeyFrame(Duration.seconds(lapTime), keyValues[i]);
					runners.getKeyFrames().addAll(keyFrames[i]);
				}
				
				
				for (int i = 0; i < Marathoners.length; i++) {
					Marathoners[i].StartRunning();
				}
				
				runners.play();
				
			}
			
		});
		
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ResetTrack();
			}
			
		});
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				/*MenuWindow.MenuWindow(currentStage);*/
				System.out.println("FUNCTIONALITY DISABLED");
			}
			
		});
	}
	
	private static void GenerateMarathonerProperties() {
		Random randomNumber = new Random();
		
		//NUMBER, COLOR, COLOR, COLOR
		for (int i = 0; i < marathonerAttributes.length; i++) {
			for (int j = 0; j < marathonerAttributes[i].length; j++) {
				if (j == 0) {
					marathonerAttributes[i][j] = Integer.toString(randomNumber.nextInt(SHIRTNUMBER_RANGE));
				} else {
					marathonerAttributes[i][j] = COLOR_LIST[randomNumber.nextInt(COLOR_LIST.length)];
				}

			}
		}
		
		for (int i = 0; i < Marathoners.length; i++) {
			Marathoners[i] = new Person(
					(String) MenuWindow.marathonerAttributes[i][0],
					(Color) MenuWindow.marathonerAttributes[i][1],
					(Color) MenuWindow.marathonerAttributes[i][2],
					(Color) MenuWindow.marathonerAttributes[i][3]);
		}
		
}
	
	private static void ResetTrack() {
		runners.stop();

		for (int i = 0; i < Marathoners.length; i++) {
			Marathoners[i].StopRunning();
			Marathoners[i].runnerNode().setTranslateX(0);
			trackText[i].setText("Track " + (i + 1) + ": 0");
			marathonFinished[i] = false;
		}
	}
	
	
	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", runners} };
		
		data_shared_write_dependant = new Object[][] { {"m1-1", Marathoners[0].runnerNode()},
													   {"m2-1", Marathoners[1].runnerNode()},
													   {"m3-1", Marathoners[2].runnerNode()},
													   {"m4-1", Marathoners[3].runnerNode()},
													   {"m5-1", Marathoners[4].runnerNode()}};
		
		data_shared_read = new Object[][]  { {"SIM_basetime", SIM_basetime}, 
											 {"SIM_distance", SIM_distance}}; 
		// Bind buttons to infopane
		Handlers();

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteData(data_shared_write_independant, data_shared_write_dependant);
	}
}