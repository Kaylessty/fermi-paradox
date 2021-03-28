package main;

public class Monster3 extends Monster{
    private int health;
    private int speed;
    public Monster3() {
        this(1000,3);
    }
    public Monster3(int health, int speed) {
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
