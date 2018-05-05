package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SinglePlayerMenu {

    @FXML private Button storyModeButton;
    @FXML private Button survivalModeButton;
    @FXML private Button selectCharacterButton;
    @FXML private Button selectCompanionButton;
    @FXML private Button shopButton;
    @FXML private Button highScoreButton;
    @FXML private Button settingsButton;
    @FXML private Button creditsButton;
    @FXML private Button multiPlayerButton;

    @FXML protected void storyModeButtonListener() throws Exception {

        Stage gameStage = (Stage) storyModeButton.getScene().getWindow();
        GameLoop gameLoop = new GameLoop(true, true);
        gameLoop.start(gameStage);
    }

    @FXML protected void survivalModeButtonListener() throws Exception {
        Stage gameStage = (Stage) survivalModeButton.getScene().getWindow();
        GameLoop gameLoop = new GameLoop(true, false);
        gameLoop.start(gameStage);
    }

    @FXML protected void selectCharacterButtonListener() throws Exception {

        Stage characterStage = (Stage) selectCharacterButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/selectCharacter.fxml"));
        Parent selectCharacterRoot =  fxmlLoader.load();
        SelectCharacter fxmlDocumentController = fxmlLoader.getController();

        if ( SelectCharacter.getSelectedCharacter().equalsIgnoreCase("askari") ) {
            fxmlDocumentController.isDefault();
        }
        else if ( SelectCharacter.getSelectedCharacter().equalsIgnoreCase("enes") ) {
            fxmlDocumentController.isQuickLearner();
        }
        else if ( SelectCharacter.getSelectedCharacter().equalsIgnoreCase("imran") ) {
            fxmlDocumentController.isHighAchiever();
        }
        else if ( SelectCharacter.getSelectedCharacter().equalsIgnoreCase("alper") ) {
            fxmlDocumentController.isBackBencher();
        }

        Scene selectCharacterScene = new Scene(selectCharacterRoot, 800, 486);
        characterStage.setScene(selectCharacterScene);

    }

    @FXML protected void selectCompanionButtonListener() throws Exception {

        Stage companionStage = (Stage) selectCompanionButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/selectCompanion.fxml"));
        Parent selectCompanionRoot =  fxmlLoader.load();
        SelectCompanion fxmlDocumentController = fxmlLoader.getController();

        if ( SelectCompanion.getSelectedCompanionLeft().equalsIgnoreCase("google") ) {
            fxmlDocumentController.isGoogle();
        }

        if ( SelectCompanion.getSelectedCompanionRight().equalsIgnoreCase("stackoverflow") ) {
            fxmlDocumentController.isStackOverflow();
        }

        if ( SelectFirstCharacter.getSelectedCharacter().equalsIgnoreCase("youtube"))
        {
            fxmlDocumentController.isYoutube();
        }

        if ( SelectFirstCharacter.getSelectedCharacter().equalsIgnoreCase("yahoo"))
        {
            fxmlDocumentController.isYahoo();
        }

        Scene selectCharacterScene = new Scene(selectCompanionRoot, 800, 486);
        companionStage.setScene(selectCharacterScene);
    }

    @FXML protected void shopButtonListener() throws Exception {

        Stage shopStage = (Stage) shopButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/shop.fxml"));
        Parent shopRoot =  fxmlLoader.load();
        Shop fxmlDocumentController = fxmlLoader.getController();

        Shop.setCoins(Integer.parseInt(GameOver.coins));

        // setting current gold
        fxmlDocumentController.setCurrentGold(Shop.getCoins());

        // setting prices
        fxmlDocumentController.setCheatSheetPrice();
        fxmlDocumentController.setCoffeePrice();
        fxmlDocumentController.setYemekSepetiPrice();
        fxmlDocumentController.setMipsPrice();

        Scene shopScene = new Scene(shopRoot, 800, 486);
        shopStage.setScene(shopScene);
    }

    @FXML protected void highScoreButtonListener() throws Exception {

        Stage shopStage = (Stage) shopButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/highScore.fxml"));
        Parent shopRoot =  fxmlLoader.load();
        HighScore fxmlDocumentController = fxmlLoader.getController();

        HighScore.setScores(GameOver.scoreStr);

        // setting current gold
        fxmlDocumentController.setHighScores(HighScore.getScores());

        Scene shopScene = new Scene(shopRoot, 800, 486);
        shopStage.setScene(shopScene);
    }

    @FXML protected void settingsButtonListener() throws Exception {

        Stage settingsStage = (Stage) settingsButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("layout/settings.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

    @FXML protected void creditsButtonListener() throws Exception {

        Stage creditsStage = (Stage) creditsButton.getScene().getWindow();
        Parent creditsRoot = FXMLLoader.load(getClass().getResource("layout/credits.fxml"));

        Scene creditsScene = new Scene(creditsRoot, 800, 486);
        creditsStage.setScene(creditsScene);
    }

    @FXML protected void multiPlayerButtonListener() throws Exception {

        Stage creditsStage = (Stage) multiPlayerButton.getScene().getWindow();
        Parent creditsRoot = FXMLLoader.load(getClass().getResource("layout/multiPlayerMenu.fxml"));

        Scene creditsScene = new Scene(creditsRoot, 800, 486);
        creditsStage.setScene(creditsScene);

        Main.setMode(false);
    }
}
