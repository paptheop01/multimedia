package com.library;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RateCommentBookController {
    private Book book;
    
    @FXML
    private Button rateButton;
    @FXML
     private Button commentButton;
    @FXML
    private Button returnButton;
    @FXML
    private TextField commentTextfield;
 
    @FXML
    private ComboBox<Double> ratingComboBox;
    @FXML 
    private Label desclabel;
    

    public void initialize(){
        ratingComboBox.getItems().addAll(1.0, 2.0, 3.0, 4.0, 5.0);
        
        // Set a default value (optional)
        ratingComboBox.setValue(1.0);
        
        desclabel.setText("Choose rating");
        commentTextfield.setPromptText("Add Review");
        
    }
    
    /** 
     * @param book
     */
    public void setup(Book book){
        this.book = book;
       
    }
    @FXML
    private void rate(){
        double rating =  ratingComboBox.getValue();
        App.getAppState().getBookService().rate(book, rating);
        createAlert("Your rating has been submitted");
    }

    @FXML 
    private void leavecomment() {
        String comment = commentTextfield.getText();
        book.addUserComment(App.getAppState().getSessionManager().getCurrentUser().getId(), comment);
        createAlert("Your comment has been submitted");
        
    }
    
    /** 
     * @throws IOException
     */
    @FXML
    private void returnBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("loan.fxml"));
        Parent root = loader.load();
        

        LoanController controller = loader.getController();
        //controller.setBook(selectedItem,categories,asAdmin);
        controller.setup(App.getAppState().getSessionManager().getCurrentUser()); // Pass the selected item data to the controller
        App.setRoot(root);
        

    }
    @FXML
    private void createAlert(String Message){
        Alert warningAlert = new Alert(AlertType.CONFIRMATION);
        
        warningAlert.setHeaderText(null);
        warningAlert.setContentText(Message);
        warningAlert.showAndWait();

    }


}
