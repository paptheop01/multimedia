package com.library.Controllers;
import com.library.Managers.*;
import com.library.Services.*;
import com.library.Models.*;
import com.library.App;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * A controller for managing the rating and commenting functionality for a book in the library application.
 */
public class RateCommentBookController {
    private Book book;
    private SessionManager sessionManager;
    private BookService bookService;
    private ShowAlertService showAlertService;
    
    @FXML
    private Button rateButton;
    @FXML
     private Button commentButton;
    @FXML
    private Button returnButton;
    @FXML
    private TextField commentTextfield;
 
    @FXML
    private ComboBox<Double> ratingComboBox;
    @FXML 
    private Label desclabel;


    
    /**
     * Initializes the rate and comment screen by setting up the session manager, show alert service, and book service.
     * It also sets up the rating combo box with default values and initializes the description label.
     */
    public void initialize(){
        this.sessionManager=App.getAppState().getSessionManager();
        this.showAlertService=App.getAppState().getShowAlertService();
        this.bookService=App.getAppState().getBookService();
        ratingComboBox.getItems().addAll(1.0, 2.0, 3.0, 4.0, 5.0);
        
        // Set a default value (optional)
        ratingComboBox.setValue(1.0);
        
        desclabel.setText("Choose rating");
        commentTextfield.setPromptText("Add Review");
        
    }
    
    /**
     * Sets up the rate and comment screen for a specific book.
     *
     * @param book The book for which the rate and comment functionality will be managed.
     */
    public void setup(Book book){
        this.book = book;
       
    }
    @FXML
    private void rate(){
        double rating =  ratingComboBox.getValue();
        bookService.rate(book, rating);
        showAlertService.showInfoAlert("Your rating has been submitted");
    }

    @FXML 
    private void leavecomment() {
        String comment = commentTextfield.getText();
        if(!comment.isEmpty()){
            book.addUserComment(sessionManager.getCurrentUser().getId(), comment);
            showAlertService.showInfoAlert("Your comment has been submitted");
        }
        else{
            showAlertService.showWarningAlert("Comment field cannot be empty");
        }
        
    }
    
    
    @FXML
    private void returnBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/loan.fxml"));
        Parent root = loader.load();
        

        LoanController controller = loader.getController();
        //controller.setBook(selectedItem,categories,asAdmin);
        controller.setup(sessionManager.getCurrentUser()); // Pass the selected item data to the controller
        App.setRoot(root);
        

    }
    
    


}
