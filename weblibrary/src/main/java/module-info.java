module com.library {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;

    opens com.library to javafx.fxml;
    exports com.library;

    
}
