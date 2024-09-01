package com.library.Managers;
import com.library.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class manages the list of books in the library system.
 * It provides methods to add, remove, and retrieve the list of books.
 */
public class BookManager {
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    
    /**
     * Returns the observable list of books.
     *
     * @return ObservableList<Book>
     */
    public ObservableList<Book> getBookList() {
        return bookList;
    }
    /**
     * Adds a  book to the list.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        bookList.add(book);
    }
    /**
     * Removes a book from the list.
     *
     * @param book The book to be removed.
     */
    public void removeBook(Book book) {
        bookList.remove(book);
    }

   
}