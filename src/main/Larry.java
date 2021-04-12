package main;

import java.util.Random;

public class Larry extends Monster {
    private Item[] drop = new Item[2];
    private int health;
    private int speed;
    private String type = "Larry";
    private static final int ORIGINAL_HEALTH = 999999999;
    private Boolean boss = true;
    private Random rNum = new Random();
    public Larry() {
        this(999999999, 1);
    }
    public Larry(int health, int speed) {
        this.health = health;
        this.speed = speed;
        drop[0] = new Item(Item.Possession.SHADOWSWORD, 15, 15, "Shadow Sword");
        drop[1] = new Item(Item.Possession.SHADOWGAUNTLET, 15, 15, "Shadow Gauntlet");
    }
    @Override
    public String getImageURL() {
        return "resources/images/Larry.png";
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

    @Override
    public Boolean getBoss() {
        return boss;
    }

    @Override
    public Item getDrop() {
        return drop[rNum.nextInt(2)];
    }
}
