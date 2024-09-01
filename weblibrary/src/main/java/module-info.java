module com.library {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;

    opens com.library to javafx.fxml;
    exports com.library;

    
}
