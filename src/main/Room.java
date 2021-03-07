package main;

/**
 * This class describes a room. A room is a 2-D array (18 by 18) where each block in the room is 32x32 pixels.
 * The outer 12 pixels around all the edges are walls which are separate from the 18x18 blocks.
 */
public class Room {

    private int row;
    private int column;
    private Locatable[][] room = new Locatable[18][18];

    /**
     * This constructor initializes the location of this room on the grid-like maze
     * @param row the row number on the grid where this room is. Rows start at 0.
     * @param column the column number on the grid where the room is. Columns start at 0.
     */
    public Room(int row, int column) {
        this.row = row;
        this.column = column;
        room = new Locatable[18][18];
    }

    public void addObject(Locatable object, int x, int y) {
        room[x][y] = object;
    }


    public Locatable[][] getRoom() {
        return room;
    }

}
