package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
    private static IntegerProperty health;
    private static String name;
    private static IntegerProperty balance;
    private static int strength;
    private static int speed;


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
        balance = new SimpleIntegerProperty(balance1);
    }

    public static void setHealth(int health1) {
        health = new SimpleIntegerProperty(health1);
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
}
