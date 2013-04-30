package ps.system.menu;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OptionElement implements MenuInterface {

	public StackPane optionElement(String name) {
		StackPane stack = new StackPane();

		// The border line is a rectangle:
		final Rectangle border = new Rectangle(o_borderWidth, o_borderHeight,
				unselected);
		border.arcHeightProperty().setValue(o_borderArc);
		border.arcWidthProperty().setValue(o_borderArc);

		// The Gradient of the button
		RadialGradient grad = new RadialGradient(0, 0, 0.5, 0.5, 1, true,
				CycleMethod.NO_CYCLE, new Stop[] { new Stop(1, backColor),
						new Stop(0, accentColor) });

		// The background of the button:
		Rectangle main = new Rectangle(o_borderWidth - (o_borToMain * 2),
				o_borderHeight - (o_borToMain * 2), grad);
		main.arcHeightProperty().setValue(o_borderArc - o_borToMain);
		main.arcWidthProperty().setValue(o_borderArc - o_borToMain);

		// The name of the label is here:
		final Text label = new Text(name);
		label.setFont(Font.font(Font.getDefault().getFamily(),
				FontWeight.EXTRA_BOLD, 60));
		label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
		label.setFill(unselected);

		stack.getChildren().addAll(border, main, label);

		stack.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				border.setFill(selected);
				label.setFill(selected);

			}
		});
		stack.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				border.setFill(unselected);
				label.setFill(unselected);
			}
		});

		return stack;
	}

}
