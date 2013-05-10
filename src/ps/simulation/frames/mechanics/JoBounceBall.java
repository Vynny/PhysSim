package ps.simulation.frames.mechanics;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.simulation.formulae.FConvert;
import ps.system.core.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoBounceBall extends SimulatorInstanceSwing {

	/*
	 * --------------- VARIABLES ---------------
	 * 
	 */

	double yFloor = 500;
	double yHigh = 0;
	int yPosition = (int)yFloor;
	double xPosition = 40;
	double vLaunch = -700;
	double g = 9.8 * 200;
	double damping = 90;
	double xspeed = 1;
	int framesOnGround;
	
	//READ
	SimVariableBean positionBean = new SimVariableBean();
	
	//WRITTEN
	SimVariableBean velocityBean = new SimVariableBean();
	SimVariableBean dampingBean = new SimVariableBean();
	
	public JoBounceBall() {	
		dampingBean.setValue(damping);
		positionBean.setValue(yFloor);
		velocityBean.setValue(vLaunch);
	}

	public void animationLogic() {
		
		if ((int) (yFloor + yHigh) >= yFloor) {
			framesOnGround++;
			if (framesOnGround >= 7) {
				xspeed = 0;
				stop();
			}
		} else {
			framesOnGround = 0;
		}
		xPosition = xPosition + xspeed;
		
		yHigh = (velocityBean.getValue() * timeBeanLocal.getTime() + (0.5 * g * (timeBeanLocal.getTime() * timeBeanLocal.getTime())));
		yPosition = (int) (yFloor + yHigh);
		positionBean.setValue(FConvert.coord(yFloor, yPosition));
		
		if ((velocityBean.getValue() + dampingBean.getValue()) >= 0) {
			yPosition = (int)yFloor;
			velocityBean.setValue(0);
			stop();
		}
		
		if ((int) (yFloor + yHigh) > yFloor && velocityBean.getValue() < 0) {
			resetLocalTime();
			
			if (velocityBean.getValue() < 0) {
				velocityBean.setValue(velocityBean.getValue() + dampingBean.getValue());
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
		g.fillOval(200, (int) yPosition, 30, 30);
		g.drawString("yPos: " + positionBean.getValue(), 200, 40);
		g.drawString("SSS: " + secondsCurrent, 200, 70);

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Ball Y", positionBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][] { {"Launch Velocity_0:-2000", velocityBean.getSimVariableBeanProperty()},
											{"Damping_0:90", dampingBean.getSimVariableBeanProperty()}};

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

