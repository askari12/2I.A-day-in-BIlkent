package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Shop{

    @FXML private Button backButton;
    @FXML private Button buyCheatSheet;
    @FXML private Button buyCoffee;
    @FXML private Button buyYemekSepeti;
    @FXML private Button buyMIPSGreenCard;
    @FXML private Label  currentGold;
    @FXML private Label  cheatSheetGold;
    @FXML private Label  coffeeGold;
    @FXML private Label  yemekSepetiGold;
    @FXML private Label  mipsGold;
    @FXML private Label  noGold;

    private static FileManager fm = FileManager.getFileManager();

    private static int coins = fm.getCoins();
    private static String item = "No Item";

    // Prices for the items
    private int cheatSheetPrice = 800;
    private int coffeePrice = 350;
    private int yemekSepetiPrice = 350;
    private int mipsPrice = 350;

    void setCheatSheetPrice()
    {
        cheatSheetGold.setText(String.valueOf(cheatSheetPrice) + " Gold");
    }

    void setCoffeePrice()
    {
        coffeeGold.setText(String.valueOf(coffeePrice) + " Gold");
    }

    void setYemekSepetiPrice()
    {
        yemekSepetiGold.setText(String.valueOf(yemekSepetiPrice) + " Gold");
    }

    void setMipsPrice()
    {
        mipsGold.setText(String.valueOf(mipsPrice) + " Gold");
    }

    void setCurrentGold(int coins)
    {
        currentGold.setText("Current Gold: " + coins);
    }

    @FXML protected void backButtonListener(ActionEvent event) throws Exception {
        fm.updateCoins(coins);
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

    @FXML protected void buyCheatSheetListener(ActionEvent event) throws Exception {

        if (buyCheatSheet.getText().equalsIgnoreCase("Buy")) {
            if (cheatSheetPrice > coins) {
                noGold.setVisible(true);
            } else {

                noGold.setVisible(false);

                // computations
                coins = coins - cheatSheetPrice;
                buyCheatSheet.setText("Equip");
                setCurrentGold(coins);
            }
        }

        if ( buyCheatSheet.getText().equalsIgnoreCase("Equip")) {
            buyCheatSheet.setOnAction(event1 -> buyCheatSheet.setText("Unclothe"));
            setItem("Cheat Sheet");
        }
    }

    @FXML protected void buyCoffeeListener(ActionEvent event) throws Exception {

        if (buyCoffee.getText().equalsIgnoreCase("Buy")) {
            if (coffeePrice > coins) {
                noGold.setVisible(true);
            } else {

                noGold.setVisible(false);

                // computations
                coins = coins - coffeePrice;
                buyCoffee.setText("Equip");
                setCurrentGold(coins);
            }
        }

        if ( buyCoffee.getText().equalsIgnoreCase("Equip")) {
            buyCoffee.setOnAction(event1 -> buyCoffee.setText("Unclothe"));
            setItem("Coffee");
        }
    }

    @FXML protected void buyYemekSepetiListener(ActionEvent event) throws Exception {

        if (buyYemekSepeti.getText().equalsIgnoreCase("Buy")) {
            if (yemekSepetiPrice > coins) {
                noGold.setVisible(true);
            } else {

                noGold.setVisible(false);

                // computations
                coins = coins - yemekSepetiPrice;
                buyYemekSepeti.setText("Equip");
                setCurrentGold(coins);
            }
        }

        if ( buyYemekSepeti.getText().equalsIgnoreCase("Equip")) {
            buyYemekSepeti.setOnAction(event1 -> buyYemekSepeti.setText("Unclothe"));
            setItem("Yemek Sepeti");
        }
    }

    @FXML protected void buyMIPSGreenCardListener(ActionEvent event) throws Exception {

        if (buyMIPSGreenCard.getText().equalsIgnoreCase("Buy")) {
            if (mipsPrice > coins) {
                noGold.setVisible(true);
            } else {

                noGold.setVisible(false);

                // computations
                coins = coins - mipsPrice;
                buyMIPSGreenCard.setText("Equip");
                setCurrentGold(coins);
            }
        }

        if ( buyMIPSGreenCard.getText().equalsIgnoreCase("Equip")) {
            buyMIPSGreenCard.setOnAction(event1 -> buyMIPSGreenCard.setText("Unclothe"));
            setItem("MIPS");
        }
    }


    public static int getCoinsShop() {
        coins = fm.getCoins();
        return coins;
    }

    public static void setCoins(int coins) {
        Shop.coins = coins;
    }
    public static String getItem()
    {
        return item;
    }

    private static void setItem(String item) {
        Shop.item = item;
    }

}

