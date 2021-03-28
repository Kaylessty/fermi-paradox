package main;

public class Monster2 extends Monster{
    private int health;
    private int speed;
    public Monster2() {
        this(400,2);
    }
    public Monster2(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    @Override
    public void damage(int amount, MathHelper.Direction knockback) {
        this.hp -= amount;
        super.x += knockback.dirX * 90;
        super.y += knockback.dirY * 90;
    }
}
