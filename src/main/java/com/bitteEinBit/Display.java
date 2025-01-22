package com.bitteEinBit;

import java.util.Scanner;

public class Display {
    Helper helper = new Helper();

    public void displayEntrySelection() {
        System.out.println("Please select a product via Product number:");
        System.out.println();
        helper.readFromJsonFile();

        for(Product product : helper.products) {
            System.out.println(product.getProductId() + ": " + product.getName());
        }
    }

    public void selectProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean productFound = false;
        while(!productFound) {
            try {
                //https://www.baeldung.com/java-scanner-integer
                int selectedProductId = Integer.parseInt(scanner.nextLine());
                for (Product product : helper.products) {
                    if (product.getProductId() == selectedProductId) {
                        System.out.println(product.getName());
                        System.out.println(product.getProductGroup());
                        System.out.println(product.getPrice());
                        productFound = true;
                        return;
                    }
                }

                if(!productFound) {
                    System.out.println("Product number was not found, please try again.");
                }
            }catch(NumberFormatException e) {
                System.out.println("Invalid input, please select a valid product from the list");
            }
        }

    }
}
