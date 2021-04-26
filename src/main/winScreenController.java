package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class winScreenController {
    @FXML
    private Button statsButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Button quitButton;

    @FXML
    void newGame(ActionEvent event) {
        try {
            Player.setHealth(20000);
            Player.setBalance(25000);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/roomcontroller.fxml"));
            Parent parent = loader.load();
            RoomController roomControl = loader.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = roomControl.getScene();
            window.setScene(scene);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    
    }

    @FXML
    void quitGame(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void viewStats(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/statsscreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
