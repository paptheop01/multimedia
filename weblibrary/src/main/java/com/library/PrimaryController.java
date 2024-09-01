package com.library;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Node;
import javafx.scene.Parent;



public class PrimaryController  {

    
    
    
    
    /** 
     * @throws IOException
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    
    /** 
     * @throws IOException
     */
    @FXML
    private void switchToLoan() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loanadmin.fxml"));
                Parent root = loader.load();
                
    
                LoanControllerAdmin controller = loader.getController();
                //controller.setBook(selectedItem,categories,asAdmin);
                controller.setup(null); // Pass the selected item data to the controller
                App.setRoot(root);
        
    }

    @FXML
    private void switchToBooks() throws IOException {
        App.setRoot("book");
    }

    @FXML 
    private void logout(ActionEvent event) throws IOException {
        App.getAppState().getSessionManager().setCurrentAdmin(null);
        App.setRoot("start");
    }


    @FXML
    private void closeApplication(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
