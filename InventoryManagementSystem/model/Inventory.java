package InventoryManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

/**
 * In charge of holding all the data of all collective part and product<br/>
 *  <strong>FUTURE ENHANCEMENT:</strong>
 *  <br/>
 *  Create another class for materials such as raw components to add onto the inventory management system
 *
 */
public class Inventory {
    // An array list of all the Parts
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    // An array list of all the Product
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // -----------------For Parts ---------------------------------------
    private static int partID = 0;

    /** Returns a list of all the parts*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**Add a Part to allParts Inventory list */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** Delete a part returns True or False depending if part got deleted */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /** In charge of incrementing PartID <br/>
     * <strong>RUNTIME ERROR:</strong>: I had a problem where the partId was not displaying properly. For example when I create my first part the id is still 0. The id did not increment properly. I found out that partId++ will increment after and what I needed was ++partID
     * */
    public static int incrementPartID() {
       return ++partID;
    }

    /** Search for Part by <strong>Id</strong>> and return a part if found else returns null */
    public static Part lookUpPart(int id) {
        Part returnedPartSearch = null;
        for (Part part : allParts){
            if(part.getId() == id){
                returnedPartSearch = part;
            }
        }
        return returnedPartSearch;
    }

    /** Search for Part by <strong>String</strong> and return a part if found else returns null */
    public static Part lookUpPart(String name) {
        Part returnedPartSearch = null;
        for (Part part : allParts){
            if(part.getName() == name){
                returnedPartSearch = part;
            }
        }
        return returnedPartSearch;
    }

    /**
     * Find the part by its id and then update the info of that specific part
     * @param p part you selected
     */
    public static void updatePart(Part p) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == p.getId()) {
                allParts.set(i, p);
                break;
            }
        }
    }

    // -----------------For Product ---------------------------------------
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

    /**
     * Find the product by its id and then update the info of that specific product
     * @param p product you selected
     */
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

    /**In charge of incrementing ProductId*/
    public static int incrementProductID() {
        return ++productID;
    }

    /**Check if what is entered can be turn into an int if it can return the string to int if not return null
     * @param toParse string that you want to turn into a double
     * @return double or null
     * */
    public static Optional<Integer> tryParseInt(String toParse) {
        try {
            return Optional.of(Integer.parseInt(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Check if what is entered can be turn into an double if it can return the string to double if not return null
     * @param toParse string that you want to turn into a double
     * @return double or null
     */
    public static Optional<Double> tryParseDouble(String toParse) {
        try {
            return Optional.of(Double.parseDouble(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
