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
    private Ta enemyTA;
    private Professor enemyBoss;
    private Obstacle enemyObs;

    @Override
    public void start(Stage primaryStage) throws Exception {//Game loop

        root = FXMLLoader.load(getClass().getResource("sample.fxml"));//play game screen fxml
        this.primaryStage = primaryStage;//stage becomes game screen
        primaryStage.setScene(new Scene(root, 800, 486));
        primaryStage.show();// game started to show
        keyboard = new Keyboard(primaryStage);//Keyboard instance created and made to listen inputs
        keyboard.kbInputs();                  //

        createPlayer();             //
        player.renderobject();      //
        createCompanions();         //Character and companions are showing in screen simultaneously
        companion.renderobject();   //
        companion2.renderobject();  //
        createEnemy(0);
        createEnemy(1);
        createEnemy(2);


        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ( !player.isDestroyed()) {
                    update();
                }
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

        if(enemyBoss!=null){
            enemyBoss.move();
        }

        if(enemyTA!=null){
            enemyTA.move();
        }

        if(enemyObs!=null){
            enemyObs.move();
        }

        createEnemy(0);
        createEnemy(1);
        createEnemy(2);


        checkcollisions();

    }


    public void checkcollisions() {

        // TA

        if (enemyTA != null && player.bullet != null)
            if (enemyTA.hasCollided(player.bullet.loc, player.bullet.dimensions)) {
                if (enemyTA.isDestroyed() ) {
                    enemyTA.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForTA();
                    }
                    enemyTA = null;
                } else
                    enemyTA.decreaseHealth(player.bullet.getDamage());
            }
        if (enemyTA != null )
            if (enemyTA.hasCollided(player.loc, player.dimensions) ) {
            player.decreaseHealth(5);
                if(player.isDestroyed())
                {
                    player.destroy();
                }
                if (enemyTA.isDestroyed() ) {
                    enemyTA.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForTA();
                    }
                    enemyTA = null;
                } else
                    enemyTA.decreaseHealth(5);
            if(player.openshield()){
                player.closeshield();
                }else{
                player.decreaseHealth(enemyTA.bullet.getDamage());
                if(player.isDestroyed()){
                    player.destroy();
                }
            }
        }
        if (enemyTA != null )
            if(enemyTA.bullet!=null)
            if (enemyTA.bullet.hasCollided(player.loc, player.dimensions) ) {
                if(player.openshield()){
                    player.closeshield();
                }
                else
                    {
                    player.decreaseHealth(5);
                    if(player.isDestroyed()){
                        player.destroy();
                    }
                }
            }
        if (enemyTA != null && companion.bullet != null)
            if (enemyTA.hasCollided(companion.bullet.loc, companion.bullet.dimensions)) {
                if (enemyTA.isDestroyed()) {
                    enemyTA.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForTA();
                    }
                    enemyTA = null;
                } else
                    enemyTA.decreaseHealth(companion.bullet.getDamage());
            }
        if (enemyTA != null && companion2.bullet != null)
            if (enemyTA.hasCollided(companion2.bullet.loc, companion2.bullet.dimensions)) {
                if (enemyTA.isDestroyed()) {
                    enemyTA.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForTA();
                    }
                    enemyTA = null;
                } else
                    enemyTA.decreaseHealth(companion2.bullet.getDamage());
            }

        if ( enemyTA != null && enemyTA.getY() >= 486 )
        {
            enemyTA.destroy();
            enemyTA = null;
        }

            // BOSS

        if (enemyBoss != null && player.bullet != null)
            if (enemyBoss.hasCollided(player.bullet.loc, player.bullet.dimensions)) {
                if (enemyBoss.isDestroyed()) {
                    enemyBoss.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForBoss();
                    }
                    enemyBoss = null;
                } else
                    enemyBoss.decreaseHealth(player.bullet.getDamage());
            }
        if (enemyBoss != null )
            if (enemyBoss.hasCollided(player.loc, player.dimensions)) {
                player.decreaseHealth(5);
                if(player.isDestroyed()){
                    player.destroy();
                }
                if (enemyBoss.isDestroyed() ) {
                    enemyBoss.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForBoss();
                    }
                    enemyBoss = null;
                } else
                    if ( player.bullet != null) {
                        enemyBoss.decreaseHealth(player.bullet.getDamage());
                    }
                if(player.openshield()){
                    player.closeshield();
                }else{
                    player.decreaseHealth(enemyBoss.bullet.getDamage());
                    if(player.isDestroyed()){
                        player.destroy();
                    }
                }
            }
        if (enemyBoss != null )
            if(enemyBoss.bullet!=null)
                if (enemyBoss.bullet.hasCollided(player.loc, player.dimensions) ) {
                    if(player.openshield() ){
                        player.closeshield();
                    }else{
                        player.decreaseHealth(5);
                        if(player.isDestroyed() ){
                            player.destroy();
                        }
                    }
                }
        if (enemyBoss != null && companion.bullet != null)
            if (enemyBoss.hasCollided(companion.bullet.loc, companion.bullet.dimensions)) {
                if (enemyBoss.isDestroyed()) {
                    enemyBoss.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForBoss();
                    }
                    enemyBoss = null;
                } else
                    enemyBoss.decreaseHealth(companion.bullet.getDamage());
            }
        if (enemyBoss != null && companion2.bullet != null)
            if (enemyBoss.hasCollided(companion2.bullet.loc, companion2.bullet.dimensions)) {
                if (enemyBoss.isDestroyed()) {
                    enemyBoss.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForBoss();
                    }
                    enemyBoss = null;
                } else
                    enemyBoss.decreaseHealth(companion2.bullet.getDamage());
            }




        if ( enemyBoss != null &&  enemyBoss.getY() >= 486 )
        {
            enemyBoss.destroy();
            enemyBoss = null;
        }

            /// OBSTACLE

        if (enemyObs != null && player.bullet != null)
            if (enemyObs.hasCollided(player.bullet.loc, player.bullet.dimensions) ) {
                if (enemyObs.isDestroyed()) {
                    enemyObs.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForObs();
                    }
                    enemyObs = null;
                } else
                    enemyObs.decreaseHealth(player.bullet.getDamage());
            }
        if (enemyObs != null )
            if (enemyObs.hasCollided(player.loc, player.dimensions) ) {
                player.decreaseHealth(5);
                if(player.isDestroyed()){
                    player.destroy();
                }
                if (enemyObs.isDestroyed() ) {
                    enemyObs.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForObs();
                    }
                    enemyObs = null;
                } else
                    enemyObs.decreaseHealth(player.bullet.getDamage());
                if(player.openshield() ){
                    player.closeshield();
                }else{
                    player.decreaseHealth(enemyObs.bullet.getDamage());
                    if(player.isDestroyed()){
                        player.destroy();
                    }
                }
            }
        if (enemyObs != null )
            if(enemyObs.bullet!=null)
                if (enemyObs.bullet.hasCollided(player.loc, player.dimensions)) {
                    if(player.openshield()){
                        player.closeshield();
                    }else{
                        player.decreaseHealth(5);
                        if(player.isDestroyed()){
                            player.destroy();
                        }
                    }
                }
        if (enemyObs != null && companion.bullet != null)
            if (enemyObs.hasCollided(companion.bullet.loc, companion.bullet.dimensions) ) {
                if (enemyObs.isDestroyed()) {
                    enemyObs.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForObs();
                    }
                    enemyObs = null;
                } else
                    enemyObs.decreaseHealth(companion.bullet.getDamage());
            }
        if (enemyObs != null && companion2.bullet != null)
            if (enemyObs.hasCollided(companion2.bullet.loc, companion2.bullet.dimensions)) {
                if (enemyObs.isDestroyed()) {
                    enemyObs.destroy();
                    if ( randomWithRange(0,1) == 1 ) {
                        createPowerUpForObs();
                    }
                    enemyObs = null;
                } else
                    enemyObs.decreaseHealth(companion2.bullet.getDamage());
            }


        if ( enemyObs != null && enemyObs.getY() >= 486 )
        {
            enemyObs.destroy();
            enemyObs = null;
        }

