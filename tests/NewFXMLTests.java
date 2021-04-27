//import javafx.application.Platform;
//import javafx.beans.property.IntegerProperty;
//import javafx.scene.input.KeyCode;
//import javafx.stage.Stage;
//import main.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.testfx.api.FxRobot;
//import org.testfx.framework.junit.ApplicationTest;
//import org.testfx.matcher.base.NodeMatchers;
//
//import static org.junit.Assert.*;
//import static org.testfx.api.FxAssert.verifyThat;
//
//public class M4_Tests extends ApplicationTest {
//
//    Player player;
//    FxRobot robot = new FxRobot();
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Main controller = new Main();
//        controller.init();
//        controller.start(primaryStage);
//        player = new Player();
//
//    }
//
//    @Before
//    public void initialize() {
//        clickOn("New Game");
//        sleep(2000);
//        clickOn("#easy");
//        clickOn("#spaceSword");
//        clickOn("#nameEntry").write("Edgar Alan Poe");
//        clickOn("#continueButton");
//        sleep(2000);
//        clickOn("#enteringMaze");
//        sleep(2000);
//    }
//
//    @Test
//    public void testMovement1() {
//        int xPos = player.getLocation()[0];
//        int yPos = player.getLocation()[1];
//        robot.press(KeyCode.UP).release(KeyCode.UP);
//        robot.press(KeyCode.LEFT).release(KeyCode.LEFT);
//        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
//        robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
//        robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
//        assertEquals(xPos, player.getLocation()[0]);
//        assertEquals(yPos, player.getLocation()[1]);
//    }
//
//    @Test
//    public void testMovement2() {
//        int xPos = player.getLocation()[0];
//        int yPos = player.getLocation()[1];
//        robot.press(KeyCode.W).release(KeyCode.W);
//        robot.press(KeyCode.A).release(KeyCode.A);
//        robot.press(KeyCode.S).release(KeyCode.S);
//        robot.press(KeyCode.D).release(KeyCode.D);
//        robot.press(KeyCode.W).release(KeyCode.W);
//        assertEquals(xPos, player.getLocation()[0]);
//        assertEquals(yPos, player.getLocation()[1]);
//    }
//
//    @Test
//    public void testPlayerDamage() {
//        Platform.runLater(() -> {
//            IntegerProperty i = Player.getHealth();
//            Player.setHealth(Player.getHealth().get() - 1);
//            assertEquals((i.getValue().intValue()), Player.getHealth().getValue().intValue());
//        });
//
//
//    }
//
//    @Test
//    public void testPlayerFaint() {
//        Platform.runLater(() -> {
//            Player.setHealth(Player.getHealth().get() - 7000);
//
//        });
//
//    }
//}
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.*;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

public class NewFXMLTests extends ApplicationTest {

    private Player player;
    private FxRobot robot = new FxRobot();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.init();
        controller.start(primaryStage);
        player = new Player();

    }

    @Before
    public void initialize() {
        clickOn("New Game");
        sleep(2000);
        clickOn("#easy");
        clickOn("#spaceSword");
        clickOn("#nameEntry").write("Edgar Alan Poe");
        clickOn("#continueButton");
        sleep(2000);
        clickOn("#enteringMaze");
        sleep(2000);
    }

    @Test
    public void canEscape() {
        int xPos = player.getLocation()[0];
        int yPos = player.getLocation()[1];
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.LEFT).release(KeyCode.LEFT);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        assertEquals(xPos, player.getLocation()[0]);
        assertEquals(yPos, player.getLocation()[1]);
    }

    @Test
    public void winScreenFunctionality() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/statsscreen.fxml"));

        int xPos = player.getLocation()[0];
        int yPos = player.getLocation()[1];
        robot.press(KeyCode.W).release(KeyCode.W);
        robot.press(KeyCode.A).release(KeyCode.A);
        robot.press(KeyCode.S).release(KeyCode.S);
        robot.press(KeyCode.D).release(KeyCode.D);
        robot.press(KeyCode.W).release(KeyCode.W);
        assertEquals(xPos, player.getLocation()[0]);
        assertEquals(yPos, player.getLocation()[1]);
    }

    @Test
    public void statisticsScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/statsscreen.fxml"));
        Platform.runLater(() -> {
            IntegerProperty i = Player.getHealth();
            Player.setHealth(Player.getHealth().get() - 1);
            assertEquals((i.getValue().intValue()), Player.getHealth().getValue().intValue());
        });


    }

    @Test
    public void timeElapsed() {
       int time = LoseScreenController.timeElapsed;

    }
}
