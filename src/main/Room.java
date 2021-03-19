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
    private int monsterNum;
    private int numRoom;
    private int special;


    /**
     * This constructor initializes the location of this room on the grid-like maze
     * @param row the row number on the grid where this room is. Rows start at 0.
     * @param column the column number on the grid where the room is. Columns start at 0.
     */
    public Room(int row, int column, String roomName, int numRoom) {
        this.row = row;
        this.column = column;
        room = new Locatable[18][18];
        doornumber = 1 + rNum.nextInt(4);
        distance = 999;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
        this.monsterNum = 1+ rNum.nextInt(2);
        this.numRoom = numRoom;
        special = rNum.nextInt(10);
        if(special == 9) {
            this.roomName = "Clown Room";
            addObject(new Item(Item.Possession.HORN, 11, 11, "Clown Horn"), 11, 11);
        }
    }

    public Room(int row, int column, int doornumber, int distance, int monsterNum, String roomName, int numRoom) {
        this.row = row;
        this.column = column;
        this.doornumber = doornumber;
        this.distance = distance;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
        this.monsterNum = monsterNum;
        this.numRoom = numRoom;
    }

    public Room(int row, int column, int doornumber, int monsterNum, String roomName, int numRoom) {
        this.row = row;
        this.column = column;
        this.doornumber = doornumber;
        this.distance = 999;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
        this.monsterNum = monsterNum;
        this.numRoom = numRoom;
    }

    public void addObject(Locatable object, int x, int y) {
        room[y][x] = object;
    }

    public void removeObject(int x, int y) {
        room[y][x] = null;
        System.out.println("removed");
    }

    public Locatable[][] getRoom() {
        return room;
    }

    public Door[] getDoors() {
        return doors;
    }

    /*
    public void removeDoor(Door door) {
        int curDoorNum = this.getCurDoors();
        for (int index = 0; index < curDoorNum; index++) {
            if (door.equals(doors[index])) {
                for (; index < curDoorNum; index++) {
                    doors[index] = doors[index + 1];
                }
                break;
            }
        }
    }
     */

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
        if (door.getRoomA().equals(this)) {
            if (door.getRoomB().getDistance() + 1 < distance) {
                distance = door.getRoomB().getDistance() + 1;
            }
        } else {
            if (door.getRoomA().getDistance() + 1 < distance) {
                distance = door.getRoomA().getDistance() + 1;
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
    public int getCurDoors() {
        int ret = 0;
        for (int i = 0; i < doors.length; i++) {
            if (doors[i] != null) {
                ret++;
            }
        }
        return ret;
    }

    public int getMonsterNum() {
        return monsterNum;
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
        return r.getRoomName().equals(this.roomName);
    }

    public void setHasHatch ( boolean hasHatch){
        this.hasHatch = hasHatch;
    }

    public boolean getHasHatch() {
        return hasHatch;
    }

    public void setDistance(int dist) {
        distance = dist;
    }

    public int getDistance() {
        return distance;
    }

    public int getNumRoom() {
        return numRoom;
    }
}