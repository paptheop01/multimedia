package com.library;



/**
 * This class represents the application's state, managing user, admin, book, and loan data.
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
    private NavigationManager navigationManager;
    private DeleteLoanService deleteLoanService;
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
        this.navigationManager = new NavigationManager("a");
        this.sessionLoginCheckService = new SessionLoginCheckService(sessionManager,userManager,adminManager,navigationManager,showAlertService);
        this.deleteLoanService = new DeleteLoanService(loanManager,bookService);
    }

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public AdminManager getAdminManager() {
        return adminManager;
    }

    public BookManager getBookManager() {
        return bookManager;
    }

    public LoanManager getLoanManager() {
        return loanManager;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public UserService getUserService() {
        return userService;
    } 
    
    public LoanService getLoanService() {
        return loanService;
    }
    
    public BookService getBookService() {
        return bookService;
    }
    
    public UserDeletionService getUserDeletionService() {
        return userDeletitionService;
    }
    
    public SessionLoginCheckService getSessionLoginCheckService() {
        return sessionLoginCheckService;
    }
    
    public ShowAlertService getShowAlertService() {
        return showAlertService;
    }
    
    public NavigationManager getNavigationManager() {
        return navigationManager;
    }
    
    public DeleteLoanService getDeleteLoanService() {
        return deleteLoanService;
    }
}

