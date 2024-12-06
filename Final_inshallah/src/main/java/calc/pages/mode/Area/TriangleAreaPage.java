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

public class TriangleAreaPage implements ActionListener {

    public static JPanel TrianglePanel = new JPanel();
    public static JLabel TriangleLabel;
    public static JButton BackButton;
    public static JTextField baseField;
    public static JTextField heightField;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton resetButton;
    public static JButton backtoHome;

    public TriangleAreaPage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);
        // إعداد واجهة المثلث
        TrianglePanel.setBounds(5, 60, 450, 400);
        TrianglePanel.setLayout(null);
        TrianglePanel.setBackground(Color.decode("#101a43"));

        TriangleLabel = new JLabel("Area of triangle");
        TriangleLabel.setBounds(150, 23, 250, 40);
        TriangleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        TriangleLabel.setForeground(Color.WHITE);

        BackButton = new JButton("BACK");
        BackButton.setFont(new Font("Arial", Font.BOLD, 20));
        BackButton.setForeground(Color.white);
        BackButton.setContentAreaFilled(false);
        BackButton.setBounds(10, 13, 100, 50);
        BackButton.setOpaque(false);
        BackButton.setBorder(null);
        BackButton.addActionListener(this);
        BackButton.setFocusable(false);

        // حقول الإدخال للقاعدة والارتفاع
        baseField = new JTextField();
        baseField.setBounds(150, 100, 200, 30);
        baseField.setFont(new Font("Arial", Font.BOLD, 19));

        heightField = new JTextField();
        heightField.setBounds(150, 150, 200, 30);
        heightField.setFont(new Font("Arial", Font.BOLD, 19));

        ((AbstractDocument) baseField.getDocument()).setDocumentFilter(new NumericFilter());  // تطبيق فلتر الأرقام
        ((AbstractDocument) heightField.getDocument()).setDocumentFilter(new NumericFilter());  // تطبيق فلتر الأرقام

        // نقل التركيز عند الضغط على Enter
        baseField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    heightField.requestFocus();
                }
            }
        });

        heightField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateButton.doClick();
                }
            }
        });

        JLabel baseLabel = new JLabel("Base:");
        baseLabel.setBounds(80, 100, 100, 30);
        baseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        baseLabel.setForeground(Color.WHITE);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setBounds(80, 150, 100, 30);
        heightLabel.setFont(new Font("Arial", Font.BOLD, 16));
        heightLabel.setForeground(Color.WHITE);

        // زر حساب المساحة
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 200, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 24));
        calculateButton.setFocusable(false);

        // عرض النتيجة
        resultLabel = new JLabel("");
        resultLabel.setBounds(80, 250, 300, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);

        // زر إعادة التعيين
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setBounds(300, 300, 100, 30);
        resetButton.setVisible(false); // الزر يظهر فقط بعد الضغط على "Calculate"
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        TrianglePanel.add(TriangleLabel);
        TrianglePanel.add(BackButton);
        TrianglePanel.add(baseField);
        TrianglePanel.add(heightField);
        TrianglePanel.add(calculateButton);
        TrianglePanel.add(resultLabel);
        TrianglePanel.add(baseLabel);
        TrianglePanel.add(heightLabel);
        TrianglePanel.add(resetButton);
        TrianglePanel.add(backtoHome);
    }

    public static void showTrianglePage() {
        homefram.setSize(450, 450);
        backtoHome.setBounds(10, 280, 100, 50);
        TrianglePanel.setVisible(true);
        homefram.add(TrianglePanel);
        homefram.revalidate();
        homefram.repaint();
    }

    public static void resetTrianglePage() {
        TrianglePanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            resetTrianglePage();
            AreaPage.showAreaPage();  // العودة إلى صفحة المساحة
        } else if (e.getSource() == calculateButton) {
            // حساب المساحة
            try {
                double base = Double.parseDouble(baseField.getText());
                double height = Double.parseDouble(heightField.getText());

                if (base <= 0 || height <= 0) {
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resultLabel.setText("Please enter positive numbers!");
                    resetButton.setVisible(true);
                } else {
                    double area = 0.5 * base * height;
                    resultLabel.setText("Traingle area : " + area);
                    resetButton.setVisible(true);  // إظهار زر "Reset" بعد الحساب
                    playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
                }
            } catch (NumberFormatException ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Invalid input! Please enter numbers only.");
            }
            addHistory(resultLabel.getText());
            updateHistoryDisplay();
        } else if (e.getSource() == resetButton) {
            // إعادة تعيين الحقول
            baseField.setText("");
            heightField.setText("");
            resultLabel.setText("");
            resetButton.setVisible(false);  // إخفاء زر "Reset" بعد التعيين
        } else if (e.getSource() == backtoHome) {
            baseField.setText("");
            heightField.setText("");
            resultLabel.setText("");
            resetButton.setVisible(false);
            resetTrianglePage();
            ShowCalculator();
        }
    }

    // DocumentFilter مخصص للسماح بإدخال الأرقام فقط
    // DocumentFilter مخصص للسماح بإدخال الأرقام العشرية فقط
    class NumericFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
            if (string != null && isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), string, offset)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
            if (text != null && isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), text, offset)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        // دالة للتحقق من صحة الإدخال بحيث يحتوي على أرقام ونقطة عشرية واحدة فقط
        private boolean isValidInput(String currentText, String newText, int offset) {
            StringBuilder updatedText = new StringBuilder(currentText);
            updatedText.insert(offset, newText);

            // التحقق من أن النص الناتج هو رقم عشري صالح (بحد أقصى نقطة عشرية واحدة)
            return updatedText.toString().matches("\\d*\\.?\\d*");
        }
    }

}
