package controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import main.Player;
import view.ConfigScreen;

import java.util.Optional;


public class Config_Screen_Controller extends Application {
    ConfigScreen screen;
    //Player player;
    boolean doneWithThisScreen = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        screen = new ConfigScreen();
        screen.setConfigScreen(primaryStage);
        screen.getProceed().setOnAction(new ProceedHandler());
    }

    /**
     * This method allows the Main class to know whether all necessary operations on this screen have been completed
     * @return boolean indicating whether it is safe to move on to next screen.
     */
    public boolean moveOn() {
        return doneWithThisScreen;
    }

    public static void main(String[] args) {
        launch(args);
    }

    class ProceedHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {
            // If the user name is not blank
            if (! screen.getNameEntry().getText().equals("")) {
                Player.setName(screen.getNameEntry().getText());
                Player.setHealth(5000);
                // If the difficulty is "easy"
                if (((RadioButton)(screen.getDifficulties().getSelectedToggle())).getText().equals("easy")) {
                    Player.setBalance(3000);
                    Player.setSpeed(10);
                    Player.setStrength(1200);
                // else if the difficulty is "amateur"
                } else if (((RadioButton)(screen.getDifficulties().getSelectedToggle())).getText().equals("amateur")) {
                    Player.setBalance(2000);
                    Player.setSpeed(7);
                    Player.setStrength(900);
                // else if the difficulty is "hard"
                } else if (((RadioButton)(screen.getDifficulties().getSelectedToggle())).getText().equals("hard")) {
                    Player.setBalance(1000);
                    Player.setSpeed(5);
                    Player.setStrength(600);
                }
                doneWithThisScreen = true;
            // if the user name is blank
            } else {
                Dialog<ButtonType> warningMessage = new Dialog<>();
                warningMessage.setContentText("The player name can't be only spaces.");
                ButtonType close = new ButtonType("Close and try again.", ButtonBar.ButtonData.CANCEL_CLOSE);
                warningMessage.getDialogPane().getButtonTypes().add(close);
                Optional<ButtonType> pressed = warningMessage.showAndWait();
                if (pressed.isPresent()) {
                    warningMessage.hide();
                }
            }
        }
    }

}
