package calc.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static calc.Calculator.ShowCalculator;
import static calc.Calculator.homefram;
import static calc.constants.Pheight;
import static calc.constants.Pwidth;

public class HistoryPage implements ActionListener {

    public static JButton deleteHistoryButton;
    static ArrayList<String> historyList = new ArrayList<>();
    public static JTextArea historyTextaArea;
    public static JButton BackButtonHistory;
    public static JPanel HistoryPanel;
    public static String savedExpression = ""; // Variable to store saved expression
    private static final String ORIGINAL_TITLE = "Calculator"; // Original title
    private static final String HISTORY_TITLE = "History Page "; // History title

    public HistoryPage() {
        // Initialize components
        deleteHistoryButton = new JButton("Delete HistorY");
        deleteHistoryButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        deleteHistoryButton.setFocusable(false);
        deleteHistoryButton.setBackground(Color.decode("#b567a8"));
        deleteHistoryButton.setForeground(Color.WHITE);
        deleteHistoryButton.setBorder(null);
        deleteHistoryButton.addActionListener(this);
        deleteHistoryButton.setFocusable(false);

        BackButtonHistory = new JButton("Back");
        BackButtonHistory.setFont(new Font("Arial", Font.BOLD, 20));
        BackButtonHistory.setForeground(Color.WHITE);
        BackButtonHistory.setContentAreaFilled(false);
        BackButtonHistory.setOpaque(false);
        BackButtonHistory.setBorderPainted(false);
        BackButtonHistory.addActionListener(this);
        BackButtonHistory.setFocusable(false);

        historyTextaArea = new JTextArea();
        historyTextaArea.setFont(new Font("Arial", Font.BOLD, 20));
        historyTextaArea.setBackground(Color.decode("#101a43"));
        historyTextaArea.setForeground(Color.WHITE);
        historyTextaArea.setEditable(false);

        // Add scroll pane for history area
        JScrollPane scrollPane = new JScrollPane(historyTextaArea);
        scrollPane.setBackground(Color.decode("#101a43"));

        // Initialize panel and layout
        HistoryPanel = new JPanel();
        HistoryPanel.setLayout(new BorderLayout());
        HistoryPanel.setBackground(Color.decode("#101a43"));
        HistoryPanel.setBounds(60, 60, 400, 500);

        // Add components to the panel
        HistoryPanel.add(scrollPane, BorderLayout.CENTER);
        HistoryPanel.add(deleteHistoryButton, BorderLayout.SOUTH);
        HistoryPanel.add(BackButtonHistory, BorderLayout.NORTH);

        // Initial display of history
        updateHistoryDisplay();
    }

    public static void HideHistory() {
        homefram.setTitle(ORIGINAL_TITLE);
        deleteHistoryButton.setVisible(false);
        BackButtonHistory.setVisible(false);
        HistoryPanel.setVisible(false);
    }

    public static void showHistory() {
        homefram.setTitle(HISTORY_TITLE); // Set title to "History Page Calculator"
        homefram.setSize(Pwidth, Pheight);
        homefram.setLayout(new BorderLayout());
        HistoryPanel.setVisible(true);
        deleteHistoryButton.setVisible(true);
        BackButtonHistory.setVisible(true);
        homefram.add(HistoryPanel, BorderLayout.CENTER);
        homefram.setVisible(true);
        homefram.revalidate();
        homefram.repaint();
    }

    public static void addHistory(String expression) {
        historyList.add(expression);
        updateHistoryDisplay();
    }

    public static void updateHistoryDisplay() {
        historyTextaArea.setText(""); // Clear existing text
        for (String expression : historyList) {
            historyTextaArea.append(expression + "\n\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BackButtonHistory) {
            HideHistory();
            ShowCalculator();
        } else if (e.getSource() == deleteHistoryButton) {
            historyList.clear();
            updateHistoryDisplay();
        }
    }
}
