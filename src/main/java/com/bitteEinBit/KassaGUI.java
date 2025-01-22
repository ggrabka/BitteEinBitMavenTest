package com.bitteEinBit;

//JavaFX
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

//keystrokes
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KassaGUI extends Application {

    public KassaGUI() {
        // default constructor
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: black; -fx-border-color: black; -fx-border-width: 5px;");
        gridPane.setAlignment(Pos.CENTER); // Center the gridPane

        String[][] products = {
                //TODO READ FROM JSON
                //id, name, price
                {"1", "Product 1", "1,00 €"},
                {"2", "Product 2", "2,00 €"},
                {"3", "Product 3", "3,00 €"},
                {"4", "Product 4", "4,00 €"},

                //basic functions

                {"DEL", "DEL", "DEL"},
                {"EXIT", "EXIT", "EXIT"},

                //TODO READ FROM JSON
                //id, name, price
                {"5", "Product 5", "5,00 €"},
                {"6", "Product 6", "6,00 €"},
                {"7", "Product 7", "7,00 €"},
                {"8", "Product 8", "8,00 €"},

                //basic functions
                {"left", "←", "←"},
                {"right", "→", "→"},

                //TODO READ FROM JSON
                //id, name, price
                {"9", "Product 9", "9,00 €"},
                {"10", "Product 10", "10,00 €"},
                {"11", "Product 11", "11,00 €"},
                {"12", "Product 12", "12,00 €"},

                //basic functions
                {"PLUS", "+", "+"},
                {"MINUS", "-", "-"},

                //TODO READ FROM JSON
                //id, name, price
                {"13", "Product 13", "13,00 €"},
                {"14", "Product 14", "14,00 €"},
                {"15", "Product 15", "15,00 €"},
                {"16", "Product 16", "16,00 €"},

                //basic functions
                {"MENU", "MENU", "MENU"},
                {"PAY", "PAY", "PAY"},
        };

        int counter = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 6; col++) {
                StackPane cell;
                if (col >= 4) {
                    cell = createBasicFunction(products[counter][2], counter);
                } else {
                    cell = createCell(products[counter][0], products[counter][1], products[counter][2], counter);
                }
                gridPane.add(cell, col, row);
                counter++;
            }
        }

        StackPane root = new StackPane(gridPane);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        //TODO UNCOMMENT
        primaryStage.setFullScreen(true);
        primaryStage.show();

        // cell size according to stage size
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 6; col++) {
                StackPane cell = (StackPane) gridPane.getChildren().get(row * 6 + col);
                cell.prefWidthProperty().bind(Bindings.min(scene.widthProperty().divide(6), scene.heightProperty().divide(4)));
                cell.prefHeightProperty().bind(Bindings.min(scene.widthProperty().divide(6), scene.heightProperty().divide(4)));
            }
        }
    }

    private StackPane createCell(String id, String product, String price, int counter) {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: white;");

        // create text elements
        Text productText = new Text(product);
        productText.setStyle("-fx-font-size: 16px;"); // Make text bigger
        Text priceText = new Text(price);
        priceText.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;"); // Make text bigger

        // text elements added to VBox for vertical stacking
        VBox vbox = new VBox(productText, priceText);
        vbox.setAlignment(Pos.CENTER);

        // red cells
        if (counter == 4 || counter == 5) {
            productText.setStyle("-fx-font-size: 16px; -fx-fill: white;");
            priceText.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-fill: white;");
        }

        stackPane.getChildren().add(vbox);

        stackPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            handleProductClick(id, product, price);
        });

        stackPane.setOnMousePressed(event -> stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: lightgrey;"));
        stackPane.setOnMouseReleased(event -> stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: white;"));

        return stackPane;
    }

    private StackPane createBasicFunction(String label, int counter) {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: rgb(255, 188, 0);");

        if (counter == 4 || counter == 5) {
            stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: red;");
        }

        // create text element
        Text labelText = new Text(label);
        labelText.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        // colored cells
        if (counter == 10 || counter == 11 || counter == 16 || counter == 17) {
            labelText.setStyle("-fx-font-weight: bold; -fx-font-size: 40px; -fx-fill: white;");
        }

        stackPane.getChildren().add(labelText);

        stackPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            handleBasicFunctionClick(label);
        });

        stackPane.setOnMousePressed(event -> stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: lightgrey;"));
        stackPane.setOnMouseReleased(event -> {
            if (counter == 4 || counter == 5) {
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: red;");
            } else if (counter == 10 || counter == 11 || counter == 16 || counter == 17) {
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: rgb(255, 188, 0);");
            } else {
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: white;");
            }
        });

        return stackPane;
    }

    private void handleProductClick(String id, String product, String price) {
//        System.out.println("------------------------------");
//        System.out.println("product chosen");
        System.out.println(id);
//        System.out.println("product name: " + product);
//        System.out.println("price:"+ price);
//        simulateKeyPress(id);
    }

    private void handleBasicFunctionClick(String label) {
//        System.out.println("------------------------------");
//        System.out.println("basic function chosen");
        System.out.println(label);
        if ("PAY".equals(label)) {
            openNewWindow();
        }
        else {
            simulateKeyPress(label);
        }
    }

    private void openNewWindow() {
        Stage newStage = new Stage();
        NumpadGUI NumpadGUI = new NumpadGUI();
        try {
            NumpadGUI.start(newStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void simulateKeyPress(String key) {
        try {
            Robot robot = new Robot();
            switch (key) {
                case "1":
                    robot.keyPress(KeyEvent.VK_1);
                    robot.keyRelease(KeyEvent.VK_1);
                    break;
                case "2":
                    robot.keyPress(KeyEvent.VK_2);
                    robot.keyRelease(KeyEvent.VK_2);
                    break;
                case "3":
                    robot.keyPress(KeyEvent.VK_3);
                    robot.keyRelease(KeyEvent.VK_3);
                    break;
                case "4":
                    robot.keyPress(KeyEvent.VK_4);
                    robot.keyRelease(KeyEvent.VK_4);
                    break;
                case "5":
                    robot.keyPress(KeyEvent.VK_5);
                    robot.keyRelease(KeyEvent.VK_5);
                    break;
                case "6":
                    robot.keyPress(KeyEvent.VK_6);
                    robot.keyRelease(KeyEvent.VK_6);
                    break;
                case "7":
                    robot.keyPress(KeyEvent.VK_7);
                    robot.keyRelease(KeyEvent.VK_7);
                    break;
                case "8":
                    robot.keyPress(KeyEvent.VK_8);
                    robot.keyRelease(KeyEvent.VK_8);
                    break;
                case "9":
                    robot.keyPress(KeyEvent.VK_9);
                    robot.keyRelease(KeyEvent.VK_9);
                    break;
                case "10":
                    robot.keyPress(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_0);
                    break;
                case "DEL":
                    robot.keyPress(KeyEvent.VK_DELETE);
                    robot.keyRelease(KeyEvent.VK_DELETE);
                    break;
                case "EXIT":
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                    break;
                case "left":
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                    break;
                case "right":
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    break;
                case "PLUS":
                    robot.keyPress(KeyEvent.VK_PLUS);
                    robot.keyRelease(KeyEvent.VK_PLUS);
                    break;
                case "MINUS":
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_MINUS);
                    break;
                case "MENU":
                    robot.keyPress(KeyEvent.VK_M);
                    robot.keyRelease(KeyEvent.VK_M);
                    break;
                case "PAY":
                    robot.keyPress(KeyEvent.VK_P);
                    robot.keyRelease(KeyEvent.VK_P);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}