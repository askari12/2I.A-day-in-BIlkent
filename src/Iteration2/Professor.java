package sample;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class Professor extends Enemy
{
    private Parent root;
    private Circle enemy;
    private Character player;
    private Character player2;


    private boolean hasReachedCenter;
    private ArrayList<Bullet> bulletList;

    private int endTimer = 2 * 1000;
    private int timer = endTimer + 1;
    private int bulletSpeed = 10;
    private int bulletDamage = 10;

    Professor(Location loc, Dimension dimensions, Movement movement, Image img, Parent root , Character player , Character player2) {
        super(loc, dimensions, movement,img);
        this.root = root;
        super.setCurrentHealth(1000);

        movement.setDy(-1);
        this.player = player;
        this.player2 = player2;
        this.hasReachedCenter = true;
        this.bulletList = new ArrayList<>();

    }

    public void shoot() {

        if (hasReachedCenter && timer >= endTimer && bulletList.size() <= 20) {
            endTimer = 0;
            for (int i = 0; i < 8; i++) {
                bulletList.add(new Bullet(
                        new Location(enemy.getCenterX() , enemy.getCenterY()),
                        new Dimension(5),
                        new Movement((Math.random() * 2) - 1,  (Math.random() * 2) - 1 ,  bulletSpeed),
                        10
                        ,root
                ));
                Bullet bullet = bulletList.get(bulletList.size() - 1);
                bullet.renderobject();
                bullet.bullet.setFill(Color.DARKGREEN);
                bullet.bullet.setStroke(Paint.valueOf("White"));
            }
        } else {
            timer++;
        }

        for (int i = 0 ; i < bulletList.size() ; i++) {
            Bullet bullet = bulletList.get(i);
            if (bullet.loc.getX() + bullet.dimensions.getRadius() >= root.getScene().getWidth() ||
                    bullet.loc.getX() - bullet.dimensions.getRadius() <= 0 ||
                    bullet.loc.getY() - bullet.dimensions.getRadius() <= 50 ||
                    bullet.loc.getY() + bullet.dimensions.getRadius() >= root.getScene().getHeight()) {
                destroyBullet(bullet);
            }

            if (bullet.hasCollided(player.loc , player.dimensions) ){
                player.decreaseHealth(bulletDamage);

                if ( player.isDestroyed())
                {
                    player.destroy();
                }

                destroyBullet(bullet);
            }

            if (player2 != null && bullet.hasCollided(player.loc , player.dimensions) ){
                player.decreaseHealth(bulletDamage);
                destroyBullet(bullet);
            }

        }

    }
    @Override
    public void move() {

        loc.setX(loc.getX() + movement.getSpeed());
        enemy.setCenterX(loc.getX());

        shoot();

        for (Bullet bullet : bulletList) {
            bullet.move();
        }
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
        for (Bullet bullet : bulletList)
            bullet.destroy();
    }

    void wrap() {//to move on every inch of screen
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

    public void destroyBullet(Bullet bullet) {
        bulletList.get(bulletList.indexOf(bullet)).destroy();
        bulletList.remove(bullet);
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }
}
