package org.launchcode.marketplace.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {

        try (InputStream input = new FileInputStream("/src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            final String CONNECTION_URL = properties.getProperty("spring.datasource.url");
            final String USER = properties.getProperty("spring.datasource.username");
            final String PASS = properties.getProperty("spring.datasource.password");

            return DriverManager.getConnection(CONNECTION_URL, USER, PASS);


        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file.");

        }
    }
}




