package main;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class StatScreenController implements Initializable {

@FXML
private Button exitButton;

@FXML
private Label nameLabel;

@FXML
private Label moneyLabel;

@FXML
private Label timeLabel;

@FXML
private Label monstersKilledLabel;

@FXML
private Label damageLabel;

private NumberFormat twoDigitFormat = new DecimalFormat("00");

@FXML
    void exitGame(ActionEvent event) {
    Platform.exit();
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.textProperty().bind(new SimpleStringProperty(Player.getName()));
        moneyLabel.textProperty().bind(Player.getBalance().asString());
        timeLabel.textProperty().bind(new SimpleStringProperty(formatSeconds(LoseScreenController.timeElapsed)));
        damageLabel.textProperty().bind(new SimpleIntegerProperty(Player.getDamageDealt()).asString());
        monstersKilledLabel.textProperty().bind(new SimpleIntegerProperty(Player.getMonstersKilled()).asString());
    }

    public String formatSeconds(int seconds){
    long minutes = seconds / 60;
    long secondsRemainder = seconds - (minutes * 60);
  return twoDigitFormat.format(minutes) + ":" + twoDigitFormat.format(secondsRemainder);
}

}
