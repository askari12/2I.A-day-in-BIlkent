package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class Settings {

    @FXML private Button backButton;
    @FXML private CheckBox musicCheckBox;
    @FXML private CheckBox audioCheckBox;
    @FXML private Button easyButton;
    @FXML private Button mediumButton;
    @FXML private Button hardButton;


    @FXML protected void backButtonListener(ActionEvent event) throws Exception {

        Stage settingsStage = (Stage) backButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

    @FXML protected void musicCheckBoxListener(ActionEvent event) throws Exception {

    }

    @FXML protected void audioCheckBoxListener(ActionEvent event) throws Exception {

    }

    @FXML protected void easyButtonListener(ActionEvent event) throws Exception {
        easyButton.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        mediumButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        hardButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }

    @FXML protected void mediumButtonListener(ActionEvent event) throws Exception {
        easyButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        mediumButton.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        hardButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }
    @FXML protected void hardButtonListener(ActionEvent event) throws Exception {
        easyButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        mediumButton.setStyle("-fx-background-color: #696969; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        hardButton.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }
}
