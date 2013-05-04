package ps.simulation.frames;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.simulation.formulae.FPendulum;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoPendulum extends SimulatorInstanceSwing {

	double y = 500;
	double x = 40;
	double high = 0;
	double g = 9.8 * 200;
	double length = 200;
	double angle = 0;
	double angleMax = 45;
	int originx = 0;
	int originy = 0;
	int framesstopped;
	
	//READ VALUES
	private SimVariableBean xposBean = new SimVariableBean();
	private SimVariableBean yposBean = new SimVariableBean();
	
	//WRITTEN VALUES
	private SimVariableBean lengthBean = new SimVariableBean();
	private SimVariableBean anglemaxBean = new SimVariableBean();
	
	public JoPendulum() {
		xposBean.setValue(x);
		yposBean.setValue(y);
		lengthBean.setValue(length);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, 900, 100);
		g.setColor(Color.white);
		g.fillOval((int) xposBean.getValue() + 200, (int) yposBean.getValue() + 100, 20, 20);
		g.drawLine(originx + 210, originy + 100, (int) xposBean.getValue() + 210, (int) yposBean.getValue() + 110);
	}

	public void animationLogic() {

		if (anglemaxBean.getValue() == 0) {
			framesstopped++;
		}
		if (framesstopped > 500) {
			stop();
		}

		anglemaxBean.setValue(FPendulum.damping(angleMax, timeBeanLocal.getTime()));

		angle = FPendulum.AngleP(anglemaxBean.getValue(), g, length, timeBeanLocal.getTime());
		xposBean.setValue(FPendulum.PendulumX(lengthBean.getValue(), angle));
		yposBean.setValue(FPendulum.PendulumY(lengthBean.getValue(), angle));

		originx = FPendulum.PendulumX(lengthBean.getValue(), 0);
		originy = FPendulum.PendulumY(lengthBean.getValue(), 0) - (int) lengthBean.getValue();

		repaint();

	}

	public void LoadData() {

		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty() } };

		data_shared_write_dependant = new Object[][] { {"X Position", xposBean.getSimVariableBeanProperty() },
													   {"Y Position", yposBean.getSimVariableBeanProperty() } };

		data_shared_read = new Object[][] { { "String Length", lengthBean.getSimVariableBeanProperty() },
											{ "Starting Angle", anglemaxBean.getSimVariableBeanProperty() } };

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

}
