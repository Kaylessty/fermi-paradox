import main.Main;
import main.Maze;
import main.RoomController;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;


public class Ben_M3_Tests extends ApplicationTest {

    private Main secondScreen;
    private RoomController test;

    @Override
    public void start(Stage primaryStage) throws Exception {
        secondScreen = new Main();
        secondScreen.start(primaryStage);

        test = new RoomController();
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
    }

    /**
    * Test to ensure that upon starting a game, you begin in "room first"
    */
    @Test
    public void testMazeInitialization() {
        test = new RoomController();
        assertEquals(test.getCurrRoom().getRoomName(), "room first");
    }

    /**
     * Test to ensure that the minimum required number of rooms exist
     */
    @Test
    public void testRoomNumber() {
        assertEquals((getRooms().length - 2) >= 8, true);
    }

    /**
    * Test to ensure existance of an exit room upon random generation
    */
    @Test
    public void testForExit() {
        Maze testMaze = new Maze();
        int a = testMaze.getRooms().length;
        boolean exitExists = false;
        for (int k = 0; k < a; k++) {
            if ((testMaze.getRooms()[k].getRoomName()).equals("room last")) {
                exitExists = true;
            }
        }
        assertEquals(exitExists, true);
    }

    /**
    * Test to ensure that the room label is actually visible
    */
    @Test
    public void roomLabelExists() {
        verifyThat("room first", NodeMatchers.isVisible());
    }
}
