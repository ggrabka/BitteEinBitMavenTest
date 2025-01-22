package com.bitteEinBit;

import java.util.Objects;

public class Product {
    private int productId;
    private double price;
    private String productGroup;
    private String name;
    private int quantity;

    private Product[] products;

    public Product(double price, String productGroup, String name) {
        this.price = price;
        this.productGroup = productGroup;
        this.name = name;
    }

    public Product(String name, double price, int id) {

        this.name = name;
        this.price = price;
        this.productId = id;
        this.quantity = 0;
    }

    public Product(Product[] products) {

        this.products = products;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public int getQuantity() {

        return quantity;
    }

    public void increaseQuantity() {

        this.quantity++;
    }

    public void decreaseQuantity() {

        if (this.quantity > 0) {

            this.quantity--;
        }
    }

    public void printProduct() {

        System.out.printf(" >> %s %.2f €\n (Im Warenkorb: %d)\n", this.name, this.price, this.quantity);
    }

    public void displayProducts() {

        for (Product product : products) {

            System.out.println(product);
        }
    }

    void setProductId(int productId) {this.productId = productId;}

    void setName(String name) {
        this.name = name;
    }

    void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    void setPrice(double price) {
        this.price = price;
    }

    public void addProduct() {
        Helper helper = new Helper();
        helper.readFromJsonFile();
        helper.checkIfProductExistsInJsonFile(this);
        helper.addProductToJsonFile(this);
    }

    public void removeProduct() {
        Helper helper = new Helper();
        helper.readFromJsonFile();
        helper.removeProductFromJsonFile(this);
    }

    @Override
    public String toString() {
        return "{productId=" + productId + ", productGroup=" + productGroup + ", name=" + name + ", price=" + price + "}";
        //return String.format(" // %s %.2f € Nr. %d", this.name, this.price, this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(productGroup, product.productGroup) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, productGroup, name);
    }
}
