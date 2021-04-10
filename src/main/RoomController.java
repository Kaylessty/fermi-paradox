package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

import java.util.ArrayList;
import java.util.List;

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
    private MonsterController[] monsterControllers;
    private List<Node> keepTrack;
    private Item dropped;
    private boolean prowlerL = true;
    private boolean howardL = true;
    private boolean engiL = true;

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
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDSWORD, 4, 11,
                "Improvised Sword"), 4, 11);
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDGUN, 7, 12,
                "Improvised Gun"), 7, 12);
        currRoom.addObject(new Item(Item.Possession.ONEID, 3, 15,
                "Visitor ID"), 3, 15);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 6, 4, "Crate") , 6, 2);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 5, 4, "Crate") , 7, 2);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 6, 5, "Crate") , 6, 3);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 2, 4, "Crate") , 2, 3);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 2, 5, "Crate") , 2, 4);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 16, 10, "Crate") , 16, 14);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 16, 11, "Crate") , 16, 15);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 16, 12, "Crate") , 16, 16);
        //adds items to room
        currRoom.addObject(Maze.getStartItem(), 4, 4);
        root = new BorderPane();
        pillar = new VBox();
        keepTrack = new ArrayList<>();
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
        health.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String str = health.getText();
                int foo = Integer.parseInt(str);
                if (foo < 0) {
                    player1.getDeadImageURL();
                    refreshRoom();
                }
            }
        });
        //keepTrack.add(pillar);//*****************************************************************
        displayRoom();
        scene1 = new Scene(root, 800, 600);
        monstersInRoom = currRoom.getMonsters();
        monsterControllers = new MonsterController[monstersInRoom.length];
    }

    /*
    This method displays the current room on the scene.
     */
    public void displayRoom() {
        // For testing: shows the room that we are in
        Text t = new Text(50, 50, currRoom.getRoomName());
        root.getChildren().add(t);
        keepTrack.add(t);
        root.setTop(topRow);
        root.setBottom(bottomRow);
        keepTrack.add(topRow);
        keepTrack.add(bottomRow);
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

                        } else if(important instanceof ClownShop) {
                            if (howardL == true) {
                                picture = new Image(imageURL, 40.0, 40.0, true, true);
                            } else {
                                picture = null;
                            }
                        } else if(important instanceof ProwlerShop) {
                            if (prowlerL == true) {
                                picture = new Image(imageURL, 35.0, 35.0, true, true);
                            } else {
                                picture = null;
                            }
                        } else if(important instanceof EngiShop) {
                            if (engiL == true) {
                                picture = new Image(imageURL, 35.0, 35.0, true, true);
                            } else {
                                picture = null;
                            }
                        }
                        else {
                            picture = new Image(imageURL, 32.0, 32.0, true, true);
                        }
                        ImageView pictureView = new ImageView(picture);
                        pictureView.setX(column * 32 + 210);
                        pictureView.setY(row * 32);
                        root.getChildren().add(pictureView);
                        keepTrack.add(pictureView);
                        //if (important instanceof Door) {
                        //pictureView.setOnMouseClicked(e -> changeRoom((Door) important));
                        //}
