package com.library;


import java.io.IOException;



import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class LoginController {
   

    @FXML 
    private Button loginButton;
    @FXML
    private Button returnBackButton ;
    @FXML
    private TextField usernameField;

    @FXML 
    private PasswordField passwordField;

    @FXML
    private Label title;

    
/**
 * Initializes the login screen based on the user type (admin or regular user).
 *
 * @param asAdmin A boolean indicating whether the login is for an admin or a regular user.
 *                If true, the login screen will be set up for an admin.
 *                If false, the login screen will be set up for a regular user.
 *
 * The function sets the title of the login screen based on the user type.
 * If the user type is admin, the title is set to "Admin Login".
 * If the user type is not admin, the title is set to "User Login".
 *
 * Depending on the user type, the function populates the userList with either the admin list or the item list.
 * It then sets up the login button to call the checkandLogin function when clicked.
 * The checkandLogin function is responsible for validating the user's credentials and logging them in.
 */
public void initData(boolean asAdmin){
    title.setText(asAdmin? "Admin Login" : "User Login");
    if(asAdmin){
       
        loginButton.setOnAction(event ->{
            checkandLogin(usernameField.getText(), passwordField.getText(),asAdmin);
        });    
    }
    else{
        
        loginButton.setOnAction(event ->{
            checkandLogin(usernameField.getText(), passwordField.getText(),asAdmin);
        });  
    }
}


        
        
        
        /** 
         * @param username
         * @param password
         * @param asAdmin
         */
        @FXML
        private void checkandLogin(String username, String password, boolean asAdmin){
            if(asAdmin){
            App.getAppState().getSessionLoginCheckService().checkandLoginAdmin(username, password);
            }
            else{
                App.getAppState().getSessionLoginCheckService().checkandLoginUser(username, password);
            }
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

    @FXML
    private void returnBack() throws IOException {
        App.setRoot("start");
        
    }
    
}



