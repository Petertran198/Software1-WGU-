package InventoryManagementSystem.controller;

import InventoryManagementSystem.model.Inventory;
import InventoryManagementSystem.model.Part;
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

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
    //Get the FXML ID
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
    @FXML
    private TextField partsSearchField;
    @FXML
    private Label partSearchErrorField;
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


    public void switchBackToMainFormScene(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method is in charge of looking for the product
     * @param event button clicked
     */
    public void partSearchResultHandler(ActionEvent event) {
        partSearchErrorField.setText("");
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
            partSearchErrorField.setText("Unable to find part");
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getAllParts());
    }
}
