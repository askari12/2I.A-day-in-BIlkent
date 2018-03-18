package sample;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Professor extends Enemy
{
    private Parent root;
    private Circle enemy;
    private Bullet bullet;

    public Professor(Location loc, Dimension dimensions, Movement movement, Image img) {
        super(loc, dimensions, movement,img);
    }

    @Override
    public void shoot() {
        bullet = new Bullet(
                new Location(loc.getX() , loc.getY()) ,
                new Dimension(5) ,
                new Movement(0 , -1 , 15),
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

    @Override
    public void destroy() {

    }
}
