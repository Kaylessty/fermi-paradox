package main;

public class Teeth extends Monster {

    private int health;
    private int speed;
    private String type = "Teeth";
    private static final int ORIGINAL_HEALTH = 999999999;
    private Boolean boss = true;
    public Teeth() {
        this(999999999, 1);
    }
    public Teeth(int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.setDrop(new Item(Item.Possession.TOOTHBRUSH, 15, 15, "Tooth Brush"));
    }
    @Override
    public String getImageURL() {
        return "resources/images/Teeth.png";
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
}
