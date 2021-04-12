package main;

import java.util.Random;

public class MathHelper {

    private static final Random RANDOM = new Random();

    public static int randomInt(int upperBound) {
        return RANDOM.nextInt(upperBound);
    }

    public static int randomInt(int lowerBound, int upperBound) {
        return RANDOM.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

    public static Direction randomDirection() {
        return Direction.values()[RANDOM.nextInt(Direction.values().length)];
    }

    public enum Direction {
        NORTH(0, -1),
        SOUTH(0, 1),
        WEST(-1, 0),
        EAST(1, 0);

        protected int dirX;
        protected int dirY;
        protected Direction opposite;

        static {
            NORTH.opposite = SOUTH;
            SOUTH.opposite = NORTH;
            WEST.opposite = EAST;
            EAST.opposite = WEST;
        }

        private Direction(int dirX, int dirY) {
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }
}
