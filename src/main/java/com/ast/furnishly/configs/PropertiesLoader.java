package com.ast.furnishly.configs;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility for loading properties from the `db.properties` file.
 */
public class PropertiesLoader {
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "db.properties";

    static {
        loadProperties();
    }

    private PropertiesLoader() {
    }

    /**
     * Retrieves the value of a property based on the provided key.
     *
     * @param key The property key.
     * @return The property value, or `null` if the property is not found.
     */
    public static String getProperties(String key) {
        return PROPERTIES.getProperty(key);
    }

    /**
     * Loads properties from the PROPERTIES_FILE file.
     */
    private static void loadProperties() {
        try (InputStream inFile = PropertiesLoader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            PROPERTIES.load(inFile);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
