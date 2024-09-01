package com.library.Controllers;
import com.library.Managers.*;
import com.library.Services.*;

import com.library.App;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * A controller for managing the user signup functionality in the library application.
 */
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


     // Store the current item being edited

    private UserService userService;
    private SessionManager sessionManager;
    private ShowAlertService showAlertService;

    
    
    @FXML
    private void initialize() {
        this.userService = App.getAppState().getUserService();
        this.sessionManager = App.getAppState().getSessionManager();
        this.showAlertService = App.getAppState().getShowAlertService();
        
       
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
                    showAlertService.showErrorAlert("Error, email already exists");
                }
                else if(userService.usernameExists(username.getText())){
                    showAlertService.showErrorAlert("Error, username already exists");
                }
                else if(userService.ssnExists(Integer.parseInt(ssnField.getText()))){
                    showAlertService.showErrorAlert("Error, ssn already exists");
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
                    sessionManager.setCurrentUser(userService.searchUser(username.getText()));
                    switchtoUser();
                }

            }
                
        
        else{
            showAlertService.showErrorAlert("All fields are mandatory and ssn should be a number!");
        }
        // Close the details page or perform other actions as needed
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
