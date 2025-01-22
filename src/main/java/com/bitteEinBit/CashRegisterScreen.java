package com.bitteEinBit;

import java.util.Scanner;

/**
 * Die `CashRegissterScreen`-Klasse stellt die Benutzeroberfläche für die Registrierkasse bereit.
 * Hier können Kunden Produkte zum Warenkorb hinzufügen, entfernen und den Bezahlvorgang durchführen.
 */
public class CashRegisterScreen {
    private int nextCartId = 1; // Variable zur Generierung eindeutiger Warenkorb-IDs
    private Cart cart = new Cart(0); // Der aktuelle Warenkorb des Kunden
    private final Scanner scanner = new Scanner(System.in); // Scanner für Benutzereingaben
    private final TransactionManager transactionManager = new TransactionManager(); // Verwalter für Transaktionen

    /**
     * Erstellt einen neuen Warenkorb mit einer eindeutigen ID.
     * @return Der neue Warenkorb.
     */
    public Cart createNewCart() {
        return new Cart(nextCartId++);
    }

    /**
     * Startet die Benutzeroberfläche für die Kasse.
     * Der Kunde kann Produkte hinzufügen, entfernen und bezahlen.
     */
    public void startForCustomer() {
        cart = createNewCart(); // Erzeugt einen neuen Warenkorb für den Kunden
        System.out.println("Neuer Warenkorb erstellt! ID: " + cart.getId());

        boolean running = true;
        while (running) {
            // Zeigt das Hauptmenü der Kasse an
            System.out.println("\n1: Produkt hinzufügen | 2: Produkt entfernen | 3: Warenkorb anzeigen | 4: Bezahlen | 5: Kassasturz | 6: Beenden");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Verhindert Scanner-Fehlverhalten

            switch (choice) {
                case 1 -> addProduct(); // Produkt hinzufügen
                case 2 -> removeProduct(); // Produkt entfernen
                case 3 -> cart.printCart(); // Warenkorb anzeigen
                case 4 -> handlePayment(); // Bezahlvorgang starten
                case 5 -> transactionManager.endOfDayReport(); // Tagesabschlussbericht anzeigen
                case 6 -> {
                    System.out.println("Registrierkasse beendet.");
                    running = false; // Schleife beenden, um das Programm zu stoppen
                }
            }
        }
    }

    /**
     * Ruft die Produktliste auf und erlaubt dem Kunden, ein Produkt zum Warenkorb hinzuzufügen.
     */
    private void addProduct() {
        Display display = new Display();
        display.displayProducts(); // Zeigt verfügbare Produkte an
        System.out.print("Produkt-ID: ");
        Product product = display.selectProduct(); // Wählt ein Produkt basierend auf der ID aus
        cart.addProduct(product); // Fügt das ausgewählte Produkt zum Warenkorb hinzu
    }

    /**
     * Ruft die Produktliste auf und erlaubt dem Kunden, ein Produkt aus dem Warenkorb zu entfernen.
     */
    private void removeProduct() {
        Display display = new Display();
        display.displayProducts(); // Zeigt verfügbare Produkte an
        System.out.print("Produkt-ID: ");
        Product product = display.selectProduct(); // Wählt ein Produkt basierend auf der ID aus
        cart.removeProduct(product); // Entfernt das Produkt aus dem Warenkorb
    }

    /**
     * Führt den Bezahlvorgang für den aktuellen Warenkorb durch.
     * Der Kunde gibt den gezahlten Betrag ein, und das Wechselgeld wird berechnet.
     * Anschließend wird die Transaktion gespeichert und der Warenkorb geleert.
     */
    private void handlePayment() {
        double totalAmount = cart.getTotal();
        System.out.printf("\nGesamtbetrag: %.2f €\n", totalAmount);
        System.out.print("Gezahlter Betrag: ");
        double receivedAmount = scanner.nextDouble(); // Eingabe des gezahlten Betrags durch den Kunden
        System.out.printf("Wechselgeld: %.2f €\n", receivedAmount - totalAmount); // Berechnung des Wechselgelds
        transactionManager.saveTransaction(cart); // Speichert die Transaktion in der JSON-Datei
        cart.clearList(); // Leert den Warenkorb nach der Zahlung
    }
}