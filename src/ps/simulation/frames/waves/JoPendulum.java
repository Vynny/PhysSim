package ps.simulation.frames.waves;

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
	double damping = 0.01;
	int originx = 0;
	int originy = 0;
	int framesstopped;
	int offsetX = 310;
	int offsetY = 100;
	
	//READ VALUES
	SimVariableBean xposBean = new SimVariableBean();
	SimVariableBean yposBean = new SimVariableBean();
	SimVariableBean dampingBean = new SimVariableBean();
	
	//WRITTEN VALUES
	SimVariableBean lengthBean = new SimVariableBean();
	SimVariableBean anglemaxBean = new SimVariableBean();
	
	public JoPendulum() {
		xposBean.setValue(x);
		yposBean.setValue(y);
		lengthBean.setValue(length);
		anglemaxBean.setValue(angleMax);
		dampingBean.setValue(damping);
	}

	public void animationLogic() {

		if (anglemaxBean.getValue() == 0) {
			framesstopped++;
		}
		if (framesstopped >= 30) {
			stop();
		}

		anglemaxBean.setValue(FPendulum.damping(anglemaxBean.getValue(), timeBeanLocal.getTime(), dampingBean.getValue()));

		angle = FPendulum.AngleP(anglemaxBean.getValue(), g, length, timeBeanLocal.getTime());
		xposBean.setValue(FPendulum.PendulumX(lengthBean.getValue(), angle));
		yposBean.setValue(FPendulum.PendulumY(lengthBean.getValue(), angle));

		originx = FPendulum.PendulumX(lengthBean.getValue(), 0);
		originy = FPendulum.PendulumY(lengthBean.getValue(), 0) - (int) lengthBean.getValue();

		repaint();
		
	}
	
	public void calculateStringLength() {
		angle = FPendulum.AngleP(anglemaxBean.getValue(), g, length, timeBeanLocal.getTime());
		xposBean.setValue(FPendulum.PendulumX(lengthBean.getValue(), angle));
		yposBean.setValue(FPendulum.PendulumY(lengthBean.getValue(), angle));
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if (iterationCount == 0) {
			calculateStringLength();
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition() , 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition() , -(500 - PhysicsWindow.getBottomSlidePosition()) );
		g.setColor(Color.white);
		g.fillOval((int) xposBean.getValue() + (offsetX - 10), (int) yposBean.getValue() + offsetY, 20, 20);
		g.drawLine(originx + offsetX, originy + offsetY, (int) xposBean.getValue() + offsetX, (int) yposBean.getValue() + offsetY);
	}

	public void LoadData() {

		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty() } };

		data_shared_write_dependant = new Object[][] { {"X Position", xposBean.getSimVariableBeanProperty() },
													   {"Y Position", yposBean.getSimVariableBeanProperty() } };

		data_shared_read = new Object[][] { { "String Length_0:550", lengthBean.getSimVariableBeanProperty() },
											{ "Starting Angle_0:90", anglemaxBean.getSimVariableBeanProperty() },
											{ "Damping_0:1", dampingBean.getSimVariableBeanProperty() }};

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

}
