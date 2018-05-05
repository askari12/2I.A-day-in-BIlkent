package sample;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends GameObject {

    private int damage;//bullet damage value
    private Circle bullet;//to be able to show bullet on the screen
    private Parent root;//


    Bullet(Location loc, Dimension dimensions, Movement movement, int damage, Parent root) {
        super(loc, dimensions , movement, null);

        this.damage = damage;
        this.root = root;
    }

    int getDamage() {
        return damage;
    }
    void setDamage(int damage) {
        this.damage =damage;
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

    void move() {//moves bullet on game

        loc.setX(loc.getX() + movement.getSpeed() * movement.getDx());
        loc.setY(loc.getY() + movement.getSpeed() * movement.getDy());

        bullet.setCenterX(loc.getX());
        bullet.setCenterY(loc.getY());
    }

    @Override
    public void destroy() {
        bullet.setVisible(false);
        bullet = null;
    }//destroys bullet
}
