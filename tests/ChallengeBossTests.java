import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.ClownShop;
import main.Item;
import main.Main;
import main.Player;
import main.ProwlerShop;
import org.junit.Test;
import org.junit.Before;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class ChallengeBossTests extends ApplicationTest {

    private FxRobot robot;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Main game = new Main();
        game.init();
        game.start(primaryStage);
    }

    @Before
    public void setup() {
        clickOn("#newButton");
        clickOn("#hard");
        clickOn("#spaceSword");
        clickOn("#nameEntry").write("shopTest");
        clickOn("#continueButton");
        clickOn("#enteringMaze");
        robot = new FxRobot();
    }

    /*
    @After
    public void closing() {
        primaryStage.close();
    }
    */

    @Test
    public void challengeroom() {
        Player.getRoom().addObject(
                new Item(Item.Possession.AAID, 8, 7, "Administrator ID"),
                8, 7);
        ClownShop shop = new ClownShop();
        shop.setLocation(8, 6);
        Player.getRoom().addMonster(shop, 100, 0);
        press(KeyCode.UP).release(KeyCode.UP);
        // Allows for the room to be refreshed so that the shop appears
        press(KeyCode.D).release(KeyCode.D);
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(),
                shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        clickOn("Shop");
        Item[] contents = shop.getShopInv();
        String query = contents[0].getName() + ": " + contents[0].getBuyPrice();
        int expected = Player.getBalance().get() - contents[0].getBuyPrice();
        clickOn(query);
        int actual = Player.getBalance().get();
        assertEquals(expected, actual);
    }

    @Test
    public void finalBossRoom() {
        Player.getRoom().addObject(
                new Item(Item.Possession.AAID, 8, 7, "Administrator ID"),
                8, 7);
        ProwlerShop shop = new ProwlerShop();
        shop.setLocation(8, 6);
        Player.getRoom().addMonster(shop, 100, 0);
        //Player.setBalance(5);// Creates thread errors
        press(KeyCode.UP).release(KeyCode.UP);
        // Allows for the room to be refreshed so that the shop appears
        press(KeyCode.D).release(KeyCode.D);
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(),
                shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        clickOn("Shop");
        Item[] contents = shop.getShopInv();
        String query = contents[1].getName() + ": " + contents[1].getBuyPrice();
        int expected = Player.getBalance().get();
        clickOn(query);
        int actual = Player.getBalance().get();
        assertEquals(expected, actual);
    }

    @Test
    public void escapeMaze() {
        Player.getRoom().addObject(
                new Item(Item.Possession.AAID, 8, 7, "Administrator ID"),
                8, 7);
        Item toSell = new Item(Item.Possession.SONICRIFLE, 8, 6, "Gun1");
        Player.getRoom().addObject(toSell, 8, 6);
        press(KeyCode.UP).release(KeyCode.UP);
        ClownShop shop = new ClownShop();
        shop.setLocation(8, 5);
        Player.getRoom().addMonster(shop, 100, 0);
        press(KeyCode.UP).release(KeyCode.UP);
        press(KeyCode.UP).release(KeyCode.UP);
        // Allows for the room to be refreshed so that the shop appears
        press(KeyCode.D).release(KeyCode.D);
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(),
                shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        clickOn("Shop");
        String query = toSell.getName() + ": " + toSell.getSellPrice();
        int expected = Player.getBalance().get() + toSell.getSellPrice();
        clickOn(query);
        int actual = Player.getBalance().get();
        assertEquals(expected, actual);
    }

    @Test
    public void challengeMonsterDrop() {
        Player.getRoom().addObject(
                new Item(Item.Possession.AAID, 8, 7, "Administrator ID"),
                8, 7);
        Item toSell = new Item(Item.Possession.SONICRIFLE, 8, 6, "Gun1");
        Player.getRoom().addObject(toSell, 8, 6);
        press(KeyCode.UP).release(KeyCode.UP);
        ClownShop shop = new ClownShop();
        shop.setLocation(8, 5);
        Player.getRoom().addMonster(shop, 100, 0);
        // Allows for the room to be refreshed so that the shop appears
        press(KeyCode.D).release(KeyCode.D);
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(),
                shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        clickOn("Shop");
        String query = toSell.getName() + ": " + toSell.getSellPrice();
        clickOn(query);
        verifyThat("SOLD", isVisible());
        int expected = Player.getBalance().get();
        clickOn("SOLD");
        assertEquals(expected, Player.getBalance().get());
    }
}
