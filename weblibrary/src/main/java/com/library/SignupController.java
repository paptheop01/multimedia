package com.library;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupController {
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
       
            title.setText("Sign Up Page");
            username.setPromptText("username");
            password.setPromptText("password"); 
            email.setPromptText("email");
            ssnField.setPromptText("Ssn");
            surname.setPromptText("Surname");
            firstname.setPromptText("First Name");
            saveChangesButton.setText("Sign Up");
            
            returnBackButton.setText("Cancel");
            returnBackButton.setOnAction(event -> {switchBackUser();} );
        
    }

    // Method to handle saving changes
    @FXML
    private void saveChanges() {

        if(validateFields()) {
            
            
            
            
                if(userService.emailExists(email.getText())){
                    createAlert("Error, email already exists");
                }
                else if(userService.usernameExists(username.getText())){
                    createAlert("Error, username already exists");
                }
                else{
                // Create a new user
                    userService.addUser(
                        surname.getText(),
                        firstname.getText(),
                        email.getText(),
                        password.getText(),
                        username.getText(),
                        Integer.parseInt(ssnField.getText())
                    );
                    App.getAppState().getSessionManager().setCurrentUser(userService.searchUser(username.getText()));
                    switchtoUser();
                }

            }
                
        
        else{
            createAlert("All fields are mandatory and ssn should be a number!");
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
    private void switchBackUser()  {
        try{
            App.setRoot("start");
        }
        catch(IOException e){
            e.printStackTrace();
            
        }
        
    }

    @FXML 
    private void switchtoUser()  {
        try{
            App.setRoot("userstart");
        }
        catch(IOException e){
            e.printStackTrace();
            
        }
    }

}
