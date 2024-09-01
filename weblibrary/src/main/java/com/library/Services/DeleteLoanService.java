package com.library.Services;
import com.library.Managers.*;
import com.library.Models.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import javafx.collections.ObservableList;
/**
 * A class that provides different services for deleting loans and books from the library system based on the use cases.
 * Interacts with the LoanManager and BookService to perform the deletion operations.
 */
public class DeleteLoanService {
    private LoanManager loanManager;
    private BookService bookService;
    /**
     * Constructs a new DeleteLoanService instance.
     *
     * @param loanManager The LoanManager to interact with.
     * @param bookService The BookService to interact with.
     */
    public DeleteLoanService(LoanManager loanManager, BookService bookService) {
        this.loanManager = loanManager;
        this.bookService = bookService;
    }

    
/**
 * Removes a specific loan from the loan manager and updates the number of copies of the corresponding book in the book service.
 *
 * @param loan The loan to be deleted.
 */
    public void deleteLoan(Loan loan){
        loanManager.removeLoan(loan);
        bookService.updateCopies(bookService.getBookByBookId(loan.getBookUid()), 1);
    }

/**
 * Removes all loans associated with a specific book from the loan manager.
 * Used right before the deletion of a book.
 *
 * @param bookId The unique identifier of the book.
 */
    public void deleteLoansByBookId(UUID bookId){
        List<Loan> loansToDelete = new ArrayList<>();
        for (Loan loan : loanManager.getLoanList()) {
            if (loan.getBookUid().equals(bookId)) {
                loansToDelete.add(loan);
            }
        }
        
    // Remove loans from the list
    for (Loan loan : loansToDelete) {
        loanManager.removeLoan(loan);
    }
    }

/**
 * Provides the complete procedure for a deletion of a book.
 * Removes a specific book from the library using the implemented book service and all associated loans from the loan manager.
 *
 * @param book The book to be deleted.
 */
    public void deleteBookAndLoan(Book book){
        
        deleteLoansByBookId(book.getUuid());
        bookService.removeBook(book);
    }
/**
 * Removes from the library all books that belong to a specific book category and their associated loans  .
 *
 * @param category The category of the books to delete.
 */
    public void deleteBooksandLoansByCategory(String category){
        ObservableList<Book> booksToDelete = bookService.searchBookByCategory(category);
        
        
        // Debugging: Print the number of books to delete
        System.out.println("Number of books to delete: " + booksToDelete.size());

        // Use a List to avoid ConcurrentModificationException
        List<Book> booksToRemove = new ArrayList<>(booksToDelete);

        // Iterate through the books to delete and remove from the main list
        for (Book book : booksToRemove) {
            
                
                // Optionally, you might want to remove loans or other related data
                deleteBookAndLoan(book);
        }
        
        
    }



}
