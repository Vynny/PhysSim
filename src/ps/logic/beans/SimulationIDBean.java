package ps.logic.beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SimulationIDBean {

	private StringProperty simulationID = new SimpleStringProperty();

	public void setSimulationID(String num) {
		simulationID.set(num);
	}

	public String getSimulationID() {
		return simulationID.get();
	}

	public StringProperty SimulationIDProperty() {
		return simulationID;
	}

}
