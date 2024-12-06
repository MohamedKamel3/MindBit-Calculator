package calc.pages.mode.equatios;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import static calc.Calculator.ShowCalculator;
import static calc.Calculator.homefram;
import static calc.constants.PFontsize;
import static calc.constants.PbuttonColor;
import static calc.logic.grapher.Window.showgraph;
import static calc.pages.HistoryPage.addHistory;
import static calc.pages.HistoryPage.updateHistoryDisplay;
import static calc.pages.mode.equatios.equationpage.equPanelshow;
import calc.tools.RoundedButton;
import static calc.tools.backtohome.backtohomee;

public class equations_X2 implements ActionListener {

    public static RoundedButton calculate, graphButton;
    public static JPanel equations_X2panel = new JPanel();
    public static JLabel eq1, resultLabel, resultX11Label, resultX12Label;
    public static JLabel equation1Label;
    public static JButton BackButtonequ_X2, clearButton;
    public static JTextField a1Field, b1Field, c1Field;
    public static JTextField resultX1, resultX2;
    public static JButton backtoHome;
    double a1;
    double b1;
    double c1;

    public equations_X2() {
        backtoHome = backtohomee();
        backtoHome.addActionListener(this);

        equations_X2panel.setSize(500, 600);
        equations_X2panel.setBackground(Color.decode("#101a43"));
        equations_X2panel.setLayout(null);

        // Back button
        BackButtonequ_X2 = new JButton("Back");
        BackButtonequ_X2.setFont(new Font("Arial", Font.BOLD, 20));
        BackButtonequ_X2.setForeground(PbuttonColor);
        BackButtonequ_X2.setBounds(-20, 3, 100, 50);
        BackButtonequ_X2.setContentAreaFilled(false);
        BackButtonequ_X2.setOpaque(false);
        BackButtonequ_X2.addActionListener(this);
        BackButtonequ_X2.setBorderPainted(false);
        BackButtonequ_X2.setFocusable(false);

        // CLEAR button
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setForeground(PbuttonColor);
        clearButton.setBounds(179, 280, 150, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setOpaque(false);
        clearButton.setFocusable(false);

        // Equation Labels
        eq1 = new JLabel("A1x² + B1x + C = 0");
        eq1.setBounds(180, 50, 200, 30);
        eq1.setFont(new Font("Arial", Font.BOLD, 18));
        eq1.setForeground(PbuttonColor);

        // Input fields for coefficients
        a1Field = createNumberField(170, 100);
        b1Field = createNumberField(230, 100);
        c1Field = createNumberField(290, 100);

        // Calculate button
        calculate = new RoundedButton("Calculate", new Dimension(120, 60), PbuttonColor, PFontsize);
        calculate.setBounds(200, 170, 250, 100);
        calculate.addActionListener(this);
        calculate.setFocusable(false);

        graphButton = new RoundedButton("Graph", new Dimension(120, 60), PbuttonColor, PFontsize);
        graphButton.setBounds(10, 170, 250, 100);
        graphButton.addActionListener(this);
        graphButton.setFocusable(false);

        // Result display
        resultLabel = new JLabel("Results:");
        resultLabel.setBounds(50, 430, 100, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 19));
        resultLabel.setForeground(PbuttonColor);

        resultX11Label = new JLabel("X1 = ");
        resultX11Label.setBounds(70, 460, 50, 30);
        resultX11Label.setFont(new Font("Arial", Font.BOLD, 16));
        resultX11Label.setForeground(PbuttonColor);

        resultX1 = new JTextField(10);
        resultX1.setBounds(120, 460, 250, 30);
        resultX1.setFont(new Font("Arial", Font.BOLD, 20));
        resultX1.setEditable(false);

        resultX12Label = new JLabel("X2 = ");
        resultX12Label.setBounds(70, 500, 50, 30);
        resultX12Label.setFont(new Font("Arial", Font.BOLD, 16));
        resultX12Label.setForeground(PbuttonColor);

        resultX2 = new JTextField(10);
        resultX2.setBounds(120, 500, 250, 30);
        resultX2.setFont(new Font("Arial", Font.BOLD, 20));
        resultX2.setEditable(false);

        // Label to display the equation
        equation1Label = new JLabel("");
        equation1Label.setBounds(50, 360, 400, 30);
        equation1Label.setFont(new Font("Arial", Font.BOLD, 20));
        equation1Label.setForeground(PbuttonColor);

        // Adding components to the panel
        equations_X2panel.add(BackButtonequ_X2);
        equations_X2panel.add(clearButton);
        equations_X2panel.add(calculate);
        equations_X2panel.add(eq1);
        equations_X2panel.add(a1Field);
        equations_X2panel.add(b1Field);
        equations_X2panel.add(c1Field);
        equations_X2panel.add(equation1Label);
        equations_X2panel.add(resultLabel);
        equations_X2panel.add(resultX11Label);
        equations_X2panel.add(resultX1);
        equations_X2panel.add(resultX12Label);
        equations_X2panel.add(resultX2);
        equations_X2panel.add(backtoHome);
        equations_X2panel.add(graphButton);
    }

