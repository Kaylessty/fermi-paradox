package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/*
This acts as a data class for the player of the game.
 */
public class Player implements Locatable {
    private static IntegerProperty health = new SimpleIntegerProperty();
    private static String name;
    private static IntegerProperty balance = new SimpleIntegerProperty();
    private static int strength;
    private static int speed;
    private int x;
    private int y;
    private String recentImageURL = "resources/images/player-down.png";


    public static String getName() {
        return name;
    }

    public static IntegerProperty getHealth() {
        return health;
    }

    public static IntegerProperty getBalance() {
        return balance;
    }

    public static int getStrength() {
        return strength;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setBalance(int balance1) {
        balance.set(balance1);
    }

    public static void setHealth(int health1) {
        health.set(health1);
    }

    public static void setName(String name1) {
        name = name1;
    }

    public static void setSpeed(int speed1) {
        speed = speed1;
    }

    public static void setStrength(int strength1) {
        strength = strength1;
    }

    public int[] getLocation() {
        return new int[] {x, y};
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getImageURL() {
        return recentImageURL;
    }

    public String getUpImageURL() {
        recentImageURL = "resources/images/player-up.png";
        return recentImageURL;
    }

    public String getDownImageURL() {
        recentImageURL = "resources/images/player-down.png";
        return recentImageURL;
    }

    public String getRightImageURL() {
        recentImageURL = "resources/images/player-right.png";
        return recentImageURL;
    }

    public String getLeftImageURL() {
        recentImageURL = "resources/images/player-left.png";
        return recentImageURL;
    }
}
