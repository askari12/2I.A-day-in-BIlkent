package GameObject;

import Manager.Keyboard;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Yahoo extends Companion {

    public Yahoo(Location loc, Dimension dim, Movement mov, Parent root, Image img, boolean left, Character player, Keyboard kb, ArrayList<Enemy> enemyList) {
        super(loc, dim, mov, root, img, left, player, kb, enemyList);
    }


    // Destroy all obstacles
    @Override
    public void powers() {
        for (int i = 0 ; i < enemyList.size() ; i++) {
            Enemy enemy = enemyList.get(i);
            if (enemy instanceof Obstacle) {
                enemy.destroy();
                enemyList.remove(i);
            }
        }
    }

    // Does nothing
    @Override
    public void powersOff() {}
}
