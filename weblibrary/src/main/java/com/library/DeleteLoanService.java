package com.library;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;

public class DeleteLoanService {
    private LoanManager loanManager;
    private BookService bookService;
    
    public DeleteLoanService(LoanManager loanManager, BookService bookService) {
        this.loanManager = loanManager;
        this.bookService = bookService;
    }

    public void deleteLoan(Loan loan){
        loanManager.removeLoan(loan);
        bookService.updateCopies(bookService.getBookByBookId(loan.getBookUid()), 1);
    }

    
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

    
    public void deleteBookAndLoan(Book book){
        
        deleteLoansByBookId(book.getUuid());
        bookService.removeBook(book);
    }

    public void deleteBooksandLoansByCategory(String category){
        ObservableList<Book> booksToDelete = bookService.searchBookByCategory(category);
     
       App.getAppState().getBookManager().getBookList().removeIf(book -> book.getCategory().equals(category));
        
        
    }



}
