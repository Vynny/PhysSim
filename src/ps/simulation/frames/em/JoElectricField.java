package ps.simulation.frames.em;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.simulation.formulae.FElectricField;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoElectricField extends SimulatorInstanceSwing {

	int x = 0;
	int y = 0;
	int SideLength = 500;
	int width = 10;

	// variable between 111-479 INPUT
	int xStart = 330;
	int yStart = 100;
	double gravity = 9.8 * 200;

	// Particle mass INPUT
	double mass = 4;

	// Particle charge INPUT
	double charge = 2;

	// Direction negative for <- INPUT
	int direction = -1;

	// Electric Field Strength INPUT
	double ElectricField = 100;

	// Calculated Velocity GRAPH
	double velocityx;

	// Modifiable, entrance speed INPUT
	int velocity = 800;

	//WRITTEN
    SimVariableBean positionBeanX = new SimVariableBean();
	SimVariableBean positionBeanY = new SimVariableBean();
	SimVariableBean velocityBean = new SimVariableBean();
	
	//READ
	SimVariableBean xStartBean = new SimVariableBean();
	SimVariableBean chargeBean = new SimVariableBean();
	SimVariableBean efBean = new SimVariableBean();
	SimVariableBean viBean = new SimVariableBean();

	
	public JoElectricField() {
		xStartBean.setValue(xStart);
		chargeBean.setValue(charge);
		efBean.setValue(ElectricField);
		viBean.setValue(velocity);
	}

	public void animationLogic() {

		velocityx = ((chargeBean.getValue() * efBean.getValue()) / mass) * timeBeanLocal.getTime();
		y = (int) (viBean.getValue() * timeBeanLocal.getTime());
		x = (int) (direction * FElectricField.xPos(velocityx, timeBeanLocal.getTime(), gravity));
		positionBeanX.setValue(x);
		positionBeanY.setValue(y);
		velocityBean.setValue(velocityx);

		repaint();

		if (x + xStart <= 110) {
			System.out.println("Stopped");
			stop();
		}
		if (x + xStart >= 480) {
			System.out.println("Stopped");
			stop();
		}
		if (y + yStart >= 600) {
			System.out.println("Stopped");
			stop();
		}

	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition() , 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition() , -(500 - PhysicsWindow.getBottomSlidePosition()) );
		g.setColor(Color.white);
		g.fillRect(100,100,width,SideLength);
		g.fillRect(500,100,width,SideLength);
		g.setColor(Color.red);
		g.fillOval((int)(x + xStartBean.getValue()),y+yStart,20,20);
		
	}
	
	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Particle X", positionBeanX.getSimVariableBeanProperty()},
												       {"Particle Y", positionBeanY.getSimVariableBeanProperty()},
												       {"Particle Velocity", velocityBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][]  { {"Start X_111:479", xStartBean.getSimVariableBeanProperty()},
											 {"EF Strength_0:1055", efBean.getSimVariableBeanProperty()},
											 {"Initial Speed_1:1500", viBean.getSimVariableBeanProperty()},
											 {"Charge_1:10", chargeBean.getSimVariableBeanProperty()}}; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

}


