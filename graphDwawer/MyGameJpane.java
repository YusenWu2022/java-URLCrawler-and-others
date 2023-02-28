package test;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * 基于swing.JPanel创建的游戏画布框架 用来绘制整个游戏的画布。
 *
 * 
 * 
 * @author J_sky
 * 
 * @version 1.0.0 2015-11-06 bosichong@qq.com
 * 
 */

public class MyGameJpane extends JPanel {
    /**
     * 
     * 构造器 初始化画板
     *
     * 
     * 
     */

    public MyGameJpane() {
        new PaintThread().start();// 启动线程刷新。

    }

    /**
     * 
     * 定义一个重绘窗口的线程类，是一个内部类。 通过线程中的循环来控制动画的间隔时间。
     * 
     */

    class PaintThread extends Thread {
        @Override

        public void run() {
            while (true) {
                try {
                    repaint();// 重绘画布

                    Thread.sleep(30);// 间隔时间

                } catch (InterruptedException ex) {
                    ex.printStackTrace();

                }

            }

        }

    }

    /**
     * 
     * 绘图方法，继承后可以重写这个方法来绘制场景及游戏元素，就可以不用。 我们制作游戏动画绘制场景主要是利用这个方法来实现。
     * 
     * 可以避免过于重写继承来的方法。也为后继加入双缓冲做准备。
     *
     * 
     * 
     * @param src
     * 
     */

    public void draw(Graphics g) {
    }

    /**
     * 
     * JPanel 双缓冲绘图的方法是paintComponent() 我们重写实现绘图。
     *
     * 
     * 
     * @param g
     * 
     */

    @Override

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // To change body of generated methods, choose Tools | Templates.\

        draw(g);

    }

    /**
     * 
     * 重写update()也是双缓冲的必须。
     *
     * 
     * 
     * @param g
     * 
     */

    @Override

    public void update(Graphics g) {
        super.update(g); // To change body of generated methods, choose Tools | Templates.

        paintComponent(g);// 双缓冲绘制

    }

}