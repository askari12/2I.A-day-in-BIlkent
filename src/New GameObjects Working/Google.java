package GameObject;

import Manager.Keyboard;
import Manager.Main;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Google extends Companion{

    Main main;

    public Google(Location loc, Dimension dim, Movement mov, Parent root, Image img, boolean left, Character player, Keyboard kb, ArrayList<Enemy> enemyList , Main main) {
        super(loc, dim, mov, root, img, left, player, kb, enemyList);
        this.main = main;
    }

    // Makes Player invisible
    @Override
    public void powers() {
        this.main.setPlayerVisibility(true);
    }

    @Override
    public void powersOff() {
        this.main.setPlayerVisibility(false);
    }
}
