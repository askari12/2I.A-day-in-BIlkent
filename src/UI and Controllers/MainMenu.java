package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenu {

    @FXML private Button playGameButton;
    @FXML private Button selectCharacterButton;
    @FXML private Button selectCompanionButton;
    @FXML private Button shopButton;
    @FXML private Button highScoreButton;
    @FXML private Button settingsButton;
    @FXML private Button creditsButton;

    @FXML protected void playGameButtonListener(ActionEvent event) throws Exception {
        Stage gameStage = (Stage) playGameButton.getScene().getWindow();
        Parent gameRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene gameScene = new Scene(gameRoot, 800, 486);
        gameStage.setScene(gameScene);
        GameLoop gloop=new GameLoop();
        gloop.start(gameStage);

    }

    @FXML protected void selectCharacterButtonListener(ActionEvent event) throws Exception {

        Stage characterStage = (Stage) selectCharacterButton.getScene().getWindow();
        Parent selectCharacterRoot = FXMLLoader.load(getClass().getResource("selectCharacter.fxml"));
        Scene selectCharacterScene = new Scene(selectCharacterRoot, 800, 486);
        characterStage.setScene(selectCharacterScene);
    }

    @FXML protected void selectCompanionButtonListener(ActionEvent event) throws Exception {

        Stage companionStage = (Stage) selectCompanionButton.getScene().getWindow();
        Parent selectCompanionRoot = FXMLLoader.load(getClass().getResource("selectCompanion.fxml"));

        Scene selectCompanionScene = new Scene(selectCompanionRoot, 800, 486);
        companionStage.setScene(selectCompanionScene);
    }

    @FXML protected void shopButtonListener(ActionEvent event) throws Exception {

        Stage shopStage = (Stage) shopButton.getScene().getWindow();
        Parent shopRoot = FXMLLoader.load(getClass().getResource("shop.fxml"));

        Scene shopScene = new Scene(shopRoot, 800, 486);
        shopStage.setScene(shopScene);
    }

    @FXML protected void highScoreButtonListener(ActionEvent event) throws Exception {

        Stage highScoreStage = (Stage) highScoreButton.getScene().getWindow();
        Parent highScoreRoot = FXMLLoader.load(getClass().getResource("highScore.fxml"));

        Scene highScoreScene = new Scene(highScoreRoot, 800, 486);
        highScoreStage.setScene(highScoreScene);
    }

    @FXML protected void settingsButtonListener(ActionEvent event) throws Exception {

        Stage settingsStage = (Stage) settingsButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("settings.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

    @FXML protected void creditsButtonListener(ActionEvent event) throws Exception {

        Stage creditsStage = (Stage) creditsButton.getScene().getWindow();
        Parent creditsRoot = FXMLLoader.load(getClass().getResource("credits.fxml"));

        Scene creditsScene = new Scene(creditsRoot, 800, 486);
        creditsStage.setScene(creditsScene);
    }
}
