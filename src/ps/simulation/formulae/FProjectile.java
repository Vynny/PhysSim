package ps.simulation.formulae;

public class FProjectile {
	public static double highChange(double v, double time, double g){
		double high = 0;
		high = (v * time + (0.5 * g * (time * time)));
		return high;
	}
}
