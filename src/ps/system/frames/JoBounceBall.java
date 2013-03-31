package ps.system.frames;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.system.api.JoFrame;
import ps.system.main.PhysicsWindow;

public class JoBounceBall extends JoFrame {

	/*
	 * --------------- VARIABLES ---------------
	 * 
	 * 
	 * -> g is scaled 300 times up to take into account how small the pixels are.
	 * other wise its like throwing a 30 diameter ball up in the air and looking
	 * at it from a distance
	 * 
	 */
	
	int SPACECONSTANT = 10;

	int position = 0;
	double mass = 1; //Mass of object
	double COR = 0.7; //Coefficient of restitution (1 - bounciest, 0 - not bouncy)
	double y = 600; //y position
	double yMaxHeight = y; //Initial y position
	double yMaxHeightLast = yMaxHeight;
	double ydamp = 0;
	SimVariableBean yBean = new SimVariableBean();
	double velocityFinal = 0;
	double v = -1300;
	double frame = 0;
	double high = 0;
//	double g = 9.8 * 300;
	double g = 9.81 * SPACECONSTANT;
	
	//TESTING
	public static int SIM_basetime = 5;
	public static double SIM_distance = 500;
	
	public JoBounceBall() {		
		yBean.setValue(y);

		start();
	}

	public void animationLogic() {

		System.out.println("y: " + yBean.getValue());
		System.out.println("yMaxHeight: " + yMaxHeight);
		System.out.println("yMaxHeightL: " + yMaxHeightLast);

		// Fall from max height
		if (y <= 0 && yMaxHeight <= 0 && velocityFinal <= 0){
			System.out.println("STOPPED");
			stop();
		} else if (yBean.getValue() > 0 && yMaxHeight == yMaxHeightLast) {

			// Height = Height(initial) - 1/2g*t^2
			ydamp = (0.5 * ((g) * (Math.pow(timeBeanLocal.getTime(), 2))));
			yBean.setValue(yMaxHeight - ydamp);
			repaint();
			
		} else if (yMaxHeight > yBean.getValue() && yMaxHeightLast > yMaxHeight && yBean.getValue() != yMaxHeight) { // Going up to new max height

			ydamp = velocityFinal - (0.5 * ((g) * (Math.pow(timeBeanLocal.getTime(), 2)))) ;
			System.out.println("ydamp: " + ydamp);
			yBean.setValue(yBean.getValue() + ydamp);
			
			if (yBean.getValue() >= yMaxHeight) {
				resetLocalTime();
				yMaxHeightLast = yMaxHeight;
			}
			
			repaint();
			
		}  else if (yBean.getValue() <= 0) { // New height calculation when ground touched
			
			// Final velocity right as it hits the floor
			velocityFinal = (COR * Math.sqrt(2 * g * yMaxHeight));
			System.out.println("vf: " + velocityFinal);
			// This is the new max height it goes to after the fall
			yMaxHeight = ((Math.pow(velocityFinal, 2)) / (2 * g));
			
			resetLocalTime();
			repaint();
			
		} 

	} 


	public void paint(Graphics g) {

		// Put anything here and it will render on screen (this includes images)
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, 900, 100);
		g.setColor(Color.white);
		g.fillOval(200, (int) yBean.getValue(), 30, 30);
		g.drawString("yPos: " + yBean.getValue(), 200, 40);
		g.drawString("SSS: " + secondsCurrent, 200, 70);
		g.drawString("Anim Time: " + timeBean.getTime(), 200, 100);

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Ball Y", yBean.getSimVariableBeanProperty()} };
	
		data_shared_read = new Object[0][0] ; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

