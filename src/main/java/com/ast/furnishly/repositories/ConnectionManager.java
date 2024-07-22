package com.ast.furnishly.repositories;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Generic interface for managing connections.
 * Implementations of this interface allow resource adapters to provide connections to the application server.
 */
public interface ConnectionManager {
    /**
     * Retrieves a connection.
     *
     * @return A database connection.
     * @throws SQLException If an error occurs while obtaining the connection.
     */
    Connection getConnection() throws SQLException;
}
