package ps.system.menu;

import javafx.scene.paint.Color;


public interface MenuInterface {
	
	//window width, height, and title variables
	int W_WIDTH = 1000;
	int W_HEIGHT = 610;
	String title = "Simple Physics Sim";
	
	//menu and option spacers
	int upBorderDistance =10;
	int borderDistance = 100;
	int spacer = 20;
	
	//menu element
	int borderWidth = 320;
	int borderHeight = 110;
	int borderArc = 10;
	int borToMain = 3;
	/*
	Color unselected = Color.web("#000000");
	Color selected = Color.web("#3AAACF");
	Color backColor = Color.web("#FF6700");
	Color accentColor = Color.web("#FFE400");
	Color backgroundcolor = Color.web("#7A7A7A");
	*/
	double unsOpac = 0.6;
	double selOpac = 1;
	
	Color unselected = Color.web("#000000");
	Color selected = Color.web("#FFFFFF");
	Color backColor = Color.web("FF7600");
	Color accentColor = Color.web("#FFC200");
	Color backgroundcolor = Color.web("#000000");
	 
	//menu option
	int o_borderWidth = 110;
	int o_borderHeight = 45;
	int o_borderArc = 5;
	int o_borToMain = 2;

}
