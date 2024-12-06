package calc.tools;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CustomKeyEvent {

    private JTextField textfield;
    private JLabel operationLabel;
    private String expression = "";
    private double ans;

    public CustomKeyEvent() {
        // إعداد واجهة المستخدم
        JFrame frame = new JFrame("Calculator");
        textfield = new JTextField();
        operationLabel = new JLabel();

        // إعداد واجهة المستخدم
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(textfield, java.awt.BorderLayout.NORTH);
        frame.add(operationLabel, java.awt.BorderLayout.CENTER);

        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // إضافة KeyListener إلى textfield
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
                        expression = String.valueOf(result); // تحديث التعبير إلى النتيجة
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

            @Override
            public void keyPressed(KeyEvent e) {
                int caretPosition = textfield.getCaretPosition();

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    // حذف الحرف السابق عند الضغط على Backspace
                    if (caretPosition > 0) {
                        expression = expression.substring(0, caretPosition - 1) + expression.substring(caretPosition);
                        textfield.setText(expression);
                        operationLabel.setText(expression);
                        textfield.setCaretPosition(caretPosition - 1); // تحديث موضع المؤشر
                    }
                    e.consume(); // منع الحدث الافتراضي
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    // تحريك المؤشر لليسار
                    if (caretPosition > 0) {
                        textfield.setCaretPosition(caretPosition - 1);
                    }
                    e.consume(); // منع الحدث الافتراضي
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    // تحريك المؤشر لليمين
                    if (caretPosition < expression.length()) {
                        textfield.setCaretPosition(caretPosition + 1);
                    }
                    e.consume(); // منع الحدث الافتراضي
                }
            }
        });
    }

    private double evaluateExpression(String expr) {
        // هنا يمكن أن تضيف منطق لتقييم التعبير الرياضي
        // كمثال، سأستخدم Double.parseDouble (تأكد من تعديلها حسب احتياجاتك)
        return Double.parseDouble(expr);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomKeyEvent::new);
    }
}
