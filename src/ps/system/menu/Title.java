package ps.system.menu;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Title implements MenuInterface {
	
	public static Group title(){
		
		Group titleElement = new Group();

		final Text programName = new Text(title);
		programName.setStyle("-fx-font-size: 52px; -fx-font-weight: bold; -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 )");
		programName.setFill(Color.web("#CFCFCF"));
		
		titleElement.getChildren().add(programName);
        
		return titleElement;
		
	}

}
