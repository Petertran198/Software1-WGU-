package InventoryManagementSystem.controller;

import InventoryManagementSystem.model.Inventory;
import InventoryManagementSystem.model.Part;
import InventoryManagementSystem.model.Product;
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

public class ModifyProductFormController implements Initializable {
    //Get the FXML ID for product textfield
    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productInventoryField;
    @FXML
    private TextField productCostField;
    @FXML
    private TextField productMaxField;
    @FXML
    private TextField productMinField;
    //Parts table FXID
    @FXML
    private TextField partsSearchField;
    @FXML
    private Label partErrorField;
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

    private ObservableList<Part> linkedParts = FXCollections.observableArrayList();
    // Linked parts Table FXID
    @FXML
    private TableView<Part> associatedParts;
    @FXML
    private TableColumn<Part, Integer> linkedPartIDColumn;

    @FXML
    private TableColumn<Part, String> linkedPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> linkedPartInventoryColumn;

    @FXML
    private TableColumn<Part, Double> linkedPartCostColumn;
    @FXML
    private Label associatedPartErrors;
    @FXML
    private Label mainErrorList;

    String mainErrorListString = new String();
    //Product selected
    private  Product product;
    private Inventory i = new Inventory();

    public void switchBackToMainFormScene(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // Handling Part Search
    public void partSearchResultHandler(ActionEvent event) {
        partErrorField.setText("");
        /** Get the text from the search field */
        String searchedForPartText = partsSearchField.getText();
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
            partErrorField.setText("Unable to find part");
        }
        partsTable.setItems(foundPartsResultList);
        partsSearchField.setText("");
    }

    private ObservableList<Part> searchByPartsName(String name){
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(Part p : allParts){
            if(p.getName().contains(name)){
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
    //--------------------------
    @FXML
    private void handleSavePartToProduct(ActionEvent event) throws IOException {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        //If no part is selected
        if (selectedPart != null ) {
            partErrorField.setText("");
            //Only add if the part selected is not already in the list
            if(!linkedParts.contains(selectedPart)){
                linkedParts.add(selectedPart);
                associatedParts.setItems(linkedParts);
            } else {
                partErrorField.setText("Part already added");
            }
        } else {
            partErrorField.setText("Must Select a Part");
        }

    }

    @FXML
    private void handleCancel(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setContentText("Cancel adding product");
        Optional<ButtonType> alertBtn = alert.showAndWait();
        if(alertBtn.get() == ButtonType.OK){
            switchBackToMainFormScene(event);
        }

    }

    @FXML
    private void handleDeletePartFromProduct(ActionEvent event) throws IOException {
        Part selectedPart = associatedParts.getSelectionModel().getSelectedItem();
        String err = "";
        if(selectedPart == null){
            err += "\n- Please select a part first";
        }

        if(linkedParts.isEmpty() ){
            err += "\n- Please associate at least one part";
        }
        associatedPartErrors.setText(err);
        if(associatedPartErrors.getText() == ""){
            linkedParts.remove(selectedPart);
            associatedParts.setItems(linkedParts);
        }

    }
    public static String handleFormErrorsEmptyField(String name, String inventory, String cost, String max, String min) {
        String errors= "";

        if (name.equals("")) {
            errors = errors + "- Name field can't be empty. ";
        }
        if (inventory.equals("")) {
            errors = errors + "\n- Inventory field can't be empty. ";
        }
        if (cost.equals("")) {
            errors = errors + "\n- Price/Cost field can't be empty. ";
        }
        if (max.equals("")) {
            errors = errors + "\n- Max field can't be empty. ";
        }
        if (min.equals("")) {
            errors = errors + "\n- Min field can't be empty. ";
        }
        return errors;
    }

    public  String handleFormValidatingDataField(String name, String inventory, String cost, String max, String min, ObservableList partLink) {
        String errors= "";
        if(!name.isBlank() && !Inventory.tryParseInt(name).isEmpty()){
            errors += "\n- Name can't contain numbers";
        }
        if(!inventory.isBlank() && Inventory.tryParseInt(inventory).isEmpty()){
            errors += "\n- Inventory must be a number";
        }
        if(!cost.isBlank() && Inventory.tryParseDouble(cost).isEmpty()){
            errors += "\n- Cost must be a number";
        }
        if(!max.isBlank() && Inventory.tryParseDouble(max).isEmpty() ){
            errors += "\n- Max/Min value must be a number";
        }
        if(!min.isBlank() && Inventory.tryParseInt(min).isEmpty()){
            errors += "\n- Min value must be a number";
        }
        try{
            if(!max.isBlank() && Integer.parseInt(max) < Integer.parseInt(min)){
                errors += "\n- Max can not be smaller then min";
            }
        }catch(Exception e){
            errors += "\n- Please fix Max/Min field";
        }
        if(partLink.size() < 1 ){
            errors += "\n- Product must have associated Part";
        }

        return errors;
    }

    @FXML
    private void saveProduct(ActionEvent event) throws Exception {
        String id = productIDField.getText();
        String name = productNameField.getText();
        String inventory = productInventoryField.getText();
        String cost = productCostField.getText();
        String max = productMaxField.getText();
        String min = productMinField.getText();
        mainErrorListString = handleFormErrorsEmptyField(name, inventory , cost, max, min);
        mainErrorListString += handleFormValidatingDataField(name,inventory,cost,max,min, linkedParts);
        mainErrorList.setText(mainErrorListString);
        //If no error
        if(mainErrorListString.isBlank()){
            // extracted attributes to make instance of Product
            id = id.replaceAll("\\D+","");
            int parseId = Integer.parseInt(id);
            double parseCost = Double.parseDouble(cost);
            int parseInventory = Integer.parseInt(inventory);
            int parseMax = Integer.parseInt(max);
            int parseMin = Integer.parseInt(min);
            Product newProductCreated = new Product(parseId, name, parseCost, parseInventory, parseMax, parseMin);
            //Add all associated part to that product
            for (Part p : linkedParts) {
                newProductCreated.addAssociatedPart(p);
            }
            Inventory.updateProduct(newProductCreated);
            switchBackToMainFormScene(event);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Get the selected products info and populate
        product = MainFormController.selectedProductToModify;
        productIDField.setText(Integer.toString(product.getId()));
        productNameField.setText(product.getName());
        productInventoryField.setText(Integer.toString(product.getStock()));
        productCostField.setText(Double.toString(product.getPrice()));
        productMaxField.setText(Integer.toString(product.getMax()));
        productMinField.setText(Integer.toString(product.getMin()));
        //Get linkedParts and populate associatedParts table
        linkedParts = product.getAllAssociatedParts();
        associatedParts.setItems(linkedParts);


        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getAllParts());

        linkedPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        linkedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        linkedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        linkedPartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
