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

public class equations_X3 implements ActionListener {

    public static RoundedButton calculate, graphButton;
    public static JPanel equations_X3panel = new JPanel();
    public static JLabel eq1, resultLabel, resultX11Label, resultX12Label, resultX13Label, equation1Label;
    public static JButton BackButtonequ_X3, clearButton;
    public static JTextField a1Field, b1Field, c1Field, d1Field;
    public static JTextField resultX1, resultX2, resultX3;
    public static JButton backtoHome;
    double a1;
    double b1;
    double c1;
    double d1;

    public equations_X3() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);

        equations_X3panel.setSize(500, 600);
        equations_X3panel.setBackground(Color.decode("#101a43"));
        equations_X3panel.setLayout(null);

        // Back button
        BackButtonequ_X3 = new JButton("Back");
        BackButtonequ_X3.setFont(new Font("Arial", Font.BOLD, 20));
        BackButtonequ_X3.setForeground(PbuttonColor);
        BackButtonequ_X3.setBounds(-20, 3, 100, 50);
        BackButtonequ_X3.setContentAreaFilled(false);
        BackButtonequ_X3.setOpaque(false);
        BackButtonequ_X3.addActionListener(this);
        BackButtonequ_X3.setBorderPainted(false);
        BackButtonequ_X3.setFocusable(false);

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

        // Input fields for coefficients
        a1Field = createNumberField(130, 100); // Coefficient a
        b1Field = createNumberField(190, 100); // Coefficient b
        c1Field = createNumberField(250, 100); // Coefficient c
        d1Field = createNumberField(310, 100); // Coefficient d

        // Calculate button
        calculate = new RoundedButton("Calculate", new Dimension(120, 60), PbuttonColor, PFontsize);
        calculate.setBounds(200, 170, 250, 100);
        calculate.addActionListener(this);
        calculate.setFocusable(false);

        graphButton = new RoundedButton("Graph", new Dimension(120, 60), PbuttonColor, PFontsize);
        graphButton.setBounds(10, 170, 250, 100);
        graphButton.addActionListener(this);
        graphButton.setFocusable(false);

        // Equation Labels
        eq1 = new JLabel("A1x³ + B1x² + C1x + D1 = 0");
        eq1.setBounds(115, 50, 270, 30);
        eq1.setFont(new Font("Arial", Font.BOLD, 21));
        eq1.setForeground(PbuttonColor);

        // Label to display the equation
        equation1Label = new JLabel("");
        equation1Label.setBounds(70, 345, 400, 30);
        equation1Label.setFont(new Font("Arial", Font.BOLD, 20));
        equation1Label.setForeground(PbuttonColor);

        // Result display
        resultLabel = new JLabel("Results:");
        resultLabel.setBounds(30, 370, 100, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 19));
        resultLabel.setForeground(PbuttonColor);

        resultX11Label = new JLabel("X1 = ");
        resultX11Label.setBounds(40, 410, 50, 30);
        resultX11Label.setFont(new Font("Arial", Font.BOLD, 20));
        resultX11Label.setForeground(PbuttonColor);

        resultX1 = new JTextField(10);
        resultX1.setBounds(90, 410, 340, 30);
        resultX1.setFont(new Font("Arial", Font.BOLD, 20));
        resultX1.setEditable(false);

        resultX12Label = new JLabel("X2 = ");
        resultX12Label.setBounds(40, 460, 50, 30);
        resultX12Label.setFont(new Font("Arial", Font.BOLD, 20));
        resultX12Label.setForeground(PbuttonColor);

        resultX2 = new JTextField(10);
        resultX2.setBounds(90, 460, 340, 30);
        resultX2.setFont(new Font("Arial", Font.BOLD, 20));
        resultX2.setEditable(false);

        resultX13Label = new JLabel("X3 = ");
        resultX13Label.setBounds(40, 510, 50, 30);
        resultX13Label.setFont(new Font("Arial", Font.BOLD, 20));
        resultX13Label.setForeground(PbuttonColor);

        resultX3 = new JTextField(10);
        resultX3.setBounds(90, 510, 340, 30);
        resultX3.setFont(new Font("Arial", Font.BOLD, 20));
        resultX3.setEditable(false);

        // Adding components to the panel
        equations_X3panel.add(BackButtonequ_X3);
        equations_X3panel.add(clearButton); // Adding CLEAR button
        equations_X3panel.add(calculate);
        equations_X3panel.add(eq1);
        equations_X3panel.add(a1Field);
        equations_X3panel.add(b1Field);
        equations_X3panel.add(c1Field);
        equations_X3panel.add(d1Field);
        equations_X3panel.add(equation1Label);
        equations_X3panel.add(resultLabel);
        equations_X3panel.add(resultX11Label);
        equations_X3panel.add(resultX1);
        equations_X3panel.add(resultX12Label);
        equations_X3panel.add(resultX2);
        equations_X3panel.add(resultX13Label);
        equations_X3panel.add(resultX3);
        equations_X3panel.add(graphButton);
        equations_X3panel.add(backtoHome);

    }

    public static void clearFields() {
        a1Field.setText("");
        b1Field.setText("");
        c1Field.setText("");
        d1Field.setText("");
        // e1Field.setText("");
        resultX1.setText("");
        resultX2.setText("");
        resultX3.setText("");
        // resultX4.setText("");
        equation1Label.setText("");
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
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_ENTER) {
                    focusNextField(textField, true);
                } else if (keyCode == KeyEvent.VK_TAB) {
                    focusNextField(textField, false);
                    e.consume();
                } else if (!Character.isDigit(c) && c != '.' && c != '-'
                        && keyCode != KeyEvent.VK_BACK_SPACE && keyCode != KeyEvent.VK_DELETE) {
                    e.consume();  // Ignore input if it's not a digit, dot, minus, backspace, or delete
                }
            }
        });

        // Applying a document filter to limit to one decimal point
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()) + string)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()).substring(0, offset) + text + fb.getDocument().getText(0, fb.getDocument().getLength()).substring(offset + length))) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isValidInput(String text) {
                return text.matches("-?\\d*\\.?\\d*") && (text.indexOf('.') == text.lastIndexOf('.'));
            }
        });

        return textField;
    }

    // Method to focus on the next or previous field and calculate if on the last field
    private void focusNextField(JTextField currentField, boolean forward) {
        if (forward) {
            if (currentField == a1Field) {
                b1Field.requestFocus();
            } else if (currentField == b1Field) {
                c1Field.requestFocus();
            } else if (currentField == c1Field) {
                d1Field.requestFocus();
            } else if (currentField == d1Field) {
                // Trigger calculation if on the last field
                calculate.doClick();
            }
        }
    }

    private void applyNumberFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("-?\\d*\\.?\\d*")) { // Allow negative numbers and decimals
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

    public static void Resetequations_X3() {
        clearFields();
        equations_X3panel.setVisible(false);
        BackButtonequ_X3.setVisible(false);
    }

    public static void equations_X3panelshow() {
        backtoHome.setBounds(370, 3, 100, 50);
        equations_X3panel.setVisible(true);
        BackButtonequ_X3.setVisible(true);
        homefram.add(equations_X3panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculate) {
            try {
                a1 = Double.parseDouble(a1Field.getText());
                b1 = Double.parseDouble(b1Field.getText());
                c1 = Double.parseDouble(c1Field.getText());
                d1 = Double.parseDouble(d1Field.getText());

                // Use Cardano's formula for cubic equations
                double f = ((3 * c1 / a1) - ((b1 * b1) / (a1 * a1))) / 3;
                double g = ((2 * b1 * b1 * b1) / (a1 * a1 * a1) - (9 * b1 * c1) / (a1 * a1) + (27 * d1 / a1)) / 27;
                double h = (g * g) / 4 + (f * f * f) / 27;

                if (h > 0) {
                    // One real root and two complex roots
                    double r = -(g / 2) + Math.sqrt(h);
                    double s = Math.cbrt(r);
                    double t = -(g / 2) - Math.sqrt(h);
                    double u = Math.cbrt(t);

                    double x1 = (s + u) - (b1 / (3 * a1));
                    resultX1.setText(String.format("%.2f", x1));

                    // Calculate complex roots
                    double realPart = -s - u - (b1 / (3 * a1));
                    double imaginaryPart = Math.sqrt(3) * (s - u) / 2;

                    resultX2.setText(String.format("%.2f + %.2fi", realPart, imaginaryPart));
                    resultX3.setText(String.format("%.2f - %.2fi", realPart, imaginaryPart));
                    equation1Label.setText(a1 + "x³ + " + b1 + "x² + " + c1 + "x + " + d1 + " = 0");

                } else if (h == 0) {
                    // All roots real and at least two are equal
                    double r = -(g / 2);
                    double s = Math.cbrt(r);

                    double x1 = (2 * s) - (b1 / (3 * a1));
                    double x2 = -s - (b1 / (3 * a1));
                    resultX1.setText(String.format("%.2f", x1));
                    resultX2.setText(String.format("%.2f", x2));
                    resultX3.setText(String.format("%.2f", x2));
                    equation1Label.setText(a1 + "x³ + " + b1 + "x² + " + c1 + "x + " + d1 + " = 0");

                } else {
                    // All roots are real and different
                    double i = Math.sqrt(((g * g) / 4) - h);
                    double j = Math.cbrt(i);
                    double k = Math.acos(-(g / (2 * i)));
                    double m = Math.cos(k / 3);
                    double n = Math.sqrt(3) * Math.sin(k / 3);
                    double p = -(b1 / (3 * a1));

                    double x1 = 2 * j * m + p;
                    double x2 = -j * (m + n) + p;
                    double x3 = -j * (m - n) + p;

                    resultX1.setText(String.format("%.2f", x1));
                    resultX2.setText(String.format("%.2f", x2));
                    resultX3.setText(String.format("%.2f", x3));
                    equation1Label.setText(a1 + "x³ + " + b1 + "x² + " + c1 + "x + " + d1 + " = 0");
                }
            } catch (NumberFormatException ex) {
                resultX1.setText("Invalid Input");
                resultX2.setText("");
                resultX3.setText("");
            }
            addHistory(equation1Label.getText() + "\n" + resultX11Label.getText() + "  " + resultX1.getText() + "\n" + resultX12Label.getText() + "  " + resultX2.getText() + "\n" + resultX13Label.getText() + "  " + resultX3.getText());
            updateHistoryDisplay();

        } else if (e.getSource() == BackButtonequ_X3) {
            a1Field.setText("");
            b1Field.setText("");
            c1Field.setText("");
            d1Field.setText("");
            resultX1.setText("");
            resultX2.setText("");
            resultX3.setText("");
            equation1Label.setText("");
            Resetequations_X3();
            equPanelshow();
        } else if (e.getSource() == backtoHome) {
            a1Field.setText("");
            b1Field.setText("");
            c1Field.setText("");
            d1Field.setText("");
            resultX1.setText("");
            resultX2.setText("");
            resultX3.setText("");
            equation1Label.setText("");
            clearFields();
            Resetequations_X3();
            ShowCalculator();
        } else if (e.getSource() == clearButton) {
            a1Field.setText("");
            b1Field.setText("");
            c1Field.setText("");
            d1Field.setText("");
            resultX1.setText("");
            resultX2.setText("");
            resultX3.setText("");
            equation1Label.setText("");
        } else if (e.getSource() == graphButton) {

            if (!equation1Label.getText().isEmpty()) {
                showgraph(a1 + "x^3 + " + b1 + "x^2 + " + c1 + "x + " + d1);
            }
        }
    }
}
