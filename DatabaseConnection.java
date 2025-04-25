/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

/**
 *
 * @author Damar Satriatama.P
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/kasir_system";  // Ganti dengan nama database Anda
    private static final String USER = "root";  // Ganti dengan username database Anda
    private static final String PASSWORD = "";  // Ganti dengan password database Anda

    // Koneksi akan dibuka hanya saat dibutuhkan, dan tidak ditutup otomatis
    public static Connection getConnection() {
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Membuka koneksi ke database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

