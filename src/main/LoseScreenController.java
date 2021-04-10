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

public class LoseScreenController {

    private Scene scene;

    @FXML
    private Button tryAgainButton;

    @FXML
    private Button quitButton;

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void tryAgain(ActionEvent event) {
        try {
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

}
