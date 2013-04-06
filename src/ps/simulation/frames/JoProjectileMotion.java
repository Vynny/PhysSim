package ps.simulation.frames;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoProjectileMotion extends SimulatorInstanceSwing {

	/*
	 * --------------- VARIABLES ---------------
	 * 
	 */
	
	int SPACECONSTANT = 10;

	int position = 0;
	// all variables that affect the simulation are here.
	double angle = 30;
	double y = 500;
	double x = 40;
	double vinit = -1000;
	double v = -1100;
	double frame = 0;
	double high = 0;
	double secondsInit = 0;
	double secondsCurrent = 0;
	double g = 9.8 * 200;
	double damping = 90;
	double xspeed = 6;
	int framesOnGround;
	
	// TESTING
	public static int SIM_basetime = 5;
	public static double SIM_distance = 500;
	
	SimVariableBean positionBeanX = new SimVariableBean();
	SimVariableBean positionBeanY = new SimVariableBean();
	
	
	public JoProjectileMotion() {	
		positionBeanY.setValue(y);
		positionBeanX.setValue(x);
	}

	public void animationLogic() {
		if ((int) (y + high) >= y) {
			framesOnGround++;
			if (framesOnGround >= 7) {
				xspeed = 0;
			}
		} else {
			framesOnGround = 0;
		}
		positionBeanX.setValue(x + xspeed);

		high = (v * timeBeanLocal.getTime() + (0.5 * g * (timeBeanLocal.getTime() * timeBeanLocal.getTime())));
		positionBeanY.setValue(y + high);
		if ((v + damping) >= 0) {
			positionBeanY.setValue(y);
			v = 0;
		}
		if ((int) (y + high) > y && v < 0) {
			// this resets the time of the system to zero everytime the ball
			// hits the floor
			resetLocalTime();
			if (v < 0) {
				v = v + damping;
			}
			if (xspeed > 0) {
				xspeed -= 0.3;
				if (xspeed < 0) {
					xspeed = 0;
				}
			} else {
				xspeed = 0;
			}

		} else {
			// this if/else prevents it from rendering the ball if its below the
			// floor
			repaint();

			// repaint() is what acutally handles the drawing and it calls on
			// update()
			// automatically to create a double buffer to reduce image flicker
			// significantly
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		// Put anything here and it will render on screen (this includes images)
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, 900, 100);
		g.setColor(Color.white);
		g.fillOval((int) positionBeanX.getValue(), (int) positionBeanY.getValue(), 20, 20);
		g.setColor(Color.red);
		g.fillRect(0, 450, 50, 50);
		g.drawString("" + (y - (int) (y + high)), 200, 10);
		g.drawString("" + timeBeanLocal.getTime(), 200, 40);

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Ball Y", positionBeanY.getSimVariableBeanProperty()},
												       {"Ball X", positionBeanX.getSimVariableBeanProperty()},};
	
		data_shared_read = new Object[0][0] ; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

