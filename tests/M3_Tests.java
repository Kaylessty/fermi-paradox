import main.Maze;
import main.Room;
import main.Item;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class M3_Tests {
    /**
     * Test to make sure all the rooms of the maze are initialized
     */
    @Test
    public void testMazeRoomsNotNull() {
        Maze testMaze = new Maze();
        for (Room r : testMaze.getRooms()) {
            assertEquals(false, r == null);
        }
    }

    /**
     * Test to make sure all the rooms of the maze have a decent number of doors
     */
    @Test
    public void testMazeRoomsDoorNum() {
        Maze testMaze = new Maze();
        for (Room r : testMaze.getRooms()) {
            assertEquals(true, r.getCurDoors() <= 4 && r.getCurDoors() > 0);
        }
    }

    /**
     * Test to verify the equals method of the Room class
     */
    @Test
    public void testRoomEquality() {
        Room r1 = new Room(0, 0, "4");
        Room r2 = new Room(2, 2, "4");
        Room r3 = new Room (0, 0, "0");
        assertEquals(false, r1.equals(r3));
        assertEquals(true, r2.equals(r1));
    }

    /**
     * Test to verify the Item is stored correctly in Room
     */
    @Test
    public void testRoomItemsLocation() {
        Room testRoom = new Room(0, 0, "test");
        Item item1 = new Item(Item.Possession.SPACESWORD, 0, 0);
        Item item2 = new Item(Item.Possession.SONARGUN, 6, 10);
        testRoom.addObject(item1, 0, 0);
        testRoom.addObject(item2, 6, 10);
        assertEquals(item1, testRoom.getRoom()[0][0]);
        assertEquals(item2, testRoom.getRoom()[6][10]);
    }
}