package com.library;

import java.util.UUID;

public class UserService {
    private UserManager usermanager;
    
    public UserService(UserManager usermanager){
        this.usermanager = usermanager;
    }
    public void addUser(String surname, String firstname ,String email, String password, String username, Integer ssn){
        User newUser = new User(surname,firstname,email,password,username,ssn);
        usermanager.addUser(newUser);  
    }

    public void updateUser(User user, String surname, String firstname ,String email, String password, String username, Integer ssn){
                user.setusername(username);
                user.setpassword(password);
                user.setemail(email);
                user.setssn(ssn);
                user.setsurname(surname);
                user.setfirstname(firstname);

    }
    
    public void deleteUser(User user) {
        usermanager.removeUser(user);
    }

    public boolean usernameExists(String username) {
        return usermanager.getUserList().stream()
            .anyMatch(u -> u.getUsername().equals(username) );
    }
    

    public boolean emailExists(String email) {
        return usermanager.getUserList().stream()
            .anyMatch(u -> u.getEmail().equals(email) );
    }

    public User getUserById(UUID uid) {
        return usermanager.getUserList().stream()
            .filter(u -> u.getId().equals(uid))
            .findFirst()
            .orElse(null);
    }

    public User searchUser(String username){
        return usermanager.getUserList().stream()
           .filter(u -> u.getUsername().equals(username) )
           .findFirst()
           .orElse(null);
    }
   
      

}
