package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent rootNode;
    private Label money;
    private Label health;

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/firstscreen.fxml"));
        rootNode = fxmlLoader.load();
        money = ConfigScreenController.getMoney();
        health = ConfigScreenController.getHealth();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Elon Musk: Alien Destroyer");
        stage.setScene(new Scene(rootNode,800,600));
        stage.show();
    }

}