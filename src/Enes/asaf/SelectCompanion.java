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
    @FXML private Button selectGoogle;
    @FXML private Button selectStackOverflow;
    @FXML private Button selectYahoo;
    @FXML private Button selectYoutube;

    private static String companionRightSelected = "stackoverflow";
    private static String companionLeftSelected = "google";

    void isGoogle()
    {
        selectGoogle.setText("Cancel");
        selectGoogle.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }

    void isStackOverflow()
    {
        selectStackOverflow.setText("Cancel");
        selectStackOverflow.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }

    void isYahoo()
    {
        selectYahoo.setText("Cancel");
        selectYahoo.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }

    void isYoutube()
    {
        selectYoutube.setText("Cancel");
        selectYoutube.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
    }

    @FXML protected void backButtonListener(ActionEvent event) throws Exception {

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

    @FXML protected void selectGoogleListener(ActionEvent event) throws Exception {
        if (selectGoogle.getText().equalsIgnoreCase("Select")) {
            selectGoogle.setText("Cancel");
            selectGoogle.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            setSelectedCompanionLeft("google");

            selectYahoo.setText("Select");
            selectYahoo.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            selectYoutube.setText("Select");
            selectYoutube.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
        else
        {
            selectGoogle.setText("Select");
            selectGoogle.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
    }

    @FXML protected void selectStackOverflowListener() {
        if ( selectStackOverflow.getText().equalsIgnoreCase("Select")) {
            selectStackOverflow.setText("Cancel");
            selectStackOverflow.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            setSelectedCompanionRight("stackoverflow");

            selectYahoo.setText("Select");
            selectYahoo.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            selectYoutube.setText("Select");
            selectYoutube.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
         else
        {
            selectStackOverflow.setText("Select");
            selectStackOverflow.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
    }

    @FXML protected void selectYahooListener()
    {
        if ( selectYahoo.getText().equalsIgnoreCase("Select"))
        {
            selectYahoo.setText("Cancel");
            selectYahoo.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            setSelectedCompanionLeft("yahoo");

            selectStackOverflow.setText("Select");
            selectStackOverflow.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            selectGoogle.setText("Select");
            selectGoogle.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
        else
        {
            selectYahoo.setText("Select");
            selectYahoo.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
    }

    @FXML protected void selectYoutubeListener()
    {
        if ( selectYoutube.getText().equalsIgnoreCase("Select"))
        {
            selectYoutube.setText("Cancel");
            selectYoutube.setStyle("-fx-background-color: #FF6347; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            setSelectedCompanionRight("youtube");

            selectStackOverflow.setText("Select");
            selectStackOverflow.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");

            selectGoogle.setText("Select");
            selectGoogle.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
        else
        {
            selectYoutube.setText("Select");
            selectYoutube.setStyle("-fx-background-color: #94CAFF; -fx-background-radius: 15; -fx-border-color: #FFFFFF; -fx-border-radius: 15;");
        }
    }

    static String getSelectedCompanionRight()
    {
        return companionRightSelected;
    }

    private static void setSelectedCompanionRight(String name)
    {
        companionRightSelected = name;
    }

    static String getSelectedCompanionLeft()
    {
        return companionLeftSelected;
    }

    private static void setSelectedCompanionLeft(String name)
    {
        companionLeftSelected = name;
    }

}
