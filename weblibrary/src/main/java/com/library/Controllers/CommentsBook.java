package com.library.Controllers;

import com.library.Services.*;
import com.library.Models.*;
import com.library.App;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
/**
 * A controller for displaying comments related to a specific book in the library application.
 * Displays a list with comments and the user that made them.
 */
public class CommentsBook {
    private ObservableList<Comment> comments;

    private UserService userService;
    
    @FXML
    private ListView<Comment> commentListView;
    @FXML
    private Label commentLabel; 

    
    
   /**
     * Initializes the controller and sets up the services that will be used.
     * Also initializes   the ui  components.
     *
     * @param book The book for which comments are to be displayed
     */
    public void initialize(Book book) {
        this.userService = App.getAppState().getUserService();
        
        // book.displayBookDetails();
        comments=FXCollections.observableArrayList(book.getUserComment());
        

        if(!comments.isEmpty()){
        commentLabel.setVisible(false);
        commentListView.setItems(comments);
        commentListView.setCellFactory(param -> new CustomCommentCell(userService));
        commentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        }
        else{
            commentListView.setVisible(false);
            commentLabel.setVisible(true);
            
        } 
    }


    private static class CustomCommentCell extends ListCell<Comment> {
        private final Label username;
        private final TextField textField;
        private UserService userService;
        
       
        private final VBox vbox = new VBox();
        
       

        public CustomCommentCell(UserService userService) {
            this.userService = userService;
            
            
            username = new Label();
            textField = new TextField();
            vbox.getChildren().addAll(username, textField);
            vbox.setSpacing(5);

        }

        @Override
        protected void updateItem(Comment item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                User u= userService.getUserById(item.getUserid());
                
                  username.setText((u!=null) ? u.getUsername() : "Deleted User") ;
                        
                    
                
                textField.setText(item.getCommentText());
                setGraphic(vbox);
                
                
            }
        }
        
        

    }
    
    
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("userstart");
    }
}
