package com.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserManager {
    private ObservableList<User> userList = FXCollections.observableArrayList();

    public ObservableList<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    // Additional methods to manage users can be added here
}