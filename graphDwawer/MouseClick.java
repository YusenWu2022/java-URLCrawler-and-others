package test;

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

@SuppressWarnings("serial")
public class MouseClick extends JPanel {
    int x = 20, y = 20; // 设置初试坐标
    int mode = 1; // 表示默认绘制模式,1:拖动，2：移动
    Image img;
    // mycanvas Mycanvas;

    /*
     * class mycanvas extends Canvas {// 内部类
     * 
     * public void paint(Graphics g, int x, int y) { Image image = new
     * ImageIcon("sailboat.jpg").getImage();// 获取图片资源 g.drawImage(image, x, y,
     * this);// 绘制图像
     * 
     * } }
     */
    public MouseClick() {

        File sourceimage = new File("SC2_simplify/chartlet/hydralisk.jpg");
        // source.gif图片要与HelloJava.java同在一目录下
        try {
            img = ImageIO.read(sourceimage);
        } catch (IOException e) {
        }

        // Image image = new ImageIcon("src/ycy.jpg").getImage();
        addMouseMotionListener(new MouseMotionListener() {// 创建匿名内部类
            public void mouseDragged(MouseEvent e) { // 鼠标拖动
                mode = 1;
                x = e.getX(); // x轴的坐标
                y = e.getY(); // y轴的坐标
                repaint();
            }

            public void mouseMoved(MouseEvent e) { // 鼠标移动
                mode = 2; // 设置为移动模式
                x = e.getX();
                y = e.getY();
                repaint();
            }
        });
        // Mycanvas = new mycanvas();
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                mode = 3;
                x = e.getX();
                y = e.getY();
                repaint();
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }
        });
    }

    public void paintComponent(Graphics g) {
        if (mode == 1)
            g.setFont(new Font("宋体", Font.BOLD, g.getFont().getSize() + 10));
        if (mode == 3)
            g.setFont(new Font("宋体", Font.BOLD, g.getFont().getSize() + 30));
        if (mode == 3) {
            g.clearRect(0, 0, 400, 200); // 清屏
            draw(g, x, y);
        }
    }

    public void draw(Graphics g, int x, int y) {
        // g.drawString("Hello, Java世界", x, y);
        if (mode == 3)
            g.drawImage(img, x, y, 20, 20, this);
        // Mycanvas.paint(g, x, y);
    }

    public Dimension getPreferredSize() { // 获取最佳尺寸
        return new Dimension(400, 200);
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.add(new MouseClick());
        frame.setTitle("鼠标移动事件示例");
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
