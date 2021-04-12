package main;


import javafx.application.Platform;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.stage.Stage;

public class MonsterController {

    private Monster thisMonster;
    private Timer timer;
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
        timer = new Timer();
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
            timerTask = new InnerClass();
            timer.scheduleAtFixedRate(timerTask, 0, 500);
        } else {
            timer.cancel();
        }
    }

    private void damageCalculation(ImageView projectile) {
        double xCoord = user.getLocation()[0] * 32 + 210; //******player1
        double yCoord = user.getLocation()[1] * 32; //************player1
        // If the center of the player is inside the region of the projectile
        /*
        System.out.println("Projectile Min X: " + projectile.getBoundsInParent().getMinX());
        System.out.println("Projectile Max X: " + projectile.getBoundsInParent().getMaxX());
        System.out.println("Projectile Min Y: " + projectile.getBoundsInParent().getMinY());
        System.out.println("Projectile Max Y: " + projectile.getBoundsInParent().getMaxY());
        System.out.println("Player's X:" + x_coord);
        System.out.println("Player's Y: " + y_coord);
         */
        if (xCoord >= projectile.getBoundsInParent().getMinX()
                && xCoord <= projectile.getBoundsInParent().getMaxX()
                && yCoord >= projectile.getBoundsInParent().getMinY()
                && yCoord <= projectile.getBoundsInParent().getMaxY()) {
            System.out.println("Player is taking damage");
            if (thisMonster instanceof TreeBore) {
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

    class InnerClass extends TimerTask {
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
                //        user.getLocation()[1] * 32 + 16, 16, Color.DIMGREY);//***********player1
                //target.setStroke(Color.YELLOW);
                //target.setStrokeWidth(4);

                //test of monster differentiation
                if (thisMonster.getType() == "Teaff" || thisMonster.getType() == "Teeth") {
                    runTeeth();
                } else if (thisMonster.getType() == "Howard" || thisMonster.getType() == "Prowler"
                        || thisMonster.getType() == "Engi") {
                    runHoward();
                }  else if (thisMonster.getType() == "Eyebore") {
                    runEyebore();
                } else if (thisMonster.getType() == "Tree Bore") {
                    runTreeBore();
                } else if (thisMonster.getType() == "Tim" || thisMonster.getType() == "Larry") {
                    runTim();
                }
            }
        }

        private void runTeeth() {
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
            int monstX = thisMonster.getLocation()[0] + x;
            int monstY = thisMonster.getLocation()[1] + y;
            if (room.getRoom()[monstY][monstX] == user) {
                System.out.println("Player is taking damage");
                Platform.runLater(() -> {
                    Player.setHealth(Player.getHealth().get() - thisMonster.getDamage());
                    System.out.println("Player's new damage: " + Player.getHealth().get());
                });
                if (Player.getHealth().get() <= 0) {
                    timer.cancel();
                    playerLoses();
                }
            } else if (room.getRoom()[monstY][monstX] == null
                    || room.getRoom()[monstY][monstX] instanceof Collectible) {
                room.removeObject(thisMonster.getLocation()[0],
                        thisMonster.getLocation()[1]);
                thisMonster.setLocation(monstX, monstY);
                room.addObject(thisMonster,
                        thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
            }
        }

        private void runHoward() {
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
            int monstX = thisMonster.getLocation()[0] + x;
            int monstY = thisMonster.getLocation()[1] + y;
            if (room.getRoom()[monstY][monstX] == user) {
                System.out.println("Player is taking damage");
                Platform.runLater(() -> {
                    Player.setHealth(Player.getHealth().get()
                            - thisMonster.getDamage());
                    System.out.println(
                            "Player's new damage: " + Player.getHealth().get());
                });
                if (Player.getHealth().get() <= 0) {
                    timer.cancel();
                    playerLoses();
                }
            } else if (room.getRoom()[monstY][monstX] == null
                    || room.getRoom()[monstY][monstX] instanceof Collectible) {
                room.removeObject(thisMonster.getLocation()[0],
                        thisMonster.getLocation()[1]);
                thisMonster.setLocation(monstX, monstY);
                room.addObject(thisMonster,
                        thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
            }
        }

        private void runEyebore() {
            Image picture = new Image("resources/images/Borepit.png", 75.0,
                    75.0, true, true);
            ImageView target = new ImageView(picture);
            target.setX(user.getLocation()[0] * 32 + 210);
            target.setY(user.getLocation()[1] * 32 - 30);
            //@
            Platform.runLater(() -> {
                root.getChildren().add(target);
            });
            //scene1.setRoot(root);
            //theStage.setScene(scene1);
            //new Flash(target).setCycleCount(3).playOnFinished(new FadeOut(target)).play();
            //target.setVisible(false);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            Image picture1 = new Image("resources/images/Borespike.png", 75.0,
                    75.0, true, true);
            ImageView projectile = new ImageView(picture1);
            projectile.setX(target.getX());
            projectile.setY(target.getY());

            //Circle projectile = new Circle(target.getCenterX(), target.getCenterY(),
            //      target.getRadius(), Color.DARKRED);
            Platform.runLater(() -> {
                root.getChildren().add(projectile);
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            //scene1.setRoot(root);
            //theStage.setScene(scene1);
            //new FadeOut(projectile).play();
            MonsterController.this.damageCalculation(projectile);
        }

        private void runTreeBore() {
            Image picture = new Image("resources/images/Borepit.png", 75.0,
                    75.0, true, true);
            ImageView target = new ImageView(picture);
            target.setX(user.getLocation()[0] * 32 + 210);
            target.setY(user.getLocation()[1] * 32 - 30);
            ImageView targeta = new ImageView(picture);
            targeta.setX((user.getLocation()[0] + 1) * 32 + 210);
            targeta.setY(user.getLocation()[1] * 32 - 30);
            ImageView targetb = new ImageView(picture);
            targetb.setX((user.getLocation()[0] - 1) * 32 + 210);
            targetb.setY(user.getLocation()[1] * 32 - 30);
            ImageView targetc = new ImageView(picture);
            targetc.setX(user.getLocation()[0] * 32 + 210);
            targetc.setY((user.getLocation()[1] + 1) * 32 - 30);
            ImageView targetd = new ImageView(picture);
            targetd.setX(user.getLocation()[0] * 32 + 210);
            targetd.setY((user.getLocation()[1] - 1) * 32 - 30);
            //@
            Platform.runLater(() -> {
                root.getChildren().addAll(target, targeta, targetb, targetc, targetd);
            });
            //scene1.setRoot(root);
            //theStage.setScene(scene1);
            //new Flash(target).setCycleCount(3).playOnFinished(new FadeOut(target)).play();
            //target.setVisible(false);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            Image picture1 = new Image("resources/images/Borespike.png", 75.0,
                    75.0, true, true);
            ImageView projectile = new ImageView(picture1);
            projectile.setX(target.getX());
            projectile.setY(target.getY());
            ImageView projectilea = new ImageView(picture1);
            projectilea.setX(targeta.getX());
            projectilea.setY(targeta.getY());
            ImageView projectileb = new ImageView(picture1);
            projectileb.setX(targetb.getX());
            projectileb.setY(targetb.getY());
            ImageView projectilec = new ImageView(picture1);
            projectilec.setX(targetc.getX());
            projectilec.setY(targetc.getY());
            ImageView projectiled = new ImageView(picture1);
            projectiled.setX(targetd.getX());
            projectiled.setY(targetd.getY());

            //Circle projectile = new Circle(target.getCenterX(), target.getCenterY(),
            //   target.getRadius(), Color.DARKRED);
            Platform.runLater(() -> {
                root.getChildren().addAll(
                        projectile, projectilea, projectileb, projectilec, projectiled);
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            //scene1.setRoot(root);
            //theStage.setScene(scene1);
            //new FadeOut(projectile).play();
            MonsterController.this.damageCalculation(projectile);
            MonsterController.this.damageCalculation(projectilea);
            MonsterController.this.damageCalculation(projectileb);
            MonsterController.this.damageCalculation(projectilec);
            MonsterController.this.damageCalculation(projectiled);
        }

        private void runTim() {
            int r = rNum.nextInt(3);
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
            if (r == 2) {
                x = x * 6;
                y = y * 6;
            }
            try {
                int monstY = thisMonster.getLocation()[1] + y;
                int monstX = thisMonster.getLocation()[0] + x;
                if (room.getRoom()[monstY][monstX] == user) {
                    System.out.println("Player is taking damage");
                    Platform.runLater(() -> {
                        Player.setHealth(
                                Player.getHealth().get() - thisMonster.getDamage());
                        System.out.println("Player's new damage: "
                                + Player.getHealth().get());
                    });
                    if (Player.getHealth().get() <= 0) {
                        timer.cancel();
                        playerLoses();
                    }
                } else if (room.getRoom()[monstY][monstX] == null
                        || room.getRoom()[monstY][monstX] instanceof Collectible) {
                    room.removeObject(
                            thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
                    thisMonster.setLocation(monstX, monstY);
                    room.addObject(thisMonster,
                            thisMonster.getLocation()[0], thisMonster.getLocation()[1]);
                }
            } catch (Exception e) {

            }
        }
    }
}
