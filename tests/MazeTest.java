import main.Main;
import main.Maze;
import main.RoomController;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.assertEquals;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class MazeTest {
    Maze maze = new Maze();
    @Test
    void roomsCorrect(){
        Assertions.assertEquals(maze.getRooms().length,maze.getRoomnum());
    }
    @Test
    void portalRoom() { Assertions.assertEquals(maze.getRooms()[maze.getRooms().length-2].getHasHatch(),true)}
}