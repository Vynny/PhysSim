package ps.simulation.frames.waves;

import java.awt.Color;
import java.awt.Graphics;

import ps.simulation.formulae.FSHM;

public class JoWOSBall {
	
	double x = 0;
	double y = 0;
	double dy = 0;

	public JoWOSBall(double xpos, double ypos) {
		x = xpos;
		y = ypos;
	}

	public void paint(Graphics g, double time, double amplitude, double omega, double radius, int i) {
		
		dy = FSHM.SHMY(amplitude, omega, time / 1.5, -0.05 * i);
		// dy = FSHM.SHMY(amplitude, omega, time,0);
		
		if (i % 8 == 0) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}
		
		g.fillOval((int) x, (int) dy + 300, (int) radius, (int) radius);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}
}
