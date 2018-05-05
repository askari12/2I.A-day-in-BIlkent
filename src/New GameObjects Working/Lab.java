package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public class Lab extends Enemy {

    private Character player;
    private int labDamage = 10;

    public Lab(Location loc, Dimension dim, Movement mov, Parent root, Image img, Character player) {
        super(loc, dim, mov, root, img);
        this.player = player;
        findDirection();
    }

    // no shots
    @Override
    public void shoot() {}

    // Moves towards the enemy
    @Override
    public void move() {

        loc.setX(loc.getX() + (0.0008 * mov.getDx() * mov.getSpeed()));
        loc.setY(loc.getY() + (0.0008 * mov.getDy() * mov.getSpeed()));

        obj.setTranslateX(loc.getX());
        obj.setTranslateY(loc.getY());
    }

    private void findDirection() {

        mov.setDx(player.loc.getX() - loc.getX());
        mov.setDy(player.loc.getY() - loc.getY());
    }

    public int getLabDamage() {
        return labDamage;
    }
}
