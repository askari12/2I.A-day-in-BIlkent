package sample;


import javafx.scene.image.Image;

abstract class DestroyableObject extends AttackingObject {
    private int currentHealth;  //
    private int maxHealth;      //Destroyable object's variables to check object Health status
    private boolean destroyed;  //

    DestroyableObject(Location loc, Dimension dimensions, Movement movement, Image img){
        super(loc, dimensions, movement, img);
    }
    DestroyableObject(){

    }

    int getCurrentHealth() {
        return currentHealth;
    }

    void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    void decreaseHealth(int damage){
        currentHealth=currentHealth-damage;
        //System.out.println(damage);
    }

    boolean isDestroyed(){//simple algorithm for health value check
        if (currentHealth <= 0)
            return true;
    else
        return false;
    }
}
