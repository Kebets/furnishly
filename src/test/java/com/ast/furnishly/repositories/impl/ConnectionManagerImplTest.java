package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.configs.PropertiesLoader;
import com.ast.furnishly.repositories.ConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerImplTest {

    private ConnectionManager connectionManager;

    @BeforeEach
    void setUp() {
        connectionManager = ConnectionManagerImpl.getConnectionManager();
    }

    @Test
    void testGetConnection() throws SQLException {
        // Мокирование свойств
        String url = "jdbc:postgresql://localhost:5432/mydb"; // Измените на свой URL PostgreSQL
        String username = "myuser";
        String password = "mypassword";

        PropertiesLoader mockPropertiesLoader = mock(PropertiesLoader.class);
        when(mockPropertiesLoader.getProperties("db.url")).thenReturn(url);
        when(mockPropertiesLoader.getProperties("db.username")).thenReturn(username);
        when(mockPropertiesLoader.getProperties("db.password")).thenReturn(password);

        // Установка мокированного загрузчика свойств
//        ConnectionManagerImpl.setPropertiesLoader(mockPropertiesLoader);

        // Тестирование getConnection
        Connection connection = connectionManager.getConnection();

        assertNotNull(connection, "Соединение не должно быть null");
        assertEquals(url, connection.getMetaData().getURL(), "URL должен совпадать");
        assertEquals(username, connection.getMetaData().getUserName(), "Имя пользователя должно совпадать");
        // Дополнительные проверки по необходимости

        // Закрытие соединения
        connection.close();
    }

    @Test
    void testLoadDriver() {
        // Тестирование загрузки драйвера
//        assertDoesNotThrow(() -> ConnectionManagerImpl.loadDriver("org.postgresql.Driver"),
//                "Драйвер должен загружаться без ошибок");
    }

}
