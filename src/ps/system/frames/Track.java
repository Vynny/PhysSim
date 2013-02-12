package ps.system.frames;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Track implements GraphicsConstants {

    public Track() {
    	
    }
    
    public void buildTrack(Pane pane, double x, double y,Color trackColor,Color startColor,Color finishColor){
        Group root = new Group();
        
        Rectangle track = new Rectangle(padding,padding,x,y);
        Rectangle sbox = new Rectangle(padding,padding,x/padding,y);
        Rectangle finish = new Rectangle(x,padding,x,y);
        
        Rectangle div0 = new Rectangle(padding,padding,x,padding);
        Rectangle div1 = new Rectangle(padding,y/divisionWidth,x,padding);
        Rectangle div2 =new Rectangle(padding,2*(y/divisionWidth),x,padding);
        Rectangle div3 =new Rectangle(padding,3*(y/divisionWidth),x,padding);
        Rectangle div4 =new Rectangle(padding,4*(y/divisionWidth),x,padding);
        Rectangle div5 =new Rectangle(padding,5*(y/divisionWidth),x,padding);
        
        track.setFill(trackColor);
        sbox.setFill(startColor);
        finish.setFill(finishColor);
        
        root.getChildren().addAll(track,div0,div1,div2,div3,div4,div5,sbox,finish);
        pane.getChildren().add(root);
    }
}
