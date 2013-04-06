package ps.system.main;

import java.util.LinkedHashMap;

import ps.simulation.frames.JoBounceBall;
import ps.simulation.frames.JoProjectileMotion;
import ps.simulation.frames.JoSHM;
import ps.simulation.frames.TrackWindow;
import ps.simulation.frames.TrackWindow2;

public class SimulationList {
	
	// Simulation list
	public static LinkedHashMap<String, Object> simulationList = new LinkedHashMap<String, Object>();
	
	/*
	 * -->Adding an element to the simulation list will automatically add it to the main menu and handle 
	 * 		loading per user request.
	 * 
	 * -->To add a SimulatorInstance object to the simulation list:
	 *     + Append a line of the form:
	 *     		simulationList.put(String TITLE, SimulatorInstance(MUST-BE-A-SUBCLASS) Object);
	 *       to the SimulationList() method.
	 *       
	 *     +The TITLE must be in the form "SUBJECT_TOPIC_OBJECTNAME"; For instance to add an option named
	 *     	"Foo Bar" of a frame object (subclass of JoFrame or Simlator instance ie. JoBounceBall) to the Mechanics menu,
	 *      the title must be "Mechanics_Foo Bar_JoBounceBall". The object declared after the title will be the object 
	 *      associated with said title and must be initialized as null on top.   
	 *      
	 *     +Allowed Topics:
	 *       ->Mechanics (ex: Mechanics_Simple Harmonic Motion)
	 *       ->Waves (ex: Waves_Double Slit)
	 *       ->EM (ex: EM_Electric Fields) 
	 */
	
	
	public SimulationList() {
		
		/*
		 * OBJECT PROTOTYPES(MUST BE NULL)
		 */
		
		//Mechanics
		TrackWindow Mechanics_Projectile_Motion = null;
		JoBounceBall Mechanics_Bouncing_Ball = null;
		JoSHM Mechanics_SimpleHarmonicMotion = null;
		JoProjectileMotion Mechanics_ProjectileMotion = null;
		
		//Waves
		TrackWindow2 Waves_DoubleSlit = null;

		//Electricity and Magnetism
		
		/*
		 * Mechanics
		 */
		simulationList.put("Mechanics_Projectile Motion_TrackWindow", Mechanics_Projectile_Motion);
		simulationList.put("Mechanics_Bouncing Ball_JoBounceBall", Mechanics_Bouncing_Ball);
		simulationList.put("Mechanics_Simple Harmonic Motion_JoSHM", Mechanics_SimpleHarmonicMotion);
		simulationList.put("Mechanics_Projectile Motion_JoProjectileMotion", Mechanics_ProjectileMotion);

		/*
		 * Waves, Optics and Modern Physics
		 */
		simulationList.put("Waves_Double Slit_TrackWindow2", Waves_DoubleSlit);
		
		/*
		 * Electricity and Magnetism
		 */

	}
}

