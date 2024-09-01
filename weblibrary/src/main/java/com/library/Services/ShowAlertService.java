package com.library.Services;




import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * A service for displaying custom alerts in a JavaFX application.
 * Uses the Alert class from the javafx.scene.control package.
 */
public class ShowAlertService {
    

    
    /**
 * Displays a custom alert with the specified alert type, title, and message.
 *
 * @param alertType The type of the alert (INFORMATION, WARNING, ERROR).
 * @param title The title of the alert.
 * @param message The message to display in the alert.
 */
    public void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
 * Displays an information alert with the specified message.
 *
 * @param message The message to display in the alert.
 */
    public void showInfoAlert( String message) {
        showAlert(AlertType.INFORMATION, "", message);
    }
    /**
 * Displays a warning alert with the specified message.
 *
 * @param message The message to display in the alert.
 */
    public void showWarningAlert( String message) {
        showAlert(AlertType.WARNING, "Warning", message);
    }
    /**
 * Displays an error alert with the specified message.
 *
 * @param message The message to display in the alert.
 */
    public void showErrorAlert( String message) {
        showAlert(AlertType.ERROR, "Error", message);
    }
}


