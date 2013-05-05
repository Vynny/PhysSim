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

	private double angle = 30;
	private double y = 500;
	private double x = 40;
	private double v = -1100;
	private double vinit = -1000;
	private double high = 0;
	private double g = 9.8 * 200;
	private double damping = 90;
	private double xspeed = 6;
	private int framesOnGround;
	
	private SimVariableBean positionBeanX = new SimVariableBean();
	private SimVariableBean positionBeanY = new SimVariableBean();
	private SimVariableBean angleBean = new SimVariableBean();
	
	private SimVariableBean vinitBean = new SimVariableBean();

	
	
	public JoProjectileMotion() {
		positionBeanY.setValue(y);
		positionBeanX.setValue(x);
		angleBean.setValue(angle);
		vinitBean.setValue(vinit);
	}

	public void runOnce() {
		xspeed = -(vinitBean.getValue() * Math.cos(Math.toRadians(angleBean.getValue())))/100;
		v = (vinitBean.getValue() * Math.sin(Math.toRadians(angleBean.getValue())));
	}
	
	public void animationLogic() {
		
		if ((int) (positionBeanY.getValue() + high) >= positionBeanY.getValue()) {
			framesOnGround++;
			if (framesOnGround >= 7) {
				xspeed = 0;
			}
		} else {
			framesOnGround = 0;
		}
		positionBeanX.setValue(positionBeanX.getValue() + xspeed);
		
		high = (v * timeBeanLocal.getTime() + (0.5 * g * (timeBeanLocal.getTime() * timeBeanLocal.getTime())));
		positionBeanY.setValue(y + high);
		
		if ((v + damping) >= 0) {
			positionBeanY.setValue(y);
			v = 0;
		}
		
		if ((int) (positionBeanY.getValue() + high) > positionBeanY.getValue() && v < 0) {
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
	

