package main;

/**
 * This class acts as the door from room to room as well as the node connecting the rooms
 */
public class Door {
    private Room roomA;
    private Room roomB;
    private String imageUrl;

    public Door(Room roomA, Room roomB) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.imageUrl = "resources/images/door.png";
    }

    public Room getRoomA() {
        return roomA;
    }

    public Room getRoomB() {
        return roomB;
    }

    public String getImageUrl() { return imageUrl; }

    public void setRoomA(Room roomA) {
        this.roomA = roomA;
    }

    public void setRoomB(Room roomB) {
        this.roomB = roomB;
    }

    public void setImageUrl(String url) { this.imageUrl = url;}


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Door)) {
            return false;
        }
        Door d = (Door) obj;
        return roomA.equals(d.roomA) && roomB.equals(d.roomB);
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
}
