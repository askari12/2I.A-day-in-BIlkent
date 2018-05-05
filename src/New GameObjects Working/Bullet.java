package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public class Bullet extends GameObject {

    public Bullet(Location loc, Dimension dim, Movement mov, Parent root, Image img ) {
        super(loc, dim, mov, root, img);
    }


    // Bullet needs to move in the y direction, up or down depending on if its an enemy shooting or companion/character
    @Override
    public void move() {
        loc.setY(loc.getY() - (0.05 * mov.getDy() * mov.getSpeed()));
        loc.setX(loc.getX() - (0.05 * mov.getDx() * mov.getSpeed()));
        obj.setTranslateY(loc.getY());
        obj.setTranslateX(loc.getX());
    }
}
