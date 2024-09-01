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

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A controller for managing the start screen after a successgul user login.
 * Displays all the app books and the user can choose to borrow or view details and comments about them. 
 * The user can also search a book based on the title,author and year of publication.
 * He can also choose to view his loans.
 */
public class UserStartController {
   

    @FXML
    private ListView<Book> bookListView;

    @FXML 
    private Button searchButton;

    @FXML
    private TextField titleField;

    @FXML  
    private TextField authorField; 

    @FXML 
    private TextField dateField;

    @FXML
    private Label remainingLoans;

    @FXML
    private Button signout;
    @FXML
    private Button viewLoans;
    
    

    private ObservableList<Book> books = FXCollections.observableArrayList();
    private ObservableList<String> categories = FXCollections.observableArrayList();
    private BookManager bookManager;
    private SessionManager sessionManager;
    private LoanService loanService;
    private BookService bookService;
    private ShowAlertService showAlertService;
    

    

    @FXML
    private void initialize() {
        this.bookManager = App.getAppState().getBookManager();
        this.sessionManager = App.getAppState().getSessionManager();
        this.loanService = App.getAppState().getLoanService();
        this.bookService = App.getAppState().getBookService();
        this.showAlertService=App.getAppState().getShowAlertService();
        // Initialize sample books
        books=bookManager.getBookList();
        

        
        for (Book book : books) {
            if (!categories.contains(book.getCategory())) {
                categories.add(book.getCategory());
            }
        }
        
        
        remainingLoans.setText("Number of remaining loans : "+(2-loanService.getLoansByUserId(sessionManager.getCurrentUser().getId()).size()));
        

        // Extract unique categories from books
       
        searchButton.setOnAction(event -> {
            filterBooks(titleField.getText(), authorField.getText(), dateField.getText());
        });
        

        bookListView.setItems(books);
        bookListView.setCellFactory(param -> new CustomBookCell( categories,bookService,loanService,sessionManager,showAlertService));
        bookListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        

        // Filter books based on selected category
        

    }
    
    
    @FXML
    private void filterBooks( String title,String author, String date) {
        
        bookListView.setItems(bookService.searchBook(title,author,date));
    }

    

    
   
   
   
    


    private  class CustomBookCell extends ListCell<Book> {
        private final Button borrowButton = new Button("Borrow");
        private final Button detailsButton = new Button("Details");
        private final Button commentsButton = new Button("Comments");
        private final Label titleLabel = new Label();
        private final Label authorLabel = new Label();
        private final Label publicationDateLabel = new Label();
        private final Label isbnLabel = new Label();
        private final Label ratingLabel = new Label();
        private final Label  usersLabel = new Label(); 
        private final Label copiesLabel = new Label();
        private final HBox hbox1 = new HBox();
        private final HBox hbox2 = new HBox();
        private final VBox vbox = new VBox();
        
        
      

        public CustomBookCell( ObservableList<String> categories,BookService bookService,LoanService loanService,SessionManager sessionManager,ShowAlertService showAlertService) {
            
           
            
            commentsButton.setOnAction(event -> {
                Book itemToComment = getItem();
                if (itemToComment != null) {
                    // Handle comments action
                    openCommentView(itemToComment);
                }
            });
            
            borrowButton.setStyle("-fx-background-color: green;");
            
            borrowButton.setOnAction(event -> {
                Book itemToBorrow = getItem();
                if (itemToBorrow != null) {
                     if(loanService.getLoansByUserId(sessionManager.getCurrentUser().getId()).size()==2){
                        showAlertService.showErrorAlert("You can't borrow more than 2 books at a time.");

                         
                    }
                    else{
                        LocalDate today = LocalDate.now();

                        
                        LocalDate futureDate = today.plusDays(5);
                
                       
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        loanService.addLoan(sessionManager.getCurrentUser().getId(), itemToBorrow.getUuid(), futureDate.format(formatter));
                        
                        bookService.updateCopies(itemToBorrow, -1);
                        copiesLabel.setText("Copies: " + itemToBorrow.getCopyNumber());
                        remainingLoans.setText("Number of remaining loans : "+(2-loanService.getLoansByUserId(sessionManager.getCurrentUser().getId()).size()));

                        
                        
                    }
                   
                    
                }
            });

            detailsButton.setOnAction(event -> {
                Book selectedItem = getItem();
                if (selectedItem != null) {
                    
                    // Handle book details action  User selectedItem = getItem();
                
                    openBookView(selectedItem,categories,false);
                    
                    // Handle navigation to details page for selectedItem
                  
                    
                }
            });

            commentsButton.setOnAction(event -> {
                Book selectedItem = getItem();
                if(selectedItem != null) {
                    openCommentView(selectedItem);
                }
            });

            hbox1.getChildren().addAll(titleLabel,authorLabel,publicationDateLabel, isbnLabel, ratingLabel);
            hbox1.setSpacing(10);
            hbox2.getChildren().addAll( usersLabel , copiesLabel,  detailsButton,commentsButton ,borrowButton);
            hbox2.setSpacing(10);
            vbox.getChildren().addAll(hbox1, hbox2);

        }




        

        @Override
        protected void updateItem(Book item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                titleLabel.setText(item.getTitle());
                authorLabel.setText("Author: " + item.getAuthor());
                publicationDateLabel.setText("Publication Date: " + item.getPublicationDate());
                isbnLabel.setText("ISBN: " + item.getIsbn());
                ratingLabel.setText("Rating: " + String.format("%.2f", item.getrating()));
                usersLabel.setText("Number of ratings: " + item.getNumberOfUsers());
                copiesLabel.setText("Copies: " + item.getCopyNumber());
                if(item.getCopyNumber()==0){
                    borrowButton.setStyle("-fx-background-color:gray;");
                    borrowButton.setDisable(true);
                }
               
                setGraphic(vbox);
            }
        }
        private  void openBookView(Book selectedItem, ObservableList<String> categories, boolean asAdmin) {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("views/bookedit.fxml"));
                Parent root = loader.load();
                System.out.println(asAdmin);
    
                DetailsBook controller = loader.getController();
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
    
        private  void openCommentView(Book selectedItem) {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("views/bookcomments.fxml"));
                Parent root = loader.load();
                
    
                CommentsBook controller = loader.getController();
                //controller.setBook(selectedItem,categories,asAdmin);
                controller.initialize(selectedItem); // Pass the selected item data to the controller
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

    
   
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void signout() throws IOException{
        sessionManager.setCurrentUser(null);
        App.setRoot("start");
    }
    @FXML
    private void manageLoans() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/loan.fxml"));
        Parent root = loader.load();
        

        LoanController controller = loader.getController();
        //controller.setBook(selectedItem,categories,asAdmin);
        controller.setup(sessionManager.getCurrentUser()); // Pass the selected item data to the controller
        App.setRoot(root);
    }

}
