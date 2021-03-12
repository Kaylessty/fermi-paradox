package main;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Item implements Locatable, Collectible {

    private Possession thing;
    private int row;
    private int column;
/**
 * This enum represents all the possible Items the Player can carry with him/her.
 * All of these items can be carried and therefore implement both Locatable and Collectible.
 * The fields of each enum type passed into the constructor represent properties of the item.
 * These fields are in the order
 * (String type, damage, range, housingSpace, healthBoost, strengthBoost, speedBoost, purchaseCost,
 * returnCost, walkable, url)
 */
    public enum Possession  {
        SPACESWORD("Weapon", 200, 1, 2, 0, 0, 0, 500, 300, true, "resources/images/sword.png"),
        SONARGUN("Weapon", 150, 10, 3, 0, 0, 0, 600, 250, true, "resources/images/sword.png");

        private final String type;
        private final int damage;
        private final int range;
        private final int housingSpace;
        private final int healthBoost;
        private final int strengthBoost;
        private final int speedBoost;
        private final int purchaseCost;
        private final int returnCost;
        private final boolean walkable;
        private final String imageURL;

        Possession(String type, int damage, int range, int housingSpace, int healthBoost,
                   int strengthBoost, int speedBoost, int purchaseCost, int returnCost,
                   boolean walkable, String imageURL) {
            this.type = type;
            this.damage = damage;
            this.range = range;
            this.housingSpace = housingSpace;
            this.healthBoost = healthBoost;
            this.strengthBoost = strengthBoost;
            this.speedBoost = speedBoost;
            this.purchaseCost = purchaseCost;
            this.returnCost = returnCost;
            this.walkable = walkable;
            this.imageURL = imageURL;
        }
    }

    /**
     * This is a constructor that initializes aht the item is along with where it is in the room.
     * @param thing describes the item it can be. Look at the enum in this class
     * @param row the x-location of where this item will be placed in the room
     * @param column the y-location of where this item will be placed in the room
     */
    public Item(Possession thing, int row, int column) {
        this.thing = thing;
        this.row = row;
        this.column = column;
    }

    /**
     * This method overrides the method from Locatable interface
     * @return an array describing where the item is. The first element is the x-value
     * while the second element is the y-value
     */
    @Override
    public int[] getLocation() {
        int[] location = new int[2];
        location[0] = row;
        location[1] = column;
        return location;
    }

    /**
     * This method overrides the method from Collectible interface
     * @return an array describing where the item is. The first element is the x-value
     * while the second element is the y-value
     */
    @Override
    public int[] getPosition() {
        int[] position = {row, column};
        return position;
    }

    /**
     * This method overrides the method from the Locatable interface.
     */
    public String getImageURL() {
        return thing.imageURL;
    }

    /**
     * This method is a getter for the Item.
     * @return the particular Possession enum this Item contains.
     */
    public Possession getPossession() {
        return thing;
    }
}
