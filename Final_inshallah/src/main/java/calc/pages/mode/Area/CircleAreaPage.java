package calc.pages.mode.Area;

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
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import static calc.Calculator.ShowCalculator;
import static calc.Calculator.homefram;
import static calc.Calculator.playSound;
import static calc.pages.HistoryPage.addHistory;
import static calc.pages.HistoryPage.updateHistoryDisplay;
import static calc.tools.backtohome.backtohomee;

public class CircleAreaPage implements ActionListener {

    public static JPanel circlePanel = new JPanel();
    public static JLabel titleLabel;
    public static JLabel radiusLabel, resultLabel;
    public static JTextField radiusField;
    public static JButton calculateButton, backButton, resetButton;
    public static JButton backtoHome;

    public CircleAreaPage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // إعداد اللوحة
        circlePanel.setBounds(5, 30, 400, 450);
        circlePanel.setLayout(null);
        circlePanel.setBackground(Color.decode("#101a43"));

        // إعداد عنوان الصفحة
        titleLabel = new JLabel("Area of Circle");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 60, 250, 40); // ضبط موضع العنوان

        // إعداد مكونات الإدخال والنتيجة
        radiusLabel = new JLabel("Radius:");
        radiusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        radiusLabel.setForeground(Color.WHITE);
        radiusLabel.setBounds(50, 150, 100, 30);

        radiusField = new JTextField();
        radiusField.setFont(new Font("Arial", Font.BOLD, 20));
        radiusField.setBounds(150, 150, 300, 30);

        // تطبيق فلتر الأرقام فقط على حقل الإدخال
        ((AbstractDocument) radiusField.getDocument()).setDocumentFilter(new NumericFilter());

        // نقل التركيز إلى زر "Calculate" عند الضغط على Enter
        radiusField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateButton.doClick();
                }
            }
        });

        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 20));
        calculateButton.setBounds(150, 220, 200, 50);
        calculateButton.addActionListener(this);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 24));
        calculateButton.setFocusable(false);

        resultLabel = new JLabel("Area:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBounds(50, 300, 300, 30);

        // زر العودة
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(10, 10, 100, 40);
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        // زر إعادة التعيين
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setBounds(300, 330, 100, 30);
        resetButton.setVisible(false);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        // إضافة المكونات إلى اللوحة
        circlePanel.add(titleLabel); // إضافة عنوان الصفحة
        circlePanel.add(radiusLabel);
        circlePanel.add(radiusField);
        circlePanel.add(calculateButton);
        circlePanel.add(resultLabel);
        circlePanel.add(backButton);
        circlePanel.add(resetButton);
        circlePanel.add(backtoHome);
    }

    public static void showCircleAreaPage() {
        backtoHome.setBounds(300, 3, 100, 50);
        homefram.setSize(460, 450);
        circlePanel.setVisible(true);
        homefram.add(circlePanel);
        homefram.revalidate();
        homefram.repaint();
    }

    public static void hideCircleAreaPage() {
        circlePanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            // حساب مساحة الدائرة
            try {
                double radius = Double.parseDouble(radiusField.getText());
                if (radius <= 0) {
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resultLabel.setText("Please enter a positive number!");
                    resetButton.setVisible(true);
                } else {
                    double area = Math.PI * radius * radius;
                    resultLabel.setText("Circle Area: " + String.format("%.2f", area));
                    resetButton.setVisible(true);
                    playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
                }
            } catch (NumberFormatException ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Please enter numbers!");
            }
            addHistory(resultLabel.getText());
            updateHistoryDisplay();
        } else if (e.getSource() == backtoHome) {
            radiusField.setText("");
            resultLabel.setText("Area:");
            resetButton.setVisible(false);
            hideCircleAreaPage();
            ShowCalculator();
        } else if (e.getSource() == backButton) {
            hideCircleAreaPage();
            AreaPage.showAreaPage();
        } else if (e.getSource() == resetButton) {
            radiusField.setText("");
            resultLabel.setText("Area:");
            resetButton.setVisible(false);
        }
    }

    // DocumentFilter مخصص للسماح بإدخال الأرقام فقط
    // Custom DocumentFilter to allow numeric input, including decimal points
    class NumericFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }
            if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), string, offset)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), text, offset)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        // Method to validate if the input contains only digits and at most one decimal point
        private boolean isValidInput(String currentText, String string, int offset) {
            StringBuilder newText = new StringBuilder(currentText);
            newText.insert(offset, string);

            // Check if the text is a valid decimal number
            return newText.toString().matches("\\d*\\.?\\d*");
        }
    }
}
