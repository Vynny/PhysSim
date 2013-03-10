package ps.system.main;

import java.util.HashMap;

import ps.system.api.SimulatorInstance;
import ps.system.frames.TrackWindow;

public class SimulationList {
	
	// Simulation list
	public static HashMap<String, SimulatorInstance> simulationList = new HashMap<String, SimulatorInstance>();
	
	/*
	 * -->Adding an element to the simulation list will automatically add it to the main menu and handle 
	 * 		loading per user request.
	 * 
	 * -->To add a SimulatorInstance object to the simulation list:
	 *     + Append a line of the form:
	 *     		simulationList.put(String TITLE, new SimulatorInstance Object);
	 *       to the SimulationList() method.
	 *       
	 *     +The TITLE must be in the form "SUBJECT_TOPIC"; For instance to add an option named
	 *     	"Foo Bar" to the Mechanics menu, the title must be "Mechanics_Foo Bar". The object
	 *      declared after the title will be the object associated with said title.   
	 *      
	 *     +Allowed Topics:
	 *       ->Mechanics (ex: Mechanics_Simple Harmonic Motion)
	 *       ->Waves (ex: Waves_Double Slit)
	 *       ->EM (ex: EM_Electric Fields) 
	 */
	
	
	public SimulationList() {
		simulationList.put("Mechanics_Track Window", new TrackWindow("TrackWindow1"));
	}
}

