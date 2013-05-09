package ps.simulation.frames.em;

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.system.api.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoCyclotron extends SimulatorInstanceSwing {

	int position = 0;
	int size = 10;
	double x = 600;
	double y = 250;
	double centerx = 200;
	double centery = 200;
	double radius = 50;
	int r1cx = 190;
	int r1cy = 250;
	int r2cx = 600;
	int r2cy = 270;
	int v = 0;
	boolean part1 = true;
	boolean part2 = false;
	boolean part3 = false;
	boolean part4 = false;
	boolean reset1 = true;
	boolean reset2 = true;
	boolean reset3 = true;
	boolean reset4 = true;
	int b1x = 100;
	int b1y = 150;
	int b2x = 600;
	int b2y = 170;
	int width = 100;
	int height = 200;
	int sMult = 10;
	
	//WRITTEN
	SimVariableBean velocityBean = new SimVariableBean();

	//READ
	SimVariableBean multBean = new SimVariableBean();


	public JoCyclotron() {
		multBean.setValue(sMult);
	}

	public void animationLogic() {

		if (part1) {
			System.out.println("part1");
			//System.out.println("RESET1: " + reset1 + " " + x + " b2x: " + b2x);
			if (reset1) {
				resetLocalTime();
				radius = r2cy - y;
			}

			x = (int) (radius * Math.cos(sMult *  timeBeanLocal.getTime() + 3 * (Math.PI) / 2) + r2cx);
			y = (int) (radius * Math.sin(sMult *  timeBeanLocal.getTime() + 3 * (Math.PI) / 2) + r2cy);
			if (x < b2x) {
				System.out.println("poop");
				System.out.println("x="+x);
				part1 = false;
				part2 = true;
			}
			reset1 = false;
			reset2 = true;
			repaint();
		}
		
		if (part2) {
			System.out.println("part2");
			if (reset2) {
				resetLocalTime();
			}
			v = (int) ((2 * Math.PI * radius) / 6);
			x = b2x - (v * (sMult * timeBeanLocal.getTime()));
			if (x < (b1x + width) && y < (b1y + height) && y > (b1y + (height / 2))) {
				part2 = false;
				part3 = true;
			}
			//if (x <= 0) {
			//	System.out.println("full stop kykes");
			//	stop();
			//}
			reset2 = false;
			reset3 = true;
			repaint();
		}
		
		if (part3) {
			System.out.println("part3");
			if (reset3) {
				resetLocalTime();
				radius = y - r1cy;
			}
			x = (int) (radius * Math.cos(sMult *  timeBeanLocal.getTime() + (Math.PI) / 2) + r1cx);
			y = (int) (radius * Math.sin(sMult *  timeBeanLocal.getTime() + (Math.PI) / 2) + r1cy);
			if (x > (b1x + width)) {
				part3 = false;
				part4 = true;
			}
			reset3 = false;
			reset4 = true;
			System.out.println("part1= "+part1+"part2= "+part2+"part 3 = "+part3+"part4= "+part4);
			repaint();
		}
		
		if (part4) {
			System.out.println("part 4");
			System.out.println("x4 "+x);
			if (reset4) {
				resetLocalTime();
			}
			v = (int) ((2 * Math.PI * radius) / 6);
			x = (b1x + width) + v * (sMult * timeBeanLocal.getTime());
			if (x > b2x && y > b2y && y < (b2y + (height / 2))) {
				part4 = false;
				part1 = true;
			}
			if (x > 2000) {
				System.out.println("full stop niggers");
				stop();
			}
			reset4 = false;
			reset1 = true;
			repaint();
		}
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
	
		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition() , 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition() , -(500 - PhysicsWindow.getBottomSlidePosition()) );
		
		g.setColor(Color.red);	
		g.drawRect(b1x, b1y, width, height);
		g.drawRect(b2x, b2y, width, height);
		
		g.fillOval(r1cx, r1cy, size, size);
		g.fillOval(r2cx, r2cy, size, size);
		
		g.setColor(Color.white);
		g.fillOval((int)x,(int)y, size,  size);
		
	}

	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Particle Velocity", velocityBean.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][] { {"Multiplier_0:30", multBean.getSimVariableBeanProperty()}};

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}