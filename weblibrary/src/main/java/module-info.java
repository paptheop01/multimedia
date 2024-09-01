module com.library {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;

    opens com.library to javafx.fxml;
    exports com.library;
    exports com.library.Controllers;
    exports com.library.Models;
    exports com.library.Services;
    exports com.library.Managers;

    // Open packages to allow reflection by JavaFX and other frameworks
    
    opens com.library.Controllers to javafx.fxml;
    opens com.library.Models to javafx.base;
    opens com.library.Managers to javafx.base;
    opens com.library.Services to javafx.base;
}