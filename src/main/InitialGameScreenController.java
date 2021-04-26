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

    private Scene scene;
    protected static long startTime;

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
            startTime = System.currentTimeMillis();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/roomcontroller.fxml"));
            Parent viewParent = loader.load();
            RoomController roomControl = loader.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = roomControl.getScene();
            window.setScene(scene);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Label getMoney() {
        return money;
    }

    public Label getHealth() {
        return health;
    }

}
