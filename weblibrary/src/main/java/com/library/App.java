package com.library;
import com.library.Services.*;
import com.library.Managers.*;
import com.library.Models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static AppState appState;
    private SerializationService serializationService;

/**
 * This method is the entry point for the JavaFX application. It initializes the application's main stage,
 * deserializes from the files  all the necessery managers that contain the state of the library, and displays the main window.
 *
 * @param stage The primary stage of the JavaFX application.
 * @throws IOException If an error occurs while loading the FXML file for the main window.
 */
@Override
public void start(Stage stage) throws IOException {
    appState = AppState.getInstance();
    UserManager userManager = appState.getUserManager();
    AdminManager adminManager = appState.getAdminManager();
    BookManager bookManager = appState.getBookManager();
    LoanManager loanManager = appState.getLoanManager();
    this.serializationService = appState.getSerializationService();
    List<Book> books = new ArrayList<Book>();
   
    try{
    books = serializationService.deserializeBooks("books.ser");
    }catch(Exception e){
        
        e.printStackTrace();
    }
    bookManager.getBookList().addAll(books);

    List<Loan> loans=new ArrayList<Loan>();
    try{
    loans = serializationService.deserializeLoans("loans.ser");
    }catch(Exception e){
        
        e.printStackTrace();
    }
    loanManager.getLoanList().addAll(loans);

    List<User> users=new ArrayList<User>();
    try{
    users = serializationService.deserializeUsers("users.ser");
    }catch(Exception e){
        
        e.printStackTrace();
    }
    userManager.getUserList().addAll(users);
    List<Admin> admins= new ArrayList<Admin>();
    List<User> adminshelp=new ArrayList<User>();
    try{
    adminshelp = serializationService.deserializeUsers("admins.ser");
    }catch(Exception e){
        
        e.printStackTrace();
    }
    for (User user : adminshelp) {
        if (user instanceof Admin) {
            admins.add((Admin) user);
        }
    }
    
    adminManager.getAdminList().addAll(admins);


    


    
    // Admin admin1 = new Admin("John", "Doe", "john.doe@medialab.com", "medialab_2024", "medialab", 123456789);
    // adminManager.addAdmin(admin1);
    // User user1 = new User("Smith", "John", "john.smith@example.com", "password1", "johnny123", 123456789);
    // User user2 = new User("Doe", "Jane", "jane.doe@example.com", "password2", "janeDoe", 987654321);
    // User user3 = new User("Brown", "Michael", "michael.brown@example.com", "password3", "mikeB", 112233445);
    // User user4 = new User("Taylor", "Emily", "emily.taylor@example.com", "password4", "emilyT", 998877665);
    // User user5 = new User("Johnson", "Chris", "chris.johnson@example.com", "password5", "chrisJ", 556677889);
    // userManager.addUser(admin1);
    // userManager.addUser(user1);
    // userManager.addUser(user2);
    // userManager.addUser(user3);
    // userManager.addUser(user4);
    // userManager.addUser(user5);

    // // Create 10 books
    // Book book1 = new Book("Java Programming", "John Doe", "TechBooks Inc.", "978-0321349606", "2022-01-01", "Programming");
    // Book book2 = new Book("Python Basics", "Jane Smith", "CodePublishers", "978-0980285841", "2021-12-15", "Programming");
    // Book book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Vintage", "978-0743273565", "2004-09-30", "Fiction");
    // Book book4 = new Book("1984", "George Orwell", "George Orwell & Company", "978-0451524935", "1949-06-09", "Fiction");
    // Book book5 = new Book("To Kill a Mockingbird", "Harper Lee", "Harper Perennial Modern Classics", "978-0061120084", "2006-10-11", "Fiction");
    // Book book6 = new Book("C++ Programming", "Bjarne Stroustrup", "Addison-Wesley", "978-0321563842", "2013-05-19", "Programming");
    // Book book7 = new Book("Clean Code", "Robert C. Martin", "Prentice Hall", "978-0132350884", "2008-08-01", "Programming");
    // Book book8 = new Book("The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company", "978-0316769488", "1951-07-16", "Fiction");
    // Book book9 = new Book("The Hobbit", "J.R.R. Tolkien", "HarperCollins", "978-0261103344", "1937-09-21", "Fiction");
    // Book book10 = new Book("Effective Java", "Joshua Bloch", "Addison-Wesley", "978-0134685991", "2017-12-27", "Programming");

    // // Add comments and ratings to some books
    // book1.addUserComment(user1.getId(), "Great for learning Java.");
    // book1.addUserComment(user2.getId(), "Needs more practical examples.");
    // book1.setrating(4.5);
    // book1.setNumberOfUsers(book1.getNumberOfUsers() + 1);
    
    // book3.addUserComment(user3.getId(), "A classic novel.");
    // book3.setrating(5.0);
    // book3.setNumberOfUsers(book3.getNumberOfUsers() + 1);
    

    // book7.addUserComment(user4.getId(), "Must-read for every programmer.");
    // book7.setrating(4.8);
    // book7.setNumberOfUsers(book7.getNumberOfUsers() + 1);
    

    // book8.addUserComment(user5.getId(), "Interesting read.");
    // book8.setrating(4.0);
    // book8.setNumberOfUsers(book8.getNumberOfUsers() + 1);
    
    // book6.setrating(3.4);
    // book2.setrating(1.6);
    // // Add books to appState
    // bookManager.addBook(book1);
    // bookManager.addBook(book2);
    // bookManager.addBook(book3);
    // bookManager.addBook(book4);
    // bookManager.addBook(book5);
    // bookManager.addBook(book6);
    // bookManager.addBook(book7);
    // bookManager.addBook(book8);
    // bookManager.addBook(book9);
    // bookManager.addBook(book10);

    // // Create 4 loans (max 2 loans per user) with end dates from September to October this year
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Loan loan1 = new Loan(user1.getId(), book1.getUuid(), LocalDate.of(2024, 9, 15).format(formatter));
    // Loan loan2 = new Loan(user1.getId(), book2.getUuid(), LocalDate.of(2024, 9, 25).format(formatter));
    // Loan loan3 = new Loan(user2.getId(), book3.getUuid(), LocalDate.of(2024, 10, 5).format(formatter));
    // Loan loan4 = new Loan(user3.getId(), book4.getUuid(), LocalDate.of(2024, 10, 15).format(formatter));
    // book1.setCopyNumber(book1.getCopyNumber()-1);
    // book2.setCopyNumber(book2.getCopyNumber()-1);
    // book3.setCopyNumber(book3.getCopyNumber()-1);
    // book4.setCopyNumber(book4.getCopyNumber()-1);


    // // Add loans to appState
    // loanManager.addLoan(loan1);
    // loanManager.addLoan(loan2);
    // loanManager.addLoan(loan3);
    // loanManager.addLoan(loan4);

    


    scene = new Scene(loadFXML("start"), 640, 480);
    stage.setScene(scene);
    stage.show();
}

    
    /** 
     * This method allows the scene to change view.
     * 
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    
    /** 
     * This method changes the scene.
     * @param root
     */
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    
    /** 
     * This method fetches the view from the resources and initializes its controller.
     * @param fxml
     * @return Parent
     * @throws IOException
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/"+fxml + ".fxml"));
        // fxmlLoader.setControllerFactory(controller -> new YourController(appState));
 
        return fxmlLoader.load();
    }


    
/**
 * Retrieves the singleton instance of the application's state.
 *
 * <p>The {@code AppState} class is designed as a singleton to maintain a single instance of the application's state
 * throughout its execution. This method provides access to the shared instance of the application's state.
 *
 * @return The singleton instance of the application's state.
 */
    public static AppState getAppState() {
        return appState;
    }
    
    
    
