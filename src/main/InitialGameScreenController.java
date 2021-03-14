package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class InitialGameScreenController {

    @FXML
    private Label health;

    @FXML
    private Label money;

    @FXML
    private Button enteringMaze;


    @FXML
    public void initialize() {
        money.textProperty().bind(Player.getBalance().asString());
        health.textProperty().bind(Player.getHealth().asString());
    }

    @FXML
    private void enterMaze(ActionEvent event) {
        try {
            Parent viewParent = FXMLLoader.load(getClass().getResource("/view/roomcontroller.fxml"));
            Scene viewScene = new Scene(viewParent, 800, 600);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.setResizable(false);
            window.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
