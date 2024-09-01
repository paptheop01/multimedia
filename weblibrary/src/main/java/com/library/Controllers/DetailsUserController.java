package com.library.Controllers;

import com.library.Services.*;
import com.library.Models.*;
import com.library.App;

import java.io.IOException;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * A controller for displaying the details of a user in the library application and allowing the admin to edit them.
 * 
 */
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
    private ShowAlertService showAlertService;

    
   /**
     * Initializes the controller and sets up event handlers, data bindings, and other necessary components.
     *
     * @param item The user for which details are to be displayed or edited
     */
    public void initData(User item) {
        this.userService = App.getAppState().getUserService();
        this.showAlertService=App.getAppState().getShowAlertService();

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
            showAlertService.showErrorAlert("All fields are mandatory and ssn should be integer!");
        }
        // Close the details page or perform other actions as needed
    }


     
    
    
    
    /** 
     * @return boolean
     */
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
