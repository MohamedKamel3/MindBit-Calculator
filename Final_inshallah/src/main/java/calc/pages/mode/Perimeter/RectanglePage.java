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
import static calc.pages.mode.Perimeter.Perimeterpage.showperpage;
import static calc.tools.backtohome.backtohomee;

public class RectanglePage implements ActionListener {

    public static JPanel RectanglePanel = new JPanel();
    public static JLabel RectangleLabel;
    public static JButton BackButtonrectangle;
    public static JTextField lengthField;
    public static JTextField widthField;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton clearButton;
    public static JButton backtoHome;

    public RectanglePage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // Setup the Rectangle interface
        RectanglePanel.setBounds(5, 20, 450, 400);
        RectanglePanel.setLayout(null);
        RectanglePanel.setBackground(Color.decode("#101a43"));

        RectangleLabel = new JLabel("Rectangle Perimeter");
        RectangleLabel.setBounds(135, 23, 250, 40);
        RectangleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        RectangleLabel.setForeground(Color.WHITE);

        BackButtonrectangle = new JButton("Back");
        BackButtonrectangle.setFont(new Font("Arial", Font.BOLD, 20));
        BackButtonrectangle.setForeground(Color.white);
        BackButtonrectangle.setContentAreaFilled(false);
        BackButtonrectangle.setBounds(10, 13, 100, 50);
        BackButtonrectangle.setOpaque(false);
        BackButtonrectangle.setBorder(null);
        BackButtonrectangle.addActionListener(this);
        BackButtonrectangle.setBorderPainted(false);
        BackButtonrectangle.setFocusable(false);

        // CLEAR button
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setForeground(PbuttonColor);
        clearButton.setBounds(300, 300, 100, 50); // Position the button
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setOpaque(false);
        clearButton.setVisible(false);
        clearButton.setFocusable(false);

        // Input fields for length and width
        lengthField = new JTextField();
        lengthField.setBounds(150, 100, 150, 30);
        lengthField.setFont(new Font("Arial", Font.BOLD, 22));
        addNumericOnlyListener(lengthField, false);

        JLabel lengthLabel = new JLabel("Length:");
        lengthLabel.setBounds(80, 100, 100, 30);
        lengthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lengthLabel.setForeground(Color.WHITE);

        widthField = new JTextField();

        widthField.setBounds(150, 150, 150, 30);
        widthField.setFont(new Font("Arial", Font.BOLD, 22));
        addNumericOnlyListener(widthField, true);

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setBounds(80, 150, 100, 30);
        widthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        widthLabel.setForeground(Color.WHITE);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 200, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFocusable(false);

        // Result display
        resultLabel = new JLabel("");
        resultLabel.setBounds(60, 270, 350, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(Color.WHITE);

        RectanglePanel.add(RectangleLabel);
        RectanglePanel.add(BackButtonrectangle);
        RectanglePanel.add(lengthField);
        RectanglePanel.add(widthField);
        RectanglePanel.add(calculateButton);
        RectanglePanel.add(resultLabel);
        RectanglePanel.add(lengthLabel);
        RectanglePanel.add(widthLabel);
        RectanglePanel.add(clearButton);
        RectanglePanel.add(backtoHome);

    }

    private void addNumericOnlyListener(JTextField field, boolean isLastField) {
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
                    if (isLastField) {
                        calculateButton.doClick();  // نفذ العملية إذا كان الحقل الأخير
                    } else {
                        field.transferFocus();  // انتقال للحقل التالي
                    }
                }
            }
        });
    }

    public static void showRectanglePage() {
        homefram.setSize(450, 450);
        backtoHome.setBounds(320, 3, 100, 50);
        RectanglePanel.setVisible(true);
        homefram.add(RectanglePanel);
    }

    public static void resetRectanglePage() {
        RectanglePanel.setVisible(false);
    }

    private void clearFields() {
        widthField.setText("");
        resultLabel.setText("");
        lengthField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButtonrectangle) {
            resetRectanglePage();
            showperpage();
        } else if (e.getSource() == backtoHome) {
            resetRectanglePage();
            ShowCalculator();
        } else if (e.getSource() == clearButton) {
            clearFields(); // Clear fields
        } else if (e.getSource() == calculateButton) {
            // Calculate perimeter
            try {
                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(widthField.getText());
                if (length > 0 && width > 0) {
                    double perimeter = 2 * (length + width);
                    resultLabel.setText("Perimeter: " + perimeter);
                    clearButton.setVisible(true);
                    playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
                } else {
                    resultLabel.setBounds(60, 270, 350, 30);
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resultLabel.setText("Invalid input! positive numbers.");
                    clearButton.setVisible(true);

                }
                // Display the result
            } catch (NumberFormatException ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Invalid input!");
            }
        }
    }
}
