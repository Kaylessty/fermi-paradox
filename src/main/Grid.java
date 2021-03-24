package main;

public class Grid {
    private Entity[] gridPosition;

    /**
     * Initializes grid array: Meant to be 18x18 grid, but will
     * be stored in an single array.
     */
    public Grid() {
        this.gridPosition = new Entity[324];
    }

    /**
     * Moves entity left by shifting position in grid Array
     * @param e Entity being moved
     */
    public void moveLeft(Entity e) {
        if ((e.getCurrentPos() % 18) != 0) {
            e.setCurrentPos(e.getCurrentPos() - 1);
        }
    }

    /**
     * Moves entity right by shifting position in grid Array
     * @param e Entity being moved
     */
    public void moveRight(Entity e) {
        if ((e.getCurrentPos() % 18) != 17) {
            e.setCurrentPos(e.getCurrentPos() + 1);
        }
    }

    /**
     * Moves entity up by shifting position in grid Array
     * @param e Entity being moved
     */
    public void moveUp(Entity e) {
        if (e.getCurrentPos() >= 18) {
            e.setCurrentPos(e.getCurrentPos() - 18);
        }
    }

    /**
     * Moves entity down by shifting position in grid Array
     * @param e Entity being moved
     */
    public void moveDown(Entity e) {
        if (e.getCurrentPos() <= 305) {
            e.setCurrentPos(e.getCurrentPos() + 18);
        }
    }
}
