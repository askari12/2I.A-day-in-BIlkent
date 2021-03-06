package sample;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Obstacle extends Enemy
{

    Bullet bullet;
    private Circle enemy;
    private Parent root;

    public Obstacle(Location loc, Dimension dimensions, Movement movement, Image img, Parent root) {
        super(loc, dimensions, movement, img);
        this.root = root;
        super.setCurrentHealth(20);
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
        loc.setY(loc.getY() + movement.getSpeed());
        enemy.setCenterY(loc.getY());
    }

    @Override
    public void move2P() {
    }

    @Override
    public void renderobject() {
        enemy = new Circle();
        enemy.setFill(new ImagePattern(this.img));
        enemy.setVisible(true);
        enemy.setCenterX(loc.getX());
        enemy.setCenterY(loc.getY());
        enemy.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(enemy);
    }

    @Override
    public void destroy() {
        enemy.setVisible(false);
    }

}
