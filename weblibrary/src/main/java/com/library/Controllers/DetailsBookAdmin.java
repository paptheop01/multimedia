package com.library.Controllers;

import com.library.Services.*;
import com.library.Models.*;
import com.library.App;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * A controller for desplaying the details of a book to the admin and allowing him to edit them.
 * 
 */
public class DetailsBookAdmin {
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

    private Book book;
    private BookService bookService;
    private ShowAlertService showAlertService;
    

    
    
    /**
     * Initializes the controller and sets up event handlers, data bindings, and other necessary components.
     *
     * @param book The book for which details are to be displayed or edited
     * @param categories The list of available categories for the book
     */
    public void initialize(Book book, ObservableList<String> categories) {
        this.bookService=App.getAppState().getBookService();
        this.showAlertService=App.getAppState().getShowAlertService();
        this.book = book;
        
       
        
        // Initialize ComboBox with available categories
        
        categoryComboBox.setItems(categories);
        categoryComboBox.setEditable(true); // Allow custom input
        // Set default selection to the book's current category
        if (book != null) {
            categoryComboBox.getSelectionModel().select(book.getCategory());
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            pubHouseField.setText(book.getPublicationHouse());
            isbnField.setText(book.getIsbn());
            pubDateField.setText(book.getPublicationDate());
        }
    }

   
    
    @FXML
    private void saveChanges() {
        if(validateFields()){
        if (book != null) {
            // Update book details
            bookService.updateBook(book,titleField.getText(),authorField.getText(),pubHouseField.getText(),isbnField.getText(),pubDateField.getText(),categoryComboBox.getValue());
            
            messageLabel.setText("Changes saved successfully!");
        }
        else {
            // Create a new book
            bookService.addBook(
                titleField.getText(),
                authorField.getText(),
                pubHouseField.getText(),
                isbnField.getText(),
                pubDateField.getText(),
                categoryComboBox.getValue()
            );

           
            messageLabel.setText("New book created successfully!");
        }
    }
    else{
        showAlertService.showErrorAlert("All fields are mandatory and the Date format is yyyy-MM-dd!");

    }
    
    }

    
    
    /** 
     * @return boolean
     */
    private boolean validateFields() {
        if (titleField.getText().isEmpty() ||
            authorField.getText().isEmpty() ||
            pubHouseField.getText().isEmpty() ||
            isbnField.getText().isEmpty() ||
            pubDateField.getText().isEmpty() ||
            !isValidDate(pubDateField.getText()) ||
            categoryComboBox.getValue() == null || categoryComboBox.getValue().isEmpty()) {
            return false;
        }
        return true;
    }
    private boolean isValidDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(text, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

       @FXML
    private void switchBack() throws IOException {
       
        App.setRoot("book");
        
        
    }

    

}
