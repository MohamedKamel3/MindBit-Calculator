package calc.pages.mode.Perimeter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static calc.Calculator.ShowCalculator;
import static calc.Calculator.homefram;
import static calc.Calculator.playSound;
import static calc.constants.PbuttonColor;
import static calc.tools.backtohome.backtohomee;

public class SquarePage implements ActionListener {

    public static JPanel SquarePanel = new JPanel();
    public static JLabel SquareLabel;
    public static JButton BackButton;
    public static JTextField sideField;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton clearButton;
    public static JButton backtoHome;

    public SquarePage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // إعداد واجهة المربع
        SquarePanel.setBounds(5, 60, 450, 400);
        SquarePanel.setLayout(null);
        SquarePanel.setBackground(Color.decode("#101a43"));

        SquareLabel = new JLabel("Square Perimeter");
        SquareLabel.setBounds(135, 23, 250, 40);
        SquareLabel.setFont(new Font("Arial", Font.BOLD, 30));
        SquareLabel.setForeground(Color.WHITE);

        BackButton = new JButton("Back");
        BackButton.setFont(new Font("Arial", Font.BOLD, 20));
        BackButton.setForeground(Color.white);
        BackButton.setContentAreaFilled(false);
        BackButton.setBounds(10, 13, 100, 50);
        BackButton.setOpaque(false);
        BackButton.setBorder(null);
        BackButton.addActionListener(this);
        BackButton.setBorderPainted(false);
        BackButton.setFocusable(false);

        // CLEAR button
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setForeground(PbuttonColor);
        clearButton.setBounds(300, 300, 100, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setOpaque(false);
        clearButton.setVisible(false);
        clearButton.setFocusable(false);

        // حقل إدخال طول الضلع مع مستمع للتحقق من الأرقام فقط وتنفيذ الحساب عند الضغط على Enter
        sideField = new JTextField();
        sideField.setBounds(150, 100, 150, 30);
        sideField.setFont(new Font("Arial", Font.BOLD, 22));
        sideField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();  // تجاهل الإدخال إذا لم يكن رقميًا
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculatePerimeter();  // حساب المحيط عند الضغط على Enter
                }
            }
        });

        JLabel sideLabel = new JLabel("Side:");
        sideLabel.setBounds(80, 100, 100, 30);
        sideLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sideLabel.setForeground(Color.WHITE);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 150, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFocusable(false);

        resultLabel = new JLabel("");
        resultLabel.setBounds(150, 200, 200, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.WHITE);

        SquarePanel.add(SquareLabel);
        SquarePanel.add(BackButton);
        SquarePanel.add(sideField);
        SquarePanel.add(calculateButton);
        SquarePanel.add(resultLabel);
        SquarePanel.add(sideLabel);
        SquarePanel.add(clearButton);
        SquarePanel.add(backtoHome);

    }

    public static void showSquarePage() {
        homefram.setSize(450, 450);
        backtoHome.setBounds(10, 285, 100, 50);
        SquarePanel.setVisible(true);
        homefram.add(SquarePanel);
    }

    public static void resetSquarePage() {
        SquarePanel.setVisible(false);

    }

    private void clearFields() {
        sideField.setText("");
        resultLabel.setText("");
    }

    private void calculatePerimeter() {
        try {
            double side = Double.parseDouble(sideField.getText());
            if (side > 0) {
                double perimeter = 4 * side;
                resultLabel.setText("Perimeter: " + perimeter);
                clearButton.setVisible(true);
                playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
            } else {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Invalid input!");
                clearButton.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
            resultLabel.setText("Invalid input!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            resetSquarePage();
            Perimeterpage.showperpage();  // الرجوع إلى الصفحة الرئيسية
        } else if (e.getSource() == clearButton) {
            clearFields();  // مسح الحقول
        } else if (e.getSource() == calculateButton) {
            calculatePerimeter();  // حساب المحيط عند الضغط على زر "Calculate"
        }
        if (e.getSource() == backtoHome) {
            resetSquarePage();
            ShowCalculator();
        }
    }
}
