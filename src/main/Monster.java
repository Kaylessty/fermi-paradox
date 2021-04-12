package main;

import java.util.Random;

public abstract class Monster implements Locatable {
    private Item drop = new Item(Item.Possession.BALLOON_R, 15, 15, "Monster Balloon");
    private int x;
    private int y;
    private int health;
    private int damage;
    private Random generator = new Random();
    private boolean hasBeenAttacked;
    private String type;
    private String[][] speech;
    private String[] talks;
    private String intro;
    private String talk;
    private Boolean Boss = false;

    public Monster() {
        x = 3 + generator.nextInt(14);
        y = 3 + generator.nextInt(14);
        health = 2800;
        damage = 1000;
        hasBeenAttacked = false;
    }

    public abstract void damage(int amount, MathHelper.Direction knockback);

    @Override
    public int[] getLocation() {
        return new int[] {x, y};
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getImageURL() {
        return "resources/images/Eyebore.png";
    }

    public boolean getHasBeenAttacked() {
        return hasBeenAttacked;
    }

    public void setHasBeenAttacked(boolean status) {
        hasBeenAttacked = status;
    }

    public abstract int getOriginalHealth();

    public String getType() {
        return type;
    }

    public void setDrop(Item drop) {
        this.drop = drop;
    }

    public Item getDrop() {
        return drop;
    }

    public String[][] getSpeech() {
        return speech;
    }

    public String[] getTalks() {
        return talks;
    }

    public String getIntro() {
        return intro;
    }

    public String getTalk() {
        return talk;
    }

    public Boolean getBoss() {
        return Boss;
    }
}
