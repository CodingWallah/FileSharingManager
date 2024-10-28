package com.file_sharing.app.exceptions;

<<<<<<< HEAD
/**
 * Custom exception class for handling file-related exceptions in the application.
 */
public class FileExceptions extends RuntimeException {
    
    /** Default constructor that calls the parent class constructor. */
    public FileExceptions() {
        super();
    }

    /** 
     * Constructor that accepts a custom error message.
     * 
     * @param message the detail message to be included with the exception.
     */
=======
public class FileExceptions extends RuntimeException {
    public FileExceptions() {
        super();
    }
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    public FileExceptions(String message) {
        super(message);
    }
}
