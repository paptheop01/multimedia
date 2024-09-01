package com.library;

import java.io.IOException;

public class SessionLoginCheckService {
    private SessionManager sessionManager;
    private UserManager userManager;
    private AdminManager adminManager;
    private NavigationManager navigationManager;
    private ShowAlertService showAlertService;

    public SessionLoginCheckService(SessionManager sessionManager, UserManager userManager, AdminManager adminManager, NavigationManager navigationManager, ShowAlertService showAlertService) {
        this.sessionManager = sessionManager;
        this.userManager = userManager;
        this.adminManager = adminManager;
        this.navigationManager=navigationManager;
        this.showAlertService = showAlertService;
    }


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


