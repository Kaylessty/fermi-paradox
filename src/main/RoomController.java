package main;

//import javafx.application.Platform;
import animatefx.animation.Flash;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.Node;

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
    private VBox healthMoneyVBox;
    private VBox invVBox;
    private HBox bottomRow;
    private Text healthText;
    private Text moneyText;
    private static Random rNum = new Random();

    //@FXML
    //private ChoiceBox<Item> inv;

    private Inventory playerInventory;
    private Player player1;
    private Monster[] monstersInRoom;
    private MonsterAI[] monsterAIs;
    private List<Node> keepTrack;
    private Item dropped;
    private boolean gauntlet = false;
    private boolean engiL;
    private boolean howardL;
    private boolean prowlerL;
    private boolean foundMonster = false;
    private boolean heart = false;
    private ImageView playerView;
    private HashMap<Item, ImageView> itemsInRoom;
    private int monstersAttacked = 0;
    private ToggleGroup invSelect;


    /**
     * This class controls the actions that occur within a room including keyEvents and
     * displaying the room
     */
    public RoomController() {
        //Create the maze
        theMaze = new Maze();
        lastDoor = null;
        engiL = true;
        howardL = true;
        prowlerL = true;
        currRoom = theMaze.getRooms()[0];
        //this is to test
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDSWORD, 4, 11,
                "Improvised Sword"), 4, 11);
        currRoom.addObject(new Item(Item.Possession.IMPROVISEDGUN, 7, 12,
                "Improvised Gun"), 7, 12);
        currRoom.addObject(new Item(Item.Possession.ONEID, 3, 15,
                "Visitor ID"), 3, 15);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 6, 4, "Crate"), 6, 2);
        currRoom.addObject(new Structure(Structure.Possession.C_Chest, 8, 5, "Chest"), 8, 5);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 5, 4, "Crate"), 7, 2);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 6, 5, "Crate"), 6, 3);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 2, 4, "Crate"), 2, 3);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 2, 5, "Crate"), 2, 4);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 16, 10, "Crate"), 16, 14);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 16, 11, "Crate"), 16, 15);
        currRoom.addObject(new Structure(Structure.Possession.CRATE, 16, 12, "Crate"), 16, 16);
        //adds items to room
        currRoom.addObject(Maze.getStartItem(), 4, 4);
        root = new BorderPane();
        pillar = new VBox();
        keepTrack = new ArrayList<>();
        itemsInRoom = new HashMap<>();
        //makes Inventory
        playerInventory = new Inventory("Player Inventory ");
        //inv = new ChoiceBox<>();
        invSelect = new ToggleGroup();
        //inv.setStyle("-fx-background-color: pink; -fx-font: 8pt 'Lao MN'");
        //

        //Add pillar/background before displayRoom()!
        try {
            ImageView background;
            if (currRoom.getRoomType() == 1) {
                background = new ImageView(
                        new Image("resources/images/room_background_a.png"));
            } else if (currRoom.getRoomType() == 2) {
                background = new ImageView(
                        new Image("resources/images/room_background_b.png"));
            } else {
                background = new ImageView(
                        new Image("resources/images/room_background_c.png"));
            }
            pillar.getChildren().add(background);
        } catch (IllegalArgumentException e) {
            System.out.println("background pic not found");
        }
        health = new Label("");
        money = new Label("");
        healthMoneyVBox = new VBox();
        invVBox = new VBox();
        healthMoneyVBox.setPadding(new Insets(30,0,0,100));
        healthMoneyVBox.setSpacing(20);
        bottomRow = new HBox();
        moneyText = new Text(" $: ");
        healthText = new Text(" Health: ");
        moneyText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-fill: yellow; -fx-font: 30pt 'Lao MN'");
        healthText.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-fill: red; -fx-font: 25pt 'Lao MN'");
        money.textProperty().bind(Player.getBalance().asString());
        health.textProperty().bind(Player.getHealth().asString());
        money.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-text-fill: yellow; -fx-font: 25pt 'Lao MN'");
        health.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-text-fill: red; -fx-font: 25pt 'Lao MN'");
        healthMoneyVBox.getChildren().addAll(health, money);
        //bottomRow.getChildren().addAll(health);
        healthMoneyVBox.setAlignment(Pos.TOP_LEFT);
        bottomRow.setAlignment(Pos.TOP_LEFT);
        invVBox.setPadding(new Insets(50,50,50,50));
        CustomItemButton blank = new CustomItemButton();
        blank.setToggleGroup(invSelect);
        Item item1 = Maze.getStartItem();
        blank.setItem(item1);
        Image icon = new Image(item1.getImageURL(), 40.0, 40.0, true, true);
        blank.setGraphic(new ImageView(icon));
        currRoom.removeObject(4, 4);
        invVBox.getChildren().add(blank);
        root.getChildren().add(pillar);
        health.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observableValue, String s, String t1) {
                String str = health.getText();
                int foo = Integer.parseInt(str);
                if (foo < 0) {
                    player1.getDeadImageURL();
                    refreshRoom();
                }
            }
        });
        displayRoom();


        scene1 = new Scene(root, 800, 600);
        monstersInRoom = currRoom.getMonsters();
        monsterAIs = new MonsterAI[monstersInRoom.length];

    }


    /*
    This method displays the current room on the scene.
     */
    public void displayRoom() {
        // For testing: shows the room that we are in
        Text t = new Text(20, 570, currRoom.getRoomName());
        t.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3),"
                + " 10,0.7,0.0,0.0); -fx-fill: white; -fx-font: 12pt 'Lao MN'");
        root.getChildren().add(t);
        keepTrack.add(t);
        root.setTop(healthMoneyVBox);
        root.setBottom(bottomRow);
        root.setLeft(invVBox);
        //keepTrack.add(healthMoneyVBox);
        //keepTrack.add(bottomRow);
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

                        } else if (important instanceof ClownShop) {
                            if (howardL) {
                                picture = new Image(imageURL, 40.0, 40.0, true, true);
                            } else {
                                picture = null;
                            }
                        } else if (important instanceof Watcher || important instanceof Slugger) {
                            picture = new Image(imageURL, 50.0, 50.0, true, true);
                        } else if (important instanceof ProwlerShop) {
                            if (prowlerL) {
                                picture = new Image(imageURL, 35.0, 35.0, true, true);
                            } else {
                                picture = null;
                            }
                        } else if (important instanceof EngiShop) {
                            if (engiL) {
                                picture = new Image(imageURL, 35.0, 35.0, true, true);
                            } else {
                                picture = null;
                            }
                        } else if (important instanceof Larry
                                || important instanceof TreeBore
                                || important instanceof Teeth || important instanceof Challenge) {
                            picture = new Image(imageURL, 200, 200, true, true);
                        } else {
                            picture = new Image(imageURL, 32.0, 32.0, true, true);
                        }
                        ImageView pictureView = new ImageView(picture);
                        pictureView.setX(column * 32 + 210);
                        pictureView.setY(row * 32);
                        root.getChildren().add(pictureView);
                        //if (important instanceof Door) {
                        //    pictureView.setOnMouseClicked(e -> changeRoom((Door) important));
                        keepTrack.add(pictureView);
                        //if (important instanceof Door) {
                        //    pictureView.setOnMouseClicked(e -> changeRoom((Door) important));
                        //}
                        if (important instanceof Item) {
                            if (gauntlet) {
                                pictureView.setOnMouseClicked(e -> {
                                    pickUp((Item) important);
                                    refreshRoom();
                                });
                                itemsInRoom.put((Item) important, pictureView);
                            }
                        } else if (important instanceof Player) {
                            //System.out.println("Reached instanceof Player");
                            player1 = (Player) important;
                            playerView = pictureView;
                            //root.requestFocus();
                            root.setOnKeyPressed(new MovementController());
                        } else if (important instanceof Monster) {
                            pictureView.setOnMouseClicked(e -> {
                                attack((Monster) important, pictureView);
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
                pictureView.setOnMouseClicked(e -> {
                    try {
                        escape();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
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
        Item item1 = null;
        if (invSelect.getSelectedToggle() != null) {
            item1 = ((CustomItemButton) (invSelect.getSelectedToggle())).getItem();
        }
        if (item1 != null) {
            int idlvl = item1.getPossession().getIdLevel();
            if (door.getLocked() && idlvl > door.getRoomA().getNumRoom()) {
                System.out.println("door unlocked");
                door.setLocked(false);
                door.getCon().setLocked(false);
            }
        }
        if ((door.getLocked() && (invSelect.getSelectedToggle() == null
                || (!(item1.getImageURL().equals("resources/images/TIMKEY.png")))))) {
            System.out.println("door locked");
            return;
        } else if (item1 != null && item1.getImageURL().equals("resources/images/TIMKEY.png")) {
            dropped = item1;
            playerInventory.dropItem(dropped);
            invSelect.getToggles().remove(invSelect.getSelectedToggle());
            invVBox.getChildren().remove(invSelect.getSelectedToggle());
        }
        System.out.println("changed room");
        lastDoor = door;
        // Remove Player from previous room
        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
        //check which room we should be changing to, could be an issue with room equality
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
        /* try {
            ImageView background;
            if (currRoom.getRoomType() == 1) {
                background = new ImageView(
                        new Image("resources/images/room_background_a.png"));
            } else if (currRoom.getRoomType() == 2) {
                background = new ImageView(
                        new Image("resources/images/room_background_b.png"));
            } else {
                background = new ImageView(
                        new Image("resources/images/room_background_c.png"));
            }
            pillar.getChildren().add(background);
        } catch (IllegalArgumentException e) {
            System.out.println("background pic not found");
        }
         each scene needs its own group*/
        root = new BorderPane();
        root.getChildren().add(pillar);
        keepTrack.clear();
        //keepTrack.add(pillar);//*******************************************
        displayRoom();
        scene1 = new Scene(root, 800, 600);
        theStage.setScene(scene1);
        theStage.show();
        System.out.println("Number of monsters:" + currRoom.getMonsterNum());
        System.out.println("Number of doors: " + currRoom.getDoors().length);
        monstersInRoom = currRoom.getMonsters();
        monsterAIs = new MonsterAI[monstersInRoom.length];
    }

    /**
     * This method this refreshes the current room
     */
    public void refreshRoom() {
        root.getChildren().removeAll(keepTrack);
        //root.getChildren().add(pillar);//******************************
        displayRoom();
    }

    /**
     * This method escapes the maze and displays the ending scene
     */
    public void escape() throws IOException {
        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - InitialGameScreenController.startTime;
        int timeElapsed = (int) (timeDiff / 1000);
        LoseScreenController.setTimeElapsed(timeElapsed);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/winscreen.fxml"));
        theStage.setScene(new Scene(loader.load()));
        theStage.show();
    }

    /**
     * this method allows the player to pickup an item
     * @param item Item the player picks up
     */
    public void pickUp(Item item) {
        if (playerInventory.getMaximumCapacity()
                > playerInventory.getCurrHousingSpace() + item.getSize()) {
            playerInventory.addItem(item);
            CustomItemButton toAdd = new CustomItemButton();
            toAdd.setItem(item);
            Image icon = new Image(item.getImageURL(), 40.0, 40.0, true, true);
            toAdd.setGraphic(new ImageView(icon));
            toAdd.setToggleGroup(invSelect);
            invVBox.getChildren().add(toAdd);
            //root.requestFocus();
            int[] pos;
            pos = item.getLocation();
            currRoom.removeObject(pos[0], pos[1]);
            //Platform.runLater(() -> root.getChildren().remove(itemsInRoom.get(item)));
            //System.out.println("here");
            //root.getChildren().remove(itemsInRoom.get(item));
            itemsInRoom.remove(item);
            //refreshRoom();
        } else {
            System.out.println("No more room left to pick up items");
            int[] loc = player1.getLocation();
            loc[1] = loc[1] - 2;
            item.setPosition(loc);
            currRoom.addObject(new Item(item.getPossession(), loc[0], loc[1],
                    item.getName()), loc[0], loc[1]);
        }
        refreshRoom();
    }

    public void drop() {
        dropped = ((CustomItemButton) invSelect.getSelectedToggle()).getItem();
        Item toDrop = ((CustomItemButton) invSelect.getSelectedToggle()).getItem();
        playerInventory.dropItem(toDrop);
        invVBox.getChildren().remove(invSelect.getSelectedToggle());
        invSelect.getToggles().remove(invSelect.getSelectedToggle());
        int[] loc = player1.getLocation();
        loc[1] = loc[1] - 1;
        toDrop.setPosition(loc);
        currRoom.addObject(new Item(toDrop.getPossession(), loc[0], loc[1],
                toDrop.getName()), loc[0], loc[1]);
        //itemsInRoom.put(toDrop, new ImageView(toDrop.getImageURL()));
        //itemsInRoom.get(toDrop).setX(9 * 32 + 210);
        //itemsInRoom.get(toDrop).setY(10 * 32);
        //root.getChildren().add(itemsInRoom.get(toDrop));
    }

    public void remove(Item toRemove) {
        CustomItemButton gone = findCustomItemButton(toRemove);
        invVBox.getChildren().remove(gone);
        invSelect.getToggles().remove(gone);
        playerInventory.dropItem(toRemove);
    }

    private CustomItemButton findCustomItemButton(Item findIt) {
        CustomItemButton match = null;
        for (Toggle button : invSelect.getToggles()) {
            if (((CustomItemButton) button).getItem().getImageURL().equals(findIt.getImageURL())) {
                match = (CustomItemButton) button;
            }
        }
        return match;
    }


    /**
     * returns the scene1
     * @return scene1
     */
    public Scene getScene() {
        return scene1;
    }



    public void attack(Monster monster, ImageView monsterView) {
        if (monster instanceof Watcher) {
            refreshRoom();
        }
        if (monster instanceof Challenge) {
            for (int i = 0; i < currRoom.getDoors().length; i++) {
                if (currRoom.getDoors()[i] != null) {
                    currRoom.getDoors()[i].setLocked(true);
                    currRoom.getDoors()[i].getCon().setLocked(true);
                }
            }

            currRoom.getRoom()[monster.getLocation()[0]][monster.getLocation()[1]] = null;
            Monster creature;
            int r = 1 + rNum.nextInt(3);
            if (r == 1) {
                creature = new Larry();
                currRoom.removeObject(
                        creature.getLocation()[0], creature.getLocation()[1]);
                creature.setLocation(8, 8);
                currRoom.addMonster(creature, 10000 * Room.getDifficulty(), 4000);
            } else if (r == 2) {
                creature = new TreeBore();
                currRoom.removeObject(
                        creature.getLocation()[0], creature.getLocation()[1]);
                creature.setLocation(8, 8);
                currRoom.addMonster(creature, 17000 * Room.getDifficulty(), 4000);
            } else if (r == 3) {
                creature = new Teeth();
                currRoom.removeObject(
                        creature.getLocation()[0], creature.getLocation()[1]);
                creature.setLocation(8, 8);
                currRoom.addMonster(creature, 13000 * Room.getDifficulty(), 4000);
            }
            currRoom.setMonsterNum(1);
            refreshRoom();
            return;
        }
        Item weight = ((CustomItemButton) invSelect.getSelectedToggle()).getItem();
        if (weight != null && weight.getPossession().getRange() == 0) {
            if (monster.getType() == "Howard") {
                ChatScreenController.display(
                        monster, playerInventory, ClownShop.getShopInv(), this);
            } else if (monster.getType() == "Prowler") {
                ChatScreenController.display(
                        monster, playerInventory, ProwlerShop.getShopInv(), this);
            } else if (monster.getType() == "Engi") {
                ChatScreenController.display(
                        monster, playerInventory, EngiShop.getShopInv(), this);
            } else {
                System.out.println("No monster within range");
            }
        }
        if (weight != null) {
            Item.Possession carrying = weight.getPossession();
            int range = carrying.getRange();
            // Iterating over the monsters in the room if player is close enough to a monster
            double distance = (Math.hypot((player1.getLocation()[0] - monster.getLocation()[0]),
                    (player1.getLocation()[1] - monster.getLocation()[1])));
            if (monster instanceof Larry || monster instanceof TreeBore) {
                distance = distance - 5;
            }

            if (distance <= range || (distance == 5
                    && weight.getImageURL() == "resources/images/SHADOWSWORD.png")) {
                // attack monster
                if (carrying.getimageURL() == "resources/images/PISTOL.png"
                        || carrying.getimageURL() == "resources/images/RIFLE.png"
                        || carrying.getimageURL() == "resources/images/H_RIFLE.png") {
                    if (player1.getAmmo() == 0) {
                        System.out.println("no ammo");
                        return;
                    } else {
                        player1.removeAmmo(1);
                        System.out.println(player1.getAmmo());
                    }
                }
                monster.setHealth(monster.getHealth()
                        - (carrying.getDamage() * Player.getGuncharged()));
                Player.setGuncharged(1);
                Player.incDamageDealt(carrying.getDamage() * Player.getGuncharged());
                System.out.println(monster.getHealth());
                new Flash(monsterView).play();
                // allow monster to attack back, assuming not yet attacked

                /*if (monster instanceof Monster2) {
                    for (int i = 0; i < monstersInRoom.length; i++) {
                        if (monstersInRoom[i] instanceof Monster2) {
                            if (!monstersInRoom[i].getHasBeenAttacked()) {
                                monsterControllers[monstersAttacked] = new MonsterController(
                                        monstersInRoom[i], scene1, root,
                                        player1, currRoom, this, monsterView);
                                monstersInRoom[i].setHasBeenAttacked(true);
                                monstersAttacked++;
                            }
                        }
                    }
                    //} else if (monster instanceof TreeBore) {
                    //monsterControllers[0] = new MonsterController(monster, scene1, root,
                    //theStage, player1, currRoom, this);
                    //monster.setHasBeenAttacked(true);
                }*/
                if (!monster.getHasBeenAttacked()) {
                    monsterAIs[monstersAttacked] = new MonsterAI(monster, scene1,
                            root, player1, currRoom, this, monsterView);
                    monster.setHasBeenAttacked(true);
                }
                foundMonster = true;
                // check if monster is alive
                if (monster.getHealth() <= 0) { //********Check back later
                    // Remove monster
                    currRoom.removeObject(monster.getLocation()[0],
                            monster.getLocation()[1]);

                    root.getChildren().remove(monsterView);
                    if (monster.getType() == "Prowler") {
                        prowlerL = false;
                    } else if (monster.getType() == "Howard") {
                        howardL = false;
                    } else if (monster.getType() == "Engi") {
                        engiL = false;
                    }
                    System.out.println("Monster killed");
                    Player.incMonstersKilled();
                    if (monster instanceof Slugger) {
                        currRoom.setSlugNum(currRoom.getSlugNum() - 1);
                    }
                    if (monster.getBoss()) {
                        currRoom.setHasHatch(true);
                    }
                    monster.getDrop().setPosition(monster.getLocation());
                    currRoom.addObject(new Item(monster.getDrop().getPossession(),
                                    monster.getLocation()[0], monster.getLocation()[1],
                                    monster.getDrop().getName()),
                            monster.getLocation()[0], monster.getLocation()[1]);
                    //itemsInRoom.put(monster.getDrop(),
                    //        new ImageView(monster.getDrop().getImageURL()));
                    //itemsInRoom.get(monster.getDrop()).setX(monster.getLocation()[0] * 32 + 210);
                    //itemsInRoom.get(monster.getDrop()).setY(monster.getLocation()[1] * 32);
                    //root.getChildren().add(itemsInRoom.get(monster.getDrop()));
                    currRoom.setMonsterNum(currRoom.getMonsterNum() - 1);
                    refreshRoom();
                    if (currRoom.getMonsterNum() == 0) {
                        monstersAttacked = 0;
                        // Iterate over all doors and unlock them
                        for (Door pathway : currRoom.getDoors()) {
                            pathway.setLocked(false);
                            pathway.getCon().setLocked(false);
                        }
                        // Restore health of Player if they have a bore heart
                        if (heart) {
                            if (Player.getHealth().get() < Player.getMaxHealth() - 5000) {
                                Player.setHealth(Player.getHealth().get() + 5000);
                            } else {
                                Player.setHealth(Player.getMaxHealth());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This class is where player movement functionality should be implemented.
     * In this class, KeyEvents should be handled.
     */
    private class MovementController implements EventHandler<KeyEvent> {

        Item weight = null;
        /**
         * The if-statements will likely go here
         * @param e the key pressed that creates this class
         */
        public void handle(KeyEvent e) {
            if (invSelect.getSelectedToggle() != null) {
                weight = ((CustomItemButton) invSelect.getSelectedToggle()).getItem();
            }
            if (e.getCode() == KeyCode.E) {
                handleE();
            } else if (e.getCode() == KeyCode.F) {
                Description.display(weight);
            } else if (e.getCode() == KeyCode.Q
                    && weight != null
                    && weight != dropped) {
                drop();
                refreshRoom();
            } else if (e.getCode() == KeyCode.P) {
                handleP();

                // Movement of Player
            } else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                int[] pos = player1.getLocation();
                // If the player isn't already at top of room
                if (pos[1] != 0) {
                    if (currRoom.getRoom()[pos[1] - 1][pos[0]] == null
                            || (currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Collectible
                            && playerInventory.getMaximumCapacity() > playerInventory.getCurrHousingSpace()
                            + ((Item) currRoom.getRoom()[pos[1] - 1][pos[0]]).getSize())) {
                        if (currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Collectible) {
                            pickUp((Item) currRoom.getRoom()[pos[1] - 1][pos[0]]);
                            //refreshRoom();
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0],
                                player1.getLocation()[1] - 1);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        playerView.setImage(new Image(player1.getUpImageURL()));
                        playerView.setX(player1.getLocation()[0] * 32 + 210);
                        playerView.setY(player1.getLocation()[1] * 32);
                    } else if (currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Door) {
                        changeRoom((Door) currRoom.getRoom()[pos[1] - 1][pos[0]]);
                    } else if (currRoom.getRoom()[pos[1] - 1][pos[0]] instanceof Structure) {
                        Structure struct = (Structure) currRoom.getRoom()[pos[1] - 1][pos[0]];
                        if (!struct.getPossession().getLooted()) {
                            System.out.println("looted");
                            int[] loc = player1.getLocation();
                            loc[0] = loc[0] - 1;
                            Item loot1 = struct.getLV1Loot();
                            Item loot = new Item(
                                    loot1.getPossession(), loc[0],
                                    loc[1], loot1.getName());
                            currRoom.addObject(loot, loc[0], loc[1]);
                            currRoom.addObject(new Structure(Structure.Possession.O_Chest,
                                    struct.getLocation()[0], struct.getLocation()[1], "Chest"),
                                    struct.getLocation()[0], struct.getLocation()[1]);
                            refreshRoom();
                        }
                        /*if (!struct.getPossession().getLooted()) {
                            System.out.println("looted");
                            int[] loc = player1.getLocation();
                            loc[0] = loc[0] - 1;
                            Item loot = new Item(
                                    struct.getPossession().getLoot().getPossession(), loc[0],
                                    loc[1], struct.getPossession().getLoot().getName());
                            struct.getPossession().setLooted(true);
                            currRoom.addObject(loot, loc[0], loc[1]);
                            refreshRoom();
                         }*/
                    }
                }
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                int[] pos = player1.getLocation();
                // If the player isn't already at bottom of room
                if (pos[1] != currRoom.getRoom()[0].length - 1) {
                    if (currRoom.getRoom()[pos[1] + 1][pos[0]] == null
                            || (currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Collectible
                            && playerInventory.getMaximumCapacity() > playerInventory.getCurrHousingSpace()
                            + ((Item) currRoom.getRoom()[pos[1] + 1][pos[0]]).getSize())) {
                        if (currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Collectible) {
                            pickUp((Item) currRoom.getRoom()[pos[1] + 1][pos[0]]);
                            //refreshRoom();
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0],
                                player1.getLocation()[1] + 1);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        playerView.setImage(new Image(player1.getDownImageURL()));
                        playerView.setX(player1.getLocation()[0] * 32 + 210);
                        playerView.setY(player1.getLocation()[1] * 32);
                    } else if (currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Door) {
                        changeRoom((Door) currRoom.getRoom()[pos[1] + 1][pos[0]]);
                        /*} else if (currRoom.getRoom()[pos[1] + 1][pos[0]] instanceof Structure) {
                        Structure struct = (Structure) currRoom.getRoom()[pos[1] - 1][pos[0]];
                        if (!struct.getPossession().getLooted()) {
                            System.out.println("looted");
                            int[] loc = player1.getLocation();
                            loc[0] = loc[0] + 1;
                            Item loot = new Item(
                                    struct.getPossession().getLoot().getPossession(), loc[0],
                                    loc[1], struct.getPossession().getLoot().getName());
                            struct.getPossession().setLooted(true);
                            currRoom.addObject(loot, loc[0], loc[1]);
                            refreshRoom();
                        }*/
                    }
                }
            } else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                int[] pos = player1.getLocation();
                // If the player isn't already at very right of room
                if (pos[0] != currRoom.getRoom().length - 1) {
                    if (currRoom.getRoom()[pos[1]][pos[0] + 1] == null
                            || (currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Collectible
                            && playerInventory.getMaximumCapacity() > playerInventory.getCurrHousingSpace()
                            + ((Item) currRoom.getRoom()[pos[1]][pos[0] + 1]).getSize())) {
                        if (currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Collectible) {
                            pickUp((Item) currRoom.getRoom()[pos[1]][pos[0] + 1]);
                            //refreshRoom();
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0] + 1,
                                player1.getLocation()[1]);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        playerView.setImage(new Image(player1.getRightImageURL()));
                        playerView.setX(player1.getLocation()[0] * 32 + 210);
                        playerView.setY(player1.getLocation()[1] * 32);
                    } else if (currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Door) {
                        changeRoom((Door) currRoom.getRoom()[pos[1]][pos[0] + 1]);
                        /*} else if (currRoom.getRoom()[pos[1]][pos[0] + 1] instanceof Structure) {
                        Structure struct = (Structure) currRoom.getRoom()[pos[1] - 1][pos[0]];
                        if (!struct.getPossession().getLooted()) {
                            System.out.println("looted");
                            int[] loc = player1.getLocation();
                            loc[1] = loc[1] + 1;
                            Item loot = new Item(
                                    struct.getPossession().getLoot().getPossession(), loc[0],
                                    loc[1], struct.getPossession().getLoot().getName());
                            struct.getPossession().setLooted(true);
                            currRoom.addObject(loot, loc[0], loc[1]);
                            refreshRoom();
                        }*/
                    }
                }
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                int[] pos = player1.getLocation();
                // If the player isn't already at very left of room
                if (pos[0] != 0) {
                    if (currRoom.getRoom()[pos[1]][pos[0] - 1] == null
                            || (currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Collectible
                            && playerInventory.getMaximumCapacity() > playerInventory.getCurrHousingSpace()
                            + ((Item) currRoom.getRoom()[pos[1]][pos[0] - 1]).getSize())) {
                        if (currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Collectible) {
                            pickUp((Item) currRoom.getRoom()[pos[1]][pos[0] - 1]);
                            //refreshRoom();
                        }
                        currRoom.removeObject(player1.getLocation()[0], player1.getLocation()[1]);
                        player1.setLocation(player1.getLocation()[0] - 1,
                                player1.getLocation()[1]);
                        currRoom.addObject(player1, player1.getLocation()[0],
                                player1.getLocation()[1]);
                        playerView.setImage(new Image(player1.getLeftImageURL()));
                        playerView.setX(player1.getLocation()[0] * 32 + 210);
                        playerView.setY(player1.getLocation()[1] * 32);
                    } else if (currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Door) {
                        changeRoom((Door) currRoom.getRoom()[pos[1]][pos[0] - 1]);
                        /*} else if (currRoom.getRoom()[pos[1]][pos[0] - 1] instanceof Structure) {
                        /*Structure struct = (Structure) currRoom.getRoom()[pos[1] - 1][pos[0]];
                        if (!struct.getPossession().getLooted()) {
                            System.out.println("looted");
                            int[] loc = player1.getLocation();
                            loc[1] = loc[1] - 1;
                            Item loot = new Item(
                                    struct.getPossession().getLoot().getPossession(), loc[0],
                                    loc[1], struct.getPossession().getLoot().getName());
                            struct.getPossession().setLooted(true);
                            currRoom.addObject(loot, loc[0], loc[1]);
                            refreshRoom();
                        }*/
                    }
                }
            }
        }

        private void handleE() {
            if (weight.getType() == "heal") {
                if (Player.getHealth().get()
                        < Player.getMaxHealth() - weight.getDamage()) {
                    Player.getHealth().set(
                            Player.getHealth().get() + weight.getDamage());
                    remove(weight);
                } else {
                    Player.getHealth().set(Player.getMaxHealth());
                    remove(weight);
                }
            } else if (weight.getImageURL() == "resources/images/TEAFFHIDE.png") {
                Player.getHealth().set(Player.getHealth().get() + 500);
                remove(weight);
            } else if (weight.getImageURL() == "resources/images/AMMOBOX.png") {
                player1.addAmmo(10);
                remove(weight);
            } else if (weight.getType() == "Shield") {
                Player.setMaxHealth(Player.getMaxHealth() + weight.getDamage());
                remove(weight);
            } else if (weight.getType() == "charge") {
                Player.setGuncharged(weight.getDamage());
                remove(weight);
            } else if (weight.getImageURL() == "resources/images/SHADOWGAUNTLET.png") {
                gauntlet = true;
                remove(weight);
            } else if (weight.getImageURL() == "resources/images/BOREHEART.png") {
                heart = true;
                remove(weight);
            }
        }

        private void handleP() {
            Item temp = new Item(Item.Possession.A_ENERGYSWORD, 2, 2,
                    "Annihilative Energy Sword");
            currRoom.addObject(temp, 2, 2);
            //itemsInRoom.put(temp, new ImageView(temp.getImageURL()));
            //itemsInRoom.get(temp).setX(9 * 32 + 210);
            //itemsInRoom.get(temp).setY(10 * 32);
            //root.getChildren().add(itemsInRoom.get(temp));
            temp = new Item(Item.Possession.A_SHOCKRIFLE, 6, 10,
                    "Annihilative Shock Rifle");
            currRoom.addObject(temp, 6, 10);
            //itemsInRoom.put(temp, new ImageView(temp.getImageURL()));
            //itemsInRoom.get(temp).setX(9 * 32 + 210);
            //itemsInRoom.get(temp).setY(10 * 32);
            //root.getChildren().add(itemsInRoom.get(temp));
            temp = new Item(Item.Possession.AAID, 9, 10,
                    "Administrator ID");
            currRoom.addObject(temp, 9, 10);
            currRoom.addObject(temp, 9, 10);
            //itemsInRoom.put(temp, new ImageView(temp.getImageURL()));
            //itemsInRoom.get(temp).setX(9 * 32 + 210);
            //itemsInRoom.get(temp).setY(10 * 32);
            //root.getChildren().add(itemsInRoom.get(temp));
            refreshRoom();
        }
    }

}