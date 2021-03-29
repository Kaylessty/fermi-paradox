package main;

public class Monster1 extends Monster {

    private int health;
    private int speed;
    private static final int ORIGINAL_HEALTH = 2800;
    public Monster1() {
        this(2800,1);
    }
    public Monster1(int health, int speed) {
        this.health = health;
        this.speed = speed;
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
}
