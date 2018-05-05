package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameLoop extends Application {
    private Parent root;//For UI
    private Keyboard keyboard;//for keyboard listening

    private Character player;       // Player instance
    private Character player2;      // Player 2 instance
    private Companion companion;    //companion instance
    private Companion companion2;   //companion instance

    // Power-up's instances
    private Mayfest mayfest;
    private RageMode rageMode;
    private Shield shield;
    private AllNighter allNighter;

    // Array Lists and Arrays
    private ArrayList<Coin> coinList = new ArrayList<>();
    private ArrayList<TA>   taArrayList = new ArrayList<>();

    // Instances
    private Professor enemyBoss;
    private Obstacle enemyObs;

    // *************************
    // USER INTERFACE COMPONENTS
    // *************************

    @FXML private Button backButton;
    @FXML private Label healthHP;
    @FXML private Label healthPH;
    @FXML private Label itemIT;
    @FXML private Label itemTIT;
    @FXML private Label firstPU;
    @FXML private Label secondPU;
    @FXML private Label thirdPU;
    @FXML private Label fourthPU;
    @FXML private Label timer;
    @FXML private Label gold;
    @FXML private Label wave;
    @FXML private HBox wavePassed;


    // FOR WAVES
    private static int waveCounter = 1;
    private int obstacleCounter = 0;
    private int assistantCounter = 0;
    private int professorCounter = 0;
    private boolean assistantFinished = false;
    private boolean professorFinished = false;
    private long messageShowed;

    // FOR POWER UPS
    private static String powerUp1 = "None";
    private static String powerUp2 = "None";
    private static String powerUp3 = "None";
    private static String powerUp4 = "None";
    private boolean spawnedMF = false;
    private boolean spawnedSD = false;
    private boolean spawnedRM = false;
    private boolean spawnedAN = false;
    private long deleteMF;
    private long deleteSD;
    private long deleteRM;
    private long deleteAN;

    // FOR COOL DOWNS
    private long elapsedTime;
    private long timeMayfestInitialized;
    private long timeShieldInitialized;
    private long timeRageModeInitialized;
    private long timeAllNighterInitialized;

    // FOR COINS
    private static int coins = 0;

    // FOR FINAL SCREEN
    private static String timeFinal;
    private static String goldFinal;
    private static String wavesFinal;

    // FOR MULTIPLAYER MODE
    private boolean singlePlayer;
    private boolean firstPlayer = true;
    private boolean secondPlayer = true;
    static boolean bothDied = false;
    static boolean singleMulti;
    private boolean firstPlayerDied;
    private boolean secondPlayerDied;

    GameLoop(boolean mode, boolean storyMode )
    {
        if ( mode ) {
            System.out.println("SinglePlayer");
            singlePlayer = true;
            singleMulti = true;
            if ( storyMode )
                System.out.print("Story Mode");
            else
                System.out.print("Survival");
        }
        else {
            System.out.println("Multiplayer");
            singlePlayer = false;
            singleMulti = false;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {//Game loop

        bothDied = false;

        FXMLLoader loader = new FXMLLoader((getClass().getResource("layout/gameLoop.fxml")));
        loader.setController(this);

        root = loader.load();//play game screen fxml
        primaryStage.setScene(new Scene(root, 800, 486));
        primaryStage.show();

        if ( singlePlayer ) {
            itemIT.setText(Shop.getItem());
            itemTIT.setText("Item: ");
            healthPH.setText("Health: ");
        }
        else {
            healthPH.setText("Player1 Health:");
            itemTIT.setText("Player2 Health:");
        }

        // timer
        long startTime = System.currentTimeMillis();
        String format = String.format("%%0%dd", 2);

        keyboard = new Keyboard(primaryStage);  //Keyboard instance created and made to listen inputs
        keyboard.kbInputs();                    // First Player
        keyboard.kbInputsMult();                // Second Player

        createPlayer();
        createCompanions();

        if ( singlePlayer )
        {
            player.renderobject();
            //createCompanions();
            companion.renderobject();
            companion2.renderobject();
        }
        else
        {
            player.renderobject();
            player2.renderobject();
        }

        new AnimationTimer() {
            @Override
            public void handle(long l) {

                if ( singlePlayer ) {
                    if (firstPlayer) {
                        update();

                        // timer
                        long elapsedMillis = System.currentTimeMillis() - startTime;
                        elapsedTime = elapsedMillis / 1000;
                        String seconds = String.format(format, elapsedTime % 60);
                        String minutes = String.format(format, (elapsedTime % 3600) / 60);
                        String time = minutes + ":" + seconds;
                        timer.setText(time);
                    }
                }
                else
                {
                    if (firstPlayer || secondPlayer) {
                        update();

                        // timer
                        long elapsedMillis = System.currentTimeMillis() - startTime;
                        elapsedTime = elapsedMillis / 1000;
                        String seconds = String.format(format, elapsedTime % 60);
                        String minutes = String.format(format, (elapsedTime % 3600) / 60);
                        String time = minutes + ":" + seconds;
                        timer.setText(time);
                    }
                }


            }
        }.start();//loop
    }


    private void update() {//gets move and wrap information from character and companions

        initializeWave();

        if (singlePlayer)
        {
            // player one ready
            player.move(firstPlayer);
            player.wrap();

            // initializing companions
            companion.move();
            companion2.move();
            companion.wrap();
            companion2.wrap();
        }
        else {
            // player one ready
            player.move(firstPlayer);
            player.wrap();

            // player two ready
            player2.move2P(secondPlayer);
            player2.wrap();

            // FOR UI
            if ( secondPlayerDied )
            {
                itemIT.setText("0 HP");
            }
            else {
                itemIT.setText(String.valueOf(player2.getCurrentHealth()) + " HP");
            }
        }

        if(enemyBoss!=null){
            enemyBoss.move();
            enemyBoss.wrap();
        }

        if(enemyObs!=null){
            enemyObs.move();
        }

        // COLLISION CHECKER
        checkCollisions();

        if ( firstPlayerDied )
        {
            healthHP.setText("0 HP");
        }
        else {
            healthHP.setText(String.valueOf(player.getCurrentHealth()) + " HP");
        }

        // POWER UPS
        firstPU.setText(powerUp1);
        secondPU.setText(powerUp2);
        thirdPU.setText(powerUp3);
        fourthPU.setText(powerUp4);

        // WAVES - SEMESTERS
        wave.setText(String.valueOf(waveCounter));

        // COINS
        gold.setText(String.valueOf(coins));

        // COOL DOWN FOR POWER UPS
        coolDown();
    }


    private void checkCollisions() {

        // TA LIST

        final Iterator<TA> iterator = taArrayList.iterator();

        if ( singlePlayer ) {

            while (iterator.hasNext()) {
                final TA enemyTA = iterator.next();

                // TA and Player1 Bullet Collided
                if (player.bullet != null) {
                    if (enemyTA != null && enemyTA.hasCollided(player.bullet.loc, player.bullet.dimensions)) {
                        enemyTA.decreaseHealth(player.bullet.getDamage());

                        if (enemyTA.isDestroyed()) {
                            enemyTA.destroy();
                            if (randomWithRange(0, 1) == 1) {
                                createPowerUp(enemyTA);
                            }
                            iterator.remove();
                        }
                    }
                }


                // TA and Player Collided
                if (enemyTA != null && enemyTA.hasCollided(player.loc, player.dimensions)) {
                    player.decreaseHealth(50);

                    enemyTA.destroy();
                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(enemyTA);
                    }
                    iterator.remove();

                    if (player.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        player.destroy();
                        firstPlayer = false;
                    }

                    // SHIELD POWER UP
                    if (player.openShield()) {
                        player.closeShield();
                    } else {
                        player.decreaseHealth(enemyTA.bullet.getDamage());
                        if (player.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            player.destroy();
                            firstPlayer = false;
                        }
                    }
                }

                if (companion.bullet != null)
                    if (enemyTA != null && enemyTA.hasCollided(companion.bullet.loc, companion.bullet.dimensions)) {

                        enemyTA.decreaseHealth(companion.bullet.getDamage());

                        if (enemyTA.isDestroyed()) {
                            enemyTA.destroy();
                            if (randomWithRange(0, 1) == 1) {
                                createPowerUp(enemyTA);
                            }
                            iterator.remove();
                        }
                    }

                if (companion2.bullet != null)
                    if (enemyTA != null && enemyTA.hasCollided(companion2.bullet.loc, companion2.bullet.dimensions)) {

                        enemyTA.decreaseHealth(companion2.bullet.getDamage());

                        if (enemyTA.isDestroyed()) {
                            enemyTA.destroy();
                            if (randomWithRange(0, 1) == 1) {
                                createPowerUp(enemyTA);
                            }
                            iterator.remove();
                        }
                    }


            }

            // BOSS

            if (enemyBoss != null && player.bullet != null)
                if (enemyBoss.hasCollided(player.bullet.loc, player.bullet.dimensions)) {

                    enemyBoss.decreaseHealth(player.bullet.getDamage());

                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }
                }
            if (enemyBoss != null)
                if (enemyBoss.hasCollided(player.loc, player.dimensions)) {

                    enemyBoss.decreaseHealth(200);
                    player.decreaseHealth(200);

                    if (player.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        player.destroy();
                        firstPlayer = false;
                    }
                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }

                    if (player.openShield()) {
                        player.closeShield();
                    } else {
                        player.decreaseHealth(enemyBoss.getBulletDamage());
                        if (player.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            player.destroy();
                            firstPlayer = false;
                        }
                    }
                }


            // PROFESSOR BULLET
            if (enemyBoss != null) {
                for ( Bullet bullet : enemyBoss.getBulletList() ) {
                    if (bullet.hasCollided(player.loc, player.dimensions)) {
                        if (player.openShield()) {
                            player.closeShield();
                        } else {

                            player.decreaseHealth(70);

                            if (player.isDestroyed()) {
                                timeFinal = timer.getText();
                                goldFinal = gold.getText();
                                wavesFinal = wave.getText();
                                player.destroy();
                                firstPlayer = false;
                            }
                        }
                    }
                }
            }

            if (enemyBoss != null && companion.bullet != null)
                if (enemyBoss.hasCollided(companion.bullet.loc, companion.bullet.dimensions)) {

                    enemyBoss.decreaseHealth(companion.bullet.getDamage());

                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }
                }
            if (enemyBoss != null && companion2.bullet != null)
                if (enemyBoss.hasCollided(companion2.bullet.loc, companion2.bullet.dimensions)) {
                    enemyBoss.decreaseHealth(companion2.bullet.getDamage());

                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }
                }


            if (enemyBoss != null && enemyBoss.getY() >= 486) {
                enemyBoss.destroy();
                enemyBoss = null;
            }

            /// OBSTACLE

            if (enemyObs != null && player.bullet != null)
                if (enemyObs.hasCollided(player.bullet.loc, player.bullet.dimensions)) {
                    enemyObs.decreaseHealth(player.bullet.getDamage());
                    if (enemyObs.isDestroyed()) {
                        enemyObs.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyObs);
                        }
                        enemyObs = null;
                    }
                }
            if (enemyObs != null)
                if (enemyObs.hasCollided(player.loc, player.dimensions)) {

                    player.decreaseHealth(10);

                    if (player.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        player.destroy();
                        firstPlayer = false;
                    }

                    enemyObs.destroy();
                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(enemyObs);
                    }
                    enemyObs = null;


                    if (player.openShield()) {
                        player.closeShield();
                    } else {
                        player.decreaseHealth(enemyObs.bullet.getDamage());
                        if (player.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            player.destroy();
                            firstPlayer = false;
                        }
                    }
                }

            if (enemyObs != null && companion.bullet != null)
                if (enemyObs.hasCollided(companion.bullet.loc, companion.bullet.dimensions)) {
                    enemyObs.decreaseHealth(companion.bullet.getDamage());
                    if (enemyObs.isDestroyed()) {
                        enemyObs.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyObs);
                        }
                        enemyObs = null;
                    }
                }
            if (enemyObs != null && companion2.bullet != null)
                if (enemyObs.hasCollided(companion2.bullet.loc, companion2.bullet.dimensions)) {
                    enemyObs.decreaseHealth(companion2.bullet.getDamage());

                    if (enemyObs.isDestroyed()) {
                        enemyObs.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyObs);
                        }
                        enemyObs = null;
                    }
                }


            if (enemyObs != null && enemyObs.getY() >= 486) {
                enemyObs.destroy();
                enemyObs = null;
            }

            // COINS COLLISION

            if (coinList.size() != 0) {
                for (int i = 0; i < coinList.size(); i++) {
                    if (coinList.get(i).hasCollided(player.loc, player.dimensions)) {
                        coinList.get(i).destroy();
                        coinList.remove(i);
                        coins = coins + 10;
                        break;
                    }
                }
            }

            // MAYFEST COLLISION

            if (spawnedMF) {
                if (mayfest.hasCollided(player.loc, player.dimensions)) {
                    // initializing Mayfest
                    timeMayfestInitialized = elapsedTime;

                    player.movement.setSpeed(2);
                    companion.movement.setSpeed(2);
                    companion2.movement.setSpeed(2);
                    player.bullet.setDamage(5);
                    companion.bullet.setDamage(5);
                    companion2.bullet.setDamage(5);
                    player.decreaseHealth(10);
                    mayfest.destroy();
                    mayfest = null;
                    powerUp1 = "Mayfest";

                    // power up taken
                    spawnedMF = false;
                }

                if (elapsedTime - deleteMF == 5) {
                    assert mayfest != null;
                    mayfest.destroy();
                    mayfest = null;
                    // power up taken
                    spawnedMF = false;
                }
            }

            // SHIELD COLLISION

            if (spawnedSD) {
                if (shield.hasCollided(player.loc, player.dimensions)) {
                    // initializing Shield
                    timeShieldInitialized = elapsedTime;

                    player.openShield();
                    shield.destroy();
                    shield = null;

                    powerUp2 = "Shield";

                    // power up taken
                    spawnedSD = false;
                }

                if (elapsedTime - deleteSD == 5) {
                    assert shield != null;
                    shield.destroy();
                    shield = null;

                    // power up taken
                    spawnedSD = false;
                }
            }

            // ALL NIGHTER COLLISION

            if (spawnedAN) {
                if (allNighter.hasCollided(player.loc, player.dimensions)) {
                    // initializing All Nighter
                    timeAllNighterInitialized = elapsedTime;

                    player.movement.setSpeed(2);
                    companion.movement.setSpeed(2);
                    companion2.movement.setSpeed(2);
                    allNighter.destroy();
                    allNighter = null;
                    powerUp3 = "All Nighter";

                    // power up taken
                    spawnedAN = false;
                }

                if (elapsedTime - deleteAN == 5) {
                    assert allNighter != null;
                    allNighter.destroy();
                    allNighter = null;

                    // power up taken
                    spawnedAN = false;
                }
            }

            // RAGE MODE COLLISION

            if (spawnedRM) {
                if (rageMode.hasCollided(player.loc, player.dimensions)) {
                    // initializing Rage Mode
                    timeRageModeInitialized = elapsedTime;

                    player.movement.setSpeed(10);
                    companion.movement.setSpeed(10);
                    companion2.movement.setSpeed(10);
                    if (player.bullet != null) {
                        player.bullet.setDamage(20);
                        companion.bullet.setDamage(20);
                        companion2.bullet.setDamage(20);

                    }
                    rageMode.destroy();
                    rageMode = null;
                    powerUp4 = "Rage Mode";

                    // power up taken
                    spawnedRM = false;
                }

                if (elapsedTime - deleteRM == 5) {
                    assert rageMode != null;
                    rageMode.destroy();
                    rageMode = null;

                    // power up taken
                    spawnedRM = false;
                }
            }
        }
        else
        {

            while (iterator.hasNext()) {

                final TA enemyTA = iterator.next();

                // TA and Player1 Bullet Collided
                if (player.bullet != null) {
                    if (enemyTA != null && enemyTA.hasCollided(player.bullet.loc, player.bullet.dimensions)) {
                        enemyTA.decreaseHealth(player.bullet.getDamage());

                        if (enemyTA.isDestroyed()) {
                            enemyTA.destroy();
                            if (randomWithRange(0, 1) == 1) {
                                createPowerUp(enemyTA);
                            }
                            iterator.remove();
                        }
                    }
                }

                // TA and Player2 Bullet Collided
                if (player2.bullet != null) {
                    if (enemyTA != null && enemyTA.hasCollided(player2.bullet.loc, player2.bullet.dimensions)) {
                        enemyTA.decreaseHealth(player2.bullet.getDamage());

                        if (enemyTA.isDestroyed()) {
                            enemyTA.destroy();
                            if (randomWithRange(0, 1) == 1) {
                                createPowerUp(enemyTA);
                            }
                            iterator.remove();
                        }
                    }
                }


                // TA and Player Collided
                if (enemyTA != null && enemyTA.hasCollided(player.loc, player.dimensions)) {
                    player.decreaseHealth(50);

                    enemyTA.destroy();
                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(enemyTA);
                    }
                    iterator.remove();

                    if (player.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        if ( secondPlayerDied)
                        {
                            bothDied = true;
                        }
                        player.destroy();
                        firstPlayer = false;
                        firstPlayerDied = true;
                    }

                    // SHIELD POWER UP
                    if (player.openShield()) {
                        player.closeShield();
                    } else {
                        player.decreaseHealth(enemyTA.bullet.getDamage());
                        if (player.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            if ( secondPlayerDied)
                            {
                                bothDied = true;
                            }
                            player.destroy();
                            firstPlayer = false;
                            firstPlayerDied = true;
                        }
                    }
                }

                // TA and Player2 Collided
                if (enemyTA != null && enemyTA.hasCollided(player2.loc, player2.dimensions)) {
                    player2.decreaseHealth(50);

                    enemyTA.destroy();
                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(enemyTA);
                    }
                    iterator.remove();

                    if (player2.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        if ( firstPlayerDied)
                        {
                            bothDied = true;
                        }
                        player2.destroy();
                        secondPlayer = false;
                        secondPlayerDied = true;
                    }

                    // SHIELD POWER UP
                    if (player2.openShield()) {
                        player2.closeShield();
                    } else {
                        player2.decreaseHealth(enemyTA.bullet.getDamage());
                        if (player2.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            if ( firstPlayerDied)
                            {
                                bothDied = true;
                            }
                            player2.destroy();
                            secondPlayer = false;
                            secondPlayerDied = true;
                        }
                    }
                }

            }

            // BOSS

            // player 1
            if (enemyBoss != null && player.bullet != null)
                if (enemyBoss.hasCollided(player.bullet.loc, player.bullet.dimensions)) {

                    enemyBoss.decreaseHealth(player.bullet.getDamage());

                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }
                }

                // player 2
            if (enemyBoss != null && player2.bullet != null)
                if (enemyBoss.hasCollided(player2.bullet.loc, player2.bullet.dimensions)) {

                    enemyBoss.decreaseHealth(player2.bullet.getDamage());

                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }
                }

                // player 1
            if (enemyBoss != null)
                if (enemyBoss.hasCollided(player.loc, player.dimensions)) {

                    enemyBoss.decreaseHealth(200);
                    player.decreaseHealth(200);

                    if (player.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        if ( secondPlayerDied)
                        {
                            bothDied = true;
                        }
                        player.destroy();
                        firstPlayer = false;
                        firstPlayerDied = true;
                    }
                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }

                    if (player.openShield()) {
                        player.closeShield();
                    } else {
                        player.decreaseHealth(enemyBoss.getBulletDamage());
                        if (player.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            if ( secondPlayerDied)
                            {
                                bothDied = true;
                            }
                            player.destroy();
                            firstPlayer = false;
                            firstPlayerDied = true;
                        }
                    }
                }

                // player 2
            if (enemyBoss != null)
                if (enemyBoss.hasCollided(player2.loc, player2.dimensions)) {

                    enemyBoss.decreaseHealth(200);
                    player2.decreaseHealth(200);

                    if (player2.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        if ( firstPlayerDied)
                        {
                            bothDied = true;
                        }
                        player2.destroy();
                        secondPlayer = false;
                        secondPlayerDied = true;
                    }
                    if (enemyBoss.isDestroyed()) {
                        enemyBoss.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyBoss);
                        }
                        enemyBoss = null;
                    }

                    if (player2.openShield()) {
                        player2.closeShield();
                    } else {
                        player2.decreaseHealth(enemyBoss.getBulletDamage());
                        if (player2.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            if ( firstPlayerDied)
                            {
                                bothDied = true;
                            }
                            player2.destroy();
                            secondPlayer = false;
                            secondPlayerDied = true;
                        }
                    }
                }


            // PROFESSOR BULLET
            if (enemyBoss != null)
                for ( Bullet bullet : enemyBoss.getBulletList() ) {
                    if (bullet.hasCollided(player.loc, player.dimensions)) {
                        if (player.openShield()) {
                            player.closeShield();
                        } else {
                            player.decreaseHealth(70);
                            if (player.isDestroyed()) {
                                timeFinal = timer.getText();
                                goldFinal = gold.getText();
                                wavesFinal = wave.getText();
                                if (secondPlayerDied) {
                                    bothDied = true;
                                }
                                player.destroy();
                                firstPlayer = false;
                                firstPlayerDied = true;
                            }
                        }
                    }
                }



            // PROFESSOR BULLET PLayer 2
            if (enemyBoss != null)
                for ( Bullet bullet : enemyBoss.getBulletList() ) {
                    if (bullet.hasCollided(player2.loc, player2.dimensions)) {
                        if (player2.openShield()) {
                            player2.closeShield();
                        } else {
                            player2.decreaseHealth(70);
                            if (player2.isDestroyed()) {
                                timeFinal = timer.getText();
                                goldFinal = gold.getText();
                                wavesFinal = wave.getText();
                                if (firstPlayerDied) {
                                    bothDied = true;
                                }
                                player2.destroy();
                                secondPlayer = false;
                                secondPlayerDied = true;
                            }
                        }
                    }
                }

            /// OBSTACLE

            // player 1
            if (enemyObs != null && player.bullet != null)
                if (enemyObs.hasCollided(player.bullet.loc, player.bullet.dimensions)) {
                    enemyObs.decreaseHealth(player.bullet.getDamage());
                    if (enemyObs.isDestroyed()) {
                        enemyObs.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyObs);
                        }
                        enemyObs = null;
                    }
                }

                // player 2
            if (enemyObs != null && player2.bullet != null)
                if (enemyObs.hasCollided(player2.bullet.loc, player2.bullet.dimensions)) {
                    enemyObs.decreaseHealth(player2.bullet.getDamage());
                    if (enemyObs.isDestroyed()) {
                        enemyObs.destroy();
                        if (randomWithRange(0, 1) == 1) {
                            createPowerUp(enemyObs);
                        }
                        enemyObs = null;
                    }
                }

                // player 1
            if (enemyObs != null)
                if (enemyObs.hasCollided(player.loc, player.dimensions)) {

                    player.decreaseHealth(10);

                    if (player.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        if ( secondPlayerDied)
                        {
                            bothDied = true;
                        }
                        player.destroy();
                        firstPlayer = false;
                        firstPlayerDied = true;
                    }

                    enemyObs.destroy();
                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(enemyObs);
                    }
                    enemyObs = null;


                    if (player.openShield()) {
                        player.closeShield();
                    } else {
                        player.decreaseHealth(enemyObs.bullet.getDamage());
                        if (player.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            if ( secondPlayerDied)
                            {
                                bothDied = true;
                            }
                            player.destroy();
                            firstPlayer = false;
                            firstPlayerDied = true;
                        }
                    }
                }

                // player2
            if (enemyObs != null)
                if (enemyObs.hasCollided(player2.loc, player2.dimensions)) {

                    player2.decreaseHealth(10);

                    if (player2.isDestroyed()) {
                        timeFinal = timer.getText();
                        goldFinal = gold.getText();
                        wavesFinal = wave.getText();
                        if ( firstPlayerDied)
                        {
                            bothDied = true;
                        }
                        player2.destroy();
                        secondPlayer = false;
                        secondPlayerDied = true;
                    }

                    enemyObs.destroy();
                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(enemyObs);
                    }
                    enemyObs = null;


                    if (player2.openShield()) {
                        player2.closeShield();
                    } else {
                        player2.decreaseHealth(enemyObs.bullet.getDamage());
                        if (player2.isDestroyed()) {
                            timeFinal = timer.getText();
                            goldFinal = gold.getText();
                            wavesFinal = wave.getText();
                            if ( firstPlayerDied)
                            {
                                bothDied = true;
                            }
                            player2.destroy();
                            secondPlayer = false;
                            secondPlayerDied = true;
                        }
                    }
                }

            if (enemyObs != null && enemyObs.getY() >= 486) {
                enemyObs.destroy();
                enemyObs = null;
            }

            // COINS COLLISION

            // player 1
            if (coinList.size() != 0) {
                for (int i = 0; i < coinList.size(); i++) {
                    if (coinList.get(i).hasCollided(player.loc, player.dimensions)) {
                        coinList.get(i).destroy();
                        coinList.remove(i);
                        coins = coins + 10;
                        break;
                    }
                }
            }

            // player 2
            if (coinList.size() != 0) {
                for (int i = 0; i < coinList.size(); i++) {
                    if (coinList.get(i).hasCollided(player2.loc, player2.dimensions)) {
                        coinList.get(i).destroy();
                        coinList.remove(i);
                        coins = coins + 10;
                        break;
                    }
                }
            }

            // MAYFEST COLLISION

            if (spawnedMF) {
                // player 1
                if (mayfest.hasCollided(player.loc, player.dimensions)) {
                    // initializing Mayfest
                    timeMayfestInitialized = elapsedTime;

                    player.movement.setSpeed(2);
                    player.bullet.setDamage(5);
                    player.decreaseHealth(10);
                    mayfest.destroy();
                    mayfest = null;
                    powerUp1 = "Mayfest";

                    // power up taken
                    spawnedMF = false;
                }

                // player 2
                if (mayfest != null && mayfest.hasCollided(player2.loc, player2.dimensions)) {
                    // initializing Mayfest
                    timeMayfestInitialized = elapsedTime;

                    player2.movement.setSpeed(2);
                    player2.bullet.setDamage(5);
                    player2.decreaseHealth(10);
                    mayfest.destroy();
                    mayfest = null;
                    powerUp1 = "Mayfest";

                    // power up taken
                    spawnedMF = false;
                }

                if (elapsedTime - deleteMF == 5) {
                    assert mayfest != null;
                    mayfest.destroy();
                    mayfest = null;
                    // power up taken
                    spawnedMF = false;
                }
            }

            // SHIELD COLLISION

            if (spawnedSD) {
                // player 1
                if (shield.hasCollided(player.loc, player.dimensions)) {
                    // initializing Shield
                    timeShieldInitialized = elapsedTime;

                    player.openShield();
                    shield.destroy();
                    shield = null;

                    powerUp2 = "Shield";

                    // power up taken
                    spawnedSD = false;
                }

                // player2

                if (shield != null && shield.hasCollided(player2.loc, player2.dimensions)) {
                    // initializing Shield
                    timeShieldInitialized = elapsedTime;

                    player2.openShield();
                    shield.destroy();
                    shield = null;

                    powerUp2 = "Shield";

                    // power up taken
                    spawnedSD = false;
                }

                if (elapsedTime - deleteSD == 5) {
                    assert shield != null;
                    shield.destroy();
                    shield = null;

                    // power up taken
                    spawnedSD = false;
                }
            }

            // ALL NIGHTER COLLISION

            if (spawnedAN) {

                // player 1
                if (allNighter.hasCollided(player.loc, player.dimensions)) {
                    // initializing All Nighter
                    timeAllNighterInitialized = elapsedTime;

                    player.movement.setSpeed(2);
                    allNighter.destroy();
                    allNighter = null;
                    powerUp3 = "All Nighter";

                    // power up taken
                    spawnedAN = false;
                }

                // player 2

                if (allNighter != null && allNighter.hasCollided(player2.loc, player2.dimensions)) {
                    // initializing All Nighter
                    timeAllNighterInitialized = elapsedTime;

                    player2.movement.setSpeed(2);
                    allNighter.destroy();
                    allNighter = null;
                    powerUp3 = "All Nighter";

                    // power up taken
                    spawnedAN = false;
                }

                if (elapsedTime - deleteAN == 5) {
                    assert allNighter != null;
                    allNighter.destroy();
                    allNighter = null;

                    // power up taken
                    spawnedAN = false;
                }
            }

            // RAGE MODE COLLISION

            if (spawnedRM) {
                if (rageMode.hasCollided(player.loc, player.dimensions)) {
                    // initializing Rage Mode
                    timeRageModeInitialized = elapsedTime;

                    player.movement.setSpeed(10);
                    if (player.bullet != null) {
                        player.bullet.setDamage(20);
                    }
                    rageMode.destroy();
                    rageMode = null;
                    powerUp4 = "Rage Mode";

                    // power up taken
                    spawnedRM = false;
                }

                // player 2

                if (rageMode != null && rageMode.hasCollided(player2.loc, player2.dimensions)) {
                    // initializing Rage Mode
                    timeRageModeInitialized = elapsedTime;

                    player2.movement.setSpeed(10);
                    if (player2.bullet != null) {
                        player2.bullet.setDamage(20);
                    }
                    rageMode.destroy();
                    rageMode = null;
                    powerUp4 = "Rage Mode";

                    // power up taken
                    spawnedRM = false;
                }

                if (elapsedTime - deleteRM == 5) {
                    assert rageMode != null;
                    rageMode.destroy();
                    rageMode = null;

                    // power up taken
                    spawnedRM = false;
                }
            }
        }
    }

    private void createPlayer() {//player instance being created

        String name;
        String name2P;

        if ( singlePlayer ) {
            name = SelectCharacter.getSelectedCharacter();

            try {
                player = new Character(
                        new Location(500, 300),
                        new Dimension(30),
                        new Movement(0, 0, 5),
                        new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/"+ name +".png")),
                        0,
                        root,
                        keyboard);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            name = SelectFirstCharacter.getSelectedCharacter();
            name2P = SelectSecondCharacter.getSelectedCharacter();

            try {
                player = new Character(
                        new Location(randomWithRange(30, 305), 300),
                        new Dimension(30),
                        new Movement(0, 0, 5),
                        new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/"+ name +".png")),
                        0,
                        root,
                        keyboard);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                player2 = new Character(
                        new Location(randomWithRange(365, 770), 300),
                        new Dimension(30),
                        new Movement(0, 0, 5),
                        new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/"+ name2P +".png")),
                        0,
                        root,
                        keyboard);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void createCompanions() {//companion instances being created

        String nameLeft = SelectCompanion.getSelectedCompanionLeft();
        String nameRight = SelectCompanion.getSelectedCompanionRight();

        try {
            companion = new Companion(
                    new Location(450, 310),
                    new Dimension(15),
                    new Movement(0, 0, 5),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/"+ nameLeft +".png")),
                    0,
                    root,
                    keyboard);

            companion2 = new Companion(
                    new Location(550, 310),
                    new Dimension(15),
                    new Movement(0, 0, 5),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/"+ nameRight +".png")),
                    0,
                    root,
                    keyboard);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createPowerUp(Enemy enemy){ // creates a power up when an enemy dies

        double randomNumber = 4 * Math.random();
        if (randomNumber <= 4) {
            double randomBuff = 5 * Math.random();

            // COINS
            if (randomBuff < 1) {
                try {
                    Coin coin = new Coin(
                            new Location(enemy.getX(), enemy.getY()),
                            new Dimension(10),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/coin.png")),
                            root);

                    coinList.add(coin);

                    coin.renderobject();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            // MAYFEST
            else if (randomBuff > 1 && randomBuff < 2)
            {
                if (!spawnedMF)
                {
                    // spawn power up
                    spawnedMF = true;

                    try {
                        mayfest = new Mayfest(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/mayfest.png")),
                                root
                        );
                        mayfest.renderobject();

                        deleteMF = elapsedTime;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            // SHIELD
            else if (randomBuff > 2 && randomBuff < 3) {
                if (!spawnedSD) {

                    // spawn power up
                    spawnedSD = true;

                    try {
                        shield = new Shield(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/shield.png")),
                                root
                        );
                        shield.renderobject();

                        deleteSD = elapsedTime;


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            // ALL NIGHTER
            else if (randomBuff > 3 && randomBuff < 4) {
                if (!spawnedAN)
                {

                    // spawn power up
                    spawnedAN = true;

                    try {
                        allNighter = new AllNighter(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/allNighter.png")),
                                root
                        );
                        allNighter.renderobject();

                        deleteAN = elapsedTime;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            // RAGE MODE
            else if (randomBuff > 4 && randomBuff < 5) {
                if (!spawnedRM)
                {

                    // spawn power up
                    spawnedRM = true;

                    try {
                        rageMode = new RageMode(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/rageMode.png")),
                                root
                        );
                        rageMode.renderobject();

                        deleteRM = elapsedTime;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // method for creating enemies according to type
    private void createEnemy( int enemyType, int numberOfObstacles, int numberOfTA, int numberOfBosses, double assistantLocation ) {

        try{

            if (enemyType == 0) {

                if (obstacleCounter != numberOfObstacles && enemyObs == null) {

                    enemyObs = new Obstacle(
                            new Location(randomWithRange(15, 785), randomWithRange(52, 85)),
                            new Dimension(15),
                            new Movement(1, 0, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/deadline.png")),
                            root
                    );

                    obstacleCounter = obstacleCounter + 1;
                    enemyObs.renderobject();
                }

            }

            else if (enemyType == 1) {

                if ( assistantCounter != numberOfTA  ) {
                    TA enemyTA = new TA(
                            new Location(randomWithRange(25,775), assistantLocation),
                            new Dimension(25),
                            new Movement(1, 0, 0),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/ercument.png")),
                            root
                    );

                    taArrayList.add(enemyTA);

                    assistantCounter = assistantCounter + 1;
                    enemyTA.renderobject();
                }

                if ( assistantCounter == numberOfTA && taArrayList.size() == 0 )
                {
                    assistantFinished = true;
                }
            }

            else if (enemyType == 2) {

                if ( professorCounter != numberOfBosses && enemyBoss == null ) {
                    enemyBoss = new Professor(
                            new Location(randomWithRange(40,760), 92),
                            new Dimension(40),
                            new Movement(1, 0,0.5),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/ugur.png")),
                            root, player, player2
                    );

                    professorCounter = professorCounter + 1;
                    enemyBoss.renderobject();
                }

                if ( professorCounter == numberOfBosses && enemyBoss == null )
                {
                    professorFinished = true;
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean waveFinished( boolean assistantFinished, boolean professorFinished) {
        if ( assistantFinished && professorFinished ) {
            obstacleCounter = 0;
            assistantCounter = 0;
            professorCounter = 0;
            return true;
        }

        return false;
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    @FXML protected void backButtonListener() throws Exception {//to be able to go back

        Stage settingsStage = (Stage) backButton.getScene().getWindow();

        boolean mode = Main.getMode();
        Parent settingsRoot;

        if (mode)
            settingsRoot = FXMLLoader.load(getClass().getResource("layout/singlePlayerMenu.fxml"));
        else
            settingsRoot = FXMLLoader.load(getClass().getResource("layout/multiPlayerMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

    private void coolDown() {
        if (elapsedTime - timeMayfestInitialized == 5) {
            player.movement.setSpeed(5);
            companion.movement.setSpeed(5);
            companion2.movement.setSpeed(5);
            if ( player.bullet != null ) {
                player.bullet.setDamage(10);
                if ( singlePlayer)
                {
                    companion.bullet.setDamage(10);
                    companion2.bullet.setDamage(10);
                }
            }

            powerUp1 = "None";
        }

        if (elapsedTime - timeShieldInitialized == 10)
        {
            player.closeShield();

            powerUp2 = "None";
        }

        if (elapsedTime - timeAllNighterInitialized == 7)
        {
            player.movement.setSpeed(5);

            if ( singlePlayer )
            {
                companion.movement.setSpeed(5);
                companion2.movement.setSpeed(5);
            }

            powerUp3 = "None";
        }

        if (elapsedTime - timeRageModeInitialized == 5)
        {
            player.movement.setSpeed(5);
            companion.movement.setSpeed(5);
            companion2.movement.setSpeed(5);
            if ( player.bullet != null ) {
                player.bullet.setDamage(10);

                if ( singlePlayer )
                {
                    companion.bullet.setDamage(10);
                    companion2.bullet.setDamage(10);
                }
            }

            powerUp4 = "None";
        }
    }

    private void initializeWave() {

        if ( waveFinished( assistantFinished, professorFinished)) {
            waveCounter = waveCounter + 1;

            // show Semester Passed Message
            wavePassed.setVisible(true);
            messageShowed = elapsedTime;

            assistantFinished = false;
            professorFinished = false;
        }

        // discard Semester Passed Message
        if ( elapsedTime - messageShowed == 2)
        {
            wavePassed.setVisible(false);
        }

        // enemyType 0 - Obstacle   |    enemyType 1 - TA    |    enemyType 2 - Boss

        // FIRST WAVE
        if ( waveCounter == 1 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 75);
            createEnemy(1, 0,7, 0, 75);
            createEnemy(2,0,0,0, 75);
        }

        // SECOND WAVE
        if ( waveCounter == 2 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 85);
            createEnemy(1, 0,8, 0,85);
            createEnemy(2, 0,0,1,85);
        }

        // THIRD WAVE
        if ( waveCounter == 3 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 100);
            createEnemy(1, 0,9, 0, 100);
            createEnemy(2, 0,0,2, 100);
        }

        // FOURTH WAVE
        if ( waveCounter == 4 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 75);
            createEnemy(1, 0,13, 0, 75);
            createEnemy(2, 0,0,1, 75);
        }

        // FIFTH WAVE
        if ( waveCounter == 5 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 100);
            createEnemy(1, 0,14, 0, 100);
            createEnemy(2, 0,0,3,100);
        }

        // SIXTH WAVE
        if ( waveCounter == 6 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0,90);
            createEnemy(1, 0,15, 0, 90);
            createEnemy(2, 0,0,5, 90);
        }

        // SEVENTH WAVE
        if ( waveCounter == 7 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 80);
            createEnemy(1, 0,19, 0, 80);
            createEnemy(2, 0,0,2, 80);
        }

        // EIGHTH WAVE
        if ( waveCounter == 8 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 120);
            createEnemy(1, 0,20, 0, 120);
            createEnemy(2, 0,0,1, 120);
        }

        // NINTH WAVE
        if ( waveCounter == 9 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 140);
            createEnemy(1, 0,21, 0, 140);
            createEnemy(2, 0,0,3, 140);
        }

        // TENTH WAVE
        if ( waveCounter == 10 )
        {
            createEnemy(0, Integer.MAX_VALUE, 0, 0, 80);
            createEnemy(1, 0,25, 0, 80);
            createEnemy(2, 0,0,5, 80);
        }
    }

    static String getTimeFinal() {
        return timeFinal;
    }

    static String getGoldFinal() {
        return goldFinal;
    }

    static String getWavesFinal() {
        return wavesFinal;
    }


}