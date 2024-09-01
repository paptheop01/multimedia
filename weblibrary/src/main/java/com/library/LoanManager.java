package com.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanManager {
    private ObservableList<Loan> loanList = FXCollections.observableArrayList();

    public ObservableList<Loan> getLoanList() {
        return loanList;
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public void removeLoan(Loan loan) {
        loanList.remove(loan);
    }
    

    // Additional methods to manage loans can be added here
}