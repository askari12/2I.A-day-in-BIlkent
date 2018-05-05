package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOver {

    @FXML private Button backButton;
    @FXML private Label timeSurvived;
    @FXML private Label goldCollected;
    @FXML private Label semestersPassed;
    @FXML private Label score;

    static String coins = "0";
    static String scoreStr = "No HighScores Available";

    @FXML protected void backButtonListener() throws Exception {

        Stage settingsStage = (Stage) backButton.getScene().getWindow();

        boolean mode = Main.getMode();
        Parent settingsRoot;

        if (mode)
            settingsRoot = FXMLLoader.load(getClass().getResource("layout/singlePlayerMenu.fxml"));
        else
            settingsRoot = FXMLLoader.load(getClass().getResource("layout/multiPlayerMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);

        coins = goldCollected.getText();
        scoreStr = score.getText();
    }

    Label getTimeSurvived() {
        return timeSurvived;
    }

    Label getGoldCollected() {
        return goldCollected;
    }

    Label getSemestersPassed() { return semestersPassed; }

    Label getScore() { return score; }

}
