package com.library.Services;

import com.library.Models.*;




import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
/**
 * A service for serializing and deserializing objects using Java serialization.
 * Provides methods for saving and loading data to and from files.
 */
public class SerializationService implements SerializationServiceInterface {
    
    /**
 * Serializes a list of books to the specified file path using Java serialization.
 *
 * @param books The list of books to serialize.
 * @param filePath The file path to save the serialized data.
 * @throws IOException If an I/O error occurs during serialization.
 */
    @Override
    public void serializeBooks(List<Book> books, String filePath) throws IOException {
        
    
    
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("medialab/"+filePath,false))) {
            oos.writeObject(books);
            
        }
    }
    /**
 * Deserializes a list of books from the specified file path using Java serialization.
 *
 * @param filePath The file path to load the serialized data.
 * @return The deserialized list of books.
 * @throws IOException If an I/O error occurs during deserialization.
 * @throws ClassNotFoundException If the class of the serialized object cannot be found.
 */
    @Override
    public List<Book> deserializeBooks(String filePath) throws IOException, ClassNotFoundException {
        List<Book> books=null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("medialab/"+filePath))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<Book> tempBooks = (List<Book>) obj;
                books = tempBooks;
            
            }
            return books;
        }
    }
    /**
 * Serializes a list of users to the specified file path using Java serialization.
 *
 * @param users The list of users to serialize.
 * @param filePath The file path to save the serialized data.
 * @throws IOException If an I/O error occurs during serialization.
 */
    @Override
    public void serializeUsers(List<User> users, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("medialab/"+filePath,false))){ {
            oos.writeObject(users);
        }
    }
}
    /**
 * Deserializes a list of users from the specified file path using Java serialization.
 *
 * @param filePath The file path to load the serialized data.
 * @return The deserialized list of users.
 * @throws IOException If an I/O error occurs during deserialization.
 * @throws ClassNotFoundException If the class of the serialized object cannot be found.
 */
    @Override
    public  List<User> deserializeUsers(String filePath) throws IOException, ClassNotFoundException {
        List<User>users=null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("medialab/"+filePath))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<User> tempUsers = (List<User>) obj;
                users = tempUsers;
            
            }
            return users;
        }
    }
    /**
 * Serializes a list of loans to the specified file path using Java serialization.
 *
 * @param loans The list of loans to serialize.
 * @param filePath The file path to save the serialized data.
 * @throws IOException If an I/O error occurs during serialization.
 */
    @Override
    public void serializeLoans(List<Loan> loans, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("medialab/"+filePath,false))) {
            oos.writeObject(loans);
        }
    }
    /**
 * Deserializes a list of loans from the specified file path using Java serialization.
 *
 * @param filePath The file path to load the serialized data.
 * @return The deserialized list of loans.
 * @throws IOException If an I/O error occurs during deserialization.
 * @throws ClassNotFoundException If the class of the serialized object cannot be found.
 */
    @Override
    public List<Loan> deserializeLoans(String filePath) throws IOException, ClassNotFoundException {
        List<Loan> loans=null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("medialab/"+filePath))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<Loan> tempLoans = (List<Loan>) obj;
                loans = tempLoans;
            
            }
            return loans;
           
        }
    }

}
