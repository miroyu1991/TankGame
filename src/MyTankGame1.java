import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyTankGame1 extends JFrame{

    MyPanel myPanel = null;
    public static void main(String[] args) {
        MyTankGame1 myTankGame1 = new MyTankGame1();
    }

    public MyTankGame1() {
        myPanel = new MyPanel();

        this.add(myPanel);
        this.addKeyListener(myPanel);

        this.setSize(400,300);
        this.setVisible(true);

    }
}

class MyPanel extends JPanel implements KeyListener {

    HeroTank myTank = null;

    public MyPanel() {
        myTank = new HeroTank(10,10);
    }

    public void paint (Graphics g){

        super.paint(g);
        g.fillRect(0,0,400,300);

        //draw my tank
        this.drawTank(myTank.getX(),myTank.getY(),g,0,1);

    }

    public void drawTank(int x, int y, Graphics g, int direct, int type){

        // Decide draw which type of Tank. 0 --> My tank; 1 --> Enemy Tank
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
            //up
            case 0:
                g.fill3DRect(x,y,5,30,false);
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,15,false);
                g.fillOval(x+5,y+7,8,10);
                g.drawLine(x+10,y,x+10,y+15);
                break;
        }

        // Decide which direction of this tank
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        //
        if(e.getKeyCode()==KeyEvent.VK_UP){
            myTank.setY(myTank.getY() - 1);
            this.repaint();
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            myTank.setY(myTank.getY() + 1);
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

class Tank{

    // == fields ==
    int x = 0;
    int y = 0;

    // Tank's direction 0 - up, 1 - down, 2 - left, 3 - right
    int direction = 0;

    // == constructors ==
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
}

class HeroTank extends Tank{

    public HeroTank(int x, int y) {
        super(x, y);
    }
}