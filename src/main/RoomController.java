package main;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private Label money;
    private Label health;
    private InitialGameScreenController temp = new InitialGameScreenController();
    private HBox topRow;
    private HBox bottomRow;
    private Text healthText;
    private Text moneyText;
    private ChoiceBox<Item> inv;
    private Inventory playerInventory;
    private Player player1;
    private Monster[] monstersInRoom;

    /**
     * This class controls the actions that occur within a room including keyEvents and
     * displaying the room
     */
    public RoomController() {

        //Create the maze
        theMaze = new Maze();
        lastDoor = null;
        currRoom = theMaze.getRooms()[0];
        //this is to test
        currRoom.addObject(new Item(Item.Possession.A_ENERGYSWORD, 2, 2,
                "Annihilative Energy Sword"), 2, 2);
        currRoom.addObject(new Item(Item.Possession.A_SHOCKRIFLE, 6, 10,
                "Annihilative Shock Rifle"), 6, 10);
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDSWORD, 4, 11,
                "Improvised Sword"), 4, 11);
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDGUN, 7, 12,
                "Improvised Gun"), 7, 12);
        currRoom.addObject(new Item(Item.Possession.AAID, 9, 10,
                "Administrator ID"), 9, 10);
        currRoom.addObject(new Item(Item.Possession.ONEID, 3, 15,
                "Visitor ID"), 3, 15);

        root = new BorderPane();
        pillar = new VBox();
        //makes Inventory
        playerInventory = new Inventory("Player Inventory ");
        inv = new ChoiceBox<>();
        //
        try {
            ImageView background = new ImageView(
                    new Image("resources/images/room_background.png"));
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
        bottomRow.getChildren().addAll(inv, healthText, health);
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
                            picture = new Image(imageURL, 64, 64, true, true);

                        } else {
                            picture = new Image(imageURL, 32.0, 32.0, true, true);
                        }
                        ImageView pictureView = new ImageView(picture);
                        pictureView.setX(column * 32 + 210);
                        pictureView.setY(row * 32);
                        root.getChildren().add(pictureView);
                        if (important instanceof Door) {
                            pictureView.setOnMouseClicked(e -> changeRoom((Door) important));
                        }
                        if (important instanceof Item) {
                            pictureView.setOnMouseClicked(e -> {
                                pickUp((Item) important);
                                refreshRoom();
                            });
                        }
                        if (important instanceof Player) {
                            System.out.println("Reached instanceof Player");
                            player1 = (Player) important;
                            root.setOnKeyPressed(new MovementController());
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("The file/image for the item could not be found.");
                    }
                }
            }
        }
        // Display the hatch if need be
        if (currRoom.getHasHatch()) {
            try {
                Image picture = new Image("resources/images/exit_portal.png", 100.0,
                        100.0, true, true);
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
     * This method changes the current room
     * @param door The door that connects current room to other room that player will move to
     */
    private void changeRoom(Door door) {
        if (inv.getValue() != null) {
            int idlvl = inv.getValue().getPossession().getIdLevel();
            if (door.getLocked() && idlvl > door.getRoomA().getNumRoom()) {
                System.out.println("door unlocked");
                door.setLocked(false);
            }
        }
        if (door.getLocked()) {
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
        monstersInRoom = currRoom.getMonsters();
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
        //displayRoom();
    }

    /**
     * returns the scene1
     * @return scene1
     */
    public Scene getScene() {
        return scene1;
    }


    /**
     * This class is where player movement functionality should be implemented.
     * In this class, KeyEvents should be handled.
     */
    private class MovementController implements EventHandler<KeyEvent> {

        /**
         * The if-statements will likely go here
         * @param e the key pressed that creates this class
         */
        public void handle(KeyEvent e) {
            System.out.println("Reached inner class");
            if (e.getCode() == KeyCode.W) {
                if (inv.getValue() != null) {
                    Item.Possession carrying = inv.getValue().getPossession();
                    int range = carrying.getRange();
                    boolean foundMonster = false;
                    for (Monster monster : monstersInRoom) {
                        if (Math.hypot((player1.getLocation()[0] - monster.getLocation()[0]),
                                (player1.getLocation()[1] - monster.getLocation()[1])) <= range) {
                            foundMonster = true;
                            // attack monster
                            monster.setHealth(monster.getHealth() - carrying.getDamage());
                            if (monster.getHealth() <= 0) {
                                currRoom.removeObject(monster.getLocation()[0],
                                        monster.getLocation()[1]);
                                System.out.println("Monster killed");
                                refreshRoom();
                            }
                        }
                    }
                    if (!foundMonster) {
                        System.out.println("No monster within range");
                    }
                }
            } else if (e.getCode() == KeyCode.UP) {
                System.out.println("You pressed up");
                int[] pos = player1.getLocation();
                // If the player isn't already at top of room
                if (pos[1] != 0) {
                    if (currRoom.getRoom()[pos[1] - 1][pos[0]] == null
                            || currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Collectible) {
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0],
                                player1.getLocation()[1] - 1);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getUpImageURL();
                        refreshRoom();
                    }
                }
            } else if (e.getCode() == KeyCode.DOWN) {
                System.out.println("You pressed down");
                int[] pos = player1.getLocation();
                // If the player isn't already at bottom of room
                if (pos[1] != currRoom.getRoom()[0].length - 1) {
                    if (currRoom.getRoom()[pos[1] + 1][pos[0]] == null
                            || currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Collectible) {
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0],
                                player1.getLocation()[1] + 1);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getDownImageURL();
                        refreshRoom();
                    }
                }
            } else if (e.getCode() == KeyCode.RIGHT) {
                System.out.println("You pressed right");
                int[] pos = player1.getLocation();
                // If the player isn't already at very right of room
                if (pos[0] != currRoom.getRoom().length - 1) {
                    if (currRoom.getRoom()[pos[1]][pos[0] + 1] == null
                            || currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Collectible) {
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0] + 1,
                                player1.getLocation()[1]);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getRightImageURL();
                        refreshRoom();
                    }
                }
            } else if (e.getCode() == KeyCode.LEFT) {
                System.out.println("You pressed left");
                int[] pos = player1.getLocation();
                // If the player isn't already at very left of room
                if (pos[0] != 0) {
                    if (currRoom.getRoom()[pos[1]][pos[0] - 1] == null
                            || currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Collectible) {
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0] - 1,
                                player1.getLocation()[1]);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getLeftImageURL();
                        refreshRoom();
                    }
                }
            }
        }
    }

}

