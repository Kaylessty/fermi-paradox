package main;

/**
 * This class represents a Monster named EyeBore
 */
public class Monster1 extends Monster {
    private int health;
    private int speed;
    private String type = "Eyebore";
    private static final int ORIGINAL_HEALTH = 2800;

    /**
     * default constructor
     */
    public Monster1() {
        this(2800, 2);
    }

    /**
     * 2-args constructor
     * @param health int value for health
     * @param speed int value for speed
     */
    public Monster1(int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.setDrop(new Item(Item.Possession.BOREEYE, 15, 15, "Bore Eye"));
    }
    @Override
    public String getImageURL() {
        return "resources/images/Eyebore.png";
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
