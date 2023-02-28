package test;

import java.awt.event.*;
import javax.swing.*;

public class Console {
    // Creating a title String from the class name
    public static String title(Object o) {
        String t = o.getClass().toString();
        if (t.indexOf("class") != -1)
            t = t.substring(6);
        return t;
    }

    public static void run(JFrame frame, int width, int heigth) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, heigth);
        frame.setVisible(true);
    }

    public static void run(JApplet applet, int width, int height) {
        JFrame frame = new JFrame(title(applet));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(applet);
        frame.setSize(width, height);
        frame.setLocation(150, 250);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }

    public static void run(JPanel panel, int width, int height) {
        JFrame frame = new JFrame(title(panel));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}