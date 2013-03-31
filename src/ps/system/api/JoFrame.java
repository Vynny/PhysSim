package ps.system.api;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import ps.logic.beans.TimeBean;
import ps.system.main.PhysicsWindow;

public class JoFrame extends JPanel implements Runnable {

	/*---------------------------------------------------------------------
								VARIABLE INIT
	 *-------------------------------------------------------------------*/

	// Graphics Containers
	protected Image dbImage;
	protected Graphics dbGraphics;

	// Time variables
	protected TimeBean timeBean = new TimeBean();
	protected TimeBean timeBeanLocal = new TimeBean();

	protected double secondsInit = 0;
	protected double secondsCurrent = 0;
	
	protected double secondsInitLocal = 0;
	protected double secondsCurrentLocal = 0;

	// Main Animation Thread
	protected Thread anim;

	// Data to load DataStore hashmaps with (for io between program components)
	public Object[][] data_shared_read;
	public Object[][] data_shared_write_independant;
	public Object[][] data_shared_write_dependant;
	

	/*---------------------------------------------------------------------
								CONSTRUCTOR(S)
	 *-------------------------------------------------------------------*/

	public JoFrame() {
		init();
	}

	/*---------------------------------------------------------------------
							THREAD SPECIFIC METHODS
	 *-------------------------------------------------------------------*/

	public void init() {
		// Initialize start time of animation
		secondsInit = secondsInitLocal = System.currentTimeMillis(); 
	}

	// Standard thread start
	public void start() {
		if (anim == null) {
			anim = new Thread(this);
			anim.start();
		}
	}

	// Standard thread stop
	public void stop() {
		if (anim != null)
			try {
				anim.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/*---------------------------------------------------------------------
	|  Method - run
	|
	|  Purpose:  This superclass run takes care of the universal time
	|		     parameter and runs the instance specific logic.
	|      
	 *-------------------------------------------------------------------*/
	public void run() {

		while (true) {

			secondsCurrent = System.currentTimeMillis() - secondsInit;
			secondsCurrentLocal = System.currentTimeMillis() - secondsInitLocal;
			
			timeBean.setTime(secondsCurrent / 1000); 
			timeBeanLocal.setTime(secondsCurrentLocal / 1000); 

			animationLogic();

			// the 17 is 17 milliseconds of sleep between frames which is 60fps
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {

			}

		}

	}

	/*---------------------------------------------------------------------
							INSTANCE SPECIFIC METHODS
	 *-------------------------------------------------------------------*/

	/*---------------------------------------------------------------------
	|  Method - animationLogic
	|
	|  Purpose:  Instance specific logic
	|
	 *-------------------------------------------------------------------*/
	public void animationLogic() {

	}

	/*---------------------------------------------------------------------
	|  Method - animationLogic
	|
	|  Purpose:  Instance specific graphics
	|
	 *-------------------------------------------------------------------*/
	public void paint(Graphics g) {

	}
	
	public void resetLocalTime() {
		secondsInitLocal = System.currentTimeMillis();
	}

	
	/*---------------------------------------------------------------------
							DATASTORE LOADING METHODS
	 *-------------------------------------------------------------------*/
	
	public Object[][] getSharedReadData() {
		return this.data_shared_read;
	}
	
	public Object[][] getIndependantSharedWriteData() {
		return this.data_shared_write_independant;
	}
	
	public Object[][] getDependantSharedWriteData() {
		return this.data_shared_write_dependant;
	}
	
	public void LoadData() {
	/*	//Add Universal Time
		data_shared_write_independant = new Object[][] { {"Time", timeBean} };
		
		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);*/
	}

	public void UnLoadData() {
		// Data Read by sim
		PhysicsWindow.sharedData.clearReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.clearWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

	/*---------------------------------------------------------------------
									MISC
	 *-------------------------------------------------------------------*/

	/*---------------------------------------------------------------------
	|  Method - update
	|
	|  Purpose:  Double Buffer
	|
	 *-------------------------------------------------------------------*/
	public void update(Graphics g) {

		if (dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbGraphics = dbImage.getGraphics();
		}
		dbGraphics.setColor(this.getBackground());
		dbGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);

		dbGraphics.setColor(this.getForeground());
		paint(dbGraphics);

		g.drawImage(dbImage, 0, 0, this);
	}

}
