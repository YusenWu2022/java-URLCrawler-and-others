package test;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MoveItem extends JFrame implements KeyListener {
    private int x, y;
    private int r;

    // 默认构造函数
    public MoveItem() {
        x = 300;
        y = 200;
        r = 50;
        this.setSize(600, 400);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // KeyListener方法
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); // 获取按键码
        switch (key) {
            case KeyEvent.VK_UP: // 向上
                y -= 10;
                break;
            case KeyEvent.VK_DOWN: // 向下
                y += 10;
                break;
            case KeyEvent.VK_LEFT: // 向左
                x -= 10;
                break;
            case KeyEvent.VK_RIGHT: // 向右
                x += 10;
                break;
        }
        // 绘制图形
        Graphics g = this.getGraphics();
        g.clearRect(0, 0, 600, 400);
        g.fillOval(x - r, y - r, 2 * r, r * 2);
    }

    // main方法
    public static void main(String args[]) {
        new MoveItem();
    }
}
