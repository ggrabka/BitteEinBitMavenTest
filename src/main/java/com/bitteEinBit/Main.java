package com.bitteEinBit;

public class Main {

    public static Product[] products = new Product[] {

            new Product("Mineralwasser (0,5l)", 2.50, 1),
            new Product("Kaffee", 3.00, 2),
            new Product("Apfelsaft (0,3l)", 2.80, 3),

            new Product("Tomatensuppe", 4.50, 4),
            new Product("Bruschetta", 5.00, 5),
            new Product("Caesar-Salat", 6.50, 6),

            new Product("Wiener Schnitzel mit Pommes", 14.90, 7),
            new Product("Spaghetti Carbonara", 12.50, 8),
            new Product("Vegetarisches Curry", 11.00, 9),

            new Product("Apfelstrudel mit Vanillesoße", 5.50, 10),
            new Product("Tiramisu", 6.00, 11),
            new Product("Schokoladenmousse", 5.80, 12),
    };

    public static void main(String[] args) {

        CashRegisterScreen cashRegisterScreen = new CashRegisterScreen();
        cashRegisterScreen.startForCustomer();
        cashRegisterScreen.printTransactions();
        Display display = new Display();

        display.displayEntrySelection();
        display.selectProduct();

       Product test = new Product(3.0, "Gebäck", "Weißbrot");
        Product test2 = new Product(3.0, "Gebäck", "Vollkornbrot");
        test.addProduct();
     //   test.removeProduct();
        test2.addProduct();
    }
    }