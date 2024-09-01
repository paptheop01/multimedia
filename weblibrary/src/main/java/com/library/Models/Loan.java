package com.library.Models;


import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a loan record in a library system.
 * Implements the Serializable interface to allow object serialization.
 */
public class Loan implements Serializable {
    private UUID useruid;
    private UUID bookuid;
    private String loanEndDate;

    /**
     * Constructs a Loan object with the provided user UUID, book UUID, and loan end date.
     *
     * @param useruid The UUID of the user who borrowed the book.
     * @param bookuid The UUID of the book being loaned.
     * @param loanEndDate The date when the loan is expected to end.
     */
    public Loan(UUID useruid, UUID bookuid, String loanEndDate) {
        this.useruid = useruid;
        this.bookuid = bookuid;
        this.loanEndDate = loanEndDate;
    }

    /**
     * Returns the UUID of the user who borrowed the book.
     *
     * @return The UUID of the user.
     */
    public UUID getUserUid() {
        return useruid;
    }

    /**
     * Returns the UUID of the book being loaned.
     *
     * @return The UUID of the book.
     */
    public UUID getBookUid() {
        return bookuid;
    }

    /**
     * Returns the date when the loan is expected to end.
     *
     * @return The loan end date as a string.
     */
    public String getLoanEndDate() {
        return loanEndDate;
    }

    /**
     * Sets the UUID of the user who borrowed the book.
     *
     * @param useruid The UUID of the user.
     */
    public void setUserUid(UUID useruid) {
        this.useruid = useruid;
    }
}

