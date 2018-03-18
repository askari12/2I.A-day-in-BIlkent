package sample;


import javafx.scene.image.Image;

public abstract class GameObject {//abstract class for every object and it contains common attributes and also collision detection

    protected Location loc;
    protected Dimension dimensions;
    protected Movement movement;
    protected Image img;

    public GameObject(Location loc, Dimension dimensions, Movement movement, Image img){
        this.loc=loc;
        this.dimensions=dimensions;
        this.movement=movement;
        this.img=img;
    }

    public GameObject(){
    }

    public abstract void renderobject();
    public abstract void destroy();

    public boolean hasCollided(Location loc, Dimension dimensions)
    {//if the distance of two circle less or equal to their total radius then it is collision
        float distance = this.loc.getDistance(this.loc, loc);
        distance = distance - dimensions.getRadius() - this.dimensions.getRadius();

        if(distance <= 0)
            return true;
        else
            return false;
    }
}
