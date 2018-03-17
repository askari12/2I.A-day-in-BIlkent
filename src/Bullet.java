package sample;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends GameObject {

    private int damage;//bullet damage value
    private Circle bullet;//to be able to show bullet on the screen
    private Parent root;//


    public Bullet(Location loc, Dimension dimensions, Movement movement, int damage , Parent root) {
        super(loc, dimensions , movement, null);

        this.damage = damage;
        this.root = root;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damagee) {
        damage=damagee;
    }
    @Override
    public void renderobject() {//moves bullet image on screen
        bullet = new Circle();
        bullet.setCenterX(loc.getX());
        bullet.setCenterY(loc.getY());
        bullet.setRadius(3);

        bullet.setStroke(Color.RED);
        bullet.setFill(Color.BLACK);

        ((Pane) root).getChildren().add(bullet);
    }

    public void move() {//moves bullet on game
        loc.setX(loc.getX() + movement.getspeed() * movement.getDx());
        loc.setY(loc.getY() + movement.getspeed() * movement.getDy());

        bullet.setCenterX(loc.getX());
        bullet.setCenterY(loc.getY());
    }

    @Override
    public void destroy() {
        bullet.setVisible(false);
    }//destroys bullet
}
