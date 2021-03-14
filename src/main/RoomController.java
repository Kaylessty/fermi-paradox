package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;


public class RoomController {

=======

public class RoomController extends Application {

    private Room currRoom;
    private Scene scene1;
    private Group root;
    private Maze theMaze;
    private VBox pillar;
    private Stage theStage = Main.getPrimaryStage();
    private Door lastDoor;
    private Label money;
    private Label health;
    InitialGameScreenController temp = new InitialGameScreenController();

    private Stage theStage;
    private Door lastDoor;

    /*
    public RoomController(Locatable[][] currRoom) {
        this.currRoom = currRoom;
    }
     */

    public RoomController() {
    @Override
    public void start(Stage primaryStage) {
        //Create the maze
        theMaze = new Maze();
        lastDoor = null;
        currRoom = theMaze.getRooms()[0];
        //this is to test
        currRoom.addObject(new Item(Item.Possession.SPACESWORD, 0, 0), 0, 0);
        currRoom.addObject(new Item(Item.Possession.SONARGUN, 6, 10), 6, 10);
        //this is to see what the hatch looks like
        //currRoom.setHasHatch(true);

        //*****************************

        root = new Group();
        pillar = new VBox();
        try {
            ImageView background = new ImageView(new Image("resources/images/room_background.png"));
            pillar.getChildren().add(background);
        } catch (IllegalArgumentException e) {
            System.out.println("background pic not found");
        }
        //Add pillar/background before displayRoom()!

        root.getChildren().add(pillar);
        displayRoom();
        scene1 = new Scene(root, 800, 600);

    }
    /*
    This method displays the current room on the scene.
     */
        root.getChildren().add(pillar);
        displayRoom();
        scene1 = new Scene(root, 800, 600);
        primaryStage.setScene(scene1);
        primaryStage.show();
        theStage = primaryStage;
    }
   /*
   This method displays the current room on the scene.
    */
    public void displayRoom() {
        // For testing: shows the room that we are in
        Text t = new Text(50, 50, currRoom.getRoomName());
        root.getChildren().add(t);

        // Displays the items currently in the room
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
        // Display the hatch if need be
        if (currRoom.getHasHatch()) {
            try {
                Image picture = new Image("resources/images/exit_portal.png", 100.0, 100.0, true, true);
                ImageView pictureView = new ImageView(picture);
                pictureView.setX(350);
                pictureView.setY(250);
                pictureView.setOnMouseClicked(e -> escape());
                root.getChildren().add(pictureView);
            } catch (IllegalArgumentException e) {
                System.out.println("The file/image for the item could not be found.");
            }
        }

        // Display the Doors
        for (int i = 0; i < currRoom.getDoors().length; i++) {
            try {
                final Door d = currRoom.getDoors()[i];
                Image picture;
                if (!d.equals(lastDoor)) {
                    picture = new Image("resources/images/door.png", 20.0, 40.0, true, true);
                } else {
                    picture = new Image("resources/images/other-door.png", 20.0, 40.0, true, true);
                }
                ImageView pictureView = new ImageView(picture);
                if (i % 2 == 1) {
                    pictureView.setX(10);
                } else {
                    pictureView.setX(785);
                }
                pictureView.setY((i+1)*50);
                // No more exceptions
                pictureView.setOnMouseClicked(e -> changeRoom(d));
                root.getChildren().add(pictureView);
            } catch (IllegalArgumentException e) {
                System.out.println("The file/image for the item could not be found.");
            }
        }
    }
    /*
    This method this changes the current room
     */
    private void changeRoom(Door door) {
        System.out.println("changed room");
        lastDoor = door;
        //check which room we should be changing to, could be an issue with room equality
        System.out.println(door.getRoomA().getRoomName());
        if (currRoom.equals(door.getRoomA())) {
            currRoom = door.getRoomB();
        } else {
            currRoom = door.getRoomA();
        }
        // each scene needs its own group
        root = new Group();
        root.getChildren().add(pillar);
        displayRoom();
        scene1 = new Scene(root, 800, 600);
        theStage.setScene(scene1);
        theStage.show();
        // that changed the room
    }
    /*
    This method escapes the maze and displays the ending scene
     */
    public void escape() {
        root = new Group();
        try {
            Image background = new Image("resources/images/Background.png");
            ImageView doneBackground = new ImageView(background);
            root.getChildren().add(doneBackground);
        } catch (IllegalArgumentException e) {
            System.out.println("The file/image for the item could not be found.");
        }
        Text congrats = new Text(375, 280, "YOU ESCAPED!");
        root.getChildren().add(congrats);
        scene1 = new Scene(root, 800, 600);
        theStage.setScene(scene1);
        theStage.show();
    }
    /*
    This method this changes the current room
     */
    private void changeRoom(Door door) {
        System.out.println("changed room");
        lastDoor = door;
        //check which room we should be changing to, could be an issue with room equality
        System.out.println(door.getRoomA().getRoomName());
        if (currRoom.equals(door.getRoomA())) {
            currRoom = door.getRoomB();
        } else {
            currRoom = door.getRoomA();
        }
        // each scene needs its own group
        root = new Group();
        root.getChildren().add(pillar);
        displayRoom();
        scene1 = new Scene(root, 800, 600);

        theStage.setScene(scene1);
        theStage.show();

        // that changed the room
    }
    /*
    This method escapes the maze and displays the ending scene
     */
    public void escape() {
        root = new Group();
        try {
            Image background = new Image("resources/images/Background.png");
            ImageView doneBackground = new ImageView(background);
            root.getChildren().add(doneBackground);
        } catch (IllegalArgumentException e) {
            System.out.println("The file/image for the item could not be found.");
        }
        Text congrats = new Text(375, 280, "YOU ESCAPED!");
        root.getChildren().add(congrats);
        scene1 = new Scene(root, 800, 600);
        theStage.setScene(scene1);
        theStage.show();
    }

    public Scene getScene() {
        return scene1;
    }

}

