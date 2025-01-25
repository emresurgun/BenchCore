import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    MainPage() {

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setBounds(0, 0, 250, 600); // Position and size
        leftPanel.setLayout(null);
        this.add(leftPanel);

        JButton runButton = new JButton("Run Benchmark");
        runButton.setBounds(25, 125, 200, 80);
        leftPanel.add(runButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(25, 220, 200, 80);
        leftPanel.add(settingsButton);

        JButton HowItsWorkButton = new JButton("How Its Work ?");
        HowItsWorkButton.setBounds(25, 315, 200, 80);
        leftPanel.add(HowItsWorkButton);

        JLabel infoLabel = new JLabel("<html>CPU Benchmarker<br>Version Alpha(1.0)<br>Emre Şurğun</html>");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBounds(50, 400, 150, 100);
        leftPanel.add(infoLabel);


        JPanel line = new JPanel();
        line.setBackground(Color.GRAY);
        line.setBounds(250, 0, 3, 600);
        this.add(line);

        this.setSize(1000, 600);
        this.setTitle("Benchmarker");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null); // Use absolute positioning
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainPage();
    }
}
