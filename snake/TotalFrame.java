package snake;

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

//
//多线程和帧率设置完成！！只需要进一步完善游戏主循环和操作循环就可以实现很多事情了，甚至加个网络接口可以联机
//这里面与游戏相关
@SuppressWarnings("serial")
class CreateCanvas extends JPanel {
    class Point {
        public int x, y;

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
        File sourceimage = new File("snake/a.jpg");
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
                // repaint();
            }

            public void mouseMoved(MouseEvent e) { // 鼠标移动
                mode = 2; // 设置为移动模式
                x = e.getX();
                y = e.getY();
                // repaint();
            }
        });
        // Mycanvas = new mycanvas();
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                mode = 3;
                x = e.getX();
                y = e.getY();
                arr.add(new Point(x, y));
                // repaint();
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

    // repaint 调用这个，间接调用draw
    // 原来如此！！好耶，不是repaint的问题，而是自己重写的paintComponent里面mode美更新的问题！之前写的鼠标检测移动会导致没有draw
    // 事实证明依次print几次还是很好用的。
    // 下一步可以开始添加鼠标符号和游戏算法了!还有拆包重组！
    public void paintComponent(Graphics g) {
        if (mode == 1)
            g.setFont(new Font("宋体", Font.BOLD, g.getFont().getSize() + 10));
        if (mode == 3)
            g.setFont(new Font("宋体", Font.BOLD, g.getFont().getSize() + 30));
        if (mode == 3) {
        }
        g.clearRect(0, 0, 400, 600); // 清屏
        draw(g, x, y);
        // System.out.println(-1);
    }

    // 根据游戏内容更新这一帧内容的变化
    public void update() {
        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).x += 1;
        }

    }

    // 有函数调用repaint时候自动调用draw
    public void draw(Graphics g, int x, int y) {
        // g.drawString("Hello, Java世界", x, y);
        System.out.println(0);
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

class GamePanel extends Panel {
    /* ... */
    public void gameStart() { // 该方法用来启动计时器
        java.util.Timer t = new java.util.Timer(); // 建立计时器对象
        java.util.Timer t1 = new java.util.Timer();
        myTimerTask game = new myTimerTask(); // 建立计时任务对象
        myTimerTask game2 = new myTimerTask(1, game);
        // myTimerTask game3 = new myTimerTask(2);
        t.scheduleAtFixedRate(game, 0, 1000 / 50); // 设定帧速率并启动计时任务
        t.scheduleAtFixedRate(game2, 0, 1000 / 50);
        // t.scheduleAtFixedRate(game3, 0, 1000);
    }

    class myTimerTask extends TimerTask {// 建立内部类，继承TimerTask类
        TotalFrame world;
        int x = 1;
        int mode;
        myTimerTask g;

        myTimerTask() {
            super();
            world = new TotalFrame();
            world.setName("Test2两个按钮实现同一个监听");
            world.setVisible(true);
            world.setLocation(100, 100);
            world.setSize(800, 800);
            world.getContentPane().setBackground(new Color(50, 125, 240));
            mode = 1;
        }

        myTimerTask(int x) {
            this.x = x;
            mode = 3;
        }

        myTimerTask(int x, myTimerTask g) {
            this.x = x;
            this.g = g;
            mode = 2;
        }

        // timerTask里面不能更新UI项目，必须在UI线程里面！
        // repaint调用paintComponent
        public void run() { // 重载run()方法，将游戏循环放置其中
            if (mode == 1) {
                world.warField.update();// 所有相关环境对象斗争update里面更新
                world.warField.repaint();
                System.out.println(1);
            } else if (mode == 2)
            // System.out.println(g.world.warField.arr.size());
            {
                g.world.warField.update();
                world.warField.repaint();
                System.out.println(2);
            } else
                System.out.println(x);

        }
    }
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
        GamePanel gp = new GamePanel();
        gp.gameStart();
        // world.warField.repaint();
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