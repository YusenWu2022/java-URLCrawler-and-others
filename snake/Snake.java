package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Snake extends JFrame implements KeyListener, ActionListener, MouseListener {

    int slong = 2;
    int[] Snakex = new int[100];
    int[] Snakey = new int[100];
    int fx = 1;
    Timer timer = new Timer(100, this);
    int foodx;
    int foody;
    Random random = new Random();
    int started = 0;

    public void myJFrame() {

        this.setTitle("hungry snake");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(Snake.EXIT_ON_CLOSE);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width - 800) / 2, (height - 600) / 2);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setVisible(true);
        Snakex[0] = 60;
        Snakey[0] = 100;
        Snakex[1] = 40;
        Snakey[1] = 100;
        foodx = random.nextInt(39);
        foody = random.nextInt(22);
        foodx = foodx * 20;
        foody = foody * 20 + 80;
        System.out.println(foodx + "，" + foody);
    }

    public void paint(Graphics g) {

        g.setColor(Color.gray);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.black);
        g.fillRect(0, 80, 800, 520);
        for (int i = 0; i < slong; i++) {
            g.setColor(Color.BLUE);
            g.fillRect(Snakex[i], Snakey[i], 20, 20);
        }
        g.setColor(Color.RED);
        g.fillOval(foodx, foody, 20, 20);
        if (started == 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("微软雅黑", 10, 20));
            g.drawString("press space button to begin", 300, 65);
        } else if (started == 1) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("微软雅黑", 10, 20));
            g.drawString("current score:", 300, 65);
            g.drawString(String.valueOf(slong - 2), 420, 65);
        } else if (started == 2) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("微软雅黑", 10, 20));
            g.drawString("game over-", 250, 65);
            g.drawString("final score:", 350, 65);
            g.drawString(String.valueOf(slong - 2), 470, 65);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (started == 1) {
            for (int i = slong - 1; i > 0; i--) {
                Snakex[i] = Snakex[i - 1];
                Snakey[i] = Snakey[i - 1];
            }
            if (fx == 0) {
                Snakex[0] = Snakex[0] - 20;
            } else if (fx == 1) {
                Snakex[0] = Snakex[0] + 20;
            } else if (fx == 2) {
                Snakey[0] = Snakey[0] - 20;
            } else if (fx == 3) {
                Snakey[0] = Snakey[0] + 20;
            }
            if (Snakex[0] < 0 || Snakex[0] > 780 || Snakey[0] < 80 || Snakey[0] > 580) {
                started = 2;
            }
            if (Snakex[0] == foodx && Snakey[0] == foody) {

                slong++;

                foodx = random.nextInt(39);
                foody = random.nextInt(22);
                foodx = foodx * 20;
                foody = foody * 20 + 80;

                System.out.println(foodx + "，" + foody);
            }
            for (int i = 1; i < slong; i++) {
                if (Snakex[0] == Snakex[i] && Snakey[0] == Snakey[i]) {
                    started = 2;
                }
            }
            for (int i = 0; i < slong; i++) {
                if (foodx == Snakex[i] && foody == Snakey[i]) {

                    foodx = random.nextInt(39);
                    foody = random.nextInt(22);
                    foodx = foodx * 20;
                    foody = foody * 20 + 80;

                    System.out.println(foodx + "，" + foody);
                }
            }
            repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            if (started == 0) {
                started = 1;
            } else if (started == 1) {
                started = 0;
            } else if (started == 2) {
                started = 0;
                slong = 2;
                Snakex[0] = 60;
                Snakey[0] = 100;
                Snakex[1] = 40;
                Snakey[1] = 100;
                foodx = random.nextInt(39);
                foody = random.nextInt(22);
                foodx = foodx * 20;
                foody = foody * 20 + 80;
                fx = 1;
            }
            repaint();
            timer.start();
        } else if (key == KeyEvent.VK_LEFT) {
            if (fx != 1) {
                fx = 0;
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (fx != 0) {
                fx = 1;
            }
        } else if (key == KeyEvent.VK_UP) {
            if (fx != 3) {
                fx = 2;
            }
        } else if (key == KeyEvent.VK_DOWN) {
            if (fx != 2) {
                fx = 3;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
