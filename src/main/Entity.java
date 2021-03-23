package main;

public class Entity {
    private int currentPos;

    /* Note: As is, this method is a placeholder
     * It is meant to encompass anything that can show up in a
     * tile on screen (Including monsters, players, chests, etc.)
     * This connects to the "grid" class, which will be intended
     * to hold Entities and maintain their location.
     *
     * In this sense, each entity has a "currentPos" coresponding to their
     * position in the Grid array. Initialized as -1, as if not in the grid
     * array.
     */
    public Entity() {
        this.currentPos = -1;
    }

    /**
     * Sets the current position of an entity in the grid array
     * @param pos position of Entity in grid array
     */
    public void setCurrentPos(int pos) {
        this.currentPos = pos;
    }

    /**
     * Gets the current position of an entity in the grid array
     * @return Current position of entity in grid, or -1 if it isn't in the
     * grid.
     */
    public int getCurrentPos() {
        return this.currentPos;
    }
}
