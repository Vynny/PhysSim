

import java.awt.Color;
import java.awt.Graphics;

import ps.logic.beans.SimVariableBean;
import ps.simulation.formulae.FConvert;
import ps.system.core.SimulatorInstanceSwing;
import ps.system.main.PhysicsWindow;

public class JoBOS extends SimulatorInstanceSwing {

	double g = 9.8 * 200;
	double angle = 45;
	double size = 10;
	int x;
	int y;
	int lineStartX = 200;
	int lineStartY = 499;
	int lineLength = 500;
	int lineFinalX;
	int lineFinalY;
	int length = 30;
	int height = 25;
	int[] ypos = new int[4];
	int[] xpos = new int[4];
	double hyp;
	
	//WRITTEN
    SimVariableBean positionBeanX = new SimVariableBean();
	SimVariableBean positionBeanY = new SimVariableBean();

	//READ
	SimVariableBean angleBean = new SimVariableBean();
	SimVariableBean linelenBean = new SimVariableBean();
	
	
	public JoBOS() {
		linelenBean.setValue(lineLength);
	}

	public void animationLogic() {

		xpos[0] = x;
		xpos[1] = x - (int) (length * (Math.cos(Math.toRadians(angle))));
		xpos[2] = xpos[1] - (int) (height * (Math.sin(Math.toRadians(angle))));
		xpos[3] = x - (int) (height * (Math.sin(Math.toRadians(angle))));
		positionBeanX.setValue(x);

		ypos[0] = y;
		ypos[1] = y + (int) (length * (Math.sin(Math.toRadians(angle))));
		ypos[2] = ypos[1] - (int) (height * (Math.cos(Math.toRadians(angle))));
		ypos[3] = y - (int) (height * (Math.cos(Math.toRadians(angle))));
		positionBeanY.setValue(FConvert.coord(lineStartY, y));

		x = x  - (int) (9.8 * Math.sin(Math.toRadians(angle)) * 0.5 * timeBeanLocal.getTime() * timeBeanLocal.getTime());
		y = lineStartY - (int) ((x - lineStartX) * (Math.tan(Math.toRadians(angle))));
		if (xpos[1] < lineStartX) {
			stop();
		}

		repaint();
	}
	
	public void calculateSlope() {
		angle = angleBean.getValue();
		
		lineFinalX = lineStartX + (int) (linelenBean.getValue() * (Math.cos(Math.toRadians(angle))));
		lineFinalY = lineStartY - (int) (linelenBean.getValue() * (Math.sin(Math.toRadians(angle))));
		x = lineFinalX;
		y = lineFinalY;

		xpos[0] = lineFinalX;
		xpos[1] = lineFinalX - (int) (length * (Math.cos(Math.toRadians(angle))));
		xpos[2] = xpos[1] - (int) (height * (Math.sin(Math.toRadians(angle))));
		xpos[3] = lineFinalX - (int) (height * (Math.sin(Math.toRadians(angle))));

		ypos[0] = lineFinalY;
		ypos[1] = lineFinalY + (int) (length * (Math.sin(Math.toRadians(angle))));
		ypos[2] = ypos[1] - (int) (height * (Math.cos(Math.toRadians(angle))));
		ypos[3] = lineFinalY - (int) (height * (Math.cos(Math.toRadians(angle))));
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		if (iterationCount == 0) {
			calculateSlope();
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, PhysicsWindow.getTopSlidePosition() , 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, PhysicsWindow.getTopSlidePosition() , -(500 - PhysicsWindow.getBottomSlidePosition()) );
		g.setColor(Color.WHITE);
		g.drawLine(lineStartX,lineStartY,lineFinalX,lineFinalY);
		g.drawLine(lineStartX, lineStartY, lineStartX+lineLength, lineStartY);
		g.fillPolygon(xpos, ypos, 4);

	}
	
	public void LoadData() {
		
		data_shared_write_independant = new Object[][] { {"Time", timeBean.getTimeProperty()} };
		
		data_shared_write_dependant = new Object[][] { {"Ball X", positionBeanX.getSimVariableBeanProperty()},
												       {"Ball Y", positionBeanY.getSimVariableBeanProperty()}};
	
		data_shared_read = new Object[][]  { {"Angle_0:90", angleBean.getSimVariableBeanProperty()},
											 {"Slope Length_0:1055", linelenBean.getSimVariableBeanProperty()}}; 

		// Data Read by sim
		PhysicsWindow.sharedData.addReadData(data_shared_read);

		// Data Written by sim
		PhysicsWindow.sharedData.addWriteDataSwing(data_shared_write_independant, data_shared_write_dependant);
	}

}