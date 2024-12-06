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

public class TrianglePage implements ActionListener {

    public static JPanel TrianglePanel = new JPanel();
    public static JLabel TriangleLabel;
    public static JButton BackButton;
    public static JTextField side1Field;
    public static JTextField side2Field;
    public static JTextField side3Field;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton clearButton;
    public static JButton backtoHome;

    public TrianglePage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // إعداد واجهة المثلث
        TrianglePanel.setBounds(5, 60, 450, 400);
        TrianglePanel.setLayout(null);
        TrianglePanel.setBackground(Color.decode("#101a43"));

        TriangleLabel = new JLabel("Triangle Perimeter");
        TriangleLabel.setBounds(120, 23, 250, 40);
        TriangleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        TriangleLabel.setForeground(Color.WHITE);

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
        clearButton.setBounds(330, 330, 100, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setOpaque(false);
        clearButton.setVisible(false);
        clearButton.setFocusable(false);

        // إعداد الحقول لقبول الأرقام فقط والتنقل باستخدام Enter
        side1Field = createNumericField(false);
        side1Field.setBounds(150, 100, 150, 30);

        JLabel side1Label = new JLabel("Side 1:");
        side1Label.setBounds(80, 100, 100, 30);
        side1Label.setFont(new Font("Arial", Font.BOLD, 16));
        side1Label.setForeground(Color.WHITE);

        side2Field = createNumericField(false);
        side2Field.setBounds(150, 150, 150, 30);

        JLabel side2Label = new JLabel("Side 2:");
        side2Label.setBounds(80, 150, 100, 30);
        side2Label.setFont(new Font("Arial", Font.BOLD, 16));
        side2Label.setForeground(Color.WHITE);

        side3Field = createNumericField(true);
        side3Field.setBounds(150, 200, 150, 30);

        JLabel side3Label = new JLabel("Side 3:");
        side3Label.setBounds(80, 200, 100, 30);
        side3Label.setFont(new Font("Arial", Font.BOLD, 16));
        side3Label.setForeground(Color.WHITE);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 250, 150, 30);
        calculateButton.addActionListener(this);

        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 300, 350, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(Color.WHITE);

        TrianglePanel.add(TriangleLabel);
        TrianglePanel.add(BackButton);
        TrianglePanel.add(side1Field);
        TrianglePanel.add(side2Field);
        TrianglePanel.add(side3Field);
        TrianglePanel.add(calculateButton);
        TrianglePanel.add(resultLabel);
        TrianglePanel.add(side1Label);
        TrianglePanel.add(side2Label);
        TrianglePanel.add(side3Label);
        TrianglePanel.add(clearButton);
        TrianglePanel.add(backtoHome);
    }

    private JTextField createNumericField(boolean isLastField) {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.BOLD, 22));
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();  // تجاهل الإدخال إذا لم يكن رقميًا
                } else if (c == '.' && field.getText().contains(".")) {
                    e.consume();  // تجاهل الإدخال إذا كان هناك بالفعل نقطة في النص
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (isLastField) {
                        calculateButton.doClick();  // تنفيذ عملية الحساب عندما يكون الحقل الأخير
                    } else {
                        field.transferFocus();  // الانتقال للحقل التالي إذا لم يكن الأخير
                    }
                }
            }
        });
        return field;
    }

    public static void showTrianglePage() {
        homefram.setSize(450, 500);
        backtoHome.setBounds(10, 330, 100, 50);
        TrianglePanel.setVisible(true);
        homefram.add(TrianglePanel);
    }

    public static void resetTrianglePage() {
        TrianglePanel.setVisible(false);
    }

    private void clearFields() {
        side1Field.setText("");
        side2Field.setText("");
        side3Field.setText("");
        resultLabel.setText("");
    }

    private boolean isValidTriangle(double side1, double side2, double side3) {
        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            resetTrianglePage();
            Perimeterpage.showperpage();  // الرجوع إلى الصفحة الرئيسية
        } else if (e.getSource() == clearButton) {
            clearFields();  // مسح الحقول
        } else if (e.getSource() == backtoHome) {
            resetTrianglePage();
            ShowCalculator();
        } else if (e.getSource() == calculateButton) {
            try {
                double side1 = Double.parseDouble(side1Field.getText());
                double side2 = Double.parseDouble(side2Field.getText());
                double side3 = Double.parseDouble(side3Field.getText());

                if (side1 > 0 && side2 > 0 && side3 > 0 && isValidTriangle(side1, side2, side3)) {
                    double perimeter = side1 + side2 + side3;
                    resultLabel.setText("Perimeter: " + perimeter);
                    playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
                } else {
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resultLabel.setText("Invalid triangle sides!");
                }
                clearButton.setVisible(true);
            } catch (NumberFormatException ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Invalid input!");
                clearButton.setVisible(true);
            }
        }
    }
}
