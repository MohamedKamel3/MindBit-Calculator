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

public class spherevolumepage implements ActionListener {

    public static JPanel SpherePanel = new JPanel();
    public static JLabel SphereLabel;
    public static JButton BackButton;
    public static JTextField radiusField;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton clearButton;
    public static JButton backtoHome;

    public spherevolumepage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // Setup the Sphere interface
        SpherePanel.setBounds(5, 60, 450, 400);
        SpherePanel.setLayout(null);
        SpherePanel.setBackground(Color.decode("#101a43"));

        SphereLabel = new JLabel("Sphere Volume");
        SphereLabel.setBounds(135, 23, 250, 40);
        SphereLabel.setFont(new Font("Arial", Font.BOLD, 30));
        SphereLabel.setForeground(Color.WHITE);

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
        clearButton.setBounds(270, 240, 100, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setVisible(false);
        clearButton.setFocusable(false);

        // Input field for radius
        radiusField = new JTextField();
        radiusField.setBounds(150, 100, 150, 30);
        radiusField.setFont(new Font("Arial", Font.BOLD, 22));
        radiusField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Allow only digits and dot (for decimal values)
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

        JLabel radiusLabel = new JLabel("Radius:");
        radiusLabel.setBounds(80, 100, 100, 30);
        radiusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        radiusLabel.setForeground(Color.WHITE);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 150, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFocusable(false);

        // Result display
        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 200, 400, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 19));
        resultLabel.setForeground(Color.WHITE);

        SpherePanel.add(SphereLabel);
        SpherePanel.add(BackButton);
        SpherePanel.add(radiusField);
        SpherePanel.add(calculateButton);
        SpherePanel.add(resultLabel);
        SpherePanel.add(radiusLabel);
        SpherePanel.add(clearButton);
        SpherePanel.add(backtoHome);
    }

    public static void showSpherePage() {
        homefram.setSize(450, 400);
        backtoHome.setBounds(10, 240, 100, 50);
        SpherePanel.setVisible(true);
        SphereLabel.setVisible(true);
        BackButton.setVisible(true);
        radiusField.setVisible(true);
        calculateButton.setVisible(true);
        resultLabel.setVisible(true);
        homefram.add(SpherePanel);
    }

    public static void resetSpherePage() {
        SpherePanel.setVisible(false);
        SphereLabel.setVisible(false);
        BackButton.setVisible(false);
        radiusField.setVisible(false);
        calculateButton.setVisible(false);
        resultLabel.setVisible(false);
    }

    private void clearFields() {
        radiusField.setText("");
        resultLabel.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            clearFields();
            resetSpherePage();
            Volumepage.showvolpage();  // Go back to the main page
        } else if (e.getSource() == clearButton) {
            clearFields(); // Clear fields
        } else if (e.getSource() == calculateButton) {
            // Calculate volume
            try {
                double radius = Double.parseDouble(radiusField.getText());
                if (radius > 0) {
                    double volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
                    resultLabel.setText("Volume: " + volume);
                    clearButton.setVisible(true);
                    playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
                } else {
                    playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                    resultLabel.setText("Wrong Radius");
                    clearButton.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("Invalid input!");
            }
            addHistory("Sphere " + resultLabel.getText());
            updateHistoryDisplay();
        }
        if (e.getSource() == backtoHome) {
            clearFields();
            resetSpherePage();
            ShowCalculator();
        }
    }
}
