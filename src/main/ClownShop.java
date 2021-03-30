package main;

public class ClownShop extends Monster {

    private Item[] shopInv = {new Item(Item.Possession.BALLOON_R, 15, 15, "Red Balloon"),
            new Item(Item.Possession.BALLOON_Y, 15, 15, "Yellow Balloon"),
            new Item(Item.Possession.BALLOON_B, 15, 15, "Blue Balloon"),
            new Item(Item.Possession.BALLOON_G, 15, 15, "Green Balloon"),
            new Item(Item.Possession.HORN, 15, 15, "Clown Horn")};
    private Item drop = new Item(Item.Possession.HORN, 15, 15, "Clown's Prized Horn");
    private int health;
    private int speed;
    private String type = "Howard";
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
}
