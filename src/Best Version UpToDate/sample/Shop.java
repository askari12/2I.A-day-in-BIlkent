package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Shop {

    @FXML private Button backButton;
    @FXML private Button buyCheatSheet;
    @FXML private Button buyCoffee;
    @FXML private Button buyYemekSepeti;
    @FXML private Button buyMIPSGreenCard;
    @FXML private Label  currentGold;
    @FXML private Label  noGold;

    @FXML protected void backButtonListener(ActionEvent event) throws Exception {

        Stage settingsStage = (Stage) backButton.getScene().getWindow();
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene settingsScene = new Scene(settingsRoot, 800, 486);
        settingsStage.setScene(settingsScene);
    }

    @FXML protected void buyCheatSheetListener(ActionEvent event) throws Exception {
        if (currentGold.getText().equalsIgnoreCase("Current Gold: 0"))
        {
            noGold.setVisible(true);
        }
        else {
            buyCheatSheet.setText("Equip");
        }
    }

    @FXML protected void buyCoffeeListener(ActionEvent event) throws Exception {
        if (currentGold.getText().equalsIgnoreCase("Current Gold: 0"))
        {
            noGold.setVisible(true);
        }
        else {
            buyCoffee.setText("Equip");
        }
    }

    @FXML protected void buyYemekSepetiListener(ActionEvent event) throws Exception {
        if (currentGold.getText().equalsIgnoreCase("Current Gold: 0"))
        {
            noGold.setVisible(true);
        }
        else {
            buyYemekSepeti.setText("Equip");
        }
    }

    @FXML protected void buyMIPSGreenCardListener(ActionEvent event) throws Exception {
        if (currentGold.getText().equalsIgnoreCase("Current Gold: 0"))
        {
            noGold.setVisible(true);
        }
        else {
            buyMIPSGreenCard.setText("Equip");
        }
    }

}

