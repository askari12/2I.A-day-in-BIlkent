package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Type_A extends Character {
    public Type_A(Location loc, Dimension dimensions, Movement movement, Image img, int type, Parent root , Keyboard kb){
        super(loc,dimensions,movement,img,type, root ,  kb);
    }
}