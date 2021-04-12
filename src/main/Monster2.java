package main;

public class Monster2 extends Monster {

    private int health;
    private int speed;
    private String type = "Teaff";
    private static final int ORIGINAL_HEALTH = 3200;
    public Monster2() {
        this(3200, 5);
    }
    public Monster2(int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.setDrop(new Item(Item.Possession.HIDE, 15, 15, "Teaff Hide"));
    }
    @Override
    public String getImageURL() {
        return "resources/images/Teaff.png";
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