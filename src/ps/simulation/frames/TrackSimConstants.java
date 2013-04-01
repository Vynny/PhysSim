package ps.simulation.frames;

import javafx.animation.Interpolator;
import javafx.scene.paint.Color;

public interface TrackSimConstants {
		
	//Titles
	String WINDOWTITLE = "Assignment 4: The Race";
	String SLIDESHOWTITLE = "Vanier Marathon - The Marathoners";
	String TRACKTITLE = "Vanier Marathon - Marathon";
	String MENUTITLE = "Vanier Marathon - Main Menu";
	
	//Color
	String BGCOLOR = "-fx-background-color: #336699";
	
	//Window Attributes
	int WIDTH = 500;
	int HEIGHT = 800;
	int BUTTONSPACING = 20;
	double BUTTONSCALE = 1.5;
	int PADDING = 10;

	//Track Attributes
	int TRACK_WIDTH = 650;
	int TRACK_HEIGHT = 500;
	int TRACK_LANE_SPACING = 100;
	Color TRACK_COLOR = Color.GOLDENROD;
	Color TRACK_COLOR_START = Color.GREEN;
	Color TRACK_COLOR_FINISH = Color.RED;
	
	//Marathoner Attributes
	int NUMBER_OF_MARATHONERS = 5;
	int NUMBER_OF_ATTRIBUTES = 4;
	int SHIRTNUMBER_RANGE = 50;
	Color[] COLOR_LIST ={ Color.PEACHPUFF, Color.BLACK, Color.OLIVE, Color.BLUE, Color.BROWN, Color.YELLOW, Color.PURPLE, Color.BROWN
						, Color.BISQUE, Color.SILVER};
	
	Interpolator[] interpolators = {Interpolator.EASE_IN, Interpolator.EASE_BOTH, Interpolator.EASE_OUT,
									Interpolator.LINEAR};

	
	int BASETIME = 5;
    double marathon_FINISH = 640d;
	
	
	
}
