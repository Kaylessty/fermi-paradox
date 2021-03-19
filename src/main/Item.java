package main;

public class Item implements Locatable, Collectible {

    private String name;
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
        A_ENERGYSWORD("Weapon", 100000, 1, 2, 0, 0, 0, 500, 300, true, false, -1, "resources/images/A_ENERGYSWORD.png"),
        A_SHOCKRIFLE("Weapon", 100000, 100, 3, 0, 0, 0, 600, 250, true, false, -1, "resources/images/A_SHOCKRIFLE.png"),
        IMPROVISEDSWORD("Weapon", 56, 0, 3, 0, 0, 0, 600, 250, true, false, -1, "resources/images/IMPROVISEDSWORD.png"),
        IMPROVISEDGUN("Weapon", 56, 0, 3, 0, 0, 0, 600, 250, true, false, -1, "resources/images/IMPROVISEDGUN.png"),
        AAID("ID", 4, 0, 1, 0, 0, 0, 999999, 0, true, true, 100, "resources/images/ID.png"),
        HORN("misc", 1, 0, 1, 0, 0, 0, 999999, 0, true, false, -1, "resources/images/Horn.png"),
        ONEID("ID", 4, 0, 1, 0, 0, 0, 999999, 0, true, true, 1, "resources/images/ID.png");
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
        private final boolean unlocker;
        private final int idLevel;
        private final String imageURL;

        Possession(String type, int damage, int range, int housingSpace, int healthBoost,
                   int strengthBoost, int speedBoost, int purchaseCost, int returnCost,
                   boolean walkable, boolean unlocker, int idLevel, String imageURL) {
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
            this.unlocker = unlocker;
            this.idLevel = idLevel;
            this.imageURL = imageURL;
        }

        public int getIdLevel() {
            return idLevel;
        }
    }

    /**
     * This is a constructor that initializes aht the item is along with where it is in the room.
     * @param thing describes the item it can be. Look at the enum in this class
     * @param row the x-location of where this item will be placed in the room
     * @param column the y-location of where this item will be placed in the room
     */
    public Item(Possession thing, int row, int column, String name) {
        this.thing = thing;
        this.row = row;
        this.column = column;
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
    public int getSize() {
        switch (thing.type) {
            case "Weapon":
                return 64;
            case "ID":
                return 25;
            default:
                return 40;
        }
    };
}
