package ps.simulation.frames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Banner{
	
	private String TITLE;
	
	public Banner(String string) {
		TITLE = string;
	}

	public HBox showBanner() {
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: #373a7f");
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		final Text titleText = new Text(TITLE);
		titleText.setFont(new Font("Arial Black", 30));
		
		hbox.getChildren().add(titleText);
		return hbox;
	}

	

}
