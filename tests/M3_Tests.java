import main.Maze;
import main.Room;
import main.Door;
import main.Item;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class M3_Tests {

    /**
     * Test to make sure all the rooms of the maze are initialized
     */
    @Test
    public static testMazeRoomsNotNull() {
        testMaze = new Maze();
        for (Room r : testMaze.getRooms()) {
            assertEquals(r == null, false);
        }
    }


}