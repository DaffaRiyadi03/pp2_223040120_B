package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/booking_sarana_olahraga";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static MySqlConnection instance;

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver MySQL tidak ditemukan");
        } catch (SQLException e) {
            System.out.println("Error: Gagal terhubung ke database");
        }
        return connection;
    }
}
