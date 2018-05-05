package GameObject;

import Manager.FileManager;
import javafx.scene.Parent;
import javafx.scene.image.Image;

public class Coin extends Collectable {

    // Instance Variables
    private FileManager fm;
    private int coinsRecieved;

    // Constructor

    public Coin(Location loc, Dimension dim, Movement mov, Parent root , Image img) {
        super(loc, dim, mov, root , img);
        fm = FileManager.getFm();
        coinsRecieved = 1;
    }

    @Override
    public void powers() {
        fm.updateCoins(coinsRecieved);
    }

    // coin does not move
    @Override
    public void move() {}
}
