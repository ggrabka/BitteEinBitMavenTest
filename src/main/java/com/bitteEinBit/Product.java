package com.bitteEinBit;

import java.util.Objects;

/**
 * Die `Product`-Klasse stellt ein Produkt dar, das in der Registrierkasse verwendet wird.
 * Jedes Produkt hat eine eindeutige ID, einen Namen, eine Produktgruppe, einen Preis und eine Menge.
 */
public class Product {
    private int productId;       // Eindeutige ID des Produkts
    private String name;         // Name des Produkts
    private String productGroup; // Kategorie des Produkts (z. B. Getränke, Essen)
    private double price;        // Preis des Produkts
    private int quantity = 0;    // Anzahl des Produkts im Warenkorb (Standard: 0)

    /**
     * Konstruktor zur Erstellung eines neuen Produkts mit einer ID, einem Namen, einer Gruppe und einem Preis.
     * @param productId Eindeutige Identifikationsnummer des Produkts
     * @param name Name des Produkts
     * @param productGroup Kategorie oder Art des Produkts
     * @param price Preis des Produkts in Euro
     */
    public Product(int productId, String name, String productGroup, double price) {
        this.productId = productId;
        this.name = name;
        this.productGroup = productGroup;
        this.price = price;
    }

    // Getter-Methoden für die Eigenschaften des Produkts
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getProductGroup() { return productGroup; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    /**
     * Erhöht die Anzahl des Produkts im Warenkorb um 1.
     */
    public void increaseQuantity() { quantity++; }

    /**
     * Verringert die Anzahl des Produkts um 1, jedoch nicht unter 0.
     */
    public void decreaseQuantity() { if (quantity > 0) quantity--; }

    /**
     * Erstellt eine formatierte Zeichenkette mit allen Produktdetails.
     * @return formatierter String mit Produktinformationen.
     */
    @Override
    public String toString() {
        return String.format("%s (%s): %.2f € [x%d]", name, productGroup, price, quantity);
    }

    /**
     * Vergleicht zwei Produkte anhand ihrer ID, um Duplikate zu verhindern.
     * @param o Das zu vergleichende Objekt.
     * @return `true`, wenn beide Produkte dieselbe ID haben, sonst `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    /**
     * Erstellt einen eindeutigen Hashcode basierend auf der Produkt-ID.
     * @return Hashcode des Produkts.
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}