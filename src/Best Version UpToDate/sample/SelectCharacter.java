package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectCharacter {

    @FXML private Button backButton;
    @FXML private Button selectDefault;
    @FXML private Button selectQuickLearner;
    @FXML private Button selectHighAchiever;
    @FXML private Button selectBackBencher;


    @FXML protected void backButtonListener(ActionEvent event) throws Exception {

        Stage settingsStage = (Stage) backButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

    @FXML protected void selectDefaultListener(ActionEvent event)  {
        if (selectDefault.getText().equalsIgnoreCase("Select"))
        {
            selectDefault.setText("Cancel");
            selectDefault.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectQuickLearner.setText("Select");
            selectQuickLearner.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectHighAchiever.setText("Select");
            selectHighAchiever.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectBackBencher.setText("Select");
            selectBackBencher.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }
        else
        {
            selectDefault.setText("Select");
            selectDefault.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }

    }

    @FXML protected void selectQuickLearnerListener(ActionEvent event) {
        if (selectQuickLearner.getText().equalsIgnoreCase("Select"))
        {
            selectQuickLearner.setText("Cancel");
            selectQuickLearner.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectDefault.setText("Select");
            selectDefault.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectHighAchiever.setText("Select");
            selectHighAchiever.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectBackBencher.setText("Select");
            selectBackBencher.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

        }
        else
        {
            selectQuickLearner.setText("Select");
            selectQuickLearner.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }

    }

    @FXML protected void selectHighAchieverListener(ActionEvent event) {
        if (selectHighAchiever.getText().equalsIgnoreCase("Select"))
        {

            selectQuickLearner.setText("Select");
            selectQuickLearner.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectDefault.setText("Select");
            selectDefault.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectHighAchiever.setText("Cancel");
            selectHighAchiever.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectBackBencher.setText("Select");
            selectBackBencher.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

        }
        else
        {
            selectHighAchiever.setText("Select");
            selectHighAchiever.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }

    }

    @FXML protected void selectBackBencherListener(ActionEvent event) {
        if (selectBackBencher.getText().equalsIgnoreCase("Select"))
        {

            selectQuickLearner.setText("Select");
            selectQuickLearner.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectDefault.setText("Select");
            selectDefault.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectBackBencher.setText("Cancel");
            selectBackBencher.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectHighAchiever.setText("Select");
            selectHighAchiever.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }
        else
        {
            selectBackBencher.setText("Select");
            selectBackBencher.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }
    }
}
