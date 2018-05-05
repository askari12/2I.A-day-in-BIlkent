package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public abstract class Collectable extends GameObject {

    // Constructor
    public Collectable(Location loc, Dimension dim, Movement mov, Parent root , Image img) {
        super(loc, dim, mov, root , img);
    }

    // Abstract Method
    public abstract void powers();
}
