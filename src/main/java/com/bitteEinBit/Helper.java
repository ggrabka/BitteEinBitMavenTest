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

/**
 * Die `Helper`-Klasse ist für das Laden und Speichern von Produkten in einer JSON-Datei verantwortlich.
 */
public class Helper {
    private static final String PRODUCTS_JSON = "products.json"; // Speicherort der Produktdaten
    private List<Product> products = new ArrayList<>(); // Liste der geladenen Produkte
    private final Gson gson = new Gson(); // JSON-Konverter für die Umwandlung zwischen Java-Objekten und JSON

    /**
     * Liest die gespeicherten Produkte aus der JSON-Datei und speichert sie in der `products`-Liste.
     * Falls die Datei nicht exisstiert oder leer ist, wird eine leere Liste verwendet.
     */
    public void readFromJsonFile() {
        try (FileReader reader = new FileReader(PRODUCTS_JSON)) {
            Type type = new TypeToken<ArrayList<Product>>() {}.getType();
            List<Product> loadedProducts = gson.fromJson(reader, type);
            if (loadedProducts != null) {
                products = loadedProducts;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Noch keine Produkte gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Produktdatei.");
        }
    }

    /**
     * Gibt eine Kopie der aktuellen Produktliste zurück, um Manipulationen zu verhindern.
     * @return Eine Liste der gespeicherten Produkte.
     */
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Fügt ein neues Produkt hinzu oder aktualisiert ein vorhandenes Produkt anhand der ID.
     * @param product Das hinzuzufügende oder zu aktualisierende Produkt.
     */
    public void addOrUpdateProduct(Product product) {
        products.removeIf(p -> p.getProductId() == product.getProductId()); // Entfernt das Produkt, falls es bereits existiert
        products.add(product);
        writeToJsonFile();
    }

    /**
     * Entfernt ein Produkt aus der JSON-Datei anhand der ID.
     * @param product Das zu entfernende Produkt.
     */
    public void removeProduct(Product product) {
        if (products.removeIf(p -> p.getProductId() == product.getProductId())) {
            writeToJsonFile();
            System.out.println("Produkt erfolgreich entfernt.");
        } else {
            System.out.println("Kein Produkt gefunden.");
        }
    }

    /**
     * Speichert die aktuelle Produktliste in die JSON-Datei.
     */
    private void writeToJsonFile() {
        try (FileWriter writer = new FileWriter(PRODUCTS_JSON)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der Produktdatei.");
        }
    }
}