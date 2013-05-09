package ps.simulation.frames.em;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import ps.logic.beans.SimVariableBean;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoCircuit extends SimulatorInstanceSwing  {

	double y = 500;
	double x = 40;
	double v = -700;
	double frame = 0;
	double high = 0;
	int framesstopped;
	double omega = 2 * Math.PI;
	int NumOfCharge;
	int current = 4;
	int regulator;
	int barHeight = 0;
	double Charge;
	boolean cap = false;
	int Io = 50;
	int Qo = 62;
	int resistance = 20;
	double capacitance = 0.2;
	ImageIcon CircuitIcon = new ImageIcon(getClass().getResource("/images/circuit.png"));
	Image circ = CircuitIcon.getImage();
	LinkedList<JoCharge> Zappa = new LinkedList<JoCharge>();

	//WRITTEN
	SimVariableBean chargeBean = new SimVariableBean();
	
	//READ
	SimVariableBean resistanceBean = new SimVariableBean();
	SimVariableBean capacitanceBean = new SimVariableBean();
	

	public JoCircuit() {
		chargeBean.setValue(0);
		resistanceBean.setValue(resistance);
		capacitanceBean.setValue(capacitance);
	}

	
	public void animationLogic() {

		frame++;
		
		if (frame > current && cap == true) {
			JoCharge zap = new JoCharge(560, 240, true);
			Zappa.add(0, zap);
			if (NumOfCharge < 100) {
				NumOfCharge++;
			}
			frame = 0;
			if (barHeight < 61) {
				barHeight++;
			}
			regulator++;
			if (regulator > 1) {
				current += 3;
				regulator = 0;
			}
		}

		if (frame > current) {
			JoCharge zap = new JoCharge(165, 235, false);
			Zappa.add(0, zap);
			if (NumOfCharge < 100) {
				NumOfCharge++;
			}
			frame = 0;
			if (barHeight < 61) {
				barHeight++;
			}
			regulator++;
			if (regulator > 1) {
				current += 3;
				regulator = 0;
			}
		}

		
		if (cap == false) {
			Charge = Qo * (1 - Math.pow((Math.E), (-timeBeanLocal.getTime()) / (resistanceBean.getValue() * capacitanceBean.getValue())));
			chargeBean.setValue(Charge);
		} else {
			Charge = (Qo * (Math.pow((Math.E), (-timeBeanLocal.getTime()) / (resistanceBean.getValue() * capacitanceBean.getValue()))));
			chargeBean.setValue(Charge);
		}

		current = (int) (Qo * (1 - Math.pow((Math.E), (-timeBeanLocal.getTime()) / (resistanceBean.getValue() * capacitanceBean.getValue()))));

		if (Charge > 60 && cap == false) {
			cap = true;
			System.out.println("CAPPED HERE!!");
			resetLocalTime();
		}


		repaint();

	}


	public void paintComponent(Graphics g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition() , 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition() , -(500 - PhysicsWindow.getBottomSlidePosition()) );

		g.drawString("" + (y - (int) (y + high)), 200, 10);
		g.drawString("" + timeBean.getTime(), 200, 40);
		g.drawImage(circ, 99, 101, 599, 401, 0, 0, 560, 286, null);
		g.setColor(Color.BLACK);
		if (cap == false) {
			g.fillRect(370, 107, 70, 9);
		} else {
			g.fillRect(429, 107, 8, 70);
		}
		g.setColor(Color.CYAN);

		for (int i = NumOfCharge; i > 0; i--) {
			if (Zappa.get(i - 1).dead == true) {
				Zappa.remove(i - 1);
				NumOfCharge--;
			}
		}
		for (int i = NumOfCharge; i > 0; i--) {
			Zappa.get(i - 1).paint(g);
		}
		g.setColor(Color.darkGray);
		g.fillRect(490, 210, 30, 70);
		g.setColor(Color.red);
		g.fillRect(494, 275 - (int) Charge, 22, (int) Charge);

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Capacitor Charge", chargeBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][] { {"Capacitance_0:1", capacitanceBean.getSimVariableBeanProperty()},
											{"Resistance_0:50", resistanceBean.getSimVariableBeanProperty()}}; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

}
