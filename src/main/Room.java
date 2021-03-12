package main;

import java.util.Random;

/**
 * This class describes a room. A room is a 2-D array (18 by 18) where each block in the room is 32x32 pixels.
 * The outer 12 pixels around all the edges are walls which are separate from the 18x18 blocks.
 */
public class Room {
    private static Random rNum = new Random();
    private int row;
    private int column;
    private Locatable[][] room = new Locatable[18][18];
    private int doornumber;
    private int roomtype;
    private Door[] doors;
    private boolean hasHatch = false;
    private String roomName;


    /**
     * This constructor initializes the location of this room on the grid-like maze
     * @param row the row number on the grid where this room is. Rows start at 0.
     * @param column the column number on the grid where the room is. Columns start at 0.
     */
    public Room(int row, int column, String roomName) {
        this.row = row;
        this.column = column;
        room = new Locatable[18][18];
        doornumber = 1 + rNum.nextInt(4);
        roomtype = 1 + rNum.nextInt(3);
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
    }

    public Room(int row, int column, int doornumber, int roomtype, String roomName) {
        this.row = row;
        this.column = column;
        this.doornumber = doornumber;
        this.roomtype = roomtype;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
    }

    public void addObject(Locatable object, int x, int y) {
        room[x][y] = object;
    }


    public Locatable[][] getRoom() {
        return room;
    }

    public Door[] getDoors() {
        return doors;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    public int getDoornumber() {
        return doornumber;
    }
    public void addDoor(Door door) {
        for (int i = 0; i < doors.length; i++) {
            if(doors[i] == null) {
                doors[i] = door;
            }
        }
    }
    public int getCurDoors() {
        int ret = 0;
        for (int i = 0; i < doors.length; i++) {
            if (doors[i] != null) {
                ret++;
            }
        }
        return ret;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room r = (Room) o;
        return r.row == this.row && r.column == this.column;
    }

    public void setHasHatch ( boolean hasHatch){
        this.hasHatch = hasHatch;
    }
}