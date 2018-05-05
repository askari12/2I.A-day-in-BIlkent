package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
//All needed imports


//SOMETIMES EVEN İF YOU DONT PİCK UP POWERUP STATS ARE CHANGING

public class GameLoop extends Application {//Class that contains game engine and controlls
    private Stage primaryStage;//For UI
    MediaPlayer mediaPlayer;
    Media sound;
    private Parent root;//For UI
    private Keyboard keyboard;//for keyboard listening

    private Character player;//player instance
    private Companion companion;//companion instance
    private Companion companion2;//companion instance

    // Power-up's instances just needed for array
    private DoubleBullet doubleBullet;
    private Mayfest mayfest;
    private RageMode rageMode;
    private Shield shield;
    private AllNighter allNighter;

    // Enemy's instances just needed for array
    private Ta enemyTA;
    private Professor enemyBoss;
    private Obstacle enemyObs;

    //Arrays of enemies and powerups
    ArrayList<Enemy> enemies = new ArrayList<>();
    Enemy enemyArray[] = new Enemy[999];

    //Counters
    int enemycounter=0;
    int enemycounter2=0;
    int wavecount = 1;//wavecounter for spawner and wavemanager

    @Override
    public void start(Stage primaryStage) throws Exception {//Game loop

        root = FXMLLoader.load(getClass().getResource("sample.fxml"));//play game screen fxml
        this.primaryStage = primaryStage;//stage becomes game screen
        primaryStage.setScene(new Scene(root, 800, 486));
        primaryStage.show();// game started to show
        keyboard = new Keyboard(primaryStage);//Keyboard instance created and made to listen inputs
        keyboard.kbInputs();                  //
        wavecount = 1;
        createPlayer(0);             //
        player.renderobject();      //
        createCompanions(0, 0);         //Character and companions are showing in screen simultaneously
        companion.renderobject();   //
        companion2.renderobject();  //
//        enemyArray[0] = createEnemy(0);//
//        enemyArray[1] = createEnemy(1);// this parts need to be changed after the creation of wave manager and spawner
//        enemyArray[2] = createEnemy(2);//
        wavemanager();

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!player.isDestroyed()) {
                    update();
                }
            }
        }.start();//loop

    }


    public void update() {//gets move and wrap information from character and companions
        player.move();//
        player.wrap();///
        companion.move();//
        companion2.move();//  Works fine
        companion.wrap();//
        companion2.wrap();//
///ENEMY SHOULD BE CREATED İN HERE WAVE SPAWN MUST BE HANDLED İN HERE
        for (int i = 0; i < enemycounter; i++) {                         //
            if (enemyArray[i] != null) {                                        //
                enemyArray[i].move();                                       //
                //
                if (player.bullet != null)                                    //
                    checkcollisions(enemyArray[i], player.bullet, i);         //
                //
                if (companion.bullet != null)                                 //
                    checkcollisions(enemyArray[i], companion.bullet, i);      //Check collisions for every enemy
                //
                if (companion2.bullet != null)                                //
                    checkcollisions(enemyArray[i], companion2.bullet, i);     //
                //
                checkcollisions(enemyArray[i], player, i);                    //
                checkcollisions(enemyArray[i], companion, i);                 //
                checkcollisions(enemyArray[i], companion2, i);                //
                //
            }                                                               //
        }                                                                   //
        if(enemycounter==enemycounter2){
            enemycounter=0;
            enemycounter2=0;
            wavemanager();
        }

//        if (enemyArray[1] == null) {                 //
//            enemyArray[1] = createEnemy(1);   // needs to be changed after spawner and wavemanager finished
//        }
//        if (enemyArray[2] == null) {                 //
//            enemyArray[2] = createEnemy(2);   // needs to be changed after spawner and wavemanager finished
//        }
        checkcollisionpowerup(player);
        checkcollisionpowerup(companion);
        checkcollisionpowerup(companion2);

    }

    public void checkcollisions(GameObject o1, GameObject o2, int id) {   //COLLECTIBLES SHOULD BE SEND AS SECOND PARAMETER  I think smth wrong with passing values
        if (o1 instanceof Enemy && o2 instanceof Bullet)  //if first object an enemy and second one enemyArray bullet
            if (o1.hasCollided(o2.loc, o2.dimensions)) {
                if (o1.isDestroyed()) {
                    Timer timer=new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                o1.img= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\askari.png"));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 4000);
                    ((Enemy) o1).destroy();

                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(o1.loc);
                    }

                    enemyArray[id] = null;
                    enemycounter2=enemycounter2+1;
                } else
                    o1.decreaseHealth(o2.getDamage());
            }
        if (o1 instanceof Enemy && o2 instanceof Character) //if first object an enemy and second one an player
            if (o1.hasCollided(o2.loc, o2.dimensions)) {
                o2.decreaseHealth(5);// we need to get every enemies damage
                if (o2.isDestroyed()) {
                    ((Character) o2).destroy();

                }
                if (o1.isDestroyed()) {
                    ((Enemy) o1).destroy();
                    String musicFile = "C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\music\\Explosion+1.wav";

                    sound = new Media(new File(musicFile).toURI().toString());
                    mediaPlayer = new MediaPlayer(sound);
                    Timer timer=new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mediaPlayer.play();
                        }
                    }, 1000);


                    mediaPlayer.stop();

                    if (randomWithRange(0, 1) == 1) {
                        createPowerUp(o1.loc);
                    }
                    enemyArray[id] = null;
                    enemycounter2=enemycounter2+1;
                } else
                    o1.decreaseHealth(5);
                if (o2.openshield()) {
                    o2.closeshield();
                } else {
                    o2.decreaseHealth(5);// we need to get every enemies damage
                    if (o2.isDestroyed()) {
                        ((Character) o2).destroy();

                    }
                }
            }

        if (o1 instanceof Bullet && o2 instanceof Character)
            if (o1.hasCollided(o2.loc, o2.dimensions)) {
                if (o2.openshield()) {
                    o2.closeshield();
                } else {
                    o2.decreaseHealth(5);
                    if (o2.isDestroyed()) {
                        ((Character) o2).destroy();
                        o2.destroy();
                    }
                }
            }
        if (o1 instanceof Enemy) {
            if (o1.getY() >= 486) {
                ((Enemy) o1).destroy();

                enemyArray[id] = null;
                enemycounter2=enemycounter2+1;
            }
        }




    }  //WORKS FINE

    private void checkcollisionpowerup(GameObject o2) {
        if (mayfest!=null)
            if (mayfest.hasCollided(o2.loc, o2.dimensions)) {
                player.movement.setSpeed(2);
                companion.movement.setSpeed(2);
                companion2.movement.setSpeed(2);
                player.bullet.setDamage(2);
                companion.bullet.setDamage(2);
                companion2.bullet.setDamage(2);
                player.decreaseHealth(2);
                mayfest.destroy();
                mayfest = null;

            }
        if (shield!=null)
            if (shield.hasCollided(o2.loc, o2.dimensions)) {
                player.openshield();
                shield.destroy();
                shield = null;

            }

        if (allNighter!=null)
            if (allNighter.hasCollided(o2.loc, o2.dimensions)) {
                player.movement.setSpeed(1);
                companion.movement.setSpeed(1);
                companion2.movement.setSpeed(1);
                allNighter.destroy();
                allNighter = null;

            }

        if (rageMode!=null)
            if (o2.hasCollided(o2.loc, o2.dimensions)) {
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


            }
    }  //looks fine


    public void createPlayer(int i) {//player instance being created
        try {
            Image image=new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\askari.png")) ;
            if(i==0) {//if first character selected
                 image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\askari.png"));
            }
            if(i==1) {//if 2. character selected
                image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\enes.png"));
            }
            if(i==2) {//if 3. character selected
                image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\imran.png"));
            }
            if(i==3) {//if 4. character selected
                image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\alper.png"));
            }
            player = new Character(
                    new Location(500, 300),
                    new Dimension(30),
                    new Movement(0, 0, 5),
                    image,
                    0,
                    root,
                    keyboard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } //done

    public void createCompanions(int i1, int i2) {//companion instances being created
        try {
            Image image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\google.png"));;
            Image image1= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\stackoverflow.png"));
            if(i1==0) {//if first companion selected
                image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\google.png"));
            }
            if(i1==1) {//if 2. companion selected
                image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\google.png"));
            }
            if(i1==2) {//if 3. companion selected
                image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\google.png"));
            }
            if(i1==3) {//if 4. companion selected
                image= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\google.png"));
            }


            if(i2==0) {//if first companion selected
                image1= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\stackoverflow.png"));
            }
            if(i2==1) {//if 2. companion selected
                image1= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\stackoverflow.png"));
            }
            if(i2==2) {//if 3. companion selected
                image1= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\stackoverflow.png"));
            }
            if(i2==3) {//if 4. companion selected
                image1= new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\stackoverflow.png"));
            }


            companion = new Companion(
                        new Location(450, 350),
                        new Dimension(20),
                        new Movement(0, 0, 5),
                        image,
                        0,
                        root,
                        keyboard);


            companion2 = new Companion(
                    new Location(550, 350),
                    new Dimension(20),
                    new Movement(0, 0, 5),
                    image1,
                    0,
                    root,
                    keyboard);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } //done

    private Collectable createPowerUp(Location o1){ // creates enemyArray power up when an enemy dies
            double randomNumber = 4 * Math.random();
            if (randomNumber <= 4) {
                double randomBuff = 5 * Math.random();
                if (randomBuff < 1) { // double bullet
                    try {
                        doubleBullet = new DoubleBullet(
                                new Location(o1.getX(), o1.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\doubleBullet.png")),
                                root);
                       // doubleBullet.renderobject(); //not working
                        return doubleBullet;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (randomBuff > 1 && randomBuff < 2) { // mayfest

                    try {

                        mayfest = new Mayfest(
                                new Location(o1.getX(), o1.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\mayfest.png")),
                                root
                        );
                        mayfest.renderobject();

return mayfest;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (randomBuff > 2 && randomBuff < 3) { // shield

                    try {

                        shield = new Shield(
                                new Location(o1.getX(), o1.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\shield.png")),
                                root
                        );
                        shield.renderobject();
return shield;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                } else if (randomBuff > 3 && randomBuff < 4) { // all nighter

                    try {

                        allNighter = new AllNighter(
                                new Location(o1.getX(), o1.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\allNighter.png")),
                                root
                        );
                        allNighter.renderobject();
return allNighter;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (randomBuff > 4 && randomBuff < 5) { // rage mode

                    try {

                        rageMode = new RageMode(
                                new Location(o1.getX(), o1.getY()),
                                new Dimension(20),
                                new Movement(1, 1, 1),
                                new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\rageMode.png")),
                                root
                        );
                        rageMode.renderobject();
return rageMode;


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

return null;
    } //done

    private Enemy createEnemy( int type) {//enemy instance being created
        try{
            if (type == 0) {
              //  if ( enemyObs == null ) {
                    enemyObs = new Obstacle(
                            new Location(randomWithRange(15,785), randomWithRange(15,85)),
                            new Dimension(15),
                            new Movement(1, 0, 1),
                            new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\deadline.png")),
                            root
                    );

                    enemyObs.renderobject();
                    return enemyObs;
             //   }
            }

            else if (type == 1) {

           //     if ( enemyTA == null ) {
                    enemyTA = new Ta(
                            new Location(randomWithRange(15,785), randomWithRange(15,85)),
                            new Dimension(20),
                            new Movement(1, 0, 1),
                            new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\ercument.png")),
                            root
                    );
                    enemyTA.renderobject();
                return enemyTA;
           //     }
            }

            else if (type == 2) {

           //     if (enemyBoss == null ) {
                    enemyBoss = new Professor(
                            new Location(randomWithRange(15,785), randomWithRange(15,85)),
                            new Dimension(25),
                            new Movement(1, 0, 1),
                            new Image(new FileInputStream("C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\ugur.png")),
                            root

                    );
                    enemyBoss.renderobject();
                return enemyBoss;
             //   }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    } //done
    private void spawner(){
        switch (wavecount) {
            /*3 enemy*/
            case 1:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                break;
            /*4 enemy*/case 2:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                break;
            /*5 enemy*/case 3:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;

                break;
            /*6 enemy*/case 4:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;

                break;
            /*7 enemy*/case 5:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;

                break;
            /*8 enemy*/case 6:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;

                break;
            /*9 enemy*/case 7:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;

                break;
            /*10 enemy*/case 8:
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(1);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(2);
                enemycounter=enemycounter+1;
                enemyArray[enemycounter]=createEnemy(0);
                enemycounter=enemycounter+1;
                System.out.println("Last wave");
                break;

            default:
                enemies.add(createEnemy(0));
                enemycounter=enemycounter+1;
                enemies.add(createEnemy(1));
                enemycounter=enemycounter+1;
                enemies.add(createEnemy(2));
                enemycounter=enemycounter+1;
                break;
        }


    } // not done
    private void wavemanager(){
        if(wavecount==1){
            String musicFile = "C:\\Users\\Enes Varol\\IdeaProjects\\src\\sample\\resources\\video\\alper 1st.mp4";

            sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mediaPlayer.play();
                }
            }, 90000);


            mediaPlayer.stop();
            spawner();
            wavecount=wavecount+1;
        }
        else if(wavecount<9){
            spawner();
            wavecount=wavecount+1;
        }else{
            //You win sceen needed here
            spawner();
        }

    } //not done
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