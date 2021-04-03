package main;

public class TreeBore extends Monster {

    private int health;
    private int speed;
    private String type = "Tree Bore";
    private static final int ORIGINAL_HEALTH = 999999999;
    private Boolean boss = true;
    public TreeBore() {
        this(999999999,1);
    }
    public TreeBore(int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.setDrop(new Item(Item.Possession.BOREHEART, 15, 15, "Bore Heart"));
    }
    @Override
    public String getImageURL() {
        return "resources/images/Tree_Bore.png";
    }

    @Override
    public void damage(int amount, MathHelper.Direction knockback) {
        this.health -= amount;
        int currX = super.getLocation()[0];
        int currY = super.getLocation()[1];
        super.setLocation(currX + knockback.dirX * 90, currY + knockback.dirY * 90);
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
