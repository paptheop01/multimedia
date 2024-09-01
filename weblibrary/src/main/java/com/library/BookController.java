package com.library;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
/**
 * 
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


/**
 * 
 */
public void initialize() {
        // Initialize sample books
        this.books=App.getAppState().getBookManager().getBookList();



        // Extract unique categories from books
        this.categories=App.getAppState().getBookService().updateCategories();
        

        categoryComboBox.setItems(categories);
        categoryComboBox.setPromptText("Select a category");

        bookListView.setItems(books);
        bookListView.setCellFactory(param -> new CustomBookCell(this::updateCategories, books, categories));
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
        ObservableList<Book> filteredBooks = App.getAppState().getBookService().searchBookByCategory(category);
        
        bookListView.setItems(filteredBooks);
    }

    private void updateCategories() {
        String selectedCategory = categoryComboBox.getValue();
        categories=App.getAppState().getBookService().updateCategories();
        
        categoryComboBox.setItems(categories);
        if(categories.contains(selectedCategory)) {
            categoryComboBox.setValue(selectedCategory);
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
    private void removeCategory() {
        String selectedCategory = categoryComboBox.getValue();
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            App.getAppState().getDeleteLoanService().deleteBooksandLoansByCategory(selectedCategory);
            categories.remove(selectedCategory);
            updateCategories();
            categoryComboBox.setValue(null);
            bookListView.setItems(books); // Show all books
        }
        else{
            createAlert("Please choose a category to remove");
        }
    }
    @FXML
    private void changeCategory() {
        String selectedCategory = categoryComboBox.getValue();
        String newCategory = newCategoryTextField.getText().trim();
        System.out.println(selectedCategory);

        if (selectedCategory != null && !selectedCategory.isEmpty() && !newCategory.isEmpty()) {
                App.getAppState().getBookService().replaceCategory(selectedCategory, newCategory);
            
            updateCategories();
            filterBooks(newCategory);
            newCategoryTextField.clear();
        }
        else if (selectedCategory == null || selectedCategory.isEmpty()) {

            createAlert("Please choose a category to edit");

        }
        else{
            createAlert("Please provide the new category in the text field above");

        }
        
    }
    @FXML
    private void addBook(){
        openBookView(null,categories,true);
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

        public CustomBookCell(CategoryUpdater categoryUpdater, ObservableList<Book> allBooks, ObservableList<String> categories) {
            this.categoryUpdater = categoryUpdater;
            this.allBooks = allBooks;
            this.categories = categories;
            
            removeButton.setStyle("-fx-background-color: red;");
            removeButton.setOnAction(event -> {
                Book itemToRemove = getItem();
                if (itemToRemove != null) {
                    App.getAppState().getDeleteLoanService().deleteBookAndLoan(itemToRemove);
                    updateCategoryList(itemToRemove.getCategory());
                }
            });

            detailsButton.setOnAction(event -> {
                Book selectedItem = getItem();
                if (selectedItem != null) {
                    
                    // Handle book details action  User selectedItem = getItem();
                
                    openBookView(selectedItem,categories,true);
                    
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
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

        private static void openBookView(Book selectedItem, ObservableList<String> categories,boolean asAdmin) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("bookeditadmin.fxml"));
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


