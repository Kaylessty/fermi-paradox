package main;

import animatefx.animation.FadeOut;
import animatefx.animation.Flash;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.beans.binding.Bindings;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
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

    // This default constructor should not be used
    private MonsterController() {
    }

    public MonsterController(Monster monster, Scene scene, Pane root, Stage stage, Player user) {
        thisMonster = monster;
        timer = new Timer();
        scene1 = scene;
        this.root = root;
        //theStage = stage;
        this.user = user;
        attack();
    }

    public void attack() {
        if (thisMonster.getHealth() > 0) {
            timerTask = new innerClass();
            timer.scheduleAtFixedRate(timerTask, 0, 3000);
        } else {
            timer.cancel();
        }
    }

    private void damageCalculation(Shape projectile) {
        double x_coord = user.getLocation()[0] * 32 + 210;//*************************************************player1
        double y_coord = user.getLocation()[1] * 32;//*********************************************player1
        // If the center of the player is inside the region of the projectile
        /*
        System.out.println("Projectile Min X: " + projectile.getBoundsInParent().getMinX());
        System.out.println("Projectile Max X: " + projectile.getBoundsInParent().getMaxX());
        System.out.println("Projectile Min Y: " + projectile.getBoundsInParent().getMinY());
        System.out.println("Projectile Max Y: " + projectile.getBoundsInParent().getMaxY());
        System.out.println("Player's X:" + x_coord);
        System.out.println("Player's Y: " + y_coord);
         */
        if (x_coord >= projectile.getBoundsInParent().getMinX()
                && x_coord <= projectile.getBoundsInParent().getMaxX()
                && y_coord >= projectile.getBoundsInParent().getMinY()
                && y_coord <= projectile.getBoundsInParent().getMaxY()) {
            System.out.println("Player is taking damage");
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

    private void playerLoses() {
        System.out.println("You lost :(");
    }

    class innerClass extends TimerTask {
        public void run() {
            if (thisMonster.getHealth() <= 0) {
                timer.cancel();
            } else {
                Circle target = new Circle(user.getLocation()[0] * 32 + 226,
                        user.getLocation()[1] * 32 + 16, 16, Color.DIMGREY);//***********player1
                target.setStroke(Color.YELLOW);
                target.setStrokeWidth(4);
                Platform.runLater(() -> {
                    root.getChildren().add(target);
                });
                //scene1.setRoot(root);
                //theStage.setScene(scene1);
                new Flash(target).setCycleCount(3).playOnFinished(new FadeOut(target)).play();
                //target.setVisible(false);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                Circle projectile = new Circle(target.getCenterX(), target.getCenterY(),
                        target.getRadius(), Color.DARKRED);
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
                new FadeOut(projectile).play();
                MonsterController.this.damageCalculation(projectile);
            }
        }
    }
}
