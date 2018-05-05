package GameObject;

import Manager.Keyboard;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class YouTube extends Companion {

    public YouTube(Location loc, Dimension dim, Movement mov, Parent root, Image img, boolean left, Character player, Keyboard kb, ArrayList<Enemy> enemyList) {
        super(loc, dim, mov, root, img, left, player, kb, enemyList);
    }


    // Double Attack
    @Override
    public void powers() {
        player.doubleShootOn();
    }

    @Override
    public void powersOff() {
        player.doubleShootOff();
    }
}
