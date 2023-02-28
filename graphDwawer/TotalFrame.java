package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

@SuppressWarnings("serial")
class CreateCanvas extends JPanel {
    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int x = 20, y = 20; // 设置初试坐标
    int mode = 1; // 表示默认绘制模式,1:拖动，2：移动
    Image img;
    // mycanvas Mycanvas;
    public ArrayList<Point> arr = new ArrayList<Point>();
    /*
     * class mycanvas extends Canvas {// 内部类
     * 
     * public void paint(Graphics g, int x, int y) { Image image = new
     * ImageIcon("sailboat.jpg").getImage();// 获取图片资源 g.drawImage(image, x, y,
     * this);// 绘制图像
     * 
     * } }
     */

    CreateCanvas() {
        super();
        this.setOpaque(true);
        this.setBackground(Color.red);
        setSize(300, 200);
        arr.add(new Point(1, 1));
        File sourceimage = new File("test/a.jpg");
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
                arr.add(new Point(x, y));
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
            g.clearRect(0, 0, 400, 600); // 清屏
            draw(g, x, y);
        }
    }

    public void draw(Graphics g, int x, int y) {
        // g.drawString("Hello, Java世界", x, y);
        setBackground(Color.blue);
        for (int i = 0; i < arr.size(); i++) {
            g.drawImage(img, arr.get(i).x, arr.get(i).y, 20, 20, this);
        }
        if (mode == 3)
            g.drawImage(img, x, y, 40, 40, this);
        // for(int i=1;i<=)
        // Mycanvas.paint(g, x, y);
    }

    public Dimension getPreferredSize() { // 获取最佳尺寸
        return new Dimension(400, 600);
    }
    /*
     * public void paintBorder(Graphics g) { setBackground(Color.blue);
     * g.setColor(Color.WHITE); g.drawOval(150, 100, 70, 50); g.setColor(Color.RED);
     * g.drawString("Java is the best", 100, 180); g.drawString("veibae", 0, 100); }
     */
    /*
     * public void paint(Graphics g) { g.setColor(Color.WHITE); g.drawOval(150, 100,
     * 70, 50); g.setColor(Color.RED); g.drawString("Java is the best", 100, 180); }
     */
}

@SuppressWarnings("serial")
public class TotalFrame extends JFrame {
    static CreateCanvas warField;

    public TotalFrame() {
        setLayout(null); // 设置为网格布局，未指定行数
        setFont(new Font("Helvetica", Font.PLAIN, 14));
        JButton north = new JButton("north");
        JButton south = new JButton("south");
        JButton zerg = new JButton("zerg");
        JButton north1 = new JButton("north1");
        warField = new CreateCanvas();
        getContentPane().add(warField);
        getContentPane().add(north);
        getContentPane().add(south);
        getContentPane().add(zerg);
        getContentPane().add(north1);
        north.setBounds(600, 60, 100, 60);
        south.setBounds(600, 140, 100, 60);
        zerg.setBounds(600, 220, 100, 60);
        north1.setBounds(600, 300, 100, 60);
        warField.setBounds(20, 40, 500, 400);
        north.addActionListener(new MyMonitor());
        south.addActionListener(new MyMonitor());
        zerg.addActionListener(new MyMonitor());
    }

    // Icon ic=new ImageIcon("load.jpg"); JLabel lb=new JLabel(ic); JPanel p=new
    // JPanel(); p.add(lb);
    public static void main(String[] args) {
        TotalFrame world = new TotalFrame();
        world.setName("Test2两个按钮实现同一个监听");
        world.setVisible(true);
        world.setLocation(100, 100);
        world.setSize(800, 800);
        world.getContentPane().setBackground(new Color(50, 125, 240));
        world.warField.repaint();
        // System.exit(0);
    }

    private static class MyMonitor implements ActionListener {
        // build the ActionLister for the north button and the south button ,named
        // myActionListener

        @Override
        public void actionPerformed(ActionEvent e) {
            // 输入 e. 查看源码.
            if (e.getActionCommand() == "north") {
                warField.arr.add(warField.new Point(100, 100));
                System.out.println("north Button been clicked ，and MyMonitor class run successfully.");
            } else if (e.getActionCommand() == "south") {
                warField.arr.remove(warField.arr.size() - 1);
                // warField.arr.remove(warField.new Point(100, 100));
                System.out.println("south Button been clicked ，and MyMonitor class run successfully.");
            } else if (e.getActionCommand() == "zerg") {
                System.exit(0);
            }
        }
    }

}
// 准备设置多线程完成两个循环