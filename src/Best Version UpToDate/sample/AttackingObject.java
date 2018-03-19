package sample;


import javafx.scene.image.Image;

public abstract class AttackingObject extends GameObject {

    public AttackingObject(Location loc, Dimension dimensions, Movement movement, Image img){
        super(loc,dimensions,movement,img);
    }

    public AttackingObject(){

    }
    public abstract void shoot();
    public abstract  void move();
}
