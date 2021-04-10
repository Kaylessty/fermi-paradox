package main;

import java.util.Random;

/**
 * An abstract class representing monsters
 */
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

    /**
     * default Monster construtor, setting default values
     */
    public Monster() {
        x = 3 + generator.nextInt(14);
        y = 3 + generator.nextInt(14);
        health = 2800;
        damage = 1000;
        hasBeenAttacked = false;
    }

    /**
     * Abstract method which will decrease the monster's health
     * @param amount int value of decrease
     * @param knockback Direction that the Monster was knockbacked to
     */
    public abstract void damage(int amount, MathHelper.Direction knockback);

    @Override
    public int[] getLocation() {
        return new int[] {x, y};
    }

    /**
     * getter for the health instance var
     * @return int value of health
     */
    public int getHealth() {
        return health;
    }

    /**
     * setter for the health instance var
     * @param health int value for health to be set to
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * setter for the location instance var
     * @param x int x value
     * @param y int y value
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for the damage instanve var
     * @return int value of damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * setter for the damage instance var
     * @param damage int new value of damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * getter for the imageURL instance var
     * @return String url for Eyebore image
     */
    public String getImageURL() {
        return "resources/images/Eyebore.png";
    }

    /**
     * getter for hasBeenAttacked instance var
     * @return boolean val of hasBeenAttacked
     */
    public boolean getHasBeenAttacked() {
        return hasBeenAttacked;
    }

    /**
     * setter for hasBeenAttacked instance var
     * @param status boolean new val of hasBeenAttacked
     */
    public void setHasBeenAttacked(boolean status) {
        hasBeenAttacked = status;
    }

    /**
     * abstract getter for the original health of the monster
     * @return int value of the original health of the monster
     */
    public abstract int getOriginalHealth();

    /**
     * getter for the type of monster
     * @return String type
     */
    public String getType() {
        return type;
    }

    /**
     * setter for the drop instance variable
     * @param drop Item for drop to be set to
     */
    public void setDrop(Item drop) {
        this.drop = drop;
    }

    /**
     * getter for the drop instance variable
     * @return Item value of drop
     */
    public Item getDrop() {
        return drop;
    }

    /**
     * getter for the speech instance var
     * @return the String[][] value of speech
     */
    public String[][] getSpeech() {
        return speech;
    }

    /**
     * getter for the talks instance var
     * @return the String[] value of talks
     */
    public String[] getTalks() {
        return talks;
    }

    /**
     * getter for the intro instance var
     * @return String value of intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * getter for the talk instance var
     * @return String value of talk
     */
    public String getTalk() {
        return talk;
    }
}