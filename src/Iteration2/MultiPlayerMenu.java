package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MultiPlayerMenu {

    @FXML private Button survivalModeButton;
    @FXML private Button selectFirstCharacterButton;
    @FXML private Button selectSecondCharacterButton;
    @FXML private Button highScoreButton;
    @FXML private Button settingsButton;
    @FXML private Button creditsButton;
    @FXML private Button singlePlayerButton;

    @FXML protected void survivalModeButtonListener() throws Exception {
        Stage gameStage = (Stage) survivalModeButton.getScene().getWindow();
        GameLoop gameLoop = new GameLoop(false, false);
        gameLoop.start(gameStage);
    }

    @FXML protected void selectFirstCharacterButtonListener() throws Exception {

        Stage characterStage = (Stage) selectFirstCharacterButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/selectFirstCharacter.fxml"));
        Parent selectCharacterRoot =  fxmlLoader.load();
        SelectFirstCharacter fxmlDocumentController = fxmlLoader.getController();

        if ( SelectFirstCharacter.getSelectedCharacter().equalsIgnoreCase("askari") ) {
            fxmlDocumentController.isDefault();
        }
        else if ( SelectFirstCharacter.getSelectedCharacter().equalsIgnoreCase("enes") ) {
            fxmlDocumentController.isQuickLearner();
        }
        else if ( SelectFirstCharacter.getSelectedCharacter().equalsIgnoreCase("imran") ) {
            fxmlDocumentController.isHighAchiever();
        }
        else if ( SelectFirstCharacter.getSelectedCharacter().equalsIgnoreCase("alper") ) {
            fxmlDocumentController.isBackBencher();
        }

        Scene selectCharacterScene = new Scene(selectCharacterRoot, 800, 486);
        characterStage.setScene(selectCharacterScene);
    }

    @FXML protected void selectSecondCharacterButtonListener() throws Exception {

        Stage characterStage = (Stage) selectSecondCharacterButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/selectSecondCharacter.fxml"));
        Parent selectCharacterRoot =  fxmlLoader.load();
        SelectSecondCharacter fxmlDocumentController = fxmlLoader.getController();

        if ( SelectSecondCharacter.getSelectedCharacter().equalsIgnoreCase("askari") ) {
            fxmlDocumentController.isDefault();
        }
        else if ( SelectSecondCharacter.getSelectedCharacter().equalsIgnoreCase("enes") ) {
            fxmlDocumentController.isQuickLearner();
        }
        else if ( SelectSecondCharacter.getSelectedCharacter().equalsIgnoreCase("imran") ) {
            fxmlDocumentController.isHighAchiever();
        }
        else if ( SelectSecondCharacter.getSelectedCharacter().equalsIgnoreCase("alper") ) {
            fxmlDocumentController.isBackBencher();
        }

        Scene selectCharacterScene = new Scene(selectCharacterRoot, 800, 486);
        characterStage.setScene(selectCharacterScene);
    }

    @FXML protected void highScoreButtonListener() throws Exception {

        Stage highScoreStage = (Stage) highScoreButton.getScene().getWindow();
        Parent highScoreRoot = FXMLLoader.load(getClass().getResource("layout/highScore.fxml"));

        Scene highScoreScene = new Scene(highScoreRoot, 800, 486);
        highScoreStage.setScene(highScoreScene);
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

    @FXML protected void singlePlayerButtonListener() throws Exception {

        Stage creditsStage = (Stage) singlePlayerButton.getScene().getWindow();
        Parent creditsRoot = FXMLLoader.load(getClass().getResource("layout/singlePlayerMenu.fxml"));

        Scene creditsScene = new Scene(creditsRoot, 800, 486);
        creditsStage.setScene(creditsScene);

        Main.setMode(true);
    }
}
