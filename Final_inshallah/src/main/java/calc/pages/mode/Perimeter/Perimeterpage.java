package calc.pages.mode.Perimeter;

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
import static calc.pages.mode.Perimeter.CirclePage.showCirclePage;
import static calc.pages.mode.Perimeter.RectanglePage.showRectanglePage;
import static calc.pages.mode.Perimeter.SquarePage.showSquarePage;
import static calc.pages.mode.Perimeter.TrianglePage.showTrianglePage;
import calc.tools.RoundedButton;

public class Perimeterpage implements ActionListener {

    public static JLabel Perlabel;
    public static JPanel Perpanel = new JPanel();
    public static JButton BackButton;
    RoundedButton Rectangle;
    RoundedButton Triangle;
    RoundedButton Circle;
    RoundedButton Square;
    RoundedButton Cube;
    RoundedButton Cylinder;
    RoundedButton Sphere;

    public Perimeterpage() {

        int width = 200;
        int height = 80;
        Perpanel.setBounds(60, 60, 400, 500);
        Perpanel.setLayout(new GridLayout(4, 1, 0, -10));
        Perpanel.setBackground(Color.decode("#101a43"));

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

        Rectangle = new RoundedButton("Rectangle", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Triangle = new RoundedButton("Triangle", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Circle = new RoundedButton("Circle", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Square = new RoundedButton("Square", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Cube = new RoundedButton("Cube", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Cylinder = new RoundedButton("Cylinder", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        Sphere = new RoundedButton("Sphere", new Dimension(width, height), new Color(173, 216, 230), PFontsize);

        Rectangle.setFocusable(false);
        Triangle.setFocusable(false);
        Circle.setFocusable(false);
        Square.setFocusable(false);
        Cube.setFocusable(false);
        Cylinder.setFocusable(false);
        Sphere.setFocusable(false);

        // Add action listeners to the buttons
        Rectangle.addActionListener(this);
        Triangle.addActionListener(this);
        Circle.addActionListener(this);
        Square.addActionListener(this);
        Cube.addActionListener(this);
        Cylinder.addActionListener(this);
        Sphere.addActionListener(this);

        Perlabel = new JLabel();
        Perlabel.setBounds(135, 23, 250, 40);
        Perlabel.setFont(new Font("Arial", Font.BOLD, 30));
        Perlabel.setForeground(Color.WHITE);
        Perlabel.setText("Perimeter Page");
        // Add buttons to the panel
        Perpanel.add(Rectangle);
        Perpanel.add(Triangle);
        Perpanel.add(Circle);
        Perpanel.add(Square);
        // Add components to the main frame
    }

    public static void showperpage() {
        Perpanel.setVisible(true);
        BackButton.setVisible(true);
        Perlabel.setVisible(true);
        homefram.add(Perpanel);
        homefram.add(BackButton);
        homefram.add(Perlabel);
        homefram.setSize(Pwidth, Pheight);
    }

    public static void resetperpgae() {
        Perlabel.setVisible(false);
        Perpanel.setVisible(false);
        BackButton.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButton) {
            // الكود الموجود مسبقاً للـ back button
            resetperpgae();
            ModPanelshow();
        } else if (e.getSource() == Rectangle) {
            resetperpgae();
            showRectanglePage();

        } else if (e.getSource() == Triangle) {
            resetperpgae();
            showTrianglePage();

        } else if (e.getSource() == Circle) {
            resetperpgae();
            showCirclePage();

        } else if (e.getSource() == Square) {
            resetperpgae();
            showSquarePage();

        }

    }
}
