package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private Parent rootNode;

    private static Stage stage;


    public static void main(final String[] args) {
        Application.launch(args);
    }

    public static Stage getPrimaryStage() {
        return Main.stage;
    }


    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/firstscreen.fxml"));
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Elon Musk: Alien Destroyer");
        stage.setScene(new Scene(rootNode, 800, 600));
        stage.show();
    }

}