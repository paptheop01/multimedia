package com.library;

import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.time.LocalDate;

import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import javafx.scene.control.ListView;

import javafx.scene.layout.HBox;



public class LoanController {

    private ObservableList<Loan> loans = FXCollections.observableArrayList();
    private  ObservableList<Book> books = FXCollections.observableArrayList();
    private  ObservableList<User> users = FXCollections.observableArrayList();
    

  

    private static class CustomListCell extends ListCell<Loan> {
        private final Button button = new Button();
        private final Label userLabel = new Label();
        private final Label bookLabel = new Label();
        private final Label dateLabel = new Label();
        private final Label startLabel = new Label();
        private final Label state = new Label();
        private final HBox hbox = new HBox();
        private ObservableList<Book> books;
        private ObservableList<User> users;
        private boolean asAdmin;
        

        public CustomListCell(ObservableList<Book> books, ObservableList<User> users, boolean asAdmin) {
            this.books = books;
            this.users = users;
            this.asAdmin = asAdmin;
            if(asAdmin){
                button.setText("Terminate");
                button.setStyle("-fx-background-color: blue;");
                button.setOnAction(event -> {
                    Loan itemToRemove = getItem();
                    if (itemToRemove != null) {
                        App.getAppState().removeItemFromList(itemToRemove);
                        getListView().getItems().remove(itemToRemove);
                        for( Book b : books){
                            if(b.getUuid().equals(itemToRemove.getBookUid())){
                                b.setCopyNumber(b.getCopyNumber()+1);
                                break;
                            }
                        }

                    }
                });
            }
            else{
                button.setText("Rate and Comment book");
                button.setStyle("-fx-background-color: blue;");
                button.setOnAction(event -> {
                    Loan itemToRate = getItem();
                    if (itemToRate != null) {
                        
                        for( Book b : books){
                            if(b.getUuid().equals(itemToRate.getBookUid())){
                                openRateComment(b);
                                break;
                            }
                        }

                    }
                });

            }

          

            hbox.getChildren().addAll(userLabel,bookLabel,startLabel,dateLabel,state,button);
            hbox.setSpacing(10);
        }

        @Override
        protected void updateItem(Loan item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                
                userLabel.setVisible(asAdmin);
                
                setGraphic(hbox);
                for( User u : users){
                    if(u.getId().equals(item.getUserUid())){
                        userLabel.setText(u.getUsername());
                        break;
                    }
                }
                for( Book b : books){
                    if(b.getUuid().equals(item.getBookUid())){
                        bookLabel.setText(b.getTitle());
                        break;
                    }
                }

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
                FXMLLoader loader = new FXMLLoader(App.class.getResource("bookrate.fxml"));
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

    
    /** 
     * @param username
     */
    private void filterLoans(String username) {
        ObservableList<Loan> filteredLoans = FXCollections.observableArrayList();
        for (Loan loan : loans) {
            if (loan.getUserUid().equals(users.stream().filter(user -> user.getUsername().equals(username)).findFirst().map(User::getId).orElse(null))) {
                filteredLoans.add(loan);
            }
        }
        if(filteredLoans.isEmpty()){
            filteredLoans = loans;
        }
        loanListView.setItems(filteredLoans);
    }

    @FXML
    private void filter() {
        String username = userNameField.getText().trim();
        if (username!="") {
            filterLoans(username);
        } else {
            loanListView.setItems(loans);
        }
    }

    @FXML
    private ListView<Loan> loanListView;

    @FXML
    private TextField userNameField;

    @FXML
    private Button filterButton;
    @FXML
    private Button returnButton;


    @FXML
    public void initialize() {
        // Initialize the ListView with data from AppState's itemList
        loans = App.getAppState().getLoanList();
        users = App.getAppState().getItemList();
        books = App.getAppState().getBookList();
        
    }

    
    /** 
     * @param user
     * @param asAdmin
     */
    @FXML
    public void setup(User user,boolean asAdmin){
        returnButton.setOnAction(event ->{switchToPrimary(asAdmin);});
        if(asAdmin){
            loanListView.setItems(loans);
            loanListView.setCellFactory(param -> new CustomListCell(books,users,asAdmin));
        }
        else{
            if(user !=null){
                filterButton.setVisible(false);
                userNameField.setVisible(false);
                loanListView.setItems(loans.filtered(l -> l.getUserUid().equals(user.getId())));
                loanListView.setCellFactory(param -> new CustomListCell(books,users,asAdmin));
            }
        }

    }



    

    @FXML
    private void switchToPrimary(boolean asAdmin)  {
        try{
          if(asAdmin){
                App.setRoot("primary");
            }
            else{
                App.setRoot("userstart");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


   

}
