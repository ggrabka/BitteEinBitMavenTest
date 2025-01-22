package com.bitteEinBit;

import java.util.ArrayList;

public class Cart {

    private final int id;
    private final ArrayList<Product> myProducts;

    public Cart(int id) {

        this.id = id;
        this.myProducts = new ArrayList<>();
    }

    public int getId() {

        return id;
    }

    public double getTotal() {

        double total = 0.0;

        for (Product product : myProducts) {

            total += (product.getQuantity() * product.getPrice());
        }

        return total;
    }

    public  void addProduct(Product product) {

        if (!this.myProducts.contains(product)) {

            this.myProducts.add(product);
            System.out.println(" " + product.getName() + " wurde Ihrem Warenkorb hinzugefügt");
            product.increaseQuantity();
        } else {

            System.out.println(" " + product.getName() + " wurde erneut Ihrem Warenkorb hinzugefügt");
            product.increaseQuantity();
        }
    }

    public void removeProduct(Product product) {

        if (this.myProducts.contains(product)) {

            product.decreaseQuantity();
            System.out.println(" " + product.getName() + " wurde einmal aus Ihrem Warenkorb entfernt");

            if (product.getQuantity() == 0) {

                this.myProducts.remove(product);
                System.out.println(" " + product.getName() + " ist nicht mehr in Ihrem Warenkorb");
            }
        }
    }

    public void clearList() {

        this.myProducts.clear();
        System.out.println(" Ihr Warenkorb wurde geleert");
    }

    public ArrayList<Product> getMyProducts() {

        return this.myProducts;
    }

    public void printMyList() {

        for (Product product : myProducts) {

            System.out.printf(" >> %d x [(Nr. %d) %s : %.2f €] = %.2f €\n",
                    product.getQuantity(), product.getProductId(), product.getName(), product.getPrice(), (product.getQuantity() * product.getPrice()));
        }

        System.out.println();
    }

    public void printCustomersCart() {

        System.out.println(" Warenkorb ID: " + id);
        System.out.println();

        if (myProducts.isEmpty()) {

            System.out.println(" Der Warenkorb ist leer.");
        } else {

            printMyList();
            System.out.printf(" Gesamtbetrag : %.2f €\n", getTotal());
        }

        System.out.println(" --------------------- ");
    }
}