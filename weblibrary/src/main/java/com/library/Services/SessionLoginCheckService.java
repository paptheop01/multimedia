package com.library.Services;
import com.library.Managers.*;
import com.library.Models.*;
import com.library.App;



import java.io.IOException;
/**
 * A service for checking user and admin login credentials and managing the session in a JavaFX application.
 * Interacts with the SessionManager, UserManager, AdminManager, and ShowAlertService.
 */
public class SessionLoginCheckService {
    private SessionManager sessionManager;
    private UserManager userManager;
    private AdminManager adminManager;
    
    private ShowAlertService showAlertService;
    /**
     * Constructs a new SessionLoginCheckService instance.
     *
     * @param sessionManager The SessionManager to interact with.
     * @param userManager The UserManager to interact with.
     * @param adminManager The AdminManager to interact with.
     * @param showAlertService The ShowAlertService to display alerts.
     */
    public SessionLoginCheckService(SessionManager sessionManager, UserManager userManager, AdminManager adminManager, ShowAlertService showAlertService) {
        this.sessionManager = sessionManager;
        this.userManager = userManager;
        this.adminManager = adminManager;
      
        this.showAlertService = showAlertService;
    }


    
    /**
 * Checks the provided username and password against the user list in the user manager.
 * If the credentials are valid, it sets the current user in the session manager and changes the root of the JavaFX application to the user start screen.
 *
 * @param username The username to check.
 * @param password The password to check.
 */
    public void checkandLoginUser(String username, String password){
          for (User user : userManager.getUserList()){
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    
                        sessionManager.setCurrentUser(user);
                        try{
                            App.setRoot("userstart");
                        }
                        catch(IOException e){
                            e.printStackTrace();
                            
                        }
                        return;

                    
                    
                }
                else if(user.getUsername().equals(username) && !user.getPassword().equals(password)){
                    showAlertService.showWarningAlert("Wrong password");
                    return;

                }

            }
            showAlertService.showWarningAlert("User not found");
            return;
        }
        /**
 * Checks the provided username and password against the admin list in the admin manager.
 * If the credentials are valid, it sets the current admin in the session manager and changes the root of the JavaFX application to the admin dashboard.
 *
 * @param username The username to check.
 * @param password The password to check.
 */
        public void checkandLoginAdmin(String username, String password){
        for (User user : adminManager.getAdminList()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
               
                    sessionManager.setCurrentAdmin((Admin) user);
                    try{
                        App.setRoot("primary");
                    }
                    catch(IOException e){
                        e.printStackTrace();
                        
                    }
                    return;

                }
            else if(user.getUsername().equals(username) && !user.getPassword().equals(password)){
                showAlertService.showWarningAlert("Wrong password");
                return;

            }

        }
        showAlertService.showWarningAlert("User not found");
        return;
    }






    }


