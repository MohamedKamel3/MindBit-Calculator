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

public class cubevolumepage implements ActionListener {

    public static JPanel CubePanel = new JPanel();
    public static JLabel CubeLabel;
    public static JButton BackButton;
    public static JTextField sideField;
    public static JButton calculateButton;
    public static JLabel resultLabel;
    public static JButton clearButton;
    public static JButton backtoHome;

    public cubevolumepage() {

        backtoHome = backtohomee();
        backtoHome.addActionListener(this);
        backtoHome.setFocusable(false);

        // Setup the Cube interface
        CubePanel.setBounds(5, 60, 450, 400);
        CubePanel.setLayout(null);
        CubePanel.setBackground(Color.decode("#101a43"));

        CubeLabel = new JLabel("Cube Volume");
        CubeLabel.setBounds(135, 23, 250, 40);
        CubeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        CubeLabel.setForeground(Color.WHITE);

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
        clearButton.setBounds(300, 250, 100, 50);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setOpaque(false);
        clearButton.setVisible(false);
        clearButton.setFocusable(false);

        // Input field for side length with KeyListener
        sideField = new JTextField();
        sideField.setBounds(150, 100, 150, 30);
        sideField.setFont(new Font("Arial", Font.BOLD, 22));
        sideField.addKeyListener(new KeyAdapter() {
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
                    calculateVolume(); // Calculate volume on Enter key press
                }
            }
        });

        JLabel sideLabel = new JLabel("Side:");
        sideLabel.setBounds(80, 100, 100, 30);
        sideLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sideLabel.setForeground(Color.WHITE);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 150, 150, 30);
        calculateButton.addActionListener(this);
        calculateButton.setFocusable(false);

        // Result display
        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 200, 400, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);

        CubePanel.add(CubeLabel);
        CubePanel.add(BackButton);
        CubePanel.add(sideField);
        CubePanel.add(calculateButton);
        CubePanel.add(resultLabel);
        CubePanel.add(sideLabel);
        CubePanel.add(clearButton);
        CubePanel.add(backtoHome);
    }

    public static void showCubePage() {
        homefram.setSize(450, 450);
        backtoHome.setBounds(330, 10, 100, 50);
        CubePanel.setVisible(true);
        CubeLabel.setVisible(true);
        BackButton.setVisible(true);
        sideField.setVisible(true);
        calculateButton.setVisible(true);
        resultLabel.setVisible(true);
        homefram.add(CubePanel);
    }

    public static void resetCubePage() {
        CubePanel.setVisible(false);
        CubeLabel.setVisible(false);
        BackButton.setVisible(false);
        sideField.setVisible(false);
        calculateButton.setVisible(false);
        resultLabel.setVisible(false);
    }

    public void clearFields() {
        sideField.setText("");
        resultLabel.setText("");
    }

    private void calculateVolume() {
        // Calculate volume
        try {
            double side = Double.parseDouble(sideField.getText());
            if (side > 0) {
                double volume = Math.pow(side, 3);
                resultLabel.setText("Volume: " + volume);
                clearButton.setVisible(true);
                playSound("Final_inshallah/src/main/java/Sounds/correct.wav");
            } else {
                playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
                resultLabel.setText("wrong Side length");
                clearButton.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            playSound("Final_inshallah/src/main/java/Sounds/Wrong.wav");
            resultLabel.setText("Invalid input!");
        }
        addHistory("Cube " + resultLabel.getText());
        updateHistoryDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            clearFields();
            resetCubePage();
            Volumepage.showvolpage(); // Go back to the volume main page
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == calculateButton) {
            calculateVolume(); // Calculate volume on Calculate button press
        } else if (e.getSource() == backtoHome) {
            clearFields();
            resetCubePage();
            ShowCalculator();
        }
    }
}
