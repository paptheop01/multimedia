package com.library.Controllers;
import com.library.Managers.*;

import com.library.App;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;


/**
 * A controller serving as the admin dashboard. 
 * Admin can choose to navigate to different controllers based on the use case.
 */
public class PrimaryController  {
    private SessionManager sessionManager;

    
    @FXML
    private void initialize() {
        sessionManager = App.getAppState().getSessionManager();
    }
    
    
   
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    
    
    @FXML
    private void switchToLoan() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/loanadmin.fxml"));
                Parent root = loader.load();
                
    
                LoanControllerAdmin controller = loader.getController();
                //controller.setBook(selectedItem,categories,asAdmin);
                controller.setup(); // Pass the selected item data to the controller
                App.setRoot(root);
        
    }

    
    
    @FXML
    private void switchToBooks() throws IOException {
        App.setRoot("book");
    }

    @FXML 
    private void logout(ActionEvent event) throws IOException {
        sessionManager.setCurrentAdmin(null);
        App.setRoot("start");
    }


    
}
