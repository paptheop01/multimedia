package com.library;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;



public class SecondaryController  {
    
    
    
    @FXML
    private ListView<User> itemListView;

    // Initialize method called after FXML loading
    @FXML
    private void initialize() {
        
        // Initialize the ListView with data from AppState's itemList
        ObservableList<User> itemList = App.getAppState().getUserManager().getUserList();
        itemListView.setItems(itemList);
        itemListView.setCellFactory(param -> new CustomListCell());
    }
        // Create a custom cell factory for the ListView
        private static class CustomListCell extends ListCell<User> {
        private final Button removeButton = new Button("Remove");
        private final Button detailsButton = new Button("Details");
        private final Label textLabel = new Label();
        private final HBox hbox = new HBox();
        

        public CustomListCell() {
            removeButton.setStyle("-fx-background-color: red;");
            removeButton.setOnAction(event -> {
                User itemToRemove = getItem();
                if (itemToRemove != null) {
                    App.getAppState().getUserDeletionService().deleteUser(itemToRemove);
                }
            });

            detailsButton.setOnAction(event -> {
                User selectedItem = getItem();
                if (selectedItem != null) {
                    openDetailsView(selectedItem);
                    // Handle navigation to details page for selectedItem
                    System.out.println("Show details for: " + selectedItem.getFullName());
                }
            });

            hbox.getChildren().addAll(textLabel,removeButton, detailsButton);
            hbox.setSpacing(10);
        }

        @Override
        protected void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                setGraphic(hbox);
                textLabel.setText(item.getUsername());
            }
        }
    }
   
    
  
    /** 
     * @param item
     */
  
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    private static void openDetailsView(User selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("detailsuser.fxml"));
            Parent root = loader.load();

            DetailsUserController controller = loader.getController();
            controller.initData(selectedItem, App.getAppState().getUserService()); // Pass the selected item data to the controller
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
