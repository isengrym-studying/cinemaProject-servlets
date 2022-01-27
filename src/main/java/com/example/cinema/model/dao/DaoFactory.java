package com.example.cinema.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoFactory {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    public static DaoFactory getInstance(String name) throws DaoConfigurationException {
        if (name == null) {
            throw new DaoConfigurationException("Database name is null.");
        }

        DaoProperties properties = new DaoProperties();
        String url = properties.getProperty(PROPERTY_URL, true);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER, false);
        String password = properties.getProperty(PROPERTY_PASSWORD, false);
        String username = properties.getProperty(PROPERTY_USERNAME, password != null);
        DaoFactory instance;


        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
                throw new DaoConfigurationException(
                        "Driver class '" + driverClassName + "' is missing in classpath.", e);
            }
        instance = new DriverManagerDaoFactory(url, username, password);
        return instance;
    }
    abstract Connection getDbConnection() throws SQLException;

}

class DriverManagerDaoFactory extends DaoFactory {
    private String url;
    private String username;
    private String password;

    DriverManagerDaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}