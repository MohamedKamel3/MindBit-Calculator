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

public class CirclePage implements ActionListener {

    public static JPanel CirclePanel = new JPanel();
    public static JLabel CircleLabel;
    public static JButton BackButton;
    static JTextField radiusField;
    static JButton calculateButton;
    static JLabel resultLabel;
    public static JButton clearButton;
    public static JButton backtoHome;

    public CirclePage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // إعداد واجهة الدائرة
        CirclePanel.setBounds(5, 60, 450, 400);
        CirclePanel.setLayout(null);
        CirclePanel.setBackground(Color.decode("#101a43"));

        CircleLabel = new JLabel("Circle Perimeter");
        CircleLabel.setBounds(135, 23, 250, 40);
        CircleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        CircleLabel.setForeground(Color.WHITE);

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

        // حقل إدخال نصف القطر
        radiusField = new JTextField();
        radiusField.setBounds(150, 100, 150, 30);
        radiusField.setFont(new Font("Arial", Font.BOLD, 22));
        addNumericOnlyListener(radiusField);

        JLabel radiusLabel = new JLabel("Radius:");
        radiusLabel.setBounds(80, 100, 100, 30);
        radiusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        radiusLabel.setForeground(Color.WHITE);

        // زر الحساب
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 150, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFocusable(false);

        // مكان عرض النتيجة
        resultLabel = new JLabel("");
        resultLabel.setBounds(90, 200, 300, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);

        CirclePanel.add(CircleLabel);
        CirclePanel.add(BackButton);
        CirclePanel.add(radiusField);
        CirclePanel.add(calculateButton);
        CirclePanel.add(resultLabel);
        CirclePanel.add(radiusLabel);
        CirclePanel.add(clearButton);
        CirclePanel.add(backtoHome);

    }

    private void addNumericOnlyListener(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = field.getText();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();  // تجاهل الإدخال إذا لم يكن رقميًا
                } else if (c == '.' && text.contains(".")) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculatePerimeter();  // Calculate when Enter is pressed
                }
            }
        });
    }

    private void calculatePerimeter() {
        try {
            double radius = Double.parseDouble(radiusField.getText());
            if (radius > 0) {
                double perimeter = 2 * Math.PI * radius;
                resultLabel.setText("Perimeter: " + perimeter);
                clearButton.setVisible(true);
                playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
            } else {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Radius should be positive!");
                clearButton.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
            resultLabel.setText("Invalid input!");
        }
    }

    public static void showCirclePage() {
        homefram.setSize(450, 450);
        backtoHome.setBounds(10, 300, 100, 50);
        CirclePanel.setVisible(true);
        homefram.add(CirclePanel);
    }

    public static void resetCirclePage() {
        CirclePanel.setVisible(false);
    }

    private void clearFields() {
        radiusField.setText("");
        resultLabel.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            resetCirclePage();
            Perimeterpage.showperpage();  // الرجوع إلى الصفحة الرئيسية
        } else if (e.getSource() == clearButton) {
            clearFields(); // Clear fields
        } else if (e.getSource() == calculateButton) {
            calculatePerimeter();  // Calculate when the button is clicked
        }
        if (e.getSource() == backtoHome) {
            resetCirclePage();
            ShowCalculator();
        }

    }
}
