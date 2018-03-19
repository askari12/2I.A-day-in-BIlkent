package sample;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class Professor extends Enemy
{
    private Parent root;
    private Circle enemy;
    Bullet bullet;
    private int timer;
    private int maxTimer;

    public Professor(Location loc, Dimension dimensions, Movement movement, Image img, Parent root) {
        super(loc, dimensions, movement,img);
        this.root = root;
        super.setCurrentHealth(20);
        maxTimer = 60;
        timer = 0;
    }

    @Override
    public void shoot() {
        bullet = new Bullet(
                new Location(loc.getX() , loc.getY()) ,
                new Dimension(5) ,
                new Movement(0 , 1 , 15),
                1 , root);
        bullet.renderobject();
    }

    @Override
    public void move() {
        loc.setY(loc.getY() + movement.getspeed());
        enemy.setCenterY(loc.getY());

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
        bullet.destroy();
        bullet = null;

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
}
