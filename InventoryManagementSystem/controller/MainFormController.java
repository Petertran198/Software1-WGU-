package InventoryManagementSystem.controller;

import InventoryManagementSystem.model.Inventory;
import InventoryManagementSystem.model.Product;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainFormController implements Initializable {
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    //How to exit application will show popup box
    public void exitApplication(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
                System.exit(0);            }
        });
    }

    public void switchToAddPartScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/AddPartForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToModifyPartScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/ModifyPartForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAddProductScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/AddProductForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToModifyProductScene(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../view/AddProductForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** Method that gets called when the connected fxml file loads */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Product p1 = new Product(1,"bike", 200.00, 5, 1, 5 );
        Product p2 = new Product(2,"computer", 300.00, 4, 1, 4 );
        Product p3 = new Product(3,"laptop", 220.00, 3, 1, 3 );
        Inventory.addProduct(p1);
        Inventory.addProduct(p2);
        Inventory.addProduct(p3);

        productsTable.setItems(Inventory.getAllProducts());
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}
