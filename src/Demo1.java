import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Demo1 extends JFrame {

    DemoPanel demoPanel =null;
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
    }

    public Demo1(){
        demoPanel = new DemoPanel();

        this.add(demoPanel);
        this.addKeyListener(demoPanel);

        this.setSize(400,300);
        this.setVisible(true);
    }
}


class DemoPanel extends JPanel implements KeyListener{

    int x = 10;
    int y = 10;
    public void paint(Graphics g){
        super.paint(g);

        g.fillOval(x,y,10,10);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            System.out.println("down");
            y++;
            this.repaint();
        }else if (e.getKeyCode()==KeyEvent.VK_UP){
            System.out.println("up");
            y--;
            this.repaint();
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            System.out.println("left");
            x--;
            this.repaint();
        }else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            System.out.println("right");
            x++;
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

