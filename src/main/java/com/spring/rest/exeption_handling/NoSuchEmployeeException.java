package com.spring.rest.exeption_handling;

/**
 * NoSuchEmployeeException exception class that trow Object with Message
 */
public class NoSuchEmployeeException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
