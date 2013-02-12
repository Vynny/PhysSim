package ps.logic.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TimerBean {

		private IntegerProperty currentTime = new SimpleIntegerProperty();

		public void setTimer(int num) {
			currentTime.set(num);
		}

		public int getTimer() {
			return currentTime.get();
		}

		public IntegerProperty getTimerProperty() {
			return currentTime;
		}

}
