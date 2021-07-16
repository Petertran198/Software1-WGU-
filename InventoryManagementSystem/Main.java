package InventoryManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class launches the application */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainForm.fxml"));
        primaryStage.setTitle("Inventory Management System");
        Scene InventoryMainScene = new Scene(root);
        primaryStage.setScene(InventoryMainScene);
        primaryStage.show();
    }



    /** This is the main method
     *  @param args
     * */
    public static void main(String[] args) {
        launch(args);
    }
}
