package main;

public class ClownShop extends Monster {

    private static Item[] shopInv = {new Item(Item.Possession.BALLOON_R, 15, 15, "Red Balloon"),
            new Item(Item.Possession.BALLOON_Y, 15, 15, "Yellow Balloon"),
            new Item(Item.Possession.BALLOON_B, 15, 15, "Blue Balloon"),
            new Item(Item.Possession.BALLOON_G, 15, 15, "Green Balloon"),
            new Item(Item.Possession.HORN, 15, 15, "Clown Horn")};
    private Item drop = new Item(Item.Possession.HORN, 15, 15, "Clown's Prized Horn");
    private int health;
    private int speed;
    private String type = "Howard";
    private String intro = "Howdy kid, need anything";
    private String talk = "Whatcha wanna talk about";
    private static final int ORIGINAL_HEALTH = 5000;
    public ClownShop() {
        this(5000,1);
    }
    public ClownShop(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }
    @Override
    public String getImageURL() {
        return "resources/images/Howard.png";
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
        String[][] speech = {{"Names Howard, i'm the stations resident funny man. Not sure why the boys back home" +
                " thought it necessary to higher a clown... but hay, a job's a job's... or... job"},
                {"Working... these alien things don't seem to attack unless provoked so... my contract says if" +
                        " I'm not in immediate danger... well, i'd rather deal with these fellas " +
                        "than deal with boss man."},{"Kid... did ya bump yer head er something? We're in space, " +
                "same as always... although... i can't really recall how we got here..."}};
        return speech;
    }

    @Override
    public String[] getTalks() {
        String[] talks = {"Who are you?","What are you doing here?","Where are we?"};
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
