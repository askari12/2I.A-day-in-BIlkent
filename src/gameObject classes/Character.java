package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
//all needed imports

public class Character extends AttackingObject{

    private int type;
    private Parent root;
    private Keyboard kb;//to be able to listen keyboard inputs
    private Circle player;//to be able to show character image on screen

    private int timer;      //
    private int maxTimer;   //Needed variables for determining bullet range
    public Bullet bullet;   //

    public Character(Location loc, Dimension dimensions, Movement movement, Image img, int type, Parent root , Keyboard kb){//constructor
        super(loc, dimensions, movement, img);
        this.type = type;
        this.root = root;
        this.kb = kb;
        maxTimer = 60;
        timer = 0;
    }

    public Character(){
    }

    @Override
    public void renderobject() {//moves character img on screen
        player = new Circle();
        player.setFill(new ImagePattern(this.img));
        player.setStroke(Color.SEAGREEN);

        player.setCenterX(loc.getX());
        player.setCenterY(loc.getY());
        player.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(player);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void shoot() {//shoots bullets from character by creating instances
        bullet = new Bullet(
                new Location(loc.getX() , loc.getY()) ,
                new Dimension(5) ,
                new Movement(0 , -1 , 15),
                1 , root);
        bullet.renderobject();
    }

    @Override
    public void move() {//checks every input case and move character with respect to that
        if (kb.getRightPressed()) {
            loc.setX(loc.getX() - movement.getspeed());
            player.setCenterX(loc.getX());
        }

        if (kb.getLeftPressed()) {
            loc.setX(loc.getX() + movement.getspeed());
            player.setCenterX(loc.getX());
        }

        if (kb.getUpPressed()) {
            loc.setY(loc.getY() - movement.getspeed());
            player.setCenterY(loc.getY());
        }

        if (kb.getDownPressed()) {
            loc.setY(loc.getY() + movement.getspeed());
            player.setCenterY(loc.getY());
        }

        if (true) {//shoots bullet continously
            if (bullet == null) {
                shoot();
            }
        }

        if (bullet != null) {//if time is up deletes the bullet so that bullet wont go forever

            timer++;

            bullet.move();

            if (timer >= maxTimer){
                timer = 0;
                bullet.destroy();
                bullet = null;
            }
        }
    }

    public void wrap() {//to move on every inch of screen
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
}
