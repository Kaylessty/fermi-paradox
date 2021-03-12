package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InitialGameScreenController {

    @FXML
    private Label health;

    @FXML
    private Label money;


    @FXML
    public void initialize() {
        money.textProperty().bind(Player.getBalance().asString());
        health.textProperty().bind(Player.getHealth().asString());
    }

}
