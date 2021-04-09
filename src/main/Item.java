package main;

public class Item implements Locatable, Collectible {

    private String name;
    private Possession thing;
    private int x;
    private int y;

    public enum Possession  {
        A_ENERGYSWORD("Sword", 100000, 1, 4, 0, 0, 0, 500, 300, true, false, -1,
                "resources/images/A_ENERGYSWORD.png"),
        ENERGYSWORD("Sword", 400, 1, 4, 0, 0, 0, 500, 300, true, false, -1,
                "resources/images/ENERGYSWORD.png"),
        LASER("Gun", 150, 10, 4, 0, 0, 0, 500, 300, true, false, -1,
                "resources/images/LASER.png"),
        SONICRIFLE("Gun", 350, 4, 4, 0, 0, 0, 500, 300, true, false, -1,
                "resources/images/SONICRIFLE.png"),
        A_SHOCKRIFLE("Gun", 100000, 100, 4, 0, 0, 0, 600, 250, true, false, -1,
                "resources/images/A_SHOCKRIFLE.png"),
        IMPROVISEDSWORD("Sword", 100, 1, 3, 0, 0, 0, 600, 250, true, false, -1,
                "resources/images/IMPROVISEDSWORD.png"),
        IMPROVISEDGUN("Gun", 56, 5, 3, 0, 0, 0, 600, 250, true, false, -1,
                "resources/images/IMPROVISEDGUN.png"),
        AAID("ID", 4, 0, 1, 0, 0, 0, 999999, 0, true, true, 100,
                "resources/images/ID.png"),
        HORN("misc", 1, 1, 1, 0, 0, 0, 999999, 0, true, false, -1,
                "resources/images/Horn.png"),
        BALLOON_R("misc", 0, 0, 1, 0, 0, 0, 999999, 0, true, false, -1,
                "resources/images/BALLOON_R.png"),
        BALLOON_G("misc", 0, 0, 1, 0, 0, 0, 999999, 0, true, false, -1,
                "resources/images/BALLOON_G.png"),
        BALLOON_Y("misc", 0, 0, 1, 0, 0, 0, 999999, 0, true, false, -1,
                "resources/images/BALLOON_Y.png"),
        BALLOON_B("misc", 0, 0, 1, 0, 0, 0, 999999, 0, true, false, -1,
                "resources/images/BALLOON_B.png"),
        ONEID("ID", 4, 0, 1, 0, 0, 0, 999999, 0, true, true, 1,
                "resources/images/ID.png");
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

        //Possession(String type, int damage, int range, int housingSpace, int healthBoost,
        //int strengthBoost, int speedBoost, int purchaseCost, int returnCost,
        //boolean walkable, String imageURL) {
        Possession(String type, int damage, int range, int purchaseCost, String imageURL) {
            this.type = type;
            this.damage = damage;
            this.range = range;
            this.housingSpace = 0;
            this.healthBoost = 0;
            this.strengthBoost = 0;
            this.speedBoost = 0;
            this.purchaseCost = purchaseCost;
            this.returnCost = 0;
            this.walkable = false;
            this.imageURL = imageURL;
            this.idLevel = 0;
            this.unlocker = false;
        }

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

        public int getRange() {
            return range;
        }

        public int getDamage() {
            return damage;
        }
    }

    /**
     * This is a constructor that initializes aht the item is along with where it is in the room.
     * @param thing describes the item it can be. Look at the enum in this class
     * @param x the x-location of where this item will be placed in the room
     * @param y the y-location of where this item will be placed in the room
     * @param name the name of the Item
     */
    public Item(Possession thing, int x, int y, String name) {
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

    /**
     * This method overrides the method from Collectible interface
     * @return an array describing where the item is. The first element is the x-value
     * while the second element is the y-value
     */
    @Override
    public int[] getPosition() {
        int[] position = {x, y};
        return position;
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
        return thing.housingSpace;
    }
}
