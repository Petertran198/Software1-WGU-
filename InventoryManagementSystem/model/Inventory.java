package InventoryManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    /** An array list of all the Parts */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /** An array list of all the Product */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** -----------------For Parts ---------------------------------------*/
    /** Returns a list of all the parts*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**Add a Part to allParts Inventory list */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** Delete a part return T/F depending if deleted */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /** -----------------For Product ---------------------------------------*/
    /** Returns a list of all the Products*/
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**Add a Product to allProduct Inventory list */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** Search for Product by Id and return a product if found else returns null */
    public static Product lookUpProduct(int id) {
        Product returnedProductSearch = null;
        for (Product product : allProducts){
            if(product.getId() == id){
                returnedProductSearch = product;
            }
        }
        return returnedProductSearch;
    }

    /** Delete product return T/F depending if deleted */
    public static boolean deleteProduct(Product selectedProduct){
       return allProducts.remove(selectedProduct);
    }




}
