package com.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookManager {
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    public ObservableList<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void removeBook(Book book) {
        bookList.remove(book);
    }

    // Additional methods to manage books can be added here
}