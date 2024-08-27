package com.library;

import java.io.IOException;

import javafx.collections.ObservableList;
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

    
    /** 
     * @param item
     */
    // Method to initialize data in the details view
    public void initData(User item) {
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
        else{
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
    }

    // Method to handle saving changes
    @FXML
    private void saveChanges() {

        if(validateFields()) {
            ObservableList<User> itemList = App.getAppState().getItemList();
            
            
            if(currentItem != null) {

                String editedItem = username.getText();
                    // Update the username
                currentItem.setusername(editedItem);
                editedItem = password.getText();
                currentItem.setpassword(editedItem);
                editedItem = email.getText();
                currentItem.setemail(editedItem);
                editedItem = ssnField.getText();
                currentItem.setssn(Integer.parseInt(editedItem));
                editedItem=surname.getText();
                currentItem.setsurname(editedItem);
                editedItem=firstname.getText();
                currentItem.setfirstname(editedItem);
                // Update the item directly in AppState's itemList
                
                int index = itemList.indexOf(currentItem);
                if (index != -1) {
                    itemList.set(index, currentItem); // Update the item at the specific index
                }

            }
            else{
                if(itemList.stream().filter(u -> u.getEmail().equals(email.getText())).findFirst().isPresent()){
                    createAlert("Error, email already exists");
                }
                else if(itemList.stream().filter(u -> u.getUsername().equals(username.getText())).findFirst().isPresent()){
                    createAlert("Error, username already exists");
                }
                else{
                // Create a new user
                    User newUser = new User(
                        surname.getText(),
                        firstname.getText(),
                        email.getText(),
                        password.getText(),
                        username.getText(),
                        Integer.parseInt(ssnField.getText())
                    );
                    App.getAppState().addItemToList(newUser);
                    
                    switchtoUser();
                }

            }
                
        }
        else{
            createAlert("All fields are mandatory!");
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
