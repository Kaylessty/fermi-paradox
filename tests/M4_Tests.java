import main.Maze;
import main.Room;
import main.Item;
import org.junit.Test;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class M4_Tests {
    private Controller secondScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        secondScreen = new Controller();
        secondScreen.start(primaryStage);
    }

    @Before
    public void initialize() {
        clickOn("New Game");
        sleep(2000);
        clickOn("easy");
        clickOn("Space Sword");
        clickOn(400, 10).write("Edgar Alan Poe");
        clickOn("Continue");
        sleep(2000);
        clickOn("Enter the maze");
        sleep(2000);
    }

    @Test
    public void testMovement1(FxRobot robot) {
        xPos = Player.getLocation()[0];
        yPos = Player.getLocation()[1];
        robot.press(KeyCode.UP).release(KeyCode.UP);
        robot.press(KeyCode.LEFT).release(KeyCode.LEFT);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        assertEquals(xPos + 1, Player.getLocation()[0]);
        assertEquals(yPos, Player.getLocation()[1]);
    }

    @Test
    public void testMovement2(FxRobot robot) {
        xPos = Player.getLocation()[0];
        yPos = Player.getLocation()[1];
        robot.press(KeyCode.W).release(KeyCode.W);
        robot.press(KeyCode.A).release(KeyCode.A);
        robot.press(KeyCode.S).release(KeyCode.S);
        robot.press(KeyCode.D).release(KeyCode.D);
        robot.press(KeyCode.W).release(KeyCode.W);
        assertEquals(xPos, Player.getLocation()[0]);
        assertEquals(yPos + 1, Player.getLocation()[1]);
    }

    @Test
    public void testPlayerDamage() {
        int i = Player.getHealth();
        //Since unsure how to spawn a monster on top of player,
        //copied over direct damage code to test it.
        Player.setHealth(Player.getHealth().get() - 1);
        assertEquals(i - 1, Player.getHealth);
        System.out.println("Player's new damage: " + Player.getHealth().get());
    }

    @Test
    public void testPlayerFaint() {
        //Since unsure how to spawn a monster on top of player,
        //copied over direct death code to test it
        assertEquals("You lose :(", playerLoses());
    }
}