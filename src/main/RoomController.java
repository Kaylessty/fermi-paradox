package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class RoomController {

    private Room currRoom;
    private Scene scene1;
    private BorderPane root;
    private Maze theMaze;
    private VBox pillar;
    private Stage theStage = Main.getPrimaryStage();
    private Door lastDoor;
    private Label health;
    private Label money;
    private HBox topRow;
    private HBox bottomRow;
    private Text healthText;
    private Text moneyText;
    InitialGameScreenController temp = new InitialGameScreenController();


    /*
    public RoomController(Locatable[][] currRoom) {
        this.currRoom = currRoom;
    }
     */

    public RoomController() {
        //Create the maze
        theMaze = new Maze();
        lastDoor = null;
        currRoom = theMaze.getRooms()[0];
        //this is to test
        currRoom.addObject(new Item(Item.Possession.SPACESWORD, 0, 0), 0, 0);
        currRoom.addObject(new Item(Item.Possession.SONARGUN, 6, 10), 6, 10);
        //this is to see what the hatch looks like

        root = new BorderPane();
        pillar = new VBox();
        try {
            ImageView background = new ImageView(new Image("resources/images/room_background.png"));
            pillar.getChildren().add(background);
        } catch (IllegalArgumentException e) {
            System.out.println("background pic not found");
        }
        //Add pillar/background before displayRoom()!
        health = new Label("");
        money = new Label("");
        topRow = new HBox();
        bottomRow = new HBox();
        moneyText = new Text("$: ");
        healthText = new Text("Health: ");
        moneyText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-fill: yellow; -fx-font: 32pt 'Lao MN'");
        healthText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-fill: red; -fx-font: 32pt 'Lao MN'");
        money.textProperty().bind(Player.getBalance().asString());
        health.textProperty().bind(Player.getHealth().asString());
        money.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-text-fill: yellow; -fx-font: 32pt 'Lao MN'");
        health.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-text-fill: red; -fx-font: 32pt 'Lao MN'");
        topRow.getChildren().addAll(moneyText, money);
        bottomRow.getChildren().addAll(healthText,health);
        topRow.setAlignment(Pos.CENTER);
        bottomRow.setAlignment(Pos.CENTER);
        root.getChildren().addAll(pillar);
        displayRoom();
        scene1 = new Scene(root, 800, 600);

    }
    /*
    This method displays the current room on the scene.
     */
    public void displayRoom() {
        // For testing: shows the room that we are in
        Text t = new Text(50, 50, currRoom.getRoomName());
        root.getChildren().add(t);
        root.setTop(topRow);
        root.setBottom(bottomRow);

        // Displays the items currently in the room
        for (int row = 0; row < currRoom.getRoom().length; row++) {
            for (int column = 0; column < currRoom.getRoom()[row].length; column++) {
                if (currRoom.getRoom()[row][column] != null) {
                    Locatable important = currRoom.getRoom()[row][column];
                    String imageURL = important.getImageURL();
                    try {
                        Image picture = new Image(imageURL, 32.0, 32.0, true, true);
                        ImageView pictureView = new ImageView(picture);
                        pictureView.setX(column * 32 + 210);
                        //***______________-----------***********
                        pictureView.setY(row * 32);
                        //***______________-----------***********
                        root.getChildren().add(pictureView);
                        //**************************************************************************************************************
                        if (important instanceof Door) {
                            pictureView.setOnMouseClicked(e -> changeRoom((Door)important));
                        }
                        //**************************************************************************************************************
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
        /*for (int i = 0; i < currRoom.getDoors().length; i++) {
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
    */
        /**
         * changes the room the player is in
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
        root = new BorderPane();
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
        root = new BorderPane();
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
