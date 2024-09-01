package com.library.Services;

import com.library.Models.*;


/**
 * A service for deleting users and their associated loans from the library system.
 * Interacts with the UserService, LoanService, and BookService to perform the deletion operations.
 */
public class UserDeletionService {
    private UserService userService;
    private LoanService loanService;
    private BookService bookService;
    /**
     * Constructs a new UserDeletionService instance.
     *
     * @param userService The UserService to interact with.
     * @param loanService The LoanService to interact with.
     * @param bookService The BookService to interact with.
     */
    public UserDeletionService(UserService userService, LoanService loanService, BookService bookService) {
        this.userService = userService;
        this.loanService = loanService;
        this.bookService = bookService;
    }

    
/**
 * Deletes a specific user from the user service and all associated loans from the loan service.
 *
 * @param user The user to be deleted.
 */
    public void deleteUser(User user) {
        // Delete all loans of the user

        for (Loan loan : loanService.getLoansByUserId(user.getId())) {
            loanService.deleteLoan(loan);
            bookService.updateCopies(bookService.getBookByBookId(loan.getBookUid()),1);
        }
        userService.deleteUser(user);
    }

}
