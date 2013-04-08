package ps.simulation.frames;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoBounceBall extends SimulatorInstanceSwing {

	/*
	 * --------------- VARIABLES ---------------
	 * 
	 */

	int position = 0;
	double y = 500;
	double x = 40;
	double v = -700;
	double high = 0;
	double g = 9.8 * 200;
	double damping = 90;
	double xspeed = 1;
	int framesOnGround;
	
	
	SimVariableBean positionBean = new SimVariableBean();
	
	
	public JoBounceBall() {	
		positionBean.setValue(y);
	}

	public void animationLogic() {

		System.out.println("y: " + positionBean.getValue());
		
		if ((int) (y + high) >= y) {
			framesOnGround++;
			if (framesOnGround >= 7) {
				xspeed = 0;
			}
		} else {
			framesOnGround = 0;
		}
		x = x + xspeed;
		
		high = (v * timeBeanLocal.getTime() + (0.5 * g * (timeBeanLocal.getTime() * timeBeanLocal.getTime())));
		positionBean.setValue((int) (y + high));
		
		if ((v + damping) >= 0) {
			positionBean.setValue((int) y);
			v = 0;
		}
		
		if ((int) (y + high) > y && v < 0) {
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
			System.out.println("xspeed " + xspeed);

		} else {
			repaint();
			System.out.println("v " + v);
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
		g.fillOval(200, (int) positionBean.getValue(), 30, 30);
		g.drawString("yPos: " + positionBean.getValue(), 200, 40);
		g.drawString("SSS: " + secondsCurrent, 200, 70);
		g.drawString("Anim Time: " + timeBean.getTime(), 200, 100);

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Ball Y", positionBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][] { {"Ball Y", positionBean.getSimVariableBeanProperty()}};

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

