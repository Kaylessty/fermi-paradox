package main;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.stage.Stage;

public class MonsterController {

    private Monster thisMonster;
    private java.util.Timer timer;
    private TimerTask timerTask;
    private Scene scene1;
    private Pane root;
    //private Stage theStage;
    private Player user;
    private boolean hasBeenAttacked;
    private Room room;
    private Random rNum = new Random();
    private RoomController con;
    private Scene scene;


    // This default constructor should not be used
    private MonsterController() {
    }

    public MonsterController(Monster monster, Scene scene, Pane root, Stage stage, Player user,
                             Room room, RoomController con) {
        thisMonster = monster;
        timer = new java.util.Timer();
        scene1 = scene;
        this.root = root;
        //theStage = stage;
        this.user = user;
        this.room = room;
        this.con = con;
        attack();
    }

    public void attack() {
        if (thisMonster.getHealth() > 0) {
            timerTask = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 500);
        } else {
            timer.cancel();
        }
    }

    private void damageCalculation(ImageView projectile) {
        double xCoord = user.getLocation()[0] * 32 + 210; //*******player1
        double yCoord = user.getLocation()[1] * 32; //****************player1
        // If the center of the player is inside the region of the projectile
        if (xCoord >= projectile.getBoundsInParent().getMinX()
                && xCoord <= projectile.getBoundsInParent().getMaxX()
                && yCoord >= projectile.getBoundsInParent().getMinY()
                && yCoord <= projectile.getBoundsInParent().getMaxY()) {
            System.out.println("Player is taking damage");
            if(thisMonster instanceof TreeBore) {
                thisMonster.setHealth(thisMonster.getHealth() + 5000);
            }
            Platform.runLater(() -> {
                Player.setHealth(Player.getHealth().get() - thisMonster.getDamage());
                System.out.println("Player's new damage: " + Player.getHealth().get());
            });
        }
        if (Player.getHealth().get() <= 0) {
            timer.cancel();
            playerLoses();
        }
    }


    /**
     * If player loses, he can either quit or retry by spawning in maze again.
     */
    public void playerLoses() {
        System.out.println("You lost :(");

        Platform.runLater(() -> {
            try {
                Player.setBalance(3000);
                Player.setHealth(5000);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/losescreen.fxml"));
                Main.getPrimaryStage().setScene(new Scene(loader.load()));
                Main.getPrimaryStage().show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    class Timer extends TimerTask {
        public void run() {
            if (thisMonster.getHealth() <= 0) {
                timer.cancel();
                // If player ran out of room before killing monster
            } else if (room != user.getRoom()) {
                timer.cancel();
                thisMonster.setHealth(thisMonster.getOriginalHealth());
                thisMonster.setHasBeenAttacked(false);
            } else {


                //Circle target = new Circle(user.getLocation()[0] * 32 + 226,
                //user.getLocation()[1] * 32 + 16, 16, Color.DIMGREY);//***********player1
                //target.setStroke(Color.YELLOW);
                //target.setStrokeWidth(4);
                //test of monster differentiation

                if (thisMonster.getType() == "Teaff" || thisMonster.getType() == "Teeth") {
                    int x = 0;
                    int y = 0;
                    if (thisMonster.getLocation()[1] > user.getLocation()[1]) {
                        y = -1;
                    } else if (thisMonster.getLocation()[1] < user.getLocation()[1]) {
                        y = 1;
                    }
                    if (thisMonster.getLocation()[0] > user.getLocation()[0]) {
                        x = -1;
                    } else if (thisMonster.getLocation()[0] < user.getLocation()[0]) {
                        x = 1;
                    }
                    if (room.getRoom()[thisMonster.getLocation()[1] + y][thisMonster.getLocation()[0] + x] == user) {
                        System.out.println("Player is taking damage");
                        Platform.runLater(() -> {
                            Player.setHealth(Player.getHealth().get() - thisMonster.getDamage());
                            System.out.println("Player's new damage: " + Player.getHealth().get());
                        });
                        if (Player.getHealth().get() <= 0) {
                            timer.cancel();
                            playerLoses();
                        }
                    } else if (room.getRoom()[thisMonster.getLocation()[1] + y][thisMonster.getLocation()[0] + x]
                            == null || room.getRoom()[thisMonster.getLocation()[1] + y][thisMonster.getLocation()[0]
                            + x] instanceof Collectible) {
                        room.removeObject(thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
                        thisMonster.setLocation(thisMonster.getLocation()[0] + x, thisMonster.getLocation()[1] + y);
                        room.addObject(thisMonster, thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
                    }
                } else if (thisMonster.getType() == "Howard" || thisMonster.getType() == "Prowler" ||
                        thisMonster.getType() == "Engi") {
                    int x = 0;
                    int y = 0;
                    if (thisMonster.getLocation()[1] > user.getLocation()[1]) {
                        y = -1;
                    } else if (thisMonster.getLocation()[1] < user.getLocation()[1]) {
                        y = 1;
                    }
                    if (thisMonster.getLocation()[0] > user.getLocation()[0]) {
                        x = -1;
                    } else if (thisMonster.getLocation()[0] < user.getLocation()[0]) {
                        x = 1;
                    }
                    if (room.getRoom()[thisMonster.getLocation()[1] + y][thisMonster.getLocation()[0] + x] == user) {
                        System.out.println("Player is taking damage");
                        Platform.runLater(() -> {
                            Player.setHealth(Player.getHealth().get() - thisMonster.getDamage());
                            System.out.println("Player's new damage: " + Player.getHealth().get());
                        });
                        if (Player.getHealth().get() <= 0) {
                            timer.cancel();
                            playerLoses();
                        }
                    } else if (room.getRoom()[thisMonster.getLocation()[1] + y][thisMonster.getLocation()[0] + x]
                            == null || room.getRoom()[thisMonster.getLocation()[1] + y][thisMonster.getLocation()[0]
                            + x] instanceof Collectible) {
                        room.removeObject(thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
                        thisMonster.setLocation(thisMonster.getLocation()[0] + x, thisMonster.getLocation()[1] + y);
                        room.addObject(thisMonster, thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
                    }
                } else if (thisMonster.getType() == "Eyebore") {
                    Image picture = new Image("resources/images/Borepit.png", 75.0,
                            75.0, true, true);
                    ImageView target = new ImageView(picture);
                    target.setX(user.getLocation()[0] * 32 + 210);
                    target.setY(user.getLocation()[1] * 32 - 30);
                }
            }
        }
    }
}
