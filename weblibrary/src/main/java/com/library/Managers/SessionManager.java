package com.library.Managers;
import com.library.Models.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
/**
 * This class manages the  user and admin that are currently logged  in the library system.
 * It provides methods to set and retrieve the current user and admin.
 */
public class SessionManager {
     private final ObjectProperty<User> currentUser = new SimpleObjectProperty<>(null);
    private final ObjectProperty<Admin> currentAdmin = new SimpleObjectProperty<>(null);

    
    /**
     * Returns the currently logged user.
     *
     * @return User
     */
    public User getCurrentUser() {
        return currentUser.get();
    }
/**
 * Sets the currently logged user.
 *
 * @param user The user to be set as the current user.
 */
    public void setCurrentUser(User user) {
        currentUser.set(user);
    }
    /**
     * Returns the currently logged admin.
     *
     * @return Admin
     */
    public Admin getCurrentAdmin() {
        return currentAdmin.get();
    }
    /**
 * Sets the currently logged user.
 *
 * @param admin The admin to be set as the current admin.
 */
    public void setCurrentAdmin(Admin admin) {
        currentAdmin.set(admin);
    }
     /**
     * Returns an ObjectProperty for the currentUser field in order for changes to be observed.
     *
     * @return ObjectProperty<User>
     */
    public ObjectProperty<User> currentUserProperty() {
        return currentUser;
    }
     /**
     * Returns an ObjectProperty for the currentAdmin field in order for changes to be observed.
     *
     * @return ObjectProperty<Admin>
     */
    public ObjectProperty<Admin> currentAdminProperty() {
        return currentAdmin;
    }

}
