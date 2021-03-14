import main.Controller;
import main.Player;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;


public class Ben_Tests extends ApplicationTest {

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
    }

    /**
    * Test to ensure that upon starting a game, you begin in "room first"
    */
    @Test
    public void testMazeInitialization() {
        assertEquals(currRoom.getRoomName(), "room first");
    }

    /**
    * Test to ensure that upon exiting a room and returning through the same
    * door, you return to the room you were just in.
    */
    @Test
    public void testDoorReturn() {
        //Note: Logic assumes door order is "logical" in a sense, and that array pos links to room pos.
        clickOn(getDoors()[0]);
        clickOn(getDoors()[2]);
        assertEquals(currRoom.getRoomName(), "room first");
    }

    /**
    * Test to ensure existance of an exit room upon random generation
    */
    @Test
    public void testForExit() {
        private int a = getRooms().length;
        private boolean exitExists = false;
        for (int k = 0; k < a; k++) {
            if ((getRooms()[k].getRoomName).equals("room last")) {
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
