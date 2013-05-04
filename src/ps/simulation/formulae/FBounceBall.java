package ps.simulation.formulae;

public class FBounceBall {
	public static double BallHigh(double v, double time, double g){
		double high = 0;
		high = (v * time + (0.5 * g * (time * time)));
		return high;
	}
}
