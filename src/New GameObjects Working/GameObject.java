package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class GameObject {

    // Instance Variable

    protected Location loc;
    protected Dimension dim;
    protected Movement mov;

    protected Parent root;
    protected Circle obj;
    protected Image img;

    // Constructor

    public GameObject(Location loc, Dimension dim, Movement mov , Parent root , Image img) {
        this.loc = loc;
        this.dim = dim;
        this.mov = mov;
        this.root = root;
        this.img = img;

        obj = new Circle(0, 0 , dim.getRadius());
        obj.setTranslateX(loc.getX());
        obj.setTranslateY(loc.getY());
        if (img != null)
           obj.setFill(new ImagePattern(img));
    }


    // Abstract Methods
    public abstract void move();

    // Methods

    public boolean hasCollided(GameObject otherObj){

        // Check if there is a collision
        // Find the distance between the two centers of the objects

        double deltaX = Math.abs(this.loc.getX() - otherObj.loc.getX());
        double deltaY = Math.abs(this.loc.getY() - otherObj.loc.getY());
        double distBwObj = Math.pow(Math.pow(deltaX , 2) + Math.pow(deltaY , 2), 0.5 );

        // if distance betweeen objects is less than the sum of the two radii , then they have collided , otherwise no collsion

        if (distBwObj < (this.dim.getRadius() + otherObj.dim.getRadius())) {
            return true;
        }

        return false;
    }

    public void destroy() {
        ((Pane) root).getChildren().remove(obj);
    }

    public void renderObject() {
        ((Pane) root).getChildren().add(obj);
    }

    // Getters and setters

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public Dimension getDim() {
        return dim;
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }

    public Movement getMov() {
        return mov;
    }

    public void setMov(Movement mov) {
        this.mov = mov;
    }
}
