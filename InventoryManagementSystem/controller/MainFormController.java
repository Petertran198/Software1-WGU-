package InventoryManagementSystem.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainFormController implements Initializable {
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

    }

}
