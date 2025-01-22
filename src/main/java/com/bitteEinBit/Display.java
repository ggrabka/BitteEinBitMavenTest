package com.bitteEinBit;

import java.util.List;
import java.util.Scanner;

/**
 * Die `Display`-Klasse ist für die Anzeige der verfügbaren Produkte und die Produktauswahl verantwortlich.
 * Sie greift auf die `Helper`-Klasse zu, um die Produkte aus der JSON-Datei zu laden.
 */
public class Display {
    private final Helper helper = new Helper();

    /**
     * Zeigt alle gespeicherten Produkte aus der JSON-Datei an.
     */
    public void displayProducts() {
        helper.readFromJsonFile();
        List<Product> products = helper.getProducts();

        if (products.isEmpty()) {
            System.out.println("Keine Produkte verfügbar.");
        } else {
            products.forEach(p -> System.out.println(p.getProductId() + ": " + p.getName()));
        }
    }

    /**
     * Erlaubt dem Benutzer, ein Produkt anhand der Produkt-ID auszuwählen.
     * Falls eine ungültige ID eingegeben wird, erscheint eine Fehlermeldung.
     * @return Das ausgewählte Produkt.
     */
    public Product selectProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = helper.getProducts();
        int minProductId = 1;
        int maxProductId = products.size();

        while (true) {
            System.out.print("Bitte die Produkt-ID eingeben (1-" + maxProductId + "): ");
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                scanner.nextLine();

                if (id >= minProductId && id <= maxProductId) {
                    for (Product product : products) {
                        if (product.getProductId() == id) {
                            return product;
                        }
                    }
                }
                System.out.println("⚠️  Ungültige Eingabe! Bitte eine Zahl zwischen 1 und " + maxProductId + " eingeben.");
            } else {
                System.out.println("⚠️  Ungültige Eingabe! Bitte eine gültige Produkt-ID eingeben.");
                scanner.next();
            }
        }
    }
}