package changjiang.yu;

import java.util.Vector;

public class Tank{

    // == fields ==
    protected int x = 0;
    protected int y = 0;

    // Tank's direction 0 - up, 1 - down, 2 - left, 3 - right
    protected int direction = 0;

    // Tank's speed
    protected int speed = 3;

    // Tank's color
    protected int color;

    // Tank's live situation
    protected boolean isLive = true;

    // == constructors ==
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // == public methods ==
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}

class HeroTank extends Tank {
    // == fields ==
    Vector<Bullet> bullets = new Vector<Bullet>();

    // == constructors ==
    public HeroTank(int x, int y) {
        super(x, y);
        this.setColor(0);
    }

    // == public methods

    //tank moves up
    public void moveUp(){
        y -= getSpeed();
    }

    //tank moves down
    public void moveDown(){
        y += getSpeed();
    }

    //tank moves left
    public void moveLeft(){
        x -= getSpeed();
    }

    //tank moves right
    public void moveRight(){
        x += getSpeed();
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    //tank fire bullet
    public void fireBullet(){

        Bullet bullet = null;

        switch (this.direction){
            case 0:
                //UP
                bullet = new Bullet(x + 10,y,this.direction);
                break;
            case 1:
                //DOWN
                bullet = new Bullet(x + 10,y + 20,this.direction);
                break;
            case 2:
                //LEFT
                bullet = new Bullet(x+10,y+10,this.direction);
                break;
            case 3:
                //right
                bullet = new Bullet(x+30,y+10,this.direction);
                break;
        }
        bullets.add(bullet);

        // Run bullet Thread
        Thread threadBullet = new Thread(bullet);
        threadBullet.start();
    }
}

class EnemyTank extends Tank{
    public EnemyTank(int x, int y) {
        super(x, y);
        this.setColor(1);
        this.setDirection(1);
    }
}