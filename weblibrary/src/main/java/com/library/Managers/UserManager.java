package com.library.Managers;
import com.library.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This class manages the list of users in the library system.
 * It provides methods to add, remove, and retrieve the list of users.
 */
public class UserManager {
    private ObservableList<User> userList = FXCollections.observableArrayList();

    
    /**
     * Returns the observable list of users.
     *
     * @return observable list of users
     */
    public ObservableList<User> getUserList() {
        return userList;
    }
    /**
     * Adds a new user to the list.
     *
     * @param user The user to be added.
     */
    public void addUser(User user) {
        userList.add(user);
    }
    /**
     * Removes a user from the list.
     *
     * @param user The user to be removed.
     */
    public void removeUser(User user) {
        userList.remove(user);
    }

    
}