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

public class LastScreenController {
    @FXML
    private Button newButton;

    @FXML
    private Button exitButton;

    @FXML
    void endGame(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void goToConfigScreen(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("/view/configscreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
