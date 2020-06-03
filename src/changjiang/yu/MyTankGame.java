package changjiang.yu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyTankGame extends JFrame{

    MyPanel myPanel = null;
    public static void main(String[] args) {
        MyTankGame myTankGame1 = new MyTankGame();
    }

    public MyTankGame() {

        myPanel = new MyPanel();

        // Register listeners
        this.add(myPanel);
        this.addKeyListener(myPanel);

        this.setSize(400,300);
        this.setVisible(true);

        //run thread of my panel
        Thread threadMyPanel = new Thread(myPanel);
        threadMyPanel.start();

    }
}

class MyPanel extends JPanel implements KeyListener, Runnable {

    // == fields ==

    //my tank
    HeroTank myTank = null;

    //enemy tanks
    int enemyNumber = 3;
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();


    public MyPanel() {
        //initialize tanks
        myTank = new HeroTank(200,150);

        for(int i = 0; i < enemyNumber; i++){
            EnemyTank enemyTank = new EnemyTank((i + 1) * 100, 0);
            enemyTanks.add(enemyTank);
        }


    }

    public void paint (Graphics g){

        super.paint(g);
        g.fillRect(0,0,400,300);

        //draw my tank
        this.drawTank(myTank.getX(),myTank.getY(),g,myTank.getDirection(),myTank.getColor());

        //draw my tank's bullet
        if(this.myTank.getBullet() != null && this.myTank.getBullet().isLive() == true){
            g.drawOval(myTank.getBullet().getX(),myTank.getBullet().getY(),2,2);
        }

        //draw enemy tanks
        for(int i = 0; i < enemyTanks.size(); i++){
            this.drawTank(enemyTanks.get(i).getX(),enemyTanks.get(i).getY(),g,enemyTanks.get(i).getDirection(),enemyTanks.get(i).getColor());
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

        // Decide which direction of this tank
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

        // J --> Fire
        if(e.getKeyCode()==KeyEvent.VK_J){
            this.myTank.fireBullet();
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

            this.repaint();
        }

    }
}


