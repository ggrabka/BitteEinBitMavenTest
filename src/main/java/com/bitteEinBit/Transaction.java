package com.bitteEinBit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die `Transaction`-Klasse speichert eine Transaktion, die beim Bezahlvorgang in der Registrierkasse erstellt wird.
 * Jede Transaktion hat einne eindeutige ID, eine Liste der gekauften Produkte, den Gesamtbetrag und einen Zeitstempel.
 */
public class Transaction {
    private final int transactionId;      // Eindeutige Transaktions-ID
    private final List<Product> purchasedProducts; // Liste der gekauften Produkte
    private final double totalAmount;     // Gesamtbetrag der Transaktion
    private final LocalDateTime transactionTime; // Zeitpunkt der Transaktion

    /**
     * Konstruktor zur Erstellung einer neuen Transaktion.
     * @param transactionId Eindeutige ID der Transaktion.
     * @param cart Der Warenkorb, der zur Transaktion wird.
     */
    public Transaction(int transactionId, Cart cart) {
        this.transactionId = transactionId;
        this.purchasedProducts = List.copyOf(cart.getMyProducts()); // Kopiert die Produktliste, um Änderungen zu vermeiden
        this.totalAmount = cart.getTotal(); // Berechnet den Gesamtbetrag der Transaktion
        this.transactionTime = LocalDateTime.now(); // Speichert den aktuellen Zeitpunkt der Transaktion
    }

    // Getter-Methoden für die Transaktionsdaten
    public int getTransactionId() { return transactionId; }
    public double getTotal() { return totalAmount; }

    /**
     * Gibt eine formatierte Zeichenkette mit den Transaktionsdetails zurück.
     * Enthält die ID, den Zeitpunkt, die Liste der Produkte und den Gesamtbetrag.
     * @return Formatierter String mit Transaktionsinformationen.
     */
    @Override
    public String toString() {
        String productList = purchasedProducts.stream()
                .map(p -> String.format("[%d] %s x%d (%.2f €)", p.getProductId(), p.getName(), p.getQuantity(), p.getPrice()))
                .collect(Collectors.joining(", ")); // Erstellt eine formatierte Liste der gekauften Produkte

        return String.format("🛒 Transaktion %d | Zeit: %s | Produkte: %s | Summe: %.2f €",
                transactionId, transactionTime, productList, totalAmount);
    }
}