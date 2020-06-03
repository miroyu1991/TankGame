package changjiang.yu;

public class Bullet implements Runnable{
    // == fields ==
    protected int x;
    protected int y;
    protected int direction;
    protected int speed = 5;
    protected boolean isLive = true;

    // ==  constructors ==
    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    // public methods
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while(true){

            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }

            switch (this.direction){
                case 0:
                    //UP
                    y -= this.speed;
                    break;
                case 1:
                    //DOWN
                    y += this.speed;
                    break;
                case 2:
                    //LEFT
                    x -= this.speed;
                    break;
                case 3:
                    //right
                    x += this.speed;
                    break;
            }

            // bullet out of screen
            if(this.x < 0 || this.x > 400 || this.y < 0 || this.y > 300){
                this.isLive = false;
                break;
            }
        }


    }
}
