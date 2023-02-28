package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MouseMotionText extends JPanel {
    int x = 20, y = 20; // 设置初试坐标
    int mode = 1; // 表示默认绘制模式,1:拖动，2：移动

    public MouseMotionText() {
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
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 400, 200); // 清屏
        if (mode == 1)
            g.setFont(new Font("宋体", Font.BOLD, g.getFont().getSize() + 10));
        draw(g, x, y);

    }

    public void draw(Graphics g, int x, int y) {
        g.drawString("Hello, Java世界", x, y);
    }

    public Dimension getPreferredSize() { // 获取最佳尺寸
        return new Dimension(400, 200);
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.add(new MouseMotionText());
        frame.setTitle("鼠标移动事件示例");
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
