import main.Main;
import main.Inventory;
import main.Item;
import main.Locatable;
import main.Collectible;
import main.RoomController;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class M4InvintoryTest {

    @Test
    public void testInvintory1() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.IMPROVISEDSWORD, 4, 11, "Improvised Sword");
        inv.addItem(it);
        assertEquals(inv.contents[0],it);
    }
    @Test
    public void testInvintory2() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.IMPROVISEDSWORD, 4, 11, "Improvised Sword");
        inv.addItem(it);
        inv.dropItem(it);
        assertEquals(inv.contents[0],null);
    }
    @Test
    public void testInvintory3() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.IMPROVISEDSWORD, 4, 11, "Improvised Sword");
        inv.addItem(it);
        assertEquals(inv.getCurrHousingSpace(),3);
    }
}