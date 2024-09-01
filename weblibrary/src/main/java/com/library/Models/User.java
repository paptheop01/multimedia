package com.library.Models;
import java.io.*;
import java.util.UUID;

/**
 * Represents a user  in the library system.
 * Implements the Serializable interface to allow object serialization.
 */
public class User implements Serializable{
    private UUID id;
    private String surname;
    private String firstname;
    private String email;
    private String password;
    private String username;
    private int  ssn;
   

/**
 * Constructs a new User object with the provided details.
 *
 * @param surname The surname of the user.
 * @param firstname The firstname of the user.
 * @param email The email of the user.
 * @param password The password of the user.
 * @param username The username of the user.
 * @param ssn The social security number of the user.
 */
public User( String surname, String firstname, String email, String password, String username, int ssn ) {
    this.id = UUID.randomUUID();
    this.surname = surname;
    this.firstname = firstname;
    this.email = email;
    this.password = password;
    this.username = username;
    this.ssn = ssn;
}
    
    
/**
 * Retrieves the unique identifier of the user.
 *
 * @return A UUID representing the unique identifier of the user.
 */
public UUID getId() {
    return id;
}
    
    
/**
 * Returns the full name of the user by concatenating the firstname and surname.
 *
 * @return A string representing the full name of the user.
 */
public String getFullName() {
    return firstname + " " + surname;
}
    
    
/**
 * Retrieves the email address of the user.
 *
 * @return A string representing the email address of the user.
 */
public String getEmail() {
    return email;
}
    
    
/**
 * Retrieves the password of the user.
 *
 * @return A string representing the password of the user.
 * The password is returned in plain text format.
 */
public String getPassword() {
    return password;
}
    
    
/**
 * Retrieves the username of the user.
 *
 * @return A string representing the username of the user.
 * This username is used to uniquely identify the user within the library system.
 */
public String getUsername() {
    return username;
}

    
/**
 * Retrieves the social security number (SSN) of the user.
 *
 * @return An integer representing the social security number (SSN) of the user.
 * It is used to verify the identity of the user and ensure account security.
 */
public int getSsn() {
    return ssn;
}
    
    
/**
 * Sets the unique identifier of the user.
 *
 * @param id A UUID representing the unique identifier of the user.
 * This identifier can be used to uniquely identify the user within the library system.
 */
public void setId(UUID id) {
    this.id = id;
}

    
/**
 * Sets the surname of the user.
 *
 * @param name A string representing the surname of the user.
 *
 */
public void setsurname(String name) {
    this.surname = name;
}
/**
 * Sets the firstname of the user.
 *
 * @param name A string representing the firstname of the user.
 *
 */
    public void setfirstname(String name) {
        this.firstname = name;
    }

/**
 * Sets the email of the user.
 *
 * @param email A string representing the email of the user.
 * This can also be used to uniquely identify the user.
 *
 */
    public void setemail(String email) {
        this.email = email;
    }

/**
 * Sets the password of the user.
 *
 * @param password A string representing the password of the user.
 *
 *
 */ 
    public void setpassword(String password) {
        this.password = password;
    }
/**
 * Sets the username of the user.
 *
 * @param username A string representing the username of the user.
 * The username can also uniquely identify a user.
 *
 *
 */ 
    public void setusername(String username) {
        this.username = username;
    }
/**
 * Sets the username of the user.
 *
 * @param ssn An integer representing the ssn of the user.
 * The ssn can also uniquely identify a user.
 *
 *
 */     
    public void setssn(int ssn) {
        this.ssn = ssn;
    }
    
    
}
