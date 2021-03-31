import javafx.stage.Stage;
import main.Main;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class RoomDisplayTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.init();
        controller.start(primaryStage);
    }
    @Test
    public void roomLabelExists() {
        clickOn("#newButton");
        clickOn("#easy");
        clickOn("#spaceSword");
        clickOn("#nameEntry").write("Alien_Slayer_5000");
        clickOn("#continueButton");
        clickOn("#enteringMaze");
        sleep(2000);
        verifyThat("room starting", NodeMatchers.isVisible());
    }
}

