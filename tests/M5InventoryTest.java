import main.Inventory;
import main.Item;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class M5InventoryTest {

    @Test
    public void testInventoryHealthPotion() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.BOREEYE, 4, 11, "Bore Eye");
        inv.addItem(it);
        assertEquals(inv.getItem(0), it);
    }

    @Test
    public void testInventoryAttackPotion() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.HORN, 4, 11, "HORN");
        inv.addItem(it);
        assertEquals(inv.getItem(0), it);
    }
    @Test
    public void testInventoryBlaster() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.SHOCKRIFLE, 4, 11, "Shock Rifle");
        inv.addItem(it);
        inv.dropItem(it);
        assertEquals(inv.getItem(0), null);
    }
    @Test
    public void testInventorySword() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.IMPROVISEDSWORD, 4, 11, "Improvised Sword");
        inv.addItem(it);
        assertEquals(inv.getCurrHousingSpace(), 3);
    }
}
