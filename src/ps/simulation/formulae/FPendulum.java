package ps.simulation.formulae;

public class FPendulum {

	public static int PendulumX(double length, double angle){
		int x = 0;
		x = (int) (length *Math.cos(Math.toRadians(90-angle)));
		return x;
	}
	public static int PendulumY(double length, double angle){
		int y = 0;
		y = (int)(length*Math.sin(Math.toRadians(90-angle)));
		return y;
	}
	public static double AngleP(double angleMax, double g, double length, double time){
		double angle = 0;
		angle = angleMax * Math.sin(Math.sqrt(g / length) * time);
		return angle;
	}
	public static double damping(double angleMax, double time){
		if (angleMax > 0) {
			angleMax -= time * 0.01;
		}
		if (angleMax < 0) {
			angleMax = 0;
		}
		return angleMax;
	}
}
