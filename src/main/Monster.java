package main;

import java.util.Random;

public abstract class Monster implements Locatable {

    private int x;
    private int y;
    private int health;
    private int damage;
    private Random generator = new Random();
    private boolean hasBeenAttacked;

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


    public String getImageURL() {
        return "resources/images/Teaff.png";
    }

    public boolean getHasBeenAttacked() {
        return hasBeenAttacked;
    }

    public void setHasBeenAttacked(boolean status) {
        hasBeenAttacked = status;
    }

    public abstract int getOriginalHealth();
}
