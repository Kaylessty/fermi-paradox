package main;

/**
 * This class acts as the door from room to room as well as the node connecting the rooms
 */
public class Door {
    private Room roomA;
    private Room roomB;

    public Door(Room roomA, Room roomB) {
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public Room getRoomA() {
        return roomA;
    }

    public Room getRoomB() {
        return roomB;
    }

    public void setRoomA(Room roomA) {
        this.roomA = roomA;
    }

    public void setRoomB(Room roomB) {
        this.roomB = roomB;
    }
}
