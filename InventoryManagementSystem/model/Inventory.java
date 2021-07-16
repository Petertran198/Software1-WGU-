package InventoryManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    /** An array list of all the Parts */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /** An array list of all the Product */
    private static ObservableList<Product> allProduct = FXCollections.observableArrayList();

    /** -----------------For Parts ---------------------------------------*/
    /** Returns a list of all the parts*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**Add a Part to allParts Inventory list */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

}
