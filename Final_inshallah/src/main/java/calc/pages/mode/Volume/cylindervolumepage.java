package calc.pages.mode.Volume;

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
import static calc.pages.HistoryPage.addHistory;
import static calc.pages.HistoryPage.updateHistoryDisplay;
import static calc.tools.backtohome.backtohomee;

public class cylindervolumepage implements ActionListener {

    public static JPanel CylinderPanel = new JPanel();
    public static JLabel CylinderLabel;
    public static JButton BackButton;
    public static JTextField radiusField;
    public static JTextField heightField;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton clearButton;
    public static JButton backtoHome;

    public cylindervolumepage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // Setup the Cylinder interface
        CylinderPanel.setBounds(5, 60, 450, 400);
        CylinderPanel.setLayout(null);
        CylinderPanel.setBackground(Color.decode("#101a43"));

        CylinderLabel = new JLabel("Cylinder Volume");
        CylinderLabel.setBounds(135, 23, 250, 40);
        CylinderLabel.setFont(new Font("Arial", Font.BOLD, 30));
        CylinderLabel.setForeground(Color.WHITE);

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

        // CLEAR button
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setForeground(PbuttonColor);
        clearButton.setContentAreaFilled(false);
        clearButton.setBounds(300, 300, 100, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setVisible(false);
        clearButton.setFocusable(false);

        // Input fields for radius and height
        radiusField = new JTextField();
        radiusField.setBounds(150, 100, 150, 30);
        radiusField.setFont(new Font("Arial", Font.BOLD, 22));
        radiusField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume(); // Ignore non-numeric input
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    heightField.requestFocus(); // Move to heightField on Enter key press
                }
            }
        });

        JLabel radiusLabel = new JLabel("Radius:");
        radiusLabel.setBounds(80, 100, 100, 30);
        radiusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        radiusLabel.setForeground(Color.WHITE);

        heightField = new JTextField();
        heightField.setBounds(150, 150, 150, 30);
        heightField.setFont(new Font("Arial", Font.BOLD, 22));
        heightField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume(); // Ignore non-numeric input
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateButton.doClick(); // Trigger calculation on Enter key press
                }
            }
        });

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setBounds(80, 150, 100, 30);
        heightLabel.setFont(new Font("Arial", Font.BOLD, 16));
        heightLabel.setForeground(Color.WHITE);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 200, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFocusable(false);

        // Result display
        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 250, 400, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 19));
        resultLabel.setForeground(Color.WHITE);

        CylinderPanel.add(CylinderLabel);
        CylinderPanel.add(BackButton);
        CylinderPanel.add(radiusField);
        CylinderPanel.add(heightField);
        CylinderPanel.add(calculateButton);
        CylinderPanel.add(resultLabel);
        CylinderPanel.add(radiusLabel);
        CylinderPanel.add(heightLabel);
        CylinderPanel.add(clearButton);
        CylinderPanel.add(backtoHome);

    }

    public static void showCylinderPage() {
        homefram.setSize(450, 450);
        backtoHome.setBounds(10, 300, 100, 50);
        CylinderPanel.setVisible(true);
        CylinderLabel.setVisible(true);
        BackButton.setVisible(true);
        radiusField.setVisible(true);
        heightField.setVisible(true);
        calculateButton.setVisible(true);
        resultLabel.setVisible(true);
        homefram.add(CylinderPanel);
        homefram.repaint();

    }

    public static void resetCylinderPage() {
        CylinderLabel.setVisible(false);
        BackButton.setVisible(false);
        radiusField.setVisible(false);
        heightField.setVisible(false);
        calculateButton.setVisible(false);
        resultLabel.setVisible(false);
        CylinderPanel.setVisible(false);
    }

    public static void clearFields() {
        radiusField.setText("");
        heightField.setText("");
        resultLabel.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            clearFields();
            resetCylinderPage();
            Volumepage.showvolpage(); // Go back to the main page
        } else if (e.getSource() == backtoHome) {
            clearFields();
            resetCylinderPage();
            ShowCalculator();
        } else if (e.getSource() == clearButton) {
            clearFields(); // Clear fields
        } else if (e.getSource() == calculateButton) {
            // Calculate volume
            try {
                double radius = Double.parseDouble(radiusField.getText());
                double height = Double.parseDouble(heightField.getText());
                if (radius > 0) {
                    double volume = Math.PI * Math.pow(radius, 2) * height;
                    resultLabel.setText("Volume: " + volume);
                    clearButton.setVisible(true);
                    playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
                } else {
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resultLabel.setText("Wrong Radius ");
                    clearButton.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Invalid input!");
            }
            addHistory("Cylinder " + resultLabel.getText());
            updateHistoryDisplay();
        }
    }
}
