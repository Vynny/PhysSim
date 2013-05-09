package ps.system.main;

import java.util.LinkedHashMap;

import ps.simulation.frames.jfx.TrackWindow;
import ps.simulation.frames.mechanics.JoBounceBall;
import ps.simulation.frames.waves.JoSHM;

public class SimulationList implements SystemConstants {
	
	// Simulation list
	public static LinkedHashMap<String, Object> simulationList = new LinkedHashMap<String, Object>();
	
	/*
	 * -->Adding an element to the simulation list will automatically add it to the main menu and handle 
	 * 		loading per user request.
	 * 
	 * -->To add a SimulatorInstance object to the simulation list:
	 *     + Append a line of the form:
	 *     	  --> simulationList.put(String TITLE, SimulatorInstance(Swing/JFX) sub-classed Object);
	 *       to the SimulationList() method.
	 *       
	 *     +The TITLE must be in the form "SUBJECT_TOPIC_OBJECTNAME"; For instance to add an option named
	 *     	"Bouncing Ball Simulation" of a frame object ie. JoBounceBall(subclass of SimulatorInstance(Swing/JFX)) 
	 *      to the Mechanics menu, the title must be "Mechanics_Bouncing Ball Simulation_JoBounceBall". The object 
	 *      declared after the title will be the object associated with said title and must be initialized as null 
	 *      on top.   
	 *      
	 *     +Example Declarations:
	 *       ->Mechanics (ex: Mechanics_Simple Harmonic Motion_ProjectileMotionSimulation)
	 *       ->Waves (ex: Waves_Double Slit_DoubleSlitSimulation)
	 *       ->EM (ex: EM_Electric Fields_ElectricFieldSimulation) 
	 */
	
	
	public SimulationList() {
		
		/*
		 * OBJECT PROTOTYPES(MUST BE NULL)
		 */
		//----------------------------------------------------------------------------------------------
		
		//Mechanics
		TrackWindow Mechanics_Projectile_Motion = null;
		JoBounceBall Mechanics_Bouncing_Ball = null;
		//JoProjectileMotion Mechanics_ProjectileMotion = null;
		
		//Waves
		JoSHM Mechanics_SimpleHarmonicMotion = null;

		//Electricity and Magnetism
		
		
		/*
		 * HASHMAP ASSOCIATIONS
		 */
		//----------------------------------------------------------------------------------------------
		
		//Mechanics
		simulationList.put("Mechanics_JFXTEST1_TrackWindow", Mechanics_Projectile_Motion);
		//simulationList.put("Mechanics_" +  SystemLanguage.getLanguageBundle().getString("SimulationList_projectilemotion") + "_JoProjectileMotion", Mechanics_ProjectileMotion);
		simulationList.put("Mechanics_" +  SystemLanguage.getLanguageBundle().getString("SimulationList_bouncingball") + "_JoBounceBall", Mechanics_Bouncing_Ball);

		//Waves
		simulationList.put("Waves_" +  SystemLanguage.getLanguageBundle().getString("SimulationList_shm") + "_JoSHM", Mechanics_SimpleHarmonicMotion);
		
		//Electricity and Magnetism
		

	}
}

