package com.library.Controllers;

import com.library.Services.*;
import com.library.Models.*;
import com.library.App;

import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.time.LocalDate;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import javafx.scene.control.ListView;

import javafx.scene.layout.HBox;


/**
 * A controller for the user to  manage his loans in the library application.
 * Loans are displayed and the user has the ability to proceed to a separate controller in order to rate and comment on the book.
 */
public class LoanController {

    
    private BookService bookService;
    private LoanService loanService;
    

    @FXML
    private ListView<Loan> loanListView;

  

    
    @FXML
    private Button returnButton;

    /**
     * Initializes the controller and sets up event handlers, data bindings, and other necessary components.
     */
    @FXML
    public void initialize() {
        this.bookService = App.getAppState().getBookService();
        this.loanService= App.getAppState().getLoanService();
        // Initialize the ListView with data from AppState's itemList
        
        
        
    }

    
   /**
     * Sets up the controller with the loans of the specific user.
     *
     * @param user The user for whom loans are to be displayed
     */
    @FXML
    public void setup(User user){
        returnButton.setOnAction(event ->{switchToPrimary();});
        
        if(user !=null){
            
            loanListView.setItems(loanService.getLoansByUserId(user.getId()));
            loanListView.setCellFactory(param -> new CustomListCell(bookService));
        }
        

    }


    private static class CustomListCell extends ListCell<Loan> {
        private final Button button = new Button();
       
        private final Label bookLabel = new Label();
        private final Label dateLabel = new Label();
        private final Label startLabel = new Label();
        private final Label state = new Label();
        private final HBox hbox = new HBox();
        private BookService bookService;
       
        

        public CustomListCell(BookService bookService) {
            this.bookService = bookService;
            
            
            
                button.setText("Rate and Comment book");
                button.setStyle("-fx-background-color: blue;");
                button.setOnAction(event -> {
                    Loan itemToRate = getItem();
                    if (itemToRate != null) {
                        
                        
                        openRateComment(bookService.getBookByBookId(itemToRate.getBookUid()));
                               

                    }
                });

            

          

            hbox.getChildren().addAll(bookLabel,startLabel,dateLabel,state,button);
            hbox.setSpacing(10);
        }

        @Override
        protected void updateItem(Loan item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                
                
                
                setGraphic(hbox);
                
                   
                
                    
                    
                
                
                bookLabel.setText(bookService.getBookByBookId(item.getBookUid()).getTitle());
                    
                    
                

                dateLabel.setText("End date: "+(item.getLoanEndDate()));
                startLabel.setText("Start date: "+((LocalDate.parse(item.getLoanEndDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).minusDays(5))));
                LocalDateTime dateend= LocalDate.parse(item.getLoanEndDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atTime(23, 59, 59); 
                LocalDateTime now = LocalDateTime.now();  
                if(now.isAfter(dateend)){
                    state.setText("Overdue");
                    hbox.setStyle("-fx-background-color: red;");

                }
                else{
                    state.setText("Active");
                    hbox.setStyle("-fx-background-color: green;");
                }
            }
        }
        private void openRateComment(Book book)  {
           try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("views/bookrate.fxml"));
                Parent root = loader.load();
                
    
                RateCommentBookController controller = loader.getController();
                //controller.setBook(selectedItem,categories,asAdmin);
                controller.setup(book); // Pass the selected item data to the controller
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
    private void switchToPrimary()  {
        try{
          
            
                App.setRoot("userstart");
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


   

}
