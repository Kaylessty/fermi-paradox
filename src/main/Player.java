package main;

public class Player {
    static int health;
    static String name;
    static int balance;
    static int strength;
    static int speed;


    public static String getName() {
        return name;
    }

    public static int getHealth() {
        return health;
    }

    public static int getBalance() {
        return balance;
    }

    public static int getStrength() {
        return strength;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setBalance(int balance1) {
        balance = balance1;
    }

    public static void setHealth(int health1) {
        health = health1;
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
