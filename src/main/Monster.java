package main;

import java.util.Random;

public class Monster implements Locatable {

    private int x;
    private int y;
    private int health;
    private Random generator = new Random();

    public Monster() {
        x = 3 + generator.nextInt(14);
        y = 3 + generator.nextInt(14);
        health = 2800;
    }

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

    public String getImageURL() {
        return "resources/images/kappa.jpg";
    }
}
