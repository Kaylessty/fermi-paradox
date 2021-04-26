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



    protected static long endTime = 0;
    public static int timeElapsed;
    public static String timeElapsedFormatted;


    @FXML
    private Button tryAgainButton;

    @FXML
    private Button quitButton;

    @FXML
    void quit(ActionEvent event) {
        Parent tableViewParent = null;
        endTime = System.currentTimeMillis();
        long timeDiff = endTime - InitialGameScreenController.startTime;
        timeElapsed = (int) (timeDiff/1000);
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("/view/statsscreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

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
    public static void setTimeElapsed(int timeElapsed) {
        LoseScreenController.timeElapsed = timeElapsed;
    }
}
