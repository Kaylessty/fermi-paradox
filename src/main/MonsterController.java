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
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class MonsterController {

    private Monster thisMonster;
    private Timer timer;
    private TimerTask timerTask;
    private Scene scene1;
    private Pane root;
    private Stage theStage;
    private Player user;
    private boolean hasBeenAttacked;

    // This default constructor should not be used
    private MonsterController() {}

    public MonsterController(Monster monster, Scene scene, Pane root, Stage stage, Player user) {
        thisMonster = monster;
        timer = new Timer();
        scene1 = scene;
        this.root = root;
        theStage = stage;
        this.user = user;
        hasBeenAttacked = false;
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
        double x_coord = user.getLocation()[0] * 32 + 210;
        double y_coord = user.getLocation()[1] * 32;
        // If the center of the player is inside the region of the projectile
        if (x_coord > projectile.getBoundsInParent().getMinX()
                && x_coord < projectile.getBoundsInParent().getMaxX()
                && y_coord > projectile.getBoundsInParent().getMinY()
                && y_coord < projectile.getBoundsInParent().getMaxY()) {
            Player.setHealth(Player.getHealth().get() - thisMonster.getDamage());//Change 1000 to monster's damage
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
            Circle target = new Circle(user.getLocation()[0] * 32 + 210,
                    user.getLocation()[1] * 32, 16, Color.DIMGREY);
            target.setStroke(Color.YELLOW);
            target.setStrokeWidth(4);
            Platform.runLater(() -> {
                root.getChildren().add(target);
                //scene1.setRoot(root);
                //theStage.setScene(scene1);
            });
            //scene1.setRoot(root);
            //theStage.setScene(scene1);
            new Flash(target).setCycleCount(3).play();
            //target.setVisible(false);
            Circle projectile = new Circle(target.getCenterX(), target.getCenterY(),
                    target.getRadius(), Color.DARKRED);
            Platform.runLater(() -> {
                root.getChildren().add(projectile);
                //scene1.setRoot(root);
                //theStage.setScene(scene1);
            });
            //scene1.setRoot(root);
            //theStage.setScene(scene1);
            //new FadeOut(projectile).play();
            MonsterController.this.damageCalculation(projectile);
        }
    }
}
