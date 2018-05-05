package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static boolean singlePlayer = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("layout/singlePlayerMenu.fxml"));
        primaryStage.setTitle("A day in Bilkent: The Game");
        primaryStage.setScene(new Scene(root, 800, 486));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static boolean getMode()
    {
        return singlePlayer;
    }

    public static void setMode(boolean bool) {
        singlePlayer = bool;
    }

}
