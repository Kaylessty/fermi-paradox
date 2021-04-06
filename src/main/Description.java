package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A temporary class to display the description of an item, will be replaced once we get a better UI
 */
public class Description {
    public static void display(Item ite) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);
        window.setMaxWidth(400);
        window.setMaxHeight(300);
        window.setMinHeight(300);
        VBox screen = new VBox();
        Text t = new Text(ite.getDiscription());
        t.setWrappingWidth(400);
        screen.getChildren().add(t);
        ScrollPane scroll = new ScrollPane(screen);
        Scene scene = new Scene(scroll,400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
