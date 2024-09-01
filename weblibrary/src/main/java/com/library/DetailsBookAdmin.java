package com.library;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    

    
    
    /** 
     * @param book
     * @param categories
     * @param asAdmin
     */
    public void initialize(Book book, ObservableList<String> categories) {
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
            App.getAppState().getBookService().updateBook(book,titleField.getText(),authorField.getText(),pubHouseField.getText(),isbnField.getText(),pubDateField.getText(),categoryComboBox.getValue());
            
            messageLabel.setText("Changes saved successfully!");
        }
        else {
            // Create a new book
            App.getAppState().getBookService().addBook(
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
        createAlert("All fields are mandatory!");

    }
    
    }

    @FXML
    private void createAlert(String Message){
        Alert warningAlert = new Alert(AlertType.WARNING);
        warningAlert.setTitle("Error");
        warningAlert.setHeaderText(null);
        warningAlert.setContentText(Message);
        warningAlert.showAndWait();

    }
    private boolean validateFields() {
        if (titleField.getText().isEmpty() ||
            authorField.getText().isEmpty() ||
            pubHouseField.getText().isEmpty() ||
            isbnField.getText().isEmpty() ||
            pubDateField.getText().isEmpty() ||
            categoryComboBox.getValue() == null || categoryComboBox.getValue().isEmpty()) {
            return false;
        }
        return true;
    }

       @FXML
    private void switchBack() throws IOException {
       
        App.setRoot("book");
        
        
    }

    

}
