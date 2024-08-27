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

    private Book book;
    private Boolean asAdmin;

    
    
    /** 
     * @param book
     * @param categories
     * @param asAdmin
     */
    public void initialize(Book book, ObservableList<String> categories, boolean asAdmin) {
        this.book = book;
        this.asAdmin = asAdmin;
       
        
        // Initialize ComboBox with available categories
        if(asAdmin){
        categoryComboBox.setItems(categories);
        categoryComboBox.setEditable(true); // Allow custom input
        }
        else{
        saveChangesButton.setVisible(false);
        categoryComboBox.setEditable(false);
        titleField.setEditable(false);
        authorField.setEditable(false);
        pubHouseField.setEditable(false);
        isbnField.setEditable(false);
        pubDateField.setEditable(false);
        }
        // Set default selection to the book's current category
        if (book != null) {
            categoryComboBox.getSelectionModel().select(book.getCategory());
            if(!asAdmin) {
                categoryComboBox.setDisable(true);
                categoryComboBox.setStyle( "-fx-opacity: 1.0; ");
                
            }
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            pubHouseField.setText(book.getPublicationHouse());
            isbnField.setText(book.getIsbn());
            pubDateField.setText(book.getPublicationDate());
        }
    }

    // public void setBook(Book book, ObservableList<String> categories, boolean asAdmin) {
    //     this.book = book;
    //     System.out.println("Details button clicked for book: " + asAdmin);
    //     this.categories = categories;
    //     this.asAdmin = false;
    //     System.out.println(this.asAdmin);
    // }

    @FXML
    private void saveChanges() {
        if(validateFields()){
        if (book != null) {
            // Update book details
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setPublicationHouse(pubHouseField.getText());
            book.setIsbn(isbnField.getText());
            book.setPublicationDate(pubDateField.getText());
            book.setCategory(categoryComboBox.getValue());
            ObservableList<Book> itemList = App.getAppState().getBookList();
            int index = itemList.indexOf(book);
            if (index != -1) {
                itemList.set(index, book); // Update the item at the specific index
            }
            messageLabel.setText("Changes saved successfully!");
        }
        else {
            // Create a new book
            Book newBook = new Book(
                titleField.getText(),
                authorField.getText(),
                pubHouseField.getText(),
                isbnField.getText(),
                pubDateField.getText(),
                categoryComboBox.getValue()
            );

            ObservableList<Book> itemList = App.getAppState().getBookList();
            itemList.add(newBook); // Add the new book to the list
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
        if(asAdmin){
            App.setRoot("book");
        }
        else{
            App.setRoot("userstart");
        }
    }

    
    
}
