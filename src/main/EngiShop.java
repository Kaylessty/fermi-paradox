package main;

public class EngiShop extends Monster {

    private static Item[] shopInv = {new Item(Item.Possession.SHIELDGENERATOR_LV1, 15, 15, "Shield Generator LV1"),
            new Item(Item.Possession.SHIELDGENERATOR_LV2, 15, 15, "Shield Generator LV2"),
            new Item(Item.Possession.SHIELDGENERATOR_LV3, 15, 15, "Shield Generator LV3"),
            new Item(Item.Possession.BATTERYLV1, 15, 15, "Regeneron Battery LV1"),
            new Item(Item.Possession.BATTERYLV2, 15, 15, "Regeneron Battery LV2"),
            new Item(Item.Possession.BATTERYLV3, 15, 15, "Regeneron Battery LV3"),
            new Item(Item.Possession.BATTERYLV4, 15, 15, "Regeneron Battery LV4"),
            new Item(Item.Possession.G_BATTERYLV1, 15, 15, "Gun Battery LV1"),
            new Item(Item.Possession.G_BATTERYLV2, 15, 15, "Gun Battery LV2"),
            new Item(Item.Possession.G_BATTERYLV3, 15, 15, "Gun Battery LV3"),
            new Item(Item.Possession.G_BATTERYLV4, 15, 15, "Gun Battery LV4")};
    private Item drop = new Item(Item.Possession.SHIELDGENERATOR_LV3, 15, 15, "Shield Generator LV3");
    private int health;
    private int speed;
    private String type = "Engi";
    private String intro = "Heya! nice ta see you again!";
    private String talk = "Ask away!";
    private static final int ORIGINAL_HEALTH = 5000;
    public EngiShop() {
        this(5000,1);
    }
    public EngiShop(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }
    @Override
    public String getImageURL() {
        return "resources/images/Engi.png";
    }

    @Override
    public void damage(int amount, MathHelper.Direction knockback) {
        this.health -= amount;
        int currX = super.getLocation()[0];
        int currY = super.getLocation()[1];
        super.setLocation(currX + knockback.dirX * 90, currY + knockback.dirY * 90);
    }

    @Override
    public int getOriginalHealth() {
        return ORIGINAL_HEALTH;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setDrop(Item drop) {
        this.drop = drop;
    }
    @Override
    public Item getDrop() {
        return drop;
    }

    public static Item[] getShopInv() {
        return shopInv;
    }

    @Override
    public String[][] getSpeech() {
        String[][] speech = {{"I'm Engi! I was hired to work aboard the station as a cybernetics specialist before " +
                "this whole mess. Something really strange is going on.. and i don't  just mean the aliens! have you" +
                " noticed that the rooms here don't seem to be connected in a way that's physically possible? perhaps" +
                " its some sort of worm hole technology... or maybe we are in a place which has entirely different " +
                "laws of physics!! OH OH ALSO... wait.. sorry i'm rambling again! "},
                {"I'm trying to find a determinable pattern to the station, map it out... see which rooms lead were." +
                        " So far a map of this station would be impossible. I'm on the verge of a breakthrough here!" +
                        " interesting right?"}, {"Scientific experimentation often contains many risks! Risks that " +
                "sometimes result in getting a sweet robot arm!"}};
        return speech;
    }

    @Override
    public String[] getTalks() {
        String[] talks = {"Who are you?","What are you doing here?","How did you lose your arm?"};
        return talks;
    }

    @Override
    public String getIntro() {
        return intro;
    }

    @Override
    public String getTalk() {
        return talk;
    }
}
