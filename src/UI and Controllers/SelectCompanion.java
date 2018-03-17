package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectCompanion {

    @FXML private Button backButton;
    @FXML private Button selectCompanionLeft;
    @FXML private Button selectCompanionRight;

    @FXML protected void backButtonListener(ActionEvent event) throws Exception {

        Stage settingsStage = (Stage) backButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

    @FXML protected void selectCompanionLeftListener(ActionEvent event) throws Exception {
        if (selectCompanionLeft.getText().equalsIgnoreCase("Select")) {
            selectCompanionLeft.setText("Cancel");
            selectCompanionLeft.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
        else
        {
            selectCompanionLeft.setText("Select");
            selectCompanionLeft.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
    }

    @FXML protected void selectCompanionRightListener(ActionEvent event) {
        if ( selectCompanionRight.getText().equalsIgnoreCase("Select")) {
            selectCompanionRight.setText("Cancel");
            selectCompanionRight.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
         else
        {
            selectCompanionRight.setText("Select");
            selectCompanionRight.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
    }

}
