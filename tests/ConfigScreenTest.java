import main.Controller;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class ConfigScreenTest extends ApplicationTest {

    Controller secondScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        secondScreen = new Controller();
        secondScreen.start(primaryStage);
    }

    @Before
    public void initialize() {
        clickOn("New Game");
    }

    @Test
    public void TestEmptyString() {
        sleep(2000);
        clickOn("easy");
        clickOn("Space Sword");
        clickOn("Continue");
        verifyThat("Close and try again.", NodeMatchers.isVisible());
        clickOn("Close and try again.");
        clickOn(400, 10).write("");
        clickOn("Continue");
        verifyThat("Close and try again.", NodeMatchers.isVisible());
        clickOn("Close and try again.");
        clickOn(400, 10).write("Hello");
        clickOn("Continue");
        verifyThat("Health:", NodeMatchers.isVisible());
    }

    @Test
    public void TestButtonsExist() {
        verifyThat("Enter your name: ", NodeMatchers.isVisible());
    }
}
