package com.library;
import com.library.Services.*;
import com.library.Managers.*;




/**
 * This class represents the application's state.
 * It contains managers that are managing user, admin, book, and loan data.
 * It also contains services that help perform opetations to these data structures.
 * It uses the Singleton design pattern to ensure only one instance of this class exists at a time.
 */
public class AppState {
    private static AppState instance;

    private UserManager userManager;
    private AdminManager adminManager;
    private BookManager bookManager;
    private LoanManager loanManager;
    private SessionManager sessionManager;
    private UserService userService;
    private LoanService loanService;
    private BookService bookService;
    private UserDeletionService userDeletitionService;
    private SessionLoginCheckService sessionLoginCheckService;
    private ShowAlertService showAlertService;
   
    private DeleteLoanService deleteLoanService;
    private SerializationService serializationService;
    private AppState() {
        this.userManager = new UserManager();
        this.adminManager = new AdminManager();
        this.bookManager = new BookManager();
        this.loanManager = new LoanManager();
        this.sessionManager = new SessionManager();
        this.userService = new UserService(userManager);
        this.loanService = new LoanService(loanManager,userService);
        this.bookService = new BookService(bookManager);
        this.userDeletitionService = new UserDeletionService(userService,loanService,bookService);
        this.showAlertService = new ShowAlertService();
        
        this.sessionLoginCheckService = new SessionLoginCheckService(sessionManager,userManager,adminManager,showAlertService);
        this.deleteLoanService = new DeleteLoanService(loanManager,bookService);
        this.serializationService = new SerializationService();
    }

    
    /** 
     * @return AppState
     */
    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    
/**
 * This method returns the instance of UserManager.
 * UserManager is responsible for managing user data, such as adding, updating, and deleting users.
 *
 * @return UserManager - The instance of UserManager used to manage user data.
 */
public UserManager getUserManager() {
    return userManager;
}

    
/**
 * This method returns the instance of AdminManager.
 * AdminManager is responsible for managing admin data, such as adding, updating, and deleting admins.
 *
 * @return AdminManager - The instance of AdminManager used to manage admin data.
 */
public AdminManager getAdminManager() {
    return adminManager;
}

    
/**
 * This method returns the instance of BookManager.
 * BookManager is responsible for managing book data, such as adding, updating, and deleting books.
 *
 * @return BookManager - The instance of BookManager used to manage book data.
 * This method does not take any parameters and returns the BookManager instance.
 * The returned BookManager instance is used to perform operations on book data.
 */
public BookManager getBookManager() {
    return bookManager;
}

    
/**
 * This method returns the instance of LoanManager.
 * LoanManager is responsible for managing loan data, such as adding, updating, and deleting loans.
 *
 * @return LoanManager - The instance of LoanManager used to manage loan data.
 * This method does not take any parameters and returns the LoanManager instance.
 * The returned LoanManager instance is used to perform operations on loan data.
 */
public LoanManager getLoanManager() {
    return loanManager;
}

    
/**
 * This method returns the instance of SessionManager.
 * SessionManager is responsible for keeping the user or the admin logged in in the current session.
 *
 * @return SessionManager - 
 * This method does not take any parameters and returns the SessionManager instance.
 * The returned SessionManager is responsible for keeping the user or the admin logged in in the current session.
 */
public SessionManager getSessionManager() {
    return sessionManager;
}

     
/**
 * This method returns the instance of UserService.
 * UserService is responsible for performing operations related to user data, such as user registration,deletion and user information retrieval.
 *
 * @return UserService - The instance of UserService used to perform operations on user data.
 * This method does not take any parameters and returns the UserService instance.
 * The returned UserService instance is used to interact with the user data and perform operations like user registration, deletion, and user information retrieval.
 */
public UserService getUserService() {
    return userService;
}
    
    
/**
 * This method returns the instance of LoanService.
 * LoanService is responsible for performing operations related to loan data, such as adding, updating, and deleting loans.
 * It also interacts with other managers like UserManager to perform operations like checking user eligibility for loan.
 *
 * @return LoanService - The instance of LoanService used to perform operations on loan data.
 * This method does not take any parameters and returns the LoanService instance.
 * The returned LoanService instance is used to interact with the loan data and perform operations like adding, updating, and deleting loans.
 * It also provides methods to check user eligibility for loan and perform other related operations.
 */
public LoanService getLoanService() {
    return loanService;
}
    
    
/**
 * This method returns the instance of BookService.
 * BookService is responsible for performing operations related to book data, such as adding, updating, and deleting books.
 * It interacts with the BookManager to perform these operations.
 *
 * @return BookService - The instance of BookService used to perform operations on book data.
 * This method does not take any parameters and returns the BookService instance.
 * The returned BookService instance is used to interact with the book data and perform operations like adding, updating, and deleting books.
 */
public BookService getBookService() {
    return bookService;
}
    
    
/**
 * This method returns the instance of UserDeletionService.
 * UserDeletionService is responsible for performing operations related to user deletion,
 * such as deleting a user and handling related operations like deleting loans and books associated with the user.
 * It interacts with other services like UserService, LoanService, and BookService to perform these operations.
 *
 * @return UserDeletionService - The instance of UserDeletionService used to perform operations on user deletion.
 * This method does not take any parameters and returns the UserDeletionService instance.
 * The returned UserDeletionService instance is used to interact with the user data and perform operations like deleting a user,
 * along with deleting related loans and books associated with the user.
 */
public UserDeletionService getUserDeletionService() {
    return userDeletitionService;
}
    
    
/**
 * This method returns the instance of SessionLoginCheckService.
 * SessionLoginCheckService is responsible for checking the login status of a user or an admin in the current session.
 * It interacts with other managers like SessionManager, UserManager, and AdminManager to perform these operations.
 *
 * @return SessionLoginCheckService - The instance of SessionLoginCheckService used to check the login status.
 * This method does not take any parameters and returns the SessionLoginCheckService instance.
 * The returned SessionLoginCheckService instance is used to interact with the session data and perform operations like checking the login status of a user or an admin.
 */
public SessionLoginCheckService getSessionLoginCheckService() {
    return sessionLoginCheckService;
}
    
    
/**
 * This method returns the instance of ShowAlertService.
 * ShowAlertService is responsible for displaying alerts to the user interface.
 * It can be used to show various types of alerts, such as success, error, warning, and informational messages.
 *
 * @return ShowAlertService - The instance of ShowAlertService used to display alerts.
 * This method does not take any parameters and returns the ShowAlertService instance.
 * The returned ShowAlertService instance is used to interact with the user interface and display alerts.
 */
public ShowAlertService getShowAlertService() {
    return showAlertService;
}
    
    
    
    
/**
 * This method returns the instance of DeleteLoanService.
 * DeleteLoanService is responsible for performing operations related to deleting loans amd books,
 * such as deleting a loan and handling related operations like updating book availability or vice versa.
 * It interacts with other services like LoanManager and BookService to perform these operations.
 *
 * @return DeleteLoanService - The instance of DeleteLoanService used to perform operations on loan deletion.
 * This method does not take any parameters and returns the DeleteLoanService instance.
 * The returned DeleteLoanService instance is used to interact with the loan data and perform operations like deleting a loan,
 * along with updating the book availability and vice versa.
 */
public DeleteLoanService getDeleteLoanService() {
    return deleteLoanService;
}
    
/**
 * This method returns the instance of SerializationService.
 * SerializationService is responsible for handling the serialization and deserialization of data,
 * such as saving and loading data from a file .
 *
 * @return SerializationService - The instance of SerializationService used for data serialization and deserialization.
 * This method does not take any parameters and returns the SerializationService instance.
 * The returned SerializationService instance is used to interact with the file system or database,
 * and perform operations like saving and loading data.
 */
public SerializationService getSerializationService(){
    return serializationService;
}
}

