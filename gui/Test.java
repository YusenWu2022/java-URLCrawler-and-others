package gui;

import java.awt.*;
import java.awt.geom.*;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
    public Test() {
        Frame f = new Frame("my app");
        MyPanel mp = new MyPanel();
        Thread t = new Thread(mp);
        t.start();
        f.setLocation(300, 200);
        f.setSize(300, 300);
        f.add(mp);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new Test();
    }
}

class MyPanel extends Panel implements Runnable {
    private int x;
    private int y;
    private int diameter;
    Image im = null;

    public MyPanel() {
        x = 50;
        y = 50;
        diameter = 100;
    }

    public void game() {
        System.out.println("hello");
    }

    public void gameUpdate() {
        x++;
    }

    public void gamePaint() {
        Graphics g = this.getGraphics();
        g.drawImage(im, 0, 0, null);
        g.dispose();
    }

    public void gameRender() {
        im = createImage(getWidth(), getHeight());
        Graphics dbg = im.getGraphics();
        dbg.setColor(Color.BLUE);
        dbg.fillOval(x, y, diameter, diameter);
    }

    @Override
    public void run() {
        final int FPS = 50;
        long t1, t2, dt, sleepTime;

        long period = 1000 / FPS;

        t1 = System.nanoTime();

        while (true) {
            gameUpdate();
            gameRender();
            gamePaint();
            t2 = System.nanoTime();
            dt = (t2 - t1) / 1000000L;
            sleepTime = period - dt;
            if (sleepTime <= 0)
                sleepTime = 2;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            t1 = System.nanoTime();
        }
    }

    public void gameStart() {
        Timer t = new Timer();
        MyTimerTask game = new MyTimerTask();
        t.scheduleAtFixedRate(game, 0, 1000 / 50);
    }

    class MyTimerTask extends TimerTask {
        public void run() {
            while (true) {
                gameUpdate();
                gameRender();
                gamePaint();
            }
        }
    }
}
