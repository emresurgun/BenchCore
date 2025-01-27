import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class MainPage extends JFrame {

    JButton runButton;
    JLabel singleCoreScoreLabel;
    JLabel multiCoreScoreLabel;

    MainPage() {

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setBounds(0, 0, 250, 600); // Position and size
        leftPanel.setLayout(null);
        this.add(leftPanel);

        runButton = new JButton("Run Benchmark");
        runButton.setBounds(25, 125, 200, 80);
        leftPanel.add(runButton);

        JLabel infoLabel = new JLabel("<html>CPU Benchmark<br>Version Alpha (2.4)<br>Emre Şurğun</html>");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBounds(50, 400, 150, 100);
        leftPanel.add(infoLabel);

        // Divider Line
        JPanel line = new JPanel();
        line.setBackground(Color.GRAY);
        line.setBounds(250, 0, 3, 600);
        this.add(line);

        // Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(253, 0, 747, 600); // Right panel size
        rightPanel.setLayout(null);
        this.add(rightPanel);

        JLabel scoreHeaderLabel = new JLabel("Benchmark Results");
        scoreHeaderLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreHeaderLabel.setBounds(0, 20, 747, 40);
        rightPanel.add(scoreHeaderLabel);

        singleCoreScoreLabel = new JLabel("Single-Core Score: Pending...");
        singleCoreScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        singleCoreScoreLabel.setBounds(30, 80, 300, 30);
        rightPanel.add(singleCoreScoreLabel);

        multiCoreScoreLabel = new JLabel("Multi-Core Score: Pending...");
        multiCoreScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        multiCoreScoreLabel.setBounds(500, 80, 300, 30);
        rightPanel.add(multiCoreScoreLabel);

        // Attach action listener to Run Benchmark button
        runButton.addActionListener(e -> runBenchmark());

        // Frame Configuration
        this.setSize(1000, 600);
        this.setTitle("Benchmarker");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null); // Use absolute positioning
        this.setVisible(true);
    }

    /**
     * Runs the single-core and multi-core benchmarks sequentially
     * and updates the UI with the results.
     */
    private void runBenchmark() {
        singleCoreScoreLabel.setText("Single-Core Score: Running...");
        multiCoreScoreLabel.setText("Multi-Core Score: Waiting...");

        new Thread(() -> {
            // Run Single-Core Benchmark
            SingleCoreBenchmark singleCoreBenchmark = new SingleCoreBenchmark();
            long singleCoreScore = singleCoreBenchmark.calculateScore() / 100; // Divide by 100

            SwingUtilities.invokeLater(() ->
                    singleCoreScoreLabel.setText("Single-Core Score: " + formatScore(singleCoreScore))
            );
            multiCoreScoreLabel.setText("Multi-Core Score: Running...");
            // Run Multi-Core Benchmark
            MultiCoreBenchmark multiCoreBenchmark = new MultiCoreBenchmark();
            long multiCoreScore = multiCoreBenchmark.calculateScore() / 100; // Divide by 100

            SwingUtilities.invokeLater(() ->
                    multiCoreScoreLabel.setText("Multi-Core Score: " + formatScore(multiCoreScore))
            );
        }).start();
    }

    /**
     * Formats the score with commas for better readability.
     *
     * @param score The score to format.
     * @return The formatted score as a string.
     */
    private String formatScore(long score) {
        return NumberFormat.getNumberInstance(Locale.US).format(score);
    }

    public static void main(String[] args) {
        new MainPage();
    }
}