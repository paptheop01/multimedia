package com.library;


/**
 * Represents an admin user in the library system.
 * Extends the User class and provides additional functionality for admin users.
 */
public class Admin extends User {

    /**
     * Constructs a new admin user with the given parameters.
     *
     * @param surname   The surname of the admin user.
     * @param firstname  The firstname of the admin user.
     * @param email     The email address of the admin user.
     * @param password  The password for the admin user.
     * @param username  The username for the admin user.
     * @param ssn       The social security number of the admin user.
     */
    public Admin(String surname, String firstname, String email, String password, String username, int ssn) {
        super(surname, firstname, email, password, username, ssn);
    }

}

