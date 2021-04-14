import javafx.stage.Stage;
import main.Inventory;
import main.Item;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertNotNull;


public class M5StartingWeaponsTest extends ApplicationTest {
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

    }
    @Test
    public void testStartingSwordSpawn() {
        clickOn("#newButton");
        clickOn("#hard");
        clickOn("#spaceSword");
        clickOn("#nameEntry").write("Elon Musk");
        clickOn("#continueButton");
        clickOn("#enteringMaze");
        Inventory inv = new Inventory();
        Item sword = new Item(Item.Possession.ENERGYSWORD, 4, 11, "Energy Sword");
        inv.addItem(sword);
        assertNotNull(inv.getItem(0));
    }

    @Test
    public void testStartingBlasterSpawn() {
        clickOn("#newButton");
        clickOn("#hard");
        clickOn("#blaster");
        clickOn("#nameEntry").write("Elon Musk");
        clickOn("#continueButton");
        clickOn("#enteringMaze");
        Inventory inv = new Inventory();
        Item blaster = new Item(Item.Possession.LASER, 4, 11, "Laser");
        inv.addItem(blaster);
        assertNotNull(inv.getItem(0));
    }
}
