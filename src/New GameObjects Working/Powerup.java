package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public abstract class Powerup extends Collectable {

    public Powerup(Location loc, Dimension dim, Movement mov, Parent root, Image img) {
        super(loc, dim, mov, root, img);
    }

    // remain abstract
    @Override
    public abstract void powers();


    // Disperse depending on the x and y values
    @Override
    public void move() {
        loc.setX((int) (mov.getDx() * mov.getSpeed()));
        loc.setY((int) (mov.getDy() * mov.getSpeed()));
    }
}
