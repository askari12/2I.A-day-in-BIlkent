package sample;
import javafx.scene.image.Image;

import java.awt.*;
public abstract class PowerUp extends Collectable{
    public PowerUp(Location loc, Dimension dimensions, Movement movement, Image img){
        super(loc,dimensions,movement,img);
    }
    public abstract void powerUp(Location loc, Dimension dimensions, Movement movement, Image img);
}
