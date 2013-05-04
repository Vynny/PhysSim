package ps.simulation.frames;

import java.awt.Graphics;

/**
 * 
 * @author Joey
 */
public class JoCharge {

	int frame = 0;
	int startx;
	int starty;
	boolean dead;
	boolean cap;

	public JoCharge(int startx, int starty, boolean cap) {

		this.startx = startx;
		this.starty = starty;
		this.cap = cap;

	}

	public void paint(Graphics g) {
		frame++;

		if (cap == false) {

			if (frame < 16) {
				starty -= 8;
			} else if (frame > 62 / 4 && frame < 261 / 4) {
				startx += 8;
			} else if (frame > 260 / 4 && frame < 400 / 4) {
				starty += 8;
			}
			if (frame > 401 / 4 && frame < 600 / 4) {
				startx -= 8;
			} else if (frame > 599 / 4 && frame < 665 / 4) {
				starty -= 8;
			} else if (frame > 665 / 4) {
				dead = true;
			}
			if (cap == true) {
				dead = true;
			}
		} else if (cap == true) {
			if (frame < 17) {
				starty -= 8;
			} else if (frame < 33) {
				startx -= 8;
			} else if (frame < 68) {
				starty += 8;
			} else if (frame < 84) {
				startx += 8;
			} else if (frame < 100) {
				starty -= 8;
			} else {
				dead = true;
			}
		}

		g.fillOval(startx - 7, starty - 10, 16, 16);

	}

}