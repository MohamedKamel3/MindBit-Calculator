package calc.pages.mode.Volume;

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
import static calc.constants.Pheight;
import static calc.constants.Pwidth;
import static calc.pages.mode.ModePage.ModPanelshow;
import static calc.pages.mode.Volume.cubevolumepage.showCubePage;
import static calc.pages.mode.Volume.cylindervolumepage.showCylinderPage;
import static calc.pages.mode.Volume.spherevolumepage.showSpherePage;
import calc.tools.RoundedButton;

public class Volumepage implements ActionListener {

    public static JLabel Vollabel;
    public static JPanel Volpanel = new JPanel();
    public static JButton BackButton;
    RoundedButton Cube;
    RoundedButton Cylinder;
    RoundedButton Sphere;

    public Volumepage() {

        int width = 200;
        int height = 80;
        Volpanel.setBounds(50, 100, 450, 400);
        Volpanel.setLayout(new GridLayout(3, 1, 0, -10));
        Volpanel.setBackground(Color.decode("#101a43"));

        // Create buttons
        BackButton = new JButton("Back");
        BackButton.setFont(new Font("Arial", Font.BOLD, 20));
        BackButton.setForeground(Color.white);
        BackButton.setContentAreaFilled(false);
        BackButton.setBounds(10, 13, 100, 50); // Position the back button
        BackButton.setOpaque(false);
        BackButton.setBorder(null);
        BackButton.addActionListener(this);
        BackButton.setBorderPainted(false);
        BackButton.setFocusable(false);

        Cube = new RoundedButton("Cube", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Cylinder = new RoundedButton("Cylinder", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Sphere = new RoundedButton("Sphere", new Dimension(width, height), new Color(173, 216, 230), PFontsize);

        Cube.setFocusable(false);
        Cylinder.setFocusable(false);
        Sphere.setFocusable(false);
        
        // Add action listeners to the buttons
        Cube.addActionListener(this);
        Cylinder.addActionListener(this);
        Sphere.addActionListener(this);

        Vollabel = new JLabel();
        Vollabel.setBounds(135, 23, 250, 40);
        Vollabel.setFont(new Font("Arial", Font.BOLD, 30));
        Vollabel.setForeground(Color.WHITE);
        Vollabel.setText("Volume Page");

        // Add buttons to the panel
        Volpanel.add(Cube);
        Volpanel.add(Cylinder);
        Volpanel.add(Sphere);

        // Add components to the main frame
    }

    public static void showvolpage() {
        homefram.setSize(Pwidth, Pheight);
        Volpanel.setVisible(true);
        BackButton.setVisible(true);
        Vollabel.setVisible(true);
        homefram.add(Volpanel);
        homefram.add(BackButton);
        homefram.add(Vollabel);
    }

    public static void resetvolpgae() {
        Vollabel.setVisible(false);
        Volpanel.setVisible(false);
        BackButton.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            // الكود الموجود مسبقاً للـ back button
            resetvolpgae();
            ModPanelshow();
        } else if (e.getSource() == Cube) {
            resetvolpgae();
            showCubePage();

        } else if (e.getSource() == Cylinder) {
            resetvolpgae();
            showCylinderPage();

        } else if (e.getSource() == Sphere) {
            resetvolpgae();
            showSpherePage();
        }

    }
}
