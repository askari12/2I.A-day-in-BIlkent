package GameObject;

import Manager.Keyboard;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class StackOverFlow extends Companion {

    private int healthIncrease = 10;

    // Constructor
    public StackOverFlow(Location loc, Dimension dim, Movement mov, Parent root, Image img, boolean left, Character player, Keyboard kb , ArrayList<Enemy> enemyList) {
        super(loc, dim, mov, root, img, left, player , kb , enemyList);
    }

    // Heal
    @Override
    public void powers() {
        player.setCurrentHealth(player.getCurrentHealth() + healthIncrease);
    }

    // Does nothing
    @Override
    public void powersOff() {}
}
