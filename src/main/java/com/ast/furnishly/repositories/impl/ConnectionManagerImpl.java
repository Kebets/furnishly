package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.configs.PropertiesLoader;
import com.ast.furnishly.exceptions.DataBaseDriverLoadException;
import com.ast.furnishly.repositories.ConnectionManager;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class ConnectionManagerImpl implements ConnectionManager {
    private static final String DRIVER_CLASS_KEY = "db.driver-class-name";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static ConnectionManager connectionManager;

    public static synchronized ConnectionManager getConnectionManager() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerImpl();
            loadDriver(PropertiesLoader.getProperties(DRIVER_CLASS_KEY));
        }
        return connectionManager;
    }

    private static void loadDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new DataBaseDriverLoadException("Database driver not loaded.");
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertiesLoader.getProperties(URL_KEY),
                PropertiesLoader.getProperties(USERNAME_KEY),
                PropertiesLoader.getProperties(PASSWORD_KEY)
        );
    }
}
