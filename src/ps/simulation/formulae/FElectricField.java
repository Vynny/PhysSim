package ps.simulation.formulae;

public class FElectricField {
	public static double xPos(double velocity, double time, double gravity){
		double xpos;
		xpos =(velocity*time)+(0.5*gravity*(time*time));
		return xpos;
	}
}
