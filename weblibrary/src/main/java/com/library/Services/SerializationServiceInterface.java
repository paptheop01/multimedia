package com.library.Services;

import com.library.Models.*;



import java.io.IOException;
import java.util.List;
/**
 * An interface for serializing and deserializing objects using Java serialization.
 * Provides methods for saving and loading data to and from files.
 */
public interface SerializationServiceInterface {
    /**
     * Serializes a list of books to the specified file path using Java serialization.
     *
     * @param books The list of books to serialize.
     * @param filePath The file path to save the serialized data.
     * @throws IOException If an I/O error occurs during serialization.
     */
    void serializeBooks(List<Book> books, String filePath) throws IOException;
    /**
     * Deserializes a list of books from the specified file path using Java serialization.
     *
     * @param filePath The file path to load the serialized data.
     * @return The deserialized list of books.
     * @throws IOException If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    List<Book> deserializeBooks(String filePath) throws IOException, ClassNotFoundException;
    /**
     * Serializes a list of users to the specified file path using Java serialization.
     *
     * @param users The list of users to serialize.
     * @param filePath The file path to save the serialized data.
     * @throws IOException If an I/O error occurs during serialization.
     */
    void serializeUsers(List<User> users, String filePath) throws IOException;
    /**
     * Deserializes a list of users from the specified file path using Java serialization.
     *
     * @param filePath The file path to load the serialized data.
     * @return The deserialized list of users.
     * @throws IOException If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    List<User> deserializeUsers(String filePath) throws IOException, ClassNotFoundException;
    /**
     * Serializes a list of loans to the specified file path using Java serialization.
     *
     * @param loans The list of loans to serialize.
     * @param filePath The file path to save the serialized data.
     * @throws IOException If an I/O error occurs during serialization.
     */
    void serializeLoans(List<Loan> loops, String filePath) throws IOException;
     /**
     * Deserializes a list of loans from the specified file path using Java serialization.
     *
     * @param filePath The file path to load the serialized data.
     * @return The deserialized list of loans.
     * @throws IOException If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    List<Loan> deserializeLoans(String filePath) throws IOException, ClassNotFoundException;
}
