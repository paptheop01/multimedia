package com.library.Managers;
import com.library.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class manages the list of loans in the library system.
 * It provides methods to add, remove, and retrieve the list of loans.
 */
public class LoanManager {
    private ObservableList<Loan> loanList = FXCollections.observableArrayList();

    
    /**
     * Returns the observable list of loans.
     *
     * @return ObservableList<Loan>
     */
    public ObservableList<Loan> getLoanList() {
        return loanList;
    }
    /**
     * Adds a new loan to the list.
     *
     * @param loan The loan to be added.
     */
    public void addLoan(Loan loan) {
        loanList.add(loan);
    }
    /**
     * Removes a loan from the list.
     *
     * @param loan The loan to be removed.
     */
    public void removeLoan(Loan loan) {
        loanList.remove(loan);
    }
    

}