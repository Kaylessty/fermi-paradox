import main.Controller;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class FirstScreenTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void testTitle() {
        verifyThat("Space Game!", NodeMatchers.isNotNull());
    }
    @Test
    public void testNG() {
        verifyThat("New Game", NodeMatchers.isNotNull());
    }
}