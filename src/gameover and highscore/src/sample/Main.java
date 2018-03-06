package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Game Over");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.show();
     //   primaryStage.close();

        Parent highscore= FXMLLoader.load(getClass().getResource("highscore.fxml"));
        primaryStage.setTitle("High Score");
        primaryStage.setScene(new Scene(highscore, 300, 275));
//        primaryStage.setMaximized(true);
//        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

    }
}
