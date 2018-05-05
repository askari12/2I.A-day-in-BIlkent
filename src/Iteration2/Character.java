package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
//all needed imports

public class Character extends DestroyableObject{

    private int type;
    private Parent root;
    private Keyboard kb;//to be able to listen keyboard inputs
    private Circle player;//to be able to show character image on screen

    private int timer;      //
    private int maxTimer;   //Needed variables for determining bullet range
    Bullet bullet;
    private boolean shield=false;
    public Character(Location loc, Dimension dimensions, Movement movement, Image img, int type, Parent root , Keyboard kb){//constructor
        super(loc, dimensions, movement, img);
        this.type = type;
        this.root = root;
        this.kb = kb;
        maxTimer = 60;
        super.setCurrentHealth(250);
        timer = 0;
    }

    public Character(){
    }
    public void openragemode(){
        
    }
    boolean openShield(){
        shield=true;
        return shield;
    }
    void closeShield(){
        shield=false;
}

    @Override
    public void renderobject() {//moves character img on screen
        player = new Circle();
        player.setFill(new ImagePattern(this.img));

        player.setCenterX(loc.getX());
        player.setCenterY(loc.getY());
        player.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(player);
    }

    @Override
    public void destroy() {

        player.setVisible(false);

        if (GameLoop.singleMulti)
        {
            Stage gameStage = (Stage) player.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/gameOver.fxml"));
            Parent gameRoot = null;
            try {
                gameRoot = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GameOver fxmlDocumentController = fxmlLoader.getController();

            int score = Integer.valueOf(GameLoop.getGoldFinal()) * 10 + Integer.valueOf(GameLoop.getWavesFinal()) * 200;

            // loading stats of the game
            fxmlDocumentController.getTimeSurvived().setText(GameLoop.getTimeFinal());
            fxmlDocumentController.getGoldCollected().setText(GameLoop.getGoldFinal());
            fxmlDocumentController.getSemestersPassed().setText(GameLoop.getWavesFinal());
            fxmlDocumentController.getScore().setText(String.valueOf(score));

            Scene gameScene = new Scene(gameRoot, 800, 486);
            gameStage.setScene(gameScene);
        }
        else
        {
            if ( GameLoop.bothDied )
            {
                Stage gameStage = (Stage) player.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/gameOver.fxml"));
                Parent gameRoot = null;
                try {
                    gameRoot = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                GameOver fxmlDocumentController = fxmlLoader.getController();

                // loading stats of the game
                fxmlDocumentController.getTimeSurvived().setText(GameLoop.getTimeFinal());
                fxmlDocumentController.getGoldCollected().setText(GameLoop.getGoldFinal());
                fxmlDocumentController.getSemestersPassed().setText(GameLoop.getWavesFinal());

                Scene gameScene = new Scene(Objects.requireNonNull(gameRoot), 800, 486);
                gameStage.setScene(gameScene);
            }
        }

    }


    @Override
    public void shoot() {//shoots bullets from character by creating instances

        if ( bullet == null ) {
            bullet = new Bullet(
                    new Location(loc.getX(), loc.getY()),
                    new Dimension(5),
                    new Movement(0, -1, 15),
                    10, root);
            bullet.renderobject();
        }
    }

    void move(boolean bool)
    {
        if ( bool )
        {
            if (kb.getRightPressed()) {
                loc.setX(loc.getX() - movement.getSpeed());
                player.setCenterX(loc.getX());
            }

            if (kb.getLeftPressed()) {
                loc.setX(loc.getX() + movement.getSpeed());
                player.setCenterX(loc.getX());
            }

            if (kb.getUpPressed()) {
                loc.setY(loc.getY() - movement.getSpeed());
                player.setCenterY(loc.getY());
            }

            if (kb.getDownPressed()) {
                loc.setY(loc.getY() + movement.getSpeed());
                player.setCenterY(loc.getY());
            }

            //shoots bullet continously
            if (bullet == null) {
                shoot();
            }

            if (bullet != null) {//if time is up deletes the bullet so that bullet wont go forever
                timer++;
                if ( super.getCurrentHealth() > 0 ) {
                    bullet.move();
                }

                if (timer >= maxTimer) {
                    timer = 0;
                    bullet.destroy();
                    bullet = null; }
            }
        }
    }
    @Override
    public void move() {//checks every input case and move character with respect to that
    }

    @Override
    public void move2P() {
    }

    void move2P(boolean bool) {
        if ( bool ) {
            if (kb.getRightArrowPressed()) {
                loc.setX(loc.getX() - movement.getSpeed());
                player.setCenterX(loc.getX());
            }

            if (kb.getLeftArrowPressed()) {
                loc.setX(loc.getX() + movement.getSpeed());
                player.setCenterX(loc.getX());
            }

            if (kb.getUpArrowPressed()) {
                loc.setY(loc.getY() - movement.getSpeed());
                player.setCenterY(loc.getY());
            }

            if (kb.getDownArrowPressed()) {
                loc.setY(loc.getY() + movement.getSpeed());
                player.setCenterY(loc.getY());
            }
            //shoots bullet continously
            if (bullet == null) {
                shoot();
            }

            if (bullet != null) {//if time is up deletes the bullet so that bullet wont go forever
                timer++;
                bullet.move();

                if (timer >= maxTimer) {
                    timer = 0;
                    bullet.destroy();
                    bullet = null;
                }
            }
        }
    }

    void wrap() {//to move on every inch of screen
        if (getX() < 0 ) {
            setX( (int) root.getScene().getWidth());
        } else if (getX() > root.getScene().getWidth()) {
            setX(0);
        } else if (getY() < 80) {
            setY( (int) root.getScene().getHeight());
        } else if (getY() > root.getScene().getHeight()) {
            setY(80);
        }
    }

    // Getters and setters

    public double getX(){
        return loc.getX();
    }

    public double getY(){
        return loc.getY();
    }

    public void setX(int x) {
        loc.setX(x);
    }

    public void setY(int y) {
        loc.setY(y);
    }
}
