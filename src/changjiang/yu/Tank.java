package changjiang.yu;

import java.util.Vector;

public class Tank {

    // == fields ==
    protected int x = 0;
    protected int y = 0;

    // Tank's direction 0 - up, 1 - down, 2 - left, 3 - right
    protected int direction = 0;

    // Tank's speed
    protected int speed = 5;

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
    public void moveUp() {
        if(y > 0){
            y -= getSpeed();
        }
    }

    //tank moves down
    public void moveDown() {
        if(y < 240){
            y += getSpeed();
        }
    }

    //tank moves left
    public void moveLeft() {
        if(x > 0) {
            x -= getSpeed();
        }
    }

    //tank moves right
    public void moveRight() {
        if(x < 360){
            x += getSpeed();
        }
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    //tank fire bullet
    public void fireBullet() {

        Bullet bullet = null;

        switch (this.direction) {
            case 0:
                //UP
                bullet = new Bullet(x + 10, y, this.direction);
                break;
            case 1:
                //DOWN
                bullet = new Bullet(x + 10, y + 20, this.direction);
                break;
            case 2:
                //LEFT
                bullet = new Bullet(x + 10, y + 10, this.direction);
                break;
            case 3:
                //right
                bullet = new Bullet(x + 30, y + 10, this.direction);
                break;
        }
        bullets.add(bullet);

        // Run bullet Thread
        Thread threadBullet = new Thread(bullet);
        threadBullet.start();
    }
}

class EnemyTank extends Tank implements Runnable {
    boolean isLive = true;
    Vector<Bullet> bullets = new Vector<Bullet>();

    public EnemyTank(int x, int y) {
        super(x, y);
        this.setColor(1);
        this.setDirection(1);
    }

    @Override
    public boolean isLive() {
        return isLive;
    }

    @Override
    public void setLive(boolean live) {
        isLive = live;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    //AI of enemy Tank
    @Override
    public void run() {
        while (true) {

            //Random move
            switch (this.direction) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (y > 0) {
                            y -= getSpeed();
                        }
                        try {
                            Thread.sleep(80);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (y < 260) {
                            y += getSpeed();
                        }

                        try {
                            Thread.sleep(80);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (x > 0) {
                            x -= getSpeed();
                        }

                        try {
                            Thread.sleep(80);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (x < 340) {
                            x += getSpeed();
                        }

                        try {
                            Thread.sleep(80);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }


            if (this.isLive()) {
                if(this.bullets.size()<1){
                    // no bullet, add one bullet
                    Bullet bullet = null;
                    switch (this.direction){
                        case 0:
                            //UP
                            bullet = new Bullet(this.getX() + 10, this.getY(), this.getDirection());
                            this.getBullets().add(bullet);
                            break;
                        case 1:
                            //DOWN
                            bullet = new Bullet(this.getX() + 10, this.getY() + 20, this.getDirection());
                            this.getBullets().add(bullet);
                            break;
                        case 2:
                            //LEFT
                            bullet = new Bullet(this.getX() + 10, this.getY() + 10, this.getDirection());
                            this.getBullets().add(bullet);
                            break;
                        case 3:
                            //right
                            bullet = new Bullet(this.getX() + 30, this.getY() + 10, this.getDirection());
                            this.getBullets().add(bullet);
                            break;
                    }

                    Thread enemyBulletThread = new Thread(bullet);
                    enemyBulletThread.start();
                }
            }else{
                break;
            }

            this.direction = (int) (Math.random() * 4);
        }
    }
}