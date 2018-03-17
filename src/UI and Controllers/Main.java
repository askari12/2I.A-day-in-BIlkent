package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setTitle("A day in Bilkent: The Game");
        primaryStage.setScene(new Scene(root, 800, 486));

        // ATTENTION
        // CHANGE MUSIC'S DATAPATH TO THE RESPECTIVE DATAPATH ON THE COMPUTER THAT CURRENTLY USES IT
        String musicFile = "/Users/imran/IdeaProjects/aDayInBilkent/src/sample/music/mainMusic.wav"; // <-- THIS ONE
        // YOU JUST WANT ATTENTION YOU DON'T WANT MY HEART (c) CHARLIE PUTH

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
