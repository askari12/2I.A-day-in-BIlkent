package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public abstract class Enemy extends AttackingObject {

    public Enemy(Location loc, Dimension dim, Movement mov, Parent root, Image img) {
        super(loc, dim, mov, root, img);
    }
}
