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
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KasirSystem kasirSystem = new KasirSystem();

        // Opsi register dan login
        while (true) {
            System.out.println("+----------------------+");
            System.out.println("| No | Option           |");
            System.out.println("+----+------------------+");
            System.out.println("| 1  | Register          |");
            System.out.println("| 2  | Login             |");
            System.out.println("| 3  | Exit              |");
            System.out.println("+----+------------------+");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1: // Register
                    System.out.println("----------------------");
                    System.out.print("Masukkan username: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan password: ");
                    String password = scanner.nextLine();
                    kasirSystem.register(username, password);
                    break;

                case 2: // Login
                    System.out.println("----------------------");
                    System.out.print("Masukkan username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Masukkan password: ");
                    String loginPassword = scanner.nextLine();
                    if (kasirSystem.login(loginUsername, loginPassword)) {
                        System.out.println("Login berhasil!");

                        // Menu setelah login
                        showMainMenu(kasirSystem, scanner);
                    } else {
                        System.out.println("Username atau password salah!");
                    }
                    break;

                case 3: // Exit
                    System.out.println("Keluar...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    // Menampilkan menu utama setelah login
    public static void showMainMenu(KasirSystem kasirSystem, Scanner scanner) {
        while (true) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Lihat Produk");
            System.out.println("2. Transaksi Pembelian");
            System.out.println("3. Riwayat Transaksi");
            System.out.println("4. Tambah Produk");
            System.out.println("5. Logout");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1: // Lihat Produk
                    kasirSystem.getAllProducts();
                    break;

                case 2: // Transaksi Pembelian
                    System.out.print("Masukkan nama produk yang ingin dibeli: ");
                    String productName = scanner.nextLine();
                    System.out.print("Masukkan jumlah produk yang ingin dibeli: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    kasirSystem.makeTransaction(productName, quantity);
                    break;

                case 3: // Riwayat Transaksi
                    kasirSystem.viewTransactionHistory();
                    break;

                case 4: // Tambah Produk
                    System.out.print("Masukkan nama produk: ");
                    String newProductName = scanner.nextLine();
                    System.out.print("Masukkan harga produk: ");
                    double newProductPrice = scanner.nextDouble();
                    scanner.nextLine(); // Clear the buffer
                    kasirSystem.addProduct(newProductName, newProductPrice);
                    break;

                case 5: // Logout
                    System.out.println("Logout berhasil!");
                    return;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }
}



