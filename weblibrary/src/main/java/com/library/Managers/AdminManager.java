package com.library.Managers;
import com.library.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This class manages the list of administrators in the library system.
 * It provides methods to add, remove, and retrieve the list of admins.
 */
public class AdminManager {
    private ObservableList<Admin> adminList = FXCollections.observableArrayList();

    
    /**
     * Returns the observable list of administrators.
     *
     * @return the observable list of administrators
     */
    public ObservableList<Admin> getAdminList() {
        return adminList;
    }

    /**
     * Adds a new admin to the list.
     *
     * @param admin The admin to be added.
     */
    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }
    /**
     * Removes an admin from the list.
     *
     * @param admin The admin to be removed.
     */
    public void removeAdmin(Admin admin) {
        adminList.remove(admin);
    }

    
}