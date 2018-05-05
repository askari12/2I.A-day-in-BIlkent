package GameObject;

import Manager.Main;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Professor extends Enemy {

    private Character player;
    private boolean hasReachedCenter;
    private ArrayList<Bullet> bulletList;

    private int endTimer = 3 * 100;
    private int timer = endTimer + 1;
    private int bulletSpeed = 100;

    public Professor(Location loc, Dimension dim, Movement mov, Parent root, Image img , Character player) {
        super(loc, dim, mov, root, img);
        mov.setDy(-1);
        this.player = player;
        this.hasReachedCenter = false;
        this.bulletList = new ArrayList<Bullet>();
    }


    // REIGN HELLLLLLL
    @Override
    public void shoot() {

        if (hasReachedCenter && timer >= endTimer && bulletList.size() <= 20) {
            endTimer = 0;
            for (int i = 0; i < 8; i++) {
                bulletList.add(new Bullet(
                        new Location(dim.getRadius() / 2 , dim.getRadius() / 2),
                        new Dimension(5),
                        new Movement((Math.random() * 2) - 1,  (Math.random() * 2) - 1 ,  bulletSpeed),
                        root,
                        null));
                bulletList.get(bulletList.size() - 1).renderObject();
            }
        } else {
            timer++;
        }

        for (int i = 0 ; i < bulletList.size() ; i++) {
            Bullet bullet = bulletList.get(i);
            if (bullet.loc.getX() + bullet.dim.getRadius() >= Main.WIDTH / 2 ||
                    bullet.loc.getX() - bullet.dim.getRadius() <= -1 * Main.WIDTH / 2 ||
                    bullet.loc.getY() - bullet.dim.getRadius() <= -1 * Main.HEIGHT / 2 ||
                    bullet.loc.getY() + bullet.dim.getRadius() >= Main.HEIGHT / 2) {
                destroyBullet(bullet);
            }

        }
    }

    // Move towards the center
    @Override
    public void move() {

        loc.setY(loc.getY() - ((0.05 * mov.getDy() * mov.getSpeed())));
        obj.setTranslateY(loc.getY());
        if (loc.getY() == 20)
            hasReachedCenter = true;

        if (hasReachedCenter) {
            bobble();
        }

        for (Bullet bullet : bulletList) {
            bullet.move();
        }
    }

    private void bobble(){
        if (loc.getY() >= 20 || loc.getY() <= -20) {
            mov.setDy(mov.getDy() * -1);
        }
    }

    public void destroyBullet(Bullet bullet) {
        bulletList.get(bulletList.indexOf(bullet)).destroy();
        bulletList.remove(bullet);
    }
}
