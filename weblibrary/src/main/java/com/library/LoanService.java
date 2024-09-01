package com.library;

import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanService {
    private LoanManager loanManager;
    private UserService userService;

    public LoanService(LoanManager loanManager, UserService userService){
        this.loanManager = loanManager;
        this.userService = userService;
    }
    
    public void addLoan(UUID userUuid, UUID bUuid, String Date){
        Loan loan = new Loan(userUuid, bUuid, Date);
        loanManager.addLoan(loan);
    }
    
    public void deleteLoan(Loan loan){
        loanManager.removeLoan(loan);
    }

    public ObservableList<Loan> getLoanList() {
        return loanManager.getLoanList();
    }

    public ObservableList<Loan> getLoansByUserId(UUID userId){
        return loanManager.getLoanList().stream().filter(loan -> loan.getUserUid().equals(userId)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Loan> getLoansByUsername(String username) {
        return getLoansByUserId(userService.searchUser(username).getId());
    }

    

    

}
