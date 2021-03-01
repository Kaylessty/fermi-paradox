package main;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ConfigScreen;
import view.FirstScreen;

/**
 * Main Controller class for the Javafx Dungeon Game.
 */
public class Controller extends Application {
    private Stage window;
    private ConfigScreen screen2;
    private Label money;
    private Label health;
    @Override
    public void start(Stage primaryStage) {
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
        money.setMaxHeight(10);
        health.setMaxHeight(10);
        VBox column = new VBox();
        column.setMinSize(800, 600);
        Parent root = FXMLLoader.load(getClass().getResource("/view/initialgamescreen.fxml"));
        column.getChildren().addAll(money, root, health);
        Scene scene = new Scene(column, 800, 600);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This inner class checks to see if the user can move on to the game screen. 
     * It checks to see if he user entered a name that is not just spaces.
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
                        money = new Label("Money: $" + Integer.toString(Player.getBalance()));
                        money.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3)," 
                            + " 10,0.7,0.0,0.0);"
                            + " -fx-text-fill: yellow; -fx-font: 32pt 'Lao MN'");
                        health = new Label("Health: " + Integer.toString(Player.getHealth()));
                        health.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                            + " 10,0.7,0.0,0.0);" 
                            + " -fx-text-fill: red; -fx-font: 32pt 'Lao MN'");
                        // else if the difficulty is "amateur"
                    } else if (((RadioButton) 
                        (screen2.getDifficulties().getSelectedToggle())).getText().equals("amateur")
                    ) {
                        Player.setBalance(2000);
                        Player.setSpeed(7);
                        Player.setStrength(900);
                        money = new Label("Money: $" + Integer.toString(Player.getBalance()));
                        money.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                            + " 10,0.7,0.0,0.0); -fx-text-fill: yellow; -fx-font: 32pt 'Lao MN'");
                        health = new Label("Health: " + Integer.toString(Player.getHealth()));
                        health.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                            + " 10,0.7,0.0,0.0); -fx-text-fill: red; -fx-font: 32pt 'Lao MN'");
                        // else if the difficulty is "hard"
                    } else if (((RadioButton) 
                        (screen2.getDifficulties().getSelectedToggle())).getText().equals("hard")
                    ) {
                        Player.setBalance(1000);
                        Player.setSpeed(5);
                        Player.setStrength(600);
                        money = new Label("Money: $" + Integer.toString(Player.getBalance()));
                        money.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                            + " 10,0.7,0.0,0.0); -fx-text-fill: yellow; -fx-font: 32pt 'Lao MN'");
                        health = new Label("Health: " + Integer.toString(Player.getHealth()));
                        health.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                            + " 10,0.7,0.0,0.0); -fx-text-fill: red; -fx-font: 32pt 'Lao MN'");
                    }
                    goToThirdScreen();
                    // if the user name is blank
                } else {
                    Dialog<ButtonType> warningMessage = new Dialog<>();
                    warningMessage.setContentText("You forgot to fill out a field.");
                    ButtonType close = new ButtonType("Close and try again.",
                        ButtonBar.ButtonData.CANCEL_CLOSE);
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
