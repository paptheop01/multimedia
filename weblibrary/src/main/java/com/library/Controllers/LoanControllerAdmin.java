package com.library.Controllers;
import com.library.Managers.*;
import com.library.Services.*;
import com.library.Models.*;
import com.library.App;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.layout.HBox;
/**
 * A controller for managing loans in the library application.
 * The admin can view all the loans of the library and terminate them.
 */
public class LoanControllerAdmin {
    private ObservableList<Loan> loans = FXCollections.observableArrayList();
    private DeleteLoanService deleteLoanService;
    private BookService bookService;
    private UserService userService;
    private LoanManager loanManager;
    
    

  

    

    
  

    @FXML
    private ListView<Loan> loanListView;

   
    @FXML
    private Button returnButton;

    /**
     * Initializes the controller and sets up event handlers, data bindings, and other necessary components.
     */
    @FXML
    public void initialize() {
        this.deleteLoanService = App.getAppState().getDeleteLoanService();
        this.loanManager=App.getAppState().getLoanManager();
        this.bookService=App.getAppState().getBookService();
        this.userService=App.getAppState().getUserService();
        // Initialize the ListView with data from AppState's itemList
        loans = loanManager.getLoanList();
       
        
    }

    
    /**
     * Sets up the ui components.
     *
     */
    @FXML
    public void setup(){
        returnButton.setOnAction(event ->{switchToPrimary();});
        
            loanListView.setItems(loans);
            loanListView.setCellFactory(param -> new CustomListCell(deleteLoanService, bookService,userService));
        
        

    }

    private static class CustomListCell extends ListCell<Loan> {
        private final Button button = new Button();
        private final Label userLabel = new Label();
        private final Label bookLabel = new Label();
        private final Label dateLabel = new Label();
        private final Label startLabel = new Label();
        private final Label state = new Label();
        private final HBox hbox = new HBox();

        private BookService bookService;
        private UserService userService;
        
        
        

        public CustomListCell(DeleteLoanService deleteLoanService, BookService bookService, UserService userService) {
                this.bookService = bookService;
                this.userService = userService;
           
            
                button.setText("Terminate");
                button.setStyle("-fx-background-color: blue;");
                button.setOnAction(event -> {
                    Loan itemToRemove = getItem();
                    if (itemToRemove != null) {
                        deleteLoanService.deleteLoan(itemToRemove);
                       

                    }
                });
            
            

          

            hbox.getChildren().addAll(userLabel,bookLabel,startLabel,dateLabel,state,button);
            hbox.setSpacing(10);
        }

        @Override
        protected void updateItem(Loan item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                
                
                
                setGraphic(hbox);
                
                   
                userLabel.setText(userService.getUserById(item.getUserUid()).getUsername());
                    
                    
                
                
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
        
    }



    

    @FXML
    private void switchToPrimary()  {
        try{
          
            App.setRoot("primary");
            
           
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


}
