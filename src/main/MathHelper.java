package main;

import java.util.Random;

public class MathHelper {

    private static final Random RAND = new Random();

    public static int randomInt(int upperBound) {
        return RAND.nextInt(upperBound);
    }

    public static int randomInt(int lowerBound, int upperBound) {
        return RAND.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

    public static Direction randomDirection() {
        return Direction.values()[RAND.nextInt(Direction.values().length)];
    }

    public enum Direction {
        NORTH(0, -1),
        SOUTH(0, 1),
        WEST(-1, 0),
        EAST(1, 0);

        private int dirX;
        private int dirY;
        private Direction opposite;

        static {
            NORTH.opposite = SOUTH;
            SOUTH.opposite = NORTH;
            WEST.opposite = EAST;
            EAST.opposite = WEST;
        }

        public int getDirX() {
            return dirX;
        }

        public int getDirY() {
            return dirY;
        }

        private Direction(int dirX, int dirY) {
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }
}
