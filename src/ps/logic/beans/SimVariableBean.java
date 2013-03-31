package ps.logic.beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SimVariableBean {

	private DoubleProperty variableValue = new SimpleDoubleProperty();

	public void setValue(double num) {
		variableValue.set(num);
	}

	public double getValue() {
		return variableValue.get();
	}

	public DoubleProperty getSimVariableBeanProperty() {
		return variableValue;
	}

}
