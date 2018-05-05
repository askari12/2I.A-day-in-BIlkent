package GameObject;

import Manager.Main;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class TA extends Enemy {

    private ArrayList<Lab> labList;
    private Character player;

    private int endTimer = 2 * 10;
    private int timer = endTimer + 1;

    public TA(Location loc, Dimension dim, Movement mov, Parent root, Image img , Character player) {
        super(loc, dim, mov, root, img);
        mov.setDx(1);
        this.player = player;
        this.labList = new ArrayList<Lab>();
    }


    // Shoots Labs
    @Override
    public void shoot() {
        // check if need to destroy lab

        if (timer >= endTimer) {
            timer = 0 ;
            labList.add(new Lab(
                    new Location(loc.getX(), loc.getY()),
                    new Dimension(5),
                    new Movement(0, 0, 7),
                    root,
                    null,
                    player));
            labList.get(labList.size() - 1).renderObject();
        } else {
            timer++;
        }

        for (int i = 0 ; i < labList.size() ; i++) {

            Lab lab = labList.get(i);

            if (lab.loc.getX() + lab.dim.getRadius() >= Main.WIDTH / 2 ||
                    lab.loc.getX() - lab.dim.getRadius() <= -1 * Main.WIDTH / 2 ||
                    lab.loc.getY() - lab.dim.getRadius() <= -1 * Main.HEIGHT / 2 ||
                    lab.loc.getY() + lab.dim.getRadius() >= Main.HEIGHT / 2) {
                destroyLab(lab);
            }


            if (lab.hasCollided(player)) {
                player.setCurrentHealth(player.getCurrentHealth() - lab.getLabDamage());
                destroyLab(lab);
            }

        }
    }


    // Slowly moves to left and right
    @Override
    public void move() {
        loc.setX((loc.getX() + ((0.05 * mov.getDx() * mov.getSpeed()))));
        obj.setTranslateX(loc.getX());
        wrap(); // so that the object does not move outside the screen


        if (!labList.isEmpty())
            for (Lab lab : labList)
                lab.move();
    }

    private void wrap() {
        if (loc.getX() + dim.getRadius() >= (Main.WIDTH / 2) ||
                loc.getX() - dim.getRadius() <= - (Main.WIDTH / 2)) {
            mov.setDx(mov.getDx() * -1);
        }
    }

    private void destroyLab(Lab lab) {
        labList.get(labList.indexOf(lab)).destroy();
        labList.remove(lab);
    }

}
