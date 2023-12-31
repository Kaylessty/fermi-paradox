package main;

public class ProwlerShop extends Monster {

    private static Item[] shopInv = {new Item(Item.Possession.AMMOBOX, 15, 15, "Ammo Box"),
        new Item(Item.Possession.SONICCANNON, 15, 15, "Sonic Cannon"),
        new Item(Item.Possession.SONICRIFLE, 15, 15, "Sonic Rifle"),
        new Item(Item.Possession.SONICSWORD, 15, 15, "Sonic Sword"),
        new Item(Item.Possession.DB_SONICSWORD, 15, 15, "Double Bladed Sonic Sword"),
        new Item(Item.Possession.SHOCKRIFLE, 15, 15, "Shock Rifle"),
        new Item(Item.Possession.LASER, 15, 15, "Laser"),
        new Item(Item.Possession.ENERGYSWORD, 15, 15, "Energy Sword"),
        new Item(Item.Possession.DB_ENERGYSWORD, 15, 15, "Double Bladed Energy Sword"),
        new Item(Item.Possession.PISTOL, 15, 15, "Pistol"),
        new Item(Item.Possession.H_RIFLE, 15, 15, "Hunting Rifle")
    };
    private Item drop = new Item(Item.Possession.A_RIFLE, 15, 15, "Assault Rifle");
    private int health;
    private int speed;
    private String type = "Prowler";
    private String intro = "Heya bud";
    private String talk = "Whatd'ya wanna talk about";
    private static final int ORIGINAL_HEALTH = 20000;
    public ProwlerShop() {
        this(20000, 1);
    }
    public ProwlerShop(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }
    @Override
    public String getImageURL() {
        return "resources/images/Prowler.png";
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
    public void setDrop(Item drop) {
        this.drop = drop;
    }
    @Override
    public Item getDrop() {
        return drop;
    }

    public static Item[] getShopInv() {
        return shopInv;
    }

    @Override
    public String[][] getSpeech() {
        String[][] speech = {
            {"Names Prowler, you'll be hearin it around so remember it, bud"},
            {"Hunting, these disgusting creatures have a lot of valuable materials"},
            {"No clue really, people say we're in space, but with the impossible way"
                        + " these rooms are connected... i don't know if i believe that."}
        };
        return speech;
    }

    @Override
    public String[] getTalks() {
        String[] talks = {"Who are you?", "What are you doing here?", "Where are we?"};
        return talks;
    }

    @Override
    public String getIntro() {
        return intro;
    }

    @Override
    public String getTalk() {
        return talk;
    }
}
