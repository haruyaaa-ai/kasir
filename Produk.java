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
public class Produk {
    private int id;
    private String name;
    private double price;

    public Produk(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product [ID=" + id + ", Name=" + name + ", Price=" + price + "]";
    }
}



