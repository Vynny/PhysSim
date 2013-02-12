package ps.logic.beans;

import java.util.ArrayList;

public class BindingInterface {

	ArrayList<Object> bindings;
	
	public BindingInterface() {
		
		
	}

	public Object getSharedDataAtIndex(int i) {
		return bindings.get(i);
	}

	public void addData(ArrayList<Object> sharedData) {
		bindings = new ArrayList<Object>();
		for (int i = 0; i < sharedData.size(); i++) {
			bindings.add(sharedData.get(i));
		}
	}
	
}
