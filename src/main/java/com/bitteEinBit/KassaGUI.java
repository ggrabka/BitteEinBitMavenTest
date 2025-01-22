//TODO CKECK PACKAGE NAME
package com.bitteEinBit;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class KassaGUI extends Application {

    public KassaGUI() {
        // default constructor
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: black; -fx-border-color: black; -fx-border-width: 5px;");

        String[][] products = {
                //TODO READ FROM JSON
                //id, name, price
                {"1", "Product 1", "1,00 €"},
                {"2", "Product 2", "1,00 €"},
                {"3", "Product 3", "1,00 €"},
                {"4", "Product 4", "1,00 €"},

                //basic functions

                {"DEL", "DEL", "DEL"},
                {"EXIT", "EXIT", "EXIT"},

                //TODO READ FROM JSON
                //id, name, price
                {"5", "Product 5", "1,00 €"},
                {"6", "Product 6", "1,00 €"},
                {"7", "Product 7", "1,00 €"},
                {"8", "Product 8", "1,00 €"},

                //basic functions
                {"left", "←", "←"},
                {"right", "→", "→"},

                //TODO READ FROM JSON
                //id, name, price
                {"9", "Product 9", "1,00 €"},
                {"10", "Product 10", "1,00 €"},
                {"11", "Product 11", "1,00 €"},
                {"12", "Product 12", "1,00 €"},

                //basic functions
                {"plus", "+", "+"},
                {"minus", "-", "-"},

                //TODO READ FROM JSON
                //id, name, price
                {"13", "Product 13", "1,00 €"},
                {"14", "Product 14", "1,00 €"},
                {"15", "Product 15", "1,00 €"},
                {"16", "Product 16", "1,00 €"},

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
        //primaryStage.setFullScreen(true);
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

        return stackPane;
    }

    private void handleProductClick(String id, String product, String price) {
        System.out.println("------------------------------");
        System.out.println("product chosen");
        System.out.println("product id: " + id);
        System.out.println("product name: " + product);
        System.out.println("price:"+ price);
        //TODO CALL METHOD
    }

    private void handleBasicFunctionClick(String label) {
        System.out.println("------------------------------");
        System.out.println("basic function chosen");
        System.out.println("basic function: "+label);
        //TODO CALL METHOD
    }



    public static void main(String[] args) {
        launch(args);
    }
}