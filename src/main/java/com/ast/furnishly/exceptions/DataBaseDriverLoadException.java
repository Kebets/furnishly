package com.ast.furnishly.exceptions;

/**
 * Exception thrown when there is an error loading a database driver.
 */
public class DataBaseDriverLoadException extends RuntimeException {

    /**
     * Constructs a new `DataBaseDriverLoadException` with the specified error message.
     *
     * @param message The error message.
     */
    public DataBaseDriverLoadException(String message) {
        super(message);
    }
}
