package changjiang.yu;

import javax.swing.*;

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




