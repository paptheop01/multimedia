package com.library;
import java.io.*;
import java.util.UUID;


public class User implements Serializable{
    private UUID id;
    private String surname;
    private String firstname;
    private String email;
    private String password;
    private String username;
    private int  ssn;
   

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
     * @return UUID
     */
    public UUID getId() {
        return id;
    }
    
    
    /** 
     * @return String
     */
    public String getFullName() {
        return firstname+" "+surname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getUsername() {
        return username;
    }

    public int getSsn() {
        return ssn;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public void setsurname(String name) {
        this.surname = name;
    }
    
    public void setfirstname(String name) {
        this.firstname = name;
    }
    
    public void setemail(String email) {
        this.email = email;
    }
    
    public void setpassword(String password) {
        this.password = password;
    }
    
    public void setusername(String username) {
        this.username = username;
    }
    
    public void setssn(int ssn) {
        this.ssn = ssn;
    }
    
    
}
