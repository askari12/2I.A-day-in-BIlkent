package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class Settings {

    MediaPlayer mediaPlayer;
    Media sound;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox musicCheckBox;
    @FXML
    private CheckBox audioCheckBox;
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;


    @FXML
    protected void backButtonListener(ActionEvent event) throws Exception {

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

    public void musicCheckBoxListener(ActionEvent event) {

        if (!musicCheckBox.isSelected()) {
            mediaPlayer.stop();
        }

        String musicFile = "/Users/imran/IdeaProjects/aDayInBilkent/src/sample/music/mainMusic.wav";

        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        if (musicCheckBox.isSelected()) {
            mediaPlayer.play();
        }
        else if (!musicCheckBox.isSelected()) {
            mediaPlayer.stop();
        }
    }

    @FXML
    protected void audioCheckBoxListener(ActionEvent event) {
        audioCheckBox.setSelected(false);
    }

    @FXML
    protected void easyButtonListener(ActionEvent event) throws Exception {
        easyButton.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        mediumButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        hardButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }

    @FXML
    protected void mediumButtonListener(ActionEvent event) throws Exception {
        easyButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        mediumButton.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        hardButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }

    @FXML
    protected void hardButtonListener(ActionEvent event) throws Exception {
        easyButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        mediumButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        hardButton.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }


}
