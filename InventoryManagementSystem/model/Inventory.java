package InventoryManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class Inventory {
    /** An array list of all the Parts */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /** An array list of all the Product */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** -----------------For Parts ---------------------------------------*/
    private static int partID = 0;
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

    //InCharge of incrementing PartID
    public static int incrementPartID() {
       return ++partID;
    }
    /** Search for Part by Id and return a part if found else returns null */
    public static Part lookUpPart(int id) {
        Part returnedPartSearch = null;
        for (Part part : allParts){
            if(part.getId() == id){
                returnedPartSearch = part;
            }
        }
        return returnedPartSearch;
    }


    public static Part lookUpPart(String name) {
        Part returnedPartSearch = null;
        for (Part part : allParts){
            if(part.getName() == name){
                returnedPartSearch = part;
            }
        }
        return returnedPartSearch;
    }

    public static void updatePart(Part p) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == p.getId()) {
                allParts.set(i, p);
                break;
            }
        }
    }

    /** -----------------For Product ---------------------------------------*/
    private static int productID = 0;

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

    public static void updateProduct(Product p) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == p.getId()) {
                allProducts.set(i, p);
                break;
            }
        }
    }

    /** Delete product return T/F depending if deleted */
    public static boolean deleteProduct(Product selectedProduct){
       return allProducts.remove(selectedProduct);
    }

    //InCharge of incrementing ProductId
    public static int incrementProductID() {
        return ++productID;
    }

    //Check if what is entered can be turn into int
    public static Optional<Integer> tryParseInt(String toParse) {
        try {
            return Optional.of(Integer.parseInt(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    //Check if what is entered can be turn into Double
    public static Optional<Double> tryParseDouble(String toParse) {
        try {
            return Optional.of(Double.parseDouble(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
