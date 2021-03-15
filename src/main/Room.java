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
    private int distance;
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
        distance = 999;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
    }

    /**
     * constructor for a Room
     * @param row int for row
     * @param column int for column
     * @param doornumber int for doornumber
     * @param distance int for distance
     * @param roomName String for name of room
     */
    public Room(int row, int column, int doornumber, int distance, String roomName) {
        this.row = row;
        this.column = column;
        this.doornumber = doornumber;
        this.distance = distance;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
    }

    /**
     * constructor for a Room
     * @param row int for row
     * @param column int for column
     * @param doornumber int for doornumber
     * @param roomName String for name of room
     */
    public Room(int row, int column, int doornumber, String roomName) {
        this.row = row;
        this.column = column;
        this.doornumber = doornumber;
        this.distance = 999;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
    }

    /**
     * adds a locatable to the room
     * @param object a Locatable to be added
     * @param x the x position of the locatable
     * @param y the y position of the locatable
     */
    public void addObject(Locatable object, int x, int y) {
        room[y][x] = object;
    }

    /**
     * getter for the matrix of the room
     * @return Locatable[][]
     */
    public Locatable[][] getRoom() {
        return room;
    }

    /**
     * getter for the doors of the room
     * @return Door[]
     */
    public Door[] getDoors() {
        return doors;
    }

    /**
     * getter for the name of the room
     * @return String roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * setter for the doors of the room
     * @param doors Door[]
     */
    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    /**
     * getter for the number of doors
     * @return int doornumber
     */
    public int getDoornumber() {
        return doornumber;
    }

    /**
     * add a door to the room
     * @param door Door to be added
     */
    public void addDoor(Door door) {
        if (door.getRoomA().equals(this)) {
            if (door.getRoomB().distance + 1 < distance) {
                distance = door.getRoomB().distance + 1;
            }
        } else {
            if (door.getRoomA().distance + 1 < distance) {
                distance = door.getRoomA().distance + 1;
            }
        }
        int g = 0;
        for (int i = 0; i < doors.length; i++) {
            if(doors[i] == null && g != 1) {
                doors[i] = door;
                g = 1;
                return;
            }
        }
    }

    /**
     * get the number of current doors
     * @return int
     */
    public int getCurDoors() {
        int ret = 0;
        for (int i = 0; i < doors.length; i++) {
            if (doors[i] != null) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * getter for the row
     * @return int
     */
    public int getRow() {
        return row;
    }

    /**
     * getter for the column
     * @return column
     */
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
        return r.roomName.equals(this.roomName);
    }

    /**
     * setter for the hasHatch
     * @param hasHatch boolean
     */
    public void setHasHatch ( boolean hasHatch){
        this.hasHatch = hasHatch;
    }

    /**
     * getter for the hasHatch
     * @return boolean
     */
    public boolean getHasHatch() {
        return hasHatch;
    }

    /**
     * setter for distance
     * @param dist int
     */
    public void setDistance(int dist) {
        distance = dist;
    }

    /**
     * getter for the distance
     * @return int
     */
    public int getDistance() {
        return distance;
    }
}

