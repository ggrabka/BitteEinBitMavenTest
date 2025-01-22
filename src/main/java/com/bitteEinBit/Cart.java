package com.bitteEinBit;

import java.util.ArrayList;
import java.util.List;

/**
 * Die `Cart`-Klasse stellt einen Warenkorb dar, in dem Produkte gespeichert, entfernt und berechnet werden können.
 * Sie unterstützt die Verwaltung von Produkten und bietet Methoden zur Gesamtpreisanzeige.
 */

public class Cart {
    private final int id; // Eindeutige Warenkorb-ID
    private final ArrayList<Product> myProducts; // Liste der Produkte im Warenkorb

    /**
     * Konstruktor für einen neuen Warenkorb.
     * Jeder Warenkorb hat eine eindeutige ID und eine leere Produktliste.
     * @param id Eine eindeutige Identifikationsnummer für den Warenkorb.
     */

    public Cart(int id) {
        this.id = id;
        this.myProducts = new ArrayList<>(); // Erstellt eine neue leere Liste für Produkte
    }

    /**
     * Gibt die eindeutige ID des Warenkorbs zurück.
     * @return Die Warenkorb-ID.
     */

    public int getId() { return id; }

    /**
     * Berechnet den Gesamtpreis des Warenkorbs, indem alle Produktpreise mit ihrer jeweiligen Menge multipliziert werden.
     * @return Der Gesamtpreis aller Produkte im Warenkorb.
     */

    public double getTotal() {
        return myProducts.stream()
                .mapToDouble(p -> p.getQuantity() * p.getPrice()) // Preis eines Produkts * Menge
                .sum(); // Summe aller Produkte berechnen
    }

    /**
     * Fügt ein Produkt zum Warenkorb hinzu. Falls es bereits existiert, wird nur die Menge erhöht.
     * @param product Das hinzuzufügende Produkt.
     */
    public void addProduct(Product product) {
        for (Product p : myProducts) {
            if (p.equals(product)) { // Falls das Produkt bereits existiert, erhöhe nur die Menge
                p.increaseQuantity();
                System.out.println(product.getName() + " erneut hinzugefügt.");
                printCart(); // Aktualisierten Warenkorb anzeigen
                return;
            }
        }
        // Falls das Produkt noch nicht existiert, wird es in die Liste aufgenommen
        product.increaseQuantity();
        myProducts.add(product);
        System.out.println(product.getName() + " wurde hinzugefügt.");
        printCart(); // Aktualisierten Warenkorb anzeigen
    }

    public void removeProduct(Product product) {
        myProducts.removeIf(p -> {
            if (p.equals(product)) {
                p.decreaseQuantity();
                System.out.println(product.getName() + " wurde reduziert.");
                return p.getQuantity() == 0;
            }
            return false;
        });
        printCart();
    }

    /**
     * Entfernt alle Prodsukte aus dem Warenkorb und setzt ihn auf leer.
     */
    public void clearList() {
        myProducts.clear(); // Leert die Produktliste
        System.out.println("Warenkorb geleert.");
    }

    /**
     * Gibt alle Produkte im Warenkorb aus und zeigt den Gesamtbetrag an.
     * Falls der Warenkorb leer ist, wird eine entsprechende Nachricht ausgegeben.
     */
    public void printCart() {
        System.out.println("Warenkorb ID: " + id);
        if (myProducts.isEmpty()) {
            System.out.println("Der Warenkorb ist leer.");
        } else {
            myProducts.forEach(System.out::println); // Alle Produkte im Warenkorb ausgeben
            System.out.printf("Gesamtbetrag: %.2f €\n", getTotal()); // Gesamtpreis anzeigen
        }
        System.out.println("---------------------");
    }

    /**
     * Gibt eine Kopie der Produktliste zurück, um Manipulationen von außen zu verhindern.
     * @return Eine Liste der aktuellen Produkte im Warenkorb.
     */
    public List<Product> getMyProducts() {
        return new ArrayList<>(myProducts); // Schützt die interne Liste vor direkten Änderungen
    }
}