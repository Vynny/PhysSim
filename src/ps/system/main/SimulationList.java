package ps.system.main;

import java.util.HashMap;

import ps.system.api.SimulatorInstance;
import ps.system.frames.TrackWindow;
import ps.system.frames.TrackWindow2;

public class SimulationList {
	
	// Simulation list
	public static HashMap<String, SimulatorInstance> simulationList = new HashMap<String, SimulatorInstance>();
	
	public SimulationList() {
		simulationList.put("Mechanics_TrackWindow", new TrackWindow("TrackWindow1"));
		simulationList.put("Mechanics_TrackWindow2", new TrackWindow2("TrackWindow2"));
	}
}

