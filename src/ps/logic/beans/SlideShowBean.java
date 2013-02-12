package ps.logic.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SlideShowBean {

	private IntegerProperty displayedMarathoner = new SimpleIntegerProperty();

	public void setDisplayedMarathoner(int num) {
		displayedMarathoner.set(num);
	}

	public int getDisplayedMarathoner() {
		return displayedMarathoner.get();
	}

	public IntegerProperty getDisplayedMarathonerProperty() {
		return displayedMarathoner;
	}

}
