package com.example.cinema.model.dao;

import com.example.cinema.model.dao.exceptions.DaoConfigurationException;

import java.io.*;
import java.util.Properties;

public class DaoProperties {

    private static final String PROPERTIES_FILE = "../../src/main/resources/dbConfig.properties";
    private static final Properties PROPERTIES = new Properties();


    public DaoProperties() throws DaoConfigurationException {

        InputStream propertiesFile = null;
        try {
            propertiesFile = new FileInputStream(PROPERTIES_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (propertiesFile == null) {
            throw new DaoConfigurationException(
                    "Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DaoConfigurationException(
                    "Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    public String getProperty(String key, boolean mandatory) throws DaoConfigurationException {
        String property = PROPERTIES.getProperty(key);

        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new DaoConfigurationException("Required property '" + key + "'"
                        + " is missing in properties file '" + PROPERTIES_FILE + "'.");
            } else {
                property = null;
            }
        }

        return property;
    }

}
