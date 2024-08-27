package com.library;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents the application's state, managing user, admin, book, and loan data.
 * It uses the Singleton design pattern to ensure only one instance of this class exists at a time.
 */
public class AppState {
    private static AppState instance;
    private ObservableList<User> userList;
    private ObservableList<Book> bookList;
    private ObservableList<Loan> loanList;
    private ObservableList<Admin> adminList;
    private final ObjectProperty<User> currentUser;
    private final ObjectProperty<Admin> currentAdmin;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the observable lists and properties.
     */
    private AppState() {
        this.userList = FXCollections.observableArrayList();
        this.bookList = FXCollections.observableArrayList();
        this.loanList = FXCollections.observableArrayList();
        this.adminList = FXCollections.observableArrayList();
        this.currentUser = new SimpleObjectProperty<>(null);
        this.currentAdmin = new SimpleObjectProperty<>(null);
    }

    /**
     * Static method to get the single instance of AppState.
     * If the instance does not exist, it creates a new one.
     *
     * @return the single instance of AppState
     */
    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    /**
     * Getter for the user list.
     *
     * @return the observable list of users
     */
    public ObservableList<User> getItemList() {
        return userList;
    }

    /**
     * Getter for the admin list.
     *
     * @return the observable list of admins
     */
    public ObservableList<Admin> getAdminList() {
        return adminList;
    }

    /**
     * Getter for the book list.
     *
     * @return the observable list of books
     */
    public ObservableList<Book> getBookList() {
        return bookList;
    }

    /**
     * Getter for the loan list.
     *
     * @return the observable list of loans
     */
    public ObservableList<Loan> getLoanList() {
        return loanList;
    }

    /**
     * Adds a user to the user list.
     *
     * @param item the user to be added
     */
    public void addItemToList(User item) {
        userList.add(item);
    }

    /**
     * Adds an admin to the admin list.
     *
     * @param item the admin to be added
     */

    public void addItemToList(Admin item) {
        adminList.add(item);
    }

    /**
     * Adds a book to the book list.
     *
     * @param item the book to be added
     */
    public void addItemToList(Book item) {
        bookList.add(item);
    }

    /**
     * Adds a loan to the loan list.
     *
     * @param item the loan to be added
     */
    public void addItemToList(Loan item) {
        loanList.add(item);
    }

    /**
     * Removes a user from the user list.
     *
     * @param item the user to be removed
     */
    public void removeItemFromList(User item) {
        userList.remove(item);
    }

    /**
     * Removes an admin from the admin list.
     *
     * @param item the admin to be removed
     */
    public void removeItemFromList(Admin item) {
        adminList.remove(item);
    }

    /**
     * Removes a book from the book list.
     *
     * @param item the book to be removed
     */
    public void removeItemFromList(Book item) {
        bookList.remove(item);
    }

    /**
     * Removes a loan from the loan list.
     *
     * @param item the loan to be removed
     */
    public void removeItemFromList(Loan item) {
        loanList.remove(item);
    }

    /**
     * Getter for the current user's property.
     *
     * @return the property of the current user
     */
    public ObjectProperty<User> currentUserProperty() {
        return currentUser;
    }

    /**
     * Getter for the current user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser.get();
    }

    /**
     * Setter for the current user.
     *
     * @param user the user to be set as the current user
     */
    public void setCurrentUser(User user) {
        currentUser.set(user);
    }

    /**
     * Getter for the current admin's property.
     *
     * @return the property of the current admin
     */
    public ObjectProperty<Admin> currentAdminProperty() {
        return currentAdmin;
    }

    /**
     * Getter for the current admin.
     *
     * @return the current admin
     */
    public Admin getCurrentAdmin() {
        return currentAdmin.get();
    }

    /**
     * Setter for the current admin.
     *
     * @param admin the admin to be set as the current admin
     */
    public void setCurrentAdmin(Admin admin) {
        currentAdmin.set(admin);
    }

    
    /** 
     * @throws IOException
     */
    public void saveChanges() throws IOException {
        
    }
}

