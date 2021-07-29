package InventoryManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This represents a model of a what makes a product
 *<br/>
 *  <strong>FUTURE ENHANCEMENT:</strong>
 *  <br/>
 *  make it abstract like part and make it that you can only add  Outsourced Product or InHouse Product
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Product constructor contains all the attributes to make a product:
     * <strong>Note: Part is an abstract class</strong>
     * @param id  product id
     * @param name  product name
     * @param price  product price
     * @param stock  product stock
     * @param min  product min
     * @param max  product max
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Return product id */
    public int getId() {
        return id;
    }

    /**
     * Give a specific product an id
     * @param id the id you want to set that product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of product
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of product
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get price of product
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of specific product
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get stock of specific product
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set the inventory of specific product
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get the min of product
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * Set the min of specific product
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Get the max of product
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * Set the max of product
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }




    // List of parts that is associated with the Product
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Add an associated part to the Product associatedParts list
     * <strong>RUNTIME ERROR:</strong>: I had trouble implementing this method because I did not know how to connect a part to a product. I resolved it by creating a private ObservableList of associatedPart and only allowed this method to associate a part with its intended Product
     * @param part part you want to add to product
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * delete an associated part to the Product associatedParts list
     * @param p part you want to delete  product
     */
    public boolean deleteAssociatedPart(Part p) {
        if(associatedParts.remove(p)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * Get all associated part that belongs to this specific Product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
