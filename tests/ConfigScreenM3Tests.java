//import javafx.application.Platform;
//import main.Main;
//import main.Player;
//import javafx.stage.Stage;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//import static org.junit.Assert.assertEquals;
//import org.testfx.matcher.base.NodeMatchers;
//import static org.testfx.api.FxAssert.verifyThat;
//
//
//public class ConfigScreenM3Tests extends ApplicationTest {
//
//    private Main secondScreen;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Main controller = new Main();
//        controller.init();
//        controller.start(primaryStage);
//    }
//
//
//    /**
//    * Test for easy difficulty to verify that proper values
//    * are delivered to third screen and displayed.
//    */
//    @Test
//    public void testEasy() {
//        clickOn("#newButton");
//        clickOn("#easy");
//        clickOn("#spaceSword");
//        clickOn("#nameEntry").write("Edgar Alan Poe");
//        clickOn("#continueButton");
//        assertEquals(Player.getBalance().getValue().intValue(), 3000);
//    }
//
//    /**
//    * Test for amateur difficulty to verify that proper values
//    * are delivered to third screen and displayed.
//    */
//    @Test
//    public void testAmateur() {
//        clickOn("#newButton");
//        clickOn("#amateur");
//        clickOn("Blaster");
//        clickOn("#nameEntry").write("Elon Musk");
//        clickOn("Continue");
//        assertEquals(Player.getBalance().getValue().intValue(), 2000);
//    }
//
//    /**
//    * Test for hard difficulty to verify that proper values
//    * are delivered to third screen and displayed.
//    */
//    @Test
//    public void testHard() {
//        clickOn("#newButton");
//        clickOn("#hard");
//        clickOn("Sonar Gun");
//        clickOn("#nameEntry").write("Cher Lloyd");
//        clickOn("Continue");
//        assertEquals(Player.getBalance().getValue().intValue(), 1000);
//    }
//
//    /**
//    * Test to see if health label exists.
//    */
//    @Test
//    public void testName() {
//        clickOn("#newButton");
//        clickOn("#easy");
//        clickOn("Sonar Gun");
//        clickOn("#nameEntry").write("Lady Gaga");
//        clickOn("Continue");
//        assertEquals(Player.getName(), "Lady Gaga");
//    }
//}
import main.Main;
import main.Player;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;

public class ConfigScreenM3Tests extends ApplicationTest {

    private Main secondScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.init();
        controller.start(primaryStage);
    }


    /**
    * Test for easy difficulty to verify that proper values 
    * are delivered to third screen and displayed.
    */
    @Test
    public void testEasy() {
        clickOn("#newButton");
        clickOn("#easy");
        clickOn("#spaceSword");
        clickOn("#nameEntry").write("Edgar Alan Poe");
        clickOn("#continueButton");
        assertEquals(Player.getBalance().getValue().intValue(), 3000);
    }

    /**
    * Test for amateur difficulty to verify that proper values 
    * are delivered to third screen and displayed.
    */
    @Test
    public void testAmateur() {
        clickOn("#newButton");
        clickOn("#amateur");
        clickOn("Blaster");
        clickOn("#nameEntry").write("Elon Musk");
        clickOn("Continue");
        assertEquals(Player.getBalance().getValue().intValue(), 2000);
    }

    /**
    * Test for hard difficulty to verify that proper values 
    * are delivered to third screen and displayed.
    */
    @Test
    public void testHard() {
        clickOn("#newButton");
        clickOn("#hard");
        clickOn("Sonar Gun");
        clickOn("#nameEntry").write("Cher Lloyd");
        clickOn("Continue");
        assertEquals(Player.getBalance().getValue().intValue(), 1000);
    }

    /**
    * Test to see if health label exists.
    */
    @Test
    public void testName() {
        clickOn("#newButton");
        clickOn("#easy");
        clickOn("Sonar Gun");
        clickOn("#nameEntry").write("Lady Gaga");
        clickOn("Continue");
        assertEquals(Player.getName(), "Lady Gaga");
    }
}
