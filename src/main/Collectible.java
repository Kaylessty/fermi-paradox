package main;


/**
 * This interface should be implemented for all objects
 * that implement Locatable but can also be carried by the Player.
 */
public interface Collectible {

    /**
     * This method is used to retrieve the location of an object in the room that can be carried
     * @return a List of length two with the x and y coordinates of the location.
     * x and y coordinates begin at 0 in top left corner
     */
    int[] getPosition();
}
