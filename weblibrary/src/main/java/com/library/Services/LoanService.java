package com.library.Services;
import com.library.Managers.*;
import com.library.Models.*;



import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * A service for managing loans in the library system.
 * Interacts with the LoanManager and UserService to perform loan-related operations based on some use cases.
 */
public class LoanService {
    private LoanManager loanManager;
    private UserService userService;
    /**
     * Constructs a new LoanService instance.
     *
     * @param loanManager The LoanManager to interact with. Contains the loan list of the library.
     * @param userService The UserService to interact with. Provides access to services regarding the user list of the library.
     */
    public LoanService(LoanManager loanManager, UserService userService){
        this.loanManager = loanManager;
        this.userService = userService;
    }
    
    
    /**
 * Creates and adds a new loan to the loan manager.
 *
 * @param userUuid The unique identifier of the user.
 * @param bUuid The unique identifier of the book.
 * @param Date The end date of the loan.
 */
    public void addLoan(UUID userUuid, UUID bUuid, String Date){
        Loan loan = new Loan(userUuid, bUuid, Date);
        loanManager.addLoan(loan);
    }
/**
 * Removes a specific loan from the loan manager.
 *
 * @param loan The loan to be deleted.
 */
    public void deleteLoan(Loan loan){
        loanManager.removeLoan(loan);
    }
/**
 * Returns the list of loans from the loan manager.
 *
 * @return The list of loans.
 */
    public ObservableList<Loan> getLoanList() {
        return loanManager.getLoanList();
    }
    /**
 * Returns a list of loans associated with a specific user.
 *
 * @param userId The unique identifier of the user.
 * @return The list of loans associated with the user.
 */
    public ObservableList<Loan> getLoansByUserId(UUID userId){
        return loanManager.getLoanList().stream().filter(loan -> loan.getUserUid().equals(userId)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    /**
 * Returns a list of loans associated with a specific user based on their username.
 *
 * @param username The username of the user.
 * @return The list of loans associated with the user.
 */
    public ObservableList<Loan> getLoansByUsername(String username) {
        return getLoansByUserId(userService.searchUser(username).getId());
    }

    

    

}
