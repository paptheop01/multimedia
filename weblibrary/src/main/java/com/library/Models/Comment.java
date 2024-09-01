package com.library.Models;
import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a comment in a library system.
 * Implements the Serializable interface to allow for object serialization.
 */
public class Comment implements Serializable {
    private UUID uuid;
    private String commentText;

    /**
     * Constructs a new Comment object with the given UUID and comment text.
     *
     * @param uuid The unique identifier for the user who made the comment.
     * @param commentText The text content of the comment.
     */
    public Comment(UUID uuid, String commentText) {
        this.uuid = uuid;
        this.commentText = commentText;
    }

    /**
     * Retrieves the unique identifier for the user who made the comment.
     *
     * @return The UUID of the user.
     */
    public UUID getUserid() {
        return uuid;
    }

    /**
     * Retrieves the text content of the comment.
     *
     * @return The comment text.
     */
    public String getCommentText() {
        return commentText;
    }
}

