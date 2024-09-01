package com.library;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DetailsUserController {
    @FXML
    private Label title;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField ssnField;
    @FXML
    private TextField firstname;
    @FXML
    private TextField surname;
    @FXML
    private Button saveChangesButton;
    @FXML
    private Button returnBackButton;


    private User currentItem; // Store the current item being edited

    private UserService userService;

    
    /** 
     * @param item
     */
    // Method to initialize data in the details view
    public void initData(User item, UserService userService) {
        this.userService = userService;
        currentItem = item;
        if (currentItem != null){
            title.setText("Edit User");
            username.setText(item.getUsername());
            password.setText(item.getPassword()); 
            email.setText(item.getEmail());
            ssnField.setText(String.valueOf(item.getSsn()));
            String[]  fullname=item.getFullName().split(" ");
            surname.setText(fullname[1]);
            firstname.setText(fullname[0]);
            saveChangesButton.setText("Save Changes");
            returnBackButton.setText("Return Back");
            returnBackButton.setOnAction(event -> {switchBackAdmin();} );
        }
       
    }

    // Method to handle saving changes
    @FXML
    private void saveChanges() {

        if(validateFields()) {
            
            
            
            if(currentItem != null) {


                
                userService.updateUser(currentItem,surname.getText(), firstname.getText() , email.getText(), password.getText(), username.getText(), Integer.parseInt(ssnField.getText()) );
                // Update the item directly in AppState's itemList
                
                

            }
            
                
        }
        else{
            createAlert("All fields are mandatory and ssn should be integer!");
        }
        // Close the details page or perform other actions as needed
    }


     
     /** 
      * @param Message
      */
     @FXML
    private void createAlert(String Message){
        Alert warningAlert = new Alert(AlertType.WARNING);
        warningAlert.setTitle("Error");
        warningAlert.setHeaderText(null);
        warningAlert.setContentText(Message);
        warningAlert.showAndWait();

    }
    
    private boolean validateFields() {
        if (username.getText().isEmpty() ||
            password.getText().isEmpty() ||
            email.getText().isEmpty() ||
            ssnField.getText().isEmpty() ||
            firstname.getText().isEmpty() ||
            !ssnField.getText().matches("\\d*") ||
            surname.getText().isEmpty()) {
            return false;
        }
        return true;
    }

      @FXML
    private void switchBackAdmin() {
        try{
            App.setRoot("secondary");
        }
        catch(IOException e){
            e.printStackTrace();
            
        }
        
    }

   
}
