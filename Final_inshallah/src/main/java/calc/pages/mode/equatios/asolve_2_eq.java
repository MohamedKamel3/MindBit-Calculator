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

public class asolve_2_eq implements ActionListener {

    public static RoundedButton calculate;
    public static JPanel solve_2_eq_panel = new JPanel();
    public static JLabel eq1, eq2, resultLabel, resultXLabel, resultYLabel, equation1Label, equation2Label;
    public static JButton BackButtonsolve, clearButton;
    public static JTextField a1Field, b1Field, c1Field, a2Field, b2Field, c2Field;
    public static JTextField resultX, resultY;
    public static JButton backtoHome;

    public asolve_2_eq() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);

        solve_2_eq_panel.setSize(500, 600);
        solve_2_eq_panel.setBackground(Color.decode("#101a43"));
        solve_2_eq_panel.setLayout(null);

        // Back button
        BackButtonsolve = new JButton("Back");
        BackButtonsolve.setFont(new Font("Arial", Font.BOLD, 20));
        BackButtonsolve.setForeground(PbuttonColor);
        BackButtonsolve.setBounds(-20, 3, 100, 50);
        BackButtonsolve.setContentAreaFilled(false);
        BackButtonsolve.setOpaque(false);
        BackButtonsolve.addActionListener(this);
        BackButtonsolve.setBorderPainted(false);
        BackButtonsolve.setFocusable(false);

        // CLEAR button
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setForeground(PbuttonColor);
        clearButton.setBounds(179, 310, 150, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setOpaque(false);
        clearButton.setFocusable(false);

        // Equation Labels
        eq1 = new JLabel("A1x + B1y = C");
        eq1.setBounds(60, 50, 200, 30);
        eq1.setFont(new Font("Arial", Font.BOLD, 20));
        eq1.setForeground(PbuttonColor);

        eq2 = new JLabel("A2x + B2y = C");
        eq2.setBounds(310, 50, 200, 30);
        eq2.setFont(new Font("Arial", Font.BOLD, 20));
        eq2.setForeground(PbuttonColor);

        // Input fields for first equation
        a1Field = createNumberField(50, 100);
        b1Field = createNumberField(110, 100);
        c1Field = createNumberField(170, 100);

        // Input fields for second equation
        a2Field = createNumberField(300, 100);
        b2Field = createNumberField(360, 100);
        c2Field = createNumberField(420, 100);

        // Calculate button
        calculate = new RoundedButton("Calculate", new Dimension(200, 110), PbuttonColor, PFontsize);
        calculate.setBounds(70, 170, 330, 180);
        calculate.addActionListener(this);
        calculate.setFocusable(false);

        // Result display
        resultLabel = new JLabel("Results:");
        resultLabel.setBounds(50, 420, 100, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(PbuttonColor);

        resultXLabel = new JLabel("X = ");
        resultXLabel.setBounds(60, 460, 50, 30);
        resultXLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultXLabel.setForeground(PbuttonColor);

        resultX = new JTextField(10);
        resultX.setBounds(100, 460, 250, 30);
        resultX.setEditable(false);
        resultX.setFont(new Font("Arial", Font.BOLD, 20));

        resultYLabel = new JLabel("Y = ");
        resultYLabel.setBounds(60, 500, 50, 30);
        resultYLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultYLabel.setForeground(PbuttonColor);

        resultY = new JTextField(10);
        resultY.setBounds(100, 500, 250, 30);
        resultY.setFont(new Font("Arial", Font.BOLD, 20));
        resultY.setEditable(false);

        // Labels لعرض المعادلات
        equation1Label = new JLabel("");
        equation1Label.setBounds(50, 350, 400, 30);
        equation1Label.setFont(new Font("Arial", Font.BOLD, 20));
        equation1Label.setForeground(PbuttonColor);

        equation2Label = new JLabel("");
        equation2Label.setBounds(50, 380, 400, 30);
        equation2Label.setFont(new Font("Arial", Font.BOLD, 20));
        equation2Label.setForeground(PbuttonColor);

        // Adding components to the panel
        solve_2_eq_panel.add(BackButtonsolve);
        solve_2_eq_panel.add(clearButton);
        solve_2_eq_panel.add(calculate);
        solve_2_eq_panel.add(eq1);
        solve_2_eq_panel.add(eq2);
        solve_2_eq_panel.add(a1Field);
        solve_2_eq_panel.add(b1Field);
        solve_2_eq_panel.add(c1Field);
        solve_2_eq_panel.add(a2Field);
        solve_2_eq_panel.add(b2Field);
        solve_2_eq_panel.add(c2Field);
        solve_2_eq_panel.add(equation1Label);
        solve_2_eq_panel.add(equation2Label);
        solve_2_eq_panel.add(resultLabel);
        solve_2_eq_panel.add(resultXLabel);
        solve_2_eq_panel.add(resultX);
        solve_2_eq_panel.add(resultYLabel);
        solve_2_eq_panel.add(resultY);
        solve_2_eq_panel.add(backtoHome);

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
                a2Field.requestFocus();
            } else if (currentField == a2Field) {
                b2Field.requestFocus();
            } else if (currentField == b2Field) {
                c2Field.requestFocus();
            } else if (currentField == c2Field) {
                calculateResults(); // Execute calculation here when in the last field
            }
        } else {
            if (currentField == c2Field) {
                b2Field.requestFocus();
            } else if (currentField == b2Field) {
                a2Field.requestFocus();
            } else if (currentField == a2Field) {
                c1Field.requestFocus();
            } else if (currentField == c1Field) {
                b1Field.requestFocus();
            } else if (currentField == b1Field) {
                a1Field.requestFocus();
            } else if (currentField == a1Field) {
                calculate.requestFocus();
            }
        }
    }

    private void applyNumberFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), string, offset)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), text, offset)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    // دالة للتحقق من صحة الإدخال بحيث يسمح بالأعداد العشرية وجذور الأرقام
    private boolean isValidInput(String currentText, String newText, int offset) {
        StringBuilder updatedText = new StringBuilder(currentText);
        updatedText.insert(offset, newText);

        // السماح بالأرقام، علامة الطرح، نقطة عشرية واحدة، ورمز الجذر فقط إذا كان في البداية
        return updatedText.toString().matches("-?√?\\d*(\\.\\d*)?");
    }

    // دالة لحساب قيمة الجذر في الإدخال عند الضرورة
    private double parseInput(String text) {
        if (text.startsWith("√")) {
            double number = Double.parseDouble(text.substring(1));
            return Math.sqrt(number);
        }
        return Double.parseDouble(text);
    }

    ;

    public static void calculateResults() {
        double a1 = Double.parseDouble(a1Field.getText());
        double b1 = Double.parseDouble(b1Field.getText());
        double c1 = Double.parseDouble(c1Field.getText());
        double a2 = Double.parseDouble(a2Field.getText());
        double b2 = Double.parseDouble(b2Field.getText());
        double c2 = Double.parseDouble(c2Field.getText());

        double determinant = a1 * b2 - a2 * b1;

        if (determinant != 0) {
            double x = (c1 * b2 - c2 * b1) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;

            resultX.setText(String.valueOf(x));
            resultY.setText(String.valueOf(y));

            equation1Label.setText(a1 + "x + " + b1 + "y = " + c1);
            equation2Label.setText(a2 + "x + " + b2 + "y = " + c2);
            playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
        } else {
            playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
            resultX.setText("No Solution");
            resultY.setText("");
            equation1Label.setText("");
            equation2Label.setText("");
        }
        addHistory(equation1Label.getText() + "\n" + equation2Label.getText() + "\n" + resultXLabel.getText() + "  " + resultX.getText() + "\n" + resultYLabel.getText() + "  " + resultY.getText());
        updateHistoryDisplay();
    }

    public static void Resetsolve() {
        solve_2_eq_panel.setVisible(false);
        BackButtonsolve.setVisible(false);
    }

    public static void solve_2_eq_panelshow() {
        backtoHome.setBounds(370, 3, 100, 50);
        solve_2_eq_panel.setVisible(true);
        BackButtonsolve.setVisible(true);
        homefram.add(solve_2_eq_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculate) {
            calculateResults();
        } else if (e.getSource() == clearButton) {
            a1Field.setText("");
            b1Field.setText("");
            c1Field.setText("");
            a2Field.setText("");
            b2Field.setText("");
            c2Field.setText("");
            resultX.setText("");
            resultY.setText("");
            equation1Label.setText("");
            equation2Label.setText("");
        } else if (e.getSource() == BackButtonsolve) {
            a1Field.setText("");
            b1Field.setText("");
            c1Field.setText("");
            a2Field.setText("");
            b2Field.setText("");
            c2Field.setText("");
            resultX.setText("");
            resultY.setText("");
            equation1Label.setText("");
            equation2Label.setText("");
            solve_2_eq_panel.setVisible(false);
            equPanelshow();
        } else if (e.getSource() == backtoHome) {
            a1Field.setText("");
            b1Field.setText("");
            c1Field.setText("");
            a2Field.setText("");
            b2Field.setText("");
            c2Field.setText("");
            resultX.setText("");
            resultY.setText("");
            equation1Label.setText("");
            equation2Label.setText("");
            Resetsolve();
            ShowCalculator();
        }
    }
}