//                        if (important instanceof Item) {
//                            pictureView.setOnMouseClicked(e -> {
//                                pickUp((Item) important);
//                                refreshRoom();
//                            });
//                        }
                        if (important instanceof Player) {
                            //System.out.println("Reached instanceof Player");
                            player1 = (Player) important;
                            root.setOnKeyPressed(new MovementController());
                        }
                        if (important instanceof Monster) {
                            pictureView.setOnMouseClicked(e -> {
                                attack((Monster) important);
                            });
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
                keepTrack.add(pictureView);
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
                door.getCon().setLocked(false);
            }
        }
        if ((door.getLocked() && (inv.getValue() == null || (!(inv.getValue().getImageURL() == "resources/images/TIMKEY.png"))))) {
            System.out.println("door locked");
            return;
        } else if(inv.getValue() != null && inv.getValue().getImageURL() == "resources/images/TIMKEY.png") {
            dropped = inv.getValue();
            Item toDrop = inv.getValue();
            playerInventory.dropItem(toDrop);
            inv.getItems().remove(toDrop);
        }
        System.out.println("changed room");
        lastDoor = door;
        // Remove Player from previous room
        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
        //check which room we should be changing to, could be an issue with room equality
        //System.out.println(door.getRoomA().getRoomName());
        if (currRoom.equals(door.getRoomA())) {
            currRoom = door.getRoomB();
        } else {
            currRoom = door.getRoomA();
        }
        System.out.println(currRoom.getRoomName());
        int doorX = door.getLocation()[0];
        int doorY = door.getLocation()[1];
        // door was at top in previous room
        if (doorX == 8) {
            player1.setLocation(9, 16);
            currRoom.addObject(player1, player1.getLocation()[0], player1.getLocation()[1]);
            // door was at bottom in previous room
        } else if (doorX == 9) {
            player1.setLocation(8, 1);
            currRoom.addObject(player1, player1.getLocation()[0], player1.getLocation()[1]);
            // door was at left in previous room
        } else if (doorX == 0) {
            player1.setLocation(16, 8);
            currRoom.addObject(player1, player1.getLocation()[0], player1.getLocation()[1]);
            // door was at right in previous room
        } else if (doorX == 17) {
            player1.setLocation(1, 9);
            currRoom.addObject(player1, player1.getLocation()[0], player1.getLocation()[1]);
        }
        player1.setRoom(currRoom);
        // each scene needs its own group
        root = new BorderPane();
        root.getChildren().add(pillar);
        keepTrack.clear();
        //keepTrack.add(pillar);//**********************************************************************
        displayRoom();
        scene1 = new Scene(root, 800, 600);

        theStage.setScene(scene1);
        theStage.show();
        System.out.println("Number of monsters:" + currRoom.getMonsterNum());
        System.out.println("Number of doors: " + currRoom.getDoors().length);
        monstersInRoom = currRoom.getMonsters();
        monsterControllers = new MonsterController[monstersInRoom.length];
        /*
        for (int j = 0; j < monstersInRoom.length; j++) {
            monsterControllers[j] = new MonsterController(monstersInRoom[j], scene1, root,
                    theStage, player1);
        }
        */

        // that changed the room
    }

    /**
     * This method this refreshes the current room
     */
    private void refreshRoom() {

        //System.out.println("refresh room");
        // each scene needs its own group
        //root = new BorderPane();
        root.getChildren().removeAll(keepTrack);
        //root.getChildren().add(pillar);//***************************************************************
        displayRoom();
        //scene1 = new Scene(root, 800, 600);
        //theStage.setScene(scene1);
        //theStage.show();
        //System.out.println("Number of monsters: " + currRoom.getMonsterNum());
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

    public void drop() {
        dropped = inv.getValue();
        Item toDrop = inv.getValue();
        playerInventory.dropItem(toDrop);
        inv.getItems().remove(toDrop);
        int[] loc = player1.getLocation();
        loc[1] = loc[1] -1;
        toDrop.setPosition(loc);
        currRoom.addObject(new Item(toDrop.getPossession(), loc[0], loc[1],
                toDrop.getName()), loc[0], loc[1]);
    }

    public void remove(Item toRemove) {
        playerInventory.dropItem(toRemove);
        inv.getItems().remove(toRemove);
    }

    /**
     * returns the scene1
     * @return scene1
     */
    public Scene getScene() {
        return scene1;
    }


    public void attack(Monster monster) {
        refreshRoom();
        if (inv.getValue() != null) {
            Item.Possession carrying = inv.getValue().getPossession();
            int range = carrying.getRange();
            boolean foundMonster = false;
            // Iterating over the monsters in the room
            // if player is close enough to a monster
            double distance = (Math.hypot((player1.getLocation()[0] - monster.getLocation()[0]),
                    (player1.getLocation()[1] - monster.getLocation()[1])));
            if (distance <= range) {
                // attack monster
                if (carrying.getimageURL() == "resources/images/PISTOL.png"
                        || carrying.getimageURL() == "resources/images/RIFLE.png" ||
                        carrying.getimageURL() == "resources/images/H_RIFLE.png") {
                    if (player1.getAmmo() == 0) {
                        System.out.println("no ammo");
                        return;
                    } else {
                        player1.removeAmmo(1);
                        System.out.println(player1.getAmmo());
                    }
                }
                monster.setHealth(monster.getHealth() - (carrying.getDamage() * Player.getGuncharged()));
                Player.setGuncharged(1);
                System.out.println(monster.getHealth());
                // allow monster to attack back, assuming not yet attacked
                if (!monster.getHasBeenAttacked()) {
                    monsterControllers[0] = new MonsterController(monster, scene1, root,
                            theStage, player1, currRoom, this);
                    monster.setHasBeenAttacked(true);
                }
                foundMonster = true;
                // check if monster is alive
                if (monster.getHealth() <= 0) {//***********************************Check back later
                    // Remove monster
                    currRoom.removeObject(monster.getLocation()[0],
                            monster.getLocation()[1]);
                    if(monster.getType() == "Prowler") {
                        prowlerL = false;
                    } else if(monster.getType() == "Howard") {
                        howardL = false;
                    } else if(monster.getType() == "Engi") {
                        engiL = false;
                    }
                    System.out.println("Monster killed");
                    monster.getDrop().setPosition(monster.getLocation());
                    currRoom.addObject(monster.getDrop(), monster.getLocation()[0], monster.getLocation()[1]);
                    currRoom.setMonsterNum(currRoom.getMonsterNum() - 1);
                    if (currRoom.getMonsterNum() == 0) {
                        // Iterate over all doors and unlock them
                        for (Door pathway : currRoom.getDoors()) {
                            pathway.setLocked(false);
                            pathway.getCon().setLocked(false);
                        }
                        // Restore health of Player
                        //player1.setHealth(5000);
                    }
                    refreshRoom();
                }
            }
            if (!foundMonster) {
                if(monster.getType() == "Howard") {
                    ChatScreenController.display(monster, playerInventory, ClownShop.getShopInv(), this);
                } else if(monster.getType() == "Prowler") {
                    ChatScreenController.display(monster, playerInventory, ProwlerShop.getShopInv(), this);
                } else if(monster.getType() == "Engi") {
                    ChatScreenController.display(monster, playerInventory, EngiShop.getShopInv(), this);
                } else {
                    System.out.println("No monster within range");
                }
            }
        }
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
            // Player tries to attack
            if (e.getCode() == KeyCode.E) {
                if(inv.getValue().getType() == "heal") {
                    if(Player.getHealth().get() < Player.getMaxHealth() - inv.getValue().getDamage()) {
                        Player.getHealth().set(Player.getHealth().get() + inv.getValue().getDamage());
                        remove(inv.getValue());
                    } else {
                        Player.getHealth().set(Player.getMaxHealth());
                        remove(inv.getValue());
                    }
                } else if(inv.getValue().getImageURL() == "resources/images/TEAFFHIDE.png") {
                    Player.getHealth().set(Player.getHealth().get() + 500);
                    remove(inv.getValue());
                } else if(inv.getValue().getImageURL() == "resources/images/AMMOBOX.png") {
                    player1.addAmmo(10);
                    remove(inv.getValue());
                } else if(inv.getValue().getType() == "Shield") {
                    Player.setMaxHealth(Player.getMaxHealth() + inv.getValue().getDamage());
                    remove(inv.getValue());
                } else if(inv.getValue().getType() == "charge") {
                    Player.setGuncharged(inv.getValue().getDamage());
                    remove(inv.getValue());
                }
            } else if (e.getCode() == KeyCode.Q && inv.getValue() != null && inv.getValue() != dropped) {
                drop();
                refreshRoom();
            } else if (e.getCode() == KeyCode.P) {
                currRoom.addObject(new Item(Item.Possession.A_ENERGYSWORD, 2, 2,
                        "Annihilative Energy Sword"), 2, 2);
                currRoom.addObject(new Item(Item.Possession.A_SHOCKRIFLE, 6, 10,
                        "Annihilative Shock Rifle"), 6, 10);
                currRoom.addObject(new Item(Item.Possession.AAID, 9, 10,
                        "Administrator ID"), 9, 10);
                refreshRoom();
                // Movement of Player
            } else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                int[] pos = player1.getLocation();
                // If the player isn't already at top of room
                if (pos[1] != 0) {
                    if (currRoom.getRoom()[pos[1] - 1][pos[0]] == null
                            || currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Collectible) {
                        if (currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Collectible) {
                            pickUp((Item)currRoom.getRoom()[pos[1] - 1][pos[0]]);
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0],
                                player1.getLocation()[1] - 1);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getUpImageURL();
                        refreshRoom();
                    } else if (currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Door) {
                        changeRoom((Door)currRoom.getRoom()[pos[1] - 1][pos[0]]);
                    }
                }
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                int[] pos = player1.getLocation();
                // If the player isn't already at bottom of room
                if (pos[1] != currRoom.getRoom()[0].length - 1) {
                    if (currRoom.getRoom()[pos[1] + 1][pos[0]] == null
                            || currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Collectible) {
                        if (currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Collectible) {
                            pickUp((Item)currRoom.getRoom()[pos[1] + 1][pos[0]]);
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0],
                                player1.getLocation()[1] + 1);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getDownImageURL();
                        refreshRoom();
                    } else if (currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Door) {
                        changeRoom((Door)currRoom.getRoom()[pos[1] + 1][pos[0]]);
                    }
                }
            } else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                int[] pos = player1.getLocation();
                // If the player isn't already at very right of room
                if (pos[0] != currRoom.getRoom().length - 1) {
                    if (currRoom.getRoom()[pos[1]][pos[0] + 1] == null
                            || currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Collectible) {
                        if (currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Collectible) {
                            pickUp((Item)currRoom.getRoom()[pos[1]][pos[0] + 1]);
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0] + 1,
                                player1.getLocation()[1]);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getRightImageURL();
                        refreshRoom();
                    } else if (currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Door) {
                        changeRoom((Door)currRoom.getRoom()[pos[1]][pos[0] + 1]);
                    }
                }
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                int[] pos = player1.getLocation();
                // If the player isn't already at very left of room
                if (pos[0] != 0) {
                    if (currRoom.getRoom()[pos[1]][pos[0] - 1] == null
                            || currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Collectible) {
                        if (currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Collectible) {
                            pickUp((Item)currRoom.getRoom()[pos[1]][pos[0] - 1]);
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0] - 1,
                                player1.getLocation()[1]);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        player1.getLeftImageURL();
                        refreshRoom();
                    } else if (currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Door) {
                        changeRoom((Door)currRoom.getRoom()[pos[1]][pos[0] - 1]);
                    }
                }
            }
        }
    }

}