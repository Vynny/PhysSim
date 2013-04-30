package ps.system.menu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class Background {
	
	static double creatorWidth = 0;
	static double creatorHeight = 0;
	
	public List<Node> getBackgroundElements(BorderPane root){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	creatorWidth = screenSize.getWidth();
    	creatorHeight = screenSize.getHeight();
    	
    	Group layer1 = new Group();
        for(int i=0; i<20;i++) {
            Circle circle = new Circle(200,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.2f));
            circle.setStrokeWidth(4f);
            layer1.getChildren().add(circle);
        }
        // create second list of circles
        Group layer2 = new Group();
        for(int i=0; i<10;i++) {
            Circle circle = new Circle(70,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.1f));
            circle.setStrokeWidth(2f);
            layer2.getChildren().add(circle);
        }
        // create third list of circles
        Group layer3 = new Group();
        for(int i=0; i<10;i++) {
            Circle circle = new Circle(150,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.16f));
            circle.setStrokeWidth(4f);
            layer3.getChildren().add(circle);
        }
        // Set a blur effect on each layer
        layer1.setEffect(new BoxBlur(30,30,3));
        layer2.setEffect(new BoxBlur(2,2,2));
        layer3.setEffect(new BoxBlur(10,10,3));
        // create a rectangle size of window with colored gradient
        Rectangle colors = new Rectangle(creatorWidth, creatorHeight,
                new LinearGradient(0f,1f,1f,0f,true, CycleMethod.NO_CYCLE, new Stop(0,Color.web("#FF7600")),
                        new Stop(0.14f,Color.web("#FFC200")),
                        new Stop(0.28f,Color.web("#FF7600")),
                        new Stop(0.43f,Color.web("#FFC200")),
                        new Stop(0.57f,Color.web("#FF7600")),
                        new Stop(0.71f,Color.web("#FFC200")),
                        new Stop(0.85f,Color.web("#FF7600")),
                        new Stop(1,Color.web("#FFC200")))
        );
        colors.setBlendMode(BlendMode.OVERLAY);
        // create main content
        Group group = new Group(
                new Rectangle(creatorWidth, creatorHeight, Color.BLACK),
                layer1, 
                layer2,
                layer3,
                colors
        );
        Rectangle clip = new Rectangle(creatorWidth, creatorHeight);
        clip.setSmooth(false);
        group.setClip(clip);
        root.getChildren().add(group);
    	
    	List<Node> allCircles = new ArrayList<Node>();
        allCircles.addAll(layer1.getChildren());
        allCircles.addAll(layer2.getChildren());
        allCircles.addAll(layer3.getChildren());
        
        return allCircles;
	}

}
