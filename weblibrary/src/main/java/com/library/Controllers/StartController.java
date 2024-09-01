package com.library.Controllers;

import com.library.Services.*;
import com.library.Models.*;
import com.library.App;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
/**
 * A controller for managing the start screen in the library application.
 * Directs to user and admin login, signup, and display of top-rated books.
 */
public class StartController {
    @FXML
    private ListView<Book> itemListView;
    @FXML 
    private Button loginButton;
    @FXML
    private Button loginAdButton; 

    private BookService bookService;
    @FXML
    private void initialize() {
        this.bookService=App.getAppState().getBookService();
        loginAdButton.setOnAction(event -> {login(true);});
        loginButton.setOnAction(event -> {login(false);});
        // Initialize the ListView with data from AppState's itemList
        
        itemListView.setItems(bookService.getTopBooks(5) );
        itemListView.setCellFactory(param -> new CustomListCell());
    }
     private static class CustomListCell extends ListCell<Book> {
       
        private final Label textLabel = new Label();
        private final Label rating = new Label();
        private final HBox hbox = new HBox();
        

        public CustomListCell() {
            

            

            hbox.getChildren().addAll(textLabel,rating);
            hbox.setSpacing(10);
        }

        @Override
        protected void updateItem(Book item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                setGraphic(hbox);
                textLabel.setText(item.getTitle());
                rating.setText("Rating: " + item.getrating());
            }
        }
    }

    @FXML
    private  void signUp() {
        try {
     
        App.setRoot("signup");
    }
            
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * @param asAdmin
     */
    @FXML
    private  void login(boolean asAdmin) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/login.fxml"));
            Parent root = loader.load();

            LoginController controller = loader.getController();
            controller.initData(asAdmin); // Pass the selected item data to the controller
            App.setRoot(root);
            // Scene scene = new Scene(root);
            // Stage stage = new Stage();
            // stage.setScene(scene);
            // stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

  
   
    


