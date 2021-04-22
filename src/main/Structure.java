package main;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Random;
//import com.sun.org.apache.xpath.internal.operations.Bool;

public class Structure implements Locatable {
    private String name;
    private Structure.Possession thing;
    private int x;
    private int y;
    private Random rNum = new Random();

    /**
     * This enum represents all the possible Items the Player can carry with him/her.
     * All of these items can be carried and therefore implement both Locatable and Collectible.
     * The fields of each enum type passed into the constructor represent properties of the item.
     * These fields are in the order
     * (String type, damage, range, housingSpace, healthBoost, strengthBoost, speedBoost,
     * purchaseCost, returnCost, walkable, unlocker, idLevel, url)
     */
    public enum Possession  {
        CRATE("Prop", new Item(Item.Possession.HORN, 11, 11, "Clown Horn"), true,"resources/images/CRATE.png"),
        C_Chest("Prop", new Item(Item.Possession.HORN, 11, 11, "Clown Horn"), false,"resources/images/Chest.png"),
        O_Chest("Prop", new Item(Item.Possession.HORN, 11, 11, "Clown Horn"), true, "resources/images/Chest.png");
        private final String type;
        private final Item loot;
        private final String imageURL;
        private Boolean looted;

        Possession(String type, Item loot, Boolean looted, String imageURL) {
            this.type = type;
            this.imageURL = imageURL;
            this.loot = loot;
            this.looted = looted;
        }
        public String getimageURL() {
            return imageURL;
        }

        public Item getLoot() {
            return loot;
        }

        public Boolean getLooted() {
            return looted;
        }

        public void setLooted(boolean looted) {
            this.looted = looted;
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

    public Item getLV1Loot() {
        int ite = rNum.nextInt(21);
        Item toret = new Item(Item.Possession.HORN, 11, 11, "Clown Horn");;
        int pick;
        if(ite < 13) {
            pick = rNum.nextInt(3);
            switch (pick) {
                case 0:
                    toret = new Item(Item.Possession.HORN, 11, 11, "Clown Horn");
                    break;
                case 1:
                    toret = new Item(Item.Possession.BATTERYLV1, 11, 11, "Regeneron Battery LV1");
                    break;
                case 2:
                    toret = new Item(Item.Possession.G_BATTERYLV1, 11, 11, "Gun Battery");
                    break;
            }
        } else if( ite <18) {
            pick = rNum.nextInt(8);
            switch (pick) {
                case 0:
                    toret = new Item(Item.Possession.LASER, 11, 11, "Laser");
                    break;
                case 1:
                    toret = new Item(Item.Possession.ENERGYSWORD, 11, 11, "Energy Sword");
                    break;
                case 2:
                    toret = new Item(Item.Possession.SONICRIFLE, 11, 11, "Sonic Rifle");
                    break;
                case 3:
                    toret = new Item(Item.Possession.SHIELDGENERATOR_LV1, 11, 11, "Shield Generator LV1");
                    break;
                case 4:
                    toret = new Item(Item.Possession.BATTERYLV2, 11, 11, "Regeneron Battery LV2");
                    break;
                case 5:
                    toret = new Item(Item.Possession.G_BATTERYLV2, 11, 11, "Gun Battery LV2");
                    break;
                case 6:
                    toret = new Item(Item.Possession.AMMOBOX, 11, 11, "Ammo");
                    break;
                case 7:
                    toret = new Item(Item.Possession.SHOCKRIFLE, 11, 11, "Shock Rifle");
                    break;
            }
        } else if(ite < 20) {
            pick = rNum.nextInt(5);
            switch (pick) {
                case 0:
                    toret = new Item(Item.Possession.SONICSWORD, 11, 11, "Sonic Sword");
                    break;
                case 1:
                    toret = new Item(Item.Possession.BATTERYLV3, 11, 11, "Regeneron Battery LV3");
                    break;
                case 2:
                    toret = new Item(Item.Possession.G_BATTERYLV3, 11, 11, "Gun Battery LV3");
                    break;
                case 3:
                    toret = new Item(Item.Possession.SHIELDGENERATOR_LV2, 11, 11, "Shield Generator LV2");
                    break;
                case 4:
                    toret = new Item(Item.Possession.DB_ENERGYSWORD, 11, 11, "Double Bladed Energy Sword");
                    break;
            }
        } else if(ite == 20) {
            pick = rNum.nextInt(2);
            switch (pick) {
                case 0:
                    toret = new Item(Item.Possession.DB_SONICSWORD, 11, 11, "Double Bladed Sonic Sword");
                    break;
                case 1:
                    toret = new Item(Item.Possession.SONICCANNON, 11, 11, "Double Bladed Energy Sword");
                    break;
            }
        }
        return toret;
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

    public Possession getPossession() {
        return thing;
    }
    /**
     * function to get the appropriate size of the item based on type
     * @return int size based on the item type
     */

}
