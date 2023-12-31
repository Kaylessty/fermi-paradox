package main;

public class Challenge extends Monster {
    private int health;
    private int speed;
    private String type = "Challenge";
    private static final int ORIGINAL_HEALTH = 1;
    public Challenge() {
        this(1, 2);
    }
    public Challenge(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }
    @Override
    public String getImageURL() {
        return "resources/images/C_ORB.png";
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