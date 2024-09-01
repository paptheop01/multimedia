package com.library;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowAlertService {
    

    public void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showInfoAlert( String message) {
        showAlert(AlertType.INFORMATION, "", message);
    }

    public void showWarningAlert( String message) {
        showAlert(AlertType.WARNING, "Warning", message);
    }

    public void showErrorAlert( String message) {
        showAlert(AlertType.ERROR, "Error", message);
    }
}


