package main;

public class Item implements Locatable, Collectible {

    private String name;
    private Possession thing;
    private int x;
    private int y;

    public enum Possession  {
        A_ENERGYSWORD("Sword", new int[]{200000, 1, 4, 100000, 100000, -1},
                "A very powerful weapon used by only the highest ranking members "
                        + "of central command to erase anything which displeased them, "
                        + "you feel powerful just holding it.",
                "resources/images/A_ENERGYSWORD.png"),
        SHADOWSWORD("Sword", new int[]{2000, 1, 4, 80000, 50000, -1},
                "A powerful weapon that resonates with a strange energy, "
                        + "can hit a target up close and from exactly 6 meters away",
                "resources/images/SHADOWSWORD.png"),
        TOOTHBRUSH("Sword", new int[]{3000, 1, 4, 80000, 50000, -1},
                "A very sharp axe made from the monster Teeth",
                "resources/images/TOOTHBRUSH.png"),
        BORESTAFF("Sword", new int[]{4000, 0, 4, 80000, 50000, -1},
                "A long staff with a strange wriggling mass at the top.",
                "resources/images/BORESTAFF.png"),
        ENERGYSWORD("Sword", new int[]{500, 1, 4, 1000, 700, -1},
                "A bladed weapon vibrating with a powerful "
                        + "energy source, can cut through almost anything",
                "resources/images/ENERGYSWORD.png"),
        DB_ENERGYSWORD("Sword", new int[]{2500, 1, 4, 20000, 17000, -1},
                "A energy sword with an extra blade, a very powerful energy "
                        + "weapon for those with high skill, double blades double damage",
                "resources/images/DB_ENERGYSWORD.png"),
        SONICSWORD("Sword", new int[]{500, 3, 4, 10000, 8000, -1},
                "A sword that uses the power of sound the extend the rage beyond"
                        + " what most weapons are capable of",
                "resources/images/SONICSWORD.png"),
        DB_SONICSWORD("Sword", new int[]{2500, 3, 4, 30000, 27000, -1},
                "A sonic sword with an extra blade, a very powerful sonic weapon"
                        + " for those with high skill, double blades double damage",
                "resources/images/DB_SONICSWORD.png"),
        LASER("Gun", new int[]{150, 7, 4, 1000, 700, -1},
                "A small and fairly week laser weapon typically used for personal defense",
                "resources/images/LASER.png"),
        SHOCKRIFLE("Gun", new int[]{500, 10, 4, 8000, 6500, -1},
                "The rifle version of the smaller laser pistol.",
                "resources/images/SHOCKRIFLE.png"),
        PISTOL("Gun", new int[]{700, 10, 1, 5500, 3000, -1},
                "A small yet powerful ballistic weapon, needs ammo to function",
                "resources/images/PISTOL.png"),
        A_RIFLE("Gun", new int[]{3000, 20, 4, 9000000, 7000000, -1},
                "A very powerful automatic ballistic weapon, one of a kind."
                        + " The name 'Prowler' is engraved in it",
                "resources/images/RIFLE.png"),
        SONICRIFLE("Gun", new int[]{350, 5, 4, 1000, 700, -1},
                "A weapon that uses a burst of low frequency  sound to deal high damage.",
                "resources/images/SONICRIFLE.png"),
        SONICCANNON("Gun", new int[]{1850, 5, 4, 20000, 17000, -1},
                "A very powerful sonic weapon. High damage, low range",
                "resources/images/SONICCANNON.png"),
        A_SHOCKRIFLE("Gun", new int[]{60000, 20, 4, 100000, 100000, -1},
                "A very powerful weapon used by only the highest ranking members of"
                        + " central command to erase anything which displeased them, "
                        + "you feel powerful just holding it.",
                "resources/images/A_SHOCKRIFLE.png"),
        H_RIFLE("Gun", new int[]{2200, 20, 4, 95000, 88000, -1},
                "A hunting rifle, it seems very old and worn out, yet still "
                        + "very powerful. High damage, High range, needs ammo to function",
                "resources/images/H_RIFLE.png"),
        IMPROVISEDSWORD("Sword", new int[]{56, 1, 3, 300, 200, -1},
                "A very poorly made sword, its just a sharpened pipe",
                "resources/images/IMPROVISEDSWORD.png"),
        IMPROVISEDGUN("Gun", new int[]{56, 3, 3, 300, 200, -1},
                "A very poorly made shot gun, it's just a pipe duck taped to an igniter."
                        + " It could fall apart at any moment",
                "resources/images/IMPROVISEDGUN.png"),
        AAID("ID", new int[]{4, 0, 1, 100000, 1000000, 100},
                "An administrator identification card, used by central "
                        + "members of central command. Almost like a skeleton key",
                "resources/images/ID.png"),
        HORN("misc", new int[]{1, 0, 1, 500, 500, -1},
                "A simple clown horn, it goes honk, nothing more",
                "resources/images/Horn.png"),
        BOREEYE("heal", new int[]{1000, 0, 1, 300, 300, -1},
                "An eye from the monster Eye Bore, drips with a strange yet"
                        + " soothing liquid, upon use restores 1000 health",
                "resources/images/BOREEYE.png"),
        BATTERYLV1("heal", new int[]{2500, 0, 1, 500, 300, -1},
                "A battery that when used on your suit can both heal "
                        + "wounds and recharge shields, this one looks very poorly made, "
                        + "it will restore 2500 health on use",
                "resources/images/BATTERYLV1.png"),
        BATTERYLV2("heal", new int[]{5000, 0, 1, 1000, 900, -1},
                "A battery that when used on your suit can both heal wounds"
                        + " and recharge shields, it will restore 5000 health on use",
                "resources/images/BATTERYLV2.png"),
        BATTERYLV3("heal", new int[]{10000, 0, 1, 1500, 1400, -1},
                "A battery that when used on your suit can both heal wounds"
                        + " and recharge shields, this one looks advanced, "
                        + "it will restore 10000 health on use",
                "resources/images/BATTERYLV3.png"),
        BATTERYLV4("heal", new int[]{20000, 0, 1, 2000, 1800, -1},
                "A battery that when used on your suit can both heal wounds"
                        + " and recharge shields, this one looks very advanced, "
                        + "it will restore 20000 health on use",
                "resources/images/BATTERYLV4.png"),
        G_BATTERYLV1("charge", new int[]{2, 0, 1, 1000, 300, -1},
                "A small device that can be used to increase the damage"
                        + " any weapon's next attack. This one looks very poorly put together",
                "resources/images/G_BATTERY_LV1.png"),
        G_BATTERYLV2("charge", new int[]{5, 0, 1, 5000, 300, -1},
                "A small device that can be used to increase the "
                        + "damage any weapon's next attack.",
                "resources/images/G_BATTERY_LV2.png"),
        G_BATTERYLV3("charge", new int[]{10, 0, 1, 10000, 300, -1},
                "A small device that can be used to increase the damage any "
                        + "weapon's next attack. This one looks slightly advanced",
                "resources/images/G_BATTERY_LV3.png"),
        G_BATTERYLV4("charge", new int[]{20, 0, 1, 20000, 300, -1},
                "A small device that can be used to increase the damage"
                        + " any weapon's next attack. This one looks very advanced",
                "resources/images/G_BATTERY_LV4.png"),
        HIDE("misc", new int[]{500, 0, 1, 300, 300, -1},
                "Thick hide from a slain Teaff. "
                        + "Can be used to temporarily increase max heath",
                "resources/images/TEAFFHIDE.png"),
        AMMOBOX("misc", new int[]{500, 0, 1, 1000, 800, -1},
                "A box of 9mm rounds, there are 10 in the box",
                "resources/images/AMMOBOX.png"),
        TIMKEY("misc", new int[]{0, 0, 1, 1500, 1000, -1},
                "A very odd metallic artifact, it resonates with a "
                        + "strange energy. Can be used to teleport through one "
                        + "door, does not unlock the door",
                "resources/images/TIMKEY.png"),
        BOREHEART("misc", new int[]{0, 0, 1, 20000, 17000, -1},
                "A strange mass of tentacles and what looks to be some "
                        + "kind of slimy flesh make up this ood looking heart, "
                        + "its still beating. Once equiped, health increased by "
                        + "5000 upon killing an enemy.",
                "resources/images/BOREHEART.png"),
        SHADOWGAUNTLET("misc", new int[]{0, 0, 1, 20000, 17000, -1},
                "A jet black hand with an eye on its back. "
                        + "It resonates with a strange energy. Can be used to grab "
                        + "things that would normally be out of reach",
                "resources/images/SHADOWGAUNTLET.png"),
        SHIELDGENERATOR_LV1("Shield", new int[]{2500, 0, 1, 10000, 9000, -1},
                "A small device which when placed on a user, generates"
                        + " a field of energy around them, increasing the amount of"
                        + " damage they can take. This one looks as if its falling apart.",
                "resources/images/SHIELDGENERATOR_LV1.png"),
        SHIELDGENERATOR_LV2("Shield", new int[]{5000, 0, 1, 40000, 37000, -1},
                "A small device which when placed on a user, "
                        + "generates a field of energy around them, increasing "
                        + "the amount of damage they can take.",
                "resources/images/SHIELDGENERATOR_LV2.png"),
        SHIELDGENERATOR_LV3("Shield", new int[]{10000, 0, 1, 80000, 70000, -1},
                "A small device which when placed on a user, "
                        + "generates a field of energy around them, increasing the "
                        + "amount of damage they can take. This one looks very advanced.",
                "resources/images/SHIELDGENERATOR_LV3.png"),
        BALLOON_R("misc", new int[]{0, 0, 1, 50, 0, -1},
                "A red balloon, how fun",
                "resources/images/BALLOON_R.png"),
        BALLOON_G("misc", new int[]{0, 0, 1, 50, 0, -1},
                "A green balloon, how fun",
                "resources/images/BALLOON_G.png"),
        BALLOON_Y("misc", new int[]{0, 0, 1, 50, 0, -1},
                "A yellow balloon, how fun",
                "resources/images/BALLOON_Y.png"),
        BALLOON_B("misc", new int[]{0, 0, 1, 50, 0, -1},
                "A blue balloon, how fun",
                "resources/images/BALLOON_B.png"),
        ONEID("ID", new int[]{4, 0, 1, 999999, 0, 1},
                "A small gray identification card",
                "resources/images/ID.png");
        private final String type;
        private final int damage;
        private final int range;
        private final int housingSpace;
        private final int purchaseCost;
        private final int returnCost;
        private final int idLevel;
        private final String discription;
        private final String imageURL;

        //Possession(String type, int damage, int range, int housingSpace, int healthBoost,
        //int strengthBoost, int speedBoost, int purchaseCost, int returnCost,
        //boolean walkable, String imageURL) {
        Possession(String type, int damage, int range, int purchaseCost, String imageURL) {
            this(type, new int[]{damage, range, 0, purchaseCost, 0, 0}, "", imageURL);
        }

        Possession(String type, int[] stats,
                   String discription, String imageURL) {

            this.type = type;
            this.damage = stats[0];
            this.range = stats[1];
            this.housingSpace = stats[2];
            this.purchaseCost = stats[3];
            this.returnCost = stats[4];
            this.idLevel = stats[5];
            this.discription = discription;
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

    public int getBuyPrice() {
        return thing.purchaseCost;
    }

    public int getSellPrice() {
        return thing.returnCost;
    }

    public String getType() {
        return thing.type;
    }

    public int getDamage() {
        return thing.damage;
    }

    public String getDiscription() {
        return thing.discription;
    }
}