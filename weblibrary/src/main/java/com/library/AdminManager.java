package com.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminManager {
    private ObservableList<Admin> adminList = FXCollections.observableArrayList();

    public ObservableList<Admin> getAdminList() {
        return adminList;
    }

    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    public void removeAdmin(Admin admin) {
        adminList.remove(admin);
    }

    // Additional methods to manage admins can be added here
}