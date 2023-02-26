import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//继承窗体
public class myBMI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JLabel sex = new JLabel("Sex", JLabel.CENTER);
    JLabel height = new JLabel("Height(m)", JLabel.CENTER);
    JLabel weight = new JLabel("Weight(kg)", JLabel.CENTER);
    JLabel bmi = new JLabel("BMI=Weight/Height^2", JLabel.CENTER);
    JTextField sex1 = new JTextField("boy");
    JTextField height1 = new JTextField("0");
    JTextField weight1 = new JTextField("0");
    JTextField bmi1 = new JTextField("N/A");
    JTextField feedback = new JTextField("waiting...");
    JButton reset = new JButton("Reset");
    JButton calculate = new JButton("Calculate");

    public myBMI() {
        super("BMI计算器");
        //设置布局
        getContentPane().setLayout(null);
        //添加组件
        sex.setBounds(0, 80, 200, 80);
        height.setBounds(0, 160, 200, 80);
        weight.setBounds(0, 240, 200, 80);
        sex1.setBounds(200, 80, 200, 80);
        height1.setBounds(200, 160, 200, 80);
        weight1.setBounds(200, 240, 200, 80);
        reset.setBounds(400, 0, 100, 250);
        calculate.setBounds(400, 250, 100, 250);
        bmi.setBounds(0, 320, 200, 80);
        bmi1.setBounds(200, 320, 200, 80);
        feedback.setBounds(0, 400, 400, 100);
        this.add(sex);
        this.add(sex1);
        this.add(height);
        this.add(height1);
        this.add(weight);
        this.add(weight1);
        this.add(bmi);
        this.add(bmi1);
        this.add(feedback);
        this.add(reset);
        this.add(calculate);

        reset.addActionListener(this);
        calculate.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    //事件处理
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == reset)
            handleReset();
        else if (source == calculate)
            handleCal();
        else
            System.out.println("Error");
    }

    public void handleReset() {
        sex1.setText("man");
        height1.setText("0");
        weight1.setText("0");
        bmi1.setText("0");
        feedback.setText("waiting...");
    }
    //计算并反馈
    public void handleCal() {
        String str_sex = sex1.getText();
        double height_val = Double.parseDouble(height1.getText());
        double weight_val = Double.parseDouble(weight1.getText());
        double bmi_val = weight_val / (height_val * height_val);
        bmi1.setText(String.format("%.4f", bmi_val));
        if (bmi_val < 18.5)
            feedback.setText("体重过轻，注意饮食");
        else if (bmi_val > 24)
            feedback.setText("太重啦，别吃力");
        else {
            if (str_sex.contains("man"))
                feedback.setText("事bmi正常的男性");
            else
                feedback.setText("事bmi正常的女性");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new myBMI();
        });
    }
}