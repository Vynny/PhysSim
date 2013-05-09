package ps.system.menu;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuElement implements MenuInterface {

	public StackPane menuElement(String name) {
		StackPane stack = new StackPane();

		// The border line is a rectangle:
		final Rectangle border = new Rectangle(borderWidth, borderHeight,
				unselected);
		border.arcHeightProperty().setValue(borderArc);
		border.arcWidthProperty().setValue(borderArc);
		border.setOpacity(unsOpac);

		// The Gradient of the button
		RadialGradient grad = new RadialGradient(0, 0, 0.5, 0.5, 1, true,
				CycleMethod.NO_CYCLE, new Stop[] { new Stop(1, backColor),
						new Stop(0, accentColor) });

		// The background of the button:
		final Rectangle main = new Rectangle(borderWidth - (borToMain * 2),
				borderHeight - (borToMain * 2), grad);
		main.arcHeightProperty().setValue(borderArc - borToMain);
		main.arcWidthProperty().setValue(borderArc - borToMain);
		main.setOpacity(unsOpac);

		// The name of the label is here:
		final Text label = new Text(name);
		label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		label.setFill(unselected);

		stack.getChildren().addAll(border, main, label);

		stack.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				border.setFill(selected);
				border.setOpacity(selOpac);
				label.setFill(selected);
				main.setOpacity(selOpac);

			}
		});
		stack.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				border.setFill(unselected);
				border.setOpacity(unsOpac);
				label.setFill(unselected);
				main.setOpacity(unsOpac);
			}
		});

		return stack;
	}

}
