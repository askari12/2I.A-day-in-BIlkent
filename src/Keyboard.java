package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Keyboard {

    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean spacePressed;
    private boolean QPressed;
    private boolean EPressed;

    private Stage primaryStage;

    public Keyboard(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void kbInputs(){
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED , new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {

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

                if (t.getCode().equals(KeyCode.SPACE)) {
                    spacePressed = true;
                }

                if (t.getCode().equals(KeyCode.Q)) {
                    QPressed = true;
                }if (t.getCode().equals(KeyCode.E)) {
                    EPressed = true;
                }
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
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

                if (t.getCode().equals(KeyCode.SPACE)) {
                    spacePressed = false;
                }

                if (t.getCode().equals(KeyCode.Q)) {
                    QPressed = false;
                }

                if (t.getCode().equals(KeyCode.E)) {
                    EPressed = false;
                }

            }
        });
    }


    // getters

    public boolean getRightPressed() {
        return rightPressed;
    }

    public boolean getLeftPressed() {
        return leftPressed;
    }

    public boolean getUpPressed() {
        return upPressed;
    }

    public boolean getDownPressed() {
        return downPressed;
    }

    public boolean getSpacePressed() {
        return spacePressed;
    }

    public boolean getQPressed() {
        return QPressed;
    }

    public boolean getEPressed() {
        return EPressed;
    }
}
