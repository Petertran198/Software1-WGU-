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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
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

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
