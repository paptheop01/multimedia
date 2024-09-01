package com.library.Controllers;

import com.library.Models.*;
import com.library.App;


import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class DetailsBook {
    
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML 
    private Button saveChangesButton;
    @FXML
    private TextField pubHouseField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField pubDateField;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private Label messageLabel;

    
    

    
    
    /** 
     * @param book
     * @param categories
     * @param asAdmin
     */
    public void initialize(Book book, ObservableList<String> categories) {
        
        
        
       
        
        // Initialize ComboBox with available categories
       
       
        categoryComboBox.setEditable(false);
        titleField.setEditable(false);
        authorField.setEditable(false);
        pubHouseField.setEditable(false);
        isbnField.setEditable(false);
        pubDateField.setEditable(false);
        
        // Set default selection to the book's current category
        if (book != null) {
            categoryComboBox.getSelectionModel().select(book.getCategory());
            categoryComboBox.setDisable(true);
            categoryComboBox.setStyle( "-fx-opacity: 1.0; ");
                
            
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            pubHouseField.setText(book.getPublicationHouse());
            isbnField.setText(book.getIsbn());
            pubDateField.setText(book.getPublicationDate());
        }
    }

    

   

   
    

       
      
       @FXML
    private void switchBack() throws IOException {
       
        App.setRoot("userstart");
        
    }

    
    
}
