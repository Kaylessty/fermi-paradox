import main.Main;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class FirstScreenTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.init();
        controller.start(primaryStage);
    }

    @Test
    public void testEG() {
        verifyThat("Exit Game", NodeMatchers.isNotNull());
    }
    @Test
    public void testNG() {
        verifyThat("New Game", NodeMatchers.isNotNull());
    }
}