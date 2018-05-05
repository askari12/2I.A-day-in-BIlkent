package GameObject;

import Manager.Keyboard;
import Manager.Main;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Character extends DestroyableObject {

    // Student type enum
    public static enum students {DEFAULT , QUICKLEARNERS , SMAERTSTUDENTS , BACKBENCHERS };

    // Instance Variables
    private int power;
    private int speed;
    private int defence;
    private Keyboard kb;
    private Bullet bullet;
    private Bullet bullet2;

    private int bulletSpeed = 200;
    private int endTime = 5 * 10;
    private int timer = 0;

    private boolean doubleShoot = false;


    public Character(Location loc, Dimension dim, Movement mov, Parent root, Image img, students type , Keyboard kb) {
        super(loc, dim, mov, root, img);

        // Assign all the students

        if (type == students.DEFAULT) {
            power = 75;
            speed = 75;
            defence = 75;
        }

        if (type == students.QUICKLEARNERS) {
            power = 50;
            speed = 90;
            defence = 50;
        }

        if (type == students.SMAERTSTUDENTS) {
            power = 90;
            speed = 50;
            defence = 50;
        }

        if (type == students.BACKBENCHERS) {
            power = 50;
            speed = 50;
            defence = 90;
        }

        maxHealth = defence;
        mov.setSpeed(speed);
        this.kb = kb;
    }

    @Override
    public void shoot() {

        // SHOOT

        if (timer >= endTime ) {
            timer = 0;
            shootBullets();
        } else {
            timer++;
        }

    }

    @Override
    public void move() {

        // RIGHT

        if (kb.getRightPressed()) {
            loc.setX((int) (loc.getX() + ( 0.05 * mov.getSpeed())));
            obj.setTranslateX(loc.getX());
        }

        // LEFT

        if (kb.getLeftPressed()) {
            loc.setX((int) (loc.getX() - ( 0.05 * mov.getSpeed())));
            obj.setTranslateX(loc.getX());
        }

        // UP

        if (kb.getUpPressed()) {
            loc.setY((int) (loc.getY() - ( 0.05 * mov.getSpeed())));
            obj.setTranslateY(loc.getY());
        }

        // DOWN

        if (kb.getDownPressed()) {
            loc.setY((int) (loc.getY() + ( 0.05 * mov.getSpeed())));
            obj.setTranslateY(loc.getY());
        }

//        wrap();

        if (bullet != null)
            bullet.move();

        if (bullet2 != null)
            bullet2.move();
    }

    private void shootBullets() {

        if (bullet != null) {
            bullet.destroy();
            bullet = null;
        }

        if (bullet2 != null) {
            bullet2.destroy();
            bullet2 = null;
        }

        int locationX = 0;

        if (doubleShoot) {
            locationX = 10;
            bullet2 = new Bullet(new Location( loc.getX() - locationX, (int) (loc.getY() - (dim.getRadius() / 2))),
                    new Dimension(5) , new Movement(0 , 1 , bulletSpeed) , root ,
                    null);
            bullet2.obj.setFill(Paint.valueOf("black"));
            bullet2.renderObject();
        }


        bullet = new Bullet(new Location( loc.getX() + locationX, (int) (loc.getY() - (dim.getRadius() / 2))),
                new Dimension(5) , new Movement(0 , 1 , bulletSpeed) , root ,
                null);
        bullet.obj.setFill(Paint.valueOf("black"));
        bullet.renderObject();
    }

    private void wrap() {
    }

    public void doubleShootOn() {
        doubleShoot = true;
    }

    public void doubleShootOff() {
        doubleShoot = false;
    }
}
