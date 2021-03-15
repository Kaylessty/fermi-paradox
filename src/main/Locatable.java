package main;

/**
 * This interface should be implemented by all objects that are placed visibally inside a room
 */
public interface Locatable {

    /**
     * This method is used to retrieve the location of an object in the room
     * @return a List of length two with the x and y coordinates of the location.
     * x and y coordinates begin at 0 in top left corner
     */
    int[] getLocation();

    /**
     * This method is helpful in displaying the Locatable object.
     * @return the image url
     */
    String getImageURL();

}
