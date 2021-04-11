package main;

public class Monster3 extends Monster {

    private int health;
    private int speed;
    private String type = "Tim";
    private static final int ORIGINAL_HEALTH = 999999999;

    /**
     * default constructor
     */
    public Monster3() {
        this(999999999, 1);
    }

    /**
     * 2-args constructor
     * @param health int value to be assigned to health
     * @param speed int value to be assigned to speed
     */
    public Monster3(int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.setDrop(new Item(Item.Possession.TIMKEY, 15, 15, "Tim's Key"));
    }
    @Override
    public String getImageURL() {
        return "resources/images/Tim.png";
    }

    @Override
    public void damage(int amount, MathHelper.Direction knockback) {
        this.health -= amount;
        int currX = super.getLocation()[0];
        int currY = super.getLocation()[1];
        super.setLocation(currX + knockback.getDirX() * 90, currY + knockback.getDirY() * 90);
    }

    @Override
    public int getOriginalHealth() {
        return ORIGINAL_HEALTH;
    }

    @Override
    public String getType() {
        return type;
    }
}