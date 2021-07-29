package InventoryManagementSystem.model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;


/**
 *
 * @author Doan Tran
 */
public abstract class Part {


    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Part constructor contains all the attributes to make a part:
     * <strong>Note: Part is an abstract class</strong>
     * @param id  part id
     * @param name  part name
     * @param price  part price
     * @param stock  part stock
     * @param min  part min
     * @param max  part max
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    /**Return part id */
    public int getId() {
        return id;
    }

    /**
     * Give a specific part an id
     * @param id the id you want to set that part
     */
    public void setId(int id) {

        this.id = id;
    }

    /**
     * Get the name of part
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * Set the name of Part
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get price of part
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of specific part
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get stock of specific part
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set the inventory of specific part
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get the min of part
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * Set the min of specific part
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Get the max of part
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * Set the max of part
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }





}