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
import java.sql.*;

public class KasirSystem {

    public void register(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("Register berhasil!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true; // Login berhasil
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Login gagal
    }

    // Menampilkan produk
    public void getAllProducts() {
        String query = "SELECT * FROM produk";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Nama: " + rs.getString("nama") + ", Harga: " + rs.getDouble("harga"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menambahkan produk baru
    public void addProduct(String productName, double productPrice) {
        String query = "INSERT INTO produk (nama, harga) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, productName);
            stmt.setDouble(2, productPrice);
            stmt.executeUpdate();
            System.out.println("Produk berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Melakukan transaksi pembelian
    public void makeTransaction(String productName, int quantity) {
        String query = "SELECT * FROM produk WHERE nama = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("harga");
                double total = price * quantity;
                System.out.println("Transaksi berhasil! Total: " + total);
                
                // Menyimpan transaksi ke database
                String insertTransaction = "INSERT INTO transaksi (product_name, quantity, total_price) VALUES (?, ?, ?)";
                try (PreparedStatement transStmt = conn.prepareStatement(insertTransaction)) {
                    transStmt.setString(1, productName);
                    transStmt.setInt(2, quantity);
                    transStmt.setDouble(3, total);
                    transStmt.executeUpdate();
                }

            } else {
                System.out.println("Produk tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Melihat riwayat transaksi
    public void viewTransactionHistory() {
        String query = "SELECT * FROM transaksi";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Produk: " + rs.getString("product_name") + ", Jumlah: " + rs.getInt("quantity") + ", Total Harga: " + rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
