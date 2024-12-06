package calc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static calc.constants.myFont;
import static calc.logic.equal.evaluateExpression;
import calc.pages.HistoryPage;
import static calc.pages.HistoryPage.addHistory;
import static calc.pages.HistoryPage.showHistory;
import static calc.pages.HistoryPage.updateHistoryDisplay;
import calc.pages.info;
import static calc.pages.info.showinfo;
import calc.pages.mode.Area.AreaPage;
import calc.pages.mode.Area.CircleAreaPage;
import calc.pages.mode.Area.RectangleAreaPage;
import calc.pages.mode.Area.SquareAreaPage;
import calc.pages.mode.Area.TriangleAreaPage;
import calc.pages.mode.ModePage;
import static calc.pages.mode.ModePage.BackButton;
import static calc.pages.mode.ModePage.ModPanel;
import static calc.pages.mode.ModePage.ModPanelshow;
import static calc.pages.mode.ModePage.modLabel;
import calc.pages.mode.Perimeter.CirclePage;
import calc.pages.mode.Perimeter.Perimeterpage;
import calc.pages.mode.Perimeter.RectanglePage;
import calc.pages.mode.Perimeter.SquarePage;
import calc.pages.mode.Perimeter.TrianglePage;
import calc.pages.mode.Volume.Volumepage;
import calc.pages.mode.Volume.cubevolumepage;
import calc.pages.mode.Volume.cylindervolumepage;
import calc.pages.mode.Volume.spherevolumepage;
import calc.pages.mode.equatios.asolve_2_eq;
import calc.pages.mode.equatios.bsolve_3_eq;
import calc.pages.mode.equatios.equationpage;
import calc.pages.mode.equatios.equations_X2;
import calc.pages.mode.equatios.equations_X3;

public final class Calculator implements ActionListener {

    public static JFrame homefram;
    public static JTextField textfield;
    public static String framname = "Calculator";
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[20];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, delButton, clrButton;
    JButton open, close, factorial, sqrt, modules, sinButton, cosButton, tanButton, power, ansButton;
    public static JButton equButton, historyButton, info, modeButton;
    public static JButton negButton, logButton, lnButton, piButton, eButton;
    public static JPanel homepanel;
    public static JLabel operationLabel; // لعرض العملية الرياضية
    double ans = 0;
    String expression = ""; // لتخزين العملية المدخلة وعرضها

