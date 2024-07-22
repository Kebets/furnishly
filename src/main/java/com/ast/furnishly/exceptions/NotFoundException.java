package com.ast.furnishly.exceptions;

/**
 * Exception thrown when a resource is not found.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new `NotFoundException` with the specified error message.
     *
     * @param message The error message.
     */
    public NotFoundException(String message){super(message);}
}
