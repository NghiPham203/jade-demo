
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
    static {
        try (Connection conn = getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS negotiations (timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, sender TEXT, receiver TEXT, message TEXT)";
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:market.db");
    }

    public static void insertLog(String sender, String receiver, String message) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO negotiations (sender, receiver, message) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setString(3, message);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
