package homework11;

import java.io.File;
import java.io.FileReader;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;

//个人收支系统
public class IncomeCount extends JFrame {

    private JFileChooser fileChooser = null;
    private JLabel image = null;
    private JLabel title = null;
    private JButton loadInButton = null;
    private JFrame frame = null;
    private File file = null;
    private FileReader fileReader = null;
    private DefaultTableModel tableModel = null;
    private JTable table = null;
    private JButton insertButton = null;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new IncomeCount();
        });
    }

    public IncomeCount() {
        init();
    }

    private void init() {
        JPanel panelButton = new JPanel();
        ImageIcon img = new ImageIcon("IncomeCount\\src\\主教.jpg");
        img.setImage(img.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        image = new JLabel(img);
        title = new JLabel("income_expense manage", JLabel.CENTER);
        loadInButton = new JButton("choose load(txt)");
        panelButton.add(image);
        panelButton.add(title);
        panelButton.add(loadInButton);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JPanel panelBottom = new JPanel();
        insertButton = new JButton("插入记录");
        panelBottom.add(insertButton);
        frame = new JFrame("收支管理");
        frame.add(panelButton, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.SOUTH);
        loadInButton.addActionListener(e -> doLoadin());
        insertButton.addActionListener(e -> doInsert());
        frame.setSize(600, 500);
        frame.setLocation(400, 250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 载入函数
    private void doLoadin() {
        fileChooser = new JFileChooser("D:\\");
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                fileReader = new FileReader(file);
                Vector<String> vector = new Vector<String>();
                vector.add("ID");
                vector.add("name");
                vector.add("income");
                vector.add("expense");
                vector.add("leftover");
                Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
                Vector<String> dataVector2 = new Vector<String>();
                String string = "";
                while (fileReader.ready()) {
                    char x = (char) fileReader.read();

                    if (x == ' ') {
                        dataVector2.add(string);
                        string = "";
                    } else if (x == '\n') {
                        dataVector2.add(string);
                        string = "";
                        dataVector.add(dataVector2);
                        dataVector2 = new Vector<String>();
                    } else {
                        string = string + x;
                    }
                }
                tableModel.setDataVector(dataVector, vector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 插入函数
    private void doInsert() {
        double lastsurplus = Double.parseDouble(
                tableModel.getValueAt(tableModel.getRowCount() - 1, tableModel.getColumnCount() - 1).toString());

        ContactDialog inputDialog = new ContactDialog();
        inputDialog.uiClear();
        inputDialog.setVisible(true);
        System.out.println(inputDialog.isOkPressed());
        if (!inputDialog.isOkPressed())
            return;
        Users inputContact = inputDialog.ui2entity();
        System.out.println(inputContact);
        Vector<String> newData = new Vector<String>();
        newData.add(tableModel.getValueAt(tableModel.getRowCount() - 1, 0).toString());
        newData.add(tableModel.getValueAt(tableModel.getRowCount() - 1, 1).toString());
        newData.add(inputContact.getIncome().toString());
        newData.add(inputContact.getExpense().toString());
        newData.add(String.valueOf((lastsurplus + inputContact.getIncome() - inputContact.getExpense())));
        tableModel.addRow(newData);
    }
}

// 插入弹框
class ContactDialog extends JDialog {
    boolean PressedOK = false;

    private JLabel labelIncome = null;
    private JTextField txtIncome = null;
    private JLabel labelExpense = null;
    private JTextField txtExpense = null;
    private JPanel panelInput = null;
    private JPanel panelButtons = null;
    private JButton ButtonOK = null;
    private JButton btnCancel = null;

    public ContactDialog() {
        labelIncome = new JLabel("income");
        txtIncome = new JTextField();
        labelExpense = new JLabel("expense");
        txtExpense = new JTextField();
        panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(2, 2));
        panelInput.add(labelIncome);
        panelInput.add(txtIncome);
        panelInput.add(labelExpense);
        panelInput.add(txtExpense);
        panelButtons = new JPanel();
        ButtonOK = new JButton("Ok");
        btnCancel = new JButton("Cancel");
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.add(ButtonOK);
        panelButtons.add(btnCancel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelInput, BorderLayout.CENTER);
        getContentPane().add(panelButtons, BorderLayout.SOUTH);
        setSize(300, 150);
        this.setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ButtonOK.addActionListener(e -> {
            PressedOK = true;
            dispose();
        });
        btnCancel.addActionListener(e -> {
            PressedOK = false;
            dispose();
        });
    }

    public boolean isOkPressed() {
        return PressedOK;
    }

    public Users ui2entity() {
        Users c = new Users();
        c.setIncome(Double.parseDouble(txtIncome.getText()));
        c.setExpense(Double.parseDouble(txtExpense.getText()));
        return c;
    }

    public void uiClear() {
        txtIncome.setText("");
        txtExpense.setText("");
    }
}

// 收支情况类
class Users {
    private int id;
    private String name = null;
    private Double income = null;
    private Double expense = null;
    private Double surplus = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

}
