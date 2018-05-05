package GameObject;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public abstract class DestroyableObject extends AttackingObject{

    // Instance Variables

    protected int currentHealth;
    protected int maxHealth;
    protected boolean destroyed;

    // Constructor

    public DestroyableObject(Location loc, Dimension dim, Movement mov, Parent root, Image img ) {
        super(loc, dim, mov, root, img);
        this.destroyed = false;
    }

    // Abstract Methods

    // Methods

    public void decreaseHealth(int damage) {
        currentHealth -= damage;

        if (currentHealth <= 0) {
            destroyed = true;
        }
    }

    // getters and setters

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
