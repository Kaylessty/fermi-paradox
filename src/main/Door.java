package main;
/**
 * This class acts as the door from room to room as well as the node connecting the rooms
 */
public class Door implements Locatable {
    private Room roomA;
    private Room roomB;
    private String imageUrl;
    private int x;
    private int y;
    private Boolean locked = true;
    private Door con;

    public Door(Room roomA, Room roomB) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.imageUrl = "resources/images/door.png";
        x = 8;
        y = 0;
    }

    public Door(Room roomA, Room roomB, String url, int x, int y) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.imageUrl = url;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the RoomA instance var
     * @return Room A of the door
     */
    public Room getRoomA() {
        return roomA;
    }

    /**
     * Getter for the RoomB instance var
     * @return Room B of the door
     */
    public Room getRoomB() {
        return roomB;
    }

    /**
     * Getter for the ImageURL instace var
     * @return String imageURL
     */
    public String getImageURL() {
        return imageUrl;
    }

    /**
     * setter for instance var roomA
      * @param roomA Room for new value
     */
    public void setRoomA(Room roomA) {
        this.roomA = roomA;
    }

    /**
     * setter for instance var roomB
     * @param roomB Room for next value
     */
    public void setRoomB(Room roomB) {
        this.roomB = roomB;
    }


    /**
     * setter for imageUrl
     * @param url String for new val
     */
    public void setImageURL(String url) {
        this.imageUrl = url;
    }


    /**
     * setter for X instance var
     * @param x int for new val
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * setter for Y instance var
     * @param y int for new val
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int[] getLocation() {
        return new int[] {x, y};
    }

    /**
     * setter for locked instance var
     * @param locked boolean for locked to be set to
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * getter for locked instance var
     * @return boolean value of locked
     */
    public boolean getLocked() {
        return locked;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Door)) {
            return false;
        }
        Door d = (Door) obj;
        return roomA.equals(d.getRoomA()) && roomB.equals(d.getRoomB());
    }

    /**
     * getter for con instance variable
     * @return the Door value of con
     */
    public Door getCon() {
        return con;
    }

    /**
     * setter for con instance variable
     * @param con the Door val for con to be set to
     */
    public void setCon(Door con) {
        this.con = con;
    }
}

