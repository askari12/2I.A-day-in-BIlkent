package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ta extends Enemy
{
    private Parent root;
    private Circle enemy;
    public Bullet bullet;
    private boolean enemyDead;

    public Ta(Location loc, Dimension dimensions, Movement movement, Image img , Parent root) {
        super(loc, dimensions, movement , img);
        this.root = root;
        super.setCurrentHealth(10);
    }

    @Override
    public void shoot() {
        bullet = new Bullet(
                new Location(loc.getX() , loc.getY()) ,
                new Dimension(5) ,
                new Movement(0 , -1 , 1),
                1 , root);
        bullet.renderobject();
    }

    @Override
    public void move() {
        loc.setY(loc.getY() + movement.getspeed());
        enemy.setCenterY(loc.getY());
    }

    @Override
    public void renderobject() {
        enemy = new Circle();
        enemy.setFill(new ImagePattern(this.img));
        enemy.setStroke(Color.SEAGREEN);

        enemy.setCenterX(loc.getX());
        enemy.setCenterY(loc.getY());
        enemy.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(enemy);
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
    @Override
    public void destroy() {
        enemy.setVisible(false);

    }
    public boolean enemyDestroyed(){
        if(super.isDestroyed()){

            return true;
        }
        return false;
    }
}
