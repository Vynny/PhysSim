package ps.logic.beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TimeBean {

		private DoubleProperty currentTime = new SimpleDoubleProperty();

		public void setTime(double num) {
			currentTime.set(num);
		}

		public double getTime() {
			return currentTime.get();
		}

		public DoubleProperty getTimeProperty() {
			return currentTime;
		}

}
