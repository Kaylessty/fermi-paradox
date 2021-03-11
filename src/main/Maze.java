package main;

import java.util.Random;
import java.util.ArrayList;

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
    private int x;
    private int y;

    /**
     * Creates a maze and fills it with a random number between 5 and 20 random rooms of size x and y.
     */
    public Maze() {
        roomnum = 10 + rNum.nextInt(10);
        rooms = new Room[roomnum];
        rooms[0] = new Room(x, y, 4, 1);
        for(int i = 1; i < roomnum; i++) {
            rooms[i] = new Room(x,y);
            doornum += rooms[i].getDoornumber();
        }
        doors = new Door[doornum];
        Room curRoom;
        int curDoorNumber;
        int counter;
        int dcount = 0;
        for(int i = 0; i < roomnum; i++) {
            curRoom = rooms[i];
            Door[] doorlist = curRoom.getDoors();
            for(int j = curRoom.getCurDoors(); j <curRoom.getDoors().length; j++){
                int v = 1;
                int k = 1;
                while(v==1) {
                    if(j+i+k>=rooms.length) {
                        Door[] doors1 = new Door[curRoom.getCurDoors()];
                        for (int n = 0; n < curRoom.getCurDoors(); n++) {
                            doors1[n] = curRoom.getDoors()[n];
                        }
                        curRoom.setDoors(doors1);
                        return;
                    } else if (rooms[j+i+k].getDoors().length==rooms[j+i+k].getCurDoors()){
                        y = 1;
                    } else {
                        Door newDoor = new Door(rooms[i],rooms[j+i+k]);
                        curRoom.addDoor(newDoor);
                        rooms[j+i+k].addDoor(newDoor);
                        doors[dcount] = newDoor;
                        dcount++;
                        v=0; // added to stop infinite while loop
                        k = 0;
                    }
                    if((j+i+k)==rooms.length-1) {
                        v=0; // added to stop infinite while loop
                        k = 0;
                    }
                }

            }
        }
        rooms[rooms.length-1].setHasHatch(true);
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
