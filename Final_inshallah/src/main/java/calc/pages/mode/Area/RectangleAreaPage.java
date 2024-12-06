package calc.pages.mode.Area;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import static calc.Calculator.ShowCalculator;
import static calc.Calculator.homefram;
import static calc.Calculator.playSound;
import static calc.constants.PFontsize;
import static calc.constants.Pheight;
import static calc.constants.Pwidth;
import static calc.pages.HistoryPage.addHistory;
import static calc.pages.HistoryPage.updateHistoryDisplay;
import static calc.tools.backtohome.backtohomee;

public class RectangleAreaPage implements ActionListener {

    public static JPanel RectanglePanel = new JPanel();
    public static JLabel RectangleLabel;
    public static JButton BackButton;
    public static JTextField lengthField;
    public static JTextField widthField;
    public static JButton calculateButton;
    public static JButton resetButton;  // زر إعادة التعيين
    public static JLabel resultLabel;
    public static JButton backtoHome;

    public RectangleAreaPage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // Set up the rectangle area page
        RectanglePanel.setBounds(5, 60, 500, 500);
        RectanglePanel.setLayout(null);
        RectanglePanel.setBackground(Color.decode("#101a43"));

        RectangleLabel = new JLabel("Area of Rectangle");
        RectangleLabel.setBounds(120, 80, 300, 40);
        RectangleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        RectangleLabel.setForeground(Color.WHITE);

        BackButton = new JButton("Back");
        BackButton.setFont(new Font("Arial", Font.BOLD, 20));
        BackButton.setForeground(Color.white);
        BackButton.setContentAreaFilled(false);
        BackButton.setBounds(10, 0, 100, 50);
        BackButton.setOpaque(false);
        BackButton.setBorder(null);
        BackButton.addActionListener(this);
        BackButton.setFocusable(false);

        // Input fields for length and width
        lengthField = new JTextField();
        lengthField.setBounds(170, 200, 200, 30);
        lengthField.setFont(new Font("Arial", Font.BOLD, PFontsize));
        ((AbstractDocument) lengthField.getDocument()).setDocumentFilter(new NumericFilter());

        widthField = new JTextField();
        widthField.setBounds(170, 250, 200, 30);
        widthField.setFont(new Font("Arial", Font.BOLD, PFontsize));
        ((AbstractDocument) widthField.getDocument()).setDocumentFilter(new NumericFilter());

        // Labels for length and width
        JLabel lengthLabel = new JLabel("Length:");
        lengthLabel.setBounds(80, 200, 100, 30);
        lengthLabel.setFont(new Font("Arial", Font.BOLD, 19));
        lengthLabel.setForeground(Color.WHITE);

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setBounds(80, 250, 100, 30);
        widthLabel.setFont(new Font("Arial", Font.BOLD, 19));
        widthLabel.setForeground(Color.WHITE);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 350, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFont(new Font("Arial", Font.BOLD, PFontsize));
        calculateButton.setFocusable(false);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(340, 350, 100, 30);
        resetButton.setVisible(false); // في البداية يكون غير مرئي
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        // Result label
        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 350, 350, 200);
        resultLabel.setFont(new Font("Arial", Font.BOLD, PFontsize));
        resultLabel.setForeground(Color.WHITE);

        RectanglePanel.add(RectangleLabel);
        RectanglePanel.add(BackButton);
        RectanglePanel.add(lengthField);
        RectanglePanel.add(widthField);
        RectanglePanel.add(calculateButton);
        RectanglePanel.add(resetButton);  // إضافة زر إعادة التعيين
        RectanglePanel.add(resultLabel);
        RectanglePanel.add(lengthLabel);
        RectanglePanel.add(widthLabel);
        RectanglePanel.add(backtoHome);

        // Add "Enter" key listeners for field transitions
        lengthField.addActionListener(e -> widthField.requestFocus());
        widthField.addActionListener(e -> calculateButton.doClick());
    }

    public static void showRectangleareaPage() {
        homefram.setSize(Pwidth, Pheight);
        backtoHome.setBounds(300, 3, 100, 50);
        RectanglePanel.setVisible(true);
        homefram.add(RectanglePanel);
        homefram.revalidate();
        homefram.repaint();
    }

    public static void resetRectanglePage() {
        RectanglePanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            resetRectanglePage();
            AreaPage.showAreaPage();  // العودة إلى صفحة المساحة
        } else if (e.getSource() == calculateButton) {
            // حساب المساحة
            try {
                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(widthField.getText());
                if (length <= 0 || width <= 0) {
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resultLabel.setText("Enter positive values only!");
                    resetButton.setVisible(true);
                } else {
                    double area = length * width;
                    resultLabel.setText("Rectangle Area: " + area);
                    resetButton.setVisible(true); // جعل زر إعادة التعيين مرئيًا بعد الحساب
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
            lengthField.setText("");
            widthField.setText("");
            resultLabel.setText("");
            resetButton.setVisible(false); // إخفاء زر إعادة التعيين بعد إعادة التعيين
        } else if (e.getSource() == backtoHome) {
            lengthField.setText("");
            widthField.setText("");
            resultLabel.setText("");
            resetButton.setVisible(false);
            resetRectanglePage();
            ShowCalculator();
        }
    }

    // Custom DocumentFilter to allow only numeric input
    // Custom DocumentFilter to allow only numeric input, including decimal points
// Custom DocumentFilter to allow only numeric input, including decimal points
    class NumericFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }
            if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), string, offset)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }
            if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), string, offset)) {
                super.replace(fb, offset, length, string, attr);
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
