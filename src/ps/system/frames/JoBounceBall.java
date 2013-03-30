package ps.system.frames;

import java.awt.Color;
import java.awt.Graphics;

import ps.system.api.JoFrame;
import ps.system.main.PhysicsWindow;

public class JoBounceBall extends JoFrame {

	/*
	 * --------------- VARIABLES ---------------
	 * 
	 * 
	 * -> g is scaled 300 times up to take into account how small the pixels are.
	 * other wise its like throwing a 30 diameter ball up in the air and looking
	 * at it from a distance
	 * 
	 */

	int position = 0;
	double y = 500;
	double v = -1300;
	double frame = 0;
	double high = 0;
	double g = 9.8 * 300;

	public JoBounceBall() {
		System.out.println("IN JOBOUNCEBALL");
		System.out.println("TIME: " + time);
		

		start();
	}

	public void animationLogic() {
		
	    high = (v * time + (0.5 * g * (time * time)));
		position = (int) (y + high);

		if ((int) (y + high) > y && v < 0) {
			// this resets the time of the system to zero every time the ball
			// hits the floor
			secondsInit = System.currentTimeMillis();
			System.out.println(secondsInit);

		} else {
			repaint();
		}
	} 


	public void paint(Graphics g) {

		// Put anything here and it will render on screen (this includes images)
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, 900, 100);
		g.setColor(Color.white);
		g.fillOval(200, position, 30, 30);
		g.drawString("" + (y - (int) (y + high)), 200, 10);
		g.drawString("" + secondsCurrent, 200, 40);

	}
	
	public void LoadData() {
		
		data_shared_write_dependant = new Object[][] { {"Ball", Marathoners[0].runnerNode()} };
	/*	
		data_shared_read = new Object[][]  { {"SIM_basetime", SIM_basetime}, 
											 {"SIM_distance", SIM_distance}}; 
*/
		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}
}
	

