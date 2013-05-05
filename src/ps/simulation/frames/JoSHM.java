package ps.simulation.frames;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
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
	double frame = 0;
	double high = 0;
	double secondsInit = 0;
	double secondsCurrent = 0;
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

	// TESTING
	public static int SIM_basetime = 5;
	public static double SIM_distance = 500;
	
	SimVariableBean positionBean = new SimVariableBean();
	SimVariableBean amplitudeBean = new SimVariableBean();
	
	public JoSHM() {	
		positionBean.setValue(y);
		amplitudeBean.setValue(amplitude);
	}

	public void animationLogic() {

		positionBean.setValue( amplitudeBean.getValue() * Math.cos(omega * timeBeanLocal.getTime()));

		repaint();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, 900, 100);
		g.setColor(Color.white);
		g.fillOval(300, (int) (positionBean.getValue() + 300), 20, 20);
		g.drawString("" + (y - (int) (y + high)), 200, 10);
		g.drawString("" + timeBeanLocal.getTime(), 200, 40);

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Amplitude", amplitudeBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[0][0] ; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

