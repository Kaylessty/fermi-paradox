package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javax.swing.text.Position;

public class RoomController {

    private Room currRoom;
    private Scene scene1;
    private BorderPane root;
    private Maze theMaze;
    private VBox pillar;
    private Stage theStage = Main.getPrimaryStage();
    private Door lastDoor;
    private Label money;
    private Label health;
    private HBox topRow;
    private HBox bottomRow;
    private Text healthText;
    private Text moneyText;
    ChoiceBox<Item> inv;
    Inventory playerInventory;
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
        currRoom.addObject(new Item(Item.Possession.A_ENERGYSWORD, 5, 5, "Annihilative Energy Sword"), 5, 5);
        currRoom.addObject(new Item(Item.Possession.A_SHOCKRIFLE, 6, 10,  "Annihilative Shock Rifle"), 6, 10);
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDSWORD, 4, 11,  "Improvised Sword"), 4, 11);
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDGUN, 7, 12,  "Improvised Gun"), 7, 12);
        currRoom.addObject(new Item(Item.Possession.AAID, 9, 10,  "Administrator ID"), 9, 10);
        currRoom.addObject(new Item(Item.Possession.ONEID, 3, 15,  "Visitor ID"), 3, 15);
        //currRoom.removeObject(6,10);
        //this is to see what the hatch looks like
        //currRoom.setHasHatch(true);

        root = new BorderPane();
        pillar = new VBox();
        //makes Inventory
        playerInventory = new Inventory("Player Inventory ");
        inv = new ChoiceBox<>();
        //
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
        bottomRow.getChildren().addAll(inv,healthText,health);
        topRow.setAlignment(Pos.CENTER);
        bottomRow.setAlignment(Pos.CENTER);
        root.getChildren().add(pillar);
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
                        Image picture;
                        if (important instanceof Item) {
                            picture = new Image(imageURL, ((Item)important).getSize(), ((Item)important).getSize(), true, true);

                        } else {
                            picture = new Image(imageURL, 32.0, 32.0, true, true);
                        }
                        ImageView pictureView = new ImageView(picture);
                        pictureView.setX(column * 32 + 210);//***______________-----------***********
                        pictureView.setY(row * 32);//***______________-----------***********
                        root.getChildren().add(pictureView);
                        //**************************************************************************************************************
                        if (important instanceof Door) {
                            pictureView.setOnMouseClicked(e -> changeRoom((Door)important));
                        }
                        if (important instanceof Item) {
                            pictureView.setOnMouseClicked(e -> {
                                pickUp((Item)important);
                                refreshRoom();
                            });
                        }
                        //**************************************************************************************************************8
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
    }

    /**
     * This method this changes the current room
     */
    private void changeRoom(Door door) {
        if(inv.getValue() != null) {
            int idlvl = inv.getValue().getPossession().getIdLevel();
            if (door.getLocked() == true && idlvl > door.getRoomA().getNumRoom()) {
                System.out.println("door unlocked");
                door.setLocked(false);
            }
        }
        if (door.getLocked() == true) {
            System.out.println("door locked");
            return;
        }
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
        System.out.println(currRoom.getMonsterNum());

        // that changed the room
    }

    /**
     * This method this refreshes the current room
     */
    private void refreshRoom() {

        System.out.println("refresh room");
        // each scene needs its own group
        root = new BorderPane();
        root.getChildren().add(pillar);
        displayRoom();
        scene1 = new Scene(root, 800, 600);
        theStage.setScene(scene1);
        theStage.show();
        System.out.println(currRoom.getMonsterNum());
        // that changed the room
    }

    /**
     * This method escapes the maze and displays the ending scene
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
        congrats.setFill(Color.AQUAMARINE);
        root.getChildren().add(congrats);
        scene1 = new Scene(root, 800, 600);
        theStage.setScene(scene1);
        theStage.show();
    }

    /**
     * this method allows the player to pickup an item
     * @param item Item the player picks up
     */
    public void pickUp(Item item) {
        playerInventory.addItem(item);
        inv.getItems().add(item);
        int[] pos;
        pos = item.getLocation();
        currRoom.removeObject(pos[0], pos[1]);
        displayRoom();
    }

    public void keyReader(int key) {
        //NOTE: I was unable to figure out how to do this with
        //a switch statement, kept getting error, so using if/else.
        scene1.addEventHandler(KeyEvent.KEY_PRESSED, (key1) -> {
            if (key1.getCode() == KeyCode.LEFT) {
                //move left
            } else if (key1.getCode() == KeyCode.RIGHT) {
                //move right
            } else if (key1.getCode() == KeyCode.UP) {
                //move up
            } else if (key1.getCode() == KeyCode.DOWN) {
                //move down
            }
        });

        //further keys can be added to this list for other actions in GameState
        //ex. attack, menu, interact
    }

    /**
     * returns the scene1
     * @return scene1
     */
    public Scene getScene() {
        return scene1;
    }

}

