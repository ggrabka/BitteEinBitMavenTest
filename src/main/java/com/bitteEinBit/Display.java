package com.bitteEinBit;

import java.util.List;
import java.util.Scanner;

/**
 * Die `Display`-Klasse ist für die Anzeige der verfügbaren Produkte und die Produktauswahl verantwortlich.
 * Sie greift auf die `Heslper`-Klasse zu, um die Produkte aus der JSON-Datei zu laden.
 */
public class Display {
    private final Helper helper = new Helper(); // Instanz der Helper-Klasse für den Zugriff auf gespeicherte Produkte

    /**
     * Zeigt alle gespeicherten Produkte aus der JSON-Datei an.
     * Falls keine Produkte vorhanden sind, wird eine entsprechende Nachricht ausgegeben.
     */
    public void displayProducts() {
        helper.readFromJsonFile(); // Lädt Produkte aus der Datei in die Liste
        List<Product> products = helper.getProducts(); // Ruft die aktuelle Produktliste ab

        if (products.isEmpty()) {
            System.out.println("Keine Produkte verfügbar.");
        } else {
            products.forEach(p -> System.out.println(p.getProductId() + ": " + p.getName())); // Gibt jedes Produkt aus
        }
    }

    /**
     * Erlaubt dem Benutzer, ein Produkt anhand der Produkt-ID auszuwählen.
     * Falls eine falsche ID eingegeben wird, wird erneut nach einer Eingabe gefragt.
     * @return Das ausgewählte Produkt.
     */
    public Product selectProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = helper.getProducts(); // Ruft die aktuelle Produktliste ab

        while (true) {
            try {
                int id = Integer.parseInt(scanner.nextLine()); // Liest die Benutzer-Eingabe (Produkt-ID)
                for (Product product : products) {
                    if (product.getProductId() == id) {
                        return product; // Gibt das Produkt zurück, falls die ID gefunden wurde
                    }
                }
                System.out.println("Produkt nicht gefunden. Erneut eingeben:");
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben."); // Falls keine Zahl eingegeben wurde
            }
        }
    }
}