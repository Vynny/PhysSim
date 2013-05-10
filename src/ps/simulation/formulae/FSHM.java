package ps.simulation.formulae;

public class FSHM {
	public static double SHMY(double A, double omega, double time, double phi) {
		double y = 0;
		y = A * Math.cos(omega * (time + phi));
		return y;
	}
}
