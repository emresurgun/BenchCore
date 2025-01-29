import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.List;

public class MainPage extends JFrame {

    JButton runButton;
    JLabel singleCoreScoreLabel;
    JLabel multiCoreScoreLabel;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JTextField usernameField; // New username field

    MainPage() {
        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setBounds(0, 0, 250, 600);
        leftPanel.setLayout(null);
        this.add(leftPanel);

        // Username Field (New)
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(25, 25, 200, 20);
        leftPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(25, 50, 200, 30);
        leftPanel.add(usernameField);

        // Run Benchmark Button (Shifted down)
        runButton = new JButton("Run Benchmark");
        runButton.setBounds(25, 125, 200, 80);
        leftPanel.add(runButton);

        // LinkedIn Button (Shifted down)
        JButton linkedinButton = new JButton("LinkedIn");
        linkedinButton.setBounds(25, 225, 200, 80); // Original: 225 → 225
        linkedinButton.addActionListener(e -> openWebpage("https://www.linkedin.com/in/emre-%C5%9Fur%C4%9Fun/"));
        leftPanel.add(linkedinButton);

        // GitHub Button (Shifted down)
        JButton githubButton = new JButton("GitHub");
        githubButton.setBounds(25, 325, 200, 80); // Original: 325 → 325
        githubButton.addActionListener(e -> openWebpage("https://github.com/emresurgun"));
        leftPanel.add(githubButton);

        // Info Label (Shifted down)
        JLabel infoLabel = new JLabel("<html>CPU Benchmark<br>Version(1.1 with AWS)<br>Emre Şurğun</html>");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBounds(50, 425, 150, 100); // Original: 400 → 425
        leftPanel.add(infoLabel);

        // Divider Line (Unchanged)
        JPanel line = new JPanel();
        line.setBackground(Color.GRAY);
        line.setBounds(250, 0, 3, 600);
        this.add(line);

        // Right Panel (Unchanged)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(253, 0, 747, 600);
        rightPanel.setLayout(null);
        this.add(rightPanel);

        // Benchmark Results Header (Unchanged)
        JLabel scoreHeaderLabel = new JLabel("Benchmark Results");
        scoreHeaderLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreHeaderLabel.setBounds(0, 20, 747, 40);
        rightPanel.add(scoreHeaderLabel);

        // Score Labels (Unchanged)
        singleCoreScoreLabel = new JLabel("Single-Core Score: Pending...");
        singleCoreScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        singleCoreScoreLabel.setBounds(30, 80, 300, 30);
        rightPanel.add(singleCoreScoreLabel);

        multiCoreScoreLabel = new JLabel("Multi-Core Score: Pending...");
        multiCoreScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        multiCoreScoreLabel.setBounds(450, 80, 300, 30);
        rightPanel.add(multiCoreScoreLabel);

        // Results Table (Updated columns)
        String[] columnNames = {"Username", "Date & Time", "Single-Core Score", "Multi-Core Score"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBounds(30, 150, 687, 400);
        rightPanel.add(scrollPane);

        // Initial Data Load
        loadHistoricalData();

        // Button Action Listener (Updated)
        runButton.addActionListener(e -> runBenchmark());

        // Window Configuration (Unchanged)
        this.setSize(1000, 600);
        this.setTitle("Benchmarker");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    // Open webpage method (FULLY INCLUDED)
    private void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to open link: " + ex.getMessage(),
                    "Browser Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadHistoricalData() {
        tableModel.setRowCount(0);
        DatabaseHelper dbHelper = new DatabaseHelper();
        try {
            dbHelper.connect();
            List<String[]> results = dbHelper.fetchBenchmarkResults();
            for (String[] row : results) {
                tableModel.addRow(row);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                dbHelper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void runBenchmark() {
        String usernameInput = usernameField.getText().trim();
        final String username = usernameInput.isEmpty() ? "Unknown User" : usernameInput; // Declare as final

        singleCoreScoreLabel.setText("Single-Core Score: Running...");
        multiCoreScoreLabel.setText("Multi-Core Score: Waiting...");

        new Thread(() -> {
            DatabaseHelper dbHelper = new DatabaseHelper();
            try {
                dbHelper.connect();

                SingleCoreBenchmark singleCore = new SingleCoreBenchmark();
                long singleScore = singleCore.calculateScore() / 100;

                singleCoreScoreLabel.setText("Single-Core Score: Finished...");
                multiCoreScoreLabel.setText("Multi-Core Score: Running...");
                MultiCoreBenchmark multiCore = new MultiCoreBenchmark();
                long multiScore = multiCore.calculateScore() / 100;

                SwingUtilities.invokeLater(() -> {
                    singleCoreScoreLabel.setText("Single-Core Score: " + formatScore(singleScore));
                    multiCoreScoreLabel.setText("Multi-Core Score: " + formatScore(multiScore));
                });

                // Use the final variable `username` here
                dbHelper.insertBenchmarkResult(username, singleScore, multiScore);

                SwingUtilities.invokeLater(this::loadHistoricalData);

            } catch (SQLException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    dbHelper.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String formatScore(long score) {
        return String.format("%,d", score);
    }

    public static void main(String[] args) {
        new MainPage();
    }
}