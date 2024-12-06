package calc.pages.mode.Area;

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
import static calc.pages.mode.Area.CircleAreaPage.showCircleAreaPage;
import static calc.pages.mode.Area.RectangleAreaPage.showRectangleareaPage;
import static calc.pages.mode.Area.SquareAreaPage.showSquarePage;
import static calc.pages.mode.Area.TriangleAreaPage.showTrianglePage;
import static calc.pages.mode.ModePage.ModPanelshow;
import calc.tools.RoundedButton;

public class AreaPage implements ActionListener {

    public static JLabel areaLabel;
    public static JPanel areaPanel = new JPanel();
    public static JButton backButton;
    RoundedButton rectangleButton;
    RoundedButton triangleButton;
    RoundedButton circleButton;
    RoundedButton squareButton;

    public AreaPage() {

        int width = 200;
        int height = 80;
        areaPanel.setBounds(60, 60, 450, 500);
        areaPanel.setLayout(new GridLayout(4, 1, 0, -10));
        areaPanel.setBackground(Color.decode("#101a43"));

        // إنشاء زر العودة
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setForeground(Color.white);
        backButton.setContentAreaFilled(false);
        backButton.setBounds(10, 13, 100, 50);
        backButton.setOpaque(false);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        backButton.setBorderPainted(false);
        backButton.setFocusable(false);

        // إنشاء الأزرار للأشكال المطلوبة
        rectangleButton = new RoundedButton("Rectangle", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        triangleButton = new RoundedButton("Triangle", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        circleButton = new RoundedButton("Circle", new Dimension(width, height), new Color(173, 216, 230), PFontsize);
        squareButton = new RoundedButton("Square", new Dimension(width, height), new Color(173, 216, 230), PFontsize);

        rectangleButton.setFocusable(false);
        triangleButton.setFocusable(false);
        circleButton.setFocusable(false);
        squareButton.setFocusable(false);

        // إضافة actionListener للأزرار
        rectangleButton.addActionListener(this);
        triangleButton.addActionListener(this);
        circleButton.addActionListener(this);
        squareButton.addActionListener(this);

        // إعداد العنوان
        areaLabel = new JLabel("Area Page");
        areaLabel.setBounds(170, 30, 150, 30);
        areaLabel.setFont(new Font("Arial", Font.BOLD, 30));
        areaLabel.setForeground(Color.WHITE);

        // إضافة الأزرار إلى اللوحة
        areaPanel.add(rectangleButton);
        areaPanel.add(triangleButton);
        areaPanel.add(circleButton);
        areaPanel.add(squareButton);
    }

    public static void showAreaPage() {
        setVisibility(true);
        homefram.add(areaPanel);
        homefram.add(backButton);
        homefram.add(areaLabel);
        homefram.revalidate();
        homefram.repaint();
        homefram.setSize(Pwidth, Pheight);

    }

    public static void resetAreaPage() {
        setVisibility(false);
    }

    private static void setVisibility(boolean isVisible) {
        areaLabel.setVisible(isVisible);
        areaPanel.setVisible(isVisible);
        backButton.setVisible(isVisible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            resetAreaPage();
            ModPanelshow();
        } else if (e.getSource() == rectangleButton) {
            System.out.println("Rectangle button clicked");
            resetAreaPage();
            showRectangleareaPage();

        } else if (e.getSource() == triangleButton) {
            System.out.println("Triangle button clicked");
            resetAreaPage();
            showTrianglePage();
        } else if (e.getSource() == circleButton) {
            System.out.println("Circle button clicked");
            resetAreaPage();
            showCircleAreaPage();
        } else if (e.getSource() == squareButton) {
            System.out.println("Square button clicked");
            resetAreaPage();
            showSquarePage();
        }
    }
}
