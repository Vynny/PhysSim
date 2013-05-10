package ps.simulation.frames.waves;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import ps.logic.beans.SimVariableBean;
import ps.system.core.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoWOS extends SimulatorInstanceSwing {

	double y = 500;
	double omega = 2 * Math.PI;
	double amplitude = 100;
	int high = 0;
	int radius = 20;
	int nbBalls = 63;
	int modulo = 8;
	ArrayList<JoWOSBall> balls = new ArrayList<JoWOSBall>();
	
	//WRITTEN
	List<SimVariableBean> ballBeans = new ArrayList<SimVariableBean>();
	
	//READ
	SimVariableBean amplitudeBean = new SimVariableBean();
	SimVariableBean omegaBean = new SimVariableBean();

	public JoWOS() {
		amplitudeBean.setValue(amplitude);
		omegaBean.setValue(omega);
		
		for (int i = 0; i < nbBalls; i++) {
			if (i % 8 == 0) {
				ballBeans.add(new SimVariableBean());
			}
		}
	}

	public void animationLogic() {
		for (int i = 0; i < ballBeans.size(); i++) {
			ballBeans.get(i).setValue(-(balls.get(i * modulo).getDy()));
		}

		repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition(), 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition(), -(500 - PhysicsWindow.getBottomSlidePosition()));
		g.setColor(Color.white);
		for (int i = 0; i < nbBalls; i++) {
			balls.add(new JoWOSBall(i * radius, y, modulo));
			balls.get(i).paint(g, timeBeanLocal.getTime(), amplitudeBean.getValue(), omegaBean.getValue(), radius, i);
		}
		g.drawString("" + (y - (int) y ), 200, 10);

	}

	public void LoadData() {

		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[ ballBeans.size()][ 2 ];
	
		data_shared_read = new Object[][] {{"Amplitude", amplitudeBean.getSimVariableBeanProperty()},
										   {"Omega_" + "0:" + (2 * Math.PI), omegaBean.getSimVariableBeanProperty()}}; 

		for (int i = 0; i < data_shared_write_dependant.length ; i++) {
			data_shared_write_dependant[i][0] = ("Ball " + (i + 1));
			data_shared_write_dependant[i][1] = ballBeans.get(i).getSimVariableBeanProperty();
		}

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

}
