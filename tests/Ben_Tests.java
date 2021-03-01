import main.Controller;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;


public class ConfigScreenTest extends ApplicationTest {

    private Controller secondScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        secondScreen = new Controller();
        secondScreen.start(primaryStage);
    }

    @Before
    public void initialize() {
        clickOn("New Game");
    }

    /**
    * Test for easy difficulty to verify that proper values 
    * are delivered to third screen and displayed.
    */
    @Test
    public void testEasy() {
        sleep(2000);
        clickOn("easy");
        clickOn("Space Sword");
        clickOn(400, 10).write("Edgar Alan Poe");
        clickOn("Continue");
        assertEquals(Player.getBalance(), 3000);
    }

    /**
    * Test for amateur difficulty to verify that proper values 
    * are delivered to third screen and displayed.
    */
    @Test
    public void testAmateur() {
        sleep(2000);
        clickOn("amateur");
        clickOn("Blaster");
        clickOn(400, 10).write("Edgar Alan Poe");
        clickOn("Continue");
        assertEquals(Player.getBalance(), 2000);
    }

    /**
    * Test for hard difficulty to verify that proper values 
    * are delivered to third screen and displayed.
    */
    @Test
    public void testHard() {
        sleep(2000);
        clickOn("easy");
        clickOn("Sonar Gun");
        clickOn(400, 10).write("Edgar Alan Poe");
        clickOn("Continue");
        assertEquals(Player.getBalance(), 1000);
    }

    /**
    *Test to see if health label exists.
    */
    @ Test
    public void BalanceExists() {
        sleep(2000);
        clickOn("easy");
        clickOn("Sonar Gun");
        clickOn(400, 10).write("Edgar Alan Poe");
        clickOn("Continue");
        verifyThat("Health: ", NodeMatchers.isVisible());
    }
}
