package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Booking;

public class BookingDAO {
    public void saveBooking(Booking booking) {
        String sql = "INSERT INTO bookings (nama_penyewa, jenis_penyewa, sarana, durasi, jumlah_orang) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, booking.getNamaPenyewa());
            stmt.setString(2, booking.getJenisPenyewa());
            stmt.setString(3, booking.getSarana());
            stmt.setString(4, booking.getDurasi());
            stmt.setInt(5, booking.getJumlahOrang());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: Gagal menyimpan booking ke database");
            // Tetap menggunakan printStackTrace untuk detail error
        }
    }
}
