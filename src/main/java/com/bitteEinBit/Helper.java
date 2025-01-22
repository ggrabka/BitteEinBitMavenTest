package com.bitteEinBit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Helper {

    private boolean isOverwritten;
    int counter = 0;
    private static final String PRODUCTS_JSON = "products.json";

    List<Product> products = new ArrayList<>();
    Gson gson = new Gson();

    void readFromJsonFile() {
        try {
            FileReader reader = new FileReader(PRODUCTS_JSON);
            Type type = new TypeToken<ArrayList<Product>>() {}.getType();
            products = gson.fromJson(reader, type);
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error in creating a FileReader object.");
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void checkIfProductExistsInJsonFile(Product product) {
        Scanner scanner = new Scanner(System.in);
        for (Product currentProduct : products) {
            if (currentProduct.getName().equals(product.getName())) {
                System.out.println("Product with the same name already exist. Do you wish to overwrite to the product? y/n");
                String input = scanner.next();
                while(!input.equals("y") && !input.equals("n")) {
                    System.out.println("Wrong input, please enter y or n");
                    input = scanner.next();
                }
                if(input.equals("y")) {
                    currentProduct.setName(product.getName());
                    currentProduct.setPrice(product.getPrice());
                    currentProduct.setProductGroup(product.getProductGroup());
                    isOverwritten = true;
                    break;
                } else {
                    return;
                }
            }
            counter++;
        }
    }

    void addProductToJsonFile(Product product) {
        int productId = products.size();
        for(Product currentProduct : products) {
            if(currentProduct.getProductId()>=products.size()) {
                productId++;
            }
        }
        product.setProductId(productId);
        if(!isOverwritten && counter == products.size()) {
            products.add(product);
        }
        writeToJsonFile();
    }

    void removeProductFromJsonFile(Product product) {
        for (Product currentProduct : products) {
            if (currentProduct.getName().equals(product.getName())) {
                products.remove(product);
                System.out.println("Product removed successfully.");
                break;
            }
        }
        if((!isOverwritten)) {
            System.out.println("No product found with the provided parameters!");
        }
        writeToJsonFile();
    }

    void writeToJsonFile() {
        try {
            FileWriter writer = new FileWriter(PRODUCTS_JSON);
            gson.toJson(products,writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error in writing the file.");
        }
    }
}
