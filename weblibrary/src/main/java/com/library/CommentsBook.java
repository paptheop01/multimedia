package com.library;


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

public class CommentsBook {
    private ObservableList<Comment> comments;
    private ObservableList<User> users;
    @FXML
    private ListView<Comment> commentListView;
    @FXML
    private Label commentLabel; 

    
    
    /** 
     * @param book
     */
    public void initialize(Book book) {
        users= App.getAppState().getItemList();
        // book.displayBookDetails();
        comments=FXCollections.observableArrayList(book.getUserComment());
        

        if(!comments.isEmpty()){
        commentLabel.setVisible(false);
        commentListView.setItems(comments);
        commentListView.setCellFactory(param -> new CustomCommentCell( users));
        commentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println("some");
        }
        else{
            commentListView.setVisible(false);
            commentLabel.setVisible(true);
            
        } 
    }


    private static class CustomCommentCell extends ListCell<Comment> {
        private final Label username;
        private final TextField textField;
        private ObservableList<User> users;

       
        private final VBox vbox = new VBox();
        
       

        public CustomCommentCell(ObservableList<User> users) {
            
            this.users = users;
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
                
                for( User u : users){
                    if(u.getId().equals(item.getUserid())){
                        username.setText(u.getUsername());
                        break;
                    }
                }
                textField.setText(item.getCommentText());
                setGraphic(vbox);
                
                
            }
        }
        
        

    }
    
    /** 
     * @throws IOException
     */
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("userstart");
    }
}
