package com.library.Services;
import com.library.Managers.*;
import com.library.Models.*;


import java.util.UUID;
/**
 * A class providing services for managing users in the library system.
 * Interacts with the UserManager to perform user-related operations.
 */
public class UserService {
    private UserManager usermanager;
    /**
     * Constructs a new UserService instance.
     *
     * @param usermanager The UserManager to interact with.
     */
    public UserService(UserManager usermanager){
        this.usermanager = usermanager;
    }
    
    /**
 * Creates and adds a new user to the user manager.
 *
 * @param surname The surname of the user.
 * @param firstname The firstname of the user.
 * @param email The email of the user.
 * @param password The password of the user.
 * @param username The username of the user.
 * @param ssn The social security number of the user.
 */
    public void addUser(String surname, String firstname ,String email, String password, String username, Integer ssn){
        User newUser = new User(surname,firstname,email,password,username,ssn);
        usermanager.addUser(newUser);  
    }
    /**
 * Updates the information of an existing user.
 *
 * @param user The user to be updated.
 * @param surname The updated surname of the user.
 * @param firstname The updated firstname of the user.
 * @param email The updated email of the user.
 * @param password The updated password of the user.
 * @param username The updated username of the user.
 * @param ssn The updated social security number of the user.
 */
    public void updateUser(User user, String surname, String firstname ,String email, String password, String username, Integer ssn){
                user.setusername(username);
                user.setpassword(password);
                user.setemail(email);
                user.setssn(ssn);
                user.setsurname(surname);
                user.setfirstname(firstname);

    }
/**
 * Deletes a specific user from the user manager.
 *
 * @param user The user to be deleted.
 */
    public void deleteUser(User user) {
        usermanager.removeUser(user);
    }
    /**
 * Checks if a username already exists in the user manager.
 *
 * @param username The username to check.
 * @return True if the username exists, false otherwise.
 */
    public boolean usernameExists(String username) {
        return usermanager.getUserList().stream()
            .anyMatch(u -> u.getUsername().equals(username) );
    }
    /**
 * Checks if the ssn already exists in the user manager.
 *
 * @param ssn The ssn to check.
 * @return True if the ssn exists, false otherwise.
 */
    public boolean ssnExists(Integer ssn) {
        return usermanager.getUserList().stream()
            .anyMatch(u -> u.getSsn()==ssn );
    }
    
    /**
 * Checks if an email already exists in the user manager.
 *
 * @param email The email to check.
 * @return True if the email exists, false otherwise.
 */
    public boolean emailExists(String email) {
        return usermanager.getUserList().stream()
            .anyMatch(u -> u.getEmail().equals(email) );
    }
    /**
 * Retrieves a user by their unique identifier. Used for retrieving users based on loan information.
 *
 * @param uid The unique identifier of the user.
 * @return The user with the specified unique identifier, or null if not found.
 */
    public User getUserById(UUID uid) {
        return usermanager.getUserList().stream()
            .filter(u -> u.getId().equals(uid))
            .findFirst()
            .orElse(null);
    }
    /**
 * Retrieves the user based on their username.
 *
 * @param username The username to search for.
 * @return The user with the specified username, or null if not found.
 */
    public User searchUser(String username){
        return usermanager.getUserList().stream()
           .filter(u -> u.getUsername().equals(username) )
           .findFirst()
           .orElse(null);
    }
   
      

}
