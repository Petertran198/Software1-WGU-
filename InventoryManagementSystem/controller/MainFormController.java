package InventoryManagementSystem.controller;

import InventoryManagementSystem.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainFormController implements Initializable {
    //------------- fxid for Part table ------------
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    @FXML
    private TableColumn<Part, Double> partCostColumn;

    @FXML
    private TextField partSearchTextField;
    @FXML
    private Label partError;
    @FXML
    private Button partDeleteBtn;
    // ------------- fxid for Product table ------------
    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    @FXML
    private TableColumn<Product, Double> productCostColumn;

    @FXML
    private TextField productSearchTextField;
    @FXML
    private Label productError;
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * This is the part that is selected from the part table and will be used to populate the modified part form
     */
    public static Part selectedPartToModify;
    /**
     * This is the product that is selected from the part table and will be used to populate the modified product form
     */
    public static Product selectedProductToModify;


    /**
     * When clicked  will show popup box asking if you want to exit from application or not
     * @param event ActionEvent event
     */
    public void exitApplication(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
                System.exit(0);            }
        });
    }
    // Methods related to parts -------------------------------------

    /** This method when click will switch to the AddPartForm.fxml
     * @param event any ActionEvent most likely click
     * */
    public void switchToAddPartScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/AddPartForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** This method when click will switch to the ModifyPartForm.fxml
     * @param event any ActionEvent most likely click
     * */
    public void switchToModifyPartScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/ModifyPartForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method  gets the part you want to modified from part table, pass that part data to the modifiedPartForm controller to use, and switch to the modifiedPartForm.fxml view
     * @param event click event
     */
    public void modifyPart(ActionEvent event) throws Exception {
        partError.setText("");
        String err = "";
        Part selected = partsTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            err += "\n- No Part Selected";
        }
        if(Inventory.getAllParts().size() == 0){
            err += "\n- Please Select a Part";
        }
        if(err.isEmpty()){
            //Get the part
            Part p = Inventory.lookUpPart(selected.getId());
            selectedPartToModify = p;
            switchToModifyPartScene(event);
        }else {
            partError.setText(err);
        }
    }

    /**
     * This method  gets the product you want to modified from product table, pass that part data to the modifiedProductForm controller to use, and switch to the modifiedProductForm.fxml view
     * @param event click event
     */
    public void modifyProduct(ActionEvent event) throws Exception {
        productError.setText("");
        String err = "";
        Product selected = productsTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            err += "\n- No Products Selected";
        }
        if(Inventory.getAllProducts().size() == 0){
            err += "\n- Please Select a Product";
        }
        if(err.isEmpty()){
            //Get the part
            Product p = Inventory.lookUpProduct(selected.getId());
            selectedProductToModify = p;
            switchToModifyProductScene(event);
        }else {
            productError.setText(err);
        }
    }

    /**
     * This method is in charge of looking for the product
     * @param event button clicked
     */
    public void partSearchResultHandler(ActionEvent event) {
        partError.setText("");
        /** Get the text from the search field */
        String searchedForPartText = partSearchTextField.getText();
        /* Look to see if any part was found by Name and return a list of the part found */
        ObservableList<Part> foundPartsResultList = searchByPartsName(searchedForPartText);
        /* If part is not found by name search by id */
        try {
            int foundPartID = Integer.parseInt(searchedForPartText);
            Part foundPart = searchForPartByID((foundPartID));
            if (foundPart != null){
                foundPartsResultList.add(foundPart);
            }
        } catch (Exception e ) {
            System.err.println("------------" + e.getMessage());

        }
        /** If product can not be found write error message */
        if(foundPartsResultList.size() ==0 ){
            partError.setText("Unable to find part");
        }
        partsTable.setItems(foundPartsResultList);
        partSearchTextField.setText("");
    }

    private ObservableList<Part> searchByPartsName(String name){
        name = name.toLowerCase();
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(Part p : allParts){
            if(p.getName().toLowerCase().contains(name)){
                foundParts.add(p);
            }
        }
        return foundParts;
    }

    private Part searchForPartByID(int id){
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(Part p : allParts){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
    /**  Runs when event is activated gets the selected part and delete it if it gets confirmation */
    @FXML
    public void handleDeleteForPart(ActionEvent event) throws IOException {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setContentText("Delete the part?");
        Optional<ButtonType> alertBtn = alert.showAndWait();
        if(alertBtn.get() == ButtonType.OK){
            Inventory.deletePart(part);
        }
    }

    // Methods related to products -------------------------------------
    /** This method when click will switch to the AddProductForm.fxml
     * @param event any ActionEvent most likely click
     * */
    public void switchToAddProductScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/AddProductForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** This method when click will switch to the ModifyProductForm.fxml
     * @param event any ActionEvent most likely click
     * */
    public void switchToModifyProductScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/ModifyProductForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method is in charge of looking for the product
     * @param event button clicked
     */
    public void productSearchResultHandler(ActionEvent event) {
        productError.setText("");
        // Get the text from the search field
        String searchedForProductText = productSearchTextField.getText();
        // Look to see if any product was found by Name and return a list of the products found
        ObservableList<Product> foundProductsResultList = searchByProductsName(searchedForProductText);
        // If product is not found by name search by id
        try {
            int foundProductID = Integer.parseInt(searchedForProductText);
            Product foundProduct = searchForProductsByID((foundProductID));
            if (foundProduct != null){
                foundProductsResultList.add(foundProduct);
            }
        } catch (Exception e ) {
            System.err.println("Caught Exception can not find" + e.getMessage());

        }
        // If product can not be found write error message
        if(foundProductsResultList.size() ==0 ){
            productError.setText("Unable to find product");
        }
        productsTable.setItems(foundProductsResultList);
        productSearchTextField.setText("");
    }

    private ObservableList<Product> searchByProductsName(String name){
        name = name.toLowerCase();
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(Product p : allProducts){
            if(p.getName().toLowerCase().contains(name)){
                foundProducts.add(p);
            }
        }
        return foundProducts;
    }

    private Product searchForProductsByID(int id){
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(Product p : allProducts){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    /**  Runs when event is activated gets the selected product and delete it if it gets confirmation */
    @FXML
    public void handleDeleteForProduct(ActionEvent event) throws IOException {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setContentText("Delete the product?");
        Optional<ButtonType> alertBtn = alert.showAndWait();
        if(alertBtn.get() == ButtonType.OK){
            Inventory.deleteProduct(product);
        }
    }

    /**
     * The initialize method gets called after all @FXML annotated members have been injected/loads. So that the method could populate these @FXML member
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTable.setItems(Inventory.getAllParts());
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        productsTable.setItems(Inventory.getAllProducts());
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

}
