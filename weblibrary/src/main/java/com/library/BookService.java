package com.library;

import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookService {
    private BookManager bookmanager;
    
    public BookService(BookManager bookmanager){
        this.bookmanager = bookmanager;
    }

    public void addBook(String title, String author, String pubHouse, String isbn, String pubDate, String category){
        Book book = new Book(title, author, pubHouse, isbn,pubDate, category);
        bookmanager.addBook(book);
        
    }

    public void removeBook(Book book){
        bookmanager.removeBook(book);
    }

    public void updateCopies(Book book,int count){
        book.setCopyNumber(book.getCopyNumber() + count);
        

    }
    public Book getBookByBookId(UUID bookId){
        return bookmanager.getBookList().stream().filter(b -> b.getUuid().equals(bookId)).findFirst().orElse(null);
    }

    
public void updateBook(Book book, String title, String author, String pubHouse, String isbn, String pubDate, String category) {

    book.setTitle(title);
    book.setAuthor(author);
    book.setPublicationHouse(pubHouse);
    book.setIsbn(isbn);
    book.setPublicationDate(pubDate);
    book.setCategory(category);
}

    public void addUserComment(Book book, UUID uuid, String commentText) {
        book.addUserComment(uuid, commentText);
    }

    public ObservableList<Book> searchBook(String title, String author, String date){
        ObservableList<Book> filteredBooks = bookmanager.getBookList();
        
        if(author != ""){
            filteredBooks=filteredBooks.filtered( b -> b.getAuthor().equals(author));
        }
        if(title!= ""){
            filteredBooks=filteredBooks.filtered( b -> b.getTitle().equals(title));
        }
        if(date!= ""){
            filteredBooks=filteredBooks.filtered( b -> b.getPublicationDate().equals(date));
        }
        return filteredBooks;


    }

    public ObservableList<Book> getTopBooks(Integer number){
        ObservableList<Book> itemList = bookmanager.getBookList();
        return FXCollections.observableArrayList(itemList.stream().sorted(Comparator.comparing(Book::getrating).reversed()).limit(number).collect(Collectors.toList()));
        
        
    }

    public void rate(Book book, double rating){
        
        double newrating= (book.getrating()*book.getNumberOfUsers()+rating)/(book.getNumberOfUsers()+1);
        book.setNumberOfUsers(book.getNumberOfUsers()+1);
        book.setrating(newrating);
    }
    
    public ObservableList<Book> searchBookByCategory(String category){
        ObservableList<Book> filteredBooks = bookmanager.getBookList();
        filteredBooks=filteredBooks.filtered( b -> b.getCategory().equals(category));
        return filteredBooks;
    }
    public ObservableList<String> updateCategories(){
        ObservableList<String> categories = FXCollections.observableArrayList();
        for (Book book : bookmanager.getBookList()) {
            if (!categories.contains(book.getCategory())) {
                categories.add(book.getCategory());
            }
        }
        return categories;
    }

    public void replaceCategory(String category, String newCategory){
        bookmanager.getBookList().stream().filter(b -> b.getCategory().equals(category)).forEach(b -> b.setCategory(newCategory));
    }


   

}
