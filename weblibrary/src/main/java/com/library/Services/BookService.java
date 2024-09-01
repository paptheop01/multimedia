package com.library.Services;
import com.library.Managers.*;
import com.library.Models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This class provides services for managing books in the library system.
 * It includes methods for adding, removing, updating, searching, and rating books.
 */
public class BookService {
    private BookManager bookmanager;
    /**
     * Constructs a new instance of the BookService class.
     *
     * @param bookmanager The BookManager  instance to be associated with this BookService.
     * The Book Manager contrains the list of the books of the library.
     */
    public BookService(BookManager bookmanager){
        this.bookmanager = bookmanager;
    }

    
    /**
     * Returns the BookManager instance associated with this BookService.
     *
     * @return BookManager
     */
    public BookManager getBookManager(){
        return bookmanager;
    }
    /**
     * Creates and adds a new book to the library system.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param pubHouse The publishing house of the book.
     * @param isbn The ISBN of the book.
     * @param pubDate The publication date of the book.
     * @param category The category of the book.
     */
    public void addBook(String title, String author, String pubHouse, String isbn, String pubDate, String category){
        Book book = new Book(title, author, pubHouse, isbn,pubDate, category);
        bookmanager.addBook(book);
        
    }
    /**
     * Removes a book from the library system.
     *
     * @param book The book to be removed.
     */
    public void removeBook(Book book){
        bookmanager.removeBook(book);
    }
    /**
     * Updates the number of copies of a book in the library system.
     *
     * @param book The book whose copies are to be updated.
     * @param count The number of copies to be added or subtracted.
     */
    public void updateCopies(Book book,int count){
        book.setCopyNumber(book.getCopyNumber() + count);
        

    }
/**
 * Retrieves a book from the library system based on its unique identifier (UUID).
 *
 * @param bookId The unique identifier of the book to retrieve.
 * @return The book with the matching UUID, or null if no book is found.
 */
    public Book getBookByBookId(UUID bookId){
        return bookmanager.getBookList().stream().filter(b -> b.getUuid().equals(bookId)).findFirst().orElse(null);
    }

/**
 * Updates the details of a book in the library system.
 *
 * @param book The book to be updated.
 * @param title The new title of the book.
 * @param author The new author of the book.
 * @param pubHouse The new publishing house of the book.
 * @param isbn The new ISBN of the book.
 * @param pubDate The new publication date of the book.
 * @param category The new category of the book.
 */
public void updateBook(Book book, String title, String author, String pubHouse, String isbn, String pubDate, String category) {

    book.setTitle(title);
    book.setAuthor(author);
    book.setPublicationHouse(pubHouse);
    book.setIsbn(isbn);
    book.setPublicationDate(pubDate);
    book.setCategory(category);
}
     /**
     * Adds a new user comment to a book in the library system.
     *
     * @param book The book to which the comment is to be added.
     * @param uuid The unique identifier of the user making the comment.
     * @param commentText The text of the user comment.
     */
    public void addUserComment(Book book, UUID uuid, String commentText) {
        book.addUserComment(uuid, commentText);
    }
    /**
     * Searches for books in the library system based on any combination of title, author, and publication date.
     * If the field is empty it ignores it.
     *
     * @param title The title of the book to search for.
     * @param author The author of the book to search for.
     * @param date The publication date of the book to search for.
     * @return An ObservableList of books that match the search criteria.
     */
    public ObservableList<Book> searchBook(String title, String author, String date){
        ObservableList<Book> filteredBooks = bookmanager.getBookList();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
            
        if(author != ""){
            filteredBooks=filteredBooks.filtered( b -> b.getAuthor().equals(author));
        }
        if(title!= ""){
            filteredBooks=filteredBooks.filtered( b -> b.getTitle().equals(title));
        }
        if(date!= "" && date.matches("\\d*")){
            filteredBooks=filteredBooks.filtered( b -> LocalDate.parse(b.getPublicationDate(), formatter).getYear()==Integer.parseInt(date));
        }
        return filteredBooks;


    }
     /**
     * Retrieves the top-rated books from the library system.
     *
     * @param number The number of top-rated books to retrieve.
     * @return An ObservableList of the top-rated books.
     */
    public ObservableList<Book> getTopBooks(Integer number){
        ObservableList<Book> itemList = bookmanager.getBookList();
        return FXCollections.observableArrayList(itemList.stream().sorted(Comparator.comparing(Book::getrating).reversed()).limit(number).collect(Collectors.toList()));
        
        
    }
    /**
     * Updates the average rating of a book in the library system after a user's rating.
     * Updates the number of the users that have rated the book.
     *
     * @param book The book to be rated.
     * @param rating The rating to be assigned to the book.
     */
    public void rate(Book book, double rating){
        
        double newrating= (book.getrating()*book.getNumberOfUsers()+rating)/(book.getNumberOfUsers()+1);
        book.setNumberOfUsers(book.getNumberOfUsers()+1);
        book.setrating(newrating);
    }
    /**
     * Searches for books in the library that belong to a category.
     *
     * @param category The category of the books to search for.
     * @return An ObservableList of books that match the search criteria.
     */
    public ObservableList<Book> searchBookByCategory(String category){
        ObservableList<Book> filteredBooks = bookmanager.getBookList();
        filteredBooks=filteredBooks.filtered( b -> b.getCategory().equals(category));
        return filteredBooks;
    }
    /**
     * Retrieves the list of unique categories of books in the library system.
     *
     * @return An ObservableList of unique categories of books.
     */
    public ObservableList<String> updateCategories(){
        ObservableList<String> categories = FXCollections.observableArrayList();
        for (Book book : bookmanager.getBookList()) {
            if (!categories.contains(book.getCategory())) {
                categories.add(book.getCategory());
            }
        }
        return categories;
    }
    /**
     * Replaces the category of books in the library system with a new category.
     *
     * @param category The category to be replaced.
     * @param newCategory The new category to replace the old category.
     */
    public void replaceCategory(String category, String newCategory){
        bookmanager.getBookList().stream().filter(b -> b.getCategory().equals(category)).forEach(b -> b.setCategory(newCategory));
    }


   

}
