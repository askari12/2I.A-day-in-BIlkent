package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public  class Companion extends AttackingObject {//Companions works with the same idea of Character class
    private int type;
    private Parent root;
    private Keyboard kb;
    private Circle companion;

    private int timer;
    private int maxTimer;

    public Bullet bullet;
    public Companion(Location loc, Dimension dimensions, Movement movement, Image img, int type, Parent root , Keyboard kb){
        super(loc, dimensions, movement, img);
        this.type = type;
        this.root = root;
        this.kb = kb;
        maxTimer = 60;
        timer = 0;
    }
    public void renderobject() {
        companion = new Circle();
        companion.setFill(new ImagePattern(this.img));
        companion.setStroke(Color.SEAGREEN);

        companion.setCenterX(loc.getX());
        companion.setCenterY(loc.getY());
        companion.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(companion);
    }
    public void destroy() {

    }
    public void shoot() {
        bullet = new Bullet(
                new Location(loc.getX() , loc.getY()) ,
                new Dimension(5) ,
                new Movement(0 , -1 , 15),
                5 , root);
        bullet.renderobject();
    }
    public void move() {
        if (kb.getRightPressed()) {
            loc.setX(loc.getX() - movement.getspeed());
            companion.setCenterX(loc.getX());
        }

        if (kb.getLeftPressed()) {
            loc.setX(loc.getX() + movement.getspeed());
            companion.setCenterX(loc.getX());
        }

        if (kb.getUpPressed()) {
            loc.setY(loc.getY() - movement.getspeed());
            companion.setCenterY(loc.getY());
        }

        if (kb.getDownPressed()) {
            loc.setY(loc.getY() + movement.getspeed());
            companion.setCenterY(loc.getY());
        }

        if (true) {
            if (bullet == null) {
                shoot();
            }
        }

        if (bullet != null) {
            timer++;

            bullet.move();

            if (timer >= maxTimer){
                timer = 0;
                bullet.destroy();
                bullet = null;
            }
        }
    }

    public void wrap() {
        if (getX() < 0 ) {
            setX( (int) root.getScene().getWidth());
        } else if (getX() > root.getScene().getWidth()) {
            setX(0);
        } else if (getY() < 0) {
            setY( (int) root.getScene().getHeight());
        } else if (getY() > root.getScene().getHeight()) {
            setY(0);
        }
    }

    // Getters and setters

    public int getX(){
        return loc.getX();
    }

    public int getY(){
        return loc.getY();
    }

    public void setX(int x) {
        loc.setX(x);
    }

    public void setY(int y) {
        loc.setY(y);
    }
    public   void powers(){
    //HEALTH Will increase
    }
}
