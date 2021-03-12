package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;


import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RoomController extends Application {

    private Room currRoom;
    private Scene scene1;
    private Group root;
    private Maze theMaze;
    private VBox pillar;
    private Stage theStage;

    /*
    public RoomController(Locatable[][] currRoom) {
        this.currRoom = currRoom;
    }
     */

    @Override
    public void start(Stage primaryStage) {
        // This is just to test the accuracy of the program pre Room class
        /*Locatable[][] room1 = new Locatable[18][18];
        room1[0][0] = new Item(Item.Possession.SPACESWORD, 0, 0);
        room1[6][10] = new Item(Item.Possession.SONARGUN, 6, 10);
        currRoom = room1;*/

        // This is to test
        theMaze = new Maze();
        currRoom = theMaze.getRooms()[0];
        currRoom.setDoors(currRoom.getDoors());
        currRoom.addObject(new Item(Item.Possession.SPACESWORD, 0, 0), 0, 0);
        currRoom.addObject(new Item(Item.Possession.SONARGUN, 6, 10), 6, 10);

        //*****************************

        root = new Group();
        pillar = new VBox();
        try {
            ImageView background = new ImageView(new Image("resources/images/Background.png"));
            pillar.getChildren().add(background);
        } catch (IllegalArgumentException e) {
            System.out.println("background pic not found");
        }
            root.getChildren().add(pillar);
            displayRoom();
            scene1 = new Scene(root, 576, 576);
            primaryStage.setScene(scene1);
            primaryStage.show();
            theStage = primaryStage;
    }

    public void displayRoom() {
        for (int row = 0; row < currRoom.getRoom().length; row++) {
            for (int column = 0; column < currRoom.getRoom()[row].length; column++) {
                if (currRoom.getRoom()[row][column] != null) {
                    Locatable important = currRoom.getRoom()[row][column];
                    String imageURL = important.getImageURL();
                    try {
                        Image picture = new Image(imageURL, 50.0, 50.0, true, true);
                        ImageView pictureView = new ImageView(picture);
                        pictureView.setX(row * 32);
                        pictureView.setY(column * 32);
                        root.getChildren().add(pictureView);
                    } catch (IllegalArgumentException e) {
                        System.out.println("The file/image for the item could not be found.");
                    }
                }
            }
        }
        // Display the Doors
        for (int i = 0; i < currRoom.getDoors().length; i++) {
            System.out.println("a door should print");
            Rectangle rectDoor = new Rectangle(560, (i+1)*50, 20, 40);
            final Door d = currRoom.getDoors()[i];
            rectDoor.setOnMouseClicked(e -> changeRoom(d));
            root.getChildren().add(rectDoor);
        }
    }

    private void changeRoom(Door door) {
        if (currRoom.equals(door.getRoomA())) {
            currRoom = door.getRoomB();
        } else {
            currRoom = door.getRoomA();
        }
        root = new Group();
        root.getChildren().add(pillar);
        displayRoom();
        scene1 = new Scene(root, 576, 576);
        theStage.setScene(scene1);
        theStage.setScene(scene1);
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
