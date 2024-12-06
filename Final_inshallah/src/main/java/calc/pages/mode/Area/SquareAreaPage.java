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
import static calc.constants.Pwidth;
import static calc.pages.HistoryPage.addHistory;
import static calc.pages.HistoryPage.updateHistoryDisplay;
import static calc.pages.mode.Area.AreaPage.showAreaPage;
import static calc.tools.backtohome.backtohomee;

public class SquareAreaPage implements ActionListener {

    public static JPanel SquarePanel = new JPanel();
    public static JLabel SquareLabel;
    public static JButton BackButton;
    public static JTextField sideField;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton resetButton;
    public static JButton backtoHome;

    public SquareAreaPage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // إعداد واجهة المربع
        SquarePanel.setBounds(5, 60, 450, 400);
        SquarePanel.setLayout(null);
        SquarePanel.setBackground(Color.decode("#101a43"));

        SquareLabel = new JLabel("Area of ​​the square");
        SquareLabel.setBounds(139, 23, 300, 40);
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

        // حقل الإدخال لطول الضلع
        sideField = new JTextField();
        sideField.setBounds(180, 100, 200, 30);
        sideField.setFont(new Font("Arial", Font.BOLD, 19));
        ((AbstractDocument) sideField.getDocument()).setDocumentFilter(new NumericFilter());  // تطبيق فلتر الأرقام

        // نقل التركيز إلى زر "Calculate" عند الضغط على Enter
        sideField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateButton.doClick();
                }
            }
        });

        JLabel sideLabel = new JLabel("side of square:");
        sideLabel.setBounds(30, 100, 150, 30);
        sideLabel.setFont(new Font("Arial", Font.BOLD, 20));
        sideLabel.setForeground(Color.WHITE);

        // زر حساب المساحة
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(180, 150, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 19));
        calculateButton.setFocusable(false);

        // عرض النتيجة
        resultLabel = new JLabel("");
        resultLabel.setBounds(130, 230, 300, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);

        // زر إعادة التعيين
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setBounds(340, 310, 100, 30);
        resetButton.setVisible(false); // الزر يظهر فقط بعد الضغط على "Calculate"
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        SquarePanel.add(SquareLabel);
        SquarePanel.add(BackButton);
        SquarePanel.add(sideField);
        SquarePanel.add(calculateButton);
        SquarePanel.add(resultLabel);
        SquarePanel.add(sideLabel);
        SquarePanel.add(resetButton);
        SquarePanel.add(backtoHome);
    }

    public static void showSquarePage() {
        backtoHome.setBounds(10, 270, 100, 50);
        SquarePanel.setVisible(true);
        homefram.add(SquarePanel);
        homefram.revalidate();
        homefram.repaint();
        homefram.setSize(Pwidth, 450);
    }

    public static void resetSquarePage() {
        SquarePanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            resetSquarePage();
            showAreaPage();  // استدعاء الصفحة الرئيسية
        } else if (e.getSource() == calculateButton) {
            // حساب المساحة
            try {
                double side = Double.parseDouble(sideField.getText());
                if (side <= 0) {
                    resultLabel.setText("Please enter a positive number!");
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resetButton.setVisible(true);
                } else {
                    double area = side * side;
                    resultLabel.setText("Square area : " + area);
                    resetButton.setVisible(true);  // إظهار زر "Reset" بعد الحساب
                    playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
                }
            } catch (NumberFormatException ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Please enter numbers.");
            }
            addHistory(resultLabel.getText());
            updateHistoryDisplay();
        } else if (e.getSource() == resetButton) {
            // إعادة تعيين الحقول
            sideField.setText("");
            resultLabel.setText("");
            resetButton.setVisible(false);  // إخفاء زر "Reset" بعد التعيين
        } else if (e.getSource() == backtoHome) {
            sideField.setText("");
            resultLabel.setText("");
            resetButton.setVisible(false);
            resetSquarePage();
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
