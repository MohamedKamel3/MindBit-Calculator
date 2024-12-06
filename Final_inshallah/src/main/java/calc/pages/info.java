package calc.pages;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static calc.Calculator.ShowCalculator;
import static calc.Calculator.homefram;
import static calc.constants.PColor;
import static calc.constants.Pwidth;

public class info implements ActionListener {

    public static JPanel infoPanel;

    JLabel Team, empty;
    JLabel Shaddad;
    JLabel Nasr;
    JLabel Ayman;
    JLabel Mohamed;
    JLabel Abdualalem;
    JLabel lida;
    JLabel Wahdan;
    JLabel Omar;
    JLabel Saja;
    JLabel Azza;
    JLabel Seham;
    public static JButton BackButtoninfo;

    public info() {

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(13, 1, 0, 0));
        infoPanel.setBounds(30, 30, 400, 500);
        infoPanel.setBackground(PColor); // لون الخلفية

        BackButtoninfo = new JButton("Back");
        BackButtoninfo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        BackButtoninfo.setForeground(Color.WHITE);
        BackButtoninfo.setBounds(350, 50, 100, 40);
        BackButtoninfo.setContentAreaFilled(false);
        BackButtoninfo.setOpaque(false);
        BackButtoninfo.setBorder(null);
        BackButtoninfo.addActionListener(this);
        BackButtoninfo.setBorderPainted(false);
        BackButtoninfo.setFocusable(false);

        empty = new JLabel(" ");

        Team = new JLabel("MindBit", JLabel.CENTER);  // Center text horizontally
        Team.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 30));
        Team.setForeground(Color.CYAN);

        Shaddad = new JLabel("Mostafa Shaddad");
        Shaddad.setSize(300, 30);
        Shaddad.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Shaddad.setForeground(Color.WHITE);

        Saja = new JLabel("Saja Mohamed");
        Saja.setSize(300, 30);
        Saja.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Saja.setForeground(Color.WHITE);

        Azza = new JLabel("Azza Yahia");
        Azza.setSize(300, 30);
        Azza.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Azza.setForeground(Color.WHITE);

        Nasr = new JLabel("Nasr Ehab");
        Nasr.setSize(300, 30);
        Nasr.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Nasr.setForeground(Color.WHITE);

        lida = new JLabel("Mohamed Walid");
        lida.setSize(300, 30);
        lida.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        lida.setForeground(Color.WHITE);

        Omar = new JLabel("Omar Hany");
        Omar.setSize(300, 30);
        Omar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Omar.setForeground(Color.WHITE);

        Mohamed = new JLabel("Mohamed M. Kamel");
        Mohamed.setSize(300, 30);
        Mohamed.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Mohamed.setForeground(Color.WHITE);

        Abdualalem = new JLabel("Abdualalem Abdalfatah");
        Abdualalem.setSize(300, 30);
        Abdualalem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Abdualalem.setForeground(Color.WHITE);

        Wahdan = new JLabel("Mahmoud Wahdan");
        Wahdan.setSize(300, 30);
        Wahdan.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Wahdan.setForeground(Color.WHITE);

        Ayman = new JLabel("Mahmoud Ayman");
        Ayman.setSize(300, 30);
        Ayman.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Ayman.setForeground(Color.WHITE);

        Seham = new JLabel("Seham Kamel");
        Seham.setSize(300, 30);
        Seham.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        Seham.setForeground(Color.WHITE);

        infoPanel.add(Team);
        infoPanel.add(empty);
        infoPanel.add(Shaddad);
        infoPanel.add(Nasr);
        infoPanel.add(Ayman);
        infoPanel.add(Mohamed);
        infoPanel.add(Abdualalem);
        infoPanel.add(lida);
        infoPanel.add(Wahdan);
        infoPanel.add(Omar);
        infoPanel.add(Saja);
        infoPanel.add(Azza);
        infoPanel.add(Seham);

    }

    public static void showinfo() {
        homefram.setSize(Pwidth, 600);
        infoPanel.setVisible(true);
        BackButtoninfo.setVisible(true);
        homefram.add(BackButtoninfo);
        homefram.add(infoPanel);
    }

    public static void hideinfo() {
        infoPanel.setVisible(false);
        BackButtoninfo.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == BackButtoninfo) {
            hideinfo();
            ShowCalculator();
        }

    }
}