/**
 * The main entry point of the JavaFX application.
 *
 * <p>This method is responsible for launching the JavaFX application and initializing its primary stage.
 * The {@code launch()} method is a static method provided by the {@code javafx.application.Application} class,
 * which is overridden in this class to define the application's behavior.
 *
 * <p>The {@code main()} method takes an array of strings as a parameter, which is typically used to pass command-line arguments
 * to the application. In this case, the array is not used, so it is declared as {@code String[] args}.
 *
 * @param args An array of strings representing command-line arguments. In this case, it is not used.
 */
    public static void main(String[] args) {
        launch();
    }
/**
 * Serializes all the managers into files uppon the close of the app so that no information is lost. Then close the App.
 *
 * 
 */
    @Override
    public void stop() {
       
        List<Book> bookList = new ArrayList<>(appState.getBookManager().getBookList());
        try{
        serializationService.serializeBooks(bookList,"books.ser");
        }catch(IOException e){
            System.err.println("Error serializing books: "+e.getMessage());
        }
        List<Loan> loanList = new ArrayList<>(appState.getLoanManager().getLoanList());
        try{
        serializationService.serializeLoans(loanList,"loans.ser");
        }catch(IOException e){
            System.err.println("Error serializing loans: "+e.getMessage());
        }
        List<User> userList = new ArrayList<>(appState.getUserManager().getUserList());
        try{
        serializationService.serializeUsers(userList,"users.ser");
        }catch(IOException e){
            System.err.println("Error serializing users: "+e.getMessage());
        }
        System.out.println("Application is closing...");
        List<User> adminList = new ArrayList<>(appState.getAdminManager().getAdminList());
        try{
        serializationService.serializeUsers(adminList,"admins.ser");
        }catch(IOException e){
            System.err.println("Error serializing users: "+e.getMessage());
        }
        
        
        
    }


}