package ps.simulation.frames;

import java.awt.Color;
import java.awt.Graphics;
import ps.system.api.SimulatorInstanceSwing;

public class JoBOS extends SimulatorInstanceSwing {


	int position = 0;
	double g = 9.8 * 200;
	double angle = 45;
	double size = 10;
	int x;
	int y;
	int lineStartX = 200;
	int lineStartY = 400;
	int lineLength =500;
	int lineFinalX;
	int lineFinalY;
	int length = 30;
	int height = 25;
	int[] ypos = new int[4];
	int[] xpos = new int[4];
	double hyp;
	
	public void init() {
		lineFinalX=lineStartX+(int)(lineLength*(Math.cos(Math.toRadians(angle))));
		lineFinalY = lineStartY-(int)(lineLength*(Math.sin(Math.toRadians(angle))));
		x = lineFinalX;
		y = lineFinalY;
		
		xpos[0]=lineFinalX;
		xpos[1]=lineFinalX-(int)(length*(Math.cos(Math.toRadians(angle))));
		xpos[2]=xpos[1]-(int)(height*(Math.sin(Math.toRadians(angle))));
		xpos[3]=lineFinalX-(int)(height*(Math.sin(Math.toRadians(angle))));
		
		ypos[0]=lineFinalY;
		ypos[1]=lineFinalY+(int)(length*(Math.sin(Math.toRadians(angle))));
		ypos[2]=ypos[1]-(int)(height*(Math.cos(Math.toRadians(angle))));
		ypos[3]=lineFinalY-(int)(height*(Math.cos(Math.toRadians(angle))));
		
		this.setFocusable(true);
		this.setSize(400, 400);
	}

	public void paint(Graphics g) {
		
		// Put anything here and it will render on screen (this includes images)
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.darkGray);
		g.fillRect(0, 500, 900, 100);
		g.setColor(Color.WHITE);
		g.drawLine(lineStartX,lineStartY,lineFinalX,lineFinalY);
		g.drawLine(lineStartX, lineStartY, lineStartX+lineLength, lineStartY);
		g.fillPolygon(xpos, ypos, 4);

	}
	
	
	public void run() {

		while (true) {
			xpos[0]=x;
			xpos[1]=x-(int)(length*(Math.cos(Math.toRadians(angle))));
			xpos[2]=xpos[1]-(int)(height*(Math.sin(Math.toRadians(angle))));
			xpos[3]=x-(int)(height*(Math.sin(Math.toRadians(angle))));
			
			ypos[0]=y;
			ypos[1]=y+(int)(length*(Math.sin(Math.toRadians(angle))));
			ypos[2]=ypos[1]-(int)(height*(Math.cos(Math.toRadians(angle))));
			ypos[3]=y-(int)(height*(Math.cos(Math.toRadians(angle))));
			
			x = x - (int) (9.8*Math.sin(Math.toRadians(angle))*0.5*timeBeanLocal.getTime()*timeBeanLocal.getTime());
			y = lineStartY -(int) ((x-lineStartX)*(Math.tan(Math.toRadians(angle))));
			if(xpos[1] < lineStartX){
				stop();
			}
			
			repaint();
		}

	}

	// Double buffer
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