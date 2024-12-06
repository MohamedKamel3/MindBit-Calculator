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
import static calc.Calculator.playSound;
import static calc.constants.PFontsize;
import static calc.constants.PbuttonColor;
import static calc.pages.HistoryPage.addHistory;
import static calc.pages.HistoryPage.updateHistoryDisplay;
import static calc.pages.mode.equatios.equationpage.equPanelshow;
import calc.tools.RoundedButton;
import static calc.tools.backtohome.backtohomee;

public class bsolve_3_eq implements ActionListener {

    public static RoundedButton calculate;
    public static JPanel solve_3_eq_panel = new JPanel();
    public static JLabel eq1, eq2, eq3, resultLabel, resultXLabel, resultYLabel, resultZLabel, equation1Label, equation2Label, equation3Label;
    public static JButton BackButtonsolve, clearButton;
    public static JTextField a1Field, b1Field, c1Field, a2Field, b2Field, c2Field, a3Field, b3Field, c3Field;
    public static JTextField resultX, resultY, resultZ;
    public static JTextField d1Field, d2Field, d3Field; // Declare these as class variables
    public static JButton backtoHome;

    public bsolve_3_eq() {
        backtoHome = backtohomee();
        backtoHome.addActionListener(this);

        solve_3_eq_panel.setSize(500, 700);
        solve_3_eq_panel.setBackground(Color.decode("#101a43"));
        solve_3_eq_panel.setLayout(null);

        // Back button
        BackButtonsolve = new JButton("Back");
        BackButtonsolve.setFont(new Font("Arial", Font.BOLD, 20));
        BackButtonsolve.setForeground(PbuttonColor);
        BackButtonsolve.setBounds(340, 100, 100, 50);
        BackButtonsolve.setContentAreaFilled(false);
        BackButtonsolve.setOpaque(false);
        BackButtonsolve.addActionListener(this);
        BackButtonsolve.setBorderPainted(false);
        BackButtonsolve.setFocusable(false);

        // CLEAR button
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setForeground(PbuttonColor);
        clearButton.setBounds(310, 260, 150, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setOpaque(false);
        clearButton.setFocusable(false);

        // Equation Labels
        eq1 = new JLabel("A1x + B1y + C1z = D");
        eq1.setBounds(60, 30, 300, 30);
        eq1.setFont(new Font("Arial", Font.BOLD, 20));
        eq1.setForeground(PbuttonColor);

        eq2 = new JLabel("A2x + B2y + C2z = D");
        eq2.setBounds(60, 110, 300, 30);
        eq2.setFont(new Font("Arial", Font.BOLD, 20));
        eq2.setForeground(PbuttonColor);

        eq3 = new JLabel("A3x + B3y + C3z = D");
        eq3.setBounds(60, 200, 300, 30);
        eq3.setFont(new Font("Arial", Font.BOLD, 20));
        eq3.setForeground(PbuttonColor);

        // Input fields for first equation
        a1Field = createNumberField(50, 70);
        b1Field = createNumberField(110, 70);
        c1Field = createNumberField(170, 70);
        d1Field = createNumberField(230, 70); // Constant for first equation

        // Input fields for second equation
        a2Field = createNumberField(50, 160);
        b2Field = createNumberField(110, 160);
        c2Field = createNumberField(170, 160);
        d2Field = createNumberField(230, 160); // Constant for second equation

        // Input fields for third equation
        a3Field = createNumberField(50, 240);
        b3Field = createNumberField(110, 240);
        c3Field = createNumberField(170, 240);
        d3Field = createNumberField(230, 240); // Constant for third equation

        // Calculate button
        calculate = new RoundedButton("Calculate", new Dimension(120, 60), PbuttonColor, PFontsize);
        calculate.setBounds(240, 150, 250, 100);
        calculate.addActionListener(this);
        calculate.setFocusable(false);

        // Result display
        resultLabel = new JLabel("Results:");
        resultLabel.setBounds(50, 400, 100, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(PbuttonColor);

        resultXLabel = new JLabel("X = ");
        resultXLabel.setBounds(60, 440, 50, 30);
        resultXLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultXLabel.setForeground(PbuttonColor);

        resultX = new JTextField(10);
        resultX.setBounds(100, 440, 250, 30);
        resultX.setEditable(false);
        resultX.setFont(new Font("Arial", Font.BOLD, 20));

        resultYLabel = new JLabel("Y = ");
        resultYLabel.setBounds(60, 480, 50, 30);
        resultYLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultYLabel.setForeground(PbuttonColor);

        resultY = new JTextField(10);
        resultY.setBounds(100, 480, 250, 30);
        resultY.setEditable(false);
        resultY.setFont(new Font("Arial", Font.BOLD, 20));

        // New Z result field
        resultZ = new JTextField(10);
        resultZ.setBounds(100, 520, 250, 30);
        resultZ.setEditable(false);
        resultZ.setFont(new Font("Arial", Font.BOLD, 20));

        // New labels for Z
        resultZLabel = new JLabel("Z = ");
        resultZLabel.setBounds(60, 520, 50, 30);
        resultZLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultZLabel.setForeground(PbuttonColor);

        // Labels for displaying the equations
        equation1Label = new JLabel("");
        equation1Label.setBounds(50, 300, 400, 30);
        equation1Label.setFont(new Font("Arial", Font.BOLD, 20));
        equation1Label.setForeground(PbuttonColor);

        equation2Label = new JLabel("");
        equation2Label.setBounds(50, 330, 400, 30);
        equation2Label.setFont(new Font("Arial", Font.BOLD, 20));
        equation2Label.setForeground(PbuttonColor);

        equation3Label = new JLabel("");
        equation3Label.setBounds(50, 360, 400, 30);
        equation3Label.setFont(new Font("Arial", Font.BOLD, 20));
        equation3Label.setForeground(PbuttonColor);

        // Adding components to the panel
        solve_3_eq_panel.add(BackButtonsolve);
        solve_3_eq_panel.add(clearButton);
        solve_3_eq_panel.add(calculate);
        solve_3_eq_panel.add(eq1);
        solve_3_eq_panel.add(eq2);
        solve_3_eq_panel.add(eq3);
        solve_3_eq_panel.add(a1Field);
        solve_3_eq_panel.add(b1Field);
        solve_3_eq_panel.add(c1Field);
        solve_3_eq_panel.add(d1Field);
        solve_3_eq_panel.add(a2Field);
        solve_3_eq_panel.add(b2Field);
        solve_3_eq_panel.add(c2Field);
        solve_3_eq_panel.add(d2Field);
        solve_3_eq_panel.add(a3Field);
        solve_3_eq_panel.add(b3Field);
        solve_3_eq_panel.add(c3Field);
        solve_3_eq_panel.add(d3Field);
        solve_3_eq_panel.add(equation1Label);
        solve_3_eq_panel.add(equation2Label);
        solve_3_eq_panel.add(equation3Label);
        solve_3_eq_panel.add(resultLabel);
        solve_3_eq_panel.add(resultXLabel);
        solve_3_eq_panel.add(resultYLabel);
        solve_3_eq_panel.add(resultZLabel);
        solve_3_eq_panel.add(resultX);
        solve_3_eq_panel.add(resultY);
        solve_3_eq_panel.add(resultZ);
        solve_3_eq_panel.add(resultZLabel);
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
                a2Field.requestFocus(); // Move to the next equation's first field
            } else if (currentField == a2Field) {
                b2Field.requestFocus();
            } else if (currentField == b2Field) {
                c2Field.requestFocus();
            } else if (currentField == c2Field) {
                d2Field.requestFocus(); // Move to the next equation's first field
            } else if (currentField == d2Field) {
                a3Field.requestFocus();
            } else if (currentField == a3Field) {
                b3Field.requestFocus();
            } else if (currentField == b3Field) {
                c3Field.requestFocus(); // Move to the last field of the third equation
            } else if (currentField == c3Field) {
                d3Field.requestFocus();
            } else if (currentField == d3Field) {
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

    public static void Resetsolve() {
        solve_3_eq_panel.setVisible(false);
        BackButtonsolve.setVisible(false);
        backtoHome.setVisible(false);
    }

    public static void solve_3_eqpanelshow() {
        backtoHome.setBounds(340, 50, 100, 50);
        BackButtonsolve.setVisible(true);
        backtoHome.setVisible(true);
        solve_3_eq_panel.setVisible(true);
        homefram.add(backtoHome);
        homefram.add(BackButtonsolve);
        homefram.add(solve_3_eq_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButtonsolve) {
            clearFields();
            Resetsolve();
            equPanelshow();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == calculate) {
            solveEquations();
        }
        if (e.getSource() == backtoHome) {

            clearFields();
            Resetsolve();
            ShowCalculator();
        }
    }

    private void solveEquations() {
        try {
            double a1 = Double.parseDouble(a1Field.getText());
            double b1 = Double.parseDouble(b1Field.getText());
            double c1 = Double.parseDouble(c1Field.getText());
            double d1 = Double.parseDouble(d1Field.getText());
            double a2 = Double.parseDouble(a2Field.getText());
            double b2 = Double.parseDouble(b2Field.getText());
            double c2 = Double.parseDouble(c2Field.getText());
            double d2 = Double.parseDouble(d2Field.getText());
            double a3 = Double.parseDouble(a3Field.getText());
            double b3 = Double.parseDouble(b3Field.getText());
            double c3 = Double.parseDouble(c3Field.getText());
            double d3 = Double.parseDouble(d3Field.getText());

            // Calculate determinants
            double D = a1 * (b2 * c3 - b3 * c2) - b1 * (a2 * c3 - a3 * c2) + c1 * (a2 * b3 - a3 * b2);
            double Dx = d1 * (b2 * c3 - b3 * c2) - b1 * (d2 * c3 - d3 * c2) + c1 * (d2 * b3 - d3 * b2);
            double Dy = a1 * (d2 * c3 - d3 * c2) - d1 * (a2 * c3 - a3 * c2) + c1 * (a2 * d3 - a3 * d2);
            double Dz = a1 * (b2 * d3 - b3 * d2) - b1 * (a2 * d3 - a3 * d2) + d1 * (a2 * b3 - a3 * b2);

            // Check if determinant is zero
            if (D != 0) {
                double x = Dx / D;
                double y = Dy / D;
                double z = Dz / D;

                resultX.setText(String.format("%.2f", x));
                resultY.setText(String.format("%.2f", y));
                resultZ.setText(String.format("%.2f", z));

                // Setting equations in labels
                equation1Label.setText(String.format(" %.2fx + %.2fy + %.2fz = %.2f", a1, b1, c1, d1));
                equation2Label.setText(String.format(" %.2fx + %.2fy + %.2fz = %.2f", a2, b2, c2, d2));
                equation3Label.setText(String.format(" %.2fx + %.2fy + %.2fz = %.2f", a3, b3, c3, d3));
                playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
            } else {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultX.setText("No unique solution");
                resultY.setText("");
                resultZ.setText("");

                // Setting equations in labels for cases with no unique solution
                equation1Label.setText("Equation 1: " + a1 + "x + " + b1 + "y + " + c1 + "z = " + d1);
                equation2Label.setText("Equation 2: " + a2 + "x + " + b2 + "y + " + c2 + "z = " + d2);
                equation3Label.setText("Equation 3: " + a3 + "x + " + b3 + "y + " + c3 + "z = " + d3);
            }
        } catch (NumberFormatException ex) {
            playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
            resultX.setText("Invalid input");
            resultY.setText("");
            resultZ.setText("");
            equation1Label.setText("");
            equation2Label.setText("");
            equation3Label.setText("");
        }
        addHistory(equation1Label.getText() + "\n" + equation2Label.getText() + "\n" + equation3Label.getText() + "\n" + resultXLabel.getText() + "  " + resultX.getText() + "\n" + resultYLabel.getText() + "  " + resultY.getText() + "\n" + resultZLabel.getText() + "  " + resultZ.getText());
        updateHistoryDisplay();
    }

    private void clearFields() {
        a1Field.setText("");
        b1Field.setText("");
        c1Field.setText("");
        d1Field.setText("");
        a2Field.setText("");
        b2Field.setText("");
        c2Field.setText("");
        d2Field.setText("");
        a3Field.setText("");
        b3Field.setText("");
        c3Field.setText("");
        d3Field.setText("");
        resultX.setText("");
        resultY.setText("");
        resultZ.setText("");
    }
}
