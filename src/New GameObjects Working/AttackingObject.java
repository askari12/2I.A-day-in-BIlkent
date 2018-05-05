package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public abstract class AttackingObject extends GameObject {

    public AttackingObject(Location loc, Dimension dim, Movement mov, Parent root, Image img) {
        super(loc, dim, mov, root, img);
    }

    // abstract Methods
    public abstract void shoot();

}
