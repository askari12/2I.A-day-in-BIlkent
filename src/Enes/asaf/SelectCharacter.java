package sample;

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

    private static String characterSelected = "askari";

    void isDefault()
    {
        selectDefault.setText("Cancel");
        selectDefault.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
    }

    void isQuickLearner()
    {
        selectQuickLearner.setText("Cancel");
        selectQuickLearner.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
    }

    void isHighAchiever()
    {
        selectHighAchiever.setText("Cancel");
        selectHighAchiever.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
    }

    void isBackBencher()
    {
        selectBackBencher.setText("Cancel");
        selectBackBencher.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
    }

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
    }

    @FXML public void selectDefaultListener()  {
        if (selectDefault.getText().equalsIgnoreCase("Select"))
        {
            selectDefault.setText("Cancel");
            selectDefault.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            setSelectedCharacter("askari");

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

    @FXML protected void selectQuickLearnerListener() {
        if (selectQuickLearner.getText().equalsIgnoreCase("Select"))
        {
            selectQuickLearner.setText("Cancel");
            selectQuickLearner.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            setSelectedCharacter("enes");

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

    @FXML protected void selectHighAchieverListener() {
        if (selectHighAchiever.getText().equalsIgnoreCase("Select"))
        {

            selectQuickLearner.setText("Select");
            selectQuickLearner.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectDefault.setText("Select");
            selectDefault.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectHighAchiever.setText("Cancel");
            selectHighAchiever.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            setSelectedCharacter("imran");

            selectBackBencher.setText("Select");
            selectBackBencher.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }
        else
        {
            selectHighAchiever.setText("Select");
            selectHighAchiever.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }

    }

    @FXML protected void selectBackBencherListener() {
        if (selectBackBencher.getText().equalsIgnoreCase("Select"))
        {
            selectQuickLearner.setText("Select");
            selectQuickLearner.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectDefault.setText("Select");
            selectDefault.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            selectBackBencher.setText("Cancel");
            selectBackBencher.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");

            setSelectedCharacter("alper");

            selectHighAchiever.setText("Select");
            selectHighAchiever.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }
        else
        {
            selectBackBencher.setText("Select");
            selectBackBencher.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15");
        }
    }

    static String getSelectedCharacter()
    {
        return characterSelected;
    }

    private static void setSelectedCharacter(String name)
    {
        characterSelected = name;
    }

}
