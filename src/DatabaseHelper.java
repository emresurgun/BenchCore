import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHelper {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public DatabaseHelper() {
        loadConfig();
    }

    private void loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            url = properties.getProperty("DB_URL");
            user = properties.getProperty("DB_USER");
            password = properties.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            System.err.println("Error loading database configuration: " + e.getMessage());
        }
    }

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    public void insertBenchmarkResult(String username, long singleScore, long multiScore) throws SQLException {
        String query = "INSERT INTO benchmark_results (username, single_core_score, multi_core_score, timestamp) VALUES (?, ?, ?, NOW())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setLong(2, singleScore);
            statement.setLong(3, multiScore);
            statement.executeUpdate();
        }
    }

    public List<String[]> fetchBenchmarkResults() throws SQLException {
        List<String[]> results = new ArrayList<>();
        String query = "SELECT COALESCE(username, 'Unknown User') AS username, timestamp, single_core_score, multi_core_score FROM benchmark_results ORDER BY timestamp DESC";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String timestamp = resultSet.getString("timestamp");
                String singleScore = resultSet.getString("single_core_score");
                String multiScore = resultSet.getString("multi_core_score");
                results.add(new String[]{username, timestamp, singleScore, multiScore});
            }
        }
        return results;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}