package main;

import java.util.Random;

/**
 * The Maze class acts as a graph of sorts for the Rooms, all existing rooms can be found in the
 * rooms array and all existing doors can be found in the doors array.
 */
public class Maze {
    private static Item startItem;
    private Random rNum = new Random();
    private Room[] rooms;
    private Door[] doors;
    private int roomnum;
    private int doornum;
    private int x = 0;
    private int y = 0;

    /**
     * Creates a maze and fills it with a random number between 10 and 20 random rooms.
     */
    public Maze() {
        roomnum = 26 + rNum.nextInt(10);
        rooms = new Room[roomnum];
        System.out.println("Number of rooms: " + rooms.length);
        rooms[0] = new Room(x, y, 4, 0, 0, "starting", 0);
        // Create a new player and add it to the first room
        Player player1 = new Player();
        player1.setLocation(8, 8);
        rooms[0].addObject(player1, player1.getLocation()[0], player1.getLocation()[1]);
        doornum = 4;
        for (int i = 1; i < roomnum; i++) {
            rooms[i] = new Room(x, y, "" + i, i);
            // Shouldn't doornum start off at the value 4?
            doornum += rooms[i].getDoornumber();
        }
        rooms[rooms.length - 2] = new Room(x, y, 4, 0, "last", rooms.length - 2);
        rooms[rooms.length - 2].setHasHatch(true);
        doors = new Door[doornum];
        Room curRoom;
        int dcount = 0;
        // Iterate over each room
        for (int i = 0; i < roomnum; i++) {
            curRoom = rooms[i];
            int k = 1;
            int cudoor = curRoom.getCurDoors();
            // Iterates over the number of doors left to be added in that room
            for (int j = 0; j < curRoom.getDoors().length - cudoor; j++) {
                int v = 1;
                // Finding a suitable room to connect to the door
                while (v == 1) {
                    if (i + k >= rooms.length) {
                        // Is this all really necessary? I feel like only the v=0; is.
                        Door[] doors1 = new Door[curRoom.getCurDoors()];
                        for (int n = 0; n < curRoom.getCurDoors(); n++) {
                            doors1[n] = curRoom.getDoors()[n];
                        }
                        curRoom.setDoors(doors1);
                        v = 0;
                    } else if (rooms[i + k].getDoors().length == rooms[i + k].getCurDoors()
                            || rooms[i + k].getDoors().length - rooms[i + k].getCurDoors() == 1) {
                        y = 1;
                        k += 1;
                    } else {
                        if (rooms[i].getDoors().length > rooms[i].getCurDoors()) {
                            Door newDoor = new Door(rooms[i], rooms[i + k]);
                            boolean found = false;
                            while (!found) {
                                int potentialLocation = rNum.nextInt(4);
                                // Fixing the orientation of the doors (a right door when clicked
                                // should appear on the left in next room)
                                if (potentialLocation == 0) {
                                    // if top slot is available
                                    if (curRoom.getRoom()[0][8] == null) {
                                        newDoor.setX(8);
                                        newDoor.setY(0);
                                        newDoor.setImageURL("resources/images/new-door-up.png");
                                        curRoom.addObject(newDoor, 8, 0);
                                        found = true;
                                    } else {
                                        continue;
                                    }
                                } else if (potentialLocation == 1) {
                                    // if right slot is available
                                    if (curRoom.getRoom()[8][17] == null) {
                                        newDoor.setX(17);
                                        newDoor.setY(8);
                                        newDoor.setImageURL("resources/images/new-door-right.png");
                                        curRoom.addObject(newDoor, 17, 8);
                                        found = true;
                                    } else {
                                        continue;
                                    }
                                } else if (potentialLocation == 2) {
                                    // if bottom slot is available
                                    if (curRoom.getRoom()[17][9] == null) {
                                        newDoor.setX(9);
                                        newDoor.setY(17);
                                        newDoor.setImageURL("resources/images/new-door-down.png");
                                        curRoom.addObject(newDoor, 9, 17);
                                        found = true;
                                    } else {
                                        continue;
                                    }
                                } else if (potentialLocation == 3) {
                                    // the left slot is available
                                    if (curRoom.getRoom()[9][0] == null) {
                                        newDoor.setX(0);
                                        newDoor.setY(9);
                                        newDoor.setImageURL("resources/images/new-door-left.png");
                                        curRoom.addObject(newDoor, 0, 9);
                                        found = true;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            //curRoom.addDoor(newDoor);
                            // Adding similar door to other Room that the door connects to
                            Door oppositeDoor = new Door(newDoor.getRoomA(), newDoor.getRoomB());
                            if (newDoor.getLocation()[0] == 8 || newDoor.getLocation()[0] == 9) {
                                if (newDoor.getLocation()[1] == 0) {
                                    oppositeDoor.setY(17);
                                    oppositeDoor.setX(9);
                                    oppositeDoor.setImageURL("resources/images/new-door-down.png");
                                } else {
                                    oppositeDoor.setY(0);
                                    oppositeDoor.setX(8);
                                    oppositeDoor.setImageURL("resources/images/new-door-up.png");
                                }
                            } else {
                                if (newDoor.getLocation()[0] == 0) {
                                    oppositeDoor.setX(17);
                                    oppositeDoor.setY(8);
                                    oppositeDoor.setImageURL("resources/images/new-door-right.png");
                                } else {
                                    oppositeDoor.setX(0);
                                    oppositeDoor.setY(9);
                                    oppositeDoor.setImageURL("resources/images/new-door-left.png");
                                }
                            }
                            rooms[i + k].addDoor(oppositeDoor);
                            rooms[i + k].addObject(oppositeDoor, oppositeDoor.getLocation()[0],
                                    oppositeDoor.getLocation()[1]);
                            rooms[i].addDoor(newDoor);
                            doors[dcount] = newDoor;
                            dcount++;
                        }
                        k += 1;
                        v = 0; // added to stop infinite while loop
                    }
                }
            }
        }
    }

    public Door[] getDoors() {
        return doors;
    }

    public int getDoornum() {
        return doornum;
    }

    public int getRoomnum() {
        return roomnum;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public static void setStartItem(Item start) {
        startItem = start;
    }

    public static Item getStartItem() {
        return startItem;
    }
}