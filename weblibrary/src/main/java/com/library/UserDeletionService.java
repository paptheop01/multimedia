package com.library;

public class UserDeletionService {
    private UserService userService;
    private LoanService loanService;
    private BookService bookService;

    public UserDeletionService(UserService userService, LoanService loanService, BookService bookService) {
        this.userService = userService;
        this.loanService = loanService;
        this.bookService = bookService;
    }

    public void deleteUser(User user) {
        // Delete all loans of the user

        for (Loan loan : loanService.getLoansByUserId(user.getId())) {
            loanService.deleteLoan(loan);
            bookService.updateCopies(bookService.getBookByBookId(loan.getBookUid()),1);
        }
        userService.deleteUser(user);
    }

}
