package ps.simulation.frames.waves;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.simulation.formulae.FConvert;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoSHM extends SimulatorInstanceSwing {

	/*
	 * --------------- VARIABLES ---------------
	 * 
	 */

	double y = 500;
	double x = 40;
	double v = -700;
	double high = 0;
	double g = 9.8 * 200;
	double damping = 90;
	double xspeed = 3;
	double length = 200;
	double angle = 0;
	double angleMax = 45;
	double subvar = 0;
	int originx = 0;
	int originy = 0;
	int framesstopped;
	double omega = 2 * Math.PI;
	double amplitude = 100;
	
	//WRITE
	SimVariableBean positionBean = new SimVariableBean();
	
	//READ
	SimVariableBean amplitudeBean = new SimVariableBean();
	SimVariableBean omegaBean = new SimVariableBean();
	
	public JoSHM() {	
		amplitudeBean.setValue(amplitude);
		omegaBean.setValue(omega);
	}

	public void animationLogic() {
	    
		y = amplitudeBean.getValue() * Math.cos(omegaBean.getValue() * timeBeanLocal.getTime());
		positionBean.setValue(FConvert.coord(500, y));

		repaint();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition() , 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition() , -(500 - PhysicsWindow.getBottomSlidePosition()) );
		g.setColor(Color.white);
		g.fillOval(300, (int) (y + 300), 20, 20);
		g.drawString("" + (y - (int) (y + high)), 200, 10);
		g.drawString("" + timeBeanLocal.getTime(), 200, 40);

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Y Position", positionBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][] {{"Amplitude_0:300", amplitudeBean.getSimVariableBeanProperty()},
									       { "Omega_0:" + 2 * Math.PI, omegaBean.getSimVariableBeanProperty()}} ; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

