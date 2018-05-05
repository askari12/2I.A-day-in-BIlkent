package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Keyboard {

    //1st player
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean QPressed;
    private boolean EPressed;

    //2nd player
    private boolean rightArrowPressed;
    private boolean leftArrowPressed;
    private boolean upArrowPressed;
    private boolean downArrowPressed;

    private Stage primaryStage;

    Keyboard(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    void kbInputs(){
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED , t -> {

            if (t.getCode().equals(KeyCode.A)) {
                rightPressed = true;
            }

            if (t.getCode().equals(KeyCode.D)) {
                leftPressed = true;
            }

            if (t.getCode().equals(KeyCode.W)) {
                upPressed = true;
            }

            if (t.getCode().equals(KeyCode.S)) {
                downPressed = true;
            }

            if (t.getCode().equals(KeyCode.Q)) {
                QPressed = true;
            }if (t.getCode().equals(KeyCode.E)) {
                EPressed = true;
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, t -> {
            if (t.getCode().equals(KeyCode.A)) {
                rightPressed = false;
            }

            if (t.getCode().equals(KeyCode.D)) {
                leftPressed = false;
            }

            if (t.getCode().equals(KeyCode.W)) {
                upPressed = false;
            }

            if (t.getCode().equals(KeyCode.S)) {
                downPressed = false;
            }

            if (t.getCode().equals(KeyCode.Q)) {
                QPressed = false;
            }

            if (t.getCode().equals(KeyCode.E)) {
                EPressed = false;
            }

        });
    }

    void kbInputsMult(){
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED , t -> {

            if (t.getCode().equals(KeyCode.LEFT)) {
                rightArrowPressed = true;
            }

            if (t.getCode().equals(KeyCode.RIGHT)) {
                leftArrowPressed = true;
            }

            if (t.getCode().equals(KeyCode.UP)) {
                upArrowPressed = true;
            }

            if (t.getCode().equals(KeyCode.DOWN)) {
                downArrowPressed = true;
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, t -> {
            if (t.getCode().equals(KeyCode.LEFT)) {
                rightArrowPressed = false;
            }

            if (t.getCode().equals(KeyCode.RIGHT)) {
                leftArrowPressed = false;
            }

            if (t.getCode().equals(KeyCode.UP)) {
                upArrowPressed = false;
            }

            if (t.getCode().equals(KeyCode.DOWN)) {
                downArrowPressed = false;
            }
        });
    }

    // 1st player
    boolean getRightPressed() {
        return rightPressed;
    }

    boolean getLeftPressed() {
        return leftPressed;
    }

    boolean getUpPressed() {
        return upPressed;
    }

    boolean getDownPressed() {
        return downPressed;
    }

    public boolean getQPressed() {
        return QPressed;
    }

    public boolean getEPressed() {
        return EPressed;
    }

    // 2nd player
    boolean getRightArrowPressed() { return rightArrowPressed; }

    boolean getLeftArrowPressed() { return leftArrowPressed; }

    boolean getUpArrowPressed() { return upArrowPressed; }

    boolean getDownArrowPressed() { return downArrowPressed; }
}
