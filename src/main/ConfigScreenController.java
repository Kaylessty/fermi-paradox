package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ConfigScreenController {

    @FXML
    private TextField nameEntry;
    @FXML
    private ToggleGroup difficulties;

    @FXML
    private ToggleGroup weapons;

    @FXML
    private RadioButton amateur;

    @FXML
    private RadioButton hard;

    @FXML
    private RadioButton blaster;

    @FXML
    private RadioButton easy;

    @FXML
    private RadioButton spaceSword;

    @FXML
    private RadioButton sonarGun;

    @FXML
    private Button continueButton;

    private static Label money;
    private static Label health;

    @FXML
    void startGame(ActionEvent event) throws IOException {
        try {
            // If the user name is not blank
            if (!nameEntry.getText().trim().equals("")
                    && difficulties.getSelectedToggle() != null
                    && weapons.getSelectedToggle() != null) {
                Player.setName(nameEntry.getText());
                Player.setHealth(5000);
                // If the difficulty is "easy"
                if (((RadioButton)
                        (difficulties.getSelectedToggle())).getText().equals("Easy")) {
                    Player.setBalance(3000);
                    Player.setSpeed(10);
                    Player.setStrength(1200);
                    // else if the difficulty is "amateur"
                } else if (((RadioButton)
                        (difficulties.getSelectedToggle())).getText().equals("Amateur")
                ) {
                    Player.setBalance(2000);
                    Player.setSpeed(7);
                    Player.setStrength(900);
                    // else if the difficulty is "hard"
                } else if (((RadioButton)
                        (difficulties.getSelectedToggle())).getText().equals("Hard")
                ) {
                    Player.setBalance(1000);
                    Player.setSpeed(5);
                    Player.setStrength(600);
                }
                if (((RadioButton)
                        (weapons.getSelectedToggle())).getText().equals("Laser")) {
                    Maze.setStartItem(new Item(Item.Possession.LASER, 4, 4,
                            "Laser"));
                    // else if the difficulty is "amateur"
                } else if (((RadioButton)
                        (weapons.getSelectedToggle())).getText().equals("Energy Sword")
                ) {
                    Maze.setStartItem(new Item(Item.Possession.ENERGYSWORD, 4, 4,
                            "Energy Sword"));
                    // else if the difficulty is "hard"
                } else if (((RadioButton)
                        (weapons.getSelectedToggle())).getText().equals("Sonic Rifle")
                ) {
                    Maze.setStartItem(new Item(Item.Possession.SONICRIFLE, 4, 4,
                            "Sonic Rifle"));
                }
                //money = new Label("Should get replaced");
                //health = new Label("Should definitely get replaced");
                //money.textProperty().bind(Player.getBalance().asString());
                //health.textProperty().bind(Player.getHealth().asString());
                Parent viewParent =
                        FXMLLoader.load(getClass().getResource("/view/initialgamescreen.fxml"));
                Scene viewScene = new Scene(viewParent, 800, 600);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(viewScene);
                window.setResizable(false);
                window.show();
                // if the user name is blank
            } else {
                Dialog<ButtonType> warningMessage = new Dialog<>();
                warningMessage.setContentText("You forgot to fill out a field.");
                warningMessage.getDialogPane().setPrefSize(420, 420);
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

    public static Label getMoney() {
        return money;
    }

    public static Label getHealth() {
        return health;
    }

    public ToggleGroup getWeapons() {
        return weapons;
    }

}


