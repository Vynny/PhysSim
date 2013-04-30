package ps.system.menu;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Text;

public class Title implements MenuInterface{
	
	public static Group title(){
		
		Group titleElement = new Group();

		final Text programName = new Text(title);
		programName.setStyle("-fx-font-size: 52px; -fx-font-weight: bold;");
		programName.setFill(selected);

		final DropShadow dropShadow = new DropShadow();
		titleElement.setEffect(dropShadow);
		
		titleElement.getChildren().add(programName);
        
		return titleElement;
		
	}

}
