package ps.simulation.frames.mechanics;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.simulation.formulae.FConvert;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoProjectileMotion extends SimulatorInstanceSwing {

	/*
	 * --------------- VARIABLES ---------------
	 * 
	 */

	double angle = 30;
	double yMax = 500;
	double yPosition = yMax;
	double xPosition = 40;
	double v = -1100;
	double vinit = -1000;
	double high = 0;
	double g = 9.8 * 200;
	double damping = 90;
	double xspeed = 6;
	int framesOnGround;
	
	SimVariableBean positionBeanX = new SimVariableBean();
	SimVariableBean positionBeanY = new SimVariableBean();
	SimVariableBean angleBean = new SimVariableBean();
	
	SimVariableBean vinitBean = new SimVariableBean();

	
	
	public JoProjectileMotion() {
		positionBeanY.setValue(yMax);
		positionBeanX.setValue(xPosition);
		angleBean.setValue(angle);
		vinitBean.setValue(vinit);
	}

	public void runOnce() {
		xspeed = -(vinitBean.getValue() * Math.cos(Math.toRadians(angleBean.getValue())))/100;
		v = (vinitBean.getValue() * Math.sin(Math.toRadians(angleBean.getValue())));
	}
	
	public void animationLogic() {
		
		if ((int) (yMax + high) >= yMax) {
			framesOnGround++;
			if (framesOnGround >= 7) {
				xspeed = 0;
				stop();
			}
		} else {
			framesOnGround = 0;
		}
		xPosition  = xPosition + xspeed;
		positionBeanX.setValue(xPosition);
		
		high = (v * timeBeanLocal.getTime() + (0.5 * g * (timeBeanLocal.getTime() * timeBeanLocal.getTime())));
		yPosition = (int)(yMax+high);
		positionBeanY.setValue(FConvert.coord(yMax, yPosition));
		
		if ((v + damping) >= 0) {
			yPosition = yMax;
			v = 0;
			stop();
		}
		
		if ((int) (yMax + high) > yMax && v < 0) {
			
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
			repaint();
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition() , 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition() , -(500 - PhysicsWindow.getBottomSlidePosition()) );
		g.setColor(Color.white);
		g.fillOval((int) xPosition, (int) yPosition, 20, 20);
		g.setColor(Color.red);
		g.fillRect(0, 450, 50, 50);
		g.drawString("" + (yMax - (int) (yMax + high)), 200, 10);
		g.drawString("" + timeBeanLocal.getTime(), 200, 40);

	}

	public void LoadData() {
			
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Ball X", positionBeanX.getSimVariableBeanProperty()},
												       {"Ball Y", positionBeanY.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][]  { {"Angle_1:90", angleBean.getSimVariableBeanProperty()},
											 {"Initial Velocity_0:-5000", vinitBean.getSimVariableBeanProperty()}}; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

