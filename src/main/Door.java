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

    public Room getRoomA() {
        return roomA;
    }

    public Room getRoomB() {
        return roomB;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public void setRoomA(Room roomA) {
        this.roomA = roomA;
    }

    public void setRoomB(Room roomB) {
        this.roomB = roomB;
    }

    public void setImageURL(String url) {
        this.imageUrl = url;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getLocation() {
        return new int[] {x, y};
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

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

    //something to be used if row/column of rooms gets updated
    public String doorPlacement(Room room) {
        if (room.equals(roomA)) {
            if (room.getRow() > roomB.getRow()) {
                return "left";
            } else if (room.getRow() < roomB.getRow()) {
                return "right";
            } else if (room.getColumn() > roomB.getColumn()) {
                return "up";
            } else {
                return "down";
            }
        } else {
            if (room.getRow() > roomA.getRow()) {
                return "left";
            } else if (room.getRow() < roomA.getRow()) {
                return "right";
            } else if (room.getColumn() > roomA.getColumn()) {
                return "up";
            } else {
                return "down";
            }
        }
    }

    public Door getCon() {
        return con;
    }

    public void setCon(Door con) {
        this.con = con;
    }
}