//        if (doubleBullet!=null)
//            if(doubleBullet.hasCollided(player.loc,player.dimensions)){
//                player.doubleshoot();
//                doubleBullet.destroy();
//                doubleBullet=null;

//            }
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
                if ( player.bullet != null ) {
                    player.bullet.setDamage(20);
                    companion.bullet.setDamage(20);
                    companion2.bullet.setDamage(20);
                }
                rageMode.destroy();
                rageMode=null;

            }

    }

    public void createPlayer() {//player instance being created
        try {
            player = new Character(
                    new Location(500, 300),
                    new Dimension(30),
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
                    new Dimension(20),
                    new Movement(0, 0, 5),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/google.png")),
                    0,
                    root,
                    keyboard);

            companion2 = new Companion(
                    new Location(550, 350),
                    new Dimension(20),
                    new Movement(0, 0, 5),
                    new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/stackoverflow.png")),
                    0,
                    root,
                    keyboard);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void createPowerUpForTA(){ // creates a power up when an enemy dies
            double randomNumber = 4 * Math.random();
            if (randomNumber <= 4) {
                double randomBuff = 5 * Math.random();
                if (randomBuff < 1) { // double bullet
                    try {
                        doubleBullet = new DoubleBullet(
                                new Location(enemyTA.getX(), enemyTA.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/doubleBullet.png")),
                                root);
                       // doubleBullet.renderobject();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (randomBuff > 1 && randomBuff < 2) { // mayfest

                    try {

                        mayfest = new Mayfest(
                                new Location(enemyTA.getX(), enemyTA.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/mayfest.png")),
                                root
                        );
                        mayfest.renderobject();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (randomBuff > 2 && randomBuff < 3) { // shield

                    try {

                        shield = new Shield(
                                new Location(enemyTA.getX(), enemyTA.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/shield.png")),
                                root
                        );
                        shield.renderobject();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                } else if (randomBuff > 3 && randomBuff < 4) { // all nighter

                    try {

                        allNighter = new AllNighter(
                                new Location(enemyTA.getX(), enemyTA.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/allNighter.png")),
                                root
                        );
                        allNighter.renderobject();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (randomBuff > 4 && randomBuff < 5) { // rage mode

                    try {

                        rageMode = new RageMode(
                                new Location(enemyTA.getX(), enemyTA.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/rageMode.png")),
                                root
                        );
                        rageMode.renderobject();



                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }


    }

    private void createPowerUpForBoss(){ // creates a power up when an enemy dies
        double randomNumber = 4 * Math.random();
        if (randomNumber <= 4) {
            double randomBuff = 5 * Math.random();
            if (randomBuff < 1) { // double bullet
                try {
                    doubleBullet = new DoubleBullet(
                            new Location(enemyBoss.getX(), enemyBoss.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/doubleBullet.png")),
                            root);
                   // doubleBullet.renderobject();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (randomBuff > 1 && randomBuff < 2) { // mayfest

                try {

                    mayfest = new Mayfest(
                            new Location(enemyBoss.getX(), enemyBoss.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/mayfest.png")),
                            root
                    );
                    mayfest.renderobject();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (randomBuff > 2 && randomBuff < 3) { // shield

                try {

                    shield = new Shield(
                            new Location(enemyBoss.getX(), enemyBoss.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/shield.png")),
                            root
                    );
                    shield.renderobject();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            } else if (randomBuff > 3 && randomBuff < 4) { // all nighter

                try {

                    allNighter = new AllNighter(
                            new Location(enemyBoss.getX(), enemyBoss.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/allNighter.png")),
                            root
                    );
                    allNighter.renderobject();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (randomBuff > 4 && randomBuff < 5) { // rage mode

                try {

                    rageMode = new RageMode(
                            new Location(enemyBoss.getX(), enemyBoss.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/rageMode.png")),
                            root
                    );
                    rageMode.renderobject();



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void createPowerUpForObs(){ // creates a power up when an enemy dies
        double randomNumber = 4 * Math.random();
        if (randomNumber <= 4) {
            double randomBuff = 5 * Math.random();
            if (randomBuff < 1) { // double bullet
                try {
                    doubleBullet = new DoubleBullet(
                            new Location(enemyObs.getX(), enemyObs.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/doubleBullet.png")),
                            root);
                    //doubleBullet.renderobject();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (randomBuff > 1 && randomBuff < 2) { // mayfest

                try {

                    mayfest = new Mayfest(
                            new Location(enemyObs.getX(), enemyObs.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/mayfest.png")),
                            root
                    );
                    mayfest.renderobject();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (randomBuff > 2 && randomBuff < 3) { // shield

                try {

                    shield = new Shield(
                            new Location(enemyObs.getX(), enemyObs.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/shield.png")),
                            root
                    );
                    shield.renderobject();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            } else if (randomBuff > 3 && randomBuff < 4) { // all nighter

                try {

                    allNighter = new AllNighter(
                            new Location(enemyObs.getX(), enemyObs.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/allNighter.png")),
                            root
                    );
                    allNighter.renderobject();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (randomBuff > 4 && randomBuff < 5) { // rage mode

                try {

                    rageMode = new RageMode(
                            new Location(enemyObs.getX(), enemyObs.getY()),
                            new Dimension(20),
                            new Movement(1, 1, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/rageMode.png")),
                            root
                    );
                    rageMode.renderobject();



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void createEnemy( int type) {//enemy instance being created

        try{

            if (type == 0) {

                if ( enemyObs == null ) {

                    enemyObs = new Obstacle(
                            new Location(randomWithRange(15,785), randomWithRange(15,85)),
                            new Dimension(15),
                            new Movement(1, 0, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/deadline.png")),
                            root
                    );

                    enemyObs.renderobject();
                }
            }

            else if (type == 1) {

                if ( enemyTA == null ) {
                    enemyTA = new Ta(
                            new Location(randomWithRange(15,785), randomWithRange(15,85)),
                            new Dimension(20),
                            new Movement(1, 0, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/ercument.png")),
                            root
                    );
                    enemyTA.renderobject();
                }
            }

            else if (type == 2) {

                if (enemyBoss == null ) {
                    enemyBoss = new Professor(
                            new Location(randomWithRange(15,785), randomWithRange(15,85)),
                            new Dimension(25),
                            new Movement(1, 0, 1),
                            new Image(new FileInputStream("/Users/imran/IdeaProjects/aDayInBilkent/src/sample/resources/ugur.png")),
                            root

                    );
                    enemyBoss.renderobject();
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    @FXML
    private Button backButton;

    @FXML protected void backButtonListener(ActionEvent event) throws Exception {//to be able to go back

        Stage settingsStage = (Stage) backButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

}