package sample;

import java.awt.*;

public class DestroyableObject extends AttackingObject {
    private int currentHealth;
    private int maxHealth;
    private boolean destroyed;
    public DestroyableObject(Location loc, Dimension dimensions, Movement movement, Image img){

    }
    public DestroyableObject(){

    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    private void decreaseHealth(int damage){

    }
    private boolean isDestroyed(){
return true;
    }
}
