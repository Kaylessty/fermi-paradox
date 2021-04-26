package main;

import java.util.Random;

/**
 * This class describes a room. A room is a 2-D array (18 by 18) where each block in the room is
 * 32x32 pixels.
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
    private Monster[] killThem;
    private int numRoom;
    private int special;
    private int roomType;
    private int slugNum = 0;
    private static int difficulty;

    /**
     * This constructor initializes the location of this room on the grid-like maze
     * @param row the row number on the grid where this room is. Rows start at 0.
     * @param column the column number on the grid where the room is. Columns start at 0.
     * @param roomName the name of the room. Will be displayed when room appears
     * @param numRoom the number associated with the room. Helps to find relative depth in maze
     */
    public Room(int row, int column, String roomName, int numRoom) {
        roomType = 1 + rNum.nextInt(3);
        this.row = row;
        this.column = column;
        room = new Locatable[18][18];
        doornumber = 1 + rNum.nextInt(4);
        distance = 999;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
        this.monsterNum = 1 + rNum.nextInt(3);
        killThem = new Monster[monsterNum];
        this.numRoom = numRoom;
        special = rNum.nextInt(15);
        if (doornumber == 1 || special < 2) {
            Monster orb = new Challenge();
            removeObject(
                    orb.getLocation()[0], orb.getLocation()[1]);
            orb.setLocation(8, 8);
            addMonster(orb, 1 * difficulty, 0);
            /*int r = 1 + rNum.nextInt(3);
            if (r == 1) {
                Monster creature = new Larry();
                removeObject(
                        creature.getLocation()[0], creature.getLocation()[1]);
                creature.setLocation(8, 8);
                addMonster(creature, 10000 * difficulty, 4000);
            } else if (r == 2) {
                Monster creature = new TreeBore();
                removeObject(
                        creature.getLocation()[0], creature.getLocation()[1]);
                creature.setLocation(8, 8);
                addMonster(creature, 17000 * difficulty, 4000);
            } else if (r == 3) {
                Monster creature = new Teeth();
                removeObject(
                        creature.getLocation()[0], creature.getLocation()[1]);
                creature.setLocation(8, 8);
                addMonster(creature, 13000 * difficulty, 4000);
            }*/
            monsterNum = 0;
            return;
        }
        if (roomType == 1 && special != 14) {
            int cnum = rNum.nextInt(15);
            for (int i = 0; i < cnum; i++) {
                int x = 1 + rNum.nextInt(17);
                int y = 1 + rNum.nextInt(17);
                addObject(new Structure(Structure.Possession.CRATE, x, y, "Crate"), x, y);
            }
        }
        if (special == 12 || special == 11) {
            Monster engi = new EngiShop();
            monsterNum = 1;
            engi.setHealth(10000);
            engi.setDamage(2000);
            room[engi.getLocation()[1]][engi.getLocation()[0]] = engi;
            killThem[0] = engi;
        }
        if (special == 13) {
            Monster prowler = new ProwlerShop();
            monsterNum = 1;
            prowler.setHealth(20000);
            prowler.setDamage(4000);
            room[prowler.getLocation()[1]][prowler.getLocation()[0]] = prowler;
            killThem[0] = prowler;
        }
        if (special == 14) {
            this.roomName = "Clown Room";
            Monster clown = new ClownShop();
            monsterNum = 1;
            room[clown.getLocation()[1]][clown.getLocation()[0]] = clown;
            killThem[0] = clown;
            //addObject(clown, clown.getLocation()[1], clown.getLocation()[0]);
            addObject(new Item(Item.Possession.HORN, 11, 11, "Clown Horn"), 11, 11);
            addObject(new Item(Item.Possession.BALLOON_R, 15, 15, "Red Balloon"), 15, 15);
            addObject(new Item(Item.Possession.BALLOON_G, 15, 15, "Red Balloon"), 15, 13);
            addObject(new Item(Item.Possession.BALLOON_Y, 15, 15, "Red Balloon"), 13, 15);
            addObject(new Item(Item.Possession.BALLOON_R, 15, 15, "Red Balloon"), 0, 1);
            addObject(new Item(Item.Possession.BALLOON_G, 15, 15, "Red Balloon"), 0, 0);
            addObject(new Item(Item.Possession.BALLOON_Y, 15, 15, "Red Balloon"), 1, 0);
            addObject(new Item(Item.Possession.BALLOON_Y, 15, 15, "Red Balloon"), 15, 1);
            addObject(new Item(Item.Possession.BALLOON_G, 15, 15, "Red Balloon"), 15, 0);
            addObject(new Item(Item.Possession.BALLOON_R, 15, 15, "Red Balloon"), 14, 0);
            addObject(new Item(Item.Possession.BALLOON_G, 15, 15, "Red Balloon"), 0, 14);
            addObject(new Item(Item.Possession.BALLOON_B, 15, 15, "Red Balloon"), 0, 15);
            addObject(new Item(Item.Possession.BALLOON_Y, 15, 15, "Red Balloon"), 1, 15);
        }
        if (roomType == 1 && special != 14) {
            int cnum = rNum.nextInt(6);
            for (int i = 0; i < cnum; i++) {
                int x = 1 + rNum.nextInt(17);
                int y = 1 + rNum.nextInt(17);
                if (getRoom()[x][y] == null) {
                    addObject(new Structure(Structure.Possession.CRATE, x, y, "Crate"), x, y);
                }
            }
        }
        if (!roomName.equals("first") && !roomName.equals("last")) {
            addMonsters();
        }
        Boolean cnf = true;
        while (cnf) {
            int xc = 4 + rNum.nextInt(9);
            int yc = 4 + rNum.nextInt(9);
            if (getRoom()[yc][xc] == null) {
                addObject(new Structure(Structure.Possession.C_Chest, xc, yc, "Chest"), xc, yc);
                cnf = false;
            }
        }
    }

    public Room(int row, int column, int doornumber, int distance, int monsterNum,
                String roomName, int numRoom) {
        roomType = 1 + rNum.nextInt(3);
        this.row = row;
        this.column = column;
        this.doornumber = doornumber;
        this.distance = distance;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
        this.monsterNum = monsterNum;
        killThem = new Monster[monsterNum];
        this.numRoom = numRoom;
        if (!roomName.equals("last") && !roomName.equals("first")) {
            addMonsters();
        }
    }

    public Room(int row, int column, int doornumber, int monsterNum, String roomName, int numRoom) {
        roomType = 1 + rNum.nextInt(3);
        this.row = row;
        this.column = column;
        this.doornumber = doornumber;
        this.distance = 999;
        doors = new Door[doornumber];
        this.roomName = "room " + roomName;
        this.monsterNum = monsterNum;
        killThem = new Monster[monsterNum];
        this.numRoom = numRoom;
        if (!roomName.equals("last") && !roomName.equals("first")) {
            addMonsters();
        }
    }

    private void addMonsters() {
        for (int index = 0; index < monsterNum; index++) {
            int pick1 = 1 + rNum.nextInt(7);
            Monster creature;
            if (pick1 == 1 || pick1 == 2 || pick1 == 3) {
                creature = new Monster1();
                creature.setHealth(3000 * difficulty);
                creature.setDamage(3000);
            } else if (pick1 == 4 || pick1 == 5 || pick1 == 6) {
                creature = new Monster2();
                creature.setHealth(1400 * difficulty);
                creature.setDamage(2000);
            } else {
                creature = new Monster3();
                creature.setHealth(960 * difficulty);
                creature.setDamage(4999);
            }
            killThem[index] = creature; //************************************
            room[creature.getLocation()[1]][creature.getLocation()[0]] = creature;
        }
    }

    public void addMonster(Monster monster, int heath, int damage) {
        monster.setHealth(heath);
        monster.setDamage(damage);
        killThem = new Monster[1];
        killThem[0] = monster;
        room[monster.getLocation()[1]][monster.getLocation()[0]] = monster;
    }
    public void addObject(Locatable object, int x, int y) {
        room[y][x] = object;
    }

    public void removeObject(int x, int y) {
        if (room[y][x] instanceof Monster) {
            for (int index = 0; index < monsterNum; index++) {
                if (killThem[index] == room[y][x]) {
                    killThem[index] = null;
                }
            }
        }
        room[y][x] = null;
        //System.out.println("removed");
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
            if (doors[i] == null && g != 1) {
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

    public void setMonsterNum(int number) {
        monsterNum = number;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room r = (Room) o;
        return r.getRoomName().equals(this.roomName);
    }

    public void setHasHatch(boolean hasHatch) {
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

    public Monster[] getMonsters() {
        return killThem;
    }

    public int getRoomType() {
        return roomType;
    }

    public int getSlugNum() {
        return slugNum;
    }

    public void setSlugNum(int slugNum) {
        this.slugNum = slugNum;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Room.difficulty = difficulty;
    }
}