package main;

import java.util.Random;

/**
 * The Maze class acts as a graph of sorts for the Rooms, all existing doors can be found in the rooms array and all
 * existing doors can be found in the doors array. the ints x and y should be used to determine room size.
 */
public class Maze {
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
        roomnum = 10 + rNum.nextInt(10);
        rooms = new Room[roomnum];
        System.out.println(rooms.length);
        rooms[0] = new Room(x, y, 4, 1, "first");
        doornum = 4;
        for(int i = 1; i < roomnum; i++) {
            rooms[i] = new Room(x,y,"" + i);
            // Shouldn't doornum start off at the value 4?
            doornum += rooms[i].getDoornumber();
        }
        rooms[rooms.length-2] = new Room(x, y, 4, 1, "last");
        doors = new Door[doornum];
        Room curRoom;
        int curDoorNumber;
        int counter;
        int dcount = 0;
        // Iterate over each room
        for(int i = 0; i < roomnum; i++) {
            curRoom = rooms[i];
            int k = 1;
            int cudoor = curRoom.getCurDoors();
            // Iterates over the number of doors left to be added in that room
            for(int j = 0; j <curRoom.getDoors().length-cudoor; j++){
                int v = 1;
                // Finding a suitable room to connect to the door
                while(v==1) {
                    if(i+k >= rooms.length) {
                        Door[] doors1 = new Door[curRoom.getCurDoors()];
                        for (int n = 0; n < curRoom.getCurDoors(); n++) {
                            doors1[n] = curRoom.getDoors()[n];
                        }
                        curRoom.setDoors(doors1);
                        v = 0;
                    } else if (rooms[i+k].getDoors().length-1<=rooms[i+k].getCurDoors() || rooms[i+k].getDoors().length == 1){
                        y = 1;
                        k += 1;
                    } else {
                        if(rooms[i].getDoors().length>rooms[i].getCurDoors()) {
                            Door newDoor = new Door(rooms[i],rooms[i+k]);
                            curRoom.addDoor(newDoor);
                            rooms[i + k].addDoor(newDoor);
                            rooms[i].addDoor(newDoor);
                            doors[dcount] = newDoor;
                            dcount++;
                        }
                        k += 1;
                        v = 0; // added to stop infinite while loop
                    }
                    if((i+k)==rooms.length-1) {
                        k += 1;
                        v=0; // added to stop infinite while loop
                    }
                }

            }
        }
        rooms[rooms.length-2].setHasHatch(true);
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
}
