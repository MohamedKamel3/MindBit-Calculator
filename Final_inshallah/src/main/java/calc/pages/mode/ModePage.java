package calc.pages.mode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static calc.Calculator.ShowCalculator;
import static calc.Calculator.homefram;
import static calc.constants.PFontsize;
import static calc.constants.Pheight;
import static calc.constants.Pwidth;
import static calc.pages.mode.Area.AreaPage.showAreaPage;
import static calc.pages.mode.Perimeter.Perimeterpage.showperpage;
import static calc.pages.mode.Volume.Volumepage.showvolpage;
import static calc.pages.mode.equatios.equationpage.equPanelshow;
import calc.tools.RoundedButton;

public class ModePage implements ActionListener {

    public static JLabel modLabel;
    public static JPanel ModPanel = new JPanel();
    public static JButton BackButton;
    static RoundedButton equations;
    static RoundedButton area;
    static RoundedButton perimeter;
    static RoundedButton volume;

    public ModePage() {
        int width = 200;
        int height = 80;

        // Set up the main panel
        ModPanel.setBounds(60, 60, 400, 500);
        ModPanel.setLayout(new GridLayout(4, 1, 0, -10));
        ModPanel.setBackground(Color.decode("#101a43")); // Change background color

        // Create buttons
        BackButton = new JButton("Back");
        BackButton.setFont(new Font("Arial", Font.BOLD, 20));
        BackButton.setForeground(Color.white); // Set font size for the back button
        BackButton.setBounds(10, 13, 100, 50); // Position the back button
        BackButton.setContentAreaFilled(false);
        BackButton.setOpaque(false);
        BackButton.setBorderPainted(false);
        BackButton.setFocusable(false);

        equations = new RoundedButton("Equations", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        area = new RoundedButton("Area", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        perimeter = new RoundedButton("Perimeter", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        volume = new RoundedButton("Volume", new Dimension(width, height), new Color(173, 216, 230), PFontsize);

        equations.setFocusable(false);
        area.setFocusable(false);
        perimeter.setFocusable(false);
        volume.setFocusable(false);

        // Add action listeners to the mode buttons
        equations.addActionListener(this);
        area.addActionListener(this);
        perimeter.addActionListener(this);
        volume.addActionListener(this);
        BackButton.addActionListener(this);

        // Set up the mode label
        modLabel = new JLabel();
        modLabel.setBounds(215, 30, 100, 40);
        modLabel.setFont(new Font("Arial", Font.BOLD, 30));
        modLabel.setForeground(Color.WHITE);
        modLabel.setText("Mode");

        // Add buttons to the panel
        ModPanel.add(equations);
        ModPanel.add(area);
        ModPanel.add(perimeter);
        ModPanel.add(volume);
    }

    public static void ModPanelshow() {
        homefram.setSize(Pwidth, Pheight);
        homefram.setLayout(null);
        modLabel.setVisible(true);
        equations.setVisible(true);
        area.setVisible(true);
        perimeter.setVisible(true);
        volume.setVisible(true);
        ModPanel.setVisible(true);
        BackButton.setVisible(true);
        homefram.add(modLabel);
        homefram.add(BackButton);
        homefram.add(ModPanel);
    }

    public void resetModPage() {
        modLabel.setVisible(false);
        ModPanel.setVisible(false);
        BackButton.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            // Reset previous components visibility
            resetModPage();

            ShowCalculator();
        } else if (e.getSource() == equations) {
            resetModPage();  // Hide previous mode
            equPanelshow();
        } else if (e.getSource() == area) {
            resetModPage();  // Hide previous mode
            showAreaPage();

        } else if (e.getSource() == perimeter) {
            resetModPage();  // Hide previous mode
            showperpage();
            // Add perimeter functionality
        } else if (e.getSource() == volume) {
            resetModPage();  // Hide previous mode
            showvolpage();
            // Add volume functionality
        }
    }

}
