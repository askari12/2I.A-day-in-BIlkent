package sample;
import javafx.scene.image.Image;


public abstract class Collectable extends GameObject {
    public Collectable(Location loc, Dimension dimensions, Movement movement, Image img){
        super(loc,dimensions,movement,img);
    }
    public Collectable(){}
    // ia this method abstract?
    public void powers(){}
}
