package GameObject;

import Manager.Keyboard;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Companion extends AttackingObject {


    // Instance Variable
    protected boolean isLeft;
    protected int distBwCompAndPlayer;
    protected int timerEnd = 5 * 10;
    protected int timer = timerEnd + 1;
    protected int bulletSpeed = 200;

    protected int bulletTimer = 0;
    protected int bulletTimerEnd = 10 * 10;

    protected Character player;
    protected Keyboard kb;
    protected ArrayList<Enemy> enemyList;
    protected Bullet bullet;

    // Constructor
    public Companion(Location loc, Dimension dim, Movement mov, Parent root, Image img , boolean left , Character player , Keyboard kb , ArrayList<Enemy> enemyList) {
        super(loc, dim, mov, root, img);
        this.isLeft = left;
        this.player = player;
        this.kb = kb;
        this.enemyList = enemyList;
        this.bullet = null;

        // X location needs to move left or right
        if (isLeft) {
            loc.setX((int) (player.getLoc().getX() - ( distBwCompAndPlayer + (dim.getRadius() / 2) + (player.getDim().getRadius() / 2))));
        } else {
            loc.setX((int) (player.getLoc().getX() + (distBwCompAndPlayer + (dim.getRadius() / 2) + (player.getDim().getRadius() / 2))));
        }

        // Update on the screen
        obj.setTranslateX(loc.getX());
        obj.setTranslateY(loc.getY());

        distBwCompAndPlayer = (int) dim.getRadius();

    }

    // Abstract Methods

    public abstract void powers();
    public abstract void powersOff();

    // Methods

    @Override
    public void shoot() {
        if (kb.getEPressed() && !isLeft && bulletTimer == 0) {
            powers();
        } else if (kb.getQPressed() && isLeft && bulletTimer == 0) {
            powers();
        }

        if (timer >= timerEnd ) {
            timer = 0;
            shootBullets();
        } else {
            timer++;
        }

        if (bulletTimer >= bulletTimerEnd) {
            bulletTimer = 0;
            powersOff();
        } else {
            bulletTimer++;
        }

    }

    // The movement of the companion should be with the character
    @Override
    public void move() {

        // Update the location
        // X location needs to move left or right
        if (isLeft) {
            loc.setX((int) (player.getLoc().getX() - ( distBwCompAndPlayer + (dim.getRadius() / 2) + (player.getDim().getRadius() / 2))));
        } else {
            loc.setX((int) (player.getLoc().getX() + (distBwCompAndPlayer + (dim.getRadius() / 2) + (player.getDim().getRadius() / 2))));
        }

        // Y location stays the same as the player
        loc.setY(player.getLoc().getY());

        // Update on the screen
        obj.setTranslateX(loc.getX());
        obj.setTranslateY(loc.getY());

        if (bullet != null) {
            bullet.move();
        }

    }

    private void shootBullets() {

        if (bullet != null) {
            bullet.destroy();
            bullet = null;
        }

        bullet = new Bullet(new Location( loc.getX(), (int) (loc.getY() - (dim.getRadius() / 2))),
                new Dimension(5) , new Movement(0 , 1 , bulletSpeed) , root ,
                null);
        bullet.obj.setFill(Paint.valueOf("black"));
        bullet.renderObject();
    }
}
