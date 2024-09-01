package com.library.Controllers;
import com.library.Managers.*;
import com.library.Services.*;
import com.library.Models.*;
import com.library.App;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
/**
 * A controller for managing book-related operations in the library application.
 * This controller shows the list of the books of the library. 
 * The admin can filter thses lists based on book category, remove books, remove books based on their category and edit categories.
 * The admin can also choose to proceed to a separate controller in order to view and edit the book details.
 */
public class BookController {
    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ListView<Book> bookListView;

    @FXML
    private Button removeCategoryButton;
   
    @FXML
    private TextField newCategoryTextField;
    
    @FXML
    private Button changeCategoryButton;

    private ObservableList<Book> books = FXCollections.observableArrayList();
    

    private ObservableList<String> categories = FXCollections.observableArrayList();
    private BookManager bookManager;
    private BookService bookService;
    private ShowAlertService showAlertService;
    private DeleteLoanService deleteLoanService;


/**
     * Initializes the controller and injects all the managers and services that will be used.
     * Initializes the Ui components.
     */
public void initialize() {
    
        this.bookManager=App.getAppState().getBookManager();
        this.bookService=App.getAppState().getBookService();
        this.deleteLoanService=App.getAppState().getDeleteLoanService();
        this.showAlertService=App.getAppState().getShowAlertService();
        // Initialize sample books
        this.books=bookManager.getBookList();



        // Extract unique categories from books
        this.categories=bookService.updateCategories();
        

        categoryComboBox.setItems(categories);
        categoryComboBox.setPromptText("Select a category");

        bookListView.setItems(books);
        bookListView.setCellFactory(param -> new CustomBookCell(this::updateCategories, books, categories,deleteLoanService));
        bookListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Filter books based on selected category
        categoryComboBox.setOnAction(event -> {
            String selectedCategory = categoryComboBox.getValue();
            if (selectedCategory != null && !selectedCategory.isEmpty()) {
                filterBooks(selectedCategory);
            } else {
                bookListView.setItems(books); // Show all books if no category selected
            }
        });

        removeCategoryButton.setOnAction(event -> removeCategory());
        changeCategoryButton.setOnAction(event -> changeCategory());
    }

    
    /** 
     * @param category
     */
    private void filterBooks(String category) {
        ObservableList<Book> filteredBooks = FXCollections.observableArrayList();
        for (Book book : books) {
            if (book.getCategory().equals(category)) {
                filteredBooks.add(book);
            }
        }
        bookListView.setItems(filteredBooks);
    }

    private void updateCategories() {
        String selectedCategory = categoryComboBox.getValue();
        categories=bookService.updateCategories();
        
        categoryComboBox.setItems(categories);
        if(categories.contains(selectedCategory)) {
            categoryComboBox.setValue(selectedCategory);
        }
    }

    
    
    @FXML
    private void removeCategory() {
        String selectedCategory = categoryComboBox.getValue();
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            deleteLoanService.deleteBooksandLoansByCategory(selectedCategory);
            categories.remove(selectedCategory);
            updateCategories();
            categoryComboBox.setValue(null);
            bookListView.setItems(books); // Show all books
        }
        else{
            showAlertService.showWarningAlert("Please choose a category to remove");
        }
    }
    @FXML
    private void changeCategory() {
        String selectedCategory = categoryComboBox.getValue();
        String newCategory = newCategoryTextField.getText().trim();
        System.out.println(selectedCategory);

        if (selectedCategory != null && !selectedCategory.isEmpty() && !newCategory.isEmpty()) {
                bookService.replaceCategory(selectedCategory, newCategory);
            
            updateCategories();
            filterBooks(newCategory);
            newCategoryTextField.clear();
        }
        else if (selectedCategory == null || selectedCategory.isEmpty()) {

            showAlertService.showWarningAlert("Please choose a category to edit");

        }
        else{
            showAlertService.showWarningAlert("Please provide the new category in the text field above");

        }
        
    }
    @FXML
    private void addBook(){
        openBookView(null,categories);
    }

    private interface CategoryUpdater {
        void updateCategories();
    }


    private static class CustomBookCell extends ListCell<Book> {
        private final Button removeButton = new Button("Remove");
        private final Button detailsButton = new Button("Details");
        private final Label textLabel = new Label();
        private final Label copiesLabel = new Label();
        private final HBox hbox = new HBox();
        private final ObservableList<Book> allBooks;
        private final ObservableList<String> categories;
        private final CategoryUpdater categoryUpdater;
        

        public CustomBookCell(CategoryUpdater categoryUpdater, ObservableList<Book> allBooks, ObservableList<String> categories,DeleteLoanService deleteLoanService) {
            this.categoryUpdater = categoryUpdater;
            this.allBooks = allBooks;
            this.categories = categories;
            
            
            removeButton.setStyle("-fx-background-color: red;");
            removeButton.setOnAction(event -> {
                Book itemToRemove = getItem();
                if (itemToRemove != null) {
                    deleteLoanService.deleteBookAndLoan(itemToRemove);
                    updateCategoryList(itemToRemove.getCategory());
                }
            });

            detailsButton.setOnAction(event -> {
                Book selectedItem = getItem();
                if (selectedItem != null) {
                    
                    // Handle book details action  User selectedItem = getItem();
                
                    openBookView(selectedItem,categories);
                    
                    // Handle navigation to details page for selectedItem
                  
                    System.out.println("Details button clicked for book: " + selectedItem.getTitle());
                }
            });

            hbox.getChildren().addAll(textLabel, copiesLabel, removeButton, detailsButton);
            hbox.setSpacing(10);
        }
        private void updateCategoryList(String category) {
            boolean hasBooks = false;
            for (Book book : allBooks) {
                if (book.getCategory().equals(category)) {
                    hasBooks = true;
                    break;
                }
            }

            if (!hasBooks) {
                categories.remove(category);
            }

            categoryUpdater.updateCategories();
        }




        

        @Override
        protected void updateItem(Book item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                textLabel.setText(item.getTitle());
                copiesLabel.setText("Copies: " + item.getCopyNumber());
                setGraphic(hbox);
            }
        }


        private  void openBookView(Book selectedItem, ObservableList<String> categories) {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("views/bookeditadmin.fxml"));
                Parent root = loader.load();
    
                DetailsBookAdmin controller = loader.getController();
                //controller.setBook(selectedItem,categories,asAdmin);
                controller.initialize(selectedItem,categories); // Pass the selected item data to the controller
                App.setRoot(root);
                // Scene scene = new Scene(root);
                // Stage stage = new Stage();
                // stage.setScene(scene);
                
                // stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    
    private  void openBookView(Book selectedItem, ObservableList<String> categories) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/bookeditadmin.fxml"));
            Parent root = loader.load();

            DetailsBookAdmin controller = loader.getController();
            //controller.setBook(selectedItem,categories,asAdmin);
            controller.initialize(selectedItem,categories); // Pass the selected item data to the controller
            App.setRoot(root);
            // Scene scene = new Scene(root);
            // Stage stage = new Stage();
            // stage.setScene(scene);
            
            // stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

        

}


