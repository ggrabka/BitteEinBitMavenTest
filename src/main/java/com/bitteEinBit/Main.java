package com.bitteEinBit;

/**
 * Die `Main`-Klasse startet das Kasssensystem und ermöglicht die Interaktion mit der Kasse.
 */
public class Main {
    public static void main(String[] args) {
        CashRegisterScreen cashRegisterScreen = new CashRegisterScreen();
        cashRegisterScreen.startForCustomer(); // Startet die Kassenoberfläche
    }
}