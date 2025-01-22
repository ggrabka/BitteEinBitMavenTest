package com.bitteEinBit;

import java.util.List;

public class Main {

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