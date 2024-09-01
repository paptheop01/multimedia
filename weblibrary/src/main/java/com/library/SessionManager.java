package com.library;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SessionManager {
     private final ObjectProperty<User> currentUser = new SimpleObjectProperty<>(null);
    private final ObjectProperty<Admin> currentAdmin = new SimpleObjectProperty<>(null);

    public User getCurrentUser() {
        return currentUser.get();
    }

    public void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public Admin getCurrentAdmin() {
        return currentAdmin.get();
    }

    public void setCurrentAdmin(Admin admin) {
        currentAdmin.set(admin);
    }

    public ObjectProperty<User> currentUserProperty() {
        return currentUser;
    }

    public ObjectProperty<Admin> currentAdminProperty() {
        return currentAdmin;
    }

}
