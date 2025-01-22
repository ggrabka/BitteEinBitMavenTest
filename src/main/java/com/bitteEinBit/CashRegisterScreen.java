package com.bitteEinBit;

import java.util.ArrayList;
import java.util.Scanner;

public class CashRegisterScreen {

    Scanner button = new Scanner(System.in);

    private int nextCartId = 1;
    private Cart cart = new Cart(0);
    private final ArrayList<Cart> completedCarts;

    Product products = new Product(Main.products);
    Product product = null;

    public CashRegisterScreen() {

        this.completedCarts = new ArrayList<>();
    }

    public Cart createNewCart() {

        return new Cart(nextCartId++);
    }

    public void displayCashRegisterScreenTop() {

        System.out.println("\n\n\n");
        System.out.println("  ========================= ");
        System.out.println("       REGISTRIERKASSE      ");
        System.out.println("  ========================= ");
    }

    public void displayCashRegisterScreenBottom() {

        System.out.println("  ------------------------- ");
        cart.printMyList();
        System.out.println("  ========================= ");
        System.out.printf("        BETRAG: %.2f €\n", cart.getTotal());
        System.out.println("  ------------------------- ");
        System.out.println(
                        "  |   1   2   3   |   #   | \n" +
                        "  |   4   5   6   |   +   | \n" +
                        "  |   7   8   9   |   -   | \n" +
                        "  |   C   0   OK  |   #   | \n" +
                        "  |   ,           |   #   | \n" +
                        "  ------------------------- \n" +
                        "    MENU  |    BEZAHLEN            \n" +
                        "  ------------------------- \n" +
                        "    EXIT  |    LÖSCHEN      \n" +
                        "  ------------------------- ");
    }

    public void caseAdd() {

        if (product == null) {

            System.out.println(" Sie haben noch kein Produkt ausgewählt");
        } else {

            cart.addProduct(product);

            System.out.println(" In Ihrem Warenkorb haben Sie " + product.getQuantity() +
                    " Stück von " + product.getName());
        }
    }

    public void caseRemove() {

        if (product == null) {

            System.out.println(" Sie haben noch kein Produkt ausgewählt");
        } else {

            if (product.getQuantity() == 0) {

                System.out.println(" Dieses Produkt (" + product.getName() +
                        ") befindet sich nicht in Ihrem Warenkorb");
            } else {

                cart.removeProduct(product);

                System.out.println(" Im Warenkorb haben Sie " + product.getQuantity() + " Stück von " + product.getName());
            }
        }
    }

    public void caseClear() {

        if (cart.getMyProducts().isEmpty()) {

            displayCashRegisterScreenTop();
            System.out.println(" Ihr Warenkorb ist noch leer");
            displayCashRegisterScreenBottom();
        } else {

            char clear;

            displayCashRegisterScreenTop();
            System.out.println(" Möchten Sie alle Produkte aus Ihrem Warenkorb entfernen?\n" +
                    " C : Zurück || O : Weiter ");
            displayCashRegisterScreenBottom();

            do {
                clear = button.next().charAt(0);
            } while (clear != 'O' && clear != 'o' && clear != 'C' && clear != 'c');

            displayCashRegisterScreenTop();
            if (clear == 'O' || clear == 'o') {

                cart.clearList();
            }
            displayCashRegisterScreenBottom();
        }
    }

    public boolean casePay() {

        if (cart.getMyProducts().isEmpty()) {

            displayCashRegisterScreenTop();
            System.out.println(" Sie haben noch nichts zu bezahlen");
            displayCashRegisterScreenBottom();
        } else {

            char pay;

            displayCashRegisterScreenTop();
            System.out.println(" Möchten Sie den Betrag bezahlen?\n" +
                    " C : Zurück || O : Weiter ");
            displayCashRegisterScreenBottom();

            do {
                pay = button.next().charAt(0);
            } while (pay != 'O' && pay != 'o' && pay != 'C' && pay != 'c');

            if (pay == 'O' || pay == 'o') {

                boolean isPaid = false;
                char choice;
                String choiceString = "";
                double receivedAmount, changeToReturn, fullAmountReceived = 0.0;
                double total = cart.getTotal();

                displayCashRegisterScreenTop();
                displayCashRegisterScreenBottom();

                System.out.println(" // Der Bezahlvorgang beginnt...");
                System.out.println(" // Bitte geben Sie den Betrag ein...");

                while(!isPaid) {

                    choice = button.next().charAt(0);

                    while(choice == ',' && choiceString.isEmpty()) {
                        System.out.println("Bitte einen Betrag vor dem Komma eingeben");
                        choice = button.next().charAt(0);
                    }

                    if ((choice >= '0' && choice <= '9') || choice == ',') {
                        if(choice == ',') {
                            choice = '.';
                        }
                        choiceString += choice;

                        displayCashRegisterScreenTop();
                        System.out.println(choiceString);
                        displayCashRegisterScreenBottom();
                    }

                    if (!choiceString.isEmpty() && (choice == 'o' || choice == 'O')) {

                        receivedAmount = Double.parseDouble(choiceString);
                        fullAmountReceived += receivedAmount;
                        changeToReturn = receivedAmount - total;

                        if (receivedAmount >= total) {

                            displayCashRegisterScreenTop();
                            System.out.printf(" Gesamtbetrag: %.2f €\n Erhalten: %.2f €\n Rückgeld: %.2f €\n\n " +
                                    " Vielen Dank! Auf Wiedersehen!\n", cart.getTotal(), fullAmountReceived, changeToReturn);
                            displayCashRegisterScreenBottom();

                            isPaid = true;

                        } else {

                            changeToReturn *= (-1);

                            displayCashRegisterScreenTop();
                            System.out.printf(" Sie müssen noch %.2f € bezahlen", changeToReturn);
                            displayCashRegisterScreenBottom();

                            total = changeToReturn;
                            choiceString = "";

                        }
                    } else if (!choiceString.isEmpty() && (choice == 'c' || choice == 'C')) {

                        displayCashRegisterScreenTop();
                        choiceString = "";
                        displayCashRegisterScreenBottom();
                    }
                }

                completedCarts.add(cart);
                startForCustomer();

                return false;

            } else {

                displayCashRegisterScreenTop();
                displayCashRegisterScreenBottom();
            }
        }

        return true;
    }

