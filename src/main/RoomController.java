package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class RoomController extends Application {

    private Locatable[][] currRoom;
    private Scene scene1;
    private Group root;

    /*
    public RoomController(Locatable[][] currRoom) {
        this.currRoom = currRoom;
    }
     */

    @Override
    public void start(Stage primaryStage) {
        // This is just to test the accuracy of the program
        Locatable[][] room1 = new Locatable[18][18];
        room1[0][0] = new Item(Item.Possession.SPACESWORD, 0, 0);
        room1[6][10] = new Item(Item.Possession.SONARGUN, 6, 10);
        currRoom = room1;
        //*****************************

        root = new Group();
        VBox pillar = new VBox();
        pillar.setStyle("-fx-background-image: url('resources/images/Background.png');");
        displayRoom();
        root.getChildren().add(pillar);
        scene1 = new Scene(root, 576, 576);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public void displayRoom() {
        for (int row = 0; row < currRoom.length; row++) {
            for (int column = 0; column < currRoom[row].length; column++) {
                if (currRoom[row][column] != null) {
                    Locatable important = currRoom[row][column];
                    String imageURL = important.getImageURL();
                    try {
                        Image picture = new Image(new FileInputStream(imageURL));
                        ImageView pictureView = new ImageView(picture);
                        pictureView.setX(row * 32);
                        pictureView.setY(column * 32);
                        root.getChildren().add(pictureView);
                    } catch (java.io.FileNotFoundException e) {
                        System.out.println("The file/image for the item could not be found.");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
