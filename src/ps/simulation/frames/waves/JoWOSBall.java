package ps.simulation.frames.waves;

import java.awt.Color;
import java.awt.Graphics;

import ps.simulation.formulae.FSHM;

public class JoWOSBall {
	
	private double x = 0;
	private double y = 0;
	private double dy = 0;
	private int modulo = 0;

	public JoWOSBall(double xpos, double ypos, int modulo) {
		this.x = xpos;
		this.y = ypos;
		this.modulo = modulo;
	}

	public void paint(Graphics g, double time, double amplitude, double omega, double radius, int i) {
		
		this.dy = FSHM.SHMY(amplitude, omega, time , -0.05 * i);
		
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
	
	public double getY() {
		return y;
	}
	
	public double getDy() {
		return dy;
	}

	public int getModulo() {
		return modulo;
	}
}
