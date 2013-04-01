package ps.simulation.frames;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TrackPerson implements TrackGraphicsConstants {

	private Group athlete = new Group();
	private String shirtNumber;
	private Color faceColor;
	private Color shirtColor;
	private Color pantsColor;
	private ParallelTransition marathonerRun;

	public TrackPerson(String shirtNumber, Color faceColor, Color shirtColor, Color pantsColor) {
		this.shirtNumber = shirtNumber;
		this.faceColor = faceColor;
		this.shirtColor = shirtColor;
		this.pantsColor = pantsColor;
	}

	
/*	public void createPerson(final Pane pane, int Xpos, int Ypos) {
		Group athlete = new Group();
		int center;
		
		if (Double.parseDouble(shirtNumber) >= 10) {
			center = 15;
		} else {
			center = 7;
		}
		
		//Text number = new Text(Xpos - center, Ypos + 90, shirtNumber);
		Text number = new Text(Xpos, Ypos + 90, shirtNumber);
		// head
		Circle face = new Circle(Xpos, Ypos, sFaceWidth);
		Circle eyeL = new Circle(Xpos - 10, Ypos - 5, sEyeWidth, Color.WHITE);
		Circle eyeR = new Circle(Xpos + 10, Ypos - 5, sEyeWidth, Color.WHITE);
		Arc mouth = new Arc(Xpos, Ypos - 10, 35, 45, 220, 100);
		// body
		Ellipse body = new Ellipse(Xpos, Ypos + 90, sBodyWidth, sBodyHeight);
		Rectangle armL = new Rectangle(Xpos - 25, Ypos + 40, sArmWidth,
				sArmHeight);
		Rectangle armR = new Rectangle(Xpos + 15, Ypos + 40, sArmWidth,
				sArmHeight);
		Circle handL = new Circle(Xpos - 25, Ypos + 125, sHandWidth);
		Circle handR = new Circle(Xpos + 25, Ypos + 125, sHandWidth);

		// legs
		Rectangle legL = new Rectangle(Xpos - 15, Ypos + 145, sLegWidth,
				sLegHeight);
		Rectangle legR = new Rectangle(Xpos + 5, Ypos + 145, sLegWidth,
				sLegHeight);
		Rectangle pants = new Rectangle(Xpos - 15, Ypos + 135, sPantSize,
				sPantSize);

		number.setFont(Font.font("Arial", sFontSize));
		armL.setRotate(sArmAngle);
		armR.setRotate(-sArmAngle);

		// setting colors
		eyeL.setStroke(Color.BLACK);
		eyeR.setStroke(Color.BLACK);
		face.setFill(faceColor);
		face.setStroke(Color.BLACK);
		legL.setFill(pantsColor);
		legR.setFill(pantsColor);
		pants.setFill(pantsColor);
		armL.setFill(shirtColor);
		armR.setFill(shirtColor);
		body.setFill(shirtColor);
		handL.setFill(faceColor);
		handR.setFill(faceColor);
		mouth.setFill(Color.BLACK);
		mouth.setStroke(Color.RED);
		mouth.setStrokeWidth(sLipWidth);

		athlete.getChildren().addAll(legL, legR, body, pants, armL, armR,
				handL, handR, face, mouth, eyeL, eyeR, number);
		pane.getChildren().add(athlete);

	}*/


	public void runner(Pane pane, int RXpos, int RYpos) {
		//athlete = new Group();
		int center;
		
	/*	if (Double.parseDouble(this.shirtNumber) >= 10) {
			center = 7;
		} else {
			center = 2;
		}*/
		
		//Text number = new Text(RXpos - center, RYpos + 30, shirtNumber);
		Text number = new Text(RXpos, RYpos + 30, shirtNumber);
		// Head
		Circle face = new Circle(RXpos, RYpos, rFaceWidth);
		Circle eye = new Circle(RXpos + 3, RYpos - 3, 2, Color.WHITE);

		// Body
		Ellipse body = new Ellipse(RXpos, RYpos + 30, rBodyWidth, rBodyHeight);
		// Front arm
		Rectangle armL = new Rectangle(RXpos + 1, RYpos + 15, rArmWidth,
				rArmHeight);
		armL.setRotationAxis(new Point3D(0, 1, 1));
		// Back arm
		Rectangle armR = new Rectangle(RXpos - 8, RYpos + 15, rArmWidth,
				rArmHeight);
		armR.setRotationAxis(new Point3D(0, 1, 1));

		// Legs
		// Front leg
		Rectangle legL = new Rectangle(RXpos + 1, RYpos + 50, rLegWidth,
				rLegHeight);
		// Back leg
		Rectangle legR = new Rectangle(RXpos - 4, RYpos + 50, rLegWidth,
				rLegHeight);
		Rectangle pants = new Rectangle(RXpos - 11, RYpos + 50, rPantsWidth,
				rPantsHeight);
		pants.setArcHeight(rPantsHeight);
		pants.setArcWidth(rPantsWidth);

		armL.setRotate(-rArmRotation);
		armR.setRotate(rArmRotation);

		eye.setStroke(Color.BLACK);
		face.setFill(faceColor);
		face.setStroke(Color.BLACK);
		body.setFill(shirtColor);
		armR.setFill(shirtColor);
		armL.setFill(shirtColor);
		legR.setFill(pantsColor);
		legL.setFill(pantsColor);
		pants.setFill(pantsColor);

		RotateTransition legRun_Right = new RotateTransition(
				Duration.millis(500), legR);
		legRun_Right.setFromAngle(rLegAnimation);
		legRun_Right.setToAngle(-rLegAnimation);
		legRun_Right.setCycleCount(Timeline.INDEFINITE);
		legRun_Right.setAutoReverse(true);

		RotateTransition legRun_Left = new RotateTransition(
				Duration.millis(500), legL);
		legRun_Left.setFromAngle(-rLegAnimation);
		legRun_Left.setToAngle(rLegAnimation);
		legRun_Left.setCycleCount(Timeline.INDEFINITE);
		legRun_Left.setAutoReverse(true);

		RotateTransition armSwing_Right = new RotateTransition(
				Duration.millis(500), armR);
		armSwing_Right.setFromAngle(-rArmRotation);
		armSwing_Right.setToAngle(rArmRotation);
		armSwing_Right.setCycleCount(Timeline.INDEFINITE);
		armSwing_Right.setAutoReverse(true);

		RotateTransition armSwing_Left = new RotateTransition(
				Duration.millis(500), armL);
		armSwing_Left.setFromAngle(rArmRotation);
		armSwing_Left.setToAngle(-rArmRotation);
		armSwing_Left.setCycleCount(Timeline.INDEFINITE);
		armSwing_Left.setAutoReverse(true);

		marathonerRun = new ParallelTransition();
		marathonerRun.getChildren().addAll(legRun_Left, legRun_Right,
				armSwing_Left, armSwing_Right);

		athlete.getChildren().addAll(armR, legR, body, face, eye, armL, legL,
				pants, number);
		pane.getChildren().add(athlete);
	}

	public Node runnerNode() {
		return athlete;
	}

	public void StartRunning() {
		marathonerRun.play();
	}

	public void StopRunning() {
		marathonerRun.stop();
	}

}