    // Method to create a JTextField with number filter and key listener
    private JTextField createNumberField(int x, int y) {
        JTextField textField = new JTextField(5);
        textField.setBounds(x, y, 50, 30);
        textField.setFont(new Font("Arial", Font.BOLD, 20));

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                String text = textField.getText();

                // الانتقال إلى الحقل التالي عند الضغط على Enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    focusNextField(textField, true);
                } // الانتقال إلى الحقل التالي عند الضغط على Tab
                else if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    focusNextField(textField, false);
                    e.consume();
                } // السماح بإدخال الأرقام فقط، مع السماح بدوت واحدة
                else if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();  // تجاهل الإدخال إذا لم يكن رقمًا أو نقطة أو Backspace
                } else if (c == '.' && text.contains(".")) {
                    e.consume();  // تجاهل الإدخال إذا كانت النقطة موجودة مسبقًا
                } // السماح بإضافة علامة السالب في البداية فقط
                else if (c == KeyEvent.VK_MINUS) {
                    if (textField.getText().isEmpty() || text.startsWith("-")) {
                        e.consume(); // تجاهل إذا كان النص فارغًا أو السالب موجود
                    } else {
                        textField.setText("-" + text);
                        e.consume();
                    }
                }
            }
        });

        applyNumberFilter(textField);
        return textField;
    }

    private void focusNextField(JTextField currentField, boolean forward) {
        if (forward) {
            if (currentField == a1Field) {
                b1Field.requestFocus();
            } else if (currentField == b1Field) {
                c1Field.requestFocus();
            } else if (currentField == c1Field) {
                calculateResults(); // Execute calculation here when in the last field
            }
        }
    }

    private void applyNumberFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("-?\\d*\\.?\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("-?\\d*\\.?\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private void calculateResults() {
        try {
            a1 = Double.parseDouble(a1Field.getText());
            b1 = Double.parseDouble(b1Field.getText());
            c1 = Double.parseDouble(c1Field.getText());

            double determinant = b1 * b1 - 4 * a1 * c1;

            if (determinant < 0) {
                double realPart = -b1 / (2 * a1);
                double imaginaryPart = Math.sqrt(-determinant) / (2 * a1);

                resultX1.setText(String.format("%.2f + %.2fi", realPart, imaginaryPart));
                resultX2.setText(String.format("%.2f - %.2fi", realPart, imaginaryPart));
                equation1Label.setText(a1 + "x² + " + b1 + "x + " + c1 + " = 0");

            } else {
                double x1 = (-b1 + Math.sqrt(determinant)) / (2 * a1);
                double x2 = (-b1 - Math.sqrt(determinant)) / (2 * a1);

                resultX1.setText(String.format("%.2f", x1));
                resultX2.setText(String.format("%.2f", x2));
                equation1Label.setText(a1 + "x² + " + b1 + "x + " + c1 + " = 0");
            }
        } catch (NumberFormatException ex) {
            resultX1.setText("Invalid Input");
            resultX2.setText("Invalid Input");
        }
        addHistory(equation1Label.getText() + "\n" + resultX11Label.getText() + "  " + resultX1.getText() + "\n" + resultX12Label.getText() + "  " + resultX2.getText());
        updateHistoryDisplay();
    }

    public static void Resetequations_X2() {
        clearFields();
        BackButtonequ_X2.setVisible(false);
        clearButton.setVisible(false);
        calculate.setVisible(false);
        eq1.setVisible(false);
        a1Field.setVisible(false);
        b1Field.setVisible(false);
        c1Field.setVisible(false);
        equation1Label.setVisible(false);
        resultLabel.setVisible(false);
        resultX11Label.setVisible(false);
        resultX1.setVisible(false);
        resultX12Label.setVisible(false);
        resultX2.setVisible(false);
        backtoHome.setVisible(false);
        equations_X2panel.setVisible(false);

    }

    public static void equations_X2panelshow() {
        backtoHome.setBounds(370, 3, 100, 50);
        BackButtonequ_X2.setVisible(true);
        clearButton.setVisible(true);
        calculate.setVisible(true);
        eq1.setVisible(true);
        a1Field.setVisible(true);
        b1Field.setVisible(true);
        c1Field.setVisible(true);
        equation1Label.setVisible(true);
        resultLabel.setVisible(true);
        resultX11Label.setVisible(true);
        resultX1.setVisible(true);
        resultX12Label.setVisible(true);
        resultX2.setVisible(true);
        backtoHome.setVisible(true);
        equations_X2panel.setVisible(true);
        homefram.add(equations_X2panel);
    }

    public static void clearFields() {
        a1Field.setText("");
        b1Field.setText("");
        c1Field.setText("");
        resultX1.setText("");
        resultX2.setText("");
        equation1Label.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculate) {
            calculateResults();
        } else if (e.getSource() == BackButtonequ_X2) {
            clearFields();
            Resetequations_X2();
            equPanelshow();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == backtoHome) {
            clearFields();
            Resetequations_X2();
            ShowCalculator();
        } else if (e.getSource() == graphButton) {

            if (!equation1Label.getText().isEmpty()) {
                showgraph(a1 + "x^2 + " + b1 + "x + " + c1);
            }
        }

    }

}
