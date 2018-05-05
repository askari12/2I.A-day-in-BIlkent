package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public class Obstacle extends Enemy {

    public Obstacle(Location loc, Dimension dim, Movement mov, Parent root, Image img) {
        super(loc, dim, mov, root, img);
        mov.setDy(-1);
    }

    @Override
    public void move() {

        System.out.println(loc.getY());

        loc.setY((int) ( loc.getY() - ( (0.05) * mov.getDy() * mov.getSpeed())));
        obj.setTranslateY(loc.getY());

        System.out.println(loc.getY());

    }

    // Obstacles dont shoot
    @Override
    public void shoot() {}


}
