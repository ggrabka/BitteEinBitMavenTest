package com.bitteEinBit;

import java.util.Scanner;

/**
 * Die `CashRegisterScreen`-Klasse stellt die Benutzeroberfläche für die Registrierkasse bereit.
 * Hier können Kunden Produkte zum Warenkorb hinzufügen, entfernen und den Bezahlvorgang durchführen.
 */
public class CashRegisterScreen {
    private int nextCartId = 1; // Zähler für die Warenkorb-IDs
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
            System.out.println("\n1: Produkt hinzufügen | 2: Produkt entfernen | 3: Warenkorb anzeigen | 4: Bezahlen | 5: Kassasturz | 6: Beenden");

            int choice = getUserChoice(1, 6); // 🔥 Verbesserte Methode zur Eingabevalidierung

            switch (choice) {
                case 1 -> addProduct(); // Produkt hinzufügen
                case 2 -> removeProduct(); // Produkt entfernen
                case 3 -> cart.printCart(); // Warenkorb anzeigen
                case 4 -> handlePayment(); // Bezahlvorgang starten
                case 5 -> transactionManager.endOfDayReport(); // Tagesabschlussbericht anzeigen
                case 6 -> {
                    System.out.println("Registrierkasse beendet.");
                    running = false; // Beendet die Schleife und somit das Programm
                }
            }
        }
    }

    /**
     * Fordert den Benutzer auf, eine gültige Zahl in einem bestimmten Bereich einzugeben.
     * Verhindert ungültige Eingaben wie Buchstaben oder Sonderzeichen.
     * @param min Mindestwert (z. B. 1 für das Menü)
     * @param max Maximalwert (z. B. 6 für das Menü)
     * @return Eine gültige Zahl im Bereich [min, max].
     */
    private int getUserChoice(int min, int max) {
        while (true) {
            System.out.print("Bitte eine Zahl von " + min + " bis " + max + " eingeben: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Verhindert Scanner-Probleme

                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("⚠️  Ungültige Eingabe! Bitte eine Zahl von " + min + " bis " + max + " eingeben.");
                }
            } else {
                System.out.println("⚠️  Ungültige Eingabe! Nur Zahlen von " + min + " bis " + max + " sind erlaubt.");
                scanner.next(); // Entfernt die falsche Eingabe
            }
        }
    }

    /**
     * Ermöglicht dem Kunden, ein Produkt zum Warenkorb hinzuzufügen.
     */
    private void addProduct() {
        Display display = new Display();
        display.displayProducts();
        System.out.print("Produkt-ID: ");
        Product product = display.selectProduct();
        cart.addProduct(product);
    }

    /**
     * Ermöglicht dem Kunden, ein Produkt aus dem Warenkorb zu entfernen.
     */
    private void removeProduct() {
        Display display = new Display();
        display.displayProducts();
        System.out.print("Produkt-ID: ");
        Product product = display.selectProduct();
        cart.removeProduct(product);
    }

    /**
     * Führt den Bezahlvorgang durch und speichert die Transaktion.
     * Es wird sichergestellt, dass nur numerische Eingaben erfolgen.
     */
    private void handlePayment() {
        double totalAmount = cart.getTotal(); // Gesamtsumme berechnen
        System.out.printf("\n🛒 Gesamtbetrag: %.2f €\n", totalAmount);

        double receivedAmount = getValidPayment(totalAmount); // 🔥 Verbesserte Methode für Eingabevalidierung

        double change = receivedAmount - totalAmount; // Wechselgeld berechnen
        System.out.printf("💶 Wechselgeld: %.2f €\n", change);

        transactionManager.saveTransaction(cart); // Transaktion speichern
        cart.clearList(); // Warenkorb leeren
    }

    /**
     * Überprüft die Eingabe des Bezahlbetrags.
     * Es sind nur Zahlen erlaubt, und der Betrag muss mindestens die Gesamtsumme sein.
     * @param minAmount Der minimale Betrag (Gesamtsumme des Warenkorbs).
     * @return Ein gültiger Betrag, der mindestens `minAmount` beträgt.
     */
    private double getValidPayment(double minAmount) {
        while (true) {
            System.out.print("💰 Bitte den gezahlten Betrag eingeben: ");
            String input = scanner.next(); // Eingabe als String einlesen

            // **Überprüft, ob die Eingabe nur Zahlen und maximal ein Komma oder Punkt enthält**
            if (input.matches("\\d+(\\.\\d{1,2})?|\\d+(,\\d{1,2})?")) {
                input = input.replace(",", "."); // Falls ein Komma benutzt wurde, in einen Punkt umwandeln
                double amount = Double.parseDouble(input);

                if (amount >= minAmount) { // 🔥 Betrag muss mindestens die Gesamtsumme sein!
                    return amount; // Gültige Eingabe zurückgeben
                } else {
                    System.out.printf("⚠️  Fehler: Der eingegebene Betrag (%.2f €) ist zu niedrig. Bitte mindestens %.2f € eingeben.\n", amount, minAmount);
                }
            } else {
                System.out.println("⚠️  Fehler: Ungültige Eingabe! Bitte nur Zahlen (z. B. 10.50 oder 10,50) eingeben.");
            }
        }
    }
}