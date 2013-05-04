package ps.simulation.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import ps.logic.beans.SimVariableBean;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoCircuit extends SimulatorInstanceSwing  {

	int position = 0;
	double y = 500;
	double x = 40;
	double v = -700;
	double time = 0;
	double frame = 0;
	double high = 0;
	double g = 9.8 * 200;
	double damping = 90;
	double xspeed = 3;
	double length = 200;
	double angle = 0;
	double angleMax = 45;
	double subvar = 0;
	int originx = 165;
	int originy = 235;
	int framesstopped;
	double omega = 2 * Math.PI;
	double amplitude = 100;
	ImageIcon CircuitIcon = new ImageIcon(getClass().getResource("/images/circuit.png"));
	Image circ = CircuitIcon.getImage();
	LinkedList<JoCharge> Zappa = new LinkedList<JoCharge>();
	int NumOfCharge;
	int current = 4;
	int regulator;
	int barHeight = 0;
	double Charge;
	double currentt;
	boolean cap = false;
	
	SimVariableBean chargeBean = new SimVariableBean();
	SimVariableBean currentBean = new SimVariableBean();
	

	public JoCircuit() {
		chargeBean.setValue(0);
		currentBean.setValue(0);
	}

	
	public void animationLogic() {
		
			frame++;
			if (frame > currentBean.getValue() && cap == true) {
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
					currentBean.setValue(currentBean.getValue() + 3);
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
					currentBean.setValue(currentBean.getValue() + 3);
					regulator = 0;
				}
			}

			int Io = 50;
			int Qo = 62;
			int R = 20;
			double C = 0.2;
			if (cap == false) {
				chargeBean.setValue(Qo * (1 - Math.pow((Math.E), (-timeBeanLocal.getTime()) / (R * C)))) ;
			} else {
				chargeBean.setValue(Qo * (Math.pow((Math.E), (-timeBeanLocal.getTime()) / (R * C))));
			}

			currentBean.setValue((int) (Qo * (1 - Math.pow((Math.E), (-timeBeanLocal.getTime()) / (R * C)))));
			System.out.println("time: " + timeBeanLocal.getTime());
			System.out.println("Charge " + chargeBean.getValue());

			if (chargeBean.getValue() > 60 && cap == false) {
				cap = true;
				secondsInit = System.currentTimeMillis();
			}

			repaint();
		}


	public void paintComponent(Graphics g) {

		// Put anything here and it will render on screen (this includes images)
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, 900, 100);

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
		g.fillRect(460, 210, 30, 70);
		g.setColor(Color.red);
		g.fillRect(464, 275 - (int) chargeBean.getValue(), 22, (int) chargeBean.getValue());

	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Capacitor Charge", chargeBean.getSimVariableBeanProperty()},
													   {"Circuit Current", currentBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[0][0] ; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

}
