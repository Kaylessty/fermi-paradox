import main.Inventory;
import main.Item;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class M4InvintoryTest {

    @Test
    public void testInvintory1() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.IMPROVISEDSWORD, 4, 11, "Improvised Sword");
        inv.addItem(it);
        assertEquals(inv.getItem(0), it);
    }
    @Test
    public void testInvintory2() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.IMPROVISEDSWORD, 4, 11, "Improvised Sword");
        inv.addItem(it);
        inv.dropItem(it);
        assertEquals(inv.getItem(0), null);
    }
    @Test
    public void testInvintory3() {
        Inventory inv = new Inventory();
        Item it = new Item(Item.Possession.IMPROVISEDSWORD, 4, 11, "Improvised Sword");
        inv.addItem(it);
        assertEquals(inv.getCurrHousingSpace(), 3);
    }
}