    Calculator() {

        bsolve_3_eq p = new bsolve_3_eq();
        equations_X3 pa = new equations_X3();
        equations_X2 pag = new equations_X2();
        equationpage page = new equationpage();
        asolve_2_eq pagee = new asolve_2_eq();
        ModePage pageee = new ModePage();
        HistoryPage pageeee = new HistoryPage();
        Perimeterpage pageeeee = new Perimeterpage();
        CirclePage pageeeeeee = new CirclePage();
        RectanglePage pageeeeee = new RectanglePage();
        SquarePage pageeeeeeeeee = new SquarePage();
        TrianglePage pageeeeeeeee = new TrianglePage();
        AreaPage pageeeeeeeeeeeeeeeee = new AreaPage();
        RectangleAreaPage pageeeeeeeeeeeeeeeeee = new RectangleAreaPage();
        SquareAreaPage pageeeeeeeeeeeeeeeeeee = new SquareAreaPage();
        TriangleAreaPage pageeeeeeeeeeeeeeeeeeeee = new TriangleAreaPage();
        CircleAreaPage pageeeeeeeeeeeeeeeeeeeeee = new CircleAreaPage();
        Volumepage pageeeeeeeeeee = new Volumepage();
        cubevolumepage pageeeeeeeeeeee = new cubevolumepage();
        cylindervolumepage pageeeeeeeeeeeee = new cylindervolumepage();
        spherevolumepage pageeeeeeeeeeeeeeeeeeee = new spherevolumepage();
        info pageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee = new info();

        // Initialize the expression
        homefram = new JFrame(framname);
        homefram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homefram.setSize(500, 700);
        homefram.setLayout(null);
        homefram.setResizable(false);
        homefram.getContentPane().setBackground(Color.decode("#101a43"));

        // حقل النص لعرض النتائج
        textfield = new JTextField();
        textfield.setBounds(50, 35, 400, 40);
        textfield.setFont(myFont);
        textfield.setEditable(true);
        textfield.setFocusable(true);
        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_BACK_SPACE) {
                    String string = textfield.getText();
                    int caretPosition = textfield.getCaretPosition();

                    if (caretPosition > 0) {
                        // تحقق إذا كانت هناك دالة في النص قبل موقع المؤشر واحذفها بالكامل
                        if (caretPosition >= 4 && string.substring(caretPosition - 4, caretPosition).equals("log(")) {
                            expression = string.substring(0, caretPosition - 4) + string.substring(caretPosition);
                            caretPosition -= 4;
                        } else if (caretPosition >= 4 && string.substring(caretPosition - 4, caretPosition).equals("ln (")) {
                            expression = string.substring(0, caretPosition - 4) + string.substring(caretPosition);
                            caretPosition -= 4;
                        } else if (caretPosition >= 2 && string.substring(caretPosition - 2, caretPosition).equals("√(")) {
                            expression = string.substring(0, caretPosition - 2) + string.substring(caretPosition);
                            caretPosition -= 4;
                        } else if (caretPosition >= 4 && (string.substring(caretPosition - 4, caretPosition).equals("sin(")
                                || string.substring(caretPosition - 4, caretPosition).equals("cos(")
                                || string.substring(caretPosition - 4, caretPosition).equals("tan("))) {
                            expression = string.substring(0, caretPosition - 4) + string.substring(caretPosition);
                            caretPosition -= 4;
                        } else {
                            // احذف الحرف السابق مباشرة إذا لم يكن هناك دالة
                            expression = string.substring(0, caretPosition - 1) + string.substring(caretPosition);
                            caretPosition -= 1;
                        }

                        // تحديث النص والموقع
                        textfield.setText(expression);
                        operationLabel.setText(expression);
                        textfield.setCaretPosition(caretPosition);
                    }
                }
            }
        });

        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();

                // السماح فقط للأرقام والعمليات الحسابية وبعض الرموز
                if (Character.isDigit(keyChar) || "+-*/().%!".indexOf(keyChar) != -1) {
                    // الحصول على موضع المؤشر الحالي
                    int caretPosition = textfield.getCaretPosition();
                    // إدراج الأحرف في موضع المؤشر
                    expression = expression.substring(0, caretPosition) + keyChar + expression.substring(caretPosition);
                    textfield.setText(expression);
                    operationLabel.setText(expression);
                    textfield.setCaretPosition(caretPosition + 1); // تحديث موضع المؤشر بعد الإضافة
                    e.consume(); // منع الحدث الافتراضي
                } else if (keyChar == KeyEvent.VK_ENTER) {
                    // حساب العملية عند الضغط على Enter
                    try {
                        double result = evaluateExpression(expression);
                        textfield.setText(String.valueOf(result));
                        operationLabel.setText(expression + " = ");
                        ans = result; // تخزين النتيجة في المتغير ans
                        addHistory(expression + " = " + result);
                        updateHistoryDisplay();
                        expression = String.valueOf(result);
                    } catch (Exception ex) {
                        textfield.setText("Error");
                        operationLabel.setText("Invalid expression");
                        expression = "";
                    }
                    e.consume(); // منع الحدث الافتراضي
                } else {
                    // تجاهل الإدخالات غير المسموح بها
                    e.consume();
                }
            }

        });
        // التسمية لعرض العملية الرياضية
        operationLabel = new JLabel();
        operationLabel.setBounds(50, 8, 400, 40);
        operationLabel.setFont(new Font("Arial", Font.BOLD, 15));
        operationLabel.setForeground(Color.WHITE);

        // الأزرار
        addButton = createColoredButton("+", "#b567a8", "Final_inshallah/src/main/java/Sounds/click.wav");
        subButton = createColoredButton("-", "#b567a8", "Final_inshallah/src/main/java/Sounds/click.wav");
        mulButton = createColoredButton("*", "#b567a8", "Final_inshallah/src/main/java/Sounds/click.wav");
        divButton = createColoredButton("/", "#b567a8", "Final_inshallah/src/main/java/Sounds/click.wav");
        negButton = createColoredButton("+/-", "#b567a8", "Final_inshallah/src/main/java/Sounds/click.wav");
        decButton = createColoredButton(".", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        delButton = createColoredButton("←", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        clrButton = createColoredButton("C", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        open = createColoredButton("(", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        close = createColoredButton(")", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        factorial = createColoredButton("!", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        sqrt = createColoredButton("√", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        modules = createColoredButton("%", "#b567a8", "Final_inshallah/src/main/java/Sounds/click.wav");
        sinButton = createColoredButton("sin", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        cosButton = createColoredButton("cos", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        tanButton = createColoredButton("tan", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        power = createColoredButton("X^N", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        ansButton = createColoredButton("ANS", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        logButton = createColoredButton("log", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        lnButton = createColoredButton("ln ", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        eButton = createColoredButton("e", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        modeButton = createColoredButton("mode", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");
        info = createColoredButton("info", "#5fc5f0", "Final_inshallah/src/main/java/Sounds/click.wav");

        equButton = new JButton("=");
        equButton.setBounds(305, 595, 140, 50);
        equButton.setFont(new Font("Arial", Font.BOLD, 20));
        equButton.setFocusable(false);
        equButton.setBackground(Color.decode("#b567a8"));
        equButton.setForeground(Color.WHITE);

        // زر التاريخ
        historyButton = new JButton("History");
        historyButton.setBounds(305, 85, 140, 50);
        historyButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        historyButton.setFocusable(false);
        historyButton.addActionListener(this); // إضافة مستمع للأحداث
        historyButton.setBackground(Color.decode("#b567a8")); // لون الخلفية
        historyButton.setForeground(Color.WHITE); // لون النص

        // إضافة مستمع للأحداث لأزرار العمليات
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        decButton.addActionListener(this);
        equButton.addActionListener(this);
        delButton.addActionListener(this);
        clrButton.addActionListener(this);
        open.addActionListener(this);
        close.addActionListener(this);
        factorial.addActionListener(this);
        sqrt.addActionListener(this);
        modules.addActionListener(this);
        sinButton.addActionListener(this);
        cosButton.addActionListener(this);
        tanButton.addActionListener(this);
        power.addActionListener(this);
        ansButton.addActionListener(this);
        logButton.addActionListener(this);
        lnButton.addActionListener(this);
        negButton.addActionListener(this);
        eButton.addActionListener(this);
        modeButton.addActionListener(this);
        info.addActionListener(this);

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = ansButton;
        functionButtons[7] = delButton;
        functionButtons[8] = clrButton;
        functionButtons[10] = open;
        functionButtons[11] = close;
        functionButtons[12] = factorial;
        functionButtons[13] = modules;
        functionButtons[14] = sqrt;
        functionButtons[15] = sinButton;
        functionButtons[16] = cosButton;
        functionButtons[17] = tanButton;
        functionButtons[18] = power;
        functionButtons[19] = info;
        addButton.setBorder(null); // إزالة الفريم
        subButton.setBorder(null); // إزالة الفريم
        mulButton.setBorder(null); // إزالة الفريم
        divButton.setBorder(null); // إزالة الفريم
        equButton.setBorder(null); // إزالة الفريم
        clrButton.setBorder(null); // إزالة الفريم
        delButton.setBorder(null); // إزالة الفريم
        factorial.setBorder(null); // إزالة الفريم
        sqrt.setBorder(null); // إزالة الفريم
        modeButton.setBorder(null); // إزالة الفريم
        historyButton.setBorder(null);
        info.setBorder(null);
        decButton.setBorder(null);
        modules.setBorder(null);
        open.setBorder(null);
        close.setBorder(null);
        sinButton.setBorder(null);
        cosButton.setBorder(null);
        tanButton.setBorder(null);
        power.setBorder(null);
        ansButton.setBorder(null);
        logButton.setBorder(null);
        lnButton.setBorder(null);
        eButton.setBorder(null);
        modeButton.setBorder(null);
        negButton.setBorder(null);

        // إعداد أزرار الأرقام وإضافة مستمع للأحداث
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createColoredButton(String.valueOf(i), "#eeeeee", "Final_inshallah/src/main/java/Sounds/click.wav");
            numberButtons[i].setBorder(null);
            numberButtons[i].addActionListener(this);
        }

        homepanel = new JPanel();
        homepanel.setBackground(Color.decode("#101a43")); // تغيير لون الخلفية
        homepanel.setBounds(50, 150, 400, 500);
        homepanel.setLayout(new GridLayout(7, 5, 20, 20));

        homepanel.add(logButton);
        homepanel.add(lnButton);
        homepanel.add(eButton);
        homepanel.add(modeButton);
        homepanel.add(info);

        homepanel.add(factorial);
        homepanel.add(sqrt);
        homepanel.add(power);
        homepanel.add(delButton);
        homepanel.add(clrButton);

        homepanel.add(sinButton);
        homepanel.add(cosButton);
        homepanel.add(tanButton);
        homepanel.add(open);
        homepanel.add(close);

        homepanel.add(numberButtons[7]);
        homepanel.add(numberButtons[8]);
        homepanel.add(numberButtons[9]);
        homepanel.add(negButton);
        homepanel.add(modules);

        homepanel.add(numberButtons[4]);
        homepanel.add(numberButtons[5]);
        homepanel.add(numberButtons[6]);
        homepanel.add(mulButton);
        homepanel.add(divButton);

        homepanel.add(numberButtons[1]);
        homepanel.add(numberButtons[2]);
        homepanel.add(numberButtons[3]);
        homepanel.add(addButton);
        homepanel.add(subButton);

        homepanel.add(decButton);
        homepanel.add(numberButtons[0]);
        homepanel.add(ansButton);

        homefram.add(equButton);
        homefram.add(homepanel); // إضافة زر الخصائص
        homefram.add(historyButton); // إضافة زر التاريخ
        homefram.add(textfield);
        homefram.add(operationLabel);
        homefram.setLocationRelativeTo(null);

        homefram.setVisible(true);

    }

    public JButton createColoredButton(String text, String colorCode, String soundFile) {
        JButton button = createCircularButton(text);
        button.setBackground(Color.decode(colorCode));
        button.addActionListener(e -> {
            playSound(soundFile); // تشغيل الصوت المخصص
        });
        return button;
    }

    // إنشاء زر دائري
    public JButton createCircularButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.LIGHT_GRAY); // عند الضغط
                } else {
                    g.setColor(getBackground());
                }
                g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
                super.paintComponent(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50); // جعل الزر دائري
            }
        };
        button.setFont(myFont);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        return button;
    }

    public static void playSound(String soundFile) {
        try {
            // الحصول على الملف الصوتي
            File file = new File(soundFile);

            // التحقق من وجود الملف قبل محاولة تشغيله
            if (file.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("File not found: " + file.getAbsolutePath());
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio format.");
        } catch (IOException e) {
            System.out.println("Error reading the audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Audio line unavailable.");
        }
    }

    private String trigOperator = ""; // Variable to store the trigonometric operator

    @SuppressWarnings("unused")
    private void TrigonometricButtonAction(java.awt.event.ActionEvent evt) {
        // Store the selected trigonometric operation
        trigOperator = evt.getActionCommand();
        expression += trigOperator + "("; // Append the function to the expression
        operationLabel.setText(expression);
        textfield.setText(""); // Clear the text field for the user to enter the number

    }

    public static void ShowCalculator() {
        homefram.setSize(500, 700);
        textfield.setVisible(true);
        operationLabel.setVisible(true);
        homepanel.setVisible(true);
        info.setVisible(true);
        equButton.setVisible(true);
        historyButton.setVisible(true);
        modeButton.setVisible(true);
    }

    public static void HideCalculator() {
        textfield.setVisible(false);
        operationLabel.setVisible(false);
        homepanel.setVisible(false);
        info.setVisible(false);
        equButton.setVisible(false);
        historyButton.setVisible(false);
        modeButton.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modeButton) {
            HideCalculator();
            ModPanelshow();
            modLabel.setVisible(true);
            BackButton.setVisible(true);
            ModPanel.setVisible(true);
        }

        // استجابة زر التاريخ
        if (e.getSource() == historyButton) {
            HideCalculator();
            showHistory();
            // عرض تاريخ الحسابات
        }

        // استجابة أزرار العمليات الأساسية
        if (e.getSource() == info) {
            HideCalculator();
            showinfo();
        }

        String command = e.getActionCommand();

        // Append numbers to the expression
        if (command.matches("[0-9]")) {
            int caretPosition = textfield.getCaretPosition();
            expression = expression.substring(0, caretPosition) + command + expression.substring(caretPosition);
            textfield.setText(expression);
            operationLabel.setText(expression);
            textfield.setCaretPosition(caretPosition + 1); // Move caret after the entered number
        }

        // Append operators with spaces for easy parsing
        if ("+-*/^%".contains(command)) {
            int caretPosition = textfield.getCaretPosition();
            expression = expression.substring(0, caretPosition) + " " + command + " " + expression.substring(caretPosition);
            textfield.setText(expression);
            operationLabel.setText(expression);
            textfield.setCaretPosition(caretPosition + 3); // Move caret after the operator
        }

        // Power
        Object source = e.getSource();
        if (source == power && !textfield.getText().isEmpty()) {
            expression += "^";
            textfield.setText(expression);
        }
        // Factorial
        if (e.getSource() == factorial && !textfield.getText().isEmpty()) {
            expression += "!";
            textfield.setText(expression);
            operationLabel.setText(expression); // update display
        }

        if (command.equals("√")) {
            expression += "√("; // Append "√" for square root
            textfield.setText(expression);
            operationLabel.setText(expression);
        }

        if (command.equals("sin") || command.equals("cos") || command.equals("tan")) {
            expression += command + "(";
            textfield.setText(expression);
            operationLabel.setText(expression);
        }
        if (command.equals("log")) {
            expression += command + "(";
            textfield.setText(expression);
            operationLabel.setText(expression);
        }
        if (command.equals("ln ")) {
            expression += command + "(";
            operationLabel.setText(expression);
            textfield.setText(expression);
        }
        if (command.equals("=")) {
            try {
                double result = evaluateExpression(expression);
                textfield.setText(String.valueOf(result));
                operationLabel.setText(expression + " = ");
                ans = result; // Store the result in ans
                addHistory(expression + " = " + result);
                updateHistoryDisplay();
                expression = String.valueOf(result);
            } catch (Exception ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                textfield.setText("Error");
                operationLabel.setText("Invalid expression");
                expression = "";
            }
        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
            expression = ""; // تفريغ العملية
            operationLabel.setText(expression);
        }

        if (e.getSource() == delButton) {
            String string = textfield.getText();
            int caretPosition = textfield.getCaretPosition();

            if (caretPosition > 0) {
                // تحقق إذا كانت هناك دالة في النص قبل موقع المؤشر واحذفها بالكامل
                if (caretPosition >= 4 && string.substring(caretPosition - 4, caretPosition).equals("log(")) {
                    expression = string.substring(0, caretPosition - 4) + string.substring(caretPosition);
                    caretPosition -= 4;
                } else if (caretPosition >= 4 && string.substring(caretPosition - 4, caretPosition).equals("ln (")) {
                    expression = string.substring(0, caretPosition - 4) + string.substring(caretPosition);
                    caretPosition -= 4;
                } else if (caretPosition >= 2 && string.substring(caretPosition - 2, caretPosition).equals("√(")) {
                    expression = string.substring(0, caretPosition - 2) + string.substring(caretPosition);
                    caretPosition -= 4;
                } else if (caretPosition >= 4 && (string.substring(caretPosition - 4, caretPosition).equals("sin(")
                        || string.substring(caretPosition - 4, caretPosition).equals("cos(")
                        || string.substring(caretPosition - 4, caretPosition).equals("tan("))) {
                    expression = string.substring(0, caretPosition - 4) + string.substring(caretPosition);
                    caretPosition -= 4;
                } else {
                    // احذف الحرف السابق مباشرة إذا لم يكن هناك دالة
                    expression = string.substring(0, caretPosition - 1) + string.substring(caretPosition);
                    caretPosition -= 1;
                }

                // تحديث النص والموقع
                textfield.setText(expression);
                operationLabel.setText(expression);
                textfield.setCaretPosition(caretPosition);
            }
        }

        if (e.getSource() == open) {
            int caretPosition = textfield.getCaretPosition(); // Get the current caret position
            expression = expression.substring(0, caretPosition) + "(" + expression.substring(caretPosition); // Insert the '(' at the caret position
            textfield.setText(expression); // Update the textfield with the new expression
            operationLabel.setText(expression); // Update the operation label
            textfield.setCaretPosition(caretPosition + 1); // Move caret after the inserted parenthesis
        }

        if (e.getSource() == close) {
            expression += ")"; // Append closing parenthesis directly to the expression
            textfield.setText(expression); // Display updated expression
            operationLabel.setText(expression); // Update the operation label if necessary
        }

        if (e.getSource() == decButton) {
            if (!textfield.getText().contains(".")) { // Check if decimal point is already present
                textfield.setText(textfield.getText() + ".");
                expression += "."; // Add decimal point to expression as well
                operationLabel.setText(expression);
            }
        }

        if (e.getSource() == ansButton) {
            // Check if the last entry in expression is not the answer
            if (!expression.endsWith(String.valueOf(ans))) {
                expression += ans;
                textfield.setText(expression);
                operationLabel.setText(expression);
            }
        }
        if (e.getSource() == negButton) {
            String[] terms = expression.split("(?<=[-+*/()])|(?=[-+*/()])"); // تقسيم التعبير إلى مكونات
            StringBuilder newExpression = new StringBuilder();
            boolean lastValueNegated = false;

            for (int i = 0; i < terms.length; i++) {
                String term = terms[i].trim();

                // تحقق إذا كان هذا هو آخر رقم في التعبير ولم يتم تغييره بعد
                if (i == terms.length - 1 && term.matches("-?\\d+(\\.\\d+)?") && !lastValueNegated) {
                    // جعل القيمة سالبة أو إيجابية حسب الحاجة
                    if (term.startsWith("-")) {
                        term = term.substring(1); // اجعلها موجبة إذا كانت سالبة
                    } else {
                        term = "-" + term; // اجعلها سالبة
                    }
                    lastValueNegated = true;
                }

                newExpression.append(term); // إعادة بناء التعبير
            }

            // تحديث التعبير والنصوص
            expression = newExpression.toString();
            textfield.setText(expression);
            operationLabel.setText(expression);
        }

        if (e.getSource() == eButton) {
            expression += "e"; // Append closing parenthesis directly to the expression
            textfield.setText(expression); // Display updated expression
            operationLabel.setText(expression); // Update the operation label if necessary
        }
    }

    public double getAns() {
        return ans;
    }
}
