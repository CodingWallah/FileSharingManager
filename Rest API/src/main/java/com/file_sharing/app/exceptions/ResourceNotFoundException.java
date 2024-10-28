package com.file_sharing.app.exceptions;

<<<<<<< HEAD
/**
 * Custom exception to indicate that a requested resource was not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Default constructor that uses a standard message.
     */
    public ResourceNotFoundException() {
        super("Resource Not Found");
    }

    /**
     * Constructor that accepts a custom message.
     *
     * @param message the custom message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor that accepts a custom message and a cause.
     *
     * @param message the custom message.
     * @param cause the cause of the exception.
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor that accepts a cause.
     *
     * @param cause the cause of the exception.
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
=======
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource Not Found");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
