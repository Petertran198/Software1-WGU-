//package InventoryManagementSystem;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//import javafx.stage.Stage;
//
//public class SceneController {
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//
//
//
//    public void switchToAddPartScene(ActionEvent event) throws Exception {
//        System.out.println("hi");
//        root = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//}
