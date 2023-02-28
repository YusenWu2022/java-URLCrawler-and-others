package test;

import java.awt.*;
import java.awt.event.*;

public class layoutnull extends Frame {
    Panel pnl;
    Button button1, button2, button3, button4;

    layoutnull() {
        super("Frame with null Layout");
        setSize(300, 250);

        setVisible(true);
        setLayout(null);// 创建一个null布局
        pnl = new Panel(); // 创建面板pn1
        pnl.setBackground(Color.blue);
        add(pnl); // 添加面板
        button1 = new Button("按钮1");// 创建按钮button1
        button2 = new Button("按钮2");
        button3 = new Button("按钮3");
        button4 = new Button("按钮4");
        add(button1); // 添加按钮button1
        add(button2);
        add(button3);
        add(button4);
        pnl.setBounds(20, 40, 200, 190); // 面板pn1的左上角坐标为(20,40),宽为200像素，高为190像素
        button1.setBounds(230, 60, 50, 30); // 按钮button1的左上角坐标为(230,60),宽为50像素，高为30像素
        button2.setBounds(230, 100, 50, 30);
        button3.setBounds(230, 140, 50, 30);
        button4.setBounds(230, 180, 50, 30);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
    }

    public static void main(String args[]) {
        layoutnull frm = new layoutnull();// 创建一个layoutnull的布局对象frm
    }
}
