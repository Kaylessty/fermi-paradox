package main;

import java.util.Random;

public class Structure implements Locatable {
    private String name;
    private Structure.Possession thing;
    private int x;
    private int y;
    /**
     * This enum represents all the possible Items the Player can carry with him/her.
     * All of these items can be carried and therefore implement both Locatable and Collectible.
     * The fields of each enum type passed into the constructor represent properties of the item.
     * These fields are in the order
     * (String type, damage, range, housingSpace, healthBoost, strengthBoost, speedBoost,
     * purchaseCost, returnCost, walkable, unlocker, idLevel, url)
     */
    public enum Possession  {
        CRATE("Prop",
                "resources/images/CRATE.png");

        private final String type;
        private final String imageURL;

        Possession(String type, String imageURL) {
            this.type = type;
            this.imageURL = imageURL;
        }
        public String getimageURL() {
            return imageURL;
        }
    }

    /**
     * This is a constructor that initializes aht the item is along with where it is in the room.
     * @param thing describes the item it can be. Look at the enum in this class
     * @param x the x-location of where this item will be placed in the room
     * @param y the y-location of where this item will be placed in the room
     * @param name the name of the Item
     */
    public Structure(Structure.Possession thing, int x, int y, String name) {
        this.thing = thing;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * This method overrides the method from Locatable interface
     * @return an array describing where the item is. The first element is the x-value
     * while the second element is the y-value
     */
    @Override
    public int[] getLocation() {
        int[] location = new int[2];
        location[0] = x;
        location[1] = y;
        return location;
    }

    public void setPosition(int[] pos) {
        this.x = pos[0];
        this.x = pos[1];
    }
    /**
     * This method overrides the method from the Locatable interface.
     * @return the path to the image of this Item
     */
    public String getImageURL() {
        return thing.imageURL;
    }


    /**
     * getter for the name instance variable
     * @return String name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * function to get the appropriate size of the item based on type
     * @return int size based on the item type
     */

}