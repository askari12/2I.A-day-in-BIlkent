package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//All needed imports

public class GameLoop extends Application {//Class that contains game engine and controlls
    private Stage primaryStage;//For UI
    private Parent root;//For UI
    private Keyboard keyboard;//for keyboard listening

    private Character player;//player instance
    private Companion companion;//companion instance
    private Companion companion2;//companion instance

    // Power-up's instances
    private DoubleBullet doubleBullet;
    private Mayfest mayfest;
    private RageMode rageMode;
    private Shield shield;
    private AllNighter allNighter;

    //private ArrayList<Enemy> enemyList;
    private Ta enemy;//enemy instance

    @Override
    public void start(Stage primaryStage) throws Exception {//Game loop

        root = FXMLLoader.load(getClass().getResource("sample.fxml"));//play game screen fxml
        this.primaryStage = primaryStage;//stage becomes game screen

        primaryStage.setTitle("Play Game");                            //Game screen created
        primaryStage.setScene(new Scene(root, 737, 491));  //
        primaryStage.show();// game started to show
        keyboard = new Keyboard(primaryStage);//Keyboard instance created and made to listen inputs
        keyboard.kbInputs();                  //

        createPlayer();             //
        player.renderobject();      //
        createCompanions();         //Character and companions are showing in screen simultaneously
        companion.renderobject();   //
        companion2.renderobject();  //
        createEnemy();              //
        enemy.renderobject();       //


        new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        }.start();//loop

    }

    public void update() {//gets move and wrap information from characher and companions

        player.move();
        player.wrap();

        companion.move();
        companion2.move();
        companion.wrap();
        companion2.wrap();

        if(enemy!=null){
            enemy.move();
        }

        checkcollisions();

    }

    public void checkcollisions() {
        if (enemy != null && player.bullet != null)
            if (enemy.hasCollided(player.bullet.loc, player.bullet.dimensions) == true) {
                if (enemy.isDestroyed() == true) {
                    enemy.destroy();
                    createPowerUp();
                    enemy = null;
                } else
                    enemy.decreaseHealth(player.bullet.getDamage());
            }
        if (enemy != null )
            if (enemy.hasCollided(player.loc, player.dimensions) == true) {
                if (enemy.isDestroyed() == true) {
                    enemy.destroy();
                    createPowerUp();
                    enemy = null;
                } else
                    enemy.decreaseHealth(player.bullet.getDamage());
            if(player.openshield()==true){
                player.closeshield();
                }else{
                player.decreaseHealth(enemy.bullet.getDamage());
                if(player.isDestroyed()==true){
                    player.destroy();
                }
            }
        }
        if (enemy != null )
            if(enemy.bullet!=null)
            if (enemy.bullet.hasCollided(player.loc, player.dimensions) == true) {
                if(player.openshield()==true){
                    player.closeshield();
                }else{
                    player.decreaseHealth(enemy.bullet.getDamage());
                    if(player.isDestroyed()==true){
                        player.destroy();
                    }
                }
            }
        if (enemy != null && companion.bullet != null)
            if (enemy.hasCollided(companion.bullet.loc, companion.bullet.dimensions) == true) {
                if (enemy.isDestroyed() == true) {
                    enemy.destroy();
                    createPowerUp();
                    enemy = null;
                } else
                    enemy.decreaseHealth(companion.bullet.getDamage());
            }
        if (enemy != null && companion2.bullet != null)
            if (enemy.hasCollided(companion2.bullet.loc, companion2.bullet.dimensions) == true) {
                if (enemy.isDestroyed() == true) {
                    enemy.destroy();
                    createPowerUp();
                    enemy = null;
                } else
                    enemy.decreaseHealth(companion2.bullet.getDamage());
            }

        if (doubleBullet!=null)
            if(doubleBullet.hasCollided(player.loc,player.dimensions)){
                player.doubleshoot();
                doubleBullet.destroy();
                doubleBullet=null;

            }
        if(mayfest!=null)
            if(mayfest.hasCollided(player.loc,player.dimensions)){
                player.movement.setSpeed(3);
                companion.movement.setSpeed(3);
                companion2.movement.setSpeed(3);
                player.bullet.setDamage(5);
                companion.bullet.setDamage(5);
                companion2.bullet.setDamage(5);
                player.decreaseHealth(5);
                mayfest.destroy();
                mayfest=null;

            }
        if(shield!=null)
            if(shield.hasCollided(player.loc,player.dimensions)){
                player.openshield();
                shield.destroy();
                shield=null;

            }

        if(allNighter!=null)
            if(allNighter.hasCollided(player.loc,player.dimensions)){
                player.movement.setSpeed(3);
                companion.movement.setSpeed(3);
                companion2.movement.setSpeed(3);
                allNighter.destroy();
                allNighter=null;

            }

        if(rageMode!=null)
            if(rageMode.hasCollided(player.loc,player.dimensions)){
                player.movement.setSpeed(10);
                companion.movement.setSpeed(10);
                companion2.movement.setSpeed(10);
                player.bullet.setDamage(20);
                companion.bullet.setDamage(20);
                companion2.bullet.setDamage(20);
                rageMode.destroy();
                rageMode=null;

            }

    }

    public void createPlayer() {//player instance being created
        try {
            player = new Character(
                    new Location(500, 300),
                    new Dimension(12),
                    new Movement(0, 0, 5),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/askari.png")),
                    0,
                    root,
                    keyboard);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createCompanions() {//companion instances being created
        try {
            companion = new Companion(
                    new Location(450, 350),
                    new Dimension(10),
                    new Movement(0, 0, 5),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/google.png")),
                    0,
                    root,
                    keyboard);

            companion2 = new Companion(
                    new Location(550, 350),
                    new Dimension(10),
                    new Movement(0, 0, 5),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/stackoverflow.png")),
                    0,
                    root,
                    keyboard);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void createPowerUp(){ // creates a power up when an enemy dies
            double randomNumber = 4 * Math.random();
            if (randomNumber <= 4) {
                double randomBuff = 5 * Math.random();
                if (randomBuff < 1) { // double bullet
                    try {
                        doubleBullet = new DoubleBullet(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(10),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/coffee.png")),
                                root);
                        doubleBullet.renderobject();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (randomBuff > 1 && randomBuff < 2) { // mayfest

                    try {

                        mayfest = new Mayfest(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(10),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/coffee.png")),
                                root
                        );
                        mayfest.renderobject();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (randomBuff > 2 && randomBuff < 3) { // shield

                    try {

                        shield = new Shield(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(10),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/coffee.png")),
                                root
                        );
                        shield.renderobject();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                } else if (randomBuff > 3 && randomBuff < 4) { // all nighter

                    try {

                        allNighter = new AllNighter(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(10),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/coffee.png")),
                                root
                        );
                        allNighter.renderobject();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (randomBuff > 4 && randomBuff < 5) { // rage mode

                    try {

                        rageMode = new RageMode(
                                new Location(enemy.getX(), enemy.getY()),
                                new Dimension(10),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/coffee.png")),
                                root
                        );
                        rageMode.renderobject();



                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }


    }

    public void createEnemy() {//enemy instance being created
        try {

            enemy = new Ta(
                    new Location(253, 0),
                    new Dimension(14),
                    new Movement(1, 0, 1),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/alper.png")),
                    root
            );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button backButton;

    @FXML protected void backButtonListener(ActionEvent event) throws Exception {//to be able to go back

        Stage settingsStage = (Stage) backButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 737, 491);
        settingsStage.setScene(settingsScene);
    }
}