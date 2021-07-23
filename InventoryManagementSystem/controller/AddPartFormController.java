package InventoryManagementSystem.controller;

import InventoryManagementSystem.model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddPartFormController implements Initializable {
    //Label for switching text if inHouse or outSourced
    @FXML
    private Label inHouseOrOutsourcedPartLabel;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField inventoryField;
    @FXML
    private TextField costField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private TextField typeOfPartField;

    @FXML
    private Label errorList;
    private String errorListString = new String();

    public void switchBackToMainFormScene(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //methods when clicked on radio btn  to handle Outsourced part
    @FXML
    void handlePartInhouse(ActionEvent event) {
        inHouseOrOutsourcedPartLabel.setText("Machine ID");
    }

    //methods when clicked on radio btn  to handle Outsourced part
    @FXML
    void handlePartOutsourced(ActionEvent event) {
        inHouseOrOutsourcedPartLabel.setText("Company's Name");
    }

    public static String handleFormErrorsEmptyField(String name, String inventory, String cost, String max, String min, String inHouseFieldOrOutsourcedField) {
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
        if (inHouseFieldOrOutsourcedField.equals("")) {
            errors = errors + "\n- MachineID/Company Name field can't be empty. ";
        }
        return errors;
    }

    //Check if what is entered can be turn into int
    public static Optional<Integer> tryParseInt(String toParse) {
        try {
            return Optional.of(Integer.parseInt(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @FXML
    void savePart(ActionEvent event){
        String name = nameField.getText();
        String inventory = inventoryField.getText();
        String cost = costField.getText();
        String max = maxField.getText();
        String min = minField.getText();
        String inHouseOrOutField = typeOfPartField.getText();
        int machineId;
        String companyName;
        errorListString =  handleFormErrorsEmptyField(name, inventory , cost, max, min, inHouseOrOutField);
        //Take the returned error messages and include it in the errorList
        if(!name.isBlank() && !tryParseInt(name).isEmpty()){
            errorListString += "\n- Name can't contain numbers";
        }

        if(!inventory.isBlank() && tryParseInt(inventory).isEmpty()){
            errorListString += "\n- Inventory must be a number";
        }
        if(!cost.isBlank() && tryParseInt(cost).isEmpty()){
            errorListString += "\n- Cost must be a number";
        }
        if(!max.isBlank() && tryParseInt(max).isEmpty()){
            errorListString += "\n- Max/Min value must be a number";
        }
        if(!min.isBlank() && tryParseInt(min).isEmpty()){
            errorListString += "\n- Min value must be a number";
        }
        try{
            if(!max.isBlank() && Integer.parseInt(max) < Integer.parseInt(min)){
                errorListString += "\n- Max can not be smaller then min";
            }
        }catch(Exception e){
            errorListString += "\n- Please fix Max/Min field";
        }
        errorList.setText(errorListString);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //In Charge of autoGen partId
         int partId = Inventory.incrementPartID();
         idField.setText("Auto Gen: " + partId);
    }


}
