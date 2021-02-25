package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public class Main extends Application {
    String title = "Space Game!";
    Stage window;
    Scene page1, page2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //page1
        FirstSreen f1 = new FirstSreen();
        f1.next.setOnAction(e -> window.setScene(page2));
        f1.exit.setOnAction(e -> window.close());
        page1 = f1.getScene();

        //page2
        VBox layoutpage2 = new VBox(1);
        page2 = new Scene(layoutpage2, 800, 800);

        //window
        window = primaryStage;
        window.setScene(page1);
        window.setTitle(title);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