    public boolean caseExit() {

        char exit;

        displayCashRegisterScreenTop();
        System.out.println(" Möchten Sie den Bestellvorgang beenden?\n" +
                " C : Zurück || O : Weiter ");
        displayCashRegisterScreenBottom();

        do {
            exit = button.next().charAt(0);
        } while (exit != 'O' && exit != 'o' && exit != 'C' && exit != 'c');

        if (exit == 'O' || exit == 'o') {

            if (cart.getMyProducts().isEmpty()) {

                displayCashRegisterScreenTop();
                System.out.println(" // Ihr Bestellvorgang wird beendet...\n" +
                        " // AUF WIEDERSEHEN!");
                displayCashRegisterScreenBottom();

                completedCarts.add(cart);
                startForCustomer();

                return false;

            } else {

                casePay();
            }

        } else {

            displayCashRegisterScreenTop();
            displayCashRegisterScreenBottom();
        }

        return true;
    }

    public Product findProductById(int id) {

        for (Product product : Main.products) {

            if (product.getProductId() == id) {

                return product;
            }
        }

        return null;
    }

    public boolean checkId(int number) {

        for (Product product : Main.products) {

            if (product.getProductId() == number) {

                return true;
            }
        }

        displayCashRegisterScreenTop();
        System.out.println(" Kein Produkt mit dieser ID gefunden.");
        displayCashRegisterScreenBottom();

        return false;
    }

    public void run() {

        boolean inSystem = true;

        displayCashRegisterScreenTop();
        displayCashRegisterScreenBottom();

        char choice;
        String choiceString = "";
        int number;

        while(inSystem) {

            choice = button.next().charAt(0);

            if (choice >= '0' && choice <= '9') {

                choiceString += choice;

                displayCashRegisterScreenTop();
                System.out.println(choiceString);
                displayCashRegisterScreenBottom();
            }

            if (!choiceString.isEmpty() && (choice == 'o' || choice == 'O')) {

                number = Integer.parseInt(choiceString);
                choiceString = "";

                boolean idExists = checkId(number);

                if (idExists) {

                    product = findProductById(number);

                    displayCashRegisterScreenTop();
                    product.printProduct();
                    displayCashRegisterScreenBottom();
                }
            }
            else if (!choiceString.isEmpty() && (choice == 'c' || choice == 'C')) {

                displayCashRegisterScreenTop();
                choiceString = "";
                displayCashRegisterScreenBottom();
            } else {

                switch (choice) {

                    case '*':
                    case '+':
                        displayCashRegisterScreenTop();
                        caseAdd();
                        displayCashRegisterScreenBottom();
                        break;

                    case '_':
                    case '-':
                        displayCashRegisterScreenTop();
                        caseRemove();
                        displayCashRegisterScreenBottom();
                        break;

                    case 'b':
                    case 'B':
                        inSystem = casePay();
                        break;

                    case 'e':
                    case 'E':
                        inSystem = caseExit();
                        break;

                    case 'l':
                    case 'L':
                        caseClear();
                        break;

                    case '\'':
                    case '#':
                        displayCashRegisterScreenTop();
                        cart.printMyList();
                        displayCashRegisterScreenBottom();
                        break;

                    case 'm':
                    case 'M':
                        displayCashRegisterScreenTop();
                        products.displayProducts();
                        displayCashRegisterScreenBottom();
                        break;

                    default:
                        break;
                }
            }
        }
    }
    public void printTransactions() {

        System.out.println(" -- Zusammenfassung der Transaktionen -- \n");

        double totalRevenue = 0.0;

        for (Cart cart : completedCarts) {

            totalRevenue += cart.getTotal();
            cart.printCustomersCart();
        }

        System.out.println();
        System.out.printf(" Gesamteinnahmen: %.2f €\n", totalRevenue);
    }

    public void startForCustomer() {

        {
            product = null;
            cart = createNewCart();
        }

        displayCashRegisterScreenTop();
        System.out.println(" Möchten Sie den Screen benutzen?\n" +
                " C : Zurück || O : Weiter ");
        displayCashRegisterScreenBottom();

        char start;

        do {
            start = button.next().charAt(0);
        } while (start != 'O' && start != 'o' && start != 'C' && start != 'c');

        if (start == 'O' || start == 'o') {

            System.out.println(" Neuer Warenkorb erstellt! ID: " + cart.getId());

            run();
        }
    }
}