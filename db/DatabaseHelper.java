package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseHelper {
    static {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:market.db");
            String createTable = "CREATE TABLE IF NOT EXISTS negotiations (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sender TEXT, receiver TEXT, message TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
            conn.createStatement().execute(createTable);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertLog(String sender, String receiver, String message) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:market.db")) {
            String sql = "INSERT INTO negotiations (sender, receiver, message) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sender);
            pstmt.setString(2, receiver);
            pstmt.setString(3, message);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
