package main;

import java.util.Random;

/**
 * A class to hold static math functions to be used in other classes
 */
public class MathHelper {

    private static final Random rand = new Random();

    /**
     * This function returns a random int
     * @param upperBound int upper bound of the number to be generated
     * @return the generated random integer
     */
    public static int randomInt(int upperBound) {
        return rand.nextInt(upperBound);
    }

    /**
     * This function returns a random int
     * @param lowerBound int lower bound of the number
     * @param upperBound int upper bound of the number
     * @return the generated random integer
     */
    public static int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

    /**
     * This function generates a random Direction
     * @return Direction randomly generated
     */
    public static Direction randomDirection() {
        return Direction.values()[rand.nextInt(Direction.values().length)];
    }

    public enum Direction {
        NORTH(0, -1),
        SOUTH(0, 1),
        WEST(-1, 0),
        EAST(1, 0);

        public int dirX;
        public int dirY;
        public Direction opposite;

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