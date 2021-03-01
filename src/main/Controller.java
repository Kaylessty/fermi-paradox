package main;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.ConfigScreen;
import view.FirstScreen;

/**
 * Main Controller class for the Javafx Dungeon Game.
 */
public class Controller extends Application {
    private Stage window;
    private ConfigScreen screen2;
    private @FXML Label money;
    private @FXML Label health;
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Space Dungeon Game!");
        initFirstScreen();
    }

    private void initFirstScreen() {
        FirstScreen screen = new FirstScreen();
        Button nextButton = screen.getNext();
        Button quitButton = screen.getExit();
        nextButton.setOnAction(e -> goToConfigScreen());
        quitButton.setOnAction(e -> window.close());
        window.setScene(screen.getScene());
        window.show();
    }

    private void goToConfigScreen() {
        screen2 = new ConfigScreen();
        screen2.setConfigScreen();
        window.setScene(screen2.getScene());
        window.show();
        screen2.getProceed().setOnAction(new ProceedHandler());
    }

    private void goToThirdScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/initialgamescreen.fxml"));
        Scene scene = new Scene(root, 800, 600);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * This inner class checks to see if the user can move on to the game screen. 
     * It checks to see if the user entered a name that is not just spaces.
     *  Furthermore, the player must select a name and a weapon.
     */
    class ProceedHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {
            try {
                // If the user name is not blank
                if (!screen2.getNameEntry().getText().equals("") 
                    && screen2.getDifficulties().getSelectedToggle() != null
                        && screen2.getWeapons().getSelectedToggle() != null) {
                    Player.setName(screen2.getNameEntry().getText());
                    Player.setHealth(5000);
                    // If the difficulty is "easy"
                    if (((RadioButton) 
                        (screen2.getDifficulties().getSelectedToggle())).getText().equals("easy")
                        ) {
                        Player.setBalance(3000);
                        Player.setSpeed(10);
                        Player.setStrength(1200);
                        money = new Label(Integer.toString(Player.getBalance()));
                        health = new Label(Integer.toString(Player.getHealth()));
                        // else if the difficulty is "amateur"
                    } else if (((RadioButton) 
                        (screen2.getDifficulties().getSelectedToggle())).getText().equals("amateur")
                    ) {
                        Player.setBalance(2000);
                        Player.setSpeed(7);
                        Player.setStrength(900);
                        money = new Label(Integer.toString(Player.getBalance()));
                        health = new Label(Integer.toString(Player.getHealth()));
                        // else if the difficulty is "hard"
                    } else if (((RadioButton) 
                        (screen2.getDifficulties().getSelectedToggle())).getText().equals("hard")) {
                        Player.setBalance(1000);
                        Player.setSpeed(5);
                        Player.setStrength(600);
                        money = new Label(Integer.toString(Player.getBalance()));
                        health = new Label(Integer.toString(Player.getHealth()));
                    }
                    goToThirdScreen();
                    // if the user name is blank
                } else {
                    Dialog<ButtonType> warningMessage = new Dialog<>();
                    warningMessage.setContentText("You forgot to fill out a field.");
                    ButtonType close = 
                        new ButtonType("Close and try again.", ButtonBar.ButtonData.CANCEL_CLOSE);
                    warningMessage.getDialogPane().getButtonTypes().add(close);
                    Optional<ButtonType> pressed = warningMessage.showAndWait();
                    if (pressed.isPresent()) {
                        warningMessage.hide();
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
