package calc.pages.mode.equatios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static calc.Calculator.homefram;
import static calc.constants.PFontsize;
import static calc.pages.mode.ModePage.BackButton;
import static calc.pages.mode.ModePage.ModPanel;
import static calc.pages.mode.ModePage.modLabel;
import static calc.pages.mode.equatios.asolve_2_eq.solve_2_eq_panelshow;
import static calc.pages.mode.equatios.bsolve_3_eq.solve_3_eqpanelshow;
import static calc.pages.mode.equatios.equations_X2.equations_X2panelshow;
import static calc.pages.mode.equatios.equations_X3.equations_X3panelshow;
import calc.tools.RoundedButton;

public class equationpage implements ActionListener {

    public static JLabel equLabel;
    public static JPanel equPanel = new JPanel();
    public static JButton BackButtonequa;
    public static RoundedButton solve_2_equ;
    public static RoundedButton equations_X2;
    public static RoundedButton equations_X3;
    public static RoundedButton solve_3_eq;

    public equationpage() {
        int width = 200;
        int height = 80;

        // Set up the main panel
        equPanel.setBackground(Color.decode("#101a43")); // Change background color
        equPanel.setBounds(60, 60, 400, 500);
        equPanel.setLayout(new GridLayout(4, 1, -20, -20));

        // Create back button
        BackButtonequa = new JButton("Back");
        BackButtonequa.setFont(new Font("Arial", Font.BOLD, 20));
        BackButtonequa.setForeground(Color.white); // Set font size for the back button
        BackButtonequa.setBounds(10, 13, 100, 50); // Position the back button
        BackButtonequa.setContentAreaFilled(false);
        BackButtonequa.setOpaque(false);
        BackButtonequa.addActionListener(this);
        BackButtonequa.setBorderPainted(false);
        BackButtonequa.setFocusable(false);

        // Create the mode buttons
        solve_2_equ = new RoundedButton("solve_2_equ", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        equations_X2 = new RoundedButton("equations_X2", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        equations_X3 = new RoundedButton("equations_X3", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        solve_3_eq = new RoundedButton("solve_3_eq", new Dimension(width, height), new Color(173, 216, 230), PFontsize);

        solve_2_equ.setFocusable(false);
        equations_X2.setFocusable(false);
        equations_X3.setFocusable(false);
        solve_3_eq.setFocusable(false);

        // Add action listeners to the mode buttons
        solve_2_equ.addActionListener(this);
        equations_X2.addActionListener(this);
        equations_X3.addActionListener(this);
        solve_3_eq.addActionListener(this);

        // Set up the mode label
        equLabel = new JLabel();
        equLabel.setBounds(135, 23, 250, 40);
        equLabel.setFont(new Font("Arial", Font.BOLD, 30));
        equLabel.setForeground(Color.WHITE);
        equLabel.setText("Equations Page");

        // Add buttons to the panel
        equPanel.add(solve_2_equ);
        equPanel.add(solve_3_eq);
        equPanel.add(equations_X2);
        equPanel.add(equations_X3);

    }

    public static void equPanelshow() {

        equLabel.setVisible(true);
        BackButtonequa.setVisible(true);
        solve_2_equ.setVisible(true);
        solve_3_eq.setVisible(true);
        equations_X3.setVisible(true);
        equations_X2.setVisible(true);
        equPanel.setVisible(true);
        homefram.add(equPanel);
        homefram.add(BackButtonequa);
        homefram.add(equLabel);
    }

    public void resetequPage() {
        equLabel.setVisible(false);
        BackButtonequa.setVisible(false);
        solve_2_equ.setVisible(false);
        solve_3_eq.setVisible(false);
        equations_X3.setVisible(false);
        equations_X2.setVisible(false);
        equPanel.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButtonequa) {

            resetequPage();

            modLabel.setVisible(true);
            ModPanel.setVisible(true);
            BackButton.setVisible(true);
        } else if (e.getSource() == solve_2_equ) {
            // Handle solve_2_equ mode action
            resetequPage();
            solve_2_eq_panelshow();

        } else if (e.getSource() == equations_X2) {
            // Handle equations_X2 mode action
            resetequPage();
            equations_X2panelshow();

        } else if (e.getSource() == equations_X3) {
            // Handle equations_X3 mode action
            resetequPage();
            equations_X3panelshow();
        } else if (e.getSource() == solve_3_eq) {
            resetequPage();
            solve_3_eqpanelshow();
        }
    }
}
