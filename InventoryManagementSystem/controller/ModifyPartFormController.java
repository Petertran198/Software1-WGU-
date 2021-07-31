package InventoryManagementSystem.controller;

import InventoryManagementSystem.model.InHouse;
import InventoryManagementSystem.model.Inventory;
import InventoryManagementSystem.model.Outsourced;
import InventoryManagementSystem.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This Controller is in charge of managing all the business logic of modifying a part
 * <br/>
 * <strong>FUTURE ENHANCEMENT:</strong> Refactor handleFormValidatingDataField and put the selecting toggle button into its own method */
public class ModifyPartFormController implements Initializable {
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
    @FXML
    private ToggleGroup typeOfModifyPartToggleGroup;
    @FXML
    private RadioButton inHousePartRadioBtn;
    @FXML
    private RadioButton outSourcedPartRadioBtn;
    private String errorListString = new String();
    //Part selected
    private  Part part;


    /** This method when click will switch to the MainForm.fxml
     * @param event any ActionEvent most likely click
     * */
    public void switchBackToMainFormScene(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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


    /**This method make sure that the form does not have any empty fields everything must be populated
     * @param name name text field input string
     * @param inventory inventory text field input string
     * @param cost cost text field input string
     * @param max  max text field input string
     * @param min  min text field input string
     * @param inHouseFieldOrOutsourcedField MachineId/Company's name text field input string
     * @return Returns correct error message if any field is blank
     */
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

    /**
     * Try to parse a given string
     * @param toParse string that you want to turn to int
     * @return either the string converted to an int or returns nothing
     */
    public static Optional<Integer> tryParseInt(String toParse) {
        try {
            return Optional.of(Integer.parseInt(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Try to parse a given string
     * @param toParse string that you want to turn to double
     * @return either the string converted to an double or returns nothing
     */
    public static Optional<Double> tryParseDouble(String toParse) {
        try {
            return Optional.of(Double.parseDouble(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     *  Handles validating form - verify that everything is the correct data type. Example <b>Name must be string</b><br/>
     * <strong>RUNTIME ERROR:</strong>: I had trouble validating the 'typeOfPart' form field because it could either be a string if it is OutSourced or an int if it is InHouse. I realized that in the grand scheme it does not matter because machineId is the only one that must be pure numbers to parse as an int. While Company's name can contain a number but it still can be turned into a string to update an instance of Part. I implemented the validation that machineId must be a string by implementing the tryParseInt method on it and if it does not return anything then what was inputted was not a number
     * @param name text of name field
     * @param inventory text of inventory field
     * @param cost text of cost field
     * @param max text of max field
     * @param min text of min field
     * @param typeOfPart text of typeOfPart field
     * @return string error message if any text fields has any invalid data
     */
    public  String handleFormValidatingDataField(String name, String inventory, String cost, String max, String min, String typeOfPart) {
        String errors= "";
        if(!name.isBlank() && !tryParseInt(name).isEmpty()){
            errors += "\n- Name can't contain numbers";
        }
        if(!inventory.isBlank() && tryParseInt(inventory).isEmpty()){
            errors += "\n- Inventory must be a number";
        }
        if( Integer.parseInt(inventory) >  Integer.parseInt(max) ||  Integer.parseInt(inventory) <  Integer.parseInt(min)){
            errors += "\n- Inventory must be between Max and Min";
        }
        if(!cost.isBlank() && tryParseDouble(cost).isEmpty()){
            errors += "\n- Cost must be a number";
        }
        if(!max.isBlank() && tryParseDouble(max).isEmpty() ){
            errors += "\n- Max/Min value must be a number";
        }
        if(!min.isBlank() && tryParseInt(min).isEmpty()){
            errors += "\n- Min value must be a number";
        }
        if((typeOfModifyPartToggleGroup.getSelectedToggle() == inHousePartRadioBtn) &&!typeOfPart.isBlank() && tryParseInt(typeOfPart).isEmpty()){
            errors += "\n- Machine Id must only be numbers";
        }
        try{
            if(!max.isBlank() && Integer.parseInt(max) < Integer.parseInt(min)){
                errors += "\n- Max can not be smaller then min";
            }
        }catch(Exception e){
            errors += "\n- Please fix Max/Min field";
        }
        return errors;
    }

    @FXML
    void savePart(ActionEvent event) throws Exception {
        String id = idField.getText();
        String name = nameField.getText();
        String inventory = inventoryField.getText();
        String cost = costField.getText();
        String max = maxField.getText();
        String min = minField.getText();
        String inHouseOrOutField = typeOfPartField.getText();
        int machineId;
        String companyName;
        errorListString =  handleFormErrorsEmptyField(name, inventory , cost, max, min, inHouseOrOutField);
        errorListString += handleFormValidatingDataField(name,inventory,cost,max,min, inHouseOrOutField);
        //Take the returned error messages and include it in the errorList
        errorList.setText(errorListString);
        //If no errors then proceed to saving the part
        if(errorListString.isBlank()){
            // extracted attributes to make instance of Part
            id = id.replaceAll("\\D+","");
            int parseId = Integer.parseInt(id);
            double parseCost = Double.parseDouble(cost);
            int parseInventory = Integer.parseInt(inventory);
            int parseMax = Integer.parseInt(max);
            int parseMin = Integer.parseInt(min);
            if(typeOfModifyPartToggleGroup.getSelectedToggle() == inHousePartRadioBtn){
                machineId= Integer.parseInt(inHouseOrOutField);
                InHouse inHousePart = new InHouse(parseId, name,parseCost, parseInventory,parseMin,parseMax, machineId);
                Inventory.updatePart(inHousePart);
            }else if(typeOfModifyPartToggleGroup.getSelectedToggle() == outSourcedPartRadioBtn){
                companyName = inHouseOrOutField;

                Outsourced outsourcedPart = new Outsourced(parseId, name,parseCost, parseInventory,parseMin,parseMax,companyName);
                Inventory.updatePart(outsourcedPart);
            }
            switchBackToMainFormScene(event);
        }
    }


    /**
     * The initialize method gets called after all @FXML annotated members have been injected/loads. So that the method could populate these @FXML member
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        part = MainFormController.selectedPartToModify;
        if(part instanceof InHouse){
            inHouseOrOutsourcedPartLabel.setText("Machine ID");
            inHousePartRadioBtn.setSelected(true);
            typeOfPartField.setText(Integer.toString(((InHouse) part).getMachineId()));
        }else {
            inHouseOrOutsourcedPartLabel.setText("Company's Name");
            outSourcedPartRadioBtn.setSelected(true);
            typeOfPartField.setText(((Outsourced) part).getCompanyName());
        }
        idField.setText(Integer.toString(part.getId()));
        nameField.setText(part.getName());
        inventoryField.setText(Integer.toString(part.getStock()));
        costField.setText(Double.toString(part.getPrice()));
        maxField.setText(Integer.toString(part.getMax()));
        minField.setText(Integer.toString(part.getMin()));
    }
}
