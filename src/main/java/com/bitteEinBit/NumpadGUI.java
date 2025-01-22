package com.bitteEinBit;

//JavaFX

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NumpadGUI extends Application {

    public NumpadGUI() {
        // default constructor
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: black; -fx-border-color: black; -fx-border-width: 5px;");
        gridPane.setAlignment(Pos.CENTER); // Center the gridPane

        String[][] numpadKeys = {
                {"7", "7"},
                {"8", "8"},
                {"9", "9"},
                {"4", "4"},
                {"5", "5"},
                {"6", "6"},
                {"1", "1"},
                {"2", "2"},
                {"3", "3"},
                {"BACK", "←"},
                {"0", "0"},
                {"OK", "OK"}
        };

        int counter = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                StackPane cell;
                if (row == 3 && col == 0) {
                    cell = createBasicFunction(numpadKeys[counter][1], "red", primaryStage);
                } else if (row == 3 && col == 2) {
                    cell = createBasicFunction(numpadKeys[counter][1], "orange", primaryStage);
                } else {
                    cell = createCell(numpadKeys[counter][0], numpadKeys[counter][1]);
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

        //primaryStage.setFullScreen(true);
        primaryStage.show();

        // cell size according to stage size
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                StackPane cell = (StackPane) gridPane.getChildren().get(row * 3 + col);
                cell.prefWidthProperty().bind(Bindings.min(scene.widthProperty().divide(3), scene.heightProperty().divide(4)));
                cell.prefHeightProperty().bind(Bindings.min(scene.widthProperty().divide(3), scene.heightProperty().divide(4)));
            }
        }
    }

    private StackPane createCell(String id, String product) {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: white;");

        // create text element
        Text productText = new Text(product);
        productText.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;"); // Make text bold and 32px

        stackPane.getChildren().add(productText);

        stackPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            handleProductClick(id);
        });

        stackPane.setOnMousePressed(event -> stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: lightgrey;"));
        stackPane.setOnMouseReleased(event -> stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: white;"));

        return stackPane;
    }

    private StackPane createBasicFunction(String label, String color, Stage stage) {
        StackPane stackPane = new StackPane();
        if (color.equals("red")) {
            stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: red;");
        } else if (color.equals("orange")) {
            stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: rgb(255, 188, 0);");
        } else {
            stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: white;");
        }

        // create text element
        Text labelText = new Text(label);
        labelText.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;"); // Make text bold and 32px

        stackPane.getChildren().add(labelText);

        stackPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            handleBasicFunctionClick(label, stage);
        });

        stackPane.setOnMousePressed(event -> stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: lightgrey;"));
        stackPane.setOnMouseReleased(event -> {
            if (color.equals("red")) {
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: red;");
            } else if (color.equals("orange")) {
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: rgb(255, 188, 0);");
            } else {
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: white;");
            }
        });

        return stackPane;
    }

    private void handleProductClick(String id) {
        System.out.println(id);
    }

    private void handleBasicFunctionClick(String label, Stage stage) {
        System.out.println(label);
        simulateKeyPress(label);
        if (label.equals("OK")) {
            displaySummaryScreen(stage);
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
                case "0":
                    robot.keyPress(KeyEvent.VK_0);
                    robot.keyRelease(KeyEvent.VK_0);
                    break;
                case "DEL":
                    robot.keyPress(KeyEvent.VK_DELETE);
                    robot.keyRelease(KeyEvent.VK_DELETE);
                    break;
                case "OK":
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displaySummaryScreen(Stage stage) {
        StackPane summaryPane = new StackPane();
        summaryPane.setStyle("-fx-background-color: black;");
        summaryPane.setAlignment(Pos.CENTER);

        Text totalText = new Text("total 12,95€");
        totalText.setFill(Color.WHITE);
        totalText.setStyle("-fx-font-size: 50px;");
        totalText.setTextAlignment(TextAlignment.CENTER);

        Text paidText = new Text("← 20,00€");
        paidText.setFill(Color.rgb(255, 188, 0));
        paidText.setStyle("-fx-font-size: 50px;");
        paidText.setTextAlignment(TextAlignment.CENTER);

        Text changeText = new Text("→ 7,05€");
        changeText.setFill(Color.RED);
        changeText.setStyle("-fx-font-size: 50px;");
        changeText.setTextAlignment(TextAlignment.CENTER);

        GridPane textGrid = new GridPane();
        textGrid.setAlignment(Pos.CENTER);
        textGrid.setVgap(40); // Increase vertical gap between lines
        textGrid.add(totalText, 0, 0);
        textGrid.add(paidText, 0, 1);
        textGrid.add(changeText, 0, 2);

        textGrid.add(changeText, 0, 2);

        summaryPane.getChildren().add(textGrid);

        Scene summaryScene = new Scene(summaryPane, 800, 600);
        stage.setScene(summaryScene);
        //stage.setFullScreen(true);

        // Schedule text appearance with 3-second intervals
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> Platform.runLater(() -> totalText.setVisible(true)), 0, TimeUnit.SECONDS);
        executor.schedule(() -> Platform.runLater(() -> paidText.setVisible(true)), 3, TimeUnit.SECONDS);
        executor.schedule(() -> Platform.runLater(() -> changeText.setVisible(true)), 6, TimeUnit.SECONDS);
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}