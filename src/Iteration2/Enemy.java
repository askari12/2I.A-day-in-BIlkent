package sample;


import javafx.scene.image.Image;

public abstract class Enemy extends DestroyableObject
{
    public Enemy(Location loc, Dimension dimensions, Movement movement, Image img)
    {
        super(loc, dimensions, movement, img);
    }

    public double getX(){
        return loc.getX();
    }

    public double getY(){
        return loc.getY();
    }

    public void setX(double x) {
        loc.setX(x);
    }

    public void setY(double y) {
        loc.setY(y);
    }

}

