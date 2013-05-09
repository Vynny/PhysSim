package ps.system.api;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import ps.logic.beans.TimeBean;
import ps.system.main.PhysicsWindow;
import ps.system.main.SystemConstants;

public class SimulatorInstanceSwing extends JPanel implements Runnable {

	/*---------------------------------------------------------------------
								VARIABLE INIT
	 *-------------------------------------------------------------------*/

	// Graphics Containers
	protected Image dbImage;
	protected Graphics dbGraphics;

	// Time variables
	
	//GLOBAL Time Bean
	protected TimeBean timeBean = new TimeBean();
	protected boolean timeRUNNING = false;
	//INSTANCE Time Bean: Can be reset without affecting graph data
	protected TimeBean timeBeanLocal = new TimeBean();
	protected boolean timeLocalRUNNING = false;

	protected double secondsInit = 0;
	protected double secondsCurrent = 0;
	
	protected double secondsInitLocal = 0;
	protected double secondsCurrentLocal = 0;
	
	protected double timeAtPause = 0;

	// Main Animation Thread
	protected Thread anim;
	protected boolean RUNNING = false;
	public boolean isRUNNING() {
		return RUNNING;
	}
	protected int iterationCount = 0;
	protected int pauseCount = 0;

	// Data to load DataStore hashmaps with (for io between program components)
	public Object[][] data_shared_read;
	public Object[][] data_shared_write_independant;
	public Object[][] data_shared_write_dependant;
	
	//DEBUG BOOLEANS
	boolean timePrint = true;
	

	/*---------------------------------------------------------------------
								CONSTRUCTOR(S)
	 *-------------------------------------------------------------------*/

	public SimulatorInstanceSwing() {
		init();
	}

	/*---------------------------------------------------------------------
							THREAD SPECIFIC METHODS
	 *-------------------------------------------------------------------*/

	public void init() {
		
	}

	// Standard thread start
	public void start() {
		if (anim == null && pauseCount == 0) {
			System.out.println("FIRST START");
			
			//Time init
			resetTime();
			startTime();
			resetLocalTime();
			startLocalTime();
			
			//Thread stuff
			RUNNING = true;
			anim = new Thread(this);
			anim.start();
		} else if (anim == null && pauseCount > 0) {
			System.out.println("PAUSE START");
			RUNNING = true;
			anim = new Thread(this);
			secondsInit = (System.currentTimeMillis() - timeAtPause) + secondsInit;
			secondsInitLocal = (System.currentTimeMillis() - timeAtPause) + secondsInitLocal;
			anim.start();
		}
	}
	
	//Standard thread pause
	public void pause() {
		System.out.println("ANIMATION PAUSED");
		if (anim != null) {
			timeAtPause = System.currentTimeMillis();
			RUNNING = false;
			anim = null;
			pauseCount++;
		}
	}

	// Standard thread stop
	public void stop() {
		System.out.println("ANIMATION STOPPED");
		if (anim != null) {
			RUNNING = false;
			anim = null;
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
	
		while (RUNNING) {
			
			System.out.println("RUNNING");
			if (iterationCount == 0) {
				runOnce();
				iterationCount++;
			}

			if (timeRUNNING && timeLocalRUNNING) {
				secondsCurrent = System.currentTimeMillis() - secondsInit;
				secondsCurrentLocal = System.currentTimeMillis() - secondsInitLocal;
				
				timeBean.setTime(secondsCurrent / 1000); 
				timeBeanLocal.setTime(secondsCurrentLocal / 1000);
			} else if (!timeRUNNING) {
				secondsCurrentLocal = System.currentTimeMillis() - secondsInitLocal;
				timeBeanLocal.setTime(secondsCurrentLocal / 1000);
			} else if (!timeLocalRUNNING) {
				secondsCurrent = System.currentTimeMillis() - secondsInit;
				timeBean.setTime(secondsCurrent / 1000); 
			}
			
			if (timePrint) {
				System.out.println("TimeCur: " + timeBean.getTime());
				System.out.println("TimeCurLoc: " + timeBeanLocal.getTime());
			}

			//Perform simulation specific logic at each thread iteration
			animationLogic();
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
	
	public void runOnce() {
		
	}
	
	
	/*---------------------------------------------------------------------
	|  Method - animationLogic
	|
	|  Purpose:  Instance specific graphics
	|
	 *-------------------------------------------------------------------*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void startTime() {
		timeRUNNING = true;
	}
	
	public void stopTime() {
		timeRUNNING = false;
	}
	
	public void resetTime() {
		secondsInit = System.currentTimeMillis();
	}

	public void startLocalTime() {
		timeLocalRUNNING = true;
	}
	
	public void stopLocalTime() {
		timeLocalRUNNING = false;
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
		//Add Universal Time
		data_shared_write_independant = new Object[][] { {"Time", timeBean} };
		
		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

	public void UnLoadData() {
		//Terminate animation thread
		stop();
		
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
