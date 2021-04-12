import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.ClownShop;
import main.Item;
import main.Main;
import main.Monster;
import main.Player;
import main.ProwlerShop;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class ShopInteractionsTest extends ApplicationTest {

    FxRobot robot;
    Stage primaryStage;

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
    public void testPurchaseTransaction() {
        Player.getRoom().addObject(new Item(Item.Possession.AAID, 8, 7, "Administrator ID"), 8, 7);
        press(KeyCode.UP).release(KeyCode.UP);
        ClownShop shop = new ClownShop();
        shop.setLocation(8, 6);
        Player.getRoom().addMonster(shop, 100, 0);
        press(KeyCode.D).release(KeyCode.D); // Allows for the room to be refreshed so that the shop appears
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(), shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        clickOn("Shop");
        Item[] contents = shop.getShopInv();
        String query = contents[0].getName() + ": " + contents[0].getBuyPrice();
        int expected = Player.getBalance().get() - contents[0].getBuyPrice();
        clickOn(query);
        int actual = Player.getBalance().get();
        assertEquals(expected, actual);
    }

    @Test
    public void testBankruptPurchase() {
        ProwlerShop shop = new ProwlerShop();
        shop.setLocation(8, 7);
        Player.getRoom().addMonster(shop, 100, 0);
        //Player.setBalance(5);// Creates thread errors
        press(KeyCode.D).release(KeyCode.D); // Allows for the room to be refreshed so that the shop appears
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(), shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        clickOn("Shop");
        Item[] contents = shop.getShopInv();
        String query = contents[1].getName() + ": " + contents[1].getBuyPrice();
        int expected = Player.getBalance().get();
        clickOn(query);
        int actual = Player.getBalance().get();
        assertEquals(expected, actual);
    }

    @Test
    public void testSellingItem() {
        Item toSell = new Item(Item.Possession.SONICRIFLE, 8, 7, "Gun1");
        Player.getRoom().addObject(toSell, 8, 7);
        press(KeyCode.UP).release(KeyCode.UP);
        ClownShop shop = new ClownShop();
        shop.setLocation(8, 6);
        Player.getRoom().addMonster(shop, 100, 0);
        press(KeyCode.D).release(KeyCode.D); // Allows for the room to be refreshed so that the shop appears
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(), shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        clickOn("Shop");
        String query = toSell.getName() + ": " + toSell.getSellPrice();
        int expected = Player.getBalance().get() + toSell.getSellPrice();
        clickOn(query);
        int actual = Player.getBalance().get();
        assertEquals(expected, actual);
    }

    @Test
    public void testSellingItemMultipleTimes() {
        Item toSell = new Item(Item.Possession.SONICRIFLE, 8, 7, "Gun1");
        Player.getRoom().addObject(toSell, 8, 7);
        press(KeyCode.UP).release(KeyCode.UP);
        ClownShop shop = new ClownShop();
        shop.setLocation(8, 6);
        Player.getRoom().addMonster(shop, 100, 0);
        press(KeyCode.D).release(KeyCode.D); // Allows for the room to be refreshed so that the shop appears
        clickOn(shop.getLocation()[0] * 32 + 220 + primaryStage.getX(), shop.getLocation()[1] * 32 + 10 + primaryStage.getY());
        clickOn("Shop");
        String query = toSell.getName() + ": " + toSell.getSellPrice();
        clickOn(query);
        verifyThat("SOLD", isVisible());
        int expected = Player.getBalance().get();
        clickOn("SOLD");
        assertEquals(expected, Player.getBalance().get());
    }
}
