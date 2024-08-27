package com.library;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.*;

/**
* Represents a book in a library system.
* Implements the Serializable interface to allow for object serialization.
*/
public class Book implements Serializable{
    private UUID uuid;
    private String title;
    private String author;
    private String publicationHouse;
    private String isbn;
    private String publicationDate;
    private int copyNumber;
    private double rating;
    private int numberOfUsers;
    private String category;
    private List<Comment> userComments;


/**
 * Constructs a new Book object with the given parameters.
 *
 * @param title The title of the book.
 * @param author The author's name of the book.
 * @param publicationHouse The publication house of the book.
 * @param isbn The International Standard Book Number (ISBN) of the book.
 * @param publicationDate The publication date of the book in the format "YYYY-MM-DD".
 * @param category The category of the book.
 */
public Book(String title, String author, String publicationHouse, String isbn, String publicationDate, String category) {
    this.uuid = UUID.randomUUID();
    this.title = title;
    this.author = author;
    this.publicationHouse = publicationHouse;
    this.isbn = isbn;
    this.publicationDate = publicationDate;
    this.copyNumber = 5;
    this.numberOfUsers = 0;
    this.rating = 0 ;
    this.category = category;
    this.userComments = new ArrayList<>();
}

/**
 * Returns the unique identifier of the book.
 *
 * @return The UUID of the book.
 */
public UUID getUuid() {
    return uuid;
}

    
/**
 * Returns the average rating of the book.
 *
 * @return The average rating of the book. The rating is a double value between 0 and 5, inclusive.
 *         If no ratings have been given, the method returns 0.
 */
public double getrating() {
    return rating;
}

/**
 * Returns the category of the book.
 *
 * @return The category of the book. The category is a string that represents the genre or theme of the book.
 */
public String getCategory() {
    return category;
}

    /**
     * @param title The title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
/**
 * Returns the title of the book.
 *
 * @return The title of the book. The title is a string that represents the name of the book.
 */
public String getTitle(){
    return title;
}


    
/**
 * Returns the author's name of the book.
 *
 * @return The author's name. 
 */
public String getAuthor() {
    return author;
}


/**
 * Sets the author of the book.
 *
 * @param author The author's name. 
 */
public void setAuthor(String author) {
    this.author = author;
}

    
/**
 * Returns the publication house of the book.
 *
 * @return The publication house of the book. The publication house is a string.
 */
public String getPublicationHouse() {
    return publicationHouse;
}

/**
 * Sets the publication house of the book.
 *
 * @param publicationHouse The publication house of the book. The publication house is a string.
 *                         
 */
public void setPublicationHouse(String publicationHouse) {
    this.publicationHouse = publicationHouse;
}



/**
 * Returns the International Standard Book Number (ISBN) of the book.
 *
 * @return The ISBN of the book. 
 */
public String getIsbn() {
    return isbn;
}

/**
 * Sets the average rating of the book.
 *
 * @param rating The average rating of the book. The rating is a double value between 0 and 5, inclusive.
 */
public void setrating(double rating) {
    this.rating = rating;
}

/**
 * Sets the International Standard Book Number (ISBN) of the book.
 *
 * @param isbn The ISBN of the book. 
 */
public void setIsbn(String isbn) {
    this.isbn = isbn;
}

/**
 * Returns the publication date of the book.
 *
 * @return The publication date of the book. The publication date is a string in the format "YYYY-MM-DD".
 */
public String getPublicationDate() {
    return publicationDate;
}

/**
 * Sets the publication date of the book.
 *
 * @param publicationDate The publication date of the book. The publication date is a string in the format "YYYY-MM-DD".
 */
public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
}

/**
 * Returns the number of copies available of the book.
 *
 * @return The number of copies available of the book.
 */
public int getCopyNumber() {
    return copyNumber;
}

/**
 * Sets the number of copies available of the book.
 *
 * @param copyNumber The number of copies available of the book.
 */
public void setCopyNumber(int copyNumber) {
    this.copyNumber = copyNumber;
}

/**
 * Returns the number of users who have rated the book.
 *
 * @return The number of users who have rated the book.
 */
public int getNumberOfUsers() {
    return numberOfUsers;
}

/**
 * Sets the number of users who have rated the book.
 *
 * @param numberOfUsers The number of users who have rated the book.
 */
public void setNumberOfUsers(int numberOfUsers) {
    this.numberOfUsers = numberOfUsers;
}

/**
 * Sets the category of the book.
 *
 * @param category The category of the book. The category is a string that represents the genre or theme of the book.
 */
public void setCategory(String category) {
    this.category = category;
}

/**
 * Adds a user comment to the book.
 *
 * @param uuid The unique identifier of the user who made the comment.
 * @param commentText The text of the comment.
 */
public void addUserComment(UUID uuid, String commentText) {
    Comment comment = new Comment(uuid, commentText);
    userComments.add(comment);
}

   



       

/**
 * Returns the list of user comments for the book.
 *
 * @return A list of Comment objects, each containing the unique identifier of the user who made the comment
 *         and the text of the comment.
 */
public List<Comment> getUserComment() {
    return userComments;
}

/**
 * Displays the details of the book, including its title, author, publication house, ISBN, publication date,
 * copy number, number of users, and user comments.
 */
public void displayBookDetails() {
    System.out.println("Book Title: " + title);
    System.out.println("Author: " + author);
    System.out.println("Publication House: " + publicationHouse);
    System.out.println("ISBN: " + isbn);
    System.out.println("Publication Date: " + publicationDate);
    System.out.println("Copy Number: " + copyNumber);
    System.out.println("Number of Users: " + numberOfUsers);
    System.out.println("User Comments:");
    for (Comment comment : userComments) {
        System.out.println("- Username: " + comment.getUserid());
        System.out.println("  Comment: " + comment.getCommentText());
    }
}
    
}