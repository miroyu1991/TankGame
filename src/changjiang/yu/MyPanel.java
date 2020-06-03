package changjiang.yu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {

    // == fields ==

    //my tank
    HeroTank myTank = null;

    //enemy tanks
    int enemyNumber = 3;
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();

    //bomb
    Vector<Bomb> bombs = new Vector<Bomb>();

    int enSize = 3;

    // 3 pictures of tank booming
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public MyPanel() {
        //initialize tanks
        myTank = new HeroTank(200,150);

        for(int i = 0; i < enemyNumber; i++){
            EnemyTank enemyTank = new EnemyTank((i + 1) * 100, 0);
            enemyTanks.add(enemyTank);
        }

        //initialize pictures for tank booming
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }

    public void paint (Graphics g){

        super.paint(g);
        g.fillRect(0,0,400,300);

        //draw my tank
        this.drawTank(this.myTank.getX(),this.myTank.getY(),g,this.myTank.getDirection(),this.myTank.getColor());

        for(int i = 0; i < this.myTank.getBullets().size(); i++) {

            Bullet bullet = this.myTank.getBullets().get(i);
            //draw my tank's bullet
            if (bullet != null && bullet.isLive()) {
                g.drawOval(bullet.getX(), bullet.getY(), 2, 2);
            }else if(!bullet.isLive()){
                this.myTank.getBullets().remove(bullet);
            }
        }

        //draw bomb
        for(int i = 0; i < bombs.size(); i++){
            Bomb bomb = bombs.get(i);

            if(bomb.getLife()>6){
                g.drawImage(image1,bomb.getX(),bomb.getY(),30,30,this);
            }else if(bomb.getLife()>3){
                g.drawImage(image2,bomb.getX(),bomb.getY(),30,30,this);
            }else{
                g.drawImage(image3,bomb.getX(),bomb.getY(),30,30,this);
            }

            bomb.lifeDown();
            if(bomb.isLive()){
                bombs.remove(bomb);
            }

        }

        //draw enemy tanks
        for(int i = 0; i < enemyTanks.size(); i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isLive()){
                this.drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirection(),enemyTank.getColor());
            }
        }

    }

    public void drawTank(int x, int y, Graphics g, int direct, int type){

        // Decide draw which type of Tank. 0 --> My tank, 1 --> Enemy Tank
        switch (type){
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.YELLOW);
                break;
            default:
                g.setColor(Color.RED);
        }

        // Decide which direction of this tank
        switch (direct){

            case 0:
                //up
                g.fill3DRect(x,y,5,30,false);
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,15,false);
                g.fillOval(x+5,y+7,8,10);
                g.drawLine(x+10,y,x+10,y+15);
                break;
            case 1:
                //down
                g.fill3DRect(x,y,5,30,false);
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,15,false);
                g.fillOval(x+5,y+7,8,10);
                g.drawLine(x+10,y+15,x+10,y+30);
                break;
            case 2:
                //left
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x,y+15,30,5,false);
                g.fill3DRect(x+5,y+5,20,10,false);
                g.fillOval(x+7,y+5,8,10);
                g.drawLine(x+15,y+10,x,y+10);
                break;
            case 3:
                //right
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x,y+15,30,5,false);
                g.fill3DRect(x+5,y+5,20,10,false);
                g.fillOval(x+7,y+5,8,10);
                g.drawLine(x+15,y+10,x + 30,y+10);
                break;
        }
    }


    // judge if bullet hit tank
    public void hitTank(Bullet bullet, EnemyTank enemyTank){
        // tank direction
        switch (enemyTank.getDirection()){
            // UP OR DOWN
            case 0:
            case 1:
                if(bullet.getX()>=enemyTank.getX()&&bullet.getX()<=enemyTank.getX()+20
                        && bullet.getY()>=enemyTank.getY()&&bullet.getY()<=enemyTank.getY()+20){
                    bullet.setLive(false);
                    enemyTank.setLive(false);
                }
                break;
            case 2:
            case 3:
                if(bullet.getX()>=enemyTank.getX()&&bullet.getX()<=enemyTank.getX()+30
                        && bullet.getY()>=enemyTank.getY()&&bullet.getY()<=enemyTank.getY()+15){
                    bullet.setLive(false);
                    enemyTank.setLive(false);
                }
        }

        //bomb happen
        Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
        bombs.add(bomb);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        //set my tanks direction
        if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W){
            this.myTank.setDirection(0);                //up
            this.myTank.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S){
            this.myTank.setDirection(1);                     //down
            this.myTank.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A){
            this.myTank.setDirection(2);                     //left
            this.myTank.moveLeft();
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D){
            this.myTank.setDirection(3);                     //right
            this.myTank.moveRight();
        }

        // J --> Fire bullet, exist most 5 bullets
        if(e.getKeyCode()==KeyEvent.VK_J){
            if(this.myTank.getBullets().size() < 5) {
                this.myTank.fireBullet();
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }

            // judge if bullet hits tank
            for(int i = 0; i < this.myTank.getBullets().size(); i++) {
                Bullet bullet = this.myTank.getBullets().get(i);
                //if bullet is still live
                if (bullet.isLive()){
                    for(int j = 0; j < this.enemyTanks.size(); j++){
                        EnemyTank enemyTank = enemyTanks.get(j);

                        if (enemyTank.isLive()){
                            this.hitTank(bullet, enemyTank);
                        }
                    }
                }
            }

            // repaint the panel
            this.repaint();
        }

    }
}